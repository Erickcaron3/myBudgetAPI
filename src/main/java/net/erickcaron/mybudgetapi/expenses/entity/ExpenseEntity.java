package net.erickcaron.mybudgetapi.expenses.entity;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

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
    @Column(name="creation_date")
    private LocalDate creationDate;

    @NotNull
    @Column(name="document_creation_date")
    private LocalDate documentCreationDate;

    @Column(name="due_date")
    private LocalDate dueDate;

    @Column(name="coverage_from")
    private LocalDate coverageFrom;

    @Column(name="coverage_to")
    private LocalDate coverageTo;

    @Column(name="paid")
    private Boolean isPaid;





}



