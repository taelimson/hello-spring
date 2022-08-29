package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    //키는 회원 아이디이기 때문에 Long으로, 값은 Member로 한다.
    //save를 할때 어딘가 저장을 해야하기 때문에.
    //실무에서는 동시되는 문제가 있을수 있기 때문에 concurrent를 사용해야하는데
    //여기는 예제니까 단순하게 HashMap으로 했음

    private static long sequence = 0L;
    // 동시성 문제를 웨해서 어텀 long을 사용해야하는데 그냥 예제라서 단순하게 함


    @Override
    public Member save(Member member) {
        member.setId(++sequence); //스토어에 넘거가기 전에 아이디를 먼저 셋팅해주고, 멤버를 세이브할때, 먼저 시퀀스 값을 하나 올려준다
        store.put(member.getId(), member); //멤버아이디를 스토어에 넣어준다
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
        //아이디가 없으면, null이 반환되기 때문에 optional을 사용해서 감싸서 반환한다. 그러면 나중에 어떤방법으로 사용할 수있다.
    }

    @Override
    public Optional<Member> findByName(String name) {
         return store.values().stream().filter(member -> member.getName().equals(name)).findAny();
                //stream을 사용해서 loop를 돌린다. stream은 람다를 사용하기 위함.
                //findAny 하나를 찾아서 리턴한다
        }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
        
        //test입니다 커밋
    }
}
