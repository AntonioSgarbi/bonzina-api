package com.antoniosgarbi.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("Tests for Scheduling Repository")
class SchedulingRepositoryTest {

    @Autowired
    private SchedulingRepository repository;

    @Test
    @DisplayName("Count schedules")
    void count(){
    //    LocalDate date = LocalDate.of(2021, 10, 10);
        Long quantity = repository.count();
        System.out.println(quantity);
        Assertions.assertThat(quantity).isNotNull();
    }

    @Test
    @DisplayName("Count schedules in a given day")
    void count_in_day(){
        LocalDate date = LocalDate.of(2021, 10, 10);
        Long quantity = repository.countAllByDate(date);
        System.out.println(quantity);
        Assertions.assertThat(quantity).isEqualTo(1);
    }
}
