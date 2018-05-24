package com.desaco.practiceknowing.utils;

import android.content.Context;

import com.desaco.practiceknowing.data_parse.Person;

import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by desaco on 2018/5/24.
 * <p>
 * https://blog.csdn.net/ithomer/article/details/7521605
 */

public class Dom4jXmlUtils {

    final static String fileFolder = "a_rtmp_video";
    final static String fileName = "person.xml";

    /**
     * Dom4j方式，创建 XML
     */
    public static String dom4jXmlCreate(Context context) {
        StringWriter xmlWriter = new StringWriter();

        Person[] persons = new Person[3];       // 创建节点Person对象
        persons[0] = new Person(1, "sunboy_2050", "http://blog.csdn.net/sunboy_2050");
        persons[1] = new Person(2, "baidu", "http://www.baidu.com");
        persons[2] = new Person(3, "google", "http://www.google.com");

        try {
            org.dom4j.Document doc = DocumentHelper.createDocument();
            doc.setXMLEncoding("utf-8");

            org.dom4j.Element eleRoot = doc.addElement("root");
            eleRoot.addAttribute("author", "homer");
            eleRoot.addAttribute("date", "2012-04-25");
            eleRoot.addComment("dom4j test");

            int personsLen = persons.length;
            for (int i = 0; i < personsLen; i++) {

                Element elePerson = eleRoot.addElement("person");   // 创建person节点，引用类为 org.dom4j.Element
                Element eleId = elePerson.addElement("id");
                eleId.addText(persons[i].getId() + "");

                Element eleName = elePerson.addElement("name");
                eleName.addText(persons[i].getName());

                Element eleBlog = elePerson.addElement("blog");
                eleBlog.addText(persons[i].getBlog());
            }

            org.dom4j.io.OutputFormat outputFormat = new org.dom4j.io.OutputFormat();   // 设置xml输出格式
            outputFormat.setEncoding("utf-8");
            outputFormat.setIndent(false);
            outputFormat.setNewlines(true);
            outputFormat.setTrimText(true);

            org.dom4j.io.XMLWriter output = new XMLWriter(xmlWriter, outputFormat);     // 保存xml
            output.write(doc);
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

//        savedXML(fileName, xmlWriter.toString()); //将XML保存下来
        FileUtils.writeStr2Sd(fileFolder, fileName, xmlWriter.toString());
        return xmlWriter.toString();
    }

    /**
     * Dom4j方式，解析 XML
     */
    public static String dom4jXmlParse(Context context) {
        StringWriter xmlWriter = new StringWriter();

        //InputStream inStream = context.getAssets().open("userxml.xml");
        InputStream is;
//                = readXML(fileName);

        try {
            is = new ByteArrayInputStream(FileUtils.read(fileFolder, fileName).getBytes("UTF-8"));

            SAXReader reader = new SAXReader();
            org.dom4j.Document doc = reader.read(is);

            List<Person> personsList = null;
            Person person = null;
            StringBuffer xmlHeader = new StringBuffer();

            Element eleRoot = doc.getRootElement();     // 获得root根节点，引用类为 org.dom4j.Element
            String attrAuthor = eleRoot.attributeValue("author");
            String attrDate = eleRoot.attributeValue("date");
            xmlHeader.append("root").append("\t\t");
            xmlHeader.append(attrAuthor).append("\t");
            xmlHeader.append(attrDate).append("\n");
            personsList = new ArrayList<Person>();

            // 获取root子节点，即person
            Iterator<Element> iter = eleRoot.elementIterator();
            for (; iter.hasNext(); ) {
                Element elePerson = (Element) iter.next();

                if ("person".equals(elePerson.getName())) {
                    person = new Person();

                    // 获取person子节点，即id、name、blog
                    Iterator<Element> innerIter = elePerson.elementIterator();
                    for (; innerIter.hasNext(); ) {
                        Element ele = (Element) innerIter.next();

                        if ("id".equals(ele.getName())) {
                            String id = ele.getText();
                            person.setId(Integer.parseInt(id));
                        } else if ("name".equals(ele.getName())) {
                            String name = ele.getText();
                            person.setName(name);
                        } else if ("blog".equals(ele.getName())) {
                            String blog = ele.getText();
                            person.setBlog(blog);
                        }
                    }

                    personsList.add(person);
                    person = null;
                }
            }

            xmlWriter.append(xmlHeader);
            int personsLen = personsList.size();
            for (int i = 0; i < personsLen; i++) {
                xmlWriter.append(personsList.get(i).toString());
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return xmlWriter.toString();
    }

    /** Dom4j方式，解析 XML（方式二）  */
//    public String dom4jXMLResolve2(){
//        StringWriter xmlWriter = new StringWriter();
//
//        InputStream is = readXML(fileName);
//        try {
//            org.dom4j.io.SAXReader reader = new org.dom4j.io.SAXReader();
//            org.dom4j.Document doc = reader.read(is);
//
//            List<Person> personsList = null;
//            Person person = null;
//            StringBuffer xmlHeader = new StringBuffer();
//
//
//            Element eleRoot = doc.getRootElement();     // 获得root根节点，引用类为 org.dom4j.Element
//            String attrAuthor = eleRoot.attributeValue("author");
//            String attrDate = eleRoot.attributeValue("date");
//            xmlHeader.append("root").append("\t\t");
//            xmlHeader.append(attrAuthor).append("\t");
//            xmlHeader.append(attrDate).append("\n");
//            personsList = new ArrayList<Person>();
//
//            @SuppressWarnings("unchecked")
//            List<Element> idList = (List<Element>) doc.selectNodes("//root//person//id");   // 选择性获取全部id
//            Iterator<Element> idIter = idList.iterator();
//            while(idIter.hasNext()){
//                person = new Person();
//
//                Element idEle = (Element)idIter.next();
//                String id = idEle.getText();
//                person.setId(Integer.parseInt(id));
//
//                personsList.add(person);
//            }
//
//            xmlWriter.append(xmlHeader);
//            int personsLen = personsList.size();
//            for(int i=0; i<personsLen; i++) {
//                xmlWriter.append("id = ").append(personsList.get(i).getId()+"").append("\n");
//            }
//
//        } catch (DocumentException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return xmlWriter.toString();
//    }
}
