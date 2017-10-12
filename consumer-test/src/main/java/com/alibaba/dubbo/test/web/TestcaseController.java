package com.alibaba.dubbo.test.web;

import com.alibaba.dubbo.test.dto.Bean;
import com.alibaba.dubbo.test.service.AnnotateService;
import com.alibaba.dubbo.test.service.AsyncService;
import com.alibaba.dubbo.test.service.CacheService;
import com.alibaba.dubbo.test.service.DemoService;
import com.alibaba.dubbo.test.service.WSService;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ken.lj on 2017/9/25.
 */
@RestController
@RequestMapping("/testcase")
public class TestcaseController {

    @Autowired
    private DemoService demoService;
    @Autowired
    private AsyncService asyncService;
    @Autowired
    private CacheService cacheService;
    @Autowired
    private WSService wsService;
    @Autowired
    private AnnotateService annotateService;

    @RequestMapping("/all")
    public String testAll() {
        String arg = "hello";
        String retStr = demoService.testString(arg);
        Assert.assertEquals(retStr, arg);

        Bean bean1 = new Bean(1, "pojo1");
        Bean retBean = demoService.testPojo(bean1);
//        Assert.assertEquals(retBean, bean1);
        Assert.assertNotNull(retBean);

        List<Bean> list = new ArrayList<Bean>();
        Bean bean2 = new Bean(2, "pojo2");
        list.add(bean1);
        list.add(bean2);
        List<Bean> retList = demoService.testList(list);
//        Assert.assertEquals(retList, list);
        Assert.assertTrue(retList.size() == 2);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("key1", bean1);
        map.put("key2", bean2);
        Map<String, Object> retMap = demoService.testMap(map);
//        Assert.assertEquals(retMap, map);
        Assert.assertTrue(map.size() == 2);

        // asyncService.test();
        return "all";
    }

    @RequestMapping("/async")
    public String testAsync() {
        return "async";
    }

    @RequestMapping("/cache")
    public String testCache() {
        return "cache";
    }

    @RequestMapping("/simple")
    public String testSimple() {
        String result = demoService.testString("hello");
        System.out.println(result);
        return "DemoService string";
    }

    @RequestMapping("/annotate")
    public String testAnnotate() {
        return "annotate";
    }

   /* @RequestMapping("/java8time")
    public String testJava8Time() {
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime retInstant = demoService.testJava8Time(localDateTime);
        System.out.println(localDateTime);
        System.out.println(retInstant);
        return "aaa";
    }

    @RequestMapping("/java8zoneddatetime")
    public String testJava8Zoneddatetime() {
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        ZonedDateTime retZonedDateTime = demoService.testJava8Time(zonedDateTime);
        System.out.println(zonedDateTime);
        System.out.println(retZonedDateTime);
        return "bbb";
    }*/
}