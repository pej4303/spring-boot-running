package com.study.springbootrunning;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller // @Controller + @ResponseBody
@RequestMapping("/")
public class RestApiController2 {

    private final CoffeeRepository coffeeRepository;    // DAO 추가

    public RestApiController2(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    /**
     * 커피 목록 조회
     * @return
     */
    @RequestMapping(value = "/coffee", method = RequestMethod.GET)
    public String getCoffee(Model model) {
        List<Coffee> coffeeList = (List<Coffee>) coffeeRepository.findAll();
        model.addAttribute("coffeeList", coffeeList);
        return "coffeeList";
    }

    /**
     * 커피 조회
     * @param id
     * @return
     */
    @RequestMapping(value = "/coffee/{id}", method = RequestMethod.GET)
    public Optional<Coffee> getCoffeeById(@PathVariable String id) {
        return coffeeRepository.findById(id);
    }

    /**
     * 커피 등록
     * @param coffee
     * @return
     */
    @RequestMapping(value = "/coffee", method = RequestMethod.POST)
    public void addCoffee(@RequestBody Coffee coffee) {
        coffeeRepository.save(coffee);
    }

    /**
     * 커피 변경
     * @param coffee
     * @return
     */
    @RequestMapping(value = "/coffee/{id}", method = RequestMethod.PUT)
    ResponseEntity<Coffee> modifiyCoffee(@PathVariable String id, @RequestBody Coffee coffee) {
      if (!coffeeRepository.existsById(id)) {
          return new ResponseEntity<>(coffeeRepository.save(coffee), HttpStatus.CREATED);
      } else {
          return new ResponseEntity<>(coffeeRepository.save(coffee), HttpStatus.OK);
      }
    }

    /**
     * 커피 삭제
     * @param id
     */
    @RequestMapping(value = "/coffee/{id}", method = RequestMethod.DELETE)
    public void removeCoffee(@PathVariable String id) {
        coffeeRepository.deleteById(id);
    }
}
