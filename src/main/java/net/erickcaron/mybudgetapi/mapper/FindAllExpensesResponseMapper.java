package net.erickcaron.mybudgetapi.mapper;

import net.erickcaron.mybudgetapi.entity.ExpenseEntity;
import net.erickcaron.mybudgetapi.expenses.FindAllExpensesResponse;
import net.erickcaron.mybudgetapi.utils.Mapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FindAllExpensesResponseMapper implements Mapper<List<ExpenseEntity>, FindAllExpensesResponse> {
    @Override
    public FindAllExpensesResponse convert(List<ExpenseEntity> source) {
        return FindAllExpensesResponse.builder()
                .expensesList(mapExpensesList(source))
                .isExpensesListEmpty(mapIsExpensesListEmpty(source))
                .build();
    }

    private boolean mapIsExpensesListEmpty(List<ExpenseEntity> source){
        return source.isEmpty();
    }

    private List<ExpenseEntity> mapExpensesList(List<ExpenseEntity> source){
        return new ArrayList<>(source);
    }
}
