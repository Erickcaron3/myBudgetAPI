package net.erickcaron.mybudgetapi.expenses.serve;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.erickcaron.mybudgetapi.expenses.*;
import net.erickcaron.mybudgetapi.expenses.request.CreateExpenseRequest;
import net.erickcaron.mybudgetapi.expenses.request.UpdateExpenseRequest;
import net.erickcaron.mybudgetapi.expenses.response.CreateExpenseResponse;
import net.erickcaron.mybudgetapi.expenses.response.FindAllExpensesResponse;
import net.erickcaron.mybudgetapi.expenses.response.FindExpenseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor

@Slf4j
@RestController
@RequestMapping("/api/expenses")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ExpenseController {

    private final ExpenseAPI expenseAPI;

    @PostMapping()
    public ResponseEntity<CreateExpenseResponse> create(@RequestBody @Valid CreateExpenseRequest createExpenseRequest) {
        return ResponseEntity.ok(expenseAPI.createExpense(createExpenseRequest));
    }

    @GetMapping()
    public ResponseEntity<FindAllExpensesResponse> findAll() {
        return ResponseEntity.ok(expenseAPI.findExpenses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FindExpenseResponse> findById(@PathVariable String id) {
        return ResponseEntity.ok(expenseAPI.findExpenseById(id));
    }

    @PatchMapping("/{id}")
    public void updateById(@PathVariable String id, @RequestBody @Valid UpdateExpenseRequest updateExpenseRequest) {
        expenseAPI.updateById(id, updateExpenseRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteExpenseById(@PathVariable String id){
        expenseAPI.deleteById(id);
    }

    @GetMapping("/generate/{numberOfExpenses}")
    public void generateData(@PathVariable Long numberOfExpenses){
        expenseAPI.saveEntities(numberOfExpenses);
    }

}
