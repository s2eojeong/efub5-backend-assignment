package efub.assignment.community.member.service;

import efub.assignment.community.member.dto.request.CreateMemberRequestDto;
import efub.assignment.community.member.dto.request.NicknameUpdateRequestDto;
import efub.assignment.community.member.dto.response.CreateMemberResponseDto;
import efub.assignment.community.member.dto.response.MemberResponseDto;
import efub.assignment.community.member.entity.Member;
import efub.assignment.community.member.entity.MemberStatus;
import efub.assignment.community.member.repository.MembersRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class MembersService {

    private final MembersRepository membersRepository;

    // 회원 단건 조회 GET members/{memberId}
    public MemberResponseDto getMember(Long MemberId) {
        Member member = membersRepository.findByMemberId(MemberId).orElseThrow(() -> new IllegalArgumentException("해당 계정을 찾을 수 없습니다."));
        return MemberResponseDto.from(member);
    }

    // 회원 생성
    public CreateMemberResponseDto createMember(@Valid CreateMemberRequestDto requestDto){
        if(membersRepository.existsByStudentId(requestDto.getStudentId())){
            throw new IllegalArgumentException("이미 존재하는 학번입니다." + requestDto.getStudentId());
        }
        Member member = requestDto.toEntity();
        Member savedMember = membersRepository.save(member);

        return CreateMemberResponseDto.from(savedMember);
    }

    // 회원 정보 수정 (닉네임)
    public MemberResponseDto updateMember(Long memberId, NicknameUpdateRequestDto requestDto) {
        Member member = membersRepository.findByMemberId(memberId).orElseThrow(()->new IllegalArgumentException("해당 회원을 찾을 수 없습니다."));
        member.updateNickname(requestDto.getNickname());
        Member updqtedMember = membersRepository.save(member);
        return MemberResponseDto.from(updqtedMember);
    }

    // 회원 논리적 삭제 (status 변경)
    public void deleteMember(Long memberId) {
        Member member = membersRepository.findByMemberId(memberId).orElseThrow(()->new IllegalArgumentException("해당 회원을 찾을 수 없습니다."));
        member.changeStatus(MemberStatus.UNREGISTER);
        membersRepository.save(member);
    }

}
