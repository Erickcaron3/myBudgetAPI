package net.erickcaron.mybudgetapi.services;

import net.erickcaron.mybudgetapi.models.Expense;

import java.util.List;
import java.util.Optional;

public interface ExpenseService {
    List<Expense> findAll();

    Long create(Long shopId, Long currencyId, Expense expense);

    Optional<Expense> findById(Long id);

    void delete(Expense expense);

    boolean isExpenseCalculationCorrect(Expense expense);

    void update(Long id, Expense expense);

    Expense instancingExpense(Expense expenseFromDB, Expense newExpense);

    List<Expense> findAllByShopId(Long shopId);

    void fullUpdate(Long expenseId, Long currencyId, Long shopId, Expense expenseToUpdate);
}

