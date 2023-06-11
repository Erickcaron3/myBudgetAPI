package net.erickcaron.mybudgetapi.expenses.entity;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

@Entity
@Table(name = "expenses")
public class ExpenseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "amount")
    private BigDecimal amount;

    @NotNull
    @NotBlank
    @Column(name = "currency")
    private String currency;

    @NotNull
    @NotBlank
    @Column(name = "shop")
    private String shop;

    @Column(name = "comment")
    private String comment;

    @NotNull
    @NotBlank
    @Column(name = "payer")
    private String payer;

    @NotNull
    @Column(name="creation_date")
    private LocalDateTime creationDate;

}



