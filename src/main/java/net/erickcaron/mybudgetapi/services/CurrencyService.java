package net.erickcaron.mybudgetapi.services;

import net.erickcaron.mybudgetapi.models.Currency;

import javax.net.ssl.SSLSession;
import java.util.List;
import java.util.Optional;

public interface CurrencyService {
    List<Currency> findAll();

    Optional<Currency> findById(Long id);

    void delete(Currency currency);

    void areInputsNotNull(Currency currency);

    Currency create(Currency currency);

    void update(Long id, Currency currency);
}
