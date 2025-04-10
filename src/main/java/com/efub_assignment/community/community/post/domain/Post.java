package com.efub_assignment.community.community.post.domain;

import com.efub_assignment.community.community.global.entity.BaseTimeEntity;
import com.efub_assignment.community.community.member.domain.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) //생성자가 외부에서 직접 호출되지 않도록 제한
public class Post extends BaseTimeEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //작성자 닉네임
    @ManyToOne(fetch = FetchType.LAZY) //성능 최적화
    private Member writer;

    // 제목
    private String title;

    //내용
    private String content;

    //조회수
    private Long viewCount;

    //빌더
    @Builder
    public Post(String title, String content, Member writer){
        this.title= title;
        this.content=content;
        this.writer =writer;
        this.viewCount = 0L;
    }

    //게시물 내용 수정
    public void changeContent(String newContent){
        this.content= newContent;
    }






}
