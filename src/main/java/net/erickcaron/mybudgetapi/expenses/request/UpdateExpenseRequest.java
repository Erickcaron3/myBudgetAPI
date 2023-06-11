package net.erickcaron.mybudgetapi.expenses.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import net.erickcaron.mybudgetapi.expenses.entity.ExpenseEntity;

@Getter
@Setter
@Builder
public class UpdateExpenseRequest {
    private ExpenseEntity expense;

    public UpdateExpenseRequest() {
    }

    public UpdateExpenseRequest(ExpenseEntity expense) {
        this.expense = expense;
    }
}
