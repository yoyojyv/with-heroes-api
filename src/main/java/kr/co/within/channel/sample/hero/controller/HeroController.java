package kr.co.within.channel.sample.hero.controller;

import kr.co.within.channel.sample.hero.domain.Hero;
import kr.co.within.channel.sample.hero.dto.HeroSearchDto;
import kr.co.within.channel.sample.hero.service.HeroService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
public class HeroController {

    private final HeroService heroService;

    public HeroController(HeroService heroService) {
        this.heroService = heroService;
    }

    @GetMapping("/heroes")
    public Page<Hero> getAll(HeroSearchDto search, Pageable pageable) {
        return heroService.getAll(search, pageable);
    }

    @GetMapping("/heroes/{id}")
    public Hero getHero(@PathVariable Long id) {
        return heroService.getById(id).orElseThrow(() -> new RuntimeException("Not found hero" ));
    }

    @PostMapping("/heroes")
    public Hero save(@RequestBody Hero hero) {
        return heroService.save(hero);
    }

}
