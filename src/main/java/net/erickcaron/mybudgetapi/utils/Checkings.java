package net.erickcaron.mybudgetapi.utils;

import net.erickcaron.mybudgetapi.exceptions.ResourceError;
import net.erickcaron.mybudgetapi.exceptions.ResourceException;
import net.erickcaron.mybudgetapi.models.Currency;
import net.erickcaron.mybudgetapi.models.Expense;
import net.erickcaron.mybudgetapi.models.Shop;

import java.util.Optional;

public class Checkings {

    public static void checkFoundExpense(Optional<Expense> expenseOptional){
        if(expenseOptional.isEmpty()){
            throw new ResourceException(ResourceError.RESOURCE_NOT_FOUND);
        }
    }

    public static void checkFoundShop(Optional<Shop> shopOptional){
        if(shopOptional.isEmpty()){
            throw new ResourceException(ResourceError.RESOURCE_NOT_FOUND);
        }
    }

    public static void checkFoundCurrency(Optional<Currency> currencyOptional){
        if(currencyOptional.isEmpty()){
            throw new ResourceException(ResourceError.RESOURCE_NOT_FOUND);
        }
    }
}
