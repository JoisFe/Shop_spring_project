package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id") // 매핑을 위해 primary key는 "MEMBER_ID"
    private Long id;

    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member") // Member와 Order의 관계는 일대다 관계
    // Order 테이블에 있는 member 필드에 의해서 매핑 된것이므로 (즉 연관관계 주인이 Order 테이블에 있는 member)
    // 이것을 mappedBy를 통해 적어줌
    // 여기에 값을 넣는다고 해서 foreign key (Member의 member_id) 값이 바뀌지 않음

    private List<Order> orders = new ArrayList<>();
}
