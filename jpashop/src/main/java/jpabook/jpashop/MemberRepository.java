package jpabook.jpashop;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {

    @PersistenceContext // 해당 어노테이션이 있으면 스프링 부트 EntityManager를 주입 해줌
    private EntityManager em;

    public long save(Member member) { // 저장
        em.persist(member);

        return member.getId();
    }

    public Member find(Long id) { // 조회
        return em.find(Member.class, id);
    }
}
