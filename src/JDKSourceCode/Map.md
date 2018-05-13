#Map接口源码解析

Map接口的顺序与迭代器在集合角度上的顺序相同，有三种集合角度，分别是键的set，值的集合，和键值对的mappings

一些实现类，如TreeMap，对于顺序做了一些保证，另一些例如HashMap就没有

map不能以map做键，能以map做值，但这样的map对于equals和hashCode方法定义的不好

    void putAll(Map<? extends K, ? extends V> m);
将参数map中的所有键值对放入原map中。如果在执行操作的过程中参数map被改变了，该操作就是undefined

    Set<Map.Entry<K, V>> entrySet();
将map中的键值对以set的形式返回，map中的改变会在set中体现出来，反过来也是

如果在用迭代器遍历该set时，map发生了改变（除了使用iterator.remove或者用迭代器对map entry的setValue），该迭代就是undefined

这个set支持Iterator.remove，Set.remove，removeAll，retainAll，clear，不支持add和addAll

    interface Entry<K,V>{......}
定义了Map.Entry的操作

    default V getOrDefault(Object key, V defaultValue) {
        V v;
        return (((v = get(key)) != null) || containsKey(key))
            ? v
            : defaultValue;
    }
如果map含有参数key这样的键，就返回对应的值，否则返回参数defaultValue

    default void forEach(BiConsumer<? super K, ? super V> action) {
        Objects.requireNonNull(action);
        for (Map.Entry<K, V> entry : entrySet()) {
            K k;
            V v;
            try {
                k = entry.getKey();
                v = entry.getValue();
            } catch(IllegalStateException ise) {
                // this usually means the entry is no longer in the map.
                throw new ConcurrentModificationException(ise);
            }
            action.accept(k, v);
        }
    }
使用迭代器遍历map，对于每一个键值对，进行action操作

    default void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
        Objects.requireNonNull(function);
        for (Map.Entry<K, V> entry : entrySet()) {
            K k;
            V v;
            try {
                k = entry.getKey();
                v = entry.getValue();
            } catch(IllegalStateException ise) {
                // this usually means the entry is no longer in the map.
                throw new ConcurrentModificationException(ise);
            }

            // ise thrown from function is not a cme.
            v = function.apply(k, v);

            try {
                entry.setValue(v);
            } catch(IllegalStateException ise) {
                // this usually means the entry is no longer in the map.
                throw new ConcurrentModificationException(ise);
            }
        }
    }
使用迭代器遍历map，对于每个键值对，对值进行操作并更新

    default V putIfAbsent(K key, V value) {
        V v = get(key);
        if (v == null) {
            v = put(key, value);
        }

        return v;
    }
如果参数key并没有对应的值，或者对应的是null，就将之与参数value对应起来，返回key此时对应的value

    default boolean remove(Object key, Object value) {
        Object curValue = get(key);
        if (!Objects.equals(curValue, value) ||
            (curValue == null && !containsKey(key))) {
            return false;
        }
        remove(key);
        return true;
    }
如果map中含有与参数一致的键值对，就将之删除，返回true，否则返回false

    default boolean replace(K key, V oldValue, V newValue) {
        Object curValue = get(key);
        if (!Objects.equals(curValue, oldValue) ||
            (curValue == null && !containsKey(key))) {
            return false;
        }
        put(key, newValue);
        return true;
    }
如果map中含有与key-oldValue一致的键值对，就将值更新为newValue

    default V replace(K key, V value) {
        V curValue;
        if (((curValue = get(key)) != null) || containsKey(key)) {
            curValue = put(key, value);
        }
        return curValue;
    }
如果map有相应的key，就更新它的值为value

    default V computeIfAbsent(K key,
            Function<? super K, ? extends V> mappingFunction) {
        Objects.requireNonNull(mappingFunction);
        V v;
        if ((v = get(key)) == null) {
            V newValue;
            if ((newValue = mappingFunction.apply(key)) != null) {
                put(key, newValue);
                return newValue;
            }
        }

        return v;
    }
如果参数key并没有与某个值对应或者对应的是null，尝试通过给定的参数函数计算出值并与key对应

    default V computeIfPresent(K key,
            BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        Objects.requireNonNull(remappingFunction);
        V oldValue;
        if ((oldValue = get(key)) != null) {
            V newValue = remappingFunction.apply(key, oldValue);
            if (newValue != null) {
                put(key, newValue);
                return newValue;
            } else {
                remove(key);
                return null;
            }
        } else {
            return null;
        }
    }
如果参数key有与之对应的值，尝试通过给定的参数函数计算出值并与key对应

    default V compute(K key,
            BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        Objects.requireNonNull(remappingFunction);
        V oldValue = get(key);

        V newValue = remappingFunction.apply(key, oldValue);
        if (newValue == null) {
            // delete mapping
            if (oldValue != null || containsKey(key)) {
                // something to remove
                remove(key);
                return null;
            } else {
                // nothing to do. Leave things as they were.
                return null;
            }
        } else {
            // add or replace old mapping
            put(key, newValue);
            return newValue;
        }
    }
对于参数key和它对应的值，用函数计算出新的值，并进行替换

    default V merge(K key, V value,
            BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
        Objects.requireNonNull(remappingFunction);
        Objects.requireNonNull(value);
        V oldValue = get(key);
        V newValue = (oldValue == null) ? value :
                   remappingFunction.apply(oldValue, value);
        if(newValue == null) {
            remove(key);
        } else {
            put(key, newValue);
        }
        return newValue;
    }
如果参数key并没有与某个值对应或者对应的是null，就将key与给定的参数value对应，否则就用给定参数函数计算出新值并进行更新
