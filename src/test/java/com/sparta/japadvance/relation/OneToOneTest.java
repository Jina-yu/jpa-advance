package com.sparta.japadvance.relation;

import com.sparta.japadvance.entity.Food;
import com.sparta.japadvance.repository.FoodRepository;
import com.sparta.japadvance.repository.UserRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
public class OneToOneTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    FoodRepository foodRepository;


    @Test
    @Rollback(value = false) // 테스트에서는 @Transactional 에 의해 자동 rollback 됨으로 false 설정해준다.
    @DisplayName("1대1 단방향 테스트")
    void test1() {

        User user = new User();
        user.setName("Robbie");

        // 외래 키의 주인인 Food Entity user 필드에 user 객체를 추가해 줍니다.
        Food food = new Food();
        food.setName("후라이드 치킨");
        food.setPrice(15000);
        food.setUser(user); // 외래 키(연관 관계) 설정

        userRepository.save(user);
        foodRepository.save(food);
    }

    package com.sparta.jpaadvance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

    @Entity
    @Getter
    @Setter
    @Table(name = "users")
    public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;

        @OneToOne(mappedBy = "user")
        private Food food;

        public void addFood(Food food) {
            this.food = food;
            food.setUser(this);
        }
    }

}
