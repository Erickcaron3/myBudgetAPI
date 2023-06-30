package net.erickcaron.mybudgetapi;

import net.erickcaron.mybudgetapi.expenses.entity.ExpenseEntity;
import net.erickcaron.mybudgetapi.expenses.mapper.FindAllExpensesResponseMapper;
import net.erickcaron.mybudgetapi.expenses.repository.ExpenseRepository;
import net.erickcaron.mybudgetapi.expenses.response.FindAllExpensesResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FindAllExpensesLogic {

    private final FindAllExpensesResponseMapper findAllExpensesResponseMapper;
    private final ExpenseRepository expenseRepository;

    public FindAllExpensesLogic(FindAllExpensesResponseMapper findAllExpensesResponseMapper, ExpenseRepository expenseRepository) {
        this.findAllExpensesResponseMapper = findAllExpensesResponseMapper;
        this.expenseRepository = expenseRepository;
    }

    public FindAllExpensesResponse retrieveExpenses() {
        List<ExpenseEntity> expensesOutput = expenseRepository.findAll()
                .stream()
                .filter( exp -> !exp.isDeleted())
                .collect(Collectors.toList());

        return findAllExpensesResponseMapper.convert(expensesOutput);
    }


}
