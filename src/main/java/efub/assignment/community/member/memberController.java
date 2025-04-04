package efub.assignment.community.member;

import efub.assignment.community.member.dto.MemberRequestDTO;
import efub.assignment.community.member.domain.Member;
import efub.assignment.community.member.dto.MemberResponseDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
public class memberController {
    private final MemberService memberService;

    // 생성자 주입
    public memberController(MemberService memberService) {
        this.memberService = memberService;
    }

    //회원가입 API
    @PostMapping("signup")
    public String registerMember(@RequestBody MemberRequestDTO requestDTO){
        memberService.registerMember(requestDTO);
        return "회원가입이 완료되었습니다!";
    }

    // memberId에 해당하는 회원 정보 조회
    @GetMapping("/{memberId}")
    public MemberResponseDTO getMemberByMemberId(@PathVariable Long memberId){
        return memberService.getMemberByMemberId(memberId);
    }

    // 회원 정보 수정
    // 이메일 변경 - 변환된 내용 반환하도록 변경해야됨
    @PatchMapping("/{memberId}/email")
    public void changeEmail(@PathVariable Long memberId,
                                 @RequestParam String newEmail){
        memberService.changeEmail(memberId, newEmail);
    }

    // 닉네임 변경
    @PatchMapping("/{memberId}/nickname")
    public void updateNickname(@PathVariable Long memberId,
                               @RequestParam String newNickname){
        memberService.updateNickname(memberId, newNickname);
    }

    // 비밀번호 변경
    @PatchMapping("/{memberId}/password")
    public String changePassword(@PathVariable Long memberId,
                               @RequestParam String newPassword){
        memberService.changePassword(memberId, newPassword);
        return "비밀번호가 성공적으로 변경되었습니다.";
    }

    // 회원 논리적 삭제
    @PatchMapping("/{memberId}")
    public String logicalDeletion(@PathVariable Long memberId){
        memberService.logicalDeleteAccount(memberId);
        return "회원이 비활성화 처리되었습니다.";
    }
}
