package net.erickcaron.mybudgetapi.expenses.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

@Entity
@Table(name = "expenses_history")
public class ExpenseHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "expense_entity_id")
    private Long expenseEntityId;

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

    @NotNull
    @NotBlank
    private String documentNumber;

    @Column(name = "comment")
    private String comment;

    @NotNull
    @Column(name = "coverage_from")
    @Temporal(TemporalType.DATE)
    private Date coverageFrom;

    @NotNull
    @Column(name = "coverage_to")
    @Temporal(TemporalType.DATE)
    private Date coverageTo;

    @NotNull
    @Column(name = "deletion_flag")
    private boolean isDeleted;

    @NotNull
    @Column(name = "last_modification_date")
    @Temporal(TemporalType.DATE)
    private Date lastModificationDate;
}
