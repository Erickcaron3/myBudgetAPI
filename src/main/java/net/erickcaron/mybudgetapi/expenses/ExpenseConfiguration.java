package net.erickcaron.mybudgetapi.expenses;

import net.erickcaron.mybudgetapi.mapper.CreateExpenseResponseMapper;
import net.erickcaron.mybudgetapi.mapper.ExpenseEntityMapper;
import net.erickcaron.mybudgetapi.mapper.FindAllExpensesResponseMapper;
import net.erickcaron.mybudgetapi.repositories.ExpenseRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExpenseConfiguration {

    @Bean
    ExpenseAPI expenseAPI(ExpenseRepository expenseRepository,
                          ExpenseEntityMapper expenseEntityMapper,
                          CreateExpenseResponseMapper createExpenseResponseMapper,
                          FindAllExpensesResponseMapper findAllExpensesResponseMapper){
        return new ExpenseFacade(
                expenseRepository,
                expenseEntityMapper,
                createExpenseResponseMapper,
                findAllExpensesResponseMapper);
    }

    /*

    missing : private final ExpenseRepository  BUT IS ABSTRACT???
     */

}
