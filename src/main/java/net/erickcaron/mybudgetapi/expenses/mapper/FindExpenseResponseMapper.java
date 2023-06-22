package net.erickcaron.mybudgetapi.expenses.mapper;

import net.erickcaron.mybudgetapi.expenses.entity.ExpenseEntity;
import net.erickcaron.mybudgetapi.expenses.response.FindExpenseResponse;
import net.erickcaron.mybudgetapi.utils.Mapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FindExpenseResponseMapper implements Mapper<ExpenseEntity, FindExpenseResponse> {

    @Override
    public FindExpenseResponse convert(ExpenseEntity source) {
        return FindExpenseResponse.builder()
                .id(source.getId())
                .amount(source.getAmount())
                .currency(source.getCurrency())
                .shop(source.getShop())
                .comment(Optional.of(source).map(ExpenseEntity::getComment).orElse(""))
                .creationDate(source.getCreationDate())
                .coverageFrom(source.getCoverageFrom())
                .coverageTo(source.getCoverageTo())
                .isPaid(source.getIsPaid())
                .dueDate(source.getDueDate())
                .documentCreationDate(source.getDocumentCreationDate())
                .build();
    }
}
