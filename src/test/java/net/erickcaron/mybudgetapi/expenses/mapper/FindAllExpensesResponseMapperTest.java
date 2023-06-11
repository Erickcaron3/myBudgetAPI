package net.erickcaron.mybudgetapi.expenses.mapper;

import net.erickcaron.mybudgetapi.expenses.entity.ExpenseEntity;
import net.erickcaron.mybudgetapi.expenses.response.FindAllExpensesResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FindAllExpensesResponseMapperTest {

    FindAllExpensesResponseMapper findAllExpensesResponseMapper;

    @BeforeEach
    public void setUp(){
        findAllExpensesResponseMapper = new FindAllExpensesResponseMapper();
    }

    @Test
    void shouldMapCorrectlyWithOneExpenseEntity() {
        List<ExpenseEntity> expenses = List.of(
                ExpenseEntity.builder()
                        .id(1L)
                        .amount(BigDecimal.valueOf(150.00))
                        .currency("PLN")
                        .payer("Erick")
                        .shop("Carrefour")
                        .comment("Shopping in carrefour")
                        .build());

        FindAllExpensesResponse response = findAllExpensesResponseMapper.convert(expenses);

        assertEquals(response.getExpensesList(), expenses);
        assertEquals(response.getExpensesList().size(), expenses.size());
        assertFalse(response.isExpensesListEmpty());
    }

    @Test
    void shouldMapCorrectlyWithMoreThanOneExpenseEntity() {
        List<ExpenseEntity> expenses = Arrays.asList(
                ExpenseEntity.builder()
                        .id(1L)
                        .amount(BigDecimal.valueOf(150.00))
                        .currency("PLN")
                        .payer("Erick")
                        .shop("Carrefour")
                        .comment("Shopping in carrefour")
                        .build(),
                ExpenseEntity.builder()
                        .id(1L)
                        .amount(BigDecimal.valueOf(150.00))
                        .currency("PLN")
                        .payer("Erick")
                        .shop("Carrefour")
                        .comment("Shopping in carrefour")
                        .build());

        FindAllExpensesResponse response = findAllExpensesResponseMapper.convert(expenses);

        assertEquals(response.getExpensesList(), expenses);
        assertEquals(response.getExpensesList().size(), expenses.size());
        assertFalse(response.isExpensesListEmpty());
    }

    @Test
    void shouldMapCorrectlyWithoutExpenseEntity() {
        List<ExpenseEntity> expenses = Collections.EMPTY_LIST;

        FindAllExpensesResponse response = findAllExpensesResponseMapper.convert(expenses);

        assertEquals(response.getExpensesList(), expenses);
        assertEquals(response.getExpensesList().size(), expenses.size());
        assertTrue(response.isExpensesListEmpty());
    }
}