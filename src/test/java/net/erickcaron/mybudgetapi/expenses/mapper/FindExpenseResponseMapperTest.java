package net.erickcaron.mybudgetapi.expenses.mapper;

import net.erickcaron.mybudgetapi.expenses.entity.ExpenseEntity;
import net.erickcaron.mybudgetapi.expenses.response.FindExpenseResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
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
                .shop("Carrefour")
                .comment("Shopping in carrefour")
                .creationDate(LocalDate.now())
                .coverageFrom(LocalDate.of(2023,01,31))
                .coverageTo(LocalDate.of(2023,01,31))
                .dueDate(LocalDate.of(2023,01,15))
                .documentCreationDate(LocalDate.of(2023,01,01))
                .build();

        FindExpenseResponse response = findExpenseResponseMapper.convert(expense);

        assertEquals(expense.getId(), response.getId());
        assertEquals(expense.getAmount(), response.getAmount());
        assertEquals(expense.getCurrency(), response.getCurrency());
        assertEquals(expense.getShop(), response.getShop());
        assertEquals(expense.getComment(), response.getComment());
        assertEquals(expense.getCreationDate(), response.getCreationDate());
        assertEquals(expense.getCoverageFrom(), response.getCoverageFrom());
        assertEquals(expense.getCoverageTo(), response.getCoverageTo());
        assertEquals(expense.getDocumentCreationDate(), response.getDocumentCreationDate());
        assertEquals(expense.getDueDate(), response.getCreationDate());





    }
}