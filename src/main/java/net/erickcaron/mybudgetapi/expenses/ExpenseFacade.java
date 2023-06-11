package net.erickcaron.mybudgetapi.expenses;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.erickcaron.mybudgetapi.expenses.entity.ExpenseEntity;
import net.erickcaron.mybudgetapi.expenses.request.CreateExpenseRequest;
import net.erickcaron.mybudgetapi.expenses.request.UpdateExpenseRequest;
import net.erickcaron.mybudgetapi.expenses.response.CreateExpenseResponse;
import net.erickcaron.mybudgetapi.expenses.response.FindAllExpensesResponse;
import net.erickcaron.mybudgetapi.expenses.response.FindExpenseResponse;
import net.erickcaron.mybudgetapi.expenses.mapper.CreateExpenseResponseMapper;
import net.erickcaron.mybudgetapi.expenses.mapper.ExpenseEntityMapper;
import net.erickcaron.mybudgetapi.expenses.mapper.FindAllExpensesResponseMapper;
import net.erickcaron.mybudgetapi.expenses.mapper.FindExpenseResponseMapper;
import net.erickcaron.mybudgetapi.expenses.repository.ExpenseRepository;
import net.erickcaron.mybudgetapi.utils.ExpenseEntityGenerator;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExpenseFacade implements ExpenseAPI {

    private final ExpenseRepository expenseRepository;
    private final ExpenseEntityMapper expenseEntityMapper;
    private final CreateExpenseResponseMapper createExpenseResponseMapper;
    private final FindAllExpensesResponseMapper findAllExpensesResponseMapper;
    private final FindExpenseResponseMapper findExpenseResponseMapper;
    private final ExpenseEntityGenerator expenseEntityGenerator;


    @Override
    public CreateExpenseResponse createExpense(CreateExpenseRequest createExpenseRequest) {
        ExpenseEntity entityToCreate = expenseRepository.save(buildExpenseEntity(createExpenseRequest));
        return createExpenseResponseMapper.convert(entityToCreate);
    }

    private ExpenseEntity buildExpenseEntity(CreateExpenseRequest createExpenseRequest){
        ExpenseEntity expenseEntity = expenseEntityMapper.convert(createExpenseRequest);
        expenseEntity.setCreationDate(LocalDateTime.now());
        return expenseEntity;

    }

    @Override
    public FindAllExpensesResponse findAllExpenses() {
        List<ExpenseEntity> allExpenses = expenseRepository.findAll();
        return findAllExpensesResponseMapper.convert(allExpenses);
    }

    @Override
    public Optional<FindExpenseResponse> findExpenseById(String id) {
        return expenseRepository.findById(Long.valueOf(id))
                .map(findExpenseResponseMapper::convert);
    }

    @Override
    public void updateById(UpdateExpenseRequest updateExpenseRequest) {
        Long id = updateExpenseRequest.getExpense().getId();

        log.info("Attempting to delete expense with id: " + updateExpenseRequest.getExpense().getId());
        if(!expenseRepository.existsById(id)) {
            log.error("There is not expense with requested id: " + id + " , update was not processed");
        }

        expenseRepository.save(updateExpenseRequest.getExpense());
        log.info("Update of expense with id: " + id + " successfully processed");

    }

    @Override
    public void saveEntities(Long numberOfExpenses) {
        expenseRepository.saveAll(expenseEntityGenerator.generateListExpenses(numberOfExpenses));
    }

    @Override
    public void deleteById(String id) {
        log.info("Attempting to delete Expense with id: " + id);
        if(!expenseRepository.existsById(Long.valueOf(id))) {
            log.error("There is not expense with requested id: " + id + " , deletion was not processed");
        }
        expenseRepository.deleteById(Long.valueOf(id));
        log.info("Deletion of expense with id: " + id + " successfully processed");

    }


}
