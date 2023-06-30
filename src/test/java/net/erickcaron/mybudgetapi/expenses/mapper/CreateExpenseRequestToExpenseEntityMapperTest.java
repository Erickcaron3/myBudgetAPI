package net.erickcaron.mybudgetapi.expenses.mapper;

import net.erickcaron.mybudgetapi.expenses.entity.ExpenseEntity;
import net.erickcaron.mybudgetapi.expenses.request.CreateExpenseRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CreateExpenseRequestToExpenseEntityMapperTest {

    CreateExpenseRequestToExpenseEntityMapper expenseEntityMapper;

    @BeforeEach
    void setUp() {
        expenseEntityMapper = new CreateExpenseRequestToExpenseEntityMapper();
    }

    @Test
    public void shouldMapCreateExpenseResponseMapperCorrectly() {
        //given
        CreateExpenseRequest request = CreateExpenseRequest.builder()
                .amount(BigDecimal.valueOf(150.00))
                .currency("PLN")
                .shop("Carrefour")
                .comment("Shopping in carrefour")
                .coverageFrom(LocalDate.now())
                .coverageTo(LocalDate.now())
                .documentNumber("123456")
                .build();

        //when
        ExpenseEntity response = expenseEntityMapper.convert(request);

        //then
        assertEquals(request.getAmount(), response.getAmount());
        assertEquals(request.getCurrency(), response.getCurrency());
        assertEquals(request.getShop(), response.getShop());
        assertEquals(request.getComment(), response.getComment());
        assertEquals(request.getCoverageFrom(), response.getCoverageFrom());
        assertEquals(request.getCoverageTo(), response.getCoverageTo());
        assertEquals(request.getDocumentNumber(), response.getDocumentNumber());
        assertFalse(response.isDeleted());

    }

    @Test
    public void shouldMapCreateExpenseResponseMapperCorrectlyWithNullComment() {
        CreateExpenseRequest request = CreateExpenseRequest.builder()
                .amount(BigDecimal.valueOf(150.00))
                .currency("PLN")
                .shop("Carrefour")
                .comment(null)
                .coverageFrom(LocalDate.now())
                .coverageTo(LocalDate.now())
                .documentNumber("123456")
                .build();

        ExpenseEntity response = expenseEntityMapper.convert(request);

        assertEquals(request.getCurrency(), response.getCurrency());
        assertEquals(request.getShop(), response.getShop());
        assertEquals(response.getComment(), "");
        assertFalse(response.isDeleted());

    }


}