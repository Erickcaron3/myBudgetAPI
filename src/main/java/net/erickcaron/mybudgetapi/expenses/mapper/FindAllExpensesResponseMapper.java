package net.erickcaron.mybudgetapi.expenses.mapper;

import net.erickcaron.mybudgetapi.expenses.entity.ExpenseEntity;
import net.erickcaron.mybudgetapi.expenses.response.FindAllExpensesResponse;
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
                .build();
    }

    private List<ExpenseEntity> mapExpensesList(List<ExpenseEntity> source){
        return new ArrayList<>(source);
    }
}
