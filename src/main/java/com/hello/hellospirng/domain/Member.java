package com.hello.hellospirng.domain;

import javax.persistence.*;

@Entity //이거는 JPA가 관리하는거!
public class Member {
    //PK Mapping , DB가 알아서 설정해주는건 IDENTY라고한다
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
