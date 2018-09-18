package bishi.ali;

import java.util.*;

public class Main1 {
    public static void main(String[] args) {
        //读取输入
        Scanner in = new Scanner(System.in);
        StringBuilder stringBuilder = new StringBuilder();
        while (in.hasNextLine()) {
            stringBuilder.append(in.nextLine());
        }
        String str = stringBuilder.toString();
        //输入边界情况
        if (str.length() == 0) {
            System.out.println(str);
            return;
        }
        //将所有可能的子字符串找出来
        Map<String, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            for (int j = i + 1; j < str.length(); j++) {
                hashMap.put(str.substring(i, j), hashMap.getOrDefault(str.substring(i, j), 0) + 1);
            }
        }
        StringBuilder result = new StringBuilder(str);
        //遍历并查看是否有重复子字符串
        for (String key : hashMap.keySet()) {
            if (key.length() > 1 && hashMap.get(key) > 1) {
                List<Integer> list = new ArrayList();
                int point = result.indexOf(key);
                list.add(point);
                while (point != -1) {
                    point = result.indexOf(key, point + 1);
                    list.add(point);
                }
                for (int i = list.size() - 1; i > 0; i--) {
                    if (list.get(i) - list.get(i - 1) == key.length()) {
                        result.delete(list.get(i), key.length());
                    }
                }
            }
        }
        System.out.println(result.toString());
    }
}
