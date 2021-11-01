package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Category {

    @Id @GeneratedValue
    @Column(name = "category_id") // 매핑을 위해 primary key는 "CATEGORY_ID"
    private Long id;

    private String name;

    @ManyToMany // Category도 리스트로 Item을 가지고 Item도 리스트로 Category를 가지므로 다대다 관계
    @JoinTable(name = "category_item", // 중간 테이블 CATEGORY_ITEM으로 매핑
        joinColumns = @JoinColumn(name = "category_id"), // 중간 테이블에서 Category 쪽으로 들어가는 FK는 CATEGORY_ID
            inverseJoinColumns = @JoinColumn(name = "item_id")) // 중간 테이블에서 Item 쪽으로 들어가는 FK는 ITEM_ID
    // 객체는 collection이 존재하여 collection 끼리 매핑하여 다대다관계가 가능하지만
    // 관계형DB경우 collection관계가 양쪽에서 가질 수 없기 때문에
    // 일대다, 다대일 관계 풀어내는 중간 테이블이 존재해야 한다!!
    private List<Item> items = new ArrayList<>();

    @ManyToOne // 해당 클래스 부모이니 Category와 부모 다대일 관계
    @JoinColumn(name = "parent_id") // 매핑을 위한 부모는 primary key가 "PARENT_ID"
    private Category parent; // Category구조는 계층구조 인데 부모에 대한

    @OneToMany(mappedBy = "parent") // 해당 클래스는 자식이니 Category와 자식은 일대다 관계
    // Category에 parent로 매핑이 됨 (자식은 부모와 매핑시킴)
    // 셀프로 양방향 연관관계를 것 것으로 볼 수 있음
    // 이름만 내 것이지 다른 Entity처럼 매핑하는 것으로 연관관계를 지어주면 됨
    private List<Category> child = new ArrayList<>();
}
