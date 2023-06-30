package net.erickcaron.mybudgetapi.logic;

import net.erickcaron.mybudgetapi.expenses.entity.ExpenseEntity;
import net.erickcaron.mybudgetapi.expenses.mapper.CreateExpenseRequestToExpenseEntityMapper;
import net.erickcaron.mybudgetapi.expenses.mapper.CreateExpenseResponseMapper;
import net.erickcaron.mybudgetapi.expenses.repository.ExpenseRepository;
import net.erickcaron.mybudgetapi.expenses.request.CreateExpenseRequest;
import net.erickcaron.mybudgetapi.expenses.response.CreateExpenseResponse;
import org.springframework.stereotype.Component;

@Component
public class CreateExpenseLogic {

    private final CreateExpenseRequestToExpenseEntityMapper expenseEntityMapper;
    private final CreateExpenseResponseMapper createExpenseResponseMapper;
    private final ExpenseRepository expenseRepository;

    public CreateExpenseLogic(CreateExpenseRequestToExpenseEntityMapper expenseEntityMapper, CreateExpenseResponseMapper createExpenseResponseMapper, ExpenseRepository expenseRepository) {
        this.expenseEntityMapper = expenseEntityMapper;
        this.createExpenseResponseMapper = createExpenseResponseMapper;
        this.expenseRepository = expenseRepository;
    }


    public CreateExpenseResponse create(CreateExpenseRequest createExpenseRequest) {
        ExpenseEntity entityToCreate = expenseRepository.save(buildExpenseEntity(createExpenseRequest));
        return createExpenseResponseMapper.convert(entityToCreate);
    }



    private ExpenseEntity buildExpenseEntity(CreateExpenseRequest createExpenseRequest) {
        return expenseEntityMapper.convert(createExpenseRequest);
    }
}
