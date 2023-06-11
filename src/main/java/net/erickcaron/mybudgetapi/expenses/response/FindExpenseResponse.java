package net.erickcaron.mybudgetapi.expenses.response;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class FindExpenseResponse {
    private Long id;
    private BigDecimal amount;
    private String currency;
    private String shop;
    private String comment;
    private String payer;
    private LocalDateTime creationDate;
}
