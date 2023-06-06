package net.erickcaron.mybudgetapi.expenses.mapper;

import net.erickcaron.mybudgetapi.entity.ExpenseEntity;
import net.erickcaron.mybudgetapi.expenses.request.CreateExpenseRequest;
import net.erickcaron.mybudgetapi.utils.Mapper;
import org.springframework.stereotype.Component;

@Component
public class ExpenseEntityMapper implements Mapper<CreateExpenseRequest, ExpenseEntity> {

    @Override
    public ExpenseEntity convert(CreateExpenseRequest source) {
        return ExpenseEntity.builder()
                .amount(source.getAmount())
                .currency(source.getCurrency())
                .shop(source.getShop())
                .comment(source.getComment())
                .payer(source.getPayer())
                .build();
    }



}
