package com.efub_assignment.community.community.post.repository;

import com.efub_assignment.community.community.member.domain.Member;
import com.efub_assignment.community.community.post.domain.Post;
import com.efub_assignment.community.community.post.domain.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

    boolean existsByPostAndMember(Post post, Member member);
    Optional<PostLike> findByPostAndMember(Post post, Member member);
}
