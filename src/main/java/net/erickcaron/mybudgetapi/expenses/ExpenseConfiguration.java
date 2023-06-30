package net.erickcaron.mybudgetapi.expenses;

import net.erickcaron.mybudgetapi.CreateExpenseLogic;
import net.erickcaron.mybudgetapi.FindAllExpensesLogic;
import net.erickcaron.mybudgetapi.expenses.mapper.FindExpenseResponseMapper;
import net.erickcaron.mybudgetapi.expenses.repository.ExpenseRepository;
import net.erickcaron.mybudgetapi.utils.ExpenseEntityGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExpenseConfiguration {

    @Bean
    ExpenseAPI expenseAPI(ExpenseRepository expenseRepository,
                          CreateExpenseLogic createExpenseLogic,
                          FindExpenseResponseMapper findExpenseResponseMapper,
                          ExpenseEntityGenerator expenseEntityGenerator,
                          FindAllExpensesLogic findAllExpensesLogic
                          ){
        return new ExpenseFacade(
                expenseRepository,
                createExpenseLogic,
                findExpenseResponseMapper,
                expenseEntityGenerator,
                findAllExpensesLogic
        );
    }

}
