#LinkedHashMap源码分析

LinkedHashMap继承了HashMap，所以HashMap的一些方法或者属性也会被继承；同时也实现了Map结构

LinkedHashMap保存着元素插入的顺序，并且可以按照我们插入的顺序进行访问

LinkedHashMap会将元素串起来，形成一个双链表结构。其结构在HashMap结构上增加了链表结构。数据结构为（数组 + 单链表 + 红黑树 + 双链表）

LinkedHashMap具有可预知的迭代顺序，根据链表中元素的顺序可以分为：按插入顺序的链表，和按访问顺序(调用get方法)的链表。
  
默认是按插入顺序排序，如果指定按访问顺序排序，那么调用get方法后，会将这次访问的元素移至链表尾部，不断访问可以形成按访问顺序排序的链表。

<pre><code>
    public LinkedHashMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
        accessOrder = false;
    }

    /**
     * Constructs an empty insertion-ordered <tt>LinkedHashMap</tt> instance
     * with the specified initial capacity and a default load factor (0.75).
     *
     * @param  initialCapacity the initial capacity
     * @throws IllegalArgumentException if the initial capacity is negative
     */
    public LinkedHashMap(int initialCapacity) {
        super(initialCapacity);
        accessOrder = false;
    }

    /**
     * Constructs an empty insertion-ordered <tt>LinkedHashMap</tt> instance
     * with the default initial capacity (16) and load factor (0.75).
     */
    public LinkedHashMap() {
        super();
        accessOrder = false;
    }
</code></pre>

    public LinkedHashMap(Map<? extends K, ? extends V> m) {
        super();
        accessOrder = false;
        putMapEntries(m, false);
    }
putMapEntries是调用到父类HashMap的函数

    public LinkedHashMap(int initialCapacity,
                         float loadFactor,
                         boolean accessOrder) {
        super(initialCapacity, loadFactor);
        this.accessOrder = accessOrder;
    }
可以指定accessOrder的值，从而控制访问顺序,true为访问顺序，false为插入顺序

按照访问的次序来排序的含义:当调用LinkedHashMap的get(key)或者put(key, value)时，碰巧key在map中被包含，那么LinkedHashMap会将key对象的entry放在线性结构的最后。

按照插入顺序来排序的含义:调用get(key), 或者put(key, value)并不会对线性结构产生任何的影响。


    private void linkNodeLast(LinkedHashMap.Entry<K,V> p) {
        LinkedHashMap.Entry<K,V> last = tail;
        tail = p;
        if (last == null)
            head = p;
        else {
            p.before = last;
            last.after = p;
        }
    }
将结点链接到双链表的末尾，维护插入顺序


    // apply src's links to dst
    private void transferLinks(LinkedHashMap.Entry<K,V> src,
                               LinkedHashMap.Entry<K,V> dst) {
        LinkedHashMap.Entry<K,V> b = dst.before = src.before;
        LinkedHashMap.Entry<K,V> a = dst.after = src.after;
        if (b == null)
            head = dst;
        else
            b.after = dst;
        if (a == null)
            tail = dst;
        else
            a.before = dst;
    }
此函数用dst结点替换结点，只考虑了before与after域，并没有考虑next域，next会在调用transferLinks函数中进行设定。



    void reinitialize() {
        super.reinitialize();
        head = tail = null;
    }
初始化，调用父类的初始化，双向链表的头尾结点都设为null

    Node<K,V> newNode(int hash, K key, V value, Node<K,V> e) {
        LinkedHashMap.Entry<K,V> p =
            new LinkedHashMap.Entry<K,V>(hash, key, value, e);
        linkNodeLast(p);
        return p;
    }
此函数在HashMap类中也有实现，LinkedHashMap重写了该函数，所以当实际对象为LinkedHashMap，桶中结点类型为Node时，我们调用的是LinkedHashMap的newNode函数，而非HashMap的函数，newNode函数会在调用put函数时被调用。

LinkedHashMap并没有重写HashMap的put方法，而是重写了newNode，调用linkNodeLast()来实现对双向链表的维护

可以看到，除了新建一个结点之外，还把这个结点链接到双链表的末尾了，这个操作维护了插入顺序。

<pre><code>
    Node<K,V> replacementNode(Node<K,V> p, Node<K,V> next) {
        LinkedHashMap.Entry<K,V> q = (LinkedHashMap.Entry<K,V>)p;
        LinkedHashMap.Entry<K,V> t =
            new LinkedHashMap.Entry<K,V>(q.hash, q.key, q.value, next);
        transferLinks(q, t);
        return t;
    }
</code></pre>

    TreeNode<K,V> newTreeNode(int hash, K key, V value, Node<K,V> next) {
        TreeNode<K,V> p = new TreeNode<K,V>(hash, key, value, next);
        linkNodeLast(p);
        return p;
    }
当桶中结点类型为TreeNode时候，插入结点时调用的此函数，也会链接到末尾。

<pre><code>
    TreeNode<K,V> replacementTreeNode(Node<K,V> p, Node<K,V> next) {
        LinkedHashMap.Entry<K,V> q = (LinkedHashMap.Entry<K,V>)p;
        TreeNode<K,V> t = new TreeNode<K,V>(q.hash, q.key, q.value, next);
        transferLinks(q, t);
        return t;
    }
</code></pre>


    void afterNodeRemoval(Node<K,V> e) { // unlink
        LinkedHashMap.Entry<K,V> p =
            (LinkedHashMap.Entry<K,V>)e, b = p.before, a = p.after;
        // 将 p 节点的前驱后后继引用置空
        p.before = p.after = null;
        // b 为 null，表明 p 是头节点
        if (b == null)
            head = a;
        else
            b.after = a;
        // a 为 null，表明 p 是尾节点
        if (a == null)
            tail = b;
        else
            a.before = b;
    }
在删除节点后，回调方法 afterNodeRemoval 会被调用。LinkedHashMap 覆写该方法，并在该方法中完成了移除被删除节点的操作
1.根据 hash 定位到桶位置
2.遍历链表或调用红黑树相关的删除方法
3.从 LinkedHashMap 维护的双链表中移除要删除的节点

    void afterNodeInsertion(boolean evict) { // possibly remove eldest
        LinkedHashMap.Entry<K,V> first;
        if (evict && (first = head) != null && removeEldestEntry(first)) {
            K key = first.key;
            removeNode(hash(key), key, null, false, true);
        }
    }
根据条件判断是否移除最近最少被访问的节点


    void afterNodeAccess(Node<K,V> e) { // move node to last
        LinkedHashMap.Entry<K,V> last;
        if (accessOrder && (last = tail) != e) {
            LinkedHashMap.Entry<K,V> p =
                (LinkedHashMap.Entry<K,V>)e, b = p.before, a = p.after;
            p.after = null;
            if (b == null)
                head = a;
            else
                b.after = a;
            if (a != null)
                a.before = b;
            else
                last = b;
            if (last == null)
                head = p;
            else {
                p.before = last;
                last.after = p;
            }
            tail = p;
            ++modCount;
        }
    }
此函数在很多函数中都会被回调，LinkedHashMap重写了HashMap中的此函数。若访问顺序为true，且访问的对象不是尾结点，会将之链接到尾节点的后面，即用来更新访问顺序的
    
    public boolean containsValue(Object value) {
        for (LinkedHashMap.Entry<K,V> e = head; e != null; e = e.after) {
            V v = e.value;
            if (v == value || (value != null && value.equals(v)))
                return true;
        }
        return false;
    }
containsValue函数根据双链表结构来查找是否包含value，是按照插入顺序进行查找的，与HashMap中的此函数查找方式不同，HashMap是使用按照桶遍历，没有考虑插入顺序。
