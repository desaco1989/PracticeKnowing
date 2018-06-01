package com.desaco.practiceknowing.data_parse;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.desaco.practiceknowing.R;
import com.desaco.practiceknowing.utils.Dom4jXmlUtils;
import com.desaco.practiceknowing.utils.XmlParseUtils;

import org.w3c.dom.Document;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by desaco on 2018/5/24.
 * XML JSON 解析等
 */

public class DataParseActivity extends Activity {
    //com.desaco.practiceknowing.data_parse.DataParseActivity

    private Context mContext;
    TextView showTv;
    TextView contentTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_parse);
        mContext = this;
        //
        showTv = (TextView) findViewById(R.id.show_tv);
        contentTv = (TextView) findViewById(R.id.content_tv);

        showTv.setText("创建XML："+Dom4jXmlUtils.dom4jXmlCreate(mContext));
        //dom4jXmlParse
        contentTv.setText("解析XML："+Dom4jXmlUtils.dom4jXmlParse(mContext));


//        parseContent();
    }

    private void parseContent() {

        InputStream in = null;
        try {
            in = getResources().getAssets().open("User.xml");
            //如果说要获取到File对象的话，获取assert文件中的文件
            //File file =new File("file:///android_asset/User.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String> fields = new ArrayList<String>();
        List<String> elements = new ArrayList<String>();
        fields.add("name");
        fields.add("age");
        elements.add("name");
        elements.add("age");
        List<User> users = XmlParseUtils.parse(in, User.class, fields, elements, "User");
        StringBuffer sb = new StringBuffer();
        int size = users.size();
        for (int i = 0; i < size; i++) {
            User user = users.get(i);
            sb.append(user.getName());
            sb.append(",");
            sb.append(user.getAge());
            sb.append(";");

        }
//        for (Object object : users) {
//            System.out.println(object.toString());
//        }
        showTv.setText(sb.toString());
    }

    private void dom4jUtils() {//TODO dom4j
        try {
            //XML用于保存及交换数据，与读取配置文件的类在同一包，或在WEB-INF(或其子目录下)，// 读取配置文件获得一个输入流
            InputStream is = mContext.getResources().getAssets().open("User.xml");
            ;// src目录下
            // 1. 获得文档解析器工厂对象
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            // 2. 获得文档解析器对象
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            // 3. 获得文档对象
            Document document = documentBuilder.parse(is);
        } catch (Exception ex) {

        }

    }
}
