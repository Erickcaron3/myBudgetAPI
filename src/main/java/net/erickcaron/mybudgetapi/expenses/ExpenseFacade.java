package net.erickcaron.mybudgetapi.expenses;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.erickcaron.mybudgetapi.entity.ExpenseEntity;
import net.erickcaron.mybudgetapi.expenses.exception.ExpenseNotFoundException;
import net.erickcaron.mybudgetapi.expenses.request.CreateExpenseRequest;
import net.erickcaron.mybudgetapi.expenses.request.UpdateExpenseRequest;
import net.erickcaron.mybudgetapi.expenses.response.CreateExpenseResponse;
import net.erickcaron.mybudgetapi.expenses.response.FindAllExpensesResponse;
import net.erickcaron.mybudgetapi.expenses.response.FindExpenseResponse;
import net.erickcaron.mybudgetapi.expenses.mapper.CreateExpenseResponseMapper;
import net.erickcaron.mybudgetapi.expenses.mapper.ExpenseEntityMapper;
import net.erickcaron.mybudgetapi.expenses.mapper.FindAllExpensesResponseMapper;
import net.erickcaron.mybudgetapi.expenses.mapper.FindExpenseResponseMapper;
import net.erickcaron.mybudgetapi.expenses.response.UpdateExpenseResponse;
import net.erickcaron.mybudgetapi.repository.ExpenseRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExpenseFacade implements ExpenseAPI {

    private final ExpenseRepository expenseRepository;
    private final ExpenseEntityMapper expenseEntityMapper;
    private final CreateExpenseResponseMapper createExpenseResponseMapper;
    private final FindAllExpensesResponseMapper findAllExpensesResponseMapper;
    private final FindExpenseResponseMapper findExpenseResponseMapper;
    private final UpdateExpenseLogic updateExpenseLogic;


    @Override
    public CreateExpenseResponse createExpense(CreateExpenseRequest createExpenseRequest) {
        ExpenseEntity convertedExpenseEntity = expenseEntityMapper.convert(createExpenseRequest);
        ExpenseEntity entityToCreate = expenseRepository.save(convertedExpenseEntity);
        return createExpenseResponseMapper.convert(entityToCreate);
    }

    @Override
    public FindAllExpensesResponse findAllExpenses() {
        List<ExpenseEntity> allExpenses = expenseRepository.findAll();
        return findAllExpensesResponseMapper.convert(allExpenses);
    }

    @Override
    public FindExpenseResponse findExpenseById(String id) {
        return expenseRepository.findById(Long.valueOf(id))
                .map(findExpenseResponseMapper::convert)
                .orElseThrow(() -> {
                    log.error("There is no expense with the id: " + id);
                    throw new ExpenseNotFoundException("There is no expense with the id: " + id);
                });
    }

    @Override
    public UpdateExpenseResponse updateById(String id, UpdateExpenseRequest updateExpenseRequest) {
        return updateExpenseLogic.updateById(id, updateExpenseRequest);
    }


}
