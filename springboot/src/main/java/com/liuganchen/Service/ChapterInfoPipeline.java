package com.liuganchen.Service;

import com.liuganchen.entity.WebInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChapterInfoPipeline implements Pipeline {
    @Override
    public void process(ResultItems resultItems, Task task) {
        System.out.println("获取页面: " + resultItems.getRequest().getUrl());
        String res = resultItems.get("name");
        Document document = Jsoup.parse(res);
        Element info = document.getElementById("content");
        Element title = document.select("#box_con > div.bookname > h1").first();
        Map<String,Object> map= new HashMap<>();
        map.put("title",title.text());
        map.put("info",info.text());
        WebInfo.bookInfo = map;
        System.out.println(map.toString());
    }
}
