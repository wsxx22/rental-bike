package com.example.rentalbike.config;

import com.example.rentalbike.annotation.MyPageable;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Optional;

public class MyPageableDefaultConfig extends PageableHandlerMethodArgumentResolver {


    @Override
    public void setMaxPageSize(int maxPageSize) {
        super.setMaxPageSize(50);
    }

    @Override
    public Pageable resolveArgument(MethodParameter methodParameter, ModelAndViewContainer mavContainer,
                                    NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        Pageable pageable = super.resolveArgument(methodParameter, mavContainer, webRequest, binderFactory);

        Optional<MyPageable> myPageable = Optional.ofNullable(methodParameter.getParameterAnnotation(MyPageable.class));


        if (myPageable.isPresent()) {
            int size;
            size = myPageable.get().size() > myPageable.get().maxSize() ? myPageable.get().maxSize() : pageable.getPageSize();

            pageable = PageRequest.of(pageable.getPageNumber(), size, pageable.getSort());
        }




        return pageable;
    }
}
