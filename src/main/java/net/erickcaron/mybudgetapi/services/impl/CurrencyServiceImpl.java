package net.erickcaron.mybudgetapi.services.impl;

import lombok.AllArgsConstructor;
import net.erickcaron.mybudgetapi.exceptions.ResourceError;
import net.erickcaron.mybudgetapi.exceptions.ResourceException;
import net.erickcaron.mybudgetapi.models.Currency;
import net.erickcaron.mybudgetapi.repositories.CurrencyRepository;
import net.erickcaron.mybudgetapi.services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor

@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    private final CurrencyRepository currencyRepository;

    @Override
    public List<Currency> findAll() {
        return currencyRepository.findAll();
    }

    @Override
    public Optional<Currency> findById(Long id) {
        return currencyRepository.findById(id);
    }

    @Override
    public void delete(Currency currency) {
        currencyRepository.delete(currency);
    }

    @Override
    public void areInputsNotNull(Currency currency) {
        inputsInspection(currency);
    }


    @Override
    public Currency create(Currency currency) {
        return currencyRepository.save(currency);
    }

    @Override
    public void update(Long id, Currency currency) {
        currency.setId(id);
        currencyRepository.save(currency);

    }

    private void inputsInspection(Currency currency) {
        if (currency.getShortcut().length() == 0 && currency.getCountry().length() == 0) {
            throw new ResourceException(ResourceError.INCORRECT_INPUT);
        }
    }


}
