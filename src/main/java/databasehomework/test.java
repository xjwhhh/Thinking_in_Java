package databasehomework;

import java.util.Date;

public class test {

    public static void main(String[] args){
        Date d1=new Date();
        for(int i=0;i<100;i++){
            System.out.println("1234");
        }
        Date d2=new Date();
        System.out.println(d2.getTime()-d1.getTime());
    }
}
