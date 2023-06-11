package net.erickcaron.mybudgetapi.expenses.mapper;

import net.erickcaron.mybudgetapi.expenses.entity.ExpenseEntity;
import net.erickcaron.mybudgetapi.expenses.response.FindExpenseResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class FindExpenseResponseMapperTest {

    FindExpenseResponseMapper findExpenseResponseMapper;

    @BeforeEach
    void setUp() {
        findExpenseResponseMapper = new FindExpenseResponseMapper();
    }

    @Test
    void shouldMapFindExpenseResponseCorrectly() {
        ExpenseEntity expense = ExpenseEntity.builder()
                .id(1L)
                .amount(BigDecimal.valueOf(150.00))
                .currency("PLN")
                .payer("Erick")
                .shop("Carrefour")
                .comment("Shopping in carrefour")
                .creationDate(LocalDateTime.now())
                .build();

        FindExpenseResponse response = findExpenseResponseMapper.convert(expense);

        assertEquals(expense.getId(), response.getId());
        assertEquals(expense.getAmount(), response.getAmount());
        assertEquals(expense.getCurrency(), response.getCurrency());
        assertEquals(expense.getShop(), response.getShop());
        assertEquals(expense.getComment(), response.getComment());
        assertEquals(expense.getPayer(), response.getPayer());
        assertEquals(expense.getCreationDate(), response.getCreationDate());
    }
}