package me.doublejin.api.video.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long videoSequence;

    private String name;
    private String fileUrl;
    private String thumbnailUrl;
    private int runningSeconds;
    private int orderIndex;
    private int star;

    @ManyToOne
    private Content content;
}
