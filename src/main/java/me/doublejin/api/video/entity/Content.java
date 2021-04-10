package me.doublejin.api.video.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contentSequence;

    private String name;

    @OneToMany(mappedBy = "content")
    private Set<Video> videos;

    public void addVideo(Video video) {
        video.setContent(this);
        videos.add(video);
    }

    public String getGetUrl(){
        return "http://api-video.doublejin.me/contents/" + contentSequence;
    }

    public String getPutUrl(){
        return "getPutUrl";
    }

    public String getPatchUrl(){
        return "getPatchUrl";
    }

    public String getDeleteUrl(){
        return "getDeleteUrl";
    }
}
