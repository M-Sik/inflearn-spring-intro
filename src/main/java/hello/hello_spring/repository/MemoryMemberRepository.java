// 구현체

package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // 리턴값이 null일 수 있는경우에는 Optinal.ifNullable로 감싸줌
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values()
                .stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // 배열로 반환할때 자바에서는 ArrayList<> 사용
    }

    public void clearStore() {
        store.clear();
    }
}
