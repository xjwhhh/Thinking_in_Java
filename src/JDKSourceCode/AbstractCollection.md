#AbstractCollection源码分析

使用迭代器，如果存在元素e与参数o均为null或者e.equals(o)，则返回true
<pre><code>
    public boolean contains(Object o) {
        Iterator<E> it = iterator();
        if (o==null) {
            while (it.hasNext())
                if (it.next()==null)
                    return true;
        } else {
            while (it.hasNext())
                if (o.equals(it.next()))
                    return true;
        }
        return false;
    }
</code></pre>

返回一个数组，含有所有集合迭代器可以得到的元素，顺序与之一样，长度由迭代器返回的元素数量决定，即使在迭代时集合发生了改变也是这样。
初始化数组时调用了size()方法，是一种优化方法
<pre><code>
    public Object[] toArray() {
        // Estimate size of array; be prepared to see more or fewer elements
        Object[] r = new Object[size()];
        Iterator<E> it = iterator();
        for (int i = 0; i < r.length; i++) {
            if (! it.hasNext()) // fewer elements than expected
                return Arrays.copyOf(r, i);
            r[i] = it.next();
        }
        return it.hasNext() ? finishToArray(r, it) : r;
    }
</code></pre>

返回一个数组，含有所有集合迭代器可以得到的元素，顺序与之一样。
如果迭代器返回的元素数量比数组可以储存的多，则新创建一个数组来存放元素。长度由迭代器返回的元素数量决定，即使在迭代时集合发生了改变也是这样。
初始化数组时调用了size()方法，只是一种优化方法

注意如果在迭代时迭代器返回的元素数量发生了变化，这个方法的处理方式
<pre><code>
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        // Estimate size of array; be prepared to see more or fewer elements
        int size = size();
        T[] r = a.length >= size ? a :
                  (T[])java.lang.reflect.Array
                  .newInstance(a.getClass().getComponentType(), size);
        Iterator<E> it = iterator();

        for (int i = 0; i < r.length; i++) {
            if (! it.hasNext()) { // fewer elements than expected
                if (a == r) {
                    r[i] = null; // null-terminate
                } else if (a.length < i) {
                    return Arrays.copyOf(r, i);
                } else {
                    System.arraycopy(r, 0, a, 0, i);
                    if (a.length > i) {
                        a[i] = null;
                    }
                }
                return a;
            }
            r[i] = (T)it.next();
        }
        // more elements than expected
        return it.hasNext() ? finishToArray(r, it) : r;
    }
</code></pre>

如果迭代器返回的元素数量超过预期，需要重新分配数组，并填充元素
<pre><code>
    @SuppressWarnings("unchecked")
    private static <T> T[] finishToArray(T[] r, Iterator<?> it) {
        int i = r.length;
        while (it.hasNext()) {
            int cap = r.length;
            if (i == cap) {
                int newCap = cap + (cap >> 1) + 1;
                // overflow-conscious code
                if (newCap - MAX_ARRAY_SIZE > 0)
                    newCap = hugeCapacity(cap + 1);
                r = Arrays.copyOf(r, newCap);
            }
            r[i++] = (T)it.next();
        }
        // trim if overallocated
        return (i == r.length) ? r : Arrays.copyOf(r, i);
    }

    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError
                ("Required array size too large");
        return (minCapacity > MAX_ARRAY_SIZE) ?
            Integer.MAX_VALUE :
            MAX_ARRAY_SIZE;
    }
</code></pre>

<pre><code>
    public boolean add(E e) {
        throw new UnsupportedOperationException();
    }
</code></pre>

使用迭代器遍历集合，如果发现要删除的元素，就是用迭代器的删除方法将之删除
<pre><code>
    public boolean remove(Object o) {
        Iterator<E> it = iterator();
        if (o==null) {
            while (it.hasNext()) {
                if (it.next()==null) {
                    it.remove();
                    return true;
                }
            }
        } else {
            while (it.hasNext()) {
                if (o.equals(it.next())) {
                    it.remove();
                    return true;
                }
            }
        }
        return false;
    }
</code></pre>

对于参数集合中的每一个元素，都调用contains()方法，只要有一个不存在就返回false
<pre><code>
    public boolean containsAll(Collection<?> c) {
        for (Object e : c)
            if (!contains(e))
                return false;
        return true;
    }
</code></pre>

对于参数集合中的每一个元素，都调用add()方法，要注意的是如果不重写add()，那么就会抛出异常
<pre><code>
    public boolean addAll(Collection<? extends E> c) {
        boolean modified = false;
        for (E e : c)
            if (add(e))
                modified = true;
        return modified;
    }
</code></pre>

使用迭代器遍历集合，如果当前元素存在于参数集合，就将之删除，如果有相同元素但迭代器没有实现remove()方法，就会抛出异常
<pre><code>
    public boolean removeAll(Collection<?> c) {
        Objects.requireNonNull(c);
        boolean modified = false;
        Iterator<?> it = iterator();
        while (it.hasNext()) {
            if (c.contains(it.next())) {
                it.remove();
                modified = true;
            }
        }
        return modified;
    }
</code></pre>

与removeAll()相反，如果如果当前元素不存在存在于参数集合，就将之删除，其余类似
<pre><code>
    public boolean retainAll(Collection<?> c) {
        Objects.requireNonNull(c);
        boolean modified = false;
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            if (!c.contains(it.next())) {
                it.remove();
                modified = true;
            }
        }
        return modified;
    }
</code></pre>

使用迭代器将每一个元素都删除，如果迭代器没有实现remove()方法，会抛出异常
<pre><code>
    public void clear() {
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
    }
</code></pre>

返回集合的string表示，集合的元素通过String.valueOf(Object)方法变为自己的string表示
<pre><code>
    public String toString() {
        Iterator<E> it = iterator();
        if (! it.hasNext())
            return "[]";

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (;;) {
            E e = it.next();
            sb.append(e == this ? "(this Collection)" : e);
            if (! it.hasNext())
                return sb.append(']').toString();
            sb.append(',').append(' ');
        }
    }
</code></pre>

