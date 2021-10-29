package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B") // 하위클래스에서 선언하는 entity를 저장할 때 슈퍼타입의 구분 column에 저장할 값을 지정
// 어노테이션 선언 않을 경우 기본값으로 클래스 이름이 들어감
@Getter @Setter
public class Book extends Item { // Item 클래스에 상속

    private String author;
    private String isbn;
}
