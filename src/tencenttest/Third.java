package tencenttest;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Third {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        ArrayList<Integer> z=new ArrayList<>();
        ArrayList<Integer> w=new ArrayList<>();
        ArrayList<Integer> x=new ArrayList<>();
        ArrayList<Integer> y=new ArrayList<>();
        int[][] V=new int[n][m];

        for (int i = 0; i < n; i++) {
            z.add(in.nextInt());
            w.add(in.nextInt());

        }
        for (int i = 0; i < m; i++) {
            x.add(in.nextInt());
            y.add(in.nextInt());
        }

        List<List<Integer>> canDo=new ArrayList<>();

        for(int i=0;i<n;i++){
            canDo.add(find(x.get(i),y.get(i),z,w));
        }



    }

    public static List<Integer> find(int x,int y,List<Integer> z,List<Integer> w){
        List<Integer> result=new ArrayList<>();
        for(int i=0;i<z.size();i++){
            if(z.get(i)<=x&&w.get(i)<=y){
                result.add(i);
            }
        }
        return result;
    }



}
