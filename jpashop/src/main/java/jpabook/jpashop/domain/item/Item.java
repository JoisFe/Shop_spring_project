package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// 상속관계 매핑에서는 상속관계 전략을 부모 클래스에 집어 넣어줘야 한다.
// 여기서는 SINGLE_TABLE 전략을 사용할 것이다.
@DiscriminatorColumn(name = "dtype") // 부모클래스에서 선언하는 하위 클래스를 구분하는 용도의 column
@Getter @Setter
public abstract class Item { // 추상클래스로 만든 이유는 구현체를 이용할 것이기 떄문

    @Id
    @GeneratedValue
    @Column(name = "item_id") // primary key가 ITEM_ID
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items") // Category도 리스트로 Item을 가지고 Item도 리스트로 Category를 가지므로 다대다 관계
    // Category에 items로 매핑이 됨
    private List<Category> categories = new ArrayList<>();
}
