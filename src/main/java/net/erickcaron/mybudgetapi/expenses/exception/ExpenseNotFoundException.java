package net.erickcaron.mybudgetapi.expenses.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "The Expense you were looking for does not exist")
public class ExpenseNotFoundException extends RuntimeException{

    public ExpenseNotFoundException(String message){
        super(message);
    }
}
