package efub.assignment.community.comment.domain;

import efub.assignment.community.global.domain.BaseEntity;
import efub.assignment.community.member.domain.Member;
import efub.assignment.community.post.domain.Post;
import jakarta.persistence.*;

@Entity
public class Comment extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @ManyToOne @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Column(nullable = false)
    private boolean anonymity;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "member_id", nullable = false)
    private Member commentor;

    protected Comment() {};

    private Comment(Post post, boolean anonymity, String content, Member commentor){
        this.post = post;
        this.anonymity = anonymity;
        this.content = content;
        this.commentor = commentor;
    }

    public static Comment create(Post post, boolean anonymity, String content, Member commentor){
        return new Comment(post, anonymity, content, commentor);
    }

    // Getter
    public Long getCommentId() {
        return commentId;
    }

    public Post getPost() {
        return post;
    }

    public boolean isAnonymity() {
        return anonymity;
    }

    public String getContent() {
        return content;
    }

    public Member getCommentor() {
        return commentor;
    }

    // Setter
    public void setContent(String content) {
        this.content = content;
    }
}
