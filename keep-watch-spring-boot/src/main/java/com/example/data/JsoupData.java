package com.example.data;

import com.example.util.SendEmail;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class JsoupData {

    private static String data = "";
    private static String js2 = "";
    private static String js1 = "";
    private static String cssMail = "";
    private static String css = "";
    @Autowired
    private SendEmail sendEmail;
    private static int count = 0;

    @PostConstruct
    @Scheduled(cron = "0/10 * * * * ? ")
    public void watch() throws Exception {
        System.out.println("===================第"+count++ +"次检测======================");
        System.out.println("正在检测注册页面变化。。。");
        Connection connect = Jsoup.connect("https://6pan.cn/auth/register");
        Document document = connect.get();
        boolean boo = false;
        if (!data.equals(document.toString())) {//如果检测下来，跟前面的结果不相等，
            //发送邮件
            boo = true;
            //将data中的值替换成新的
            data = document.toString();
        }
        String jsResult1 = selectJS1();
        if (!js1.equals(jsResult1)) {
            boo = true;
            //将data中的值替换成新的
            js1 = jsResult1;
        }
        String jsResult2 = selectJS2();
        if (!js2.equals(jsResult2)) {
            boo = true;
            //将data中的值替换成新的
            js2 = jsResult2;
        }
        String CSSMail = selectCSSMail();
        if (!cssMail.equals(CSSMail)) {
            boo = true;
            //将data中的值替换成新的
            cssMail = CSSMail;
        }
        String selectCss = selectCss();
        if (!css.equals(selectCss)){
            boo = true;
            //将data中的值替换成新的
            css = selectCss;
        }
        if (boo){
            sendEmail.send();
        }

    }

    public String selectJS2() throws Exception {
        System.out.println("检测js2的变化。。。");
        URL url = new URL("https://6pan.cn/static/js/main.283e2992.chunk.js");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(18000);
        connection.setReadTimeout(6000);
        connection.setRequestMethod("GET");
        connection.connect();
        StringBuilder result = new StringBuilder();
        if (connection.getResponseCode() == 200) {
            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String s = reader.readLine();
            while (s != null && !"".equals(s)) {
                result.append(s);
                s = reader.readLine();
            }
        }else{
            sendEmail.send();
        }
        return result.toString();
    }
    public String selectJS1() throws Exception {
        System.out.println("检测js1的变化。。。");
        URL url = new URL("https://6pan.cn/static/js/1.14cfac43.chunk.js");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(18000);
        connection.setReadTimeout(6000);
        connection.setRequestMethod("GET");
        connection.connect();
        StringBuilder result = new StringBuilder();
        if (connection.getResponseCode() == 200) {
            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String s = reader.readLine();
            while (s != null && !"".equals(s)) {
                result.append(s);
                s = reader.readLine();
            }
        }else{
            sendEmail.send();
        }
        return result.toString();
    }
    public String selectCSSMail() throws Exception {
        System.out.println("检测cssMail的变化。。。");
        URL url = new URL("https://6pan.cn/static/css/main.13698227.chunk.css");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(18000);
        connection.setReadTimeout(6000);
        connection.setRequestMethod("GET");
        connection.connect();
        StringBuilder result = new StringBuilder();
        if (connection.getResponseCode() == 200) {
            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String s = reader.readLine();
            while (s != null && !"".equals(s)) {
                result.append(s);
                s = reader.readLine();
            }
        }else{
            sendEmail.send();
        }
        return result.toString();
    }
    public String selectCss() throws Exception {
        System.out.println("检测css的变化。。。");
        URL url = new URL("https://6pan.cn/static/css/1.235196c3.chunk.css");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(18000);
        connection.setReadTimeout(6000);
        connection.setRequestMethod("GET");
        connection.connect();
        StringBuilder result = new StringBuilder();
        if (connection.getResponseCode() == 200) {
            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String s = reader.readLine();
            while (s != null && !"".equals(s)) {
                result.append(s);
                s = reader.readLine();
            }
        }else{
            sendEmail.send();
        }
        return result.toString();
    }
}
