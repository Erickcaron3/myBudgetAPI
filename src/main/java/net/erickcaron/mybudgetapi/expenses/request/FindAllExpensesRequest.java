package net.erickcaron.mybudgetapi.expenses.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FindAllExpensesRequest {
    private boolean findAll;

    public FindAllExpensesRequest(boolean findAll) {
        this.findAll = findAll;
    }

}
