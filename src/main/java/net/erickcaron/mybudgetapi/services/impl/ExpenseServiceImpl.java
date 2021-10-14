package net.erickcaron.mybudgetapi.services.impl;

import lombok.AllArgsConstructor;
import net.erickcaron.mybudgetapi.models.Expense;
import net.erickcaron.mybudgetapi.repositories.CurrencyRepository;
import net.erickcaron.mybudgetapi.repositories.ExpenseRepository;
import net.erickcaron.mybudgetapi.repositories.ShopRepository;
import net.erickcaron.mybudgetapi.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@AllArgsConstructor

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private final ExpenseRepository expenseRepository;

    @Autowired
    private final ShopRepository shopRepository;

    @Autowired
    private final CurrencyRepository currencyRepository;


    @Override
    public List<Expense> findAll() {
        return expenseRepository.findAll();
    }

    @Override
    public Long create(Long shopId, Long currencyId, Expense expense) {
        expense.setShop(shopRepository.findById(shopId).get());
        expense.setCurrency(currencyRepository.findById(currencyId).get());
        return expenseRepository.save(expense).getId();
    }

    @Override
    public Optional<Expense> findById(Long id) {
        return expenseRepository.findById(id);
    }

    @Override
    public void delete(Expense expense) {
        expenseRepository.delete(expense);
    }


    @Override
    public void update(Long id, Expense expense) {
        expense.setId(id);
        expenseRepository.save(expense);
    }

    @Override
    public Expense instancingExpense(Expense expenseFromDB, Expense newExpense) {
        Expense expenseToUpdate = settleExpenseToUpdate(expenseFromDB, newExpense);
        expenseToUpdate.setCreatedOn(expenseFromDB.getCreatedOn());
        if (newExpense.getComments().equals(expenseFromDB.getComments())) {
            expenseToUpdate.setComments(expenseFromDB.getComments());
        }
        return expenseToUpdate;
    }

    @Override
    public List<Expense> findAllByShopId(Long shopId) {
        return expenseRepository.findAllByShopId(shopId);
    }

    @Override
    public void fullUpdate(Long expenseId, Long currencyId, Long shopId, Expense expense) {
        expense.setId(expenseId);
        expense.setCurrency(currencyRepository.findById(currencyId).get());
        expense.setShop(shopRepository.findById(shopId).get());
        expenseRepository.save(expense);
    }

    @Override
    public boolean isExpenseCalculationCorrect(Expense expense) {
        return expense.getPersonalAmount().add(expense.getSharedAmount()).equals(expense.getTotalAmount());
    }

    private Expense settleExpenseToUpdate(Expense expenseFromDB, Expense newExpense) {
        Expense result = new Expense();
        result.setPersonalAmount(fixSettleExpenseToUpdate(expenseFromDB.getPersonalAmount(), newExpense.getPersonalAmount()));
        result.setSharedAmount(fixSettleExpenseToUpdate(expenseFromDB.getSharedAmount(), newExpense.getSharedAmount()));
        result.setTotalAmount(fixSettleExpenseToUpdate(expenseFromDB.getTotalAmount(), newExpense.getTotalAmount()));
        return result;
    }

    private BigDecimal fixSettleExpenseToUpdate(BigDecimal expenseFromDB, BigDecimal newExpense) {
        BigDecimal result;
        if (!expenseFromDB.equals(newExpense)) {
            result = newExpense;
        } else {
            result = expenseFromDB;
        }
        return result;
    }
}
