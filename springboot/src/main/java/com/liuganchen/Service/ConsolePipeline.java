package com.liuganchen.Service;

import com.liuganchen.entity.WebInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ConsolePipeline implements Pipeline {

    @Override
    public void process(ResultItems resultItems, Task task) {
        System.out.println("获取页面: " + resultItems.getRequest().getUrl());
        String res = resultItems.get("name");
        Document document = Jsoup.parse(res);
        Elements elements = document.select("a[href]");
        List<Map<String,Object>> stringList = new ArrayList<>();
        for (Element element : elements) {
            Map<String,Object> map= new HashMap<>();
            map.put("id",element.attr("href"));
            map.put("info",element.text());
            stringList.add(map);
        }
        WebInfo.bookChapter = stringList.toArray();;

    }
}
