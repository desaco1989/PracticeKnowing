package com.desaco.practiceknowing.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author desaco
 *         去除List集合中的重复值（四种好用的方法） - https://blog.csdn.net/cs6704/article/details/50158373
 */
public class CompareList {

    public static void main(String[] args) {
        //去重并且按照自然顺序排列
//		List newList = new ArrayList(new TreeSet(list));
        list1();
        System.out.println("--------------------------");
        list2();
        System.out.println("--------------------------");
        list3();
        System.out.println("--------------------------");
        list4();
        System.out.println("--------------------------");
    }

    private static void list1() {
        List<String> list = new ArrayList<String>();
        list.add("aaa");
        list.add("bbb");
        list.add("aaa");
        list.add("aba");
        list.add("aaa");

        Set set = new HashSet();
        List newList = new ArrayList();
        for (String cd : list) {
            if (set.add(cd)) {
                newList.add(cd);
            }
        }
        System.out.println("去重后的集合： " + newList);
    }

    private static void list2() {
        List<String> list = new ArrayList<String>();
        list.add("aaa");
        list.add("bbb");
        list.add("aaa");
        list.add("aba");
        list.add("aaa");

        List<String> newList = new ArrayList<String>();
        for (String cd : list) {
            if (!newList.contains(cd)) {
                newList.add(cd);
            }
        }
        System.out.println("去重后的集合： " + newList);
    }

    private static void list3() {
        List<String> list = new ArrayList<String>();
        list.add("aaa");
        list.add("bbb");
        list.add("aaa");
        list.add("aba");
        list.add("aaa");

        Set set = new HashSet();
        List newList = new ArrayList();
        set.addAll(list);
        newList.addAll(set);

        System.out.println("去重后的集合： " + newList);

    }

    private static void list4() {
        List<String> list = new ArrayList<String>();
        list.add("aaa");
        list.add("bbb");
        list.add("aaa");
        list.add("aba");
        list.add("aaa");

        List newList = new ArrayList(new HashSet(list));

        System.out.println("去重后的集合： " + newList);
    }

}
