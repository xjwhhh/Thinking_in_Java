#List接口源码分析

    default void replaceAll(UnaryOperator<E> operator) {
        Objects.requireNonNull(operator);
        final ListIterator<E> li = this.listIterator();
        while (li.hasNext()) {
            li.set(operator.apply(li.next()));
        }
    }
对列表中的所有元素进行operator这样的操作，并用结果代替原来的元素

    @SuppressWarnings({"unchecked", "rawtypes"})
    default void sort(Comparator<? super E> c) {
        Object[] a = this.toArray();
        Arrays.sort(a, (Comparator) c);
        ListIterator<E> i = this.listIterator();
        for (Object e : a) {
            i.next();
            i.set((E) e);
        }
    }
排序操作，对元素进行互相比较，比较方式按照Comparator定义的来，如果Comparator是null，那么需要列表中的所有元素实现了Comparable接口，就可以使用元素的比较方式

    E get(int index);
通过索引获取元素，返回该元素

    E set(int index, E element);
将index处的元素设置为element，返回index处原来的元素

    void add(int index, E element);
将element插入到列表的index位置，将index处原来的元素和后继元素全部右移，长度加一

    E remove(int index);
删除index处的元素，将后继元素全部左移，返回被删除的元素

    int indexOf(Object o);
如果列表含有该对象，则返回它在列表中第一次出现的索引，否则返回-1

    int lastIndexOf(Object o);
如果列表含有该对象，则返回它在列表中最后一次出现的索引，否则返回-1

    ListIterator<E> listIterator();
返回一个列表迭代器

    ListIterator<E> listIterator(int index);
返回一个列表迭代器，从index开始，即第一个调用ListIterator.next()返回的应该是index处的元素

    List<E> subList(int fromIndex, int toIndex);
返回原来list的从[fromIndex, toIndex)之间这一部分的视图，之所以说是视图，是因为实际上，返回的list是靠原来的list支持的。

所以，你对原来的list和返回的list做的“非结构性修改”(non-structural changes)，都会影响到彼此。

所谓的“非结构性修改”，是指不涉及到list的大小改变的修改。相反，结构性修改，指改变了list大小的修改。

如果发生结构性修改的是返回的子list，那么原来的list的大小也会发生变化；

而如果发生结构性修改的是原来的list（不包括由于返回的子list导致的改变），那么返回的子list语义上将会是undefined。在AbstractList（ArrayList的父类）中，undefined的具体表现形式是抛出一个ConcurrentModificationException。

因此，如果你在调用了sublist返回了子list之后，如果修改了原list的大小，那么之前产生的子list将会失效，变得不可使用。














