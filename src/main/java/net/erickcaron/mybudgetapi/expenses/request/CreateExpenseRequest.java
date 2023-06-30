package net.erickcaron.mybudgetapi.expenses.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
public class CreateExpenseRequest {

    @NotNull
    private BigDecimal amount;

    @NotNull
    @NotBlank
    private String currency;

    @NotNull
    @NotBlank
    private String shop;

    private String comment;

    @NotNull
    @NotBlank
    private String documentNumber;

    @NotNull
    private LocalDate coverageFrom;

    @NotNull
    private LocalDate coverageTo;

    public CreateExpenseRequest() {
    }

    public CreateExpenseRequest(BigDecimal amount, String currency, String shop, String comment, String documentNumber, LocalDate coverageFrom, LocalDate coverageTo) {
        this.amount = amount;
        this.currency = currency;
        this.shop = shop;
        this.comment = comment;
        this.documentNumber = documentNumber;
        this.coverageFrom = coverageFrom;
        this.coverageTo = coverageTo;
    }
}
