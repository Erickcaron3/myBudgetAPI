package net.erickcaron.mybudgetapi.expenses;

import lombok.RequiredArgsConstructor;
import net.erickcaron.mybudgetapi.entity.ExpenseEntity;
import net.erickcaron.mybudgetapi.mapper.CreateExpenseResponseMapper;
import net.erickcaron.mybudgetapi.mapper.ExpenseEntityMapper;
import net.erickcaron.mybudgetapi.mapper.FindAllExpensesResponseMapper;
import net.erickcaron.mybudgetapi.repositories.ExpenseRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ExpenseFacade implements ExpenseAPI{

    private final ExpenseRepository expenseRepository;
    private final ExpenseEntityMapper expenseEntityMapper;
    private final CreateExpenseResponseMapper createExpenseResponseMapper;
    private final FindAllExpensesResponseMapper findAllExpensesResponseMapper;


    @Override
    public CreateExpenseResponse create(CreateExpenseRequest createExpenseRequest) {
        ExpenseEntity convertedExpenseEntity = expenseEntityMapper.convert(createExpenseRequest);
        ExpenseEntity entityToCreate = expenseRepository.save(convertedExpenseEntity);
        CreateExpenseResponse response = createExpenseResponseMapper.convert(entityToCreate);
        return response;
    }

    @Override
    public FindAllExpensesResponse findAllExpenses() {
        List<ExpenseEntity> allExpenses = expenseRepository.findAll();
        FindAllExpensesResponse response = findAllExpensesResponseMapper.convert(allExpenses);
        return response;
    }
}
