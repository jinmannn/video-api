package me.doublejin.api.video.repos;

import me.doublejin.api.video.entity.Content;
import me.doublejin.api.video.entity.ContentMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {

    List<ContentMapping> findAllBy();
}
