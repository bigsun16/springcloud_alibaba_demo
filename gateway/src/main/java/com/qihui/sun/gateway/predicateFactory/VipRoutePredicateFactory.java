package com.qihui.sun.gateway.predicateFactory;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@Component
public class VipRoutePredicateFactory extends AbstractRoutePredicateFactory<VipRoutePredicateFactory.Config> {
    public VipRoutePredicateFactory() {
        super(Config.class);
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return new GatewayPredicate() {

            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                //localhost:8080/search?q=haha&user=qihuis
                String param = serverWebExchange.getRequest().getQueryParams().getFirst(config.getParam());
                return config.getValue().equals(param);
            }
        };
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("param", "value");
    }

    @Setter
    @Getter
    public static class Config {
        @NonNull
        private String param;

        @NonNull
        private String value;

    }
}
