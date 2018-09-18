package bishi.ali;

import java.util.*;

public class Main2 {
    public static void main(String[] args){
        //读取输入
        Scanner in = new Scanner(System.in);
        int startX=in.nextInt();
        int startY=in.nextInt();
        String startF=in.next();
        int endX=in.nextInt();
        int endY=in.nextInt();
        String endF=in.next();
        int row=in.nextInt();
        int column=in.nextInt();
        int[][] map=new int[row][column];
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                map[i][j]=in.nextInt();
            }
        }
        int x=0;
        int y=0;
        if(startX<endX){
            x=1;
        }else if(startX>endX){
            x=-1;
        }
        if(startY<endY){
            y=1;
        }else if(startY>endY){
            y=-1;
        }
        int time=0;
        boolean first=false;
        if(x==1&&startF.equals("WEST")){
            first=true;
        }
        if(x==-1&&startF.equals("EAST")){
            first=true;
        }
        if(y==1&&startF.equals("NORTH")){
            first=true;
        }
        if(y==-1&&startF.equals("SOUTH")){
            first=true;
        }
        if(first){
            time++;
        }
        time+=(Math.abs(endX-startX)+Math.abs(endY-startY));
        if(startX>endX){
            int temp=endX;
            endX=startX;
            startX=temp;
        }
        if(startY>endY){
            int temp=endY;
            endY=startY;
            startY=temp;
        }
        for(int i=startX;i<endX;i++){
            for(int j=startY;j<endY;j++){
                if(map[i][j]==1){
                    time++;
                }
            }
        }
        System.out.println(time);
    }
}
