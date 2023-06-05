package net.erickcaron.mybudgetapi.expenses.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class UpdateExpenseRequest {

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
    private String payer;
}
