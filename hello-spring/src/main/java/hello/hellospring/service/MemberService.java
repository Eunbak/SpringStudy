package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

    //직접 생성하는 것이 아니라 외부에서 생성하도록
    //Dependency Injection DI
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(Member member) {
        //같은 이름이 있는 중복 회원 X
        // memberRepository.findByName(member.getName());
        // 입력하고 단축키 ctrl + alt + v (window)
        // 입력하고 단축키 command + option + v (mac)
        // => Optional<Member> result = memberRepository.findByName(member.getName());

        //result.orElseGet() 있고 없고에 따라 다른 처리

        //만약 값이 존재하면,
        //result.ifPresent(m -> {
        //    throw new IllegalStateException("이미 존재하는 회원입니다.");
        //}); // Optional 기능

        //하나로 깔끔하게 통일
        // ctrl + alt + m 으로 메서드로 뽑아냄
        validateDuplicatedMember(member); // 중복회원 검증

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicatedMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
