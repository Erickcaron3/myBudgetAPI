package net.erickcaron.mybudgetapi.dtos;

import lombok.*;
import net.erickcaron.mybudgetapi.models.Currency;
import net.erickcaron.mybudgetapi.models.Shop;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class ExpenseDTO {

    private Long id;
    private BigDecimal totalAmount;
    private BigDecimal sharedAmount;
    private BigDecimal personalAmount;
    private Shop shop;
    private Currency currency;
    private String comments;

}
