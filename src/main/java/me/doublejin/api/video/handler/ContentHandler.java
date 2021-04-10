package me.doublejin.api.video.handler;

import lombok.RequiredArgsConstructor;
import me.doublejin.api.video.entity.Content;
import me.doublejin.api.video.repos.ContentRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ContentHandler {

    private final ContentRepository contentRepository;

    public ServerResponse getContents(ServerRequest serverRequest) {
        return ServerResponse.ok().body(contentRepository.findAll());
    }

    public ServerResponse getContent(ServerRequest serverRequest) {
        long contentIndex = Long.parseLong(serverRequest.pathVariable("contentIndex"));
        return ServerResponse.ok().body(contentRepository.findById(contentIndex));
    }

    public ServerResponse putContent(ServerRequest serverRequest) throws ServletException, IOException {
        Content content = contentRepository.save(serverRequest.body(Content.class));
        return ServerResponse.ok().body(contentRepository.findById(content.getContentSequence()));
    }

    public ServerResponse deleteContent(ServerRequest serverRequest) {
        long contentIndex = Long.parseLong(serverRequest.pathVariable("contentIndex"));
        Optional<Content> content = contentRepository.findById(contentIndex);
        contentRepository.delete(content.orElse(null));
        return ServerResponse.ok().body(content);
    }
}
