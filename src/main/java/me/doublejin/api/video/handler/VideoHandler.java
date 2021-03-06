package me.doublejin.api.video.handler;

import me.doublejin.api.video.entity.Video;
import me.doublejin.api.video.repos.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class VideoHandler {

    private final VideoRepository videoRepository;

    public ServerResponse getVideos(ServerRequest serverRequest) {
        return ServerResponse.ok().body(videoRepository.findAll());
    }

    public ServerResponse getVideo(ServerRequest serverRequest) {
        long videoIndex = Long.parseLong(serverRequest.pathVariable("videoIndex"));
        if (!videoRepository.existsById(videoIndex)) {
            return ServerResponse.notFound().build();
        }
        return ServerResponse.ok().body(videoRepository.findById(videoIndex));
    }

    public ServerResponse putVideo(ServerRequest serverRequest) throws ServletException, IOException {
        long contentIndex = Long.parseLong(serverRequest.pathVariable("contentIndex"));
        Video video = videoRepository.save(serverRequest.body(Video.class));
        return ServerResponse.ok().body(videoRepository.findById(video.getVideoSequence()));
    }

    public ServerResponse deleteVideo(ServerRequest serverRequest) {
        long videoIndex = Long.parseLong(serverRequest.pathVariable("videoIndex"));
        Optional<Video> video = videoRepository.findById(videoIndex);
        if (video.isEmpty()) {
            return ServerResponse.notFound().build();
        }
        videoRepository.delete(video.orElse(null));
        return ServerResponse.ok().body(video);
    }
}