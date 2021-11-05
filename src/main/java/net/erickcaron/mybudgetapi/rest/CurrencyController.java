package net.erickcaron.mybudgetapi.rest;

import lombok.AllArgsConstructor;
import net.erickcaron.mybudgetapi.models.Currency;
import net.erickcaron.mybudgetapi.services.CurrencyService;
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
@RequestMapping("/currencies")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping
    public List<Currency> findAll() {
        return currencyService.findAll();
    }

    @GetMapping("/{id}")
    public Currency findById(@PathVariable("id") Long id) {
        Checkings.checkFoundCurrency(currencyService.findById(id));
        return currencyService.findById(id).get();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Long create(@RequestBody @Valid Currency currency) {
        checkInputsNotNull(currency);
        return currencyService.create(currency).getId();
    }
    
    @PatchMapping("/{id}")
    @ResponseStatus(code=HttpStatus.OK)
    public void update(@PathVariable("id") Long id, @RequestBody  @Valid Currency currency){
        Checkings.checkFoundCurrency(currencyService.findById(id));
        currencyService.areInputsNotNull(currency);
        currencyService.update(id, currency);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        Checkings.checkFoundCurrency(currencyService.findById(id));
        currencyService.delete(currencyService.findById(id).get());
    }


    private void checkInputsNotNull(Currency currency) {
        currencyService.areInputsNotNull(currency);
    }


}

