package net.erickcaron.mybudgetapi.expenses;

public interface ExpenseAPI {
    CreateExpenseResponse create(CreateExpenseRequest createExpenseRequest);
    FindAllExpensesResponse findAllExpenses();
}
