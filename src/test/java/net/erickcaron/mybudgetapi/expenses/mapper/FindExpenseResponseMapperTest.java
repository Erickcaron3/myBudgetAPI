package net.erickcaron.mybudgetapi.expenses.mapper;

import net.erickcaron.mybudgetapi.expenses.entity.ExpenseEntity;
import net.erickcaron.mybudgetapi.expenses.response.FindExpenseResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.GregorianCalendar;

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
                .documentNumber("123456")
                .coverageFrom(new GregorianCalendar(2023,01,01).getTime())
                .coverageFrom(new GregorianCalendar(2023,01,31).getTime())
                .build();

        FindExpenseResponse response = findExpenseResponseMapper.convert(expense);

        assertEquals(expense.getId(), response.getId());
        assertEquals(expense.getAmount(), response.getAmount());
        assertEquals(expense.getCurrency(), response.getCurrency());
        assertEquals(expense.getShop(), response.getShop());
        assertEquals(expense.getComment(), response.getComment());
        assertEquals(expense.getDocumentNumber(), response.getDocumentNumber());
        assertEquals(expense.getCoverageFrom(), response.getCoverageFrom());
        assertEquals(expense.getCoverageTo(), response.getCoverageTo());
    }
}