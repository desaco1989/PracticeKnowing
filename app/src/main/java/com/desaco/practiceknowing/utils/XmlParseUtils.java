package com.desaco.practiceknowing.utils;

import android.util.Log;
import android.util.Xml;

import org.w3c.dom.Document;
import org.xmlpull.v1.XmlPullParser;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by desaco on 2018/5/24.
 * 解析XML工具类 https://blog.csdn.net/huwenzhi1991/article/details/46558601
 */

public class XmlParseUtils {
    /**
     * 解析XML转换成对象
     *
     * @param is          输入流
     * @param clazz       对象Class
     * @param fields      字段集合一一对应节点集合
     * @param elements    节点集合一一对应字段集合
     * @param itemElement 每一项的节点标签
     * @return
     */
    public static <T> List<T> parse(InputStream is, Class<T> clazz,
                                    List<String> fields, List<String> elements, String itemElement) {
        Log.v("rss", "开始解析XML.");
        List<T> list = new ArrayList<T>();
        try {
            XmlPullParser xmlPullParser = Xml.newPullParser();
            xmlPullParser.setInput(is, "UTF-8");
            int event = xmlPullParser.getEventType();
            T obj = null;
            while (event != XmlPullParser.END_DOCUMENT) {
                switch (event) {
                    case XmlPullParser.START_TAG:
                        if (itemElement.equals(xmlPullParser.getName())) {
                            obj = clazz.newInstance();
                        }
                        if (obj != null
                                && elements.contains(xmlPullParser.getName())) {
                            setFieldValue(obj, fields.get(elements
                                            .indexOf(xmlPullParser.getName())),
                                    xmlPullParser.nextText());
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (itemElement.equals(xmlPullParser.getName())) {
                            list.add(obj);
                            obj = null;
                        }
                        break;
                }
                event = xmlPullParser.next();
            }
        } catch (Exception e) {
            Log.e("rss", "解析XML异常：" + e.getMessage());
            throw new RuntimeException("解析XML异常：" + e.getMessage());
        }
        return list;
    }

    /**
     * 设置字段值
     *
     * @param propertyName 字段名
     * @param obj          实例对象
     * @param value        新的字段值
     * @return
     */
    public static void setFieldValue(Object obj, String propertyName,
                                     Object value) {
        try {
            Field field = obj.getClass().getDeclaredField(propertyName);
            field.setAccessible(true);
            field.set(obj, value);
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }

}
