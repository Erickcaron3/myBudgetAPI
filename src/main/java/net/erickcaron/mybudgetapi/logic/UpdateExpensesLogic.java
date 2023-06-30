package net.erickcaron.mybudgetapi.logic;

import lombok.SneakyThrows;
import net.erickcaron.mybudgetapi.exception.ExpenseNotFoundException;
import net.erickcaron.mybudgetapi.expenses.entity.ExpenseEntity;
import net.erickcaron.mybudgetapi.expenses.entity.ExpenseHistoryEntity;
import net.erickcaron.mybudgetapi.expenses.mapper.ExpenseHistoryRepositoryMapper;
import net.erickcaron.mybudgetapi.expenses.repository.ExpenseHistoryRepository;
import net.erickcaron.mybudgetapi.expenses.repository.ExpenseRepository;
import net.erickcaron.mybudgetapi.expenses.request.UpdateExpenseRequest;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UpdateExpensesLogic {

    private final ExpenseRepository expenseRepository;
    private final ExpenseHistoryRepository expenseHistoryRepository;
    private final ExpenseHistoryRepositoryMapper expenseHistoryRepositoryMapper;

    public UpdateExpensesLogic(ExpenseRepository expenseRepository, ExpenseHistoryRepository expenseHistoryRepository, ExpenseHistoryRepositoryMapper expenseHistoryRepositoryMapper) {
        this.expenseRepository = expenseRepository;
        this.expenseHistoryRepository = expenseHistoryRepository;
        this.expenseHistoryRepositoryMapper = expenseHistoryRepositoryMapper;
    }

    @SneakyThrows
    public void updateById(String id, UpdateExpenseRequest updateExpenseRequest) {
        ExpenseEntity actualExpensesVersion = expenseRepository.findById(Long.valueOf(id))
                .orElseThrow(
                        () -> new ExpenseNotFoundException("There is no expense with requested id: " + id));

        saveActualEntityToExpenseHistoryRepository(actualExpensesVersion);
        saveNewestEntityVersion(actualExpensesVersion, updateExpenseRequest);

    }


    private ExpenseEntity buildExpenseEntityToSave(ExpenseEntity actualEntityVersion, UpdateExpenseRequest source) {
        return ExpenseEntity.builder()
                .id(actualEntityVersion.getId())
                .amount(source.getAmount())
                .currency(source.getCurrency())
                .shop(source.getShop())
                .comment(Optional.of(source).map(UpdateExpenseRequest::getComment).orElse(""))
                .documentNumber(source.getDocumentNumber())
                .coverageFrom(source.getCoverageFrom())
                .coverageTo(source.getCoverageTo())
                .build();
    }

    private void saveActualEntityToExpenseHistoryRepository(ExpenseEntity source) {
        ExpenseHistoryEntity expenseHistory = expenseHistoryRepositoryMapper.convert(source);
        expenseHistoryRepository.save(expenseHistory);
    }

    private void saveNewestEntityVersion(ExpenseEntity actualEntityVersion, UpdateExpenseRequest source) {
        ExpenseEntity expenseToSave = buildExpenseEntityToSave(actualEntityVersion, source);
        expenseRepository.save(expenseToSave);
    }
}
