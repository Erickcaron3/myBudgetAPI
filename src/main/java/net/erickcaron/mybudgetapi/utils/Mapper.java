package net.erickcaron.mybudgetapi.utils;

import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Try;

public interface Mapper<S, T> {
    T convert(S source);

    default Try<T> tryToConvert(S source) {
        return Try.of(() -> convert(source));
    }

    default Option<T> optionConversion(S source) {
        return Try.of(() -> convert(source)).toOption();
    }

    default Either<RuntimeException, T> eitherConversion(S source) {
        return Try.of(() -> convert(source)).toEither().mapLeft(RuntimeException::new);
    }
}
