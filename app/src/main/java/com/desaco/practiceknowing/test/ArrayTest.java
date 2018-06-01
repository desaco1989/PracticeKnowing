package com.desaco.practiceknowing.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by desaco on 2018/6/1.
 */

public class ArrayTest {
    public void arrayIte() {
        List<Integer> lstint = new ArrayList<Integer>();
        lstint.add(1);
        lstint.add(2);
        lstint.add(3);

        // Iterator遍历一
        Iterator<Integer> iterator = lstint.iterator();
        while (iterator.hasNext()) {
            int i = (Integer) iterator.next();
            System.out.println(i);
        }

        // Iterator遍历二
        for (Iterator<Integer> it = lstint.iterator(); it.hasNext(); ) {
            int i = (Integer) it.next();
            System.out.println(i);
        }

        // for循环
        for (int i = 0; i < lstint.size(); i++) {
            System.out.println(lstint.get(i));
        }

        // for循环加强版
        for (Integer i : lstint) {
            System.out.println(i);
        }
    }
}
