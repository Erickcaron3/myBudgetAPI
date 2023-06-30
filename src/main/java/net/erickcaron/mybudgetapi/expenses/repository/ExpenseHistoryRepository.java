package net.erickcaron.mybudgetapi.expenses.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseHistoryRepository extends JpaRepository<ExpenseHistoryRepository, Long> {
}
