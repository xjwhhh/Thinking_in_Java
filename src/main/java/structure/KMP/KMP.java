package structure.KMP;

public class KMP {
    void GetNext(String p, int next[]) {
        int pLen = p.length();
        next[0] = -1;
        int k = -1;
        int j = 0;
        char[] pChar = p.toCharArray();
        while (j < pLen - 1) {
            //pChar[k]表示前缀，pChar[j]表示后缀
            if (k == -1 || pChar[j] == pChar[k]) {
                ++k;
                ++j;
                next[j] = k;
            } else {
                k = next[k];
            }
        }
    }

    //优化过后的next 数组求法
    public void GetNextval(String p, int next[]) {
        int pLen = p.length();
        next[0] = -1;
        int k = -1;
        int j = 0;
        char[] pChar = p.toCharArray();
        while (j < pLen - 1) {
            //p[k]表示前缀，p[j]表示后缀
            if (k == -1 || pChar[j] == pChar[k]) {
                ++j;
                ++k;
                //较之前next数组求法，改动在下面4行
                if (pChar[j] != pChar[k])
                    next[j] = k;   //之前只有这一行
                else
                    //因为不能出现pChar[j] = pChar[ next[j ]]，所以当出现时需要继续递归，k = next[k] = next[next[k]]
                    next[j] = next[k];
            } else {
                k = next[k];
            }
        }
    }

    int KmpSearch(String s, String p) {
        int i = 0;
        int j = 0;
        int sLen = s.length();
        int pLen = p.length();
        char[] pChar = p.toCharArray();
        char[] sChar = s.toCharArray();
        int[] next = new int[pLen];
        this.GetNextval(p, next);
        while (i < sLen && j < pLen) {
            //①如果j = -1，或者当前字符匹配成功（即sChar[i] == pChar[j]），都令i++，j++
            if (j == -1 || sChar[i] == pChar[j]) {
                i++;
                j++;
            } else {
                //②如果j != -1，且当前字符匹配失败（即S[i] != P[j]），则令 i 不变，j = next[j]
                //next[j]即为j所对应的next值
                j = next[j];
            }
        }
        if (j == pLen)
            return i - j;
        else
            return -1;
    }


}
