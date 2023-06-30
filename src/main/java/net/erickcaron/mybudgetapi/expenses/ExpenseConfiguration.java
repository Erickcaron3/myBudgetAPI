package net.erickcaron.mybudgetapi.expenses;

import net.erickcaron.mybudgetapi.logic.CreateExpenseLogic;
import net.erickcaron.mybudgetapi.logic.FindAllExpensesLogic;
import net.erickcaron.mybudgetapi.logic.UpdateExpensesLogic;
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
                          FindAllExpensesLogic findAllExpensesLogic,
                          UpdateExpensesLogic updateExpensesLogic
                          ){
        return new ExpenseFacade(
                expenseRepository,
                createExpenseLogic,
                findExpenseResponseMapper,
                expenseEntityGenerator,
                findAllExpensesLogic,
                updateExpensesLogic
        );
    }

}
