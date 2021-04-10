package me.doublejin.api.video.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.doublejin.api.video.entity.Content;
import me.doublejin.api.video.entity.Video;
import me.doublejin.api.video.repos.ContentRepository;
import me.doublejin.api.video.repos.VideoRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ToolHandler {

    public ServerResponse isRunning(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .body("isRunning");
    }

    @Value("${server.video-path}")
    private String videoPath;

    @Value("${server.video-url}")
    private String videoUrl;

    private final ContentRepository contentRepository;
    private final VideoRepository videoRepository;

    public ServerResponse dispatch(ServerRequest serverRequest) {

        File videoDir = new File(videoPath);
        for (File contentDir : videoDir.listFiles()) {

            if (!contentDir.isDirectory()) {
                log.debug("is not directory : {}", contentDir.getName());
                continue;
            }

            Content content = Content.builder()
                    .name(contentDir.getName())
                    .videos(new HashSet<>())
                    .build();

            contentRepository.save(content);

            List<Video> videoList = new ArrayList<>();
            int orderIndex = 0;
            for (File videoFile : contentDir.listFiles()) {
                Video video = Video.builder()
                        .name(videoFile.getName())
                        .fileUrl(videoUrl + content.getName() + "/" + videoFile.getName())
                        .orderIndex(orderIndex++)
                        .content(content)
                        .build();

                content.addVideo(video);
                videoList.add(video);
            }
            videoRepository.saveAll(videoList);
        }

        return ServerResponse.ok()
                .body("ok");
    }
}
