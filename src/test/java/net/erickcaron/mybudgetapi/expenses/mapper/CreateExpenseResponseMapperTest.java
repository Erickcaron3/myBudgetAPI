package net.erickcaron.mybudgetapi.expenses.mapper;

import net.erickcaron.mybudgetapi.expenses.entity.ExpenseEntity;
import net.erickcaron.mybudgetapi.expenses.response.CreateExpenseResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

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
                .shop("Carrefour")
                .comment("Shopping in carrefour")
                .coverageFrom(LocalDate.of(2023,01,31))
                .coverageTo(LocalDate.of(2023,01,31))
                .dueDate(LocalDate.of(2023,01,15))
                .documentCreationDate(LocalDate.of(2023,01,01))
                .build();
        //when
        CreateExpenseResponse createExpenseResponse = createExpenseResponseMapper.convert(expense);

        //then
        assertEquals(createExpenseResponse.getId(), expense.getId().toString());
    }

}