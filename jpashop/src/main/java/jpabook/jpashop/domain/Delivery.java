package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id") // 매핑을 위해 primary key는 "DELIVERY_ID"
    private Long id;

    @OneToOne(mappedBy = "delivery") // Delivery와 Order 간은 일대일 관계
    // Order에 delivery로 매핑이 됨
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    // EnumType이 ORDINAL이면 column이 숫자로 들어감
    // 그런데 ORDINAL로 하면 중간에 다른 상태가 생기면 번호가 바뀌면서 문제가 생김
    // 따라서 EnumType을 꼭 STRING으로 써서 중간에 다른것이 들어와도 순서에 의해서 문제가 생기지 않음
    private DeliveryStatus status; // READY(배송 준비), COMP(배송 완료)
}
