#AbstractSet源码解析

本类没有重写任何AbstractCollection类的方法实现，只是增加了对于equals和hashCode的实现

equals，比较set与参数object是否相等

当且仅当参数object类型是set，两个set有相同长度，并且参数set的每个元素都存在于原set中
    
    public boolean equals(Object o) {
        if (o == this)
            return true;//首先判断是不是就是原set
        if (!(o instanceof Set))
            return false;//判断类型
        Collection<?> c = (Collection<?>) o;
        if (c.size() != size())
            return false;//判断长度
        try {
            return containsAll(c);//调用containsAll((Collection) o)
        } catch (ClassCastException unused)   {
            return false;
        } catch (NullPointerException unused) {
            return false;
        }
    }

hashCode，返回set的哈希值

set的哈希值是set中所有元素哈希值的总和，确保了对任意两个set:s1和s2，s1.equals(s2)就意味着s1.hashCode()==s2.hashCode()

    public int hashCode() {
        int h = 0;
        Iterator<E> i = iterator();
        //遍历，相加
        while (i.hasNext()) {
            E obj = i.next();
            if (obj != null)
                h += obj.hashCode();
        }
        return h;
    }

removeAll，从set中将参数集合中的所有元素删除

首先判断set和参数集合谁的长度更大，如果set大，就用迭代器遍历集合，从set中删除集合中的元素；如果集合更大，就用迭代器遍历set，从中删除元素

    public boolean removeAll(Collection<?> c) {
        Objects.requireNonNull(c);
        boolean modified = false;

        if (size() > c.size()) {
            for (Iterator<?> i = c.iterator(); i.hasNext(); )
                modified |= remove(i.next());
        } else {
            for (Iterator<?> i = iterator(); i.hasNext(); ) {
                if (c.contains(i.next())) {
                    i.remove();
                    modified = true;
                }
            }
        }
        return modified;
    }




              