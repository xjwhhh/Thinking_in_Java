package structure.BitSet;

import java.util.BitSet;

public class ComputeSieve {

    public static void main(String[] args) {

        //step1:开辟指定大小的位集空间；
        int n = 100;
        BitSet b = new BitSet(n + 1);
        int i;
        //step2:将所有位置true;
        for (i = 2; i <= n; i++) {
            b.set(i);
        }

        //step3:将已知素数的倍数所对应的位置false
        i = 2;
        while (i * i <= n) {
            if (b.get(i)) {
                int k = 2 * i;
                while (k <= n) {
                    b.clear(k);
                    k += i;
                }
            }
            i++;
        }

        i = 0;
        while (i <= n) {
            if (b.get(i)) {
                System.out.println(i);
            }
            i++;
        }
    }

}
