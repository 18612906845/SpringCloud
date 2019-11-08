package com.citms.cloud.gateway.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * 动态路由业务类
 *
 * @author wanglei@citms.cn
 * @date 2019/11/08 10:56
 */
@Service
public class DynamicRouteService implements ApplicationEventPublisherAware {

    @Autowired
    private RouteDefinitionWriter routeDefinitionWriter;

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    /**
     * 开启路由配置变更监听
     *
     * @throws NacosException
     */
    @Bean
    public void refreshRouting() throws NacosException {
        System.out.println("开启Nacos配置文件监听");
        Properties properties = new Properties();
        properties.put("serverAddr", "39.106.8.177:8848");
        ConfigService configService = NacosFactory.createConfigService(properties);
        String dataId = "cloud-gateway.json";
        String group = "DEFAULT_GROUP";

        configService.addListener(dataId, group, new Listener() {
            @Override
            public void receiveConfigInfo(String configInfo) {
                List<RouteDefinition> routeDefinitions = JSON.parseArray(configInfo, RouteDefinition.class);
                updateList(routeDefinitions);
            }
            @Override
            public Executor getExecutor() {
                return null;
            }
        });
    }

    /**
     * 更新路由
     */
    public String update(RouteDefinition definition) {
        try {
            this.routeDefinitionWriter.delete(Mono.just(definition.getId()));
            this.routeDefinitionWriter.save(Mono.just(definition)).subscribe();
            this.applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this));
            return "update success";
        } catch (Exception e) {
            e.printStackTrace();
            return "update failure";
        }
    }

    /**
     * 批量更新路由
     */
    public String updateList(List<RouteDefinition> routeDefinitions) {
        routeDefinitions.forEach(this::update);
        return "update done";
    }
}
