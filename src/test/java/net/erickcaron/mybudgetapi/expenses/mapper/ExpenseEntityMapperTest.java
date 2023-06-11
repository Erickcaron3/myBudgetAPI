package net.erickcaron.mybudgetapi.expenses.mapper;

import net.erickcaron.mybudgetapi.expenses.entity.ExpenseEntity;
import net.erickcaron.mybudgetapi.expenses.request.CreateExpenseRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ExpenseEntityMapperTest {

     ExpenseEntityMapper expenseEntityMapper;

    @BeforeEach
    void setUp() {
        expenseEntityMapper = new ExpenseEntityMapper();
    }

    @Test
    public void shouldMapCreateExpenseResponseMapperCorrectly(){
        //given
        CreateExpenseRequest request = CreateExpenseRequest.builder()
                .amount(BigDecimal.valueOf(150.00))
                .currency("PLN")
                .shop("Carrefour")
                .comment("Shopping in carrefour")
                .payer("Erick")
                .build();

        //when
        ExpenseEntity entity = expenseEntityMapper.convert(request);

        //then
       assertEquals(request.getCurrency(), entity.getCurrency());
       assertEquals(request.getShop(), entity.getShop());
       assertEquals(request.getComment(), entity.getComment());
       assertEquals(request.getPayer(), entity.getPayer());

    }

    @Test
    public void shouldMapCreateExpenseResponseMapperCorrectlyWithNullComment(){
        //given
        CreateExpenseRequest request = CreateExpenseRequest.builder()
                .amount(BigDecimal.valueOf(150.00))
                .currency("PLN")
                .shop("Carrefour")
                .comment(null)
                .payer("Erick")
                .build();

        //when
        ExpenseEntity entity = expenseEntityMapper.convert(request);

        //then
        assertEquals(request.getCurrency(), entity.getCurrency());
        assertEquals(request.getShop(), entity.getShop());
        assertEquals(entity.getComment(), "");
        assertEquals(request.getPayer(), entity.getPayer());

    }



}