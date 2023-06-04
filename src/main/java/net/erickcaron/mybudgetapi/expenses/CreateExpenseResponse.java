package net.erickcaron.mybudgetapi.expenses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder

public class CreateExpenseResponse {
    private String id;

    public CreateExpenseResponse(String id) {
        this.id = id;
    }
}
