package efub.assignment.community.board.domain;

import efub.assignment.community.member.domain.Member;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    @Column(nullable = false)
    private String boardName;

    @Column
    private String announcement;

    @Column
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id", nullable = false)
    private Member master;      //FK

    //생성일, 수정일 필요
    @CreatedDate @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;


    // 기본 생성자 (JPA가 필요로 함)
    protected Board() {
    }

    // private 생성자
    public Board(String boardName, String announcement, String description, Member master) {
        this.boardName = boardName;
        this.announcement = announcement;
        this.description = description;
        this.master = master;
    }

    public static Board create(String boardName, String announcement, String description, Member master){
        return new Board(boardName, announcement, description, master);
    }

    //Getter
    public Long getBoardId() {
        return boardId;
    }

    public String getBoardName() {
        return boardName;
    }

    public String getAnnouncement() {
        return announcement;
    }

    public String getDescription() {
        return description;
    }

    public Member getMaster() {
        return master;
    }

    //Setter
    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMaster(Member master) {
        this.master = master;
    }
}
