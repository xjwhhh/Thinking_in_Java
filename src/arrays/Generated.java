package arrays;

import generic.Generator;

//这个工具只能产生Object子类型的数组，而不能产生基本类型数组
//public class Generated {
//    //Fill an existing array
//    //接受一个已有的数组，并用某个Generator来填充它
//    public static <T> T[] array(T[] a, Generator<T> gen){
//        return new CollectionData<T>(gen,a.length).toArray(a);
//    }
//
//    //Create a new array
//    //接受一个Class对象，一个Generator和所需的元素数量，然后创建一个新数组，并使用所接受的Generator来填充它
//    public static <T> T[] array(Class<T> type,Generator<T> gen,int size){
//        T[] a=(T[]) java.lang.reflect.Array.newInstance(type,size);
//        return new CollectionData<T>(gen,size).toArray(a);
//    }
//}
