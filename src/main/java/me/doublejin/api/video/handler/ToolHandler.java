package me.doublejin.api.video.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

@Component
public class ToolHandler {

    public ServerResponse isRunning(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .body("isRunning");
    }
}
