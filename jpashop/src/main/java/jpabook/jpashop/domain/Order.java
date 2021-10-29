package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id") // primary key가 ORDER_ID
    private Long id;

    @ManyToOne // Order와 Member는 다대일 관계
    @JoinColumn(name = "member_id") // 매핑을 "member_id"로 할 것 (foreign key는 "MEMBER_ID")
    // Order와 Member 사이에서 Order 테이블에 있는 member가 연관관계 주인 -> 그냥 두면 됨
    // 여기에 값을 세팅하면 member_id인 foreign key 값 다른 멤버로 바뀌게 됨
    private Member member;

    @OneToMany(mappedBy = "order") // Order와 OrderItem은 일대다 관계
    // OrderItem에 order로 매핑이 됨
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne // Order과 Delivery 간의 관계는 일대일 관계
    // 일대일 관계의 문제점은 FK를 Order에 두어도 되고 Delivery에 두어도
    // 단 어디에 두느냐에 따라 장단점이 존재
    // 주로 access를 많이 하는 곳에 FK를 두는 방법이 좋다.
    // 이번 프로젝트에서 Delivery를 직접 조회하는 경우 보다 Order를 보면서 Delivery에 접근하는 경우가 많아서
    // 따라서 FK를 ORDERS에 DELIVERY_ID를 FK로 두었다.
    // 그러면 연관관계의 주인을 FK에 가까이 있는 Order에 있는 delivery를 선택하면 된다.
    @JoinColumn(name = "delivery_id") // 매핑을 "delivery_id"로 할 것 (foreign key는 "DELIVERY_ID")
    private Delivery delivery;

    private LocalDateTime orderDate; // 주문시간

    @Enumerated(EnumType.STRING)
    // EnumType이 ORDINAL이면 column이 숫자로 들어감
    // 그런데 ORDINAL로 하면 중간에 다른 상태가 생기면 번호가 바뀌면서 문제가 생김
    // 따라서 EnumType을 꼭 STRING으로 써서 중간에 다른것이 들어와도 순서에 의해서 문제가 생기지 않음
    private OrderStatus status; // 주문상태 [ORDER, CANCEL]
}
