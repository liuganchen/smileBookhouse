package com.liuganchen.Service;

import com.liuganchen.entity.WebInfo;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.Map;

@Service
public class WebGtwosvcImpl implements WebGtwosvc,PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    @Override
    public Map getContent(String url) {
         Spider.create(new WebGtwosvcImpl())
                .addUrl(url)
                 .addPipeline(new ChapterInfoPipeline())
                //开启5个线程抓取
                .thread(5)
                //启动爬虫
                .run();
        return WebInfo.bookInfo;
    }

    @Override
    public void process(Page page) {
        page.putField("name", page.getHtml().xpath("//*[@id=\"box_con\"]").toString());
        if (page.getResultItems().get("name") == null) {
            //skip this page
            page.setSkip(true);
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {

        Spider.create(new WebGtwosvcImpl())
                .addUrl("http://www.cangqionglongqi.com/fanrenxiuxianzhuan/368586.html")
                .addPipeline(new ChapterInfoPipeline())
                //开启5个线程抓取
                .thread(5)
                //启动爬虫
                .run();
    }
}
