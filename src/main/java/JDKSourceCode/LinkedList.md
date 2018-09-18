#LinkedList源码分析

双向链表

不是同步的

<pre><code>
transient int size = 0;

//指向第一个结点的指针
    /**
     * Pointer to first node.
     * Invariant: (first == null && last == null) ||
     *            (first.prev == null && first.item != null)
     */
    transient Node<E> first;

//指向最后一个结点的指针
    /**
     * Pointer to last node.
     * Invariant: (first == null && last == null) ||
     *            (last.next == null && last.item != null)
     */
    transient Node<E> last;
</code></pre>


    public LinkedList() {
    }

使用集合创建列表，列表含有集合中所有元素，顺序与集合迭代器返回的元素顺序相同

    public LinkedList(Collection<? extends E> c) {
        this();
        addAll(c);
    }

将参数元素连接到首位

    private void linkFirst(E e) {
        final Node<E> f = first;
        final Node<E> newNode = new Node<>(null, e, f);
        first = newNode;
        if (f == null)
            last = newNode;
        else
            f.prev = newNode;
        size++;
        modCount++;
    }

将参数元素连接到最后一位
    
    void linkLast(E e) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, e, null);
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.next = newNode;
        size++;
        modCount++;
    }

在第二个参数节点之前插入一个新节点
1. 创建newNode节点，将newNode的后继指针指向succ，前驱指针指向pred 
2. 将succ的前驱指针指向newNode 
3. 根据pred是否为null，进行不同操作。
- 如果pred为null，说明该节点插入在头节点之前，要重置first头节点 
- 如果pred不为null，那么直接将pred的后继指针指向newNode即可 

    
    void linkBefore(E e, Node<E> succ) {
        // assert succ != null;
        final Node<E> pred = succ.prev;
        final Node<E> newNode = new Node<>(pred, e, succ);
        succ.prev = newNode;
        if (pred == null)
            first = newNode;
        else
            pred.next = newNode;
        size++;
        modCount++;
    }

解除第一个元素的链接

    private E unlinkFirst(Node<E> f) {
        // assert f == first && f != null;
        final E element = f.item;
        final Node<E> next = f.next;
        f.item = null;
        f.next = null; // help GC
        first = next;
        if (next == null)
            last = null;
        else
            next.prev = null;
        size--;
        modCount++;
        return element;
    }

解除最后一个元素的链接

    private E unlinkLast(Node<E> l) {
        // assert l == last && l != null;
        final E element = l.item;
        final Node<E> prev = l.prev;
        l.item = null;
        l.prev = null; // help GC
        last = prev;
        if (prev == null)
            first = null;
        else
            prev.next = null;
        size--;
        modCount++;
        return element;
    }

删除某个元素的链接

    E unlink(Node<E> x) {
        // assert x != null;
        final E element = x.item;
        final Node<E> next = x.next;
        final Node<E> prev = x.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.item = null;
        size--;
        modCount++;
        return element;
    }

返回列表的第一个节点

    public E getFirst() {
        final Node<E> f = first;
        if (f == null)
            throw new NoSuchElementException();
        return f.item;
    }

返回列表的最后一个节点

    public E getLast() {
        final Node<E> l = last;
        if (l == null)
            throw new NoSuchElementException();
        return l.item;
    }

删除并返回列表的第一个节点

    public E removeFirst() {
        final Node<E> f = first;
        if (f == null)
            throw new NoSuchElementException();
        return unlinkFirst(f);
    }

删除并返回列表的最后一个节点

    public E removeLast() {
        final Node<E> l = last;
        if (l == null)
            throw new NoSuchElementException();
        return unlinkLast(l);
    }

将参数元素连接到首位

    public void addFirst(E e) {
        linkFirst(e);
    }

将参数元素连接到最后一位

    public void addLast(E e) {
        linkLast(e);
    }

将参数元素连接到列表最后一位，与addLast效果相同

    public boolean add(E e) {
        linkLast(e);
        return true;
    }

如果列表中含有参数元素，则删除它的第一次出现

    public boolean remove(Object o) {
        if (o == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.item == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (o.equals(x.item)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

将集合中的元素全部加入列表中

    public boolean addAll(Collection<? extends E> c) {
        return addAll(size, c);
    }


在指定位置处，插入集合中的所有元素，顺序与集合迭代器返回元素顺序相同
1. 检查index索引范围 
2. 得到集合数据 
3. 得到插入位置的前驱和后继节点 
4. 遍历数据，将数据插入到指定位置


    public boolean addAll(int index, Collection<? extends E> c) {
        checkPositionIndex(index);

        Object[] a = c.toArray();
        int numNew = a.length;
        if (numNew == 0)
            return false;

        Node<E> pred, succ;
        if (index == size) {
            succ = null;
            pred = last;
        } else {
            succ = node(index);
            pred = succ.prev;
        }

        for (Object o : a) {
            @SuppressWarnings("unchecked") E e = (E) o;
            Node<E> newNode = new Node<>(pred, e, null);
            if (pred == null)
                first = newNode;
            else
                pred.next = newNode;
            pred = newNode;
        }

        if (succ == null) {
            last = pred;
        } else {
            pred.next = succ;
            succ.prev = pred;
        }

        size += numNew;
        modCount++;
        return true;
    }


1. 检查index的范围，否则抛出异常 
2. 如果插入位置是链表尾部，那么调用linkLast方法 
3. 如果插入位置是链表中间，那么调用linkBefore方法 

    
    public void add(int index, E element) {
        checkPositionIndex(index);

        if (index == size)
            linkLast(element);
        else
            linkBefore(element, node(index));
    }

返回链表的头部结点

    public E peek() {
        final Node<E> f = first;
        return (f == null) ? null : f.item;
    }

返回链表的头部结点

    public E element() {
        return getFirst();
    }

删除并返回链表的头部结点

    public E poll() {
        final Node<E> f = first;
        return (f == null) ? null : unlinkFirst(f);
    }

删除并返回链表的头部结点

    public E remove() {
        return removeFirst();
    }

将参数元素连接到链表尾部

    public boolean offer(E e) {
        return add(e);
    }

将参数元素连接到链表头部

    public boolean offerFirst(E e) {
        addFirst(e);
        return true;
    }

将参数元素连接到链表尾部

    public boolean offerLast(E e) {
        addLast(e);
        return true;
    }

返回链表的头部结点

    public E peekFirst() {
        final Node<E> f = first;
        return (f == null) ? null : f.item;
     }

返回链表的尾部结点

    public E peekLast() {
        final Node<E> l = last;
        return (l == null) ? null : l.item;
    }

删除并返回链表的头部结点

    public E pollFirst() {
        final Node<E> f = first;
        return (f == null) ? null : unlinkFirst(f);
    }

删除并返回链表的头部结点

    public E pollLast() {
        final Node<E> l = last;
        return (l == null) ? null : unlinkLast(l);
    }

将参数元素连接到链表头部

    public void push(E e) {
        addFirst(e);
    }

删除并返回链表的头部结点

    public E pop() {
        return removeFirst();
    }

删除参数元素在链表中的第一次出现

    public boolean removeFirstOccurrence(Object o) {
        return remove(o);
    }

删除参数元素在链表中的最后一次出现

    public boolean removeLastOccurrence(Object o) {
        if (o == null) {
            for (Node<E> x = last; x != null; x = x.prev) {
                if (x.item == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node<E> x = last; x != null; x = x.prev) {
                if (o.equals(x.item)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

浅拷贝

    public Object clone() {
        LinkedList<E> clone = superClone();

        // Put clone into "virgin" state
        clone.first = clone.last = null;
        clone.size = 0;
        clone.modCount = 0;

        // Initialize clone with our elements
        for (Node<E> x = first; x != null; x = x.next)
            clone.add(x.item);

        return clone;
    }







































































