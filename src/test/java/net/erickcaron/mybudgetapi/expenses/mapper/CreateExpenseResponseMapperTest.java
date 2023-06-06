package net.erickcaron.mybudgetapi.expenses.mapper;

import net.erickcaron.mybudgetapi.entity.ExpenseEntity;
import net.erickcaron.mybudgetapi.expenses.response.CreateExpenseResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateExpenseResponseMapperTest {

     CreateExpenseResponseMapper createExpenseResponseMapper;

    @BeforeEach
    void setUp() {
        createExpenseResponseMapper = new CreateExpenseResponseMapper();
    }

    @Test
    public void shouldMapCreateExpenseResponseCorrectly() {
        //given
        ExpenseEntity expense = ExpenseEntity.builder()
                .id(1L)
                .amount(BigDecimal.valueOf(150.00))
                .currency("PLN")
                .payer("Erick")
                .shop("Carrefour")
                .comment("Shopping in carrefour")
                .build();
        //when
        CreateExpenseResponse createExpenseResponse = createExpenseResponseMapper.convert(expense);

        //then
        assertEquals(createExpenseResponse.getId(), expense.getId().toString());
    }

}