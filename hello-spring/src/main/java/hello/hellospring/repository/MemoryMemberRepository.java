package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.nio.LongBuffer;
import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        //시스템이 id 부여
        member.setId(++sequence);
        //save 하기 전에 이름이 넘어옴
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // 결과가 null 일 가능성이 있으면 다음과 같이 감싼다.
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        // findAny 가장 먼저 찾은 요소를 반환
        // 결과가 없으면 Optional 에 null 이 포함되어서 반환
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
