package com.citms.cloud.demo.server.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 服务端控制器
 *
 * @author wanglei@citms.cn
 * @date 2019/11/07 11:16
 */
@RequestMapping("/server")
@RestController
@RefreshScope
public class ServerController {

    private final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Value("${server.param:''}")
    private String param;

    /**
     * 获取当前时间
     *
     * @return
     */
    @GetMapping("/getNowTime")
    public String getNowTime(){
        return SDF.format(new Date());
    }

    /**
     * 获取Nacos配置动态变更
     *
     * @return
     */
    @GetMapping("/getNacosName")
    public String getNacosName(){
        return "Nacos配置server.name的值为：" + param;
    }

    /**
     * 输出字符串参数
     *
     * @return
     */
    @GetMapping("/outStringParam")
    public String outStringParam(String param){
        return param;
    }
}
