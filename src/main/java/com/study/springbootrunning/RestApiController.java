package com.study.springbootrunning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@RestController // @Controller + @ResponseBody
//@RequestMapping("/")
public class RestApiController {
    private List<Coffee> coffeeList = new ArrayList<>();

    public RestApiController(CoffeeRepository coffeeRepository) {
        coffeeList.addAll(List.of(new Coffee("커피1"), new Coffee("커피2"), new Coffee("커피3"), new Coffee("커피4")));
    }

    /**
     * 커피 목록 조회
     * @return
     */
    @RequestMapping(value = "/coffee", method = RequestMethod.GET)
//    @GetMapping("/coffee")
//    public Iterator<Coffee> getCoffee() {
    public List<Coffee> getCoffee() {
        System.out.println("나오니");
        /**
         * @GetMapping("/getCoffeeList") :  get 요청 시 사용하는 어노테이션
         */
//        return (Iterator<Coffee>) coffeeList;
        return coffeeList;
    }

    /**
     * 커피 조회
     * @param id
     * @return
     */
    @RequestMapping(value = "/coffee/{id}", method = RequestMethod.GET)
//    @GetMapping("/{id}")
    public Optional<Coffee> getCoffeeById(@PathVariable String id) {
        for (Coffee item : coffeeList) {
            if ( item.getId().equals(id) ) {
                return Optional.of(item);
            }
        }

        return Optional.empty();
    }

    /**
     * 커피 등록
     * @param coffee
     * @return
     */
//    @PostMapping
    @RequestMapping(value = "/coffee", method = RequestMethod.POST)
    ResponseEntity<Coffee> addCoffee(@RequestBody Coffee coffee) {
        coffeeList.add(coffee);
        System.out.println("등록 완료");
        return new ResponseEntity<>(coffee, HttpStatus.OK);
    }

    /**
     * 커피 변경
     * @param coffee
     * @return
     */
//    @PutMapping("/{id}")
    @RequestMapping(value = "/coffee/{id}", method = RequestMethod.PUT)
    ResponseEntity<Coffee> modifiyCoffee(@PathVariable String id, @RequestBody Coffee coffee) {
        int coffeeIndex = -1;
        
        for (Coffee item : coffeeList) {
            if ( item.getId().equals(id) ) {
                coffeeIndex = coffeeList.indexOf(item);
                coffeeList.set(coffeeIndex, item);
            }
        }
        // PUT 메서드 응답시 상태코드가 필수여서 이렇게 변경함
//        return (coffeeIndex == -1) ? new ResponseEntity<>(addCoffee(coffee), HttpStatus.CREATED) : new ResponseEntity<>(coffee, HttpStatus.OK);

        return new ResponseEntity<>(coffee, HttpStatus.OK);
    }

    /**
     * 커피 삭제
     * @param id
     */
//    @DeleteMapping("/{id}")
    @RequestMapping(value = "/coffee/{id}", method = RequestMethod.DELETE)
    public void removeCoffee(@PathVariable String id) {
        coffeeList.removeIf(c -> c.getId().equals(id));
    }
}
