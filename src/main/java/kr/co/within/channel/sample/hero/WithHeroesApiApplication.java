package kr.co.within.channel.sample.hero;

import kr.co.within.channel.sample.hero.domain.Hero;
import kr.co.within.channel.sample.hero.repository.HeroRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootApplication
public class WithHeroesApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(WithHeroesApiApplication.class, args);
    }

    @Component
    class HeroesAppInitializer implements ApplicationRunner {

        private final HeroRepository heroRepository;

        HeroesAppInitializer(HeroRepository heroRepository) {
            this.heroRepository = heroRepository;
        }

        @Override
        public void run(ApplicationArguments args) throws Exception {

            // remove all
            heroRepository.deleteAll();

            List<Hero> heroes = Arrays.asList(
                new Hero("김재용", "Jerry", Hero.Role.DEVELOPER, 38),
                new Hero("피제이", "Peejay", Hero.Role.DEVELOPER, 34),
                new Hero("댄", "Dan", Hero.Role.DEVELOPER, 33),
                new Hero("토레스", "Torres", Hero.Role.MANAGER, 34),
                new Hero("최진섭", "Colin", Hero.Role.SALES, 37),
                new Hero("도미닉", "Dominic", Hero.Role.DEVELOPER, 37)
            );

            // add all
            heroes
                .parallelStream() // 일부러 섞이게...
                .forEach((hero) ->
                    IntStream.range(1, 100)
                        .forEach((i) -> heroRepository.save(hero.getInstanceForAppInit(i)))
                );
        }

    }


}

