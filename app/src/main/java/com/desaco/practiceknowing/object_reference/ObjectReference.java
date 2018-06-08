package com.desaco.practiceknowing.object_reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * Created by desaco on 2018/6/8.
 */

public class ObjectReference {

    static Object object = new Object();

    /**
     * 强引用;只要引用存在，垃圾回收器永远不会回收
     */
    public static void testStrongReference() {
        Object obj = new Object();
        obj = null;
        System.gc();
        System.out.print("after system.gc-strongReference---obj = " + obj);
    }

    /**
     * 软引用;非必须引用，内存溢出之前进行回收
     */
    public static void testSoftReference() {
        SoftReference<Object> obj = new SoftReference<Object>(object);
        obj = null;
        System.gc();
        System.out.print("after system.gc---softReference = " + obj);
    }

    /**
     * 弱引用;第二次垃圾回收时回收
     */
    public static void testWeakReference() {
        String str = "desaco";
        WeakReference<Object> weakReference = new WeakReference<Object>(object);
        WeakReference<Object> weakReferenceStr = new WeakReference<Object>(str);
        object = null;
        str = null;
        System.gc();
        System.out.println("after system.gc---weakReference = " + weakReference.get());
        System.out.print("after system.gc---weakReferenceStr = " + weakReferenceStr.get());
    }

    /**
     * 虚引用（PhantonReference）;虚引用是每次垃圾回收的时候都会被回收，通过虚引用的get方法永远获取到的数据为null，因此也被成为幽灵引用。
     * 虚引用主要用于检测对象是否已经从内存中删除。
     */
    public static void testPhantonReference() {
        Object str = "desaco";
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        PhantomReference<Object> phantomReference = new PhantomReference<>(object, referenceQueue);
        PhantomReference<Object> phantomReferenceStr = new PhantomReference<>(str, referenceQueue);
//        object = null;
//        str = null;
        System.gc();
        System.out.println("after system.gc---phantomReference = " + phantomReference.get());
        System.out.print("after system.gc---phantomReferenceStr = " + phantomReferenceStr.get());
    }
}
