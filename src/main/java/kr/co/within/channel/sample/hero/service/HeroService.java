package kr.co.within.channel.sample.hero.service;

import kr.co.within.channel.sample.hero.domain.Hero;
import kr.co.within.channel.sample.hero.dto.HeroSearchDto;
import kr.co.within.channel.sample.hero.repository.HeroRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@Service
public class HeroService {

    private final HeroRepository heroRepository;

    public HeroService(HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }

    public Page<Hero> getAll(HeroSearchDto search, Pageable pageable) {
        Specification<Hero> spec = (Specification<Hero>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            // name
            if(!StringUtils.isEmpty(search.getName())) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("name"), "%" + search.getName() + "%")));
            }

            // nickname
            if(!StringUtils.isEmpty(search.getNickname())) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("nickname"), "%" + search.getNickname() + "%")));
            }

            // role
            if(!CollectionUtils.isEmpty(search.getRoles())) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.in(root.get("role" )).value(search.getRoles())));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
        return heroRepository.findAll(spec, pageable);
    }

    public Optional<Hero> getById(Long id) {
        return heroRepository.findById(id);
    }

    @Transactional
    public Hero save(Hero hero) {
        return heroRepository.save(hero);
    }

}
