package com.efub_assignment.community.community.comment.domain;

import com.efub_assignment.community.community.global.entity.BaseTimeEntity;
import com.efub_assignment.community.community.member.domain.Member;
import com.efub_assignment.community.community.post.domain.Post;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseTimeEntity {

    @Id
    @Column(name="comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length=1000)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", updatable = false)
    private Member writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="post_id",updatable = false)
    private Post post;

    @Builder
    public Comment(String content, Member writer, Post post){
        this.content=content;
        this.writer=writer;
        this.post=post;
    }



}
