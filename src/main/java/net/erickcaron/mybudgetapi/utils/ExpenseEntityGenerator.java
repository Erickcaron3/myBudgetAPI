package net.erickcaron.mybudgetapi.utils;

import net.erickcaron.mybudgetapi.expenses.entity.ExpenseEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

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
                .comment("This is a comment")
                .documentNumber("1223456")
                .coverageFrom(new GregorianCalendar(2023,01,01).getTime())
                .coverageTo(new GregorianCalendar(2023,01,31).getTime())
                .build();
    }


    private BigDecimal generateAmount() {
        return BigDecimal.valueOf(RANDOM.nextInt(1_000));
    }

    private String generateCurrency() {
        String[] currencies = {"zł", "EUR"};
        return getRandomStringFromArray(currencies);
    }


    private String generateShop() {
        String[] shops = {"Auchan", "Carrefour", "Biedronka", "Decathlon", "Lidl"};
        return getRandomStringFromArray(shops);
    }

    private String getRandomStringFromArray(String[] data) {
        return data[RANDOM.nextInt(data.length)];
    }


}
