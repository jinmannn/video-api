package me.doublejin.api.video.router;

import me.doublejin.api.video.handler.ToolHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.web.servlet.function.RequestPredicates.accept;
import static org.springframework.web.servlet.function.RouterFunctions.route;

@Configuration
public class ToolRouter {

    @Bean
    public RouterFunction<ServerResponse> toolRoutes(ToolHandler toolHandler) {
        return route().path("/tools", toolBuilder -> {
            //
            toolBuilder.nest(accept(MediaType.TEXT_PLAIN), textPlainBuilder -> {
                //
                textPlainBuilder.GET("/is-running", toolHandler::isRunning)
                        .POST("/dispatch", toolHandler::dispatch);
            });
        }).build();
    }
}
