package com.citms.cloud.demo.server.feign;

import com.citms.cloud.demo.server.feign.fallback.ServerClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 服务Feign接口
 *
 * @author wanglei@citms.cn
 * @date 2019/11/07 15:52
 */
@FeignClient(name = "cloud-demo-server", fallback = ServerClientFallback.class)
public interface IServerClient {

    @GetMapping("/server/getNowTime")
    String getNowTime();

    @GetMapping("/server/getNacosName")
    String getNacosName();

    @GetMapping("/server/outStringParam")
    String outStringParam(String param);
}
