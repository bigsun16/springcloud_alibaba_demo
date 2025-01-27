package com.qihui.sun.gateway.filters;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
public class OnceTokenGatewayFilterFactory extends AbstractNameValueGatewayFilterFactory {
    @Override
    public GatewayFilter apply(NameValueConfig config) {
        //给请求添加一个请求头，key为token，value为config.getValue()
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                return chain.filter(exchange).then(
                        Mono.fromRunnable(() -> {
                            System.out.println("过滤器执行了");
                            String value = config.getValue();
                            String headerValue = "uuid".equals(value) ? UUID.randomUUID().toString() : value;
                            exchange.getRequest().getHeaders()
                                    .add(config.getName(), headerValue);
                        })
                );
            }
        };
    }
}
