package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member); // 저장하고 저장한 멤버 반환
    Optional<Member> findById(Long id); // Id 로 찾기
    Optional<Member> findByName(String name); // Name 로 찾기
    List<Member> findAll(); // 모든 회원 정보 반환
}
