package com.easy.elk;

import com.easy.elk.exception.JsonExceptionHandler;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.http.codec.support.DefaultServerCodecConfigurer;
import org.springframework.web.reactive.result.view.ViewResolver;

import java.util.Collections;
import java.util.List;

/**
 * 启动项
 *
 * @author tallon
 * @version v1.0.0
 * @date 2020-12-10 11:14
 */
@SpringBootApplication
public class ElkApplication {

    @Primary
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public ErrorWebExceptionHandler errorWebExceptionHandler(ObjectProvider<List<ViewResolver>> viewResolversProvider,
                                                             ServerCodecConfigurer serverCodecConfigurer) {
        JsonExceptionHandler jsonExceptionHandler = new JsonExceptionHandler();
        jsonExceptionHandler.setViewResolvers(viewResolversProvider.getIfAvailable(Collections::emptyList));
        jsonExceptionHandler.setMessageWriters(serverCodecConfigurer.getWriters());
        jsonExceptionHandler.setMessageReaders(serverCodecConfigurer.getReaders());
        return jsonExceptionHandler;
    }


    @Bean
    public ServerCodecConfigurer serverCodecConfigurer() {
        return new DefaultServerCodecConfigurer();
    }

    public static void main(String[] args) {
        SpringApplication.run(ElkApplication.class);
    }
}
