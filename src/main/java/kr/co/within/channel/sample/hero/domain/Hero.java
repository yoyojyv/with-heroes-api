package kr.co.within.channel.sample.hero.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Hero {

    @Id @GeneratedValue
    private Long id;

    private String name;

    private String nickname;

    private Role role;

    private Integer age;

    public enum Role {
        DEVELOPER,
        MANAGER,
        SALES
    }

    public Hero(String name, String nickname, Role role, Integer age) {
        this.name = name;
        this.nickname = nickname;
        this.role = role;
        this.age = age;
    }

    @Transient
    public Hero getInstanceForAppInit(Integer i) {
        return new Hero(name + i, nickname + 1, role, age);
    }

}
