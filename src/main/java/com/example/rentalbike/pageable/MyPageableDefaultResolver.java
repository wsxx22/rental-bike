package com.example.rentalbike.pageable;

import com.example.rentalbike.annotation.MyPageable;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Optional;

public class MyPageableDefaultResolver extends PageableHandlerMethodArgumentResolver {


    @Override
    public Pageable resolveArgument(MethodParameter methodParameter, ModelAndViewContainer mavContainer,
                                    NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        Pageable pageable = super.resolveArgument(methodParameter, mavContainer, webRequest, binderFactory);

        Optional<MyPageable> myPageable = Optional.ofNullable(methodParameter.getParameterAnnotation(MyPageable.class));


        if (myPageable.isPresent()) {

            int size = pageable.getPageSize() == 20 ? myPageable.get().size() : pageable.getPageSize();

            if (size > myPageable.get().maxSize()) {
                size = myPageable.get().maxSize();
            } else if (size < myPageable.get().minSize()){
                size = myPageable.get().minSize();
            }

            pageable = PageRequest.of(pageable.getPageNumber(), size, pageable.getSort());
        }

        return pageable;
    }
}
