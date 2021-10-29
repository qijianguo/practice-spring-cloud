package com.qijianguo.springcloud.resttemplate.controller;

import com.qijianguo.springcloud.resttemplate.bean.HouseInfo;
import org.springframework.web.bind.annotation.*;

/**
 * @author qijianguo
 */
@RestController
@RequestMapping("/house")
public class HouseController {

    @GetMapping("/data")
    public HouseInfo getData(@RequestParam("name") String name) {
        return new HouseInfo(1L, "上海市浦东新区梅园三街坊" + name);
    }

    @GetMapping("/data/{name}")
    public String getData2(@PathVariable("name") String name) {
        return name;
    }
}
