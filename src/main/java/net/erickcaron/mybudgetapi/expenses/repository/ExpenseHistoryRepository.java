package net.erickcaron.mybudgetapi.expenses.repository;

import net.erickcaron.mybudgetapi.expenses.entity.ExpenseHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseHistoryRepository extends JpaRepository<ExpenseHistoryEntity, Long> {
}
