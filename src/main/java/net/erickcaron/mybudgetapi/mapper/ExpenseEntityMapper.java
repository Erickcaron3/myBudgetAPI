package net.erickcaron.mybudgetapi.mapper;

import net.erickcaron.mybudgetapi.entity.ExpenseEntity;
import net.erickcaron.mybudgetapi.expenses.CreateExpenseRequest;
import net.erickcaron.mybudgetapi.utils.Mapper;
import org.springframework.stereotype.Component;

@Component
public class ExpenseEntityMapper implements Mapper<CreateExpenseRequest, ExpenseEntity> {

    @Override
    public ExpenseEntity convert(CreateExpenseRequest source) {
        ExpenseEntity expenseEntity = new ExpenseEntity();
        expenseEntity.setAmount(source.getAmount());
        expenseEntity.setCurrency(source.getCurrency());
        expenseEntity.setShop(source.getShop());
        expenseEntity.setComment(source.getComment());
        expenseEntity.setPayer(source.getPayer());
        return expenseEntity;
    }
}
