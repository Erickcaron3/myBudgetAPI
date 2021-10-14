package net.erickcaron.mybudgetapi.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public class ErrorInfo {

    private String message;
}
