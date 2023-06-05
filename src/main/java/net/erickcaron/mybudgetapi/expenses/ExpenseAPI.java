package net.erickcaron.mybudgetapi.expenses;

import net.erickcaron.mybudgetapi.expenses.request.CreateExpenseRequest;
import net.erickcaron.mybudgetapi.expenses.request.UpdateExpenseRequest;
import net.erickcaron.mybudgetapi.expenses.response.CreateExpenseResponse;
import net.erickcaron.mybudgetapi.expenses.response.FindAllExpensesResponse;
import net.erickcaron.mybudgetapi.expenses.response.FindExpenseResponse;
import net.erickcaron.mybudgetapi.expenses.response.UpdateExpenseResponse;

public interface ExpenseAPI {
    CreateExpenseResponse createExpense(CreateExpenseRequest createExpenseRequest);
    FindAllExpensesResponse findAllExpenses();
    FindExpenseResponse findExpenseById(String id);
    UpdateExpenseResponse updateById(String id, UpdateExpenseRequest updateExpenseRequest);
}
