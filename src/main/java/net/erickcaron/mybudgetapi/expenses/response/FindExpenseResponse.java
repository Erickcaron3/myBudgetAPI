package net.erickcaron.mybudgetapi.expenses.response;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Builder
public class FindExpenseResponse {
    private Long id;
    private BigDecimal amount;
    private String currency;
    private String shop;
    private String comment;
    private String documentNumber;
    private Date coverageFrom;
    private Date coverageTo;
}
