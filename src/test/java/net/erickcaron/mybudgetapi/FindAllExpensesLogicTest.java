package net.erickcaron.mybudgetapi;

import net.erickcaron.mybudgetapi.expenses.entity.ExpenseEntity;
import net.erickcaron.mybudgetapi.expenses.mapper.FindAllExpensesResponseMapper;
import net.erickcaron.mybudgetapi.expenses.repository.ExpenseRepository;
import net.erickcaron.mybudgetapi.expenses.response.FindAllExpensesResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

class FindAllExpensesLogicTest {
    private FindAllExpensesLogic findAllExpensesLogic;
    private FindAllExpensesResponseMapper findAllExpensesResponseMapper;

    @Mock
    private ExpenseRepository expenseRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        findAllExpensesResponseMapper = mock(FindAllExpensesResponseMapper.class);
        findAllExpensesLogic = new FindAllExpensesLogic(findAllExpensesResponseMapper, expenseRepository);
    }

    @Test
    void shouldRetrieveDataCorrectly() {

        ExpenseEntity expense1 = ExpenseEntity.builder()
                .id(1L)
                .amount(BigDecimal.valueOf(150.00))
                .currency("PLN")
                .shop("Carrefour")
                .comment("Shopping in carrefour")
                .documentNumber("123456")
                .coverageFrom(new GregorianCalendar(2023, 01, 01).getTime())
                .coverageFrom(new GregorianCalendar(2023, 01, 31).getTime())
                .build();

        ExpenseEntity expense2 = ExpenseEntity.builder()
                .id(1L)
                .amount(BigDecimal.valueOf(150.00))
                .currency("PLN")
                .shop("Carrefour")
                .comment("Shopping in carrefour")
                .documentNumber("123456")
                .coverageFrom(new GregorianCalendar(2023, 01, 01).getTime())
                .coverageFrom(new GregorianCalendar(2023, 01, 31).getTime())
                .build();

        List<ExpenseEntity> entitiesList = List.of(expense1, expense2);

        doReturn(entitiesList).when(expenseRepository).findAll();
        FindAllExpensesResponse findAllExpensesResponse = FindAllExpensesResponse.builder().expensesList(entitiesList).build();
        doReturn(findAllExpensesResponse).when(findAllExpensesResponseMapper).convert(entitiesList);
        FindAllExpensesResponse response = findAllExpensesLogic.retrieveExpenses();

        assertEquals(response.getExpensesList(), findAllExpensesResponse.getExpensesList());
        assertEquals(response, findAllExpensesResponse);

    }

}