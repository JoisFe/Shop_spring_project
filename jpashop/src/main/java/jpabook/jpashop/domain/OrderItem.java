package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "order_item_id") // 매핑을 위해 primary key는 "ORDER_ITEM_ID"
    private Long id;

    @ManyToOne // OrderItem과 Item은 다대일 관계
    @JoinColumn(name = "item_id") // 매핑을 ITEM_ID가 연관관계 주인
    private Item item;

    @ManyToOne // OrderItem과 Order는 다대일 관계
    @JoinColumn(name = "order_id") // 매핑을 ORDER_ID가 연관관계 주인
    // Order에도 orderItems가 있고 OrderItem에도 order가 있다.
    // 양방향 연관관계
    // 테이블을 보면 ORDER_ITEM에 ORDER_ID가 FK
    private Order order;

    private int orderPrice; // 주문 가격

    private int count; // 주문 수량
}
