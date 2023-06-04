package net.erickcaron.mybudgetapi.expenses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import net.erickcaron.mybudgetapi.entity.ExpenseEntity;

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
