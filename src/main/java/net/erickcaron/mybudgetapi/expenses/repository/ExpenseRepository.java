package net.erickcaron.mybudgetapi.expenses.repository;

import net.erickcaron.mybudgetapi.expenses.entity.ExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<ExpenseEntity, Long> {
}
