package com.study.springbootrunning;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class DataLoder {
    private final CoffeeRepository coffeeRepository;

    public DataLoder(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    @PostConstruct
    private void loadData() {
        coffeeRepository.saveAll(List.of(new Coffee("커피1"), new Coffee("커피2"), new Coffee("커피3"), new Coffee("커피4")));
    }
}
