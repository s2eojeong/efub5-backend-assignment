package efub.assignment.community.member.controller;

import efub.assignment.community.member.dto.*;
import efub.assignment.community.member.dto.CreateMemberResponseDto;
import efub.assignment.community.member.dto.NicknameUpdateResponseDto;
import efub.assignment.community.member.service.MembersService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MembersService membersService;

    // 회원 조회: GET /members/{memberId}
    @GetMapping("/{memberId}")
    public ResponseEntity<MemberResponseDto> getMember(@PathVariable("memberId") Long memberId) {
        MemberResponseDto responseDto = membersService.getMember(memberId);
        return ResponseEntity.ok(responseDto);
    }

    // 회원 생성: POST/members
    @PostMapping
    public ResponseEntity<CreateMemberResponseDto> createMember(@RequestBody @Valid CreateMemberRequestDto requestDto) {
        CreateMemberResponseDto responseDto = membersService.createMember(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    // 회원 정보 수정: PATCH/members/profile/{memberId}
    @PatchMapping("/profile/{memberId}")
    public ResponseEntity<NicknameUpdateResponseDto> updateMember(@PathVariable("memberId")Long memberId, @RequestBody @Valid NicknameUpdateRequestDto requestDto) {
        NicknameUpdateResponseDto responseDto = membersService.updateMember(memberId, requestDto);
        return ResponseEntity.ok(responseDto);
    }


    // 회원 논리적 삭제(탈퇴): PATCH/members/{memberId}
    @PatchMapping("/{memberId}")
    public ResponseEntity<String> deleteMember(@PathVariable("memberId") Long memberId) {
        membersService.deleteMember(memberId);
        return ResponseEntity.ok("message: 성공적으로 탈퇴되었습니다.");
    }

}
