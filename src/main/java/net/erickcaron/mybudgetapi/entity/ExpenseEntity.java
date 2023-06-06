package net.erickcaron.mybudgetapi.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@RequiredArgsConstructor
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

}



