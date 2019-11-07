package com.citms.cloud.demo.client.controller;

import com.citms.cloud.demo.server.feign.IServerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 客户端控制器
 *
 * @author wanglei@citms.cn
 * @date 2019/11/07 15:43
 */
@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private IServerClient serverClient;

    @GetMapping("/callFeignByGetNowTime")
    public String callFeignByGetNowTime(){
        return serverClient.getNowTime();
    }

}
