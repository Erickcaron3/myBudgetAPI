package net.erickcaron.mybudgetapi.expenses;

import net.erickcaron.mybudgetapi.expenses.mapper.CreateExpenseResponseMapper;
import net.erickcaron.mybudgetapi.expenses.mapper.ExpenseEntityMapper;
import net.erickcaron.mybudgetapi.expenses.mapper.FindAllExpensesResponseMapper;
import net.erickcaron.mybudgetapi.expenses.mapper.FindExpenseResponseMapper;
import net.erickcaron.mybudgetapi.expenses.repository.ExpenseRepository;
import net.erickcaron.mybudgetapi.utils.ExpenseEntityGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExpenseConfiguration {

    @Bean
    ExpenseAPI expenseAPI(ExpenseRepository expenseRepository,
                          ExpenseEntityMapper expenseEntityMapper,
                          CreateExpenseResponseMapper createExpenseResponseMapper,
                          FindAllExpensesResponseMapper findAllExpensesResponseMapper,
                          FindExpenseResponseMapper findExpenseResponseMapper,
                          ExpenseEntityGenerator expenseEntityGenerator
                          ){
        return new ExpenseFacade(
                expenseRepository,
                expenseEntityMapper,
                createExpenseResponseMapper,
                findAllExpensesResponseMapper,
                findExpenseResponseMapper,
                expenseEntityGenerator
        );
    }

}
