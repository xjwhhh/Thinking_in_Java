#HashSet源码解析

HashSet类实现了Set。对于迭代器的顺序不做保证，并且不保证随着时间变化，顺序会保持一致。HashSet类允许null元素

在元素均匀分布的情况下，HashSet对于一些基础操作，如add,remove,contains,size提供了常数时间的操作。用迭代器遍历set所需要的时间与HashSet元素总数和哈希桶的数量成正比。如果遍历速度很重要的话，不要把set的初始容量设得太高

HashSet不是同步的

HashSet中只包含键，不包含值，在底层具体实现时，使用的HashMap或者是LinkedHashMap(可以指定构造函数来确定使用哪种结构)

HashMap是键值对存储，所以为了适应HashMap存储，HashSet增加了一个PRESENT类域，所有的键都有同一个值（PRESENT）

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

    public HashSet(Collection<? extends E> c) {
        map = new HashMap<>(Math.max((int) (c.size()/.75f) + 1, 16));
        addAll(c);
    }
构建一个新的set，含有参数集合中的所有元素

    public boolean add(E e) {
        return map.put(e, PRESENT)==null;
    }
调用map的put方法，如果set中不存在该值，即map中不存在该键，put返回的是null，则add返回true，如果存在，put返回的是之前的该键对应的值，则add返回false

    public boolean remove(Object o) {
        return map.remove(o)==PRESENT;
    }
调用map的remove方法，如果set中不存在该值，即map中不存在该键，map.remove返回的是null，则set.remove返回false，如果存在，map.remove返回的是之前的该键对应的值，则set.remove返回true


#LinkedHashSet

LinkedHashSet会调用HashSet的父类构造函数，让其底层实现为LinkedHashMap，这样就很好的实现了LinkedHashSet所需要的功能。

其余与HashSet类似