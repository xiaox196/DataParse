package com.ming.data.utils;

import com.ming.data.entity.TestHome;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author alun
 * @data 2019/10/8
 */
public class CrawlingTestHome {

    public static void body(String url, TestHome testHome) {
        try {
            String hosts = "https://testerhome.com";
            Document document = Jsoup.connect(hosts + url).get();
            Elements elements = document.select("div.panel-body");
            testHome.setText(elements.text());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<TestHome> home(String url) {
        List<TestHome> lists = new ArrayList<>();
        Jedis jedis = new Jedis("203.195.193.153", 6379);
        try {
            Document document = Jsoup.connect(url).get();
            Elements titles = document.select("div.topics").select("div.topic").select("div.infos");
            for (Element element : titles) {
                Elements links = element.getElementsByTag("a");
                if (links.hasAttr("title")) {
                    TestHome testHome = new TestHome();
                    String title = links.attr("title");
                    String href = links.attr("href");
                    testHome.setTitle(title.trim());
                    testHome.setUrl(href);
                    body(href, testHome);
                    jedis.set(href, testHome.getText());
                    lists.add(testHome);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lists;
    }

}
