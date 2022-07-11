package com.endava.tmd.bookclubapp.utilities;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class BooleanUtilities {

    @SafeVarargs
    public static <T> boolean anyEmptyParameters(final Optional<T>... args){
        return Arrays.stream(args).anyMatch(Optional::isEmpty);
    }
    @SafeVarargs
    public static<T> boolean anyNullParameters(final T... args){
        return Arrays.stream(args).anyMatch(Objects::isNull);
    }

    public static boolean anyEmptyString(final String... args){
        return Arrays.stream(args).anyMatch(String::isEmpty);
    }

    public static <T> boolean emptyList(final List<T> list){
        return list.isEmpty();
    }
}
