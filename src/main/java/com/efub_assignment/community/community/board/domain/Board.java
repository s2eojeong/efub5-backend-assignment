package com.efub_assignment.community.community.board.domain;

import com.efub_assignment.community.community.global.entity.BaseTimeEntity;
import com.efub_assignment.community.community.member.domain.Member;
import com.efub_assignment.community.community.post.domain.Post;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    // 게시판 이름
    private String boardName;

    // 게시판 설명
    private String description;

    //게시판 공지
    private String notice;

    //게시판 비밀번호
    private String password;

    //게시판 작성자 닉네임
    @ManyToOne(fetch = FetchType.LAZY) //성능 최적화
    private Member owner;

    // 게시물 리스트
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true) // 게시판이 삭제되면 게시물도 삭제
    private List<Post> posts = new ArrayList<>();

    //포스트 개수
    private Long postCount;

    @Builder
    public Board(String boardName, String description, String notice, String password, Member owner){
        this.boardName = boardName;
        this.description = description;
        this.notice = notice;
        this.password = password;
        this.owner = owner;
        this.postCount = 0L;

    }

    public void changeOwner(Member newOwner){
        this.owner = newOwner;
    }
}
