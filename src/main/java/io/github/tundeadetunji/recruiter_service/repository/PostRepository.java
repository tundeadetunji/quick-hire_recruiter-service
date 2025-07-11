package io.github.tundeadetunji.recruiter_service.repository;

import io.github.tundeadetunji.recruiter_service.domain.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
