package net.erickcaron.mybudgetapi.exceptions;

import lombok.*;

@Getter
@AllArgsConstructor
public enum ResourceError {

    INCORRECT_INPUT("The input provided is incorrect"), RESOURCE_ALREADY_EXISTING("The resource you are asking is already existing"), RESOURCE_NOT_FOUND("The resource you are asking for is not existing");
    private String message;
}
