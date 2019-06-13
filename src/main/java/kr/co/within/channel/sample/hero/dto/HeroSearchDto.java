package kr.co.within.channel.sample.hero.dto;

import kr.co.within.channel.sample.hero.domain.Hero;
import lombok.Data;

import java.util.List;

@Data
public class HeroSearchDto {

    private String name;

    private String nickname;

    private List<Hero.Role> roles;

}
