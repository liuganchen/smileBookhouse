package com.liuganchen.Service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class webGonesvcImplTest {

    @Test
    public void process() {
    }

    @Test
    public void getSite() {
    }

    @Autowired
    private WebGonesvc webGonesvc;

    @Test
    public void getitle() {
        webGonesvc.getitle("http://www.cangqionglongqi.com/fanrenxiuxianzhuan/");
    }
}