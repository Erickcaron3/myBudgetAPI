package net.erickcaron.mybudgetapi.expenses;

import net.erickcaron.mybudgetapi.expenses.request.CreateExpenseRequest;
import net.erickcaron.mybudgetapi.expenses.request.UpdateExpenseRequest;
import net.erickcaron.mybudgetapi.expenses.response.CreateExpenseResponse;
import net.erickcaron.mybudgetapi.expenses.response.FindAllExpensesResponse;
import net.erickcaron.mybudgetapi.expenses.response.FindExpenseResponse;

import java.util.Optional;

public interface ExpenseAPI {
    CreateExpenseResponse createExpense(CreateExpenseRequest createExpenseRequest);
    FindAllExpensesResponse findAllExpenses();
    Optional<FindExpenseResponse> findExpenseById(String id);
    void updateById(UpdateExpenseRequest updateExpenseRequest);
    void saveEntities(Long numberOfExpenses);
    void deleteById(String id);
}
