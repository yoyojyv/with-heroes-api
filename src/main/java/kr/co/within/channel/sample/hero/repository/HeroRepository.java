package kr.co.within.channel.sample.hero.repository;

import kr.co.within.channel.sample.hero.domain.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HeroRepository extends JpaRepository<Hero, Long>, JpaSpecificationExecutor<Hero> {

}
