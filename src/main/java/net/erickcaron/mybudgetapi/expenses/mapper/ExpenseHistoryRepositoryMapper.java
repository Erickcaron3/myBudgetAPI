package net.erickcaron.mybudgetapi.expenses.mapper;

import net.erickcaron.mybudgetapi.expenses.entity.ExpenseEntity;
import net.erickcaron.mybudgetapi.expenses.entity.ExpenseHistoryEntity;
import net.erickcaron.mybudgetapi.utils.Mapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ExpenseHistoryRepositoryMapper implements Mapper<ExpenseEntity, ExpenseHistoryEntity> {

    @Override
    public ExpenseHistoryEntity convert(ExpenseEntity source) {
        return ExpenseHistoryEntity.builder()
                .amount(source.getAmount())
                .currency(source.getCurrency())
                .shop(source.getShop())
                .documentNumber(source.getDocumentNumber())
                .comment(source.getComment())
                .coverageFrom(source.getCoverageFrom())
                .coverageTo(source.getCoverageTo())
                .isDeleted(source.isDeleted())
                .expenseEntityId(source.getId())
                .lastModificationDate(LocalDate.now())
                .build();
    }


}
