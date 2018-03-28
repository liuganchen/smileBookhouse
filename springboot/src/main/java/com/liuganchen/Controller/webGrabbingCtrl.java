package com.liuganchen.Controller;

import com.liuganchen.Service.WebGonesvc;
import com.liuganchen.Service.WebGtwosvc;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

@RestController
public class webGrabbingCtrl {

    @Autowired
    private WebGonesvc webGonesvc;

    @Autowired
    private WebGtwosvc webGtwosvc;

    @RequestMapping("/test")
    public String test() {
        return "test";
    }

    private RestTemplate restTemplate = new RestTemplate();


    @RequestMapping("/getUrlData")
    public Object[] getFormUrlData(String url) {
        return webGonesvc.getitle(url);
    }

    @RequestMapping("/getChapterInfo")
    public Map getChapterInfo(String url) {
        return webGtwosvc.getContent(url);
    }

    @RequestMapping("/getUrlDataTest")
    public String getFormUrlDataTest() throws IOException {
        URL url = new URL("https://www.baidu.com/");
        return IOUtils.toString(url.openStream());
    }
}
