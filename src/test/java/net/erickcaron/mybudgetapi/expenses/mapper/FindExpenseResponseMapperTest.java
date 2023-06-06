package net.erickcaron.mybudgetapi.expenses.mapper;

import net.erickcaron.mybudgetapi.entity.ExpenseEntity;
import net.erickcaron.mybudgetapi.expenses.request.CreateExpenseRequest;
import net.erickcaron.mybudgetapi.expenses.response.FindExpenseResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

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
                .build();

        FindExpenseResponse findExpenseResponse = findExpenseResponseMapper.convert(expense);

        assertEquals(expense.getId(), findExpenseResponse.getId());
        assertEquals(expense.getAmount(), findExpenseResponse.getAmount());
        assertEquals(expense.getCurrency(), findExpenseResponse.getCurrency());
        assertEquals(expense.getShop(), findExpenseResponse.getShop());
        assertEquals(expense.getComment(), findExpenseResponse.getComment());
        assertEquals(expense.getPayer(), findExpenseResponse.getPayer());
    }
}