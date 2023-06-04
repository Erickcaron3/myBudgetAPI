package net.erickcaron.mybudgetapi.mapper;

import net.erickcaron.mybudgetapi.entity.ExpenseEntity;
import net.erickcaron.mybudgetapi.expenses.CreateExpenseResponse;
import net.erickcaron.mybudgetapi.utils.Mapper;
import org.springframework.stereotype.Component;

@Component
public class CreateExpenseResponseMapper implements Mapper<ExpenseEntity, CreateExpenseResponse> {

    @Override
    public CreateExpenseResponse convert(ExpenseEntity source) {
        return CreateExpenseResponse.builder()
                .id(source.getId().toString())
                .build();
    }
}
