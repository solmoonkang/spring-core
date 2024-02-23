package hello.core.member;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryMemberRepository implements MemberRepository {

    // 동시성 이슈로 인해 ConcurrentHashMap을 사용했다.
    private static Map<Long, Member> store = new ConcurrentHashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}