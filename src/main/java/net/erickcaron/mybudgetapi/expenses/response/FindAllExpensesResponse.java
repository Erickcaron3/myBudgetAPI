package net.erickcaron.mybudgetapi.expenses.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import net.erickcaron.mybudgetapi.expenses.entity.ExpenseEntity;

import java.util.List;

@Getter
@Setter
@Builder
public class FindAllExpensesResponse {
    public FindAllExpensesResponse(boolean isExpensesListEmpty, List<ExpenseEntity> expensesList) {
        this.isExpensesListEmpty = isExpensesListEmpty;
        this.expensesList = expensesList;
    }

    private boolean isExpensesListEmpty;
    private List<ExpenseEntity> expensesList;
}
