package net.erickcaron.mybudgetapi.repositories;

import net.erickcaron.mybudgetapi.models.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    Currency findByShortcut(String shortcut); //TODO utiliser ExistsBy!
}
