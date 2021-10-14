package net.erickcaron.mybudgetapi.repositories;

import net.erickcaron.mybudgetapi.models.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findAllByShopId(Long shopId);
}
