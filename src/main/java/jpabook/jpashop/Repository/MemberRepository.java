package jpabook.jpashop.Repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

//    @PersistenceContext // JPA 엔티티 매니저를 스프링이 주입시켜 준다.
//    private EntityManager em;

    public final EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) { // id 로 단건 조회
        return em.find(Member.class, id);
    }

    public List<Member> findAll() { // 모든 회원 조회
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name) { // 이름으로 회원 조회
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
