package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    // jpa 생성시 여러 기술들을 사용하는데 (프록시 등)
    // 기본 생성자 없으면 이런 기술들을 사용할 수 없기 때문에 기본 생성자를 만들어 줘야한다
    protected Address() { // 접근지정자를 jpa 스펙상 protected까지 허용 (public으로 많은 호출 못하도록)
    }

    // 값 타입은 기본적으로 immutable 하게 설계되어야 한다.
    // 따라서 Setter를 제공하지 않고
    // 생성할 때 값이 세팅이 되고 그 뒤로 변경이 불가능 해야 한다.
    public Address(String city, String street, String zipcod) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
