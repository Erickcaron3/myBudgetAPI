package net.erickcaron.mybudgetapi.expenses.mapper;

import net.erickcaron.mybudgetapi.expenses.entity.ExpenseEntity;
import net.erickcaron.mybudgetapi.expenses.entity.ExpenseHistoryEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class ExpenseHistoryRepositoryMapperTest {

    private ExpenseHistoryRepositoryMapper expenseHistoryRepositoryMapper;

    @BeforeEach
    void setUp() {
        expenseHistoryRepositoryMapper = new ExpenseHistoryRepositoryMapper();
    }

    @Test
    void shouldMapCorrectly() {
        LocalDate from = LocalDate.now();
        LocalDate to = LocalDate.now();


        ExpenseEntity expense = ExpenseEntity.builder()
                .id(1L)
                .amount(BigDecimal.valueOf(150.00))
                .currency("PLN")
                .shop("Carrefour")
                .comment("Shopping in carrefour")
                .documentNumber("123456")
                .coverageFrom(from)
                .coverageTo(to)
                .build();

        ExpenseHistoryEntity response = expenseHistoryRepositoryMapper.convert(expense);

        assertEquals(response.getId(), null);
        assertEquals(expense.getAmount(), response.getAmount());
        assertEquals(expense.getCurrency(), response.getCurrency());
        assertEquals(expense.getShop(), response.getShop());
        assertEquals(expense.getComment(), response.getComment());
        assertEquals(expense.getDocumentNumber(), response.getDocumentNumber());
        assertEquals(expense.getCoverageFrom(), response.getCoverageFrom());
        assertEquals(expense.getCoverageTo(), response.getCoverageTo());
        assertEquals(expense.getId(), response.getExpenseEntityId());
        assertEquals(response.getLastModificationDate(), LocalDate.now());


    }
}