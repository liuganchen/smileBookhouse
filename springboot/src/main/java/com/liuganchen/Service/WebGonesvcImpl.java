package com.liuganchen.Service;

import com.liuganchen.entity.WebInfo;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import javax.annotation.Resource;

@Service
public class WebGonesvcImpl implements WebGonesvc, PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    public void setSite(Site site) {
        this.site = site;
    }

    public Object[] getObjects() {
        return objects;
    }

    public void setObjects(Object[] objects) {
        this.objects = objects;
    }

    private Object[] objects;

    @Override
    public void process(Page page) {
        page.putField("name", page.getHtml().xpath("//*[@id=\"list\"]/dl").toString());
        if (page.getResultItems().get("name") == null) {
            //skip this page
            page.setSkip(true);

        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    @Override
    public Object[] getitle(String url) {
        Spider.create(new WebGonesvcImpl())
                //从"https://github.com/code4craft"开始抓
                .addUrl(url)
                .addPipeline(new ConsolePipeline())
                //开启5个线程抓取
                .thread(5)
                //启动爬虫
                .run();

        return  WebInfo.bookChapter;
    }

    public static void main(String[] args) {
        Spider.create(new WebGonesvcImpl())
                //从"https://github.com/code4craft"开始抓
                .addUrl("http://www.cangqionglongqi.com/fanrenxiuxianzhuan/")
                .addPipeline(new ConsolePipeline())
                //开启5个线程抓取
                .thread(5)
                //启动爬虫
                .run();
    }
}
