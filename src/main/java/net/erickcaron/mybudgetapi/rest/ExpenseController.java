package net.erickcaron.mybudgetapi.rest;

import lombok.AllArgsConstructor;
import net.erickcaron.mybudgetapi.exceptions.ResourceError;
import net.erickcaron.mybudgetapi.exceptions.ResourceException;
import net.erickcaron.mybudgetapi.models.Expense;
import net.erickcaron.mybudgetapi.services.CurrencyService;
import net.erickcaron.mybudgetapi.services.ExpenseService;
import net.erickcaron.mybudgetapi.services.ShopService;
import net.erickcaron.mybudgetapi.utils.Checkings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor

@Transactional
@RestController
public class ExpenseController {

    @Autowired
    private final ExpenseService expenseService;

    @Autowired
    private final ShopService shopService;

    @Autowired
    private final CurrencyService currencyService;

    @PostMapping("shops/{shopId}/currencies/{currencyId}/expenses")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Long create(@PathVariable("shopId") Long shopId, @PathVariable("currencyId") Long currencyId, @RequestBody @Valid Expense expense) {
        Checkings.checkFoundShop(shopService.findById(shopId));
        Checkings.checkFoundCurrency(currencyService.findById(currencyId));
        checkExpenseCalculation(expense);
        return expenseService.create(shopId, currencyId, expense);

    }

    @GetMapping("/expenses")
    public List<Expense> findAll() {
        return expenseService.findAll();
    }

    @GetMapping("/shops/{shopId}/expenses")
    public List<Expense> findAllByShopId(@PathVariable("shopId") Long shopId) {
        Checkings.checkFoundShop(shopService.findById(shopId));
        return expenseService.findAllByShopId(shopId);
    }

    @GetMapping("/expenses/{id}")
    public Expense findById(@PathVariable("id") Long id) {
        Checkings.checkFoundExpense(expenseService.findById(id));
        return expenseService.findById(id).get();
    }

    @DeleteMapping("/expenses/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        Checkings.checkFoundExpense(expenseService.findById(id));
        expenseService.delete(expenseService.findById(id).get());
    }

    @PatchMapping("/expenses/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void partialUpdate(@PathVariable("id") Long id, @RequestBody @Valid Expense expense) {
        Checkings.checkFoundExpense(expenseService.findById(id));
        checkExpenseCalculation(expense);
        Expense expenseToUpdate = expenseService.instancingExpense(expenseService.findById(id).get(), expense);
        expenseService.update(id, expenseToUpdate);
    }

    @PatchMapping("shops/{shopId}/currencies/{currencyId}/expenses/{expenseId}")
    @ResponseStatus(code = HttpStatus.OK)
    public void fullUpdate(@PathVariable("shopId") Long shopId, @PathVariable("currencyId") Long currencyId, @PathVariable("expenseId") Long expenseId, @RequestBody @Valid Expense expense) {
        Checkings.checkFoundExpense(expenseService.findById(expenseId));
        Checkings.checkFoundCurrency(currencyService.findById(currencyId));
        Checkings.checkFoundShop(shopService.findById(shopId));
        checkExpenseCalculation(expense);
        Expense expenseToUpdate = expenseService.instancingExpense(expenseService.findById(expenseId).get(), expense);
        expenseService.fullUpdate(expenseId, currencyId, shopId, expenseToUpdate);
    }


    private void checkExpenseCalculation(Expense expense) {
        if (!expenseService.isExpenseCalculationCorrect(expense)) {
            throw new ResourceException(ResourceError.INCORRECT_INPUT);
        }
    }


}
