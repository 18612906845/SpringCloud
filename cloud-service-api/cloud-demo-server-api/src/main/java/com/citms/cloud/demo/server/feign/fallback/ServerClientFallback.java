package com.citms.cloud.demo.server.feign.fallback;

import com.citms.cloud.demo.server.feign.IServerClient;
import org.springframework.stereotype.Component;

/**
 * 异常处理
 *
 * @author wanglei@citms.cn
 * @date 2019/11/07 15:54
 */
@Component
public class ServerClientFallback implements IServerClient {

    @Override
    public String getNowTime() {
        return "获取当前时间异常。";
    }

    @Override
    public String getNacosName() {
        return "获取Nacos配置动态变更异常。";
    }

    @Override
    public String outStringParam(String param) {
        return "输出字符串参数异常。";
    }
}
