package com.qihui.sun.gateway.filters;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class RTGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //记录请求执行时间
        exchange.getAttributes().put("startTime", System.currentTimeMillis());
        return chain.filter(exchange).then(
                Mono.fromRunnable(() -> {
                    Long startTime = exchange.getAttribute("startTime");
                    Long endTime = System.currentTimeMillis();
                    System.out.println("请求执行时间：" + (endTime - startTime) + "ms");
                })
        ).then(Mono.empty());

    }

    @Override
    public int getOrder() {
        return 0;
    }

}
