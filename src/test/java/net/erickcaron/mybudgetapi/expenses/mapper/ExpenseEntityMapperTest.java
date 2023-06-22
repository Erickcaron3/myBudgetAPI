package net.erickcaron.mybudgetapi.expenses.mapper;

import net.erickcaron.mybudgetapi.expenses.entity.ExpenseEntity;
import net.erickcaron.mybudgetapi.expenses.request.CreateExpenseRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ExpenseEntityMapperTest {

    ExpenseEntityMapper expenseEntityMapper;

    @BeforeEach
    void setUp() {
        expenseEntityMapper = new ExpenseEntityMapper();
    }

    @Test
    public void shouldMapCreateExpenseResponseMapperCorrectly() {
        //given
        CreateExpenseRequest request = CreateExpenseRequest.builder()
                .amount(BigDecimal.valueOf(150.00))
                .currency("PLN")
                .shop("Carrefour")
                .comment("Shopping in carrefour")
                .expenseCreationDate(LocalDate.now())
                .coverageFrom(LocalDate.of(2023, 01, 31))
                .coverageTo(LocalDate.of(2023, 01, 31))
                .dueDate(LocalDate.of(2023, 01, 15))
                .documentCreationDate(LocalDate.of(2023, 01, 01))
                .build();

        //when
        ExpenseEntity entity = expenseEntityMapper.convert(request);

        //then
        assertEquals(request.getAmount(), entity.getAmount());
        assertEquals(request.getCurrency(), entity.getCurrency());
        assertEquals(request.getShop(), entity.getShop());
        assertEquals(request.getComment(), entity.getComment());
        assertEquals(request.getExpenseCreationDate(), entity.getCreationDate());
        assertEquals(request.getCoverageFrom(), entity.getCoverageFrom());
        assertEquals(request.getCoverageTo(), entity.getCoverageTo());
        assertEquals(request.getDueDate(), entity.getDueDate());
        assertEquals(request.getDocumentCreationDate(), entity.getDocumentCreationDate());



    }

    @Test
    public void shouldMapCreateExpenseResponseMapperCorrectlyWithNullComment() {
        //given
        CreateExpenseRequest request = CreateExpenseRequest.builder()
                .amount(BigDecimal.valueOf(150.00))
                .currency("PLN")
                .shop("Carrefour")
                .comment(null)
                .build();

        //when
        ExpenseEntity entity = expenseEntityMapper.convert(request);

        //then
        assertEquals(request.getCurrency(), entity.getCurrency());
        assertEquals(request.getShop(), entity.getShop());
        assertEquals(entity.getComment(), "");
    }


}