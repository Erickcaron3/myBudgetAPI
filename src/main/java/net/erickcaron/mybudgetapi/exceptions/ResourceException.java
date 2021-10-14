package net.erickcaron.mybudgetapi.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public class ResourceException extends RuntimeException {

    private final ResourceError resourceError;
}
