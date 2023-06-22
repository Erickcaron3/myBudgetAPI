package net.erickcaron.mybudgetapi.expenses.mapper;

import net.erickcaron.mybudgetapi.expenses.entity.ExpenseEntity;
import net.erickcaron.mybudgetapi.expenses.request.CreateExpenseRequest;
import net.erickcaron.mybudgetapi.utils.Mapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ExpenseEntityMapper implements Mapper<CreateExpenseRequest, ExpenseEntity> {

    @Override
    public ExpenseEntity convert(CreateExpenseRequest source) {
        return ExpenseEntity.builder()
                .amount(source.getAmount())
                .currency(source.getCurrency())
                .shop(source.getShop())
                .comment(Optional.of(source).map(CreateExpenseRequest::getComment).orElse(""))
                .coverageFrom(source.getCoverageFrom())
                .coverageTo(source.getCoverageTo())
                .isPaid(source.getIsPaid())
                .dueDate(source.getDueDate())
                .documentCreationDate(source.getDocumentCreationDate())
                .creationDate(source.getExpenseCreationDate())
                .build();
    }



}
