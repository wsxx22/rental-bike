package com.example.rentalbike.annotation;

import org.springframework.data.domain.Sort;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
public @interface MyPageable {

    int value() default 10;

    int size() default 10;

    int maxSize() default 15;

    int page() default 0;

    String[] sort() default {};

    Sort.Direction direction() default Sort.Direction.ASC;

}
