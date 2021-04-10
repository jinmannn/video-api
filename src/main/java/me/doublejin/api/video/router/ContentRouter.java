package me.doublejin.api.video.router;

import me.doublejin.api.video.handler.ContentHandler;
import me.doublejin.api.video.handler.VideoHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.web.servlet.function.RequestPredicates.accept;
import static org.springframework.web.servlet.function.RouterFunctions.route;

@Configuration
public class ContentRouter {

    @Bean
    public RouterFunction<ServerResponse> contentRoutes(ContentHandler contentHandler) {
        return route().path("/contents", toolBuilder -> {
            //
            toolBuilder.nest(accept(MediaType.APPLICATION_JSON), jsonBuilder -> {
                //
                jsonBuilder.GET("", contentHandler::getContents)
                        .PUT("", contentHandler::putContent)
                        .GET("/{contentIndex}", contentHandler::getContent)
                        .DELETE("/{contentIndex}", contentHandler::deleteContent);
            });
        }).build();
    }

    @Bean
    public RouterFunction<ServerResponse> videoRoutes(VideoHandler videoHandler) {
        return route().path("/contents/{contentIndex}/videos", toolBuilder -> {
            //
            toolBuilder.nest(accept(MediaType.APPLICATION_JSON), jsonBuilder -> {
                //
                jsonBuilder.GET("", videoHandler::getVideos)
                        .PUT("", videoHandler::putVideo)
                        .GET("/{videoIndex}", videoHandler::getVideo)
                        .DELETE("/{videoIndex}", videoHandler::deleteVideo);
            });
        }).build();
    }
}
