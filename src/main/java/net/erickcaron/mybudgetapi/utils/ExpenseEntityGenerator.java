package net.erickcaron.mybudgetapi.utils;

import net.erickcaron.mybudgetapi.expenses.entity.ExpenseEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class ExpenseEntityGenerator {

    final Random RANDOM = new Random();

    public List<ExpenseEntity> generateListExpenses(Long numberOfExpenses) {
        List<ExpenseEntity> expenses = new ArrayList<>();


        for (int i = 1; i <= numberOfExpenses; i++) {
            expenses.add(generateRandomExpenseEntity());
        }

        return expenses;
    }

    private ExpenseEntity generateRandomExpenseEntity() {
        return ExpenseEntity.builder()
                .amount(generateAmount())
                .currency(generateCurrency())
                .shop(generateShop())
                .payer(generatePayer())
                .creationDate(LocalDateTime.now())
                .comment("This is a comment")
                .build();
    }


    private BigDecimal generateAmount() {
        return BigDecimal.valueOf(RANDOM.nextInt(1_000));
    }

    private String generateCurrency() {
        String[] currencies = {"zł", "EUR"};
        return getRandomFromArray(currencies);
    }

    private String generatePayer() {
        String[] payers = {"Erick", "Magda"};
        return getRandomFromArray(payers);
    }

    private String generateShop() {
        String[] shops = {"Auchan", "Carrefour", "Biedronka", "Decathlon", "Lidl"};
        return getRandomFromArray(shops);
    }

    private String getRandomFromArray(String[] data) {
        return data[RANDOM.nextInt(data.length)];
    }


}
