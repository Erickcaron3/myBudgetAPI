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
    private LocalDate expenseCreationDate;

    @NotNull
    @NotBlank
    private String documentNumber;

    private LocalDate documentCreationDate;

    private LocalDate coverageFrom;
    private LocalDate coverageTo;

    private LocalDate dueDate;

    @NotNull
    private Boolean isPaid;

}
