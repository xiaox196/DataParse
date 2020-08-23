package com.ming.data.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author alun
 * @data 2018/12/27
 */
public class CalcNumber {
    static List<Integer> total = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22,
            23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49);

    static List<Integer> aa = Arrays.asList(1, 2, 7, 8, 13, 14, 19, 20, 25, 26, 31, 32, 37, 38, 43, 44, 49);
    static List<Integer> bb = Arrays.asList(3, 4, 9, 10, 15, 16, 21, 22, 27, 28, 33, 34, 39, 40, 45, 46);
    static List<Integer> cc = Arrays.asList(5, 6, 11, 12, 17, 18, 23, 24, 29, 30, 35, 36, 41, 42, 47, 48);

    static List<Integer> ccc=Arrays.asList(1,4,7,9,11,14,16,17,21,24,25,27,31,34,36,37,41,44,47,49);
    static List<Integer> nnn=Arrays.asList(2,3,5,6,8,10,12,13,15,18,19,20,22,23,26,28,29,30,32,33,35,38,39,40,42,43,45,46,48);

    static List<Integer> a = Arrays.asList(1, 13, 25, 37, 49);
    static List<Integer> b = Arrays.asList(2, 14, 26, 38);
    static List<Integer> c = Arrays.asList(3, 15, 27, 39);
    static List<Integer> d = Arrays.asList(4, 16, 28, 40);
    static List<Integer> e = Arrays.asList(5, 17, 29, 41);
    static List<Integer> f = Arrays.asList(6, 18, 30, 42);

    static List<Integer> g = Arrays.asList(7, 19, 31, 43);
    static List<Integer> h = Arrays.asList(8, 20, 32, 44);
    static List<Integer> i = Arrays.asList(9, 21, 33, 45);
    static List<Integer> j = Arrays.asList(10, 22, 34, 46);
    static List<Integer> k = Arrays.asList(11, 23, 35, 47);
    static List<Integer> l = Arrays.asList(12, 24, 36, 48);

    static List<Integer> one = Arrays.asList(1,8,15,22,29,36,43);//D
    static List<Integer> two  =Arrays.asList(2,9,16,23,30,37,44);//R
    static List<Integer> three = Arrays.asList(3,10,17,24,31,38,45);//M
    static List<Integer> four = Arrays.asList(4,11,18,25,32,39,46);//F
    static List<Integer> five= Arrays.asList(5,12,19,26,33,40,47);//S
    static List<Integer> six = Arrays.asList(6,13,20,27,34,41,48);//L
    static List<Integer> seven = Arrays.asList(7,14,21,28,35,42,49);//T

    static List<Integer> zz = Arrays.asList(1, 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47);
    static List<Integer> ff = Arrays.asList(4, 6, 8, 9, 10, 12, 14, 15, 16, 18, 20, 21, 22, 24, 25, 26, 27, 28, 30, 32,
            33, 34, 35, 36, 38, 39, 40, 42, 44, 45, 46, 48, 49);

    public static List<Integer> hhh=Arrays.asList(4,6,10,12,16,18,22,24,27,28,33,34,38,39,44,45,49);
    public static List<Integer> kkk=Arrays.asList(8,9,14,15,20,21,25,26,30,32,35,36,40,42,46,48);
    static List<Integer> fc=Arrays.asList(4,9,16,25,36,49,30,22,33,44,32);
    static List<Integer> fd=Arrays.asList(6,10,8,12,20,21,24,27,38,40,45);
    static List<Integer> fe=Arrays.asList(14,15,18,26,28,34,35,39,42,46,48);

    public static List<Object> lists = transferArrayToList(total.toArray());

    public static void main(String[] args) {
      removeList(zz);
      removeList(fc);
      removeList(fd);
      removeList(fe);
      System.out.println(lists);
    }

    public static void removeList(List<Integer> removeItem){
        for (int i = 0; i < removeItem.size(); i++) {
            lists.remove(removeItem.get(i));
        }
    }

    private static <E> List<E> transferArrayToList(E[] array) {
        List<E> transferedList = new ArrayList<>();
        Arrays.stream(array).forEach(arr -> transferedList.add(arr));
        return transferedList;
    }

    public static void calcData() {

    }
}
