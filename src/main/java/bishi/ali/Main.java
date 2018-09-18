package bishi.ali;

import java.util.*;

public class Main {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int numOfGroup=in.nextInt();
        int m=in.nextInt();
        Map<Integer,List<Integer>> hashMap=new HashMap<>();
        for(int i=0;i<m;i++){
            int a=in.nextInt();
            int b=in.nextInt();
            if(hashMap.containsKey(a)){
                List<Integer> list=hashMap.get(a);
                list.add(b);
                hashMap.put(a,list);
            }else{
                List<Integer> list=new ArrayList<>();
                list.add(b);
                hashMap.put(a,list);
            }
            if(hashMap.containsKey(b)){
                List<Integer> list=hashMap.get(b);
                list.add(a);
                hashMap.put(b,list);
            }else{
                List<Integer> list=new ArrayList<>();
                list.add(a);
                hashMap.put(b,list);
            }

        }
        int[] org=new int[m];
        boolean result=allocate(org,0,hashMap,m);
        if(result){
            System.out.print("yes");
        }else{
            System.out.print("no");
        }
    }

    private static boolean allocate(int[] org,int start,Map<Integer,List<Integer>> hashMap,int m){
        boolean first=true;
        boolean second=true;
        for(int i=0;i<start;i++){
            if(hashMap.get(2*start-1).contains(i)){
                first=false;
                break;
            }
        }
        for(int i=0;i<start;i++){
            if(hashMap.get(2*start).contains(i)){
                second=false;
                break;
            }
        }
        if(start==m-1){
            if(first){
                return true;
            }if(second){
                return true;
            }
            return false;
        }
        boolean firstOrg=false;
        boolean secondOrg=false;
        if(first){
            int[] array=org;
            array[start]=2*start-1;
            firstOrg=allocate(array,start+1,hashMap,m);
        }
        if(second){
            int[] array=org;
            array[start]=2*start;
            secondOrg=allocate(array,start+1,hashMap,m);
        }
        if(firstOrg){
            return true;
        }if(secondOrg){
            return true;
        }
        return false;
    }
}
