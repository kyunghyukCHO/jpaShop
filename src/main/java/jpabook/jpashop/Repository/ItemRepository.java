package jpabook.jpashop.Repository;

import jpabook.jpashop.domain.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item) {
        if (item.getId() == null) {
            em.persist(item); // 저장
        } else {
            em.merge(item); // 업데이트
            // JPA 합병은 수정되지 않은 변수를 NULL 로 업데이트 합니다.
            // 즉 권해지는 방법이 아닙니다.
        }
    }
    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }
    public List<Item> findAll() {
        return em.createQuery("select i from Item i",Item.class).getResultList();
    }
}
