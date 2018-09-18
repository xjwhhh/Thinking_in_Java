#Collection接口源码分析

    int size();
获取集合长度

    boolean isEmpty();
集合是否不包含任何元素

    boolean contains(Object o);
集合是否含有该元素
(o==null?e==null:o.equals(e))

    Iterator<E> iterator();
返回了一个迭代器，这个迭代器的元素顺序是否有保证由各实现类自己决定

    Object[] toArray();
返回一个包含集合所有元素的数组，如果这个集合的迭代器保证元素顺序，则这个数组的顺序与之一样

这个返回的数组是一个新数组，即是安全的，可以自由更改，对原来的集合没有影响

    <T> T[] toArray(T[] a);
如果集合与数组的类型兼容，则返回含有该集合元素的数组，否则新分配一个数组，元素类型是运行时类型，长度是集合长度

如果数组长度比集合长度长，则在集合元素之后添加null，如果该集合不存在null值，可以用来获得集合的长度

如果这个集合的迭代器保证元素顺序，则这个数组的顺序与之一样

    boolean add(E e);
基于不同的集合，有些集合不允许增加null，有些对于插入元素的类型有要求，如果增加成功，返回true，否则返回false。

如果一个集合因为该集合已含有此元素之外的原因拒绝增加元素，则抛出异常，为的是保证该集合在调用这个方法后一定含有此元素

    boolean containsAll(Collection<?> c);
是否含有c集合中所有元素

    boolean addAll(Collection<? extends E> c);
将集合c中所有元素加入集合中

    boolean removeAll(Collection<?> c);
将集合c中所有元素从集合中删除

    default boolean removeIf(Predicate<? super E> filter) {
        Objects.requireNonNull(filter);
        boolean removed = false;
        final Iterator<E> each = iterator();
        while (each.hasNext()) {
            if (filter.test(each.next())) {
                each.remove();
                removed = true;
            }
        }
        return removed;
    }
将所有满足给定断言的元素从集合中删除

    boolean retainAll(Collection<?> c);
如果元素在集合c中，则保留，否则删除

    void clear();
将集合中所有元素删除

    boolean equals(Object o);
程序员如果写直接继承Collection类的类，即没有继承List或者Set，必须考虑要不要重写equals方法，因为有时的实现要求比较值，有时要比较引用

只有list才能与list相等，也只有set才能与set相等，这也说明了没有一个类可以既实现list接口，又实现set接口

    int hashCode();
返回该集合的哈希码

如果重写了equals方法，则必须重写hashCode方法

c1.equals(c2)等价于c1.hashCode()==c2.hashCode()

    @Override
    default Spliterator<E> spliterator() {
        return Spliterators.spliterator(this, 0);
    }
返回一个并行迭代器

    default Stream<E> stream() {
        return StreamSupport.stream(spliterator(), false);
    }
返回一个以集合作为资源的顺序流

    default Stream<E> parallelStream() {
        return StreamSupport.stream(spliterator(), true);
    }
返回一个以集合作为资源的，尽可能平行的数据流







