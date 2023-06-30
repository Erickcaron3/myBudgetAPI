package net.erickcaron.mybudgetapi.expenses;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.erickcaron.mybudgetapi.logic.CreateExpenseLogic;
import net.erickcaron.mybudgetapi.logic.FindAllExpensesLogic;
import net.erickcaron.mybudgetapi.logic.UpdateExpensesLogic;
import net.erickcaron.mybudgetapi.exception.ExpenseNotFoundException;
import net.erickcaron.mybudgetapi.expenses.entity.ExpenseEntity;
import net.erickcaron.mybudgetapi.expenses.request.CreateExpenseRequest;
import net.erickcaron.mybudgetapi.expenses.request.UpdateExpenseRequest;
import net.erickcaron.mybudgetapi.expenses.response.CreateExpenseResponse;
import net.erickcaron.mybudgetapi.expenses.response.FindAllExpensesResponse;
import net.erickcaron.mybudgetapi.expenses.response.FindExpenseResponse;
import net.erickcaron.mybudgetapi.expenses.mapper.FindExpenseResponseMapper;
import net.erickcaron.mybudgetapi.expenses.repository.ExpenseRepository;
import net.erickcaron.mybudgetapi.utils.ExpenseEntityGenerator;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExpenseFacade implements ExpenseAPI {

    private final ExpenseRepository expenseRepository;
    private final CreateExpenseLogic createExpenseLogic;
    private final FindExpenseResponseMapper findExpenseResponseMapper;
    private final ExpenseEntityGenerator expenseEntityGenerator;
    private final FindAllExpensesLogic findAllExpensesLogic;
    private final UpdateExpensesLogic updateExpensesLogic;



    @Override
    public CreateExpenseResponse createExpense(CreateExpenseRequest createExpenseRequest) {
        return createExpenseLogic.create(createExpenseRequest);
    }


    @Override
    public FindAllExpensesResponse findExpenses() {
        return findAllExpensesLogic.retrieveExpenses();
    }

    @SneakyThrows
    @Override
    public FindExpenseResponse findExpenseById(String id) {
        return expenseRepository.findById(Long.valueOf(id))
                .map(findExpenseResponseMapper::convert)
                .orElseThrow(
                        () -> new ExpenseNotFoundException("There is no expense with requested id: " + id)
                );
    }

    @SneakyThrows
    @Override
    public void updateById(String id, UpdateExpenseRequest updateExpenseRequest) {
        updateExpensesLogic.updateById(id,updateExpenseRequest);

    }

    @SneakyThrows
    @Override
    public void deleteById(String id) {
        ExpenseEntity expenseToSave = expenseRepository.findById(Long.valueOf(id))
                .map(this::markExpenseAsDeleted)
                .orElseThrow(
                        () -> new ExpenseNotFoundException("There is no expense with id: " + id + " to be deleted")
                );

        expenseRepository.save(expenseToSave);
        log.info("Expense with id: " + id + " successfully marked as deleted");

    }


    @Override
    public void saveEntities(Long numberOfExpenses) {
        expenseRepository.saveAll(expenseEntityGenerator.generateListExpenses(numberOfExpenses));
    }

    private ExpenseEntity markExpenseAsDeleted(ExpenseEntity source) {
        source.setDeleted(true);
        return source;
    }


}
