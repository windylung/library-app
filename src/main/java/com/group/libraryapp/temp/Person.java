package com.group.libraryapp.temp;

import javax.persistence.*;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;
    private String name;
    @OneToOne
    private Address address;

    public void setAddress(Address address) {
        this.address = address ;
    }
}
//연관관계 주인이 가르키는 객체가 세팅되어야 > DB에서 두 테이블이 연결

