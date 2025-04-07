package efub.assignment.community.member.controller;

import efub.assignment.community.member.service.MemberService;
import efub.assignment.community.member.dto.EmailUpdateRequestDTO;
import efub.assignment.community.member.dto.MemberRequestDTO;
import efub.assignment.community.member.dto.MemberResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    // 생성자 주입
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    //회원가입 API
    @PostMapping("/")
    public ResponseEntity<MemberResponseDTO> registerMember(@RequestBody MemberRequestDTO requestDTO){
        MemberResponseDTO responseDTO = memberService.registerMember(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    // memberId에 해당하는 회원 정보 조회
    @GetMapping("/{memberId}")
    public ResponseEntity<MemberResponseDTO> getMemberByMemberId(@PathVariable Long memberId){
        MemberResponseDTO member = memberService.getMemberByMemberId(memberId);
        return ResponseEntity.ok(member);
    }

    // 회원 정보 수정
    // 이메일 변경
    @PatchMapping("/{memberId}/email")
    public ResponseEntity<MemberResponseDTO> changeEmail(@PathVariable Long memberId, @RequestBody EmailUpdateRequestDTO request){
        MemberResponseDTO responseDTO = memberService.changeEmail(memberId, request.getNewEmail());
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    // 닉네임 변경
    @PatchMapping("/{memberId}/nickname")
    public ResponseEntity<Void> updateNickname(@PathVariable Long memberId,
                               @RequestParam String newNickname){
        memberService.updateNickname(memberId, newNickname);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 비밀번호 변경
    @PatchMapping("/{memberId}/password")
    public ResponseEntity<String> changePassword(@PathVariable Long memberId,
                               @RequestParam String newPassword){
        memberService.changePassword(memberId, newPassword);
        return ResponseEntity.ok("비밀번호가 성공적으로 변경되었습니다.");
    }

    // 회원 논리적 삭제
    @PatchMapping("/{memberId}")
    public ResponseEntity<String> logicalDeletion(@PathVariable Long memberId){
        memberService.logicalDeleteAccount(memberId);
        return ResponseEntity.ok("회원이 비활성화 처리되었습니다.");
    }
}
