package com.desaco.practiceknowing.test;

import java.util.Iterator;

/**
 * Created by desaco on 2018/6/1.
 */

public class NewStringArray {
    /**
     * 新的数组形式
     * @param array
     */
    public void arrayIterate(String...array){
        int size = array.length;
        System.out.println("用for循环遍历");
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }

        System.out.println("用增强for循环");
        for (String i : array) {
            System.out.println(i);
        }

//        System.out.println("用iterator+while");
//        Iterator<String> it = array.iterator();
//        while (it.hasNext()) {
//            String i = (String) it.next();
//            System.out.println(i);
//        }
//
//        System.out.println("用iterator+for");
//        for (Iterator<Integer> iter = array.iterator(); iter.hasNext();) {
//            String i = (String) iter.next();
//            System.out.println(i);
//        }
    }
}
