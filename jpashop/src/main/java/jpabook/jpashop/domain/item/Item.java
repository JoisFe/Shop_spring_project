package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
}
