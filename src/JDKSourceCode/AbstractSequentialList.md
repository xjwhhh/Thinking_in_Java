#AbstractSequentialList源码分析

调用列表迭代器，返回index位置的元素
<pre><code>
    public E get(int index) {
        try {
            return listIterator(index).next();
        } catch (NoSuchElementException exc) {
            throw new IndexOutOfBoundsException("Index: "+index);
        }
    }
</code></pre>

调用列表迭代器，将index处的元素设置为element
<pre><code>
    public E set(int index, E element) {
        try {
            ListIterator<E> e = listIterator(index);
            E oldVal = e.next();
            e.set(element);
            return oldVal;
        } catch (NoSuchElementException exc) {
            throw new IndexOutOfBoundsException("Index: "+index);
        }
    }
</code></pre>

调用列表迭代器，在index处插入element
<pre><code>
    public void add(int index, E element) {
        try {
            listIterator(index).add(element);
        } catch (NoSuchElementException exc) {
            throw new IndexOutOfBoundsException("Index: "+index);
        }
    }
</code></pre>

调用列表迭代器，删除index处的元素，将后继元素左移，返回删除的元素
<pre><code>
    public E remove(int index) {
        try {
            ListIterator<E> e = listIterator(index);
            E outCast = e.next();
            e.remove();
            return outCast;
        } catch (NoSuchElementException exc) {
            throw new IndexOutOfBoundsException("Index: "+index);
        }
    }
</code></pre>

获取原列表的列表迭代器和插入列表的迭代器，从index处开始一个个插入新元素，顺序与插入列表迭代器的返回顺序相同。如果在执行这个函数的过程中插入列表被修改了，这个操作就是undefined
<pre><code>
    public boolean addAll(int index, Collection<? extends E> c) {
        try {
            boolean modified = false;
            ListIterator<E> e1 = listIterator(index);
            Iterator<? extends E> e2 = c.iterator();
            while (e2.hasNext()) {
                e1.add(e2.next());
                modified = true;
            }
            return modified;
        } catch (NoSuchElementException exc) {
            throw new IndexOutOfBoundsException("Index: "+index);
        }
    }
</code></pre>

<pre><code>
    public Iterator<E> iterator() {
        return listIterator();
    }
</code></pre>

<pre><code>
public abstract ListIterator<E> listIterator(int index);
</code></pre>







