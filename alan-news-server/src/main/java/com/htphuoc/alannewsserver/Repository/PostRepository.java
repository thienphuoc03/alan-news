package com.htphuoc.alannewsserver.Repository;

import com.htphuoc.alannewsserver.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Long countByCreatedBy(Long userId);
}
