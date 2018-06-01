package com.desaco.practiceknowing.test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by desaco on 2018/6/1.
 * 泛型的上限和下限
 * 上限：<? extends T> ?是T的子类
 * 下限：<? super T> ?是T的父类
 */

public class GenericLowUp {
    private void generic() {
        //因为show方法是用List<?>通配符接收的，所以可以是任意类型！
        List<String> l1 = new ArrayList<>();//new ArrayList<String>()
        l1.add("list 1");
        show(l1);

        List<Double> l2 = new ArrayList<>();
        l2.add(2.3);
        show(l2);

        List<Number> l3 = new ArrayList<>();
        Number num = new Number() {
            @Override
            public int intValue() {
                return 0;
            }

            @Override
            public long longValue() {
                return 0;
            }

            @Override
            public float floatValue() {
                return 0;
            }

            @Override
            public double doubleValue() {
                return 0;
            }
        };
        l3.add(num);
        show(l3);

        List<Object> l4 = new ArrayList<>();
        l4.add("list 4");
        show(l4);

        //使用up方法的话接收类型为Number或者其子类
        //up(l1);//错误，因为up方法接收类型为Number或者其子类，l1（String）不符合！
        up(l2);
        up(l3);

        //使用down方法的话接收类型为Number或者其父类
        //down(l2);error
        down(l3);
        down(l4);
    }

    public static void down(List<? super Number> list) {
        for (Object object : list) {
            System.out.println(object);
        }
    }

    public static void up(List<? extends Number> list) {
        for (Object object : list) {
            System.out.println(object);
        }
    }

    public static void show(List<?> list) {
        for (Object object : list) {
            System.out.println(object);
        }

    }
}
