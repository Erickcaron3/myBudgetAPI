package net.erickcaron.mybudgetapi.expenses.mapper;

import net.erickcaron.mybudgetapi.entity.ExpenseEntity;
import net.erickcaron.mybudgetapi.expenses.request.CreateExpenseRequest;
import net.erickcaron.mybudgetapi.utils.Mapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ExpenseEntityMapper implements Mapper<CreateExpenseRequest, ExpenseEntity> {

    @Override
    public ExpenseEntity convert(CreateExpenseRequest source) {
        ExpenseEntity expenseEntity = new ExpenseEntity();
        expenseEntity.setAmount(source.getAmount());
        expenseEntity.setCurrency(source.getCurrency());
        expenseEntity.setShop(source.getShop());
        expenseEntity.setComment(Optional.of(source).map(CreateExpenseRequest::getComment).orElse(""));
        expenseEntity.setPayer(source.getPayer());
        return expenseEntity;
    }



}
