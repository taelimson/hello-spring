package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    //repository의 4가지 기능을 만들었다.
    Member save(Member member); //회원을 저장하면 저장된 회원이 반환
    Optional<Member> findById(Long id); //optional은 findby로 가져오면 null일수있어서, null을 그대로 반환하는 것 보다 옵셔널로 감싸서 가져온다.
    Optional<Member> findByName(String name);
    List<Member> findAll(); //지금까지 저장된 모든 리스트를 반환
}
