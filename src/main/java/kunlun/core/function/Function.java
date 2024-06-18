/*
 * Copyright (c) 2018. the original author or authors.
 * Kunlun is licensed under the "LICENSE" file in the project's root directory.
 */

package kunlun.core.function;

/**
 * Represents a function that accepts one argument and produces a result.
 * @param <Param>
 * @param <Result>
 * @author Kahle
 */
public interface Function<Param, Result> {

    /**
     * Applies this function to the given argument.
     *
     * @param param the function argument
     * @return the function result
     */
    Result apply(Param param);

}
