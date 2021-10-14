package net.erickcaron.mybudgetapi.models;

import lombok.*;
import net.erickcaron.mybudgetapi.models.statuses.ExpenseStatus;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name = "expenses")
public class Expense {

    //TODO validation/error throwing dans le model

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Digits(integer = 10, fraction = 2)
    @DecimalMin("00.01")
    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @Digits(integer = 10, fraction = 2)
    @Column(name = "shared_amount")
    private BigDecimal sharedAmount;

    @Digits(integer = 10, fraction = 2)
    @Column(name = "personal_amount")
    private BigDecimal personalAmount;

    @ManyToOne(fetch = FetchType.EAGER)
    private Shop shop;

    @ManyToOne(fetch = FetchType.EAGER)
    private Currency currency;

    @NotBlank
    private String comments;

    private LocalDate createdOn;

    private LocalDate updatedOn;

    @Enumerated(EnumType.STRING)
    private ExpenseStatus expenseStatus;

    @PrePersist
    public void PrePersist() {
        createdOn = LocalDate.now();
        expenseStatus = ExpenseStatus.CREATED;
    }

    @PreUpdate
    public void PreUpdate() {
        updatedOn = LocalDate.now();
        expenseStatus = ExpenseStatus.UPDATED;
    }

}
