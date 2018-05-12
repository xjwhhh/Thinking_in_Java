#HashSet源码解析

HashSet类实现了Set。对于迭代器的顺序不做保证，并且不保证随着时间变化，顺序会保持一致。HashSet类允许null元素

在元素均匀分布的情况下，HashSet对于一些基础操作，如add,remove,contains,size提供了常数时间的操作。用迭代器遍历set所需要的时间与HashSet元素总数和哈希桶的数量成正比。如果遍历速度很重要的话，不要把set的初始容量设得太高

HashSet不是同步的

<pre></code>
    static final long serialVersionUID = -5024744406713321676L;

    private transient HashMap<E,Object> map;

    // Dummy value to associate with an Object in the backing Map
    private static final Object PRESENT = new Object();

    /**
     * Constructs a new, empty set; the backing <tt>HashMap</tt> instance has
     * default initial capacity (16) and load factor (0.75).
     */
    public HashSet() {
        map = new HashMap<>();
    }
</code></pre>