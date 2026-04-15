package com.novibe.common.util;

import org.springframework.util.function.ThrowingFunction;

import java.util.function.Function;

public interface FunctionWrapper {

    static <T, R> Function<T, R> wrap(ThrowingFunction<T, R> throwingFunction) {
        return t -> {
            try {
                return throwingFunction.apply(t);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
}
