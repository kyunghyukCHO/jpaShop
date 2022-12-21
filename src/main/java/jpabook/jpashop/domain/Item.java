package jpabook.jpashop.domain;

import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
public abstract class Item {
    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity; // 데이터를 가지고 있는 객체가 로직을 담당한다.

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    // == 재고 수량 증가 비즈니스 로직 == //
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    // == 재고 수량 감소 비즈니스 로직 == //
    public void removeStock(int quantity) {
        int restStock = stockQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }

}
