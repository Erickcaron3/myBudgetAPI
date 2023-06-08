package net.erickcaron.mybudgetapi.expenses.serve;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.erickcaron.mybudgetapi.expenses.*;
import net.erickcaron.mybudgetapi.expenses.request.CreateExpenseRequest;
import net.erickcaron.mybudgetapi.expenses.request.UpdateExpenseRequest;
import net.erickcaron.mybudgetapi.expenses.response.CreateExpenseResponse;
import net.erickcaron.mybudgetapi.expenses.response.FindAllExpensesResponse;
import net.erickcaron.mybudgetapi.expenses.response.FindExpenseResponse;
import net.erickcaron.mybudgetapi.expenses.response.UpdateExpenseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Optional;

@RequiredArgsConstructor

@Slf4j
@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseAPI expenseAPI;

    @PostMapping()
    public ResponseEntity<CreateExpenseResponse> create(@RequestBody @Valid CreateExpenseRequest createExpenseRequest) {
        CreateExpenseResponse response = expenseAPI.createExpense(createExpenseRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping()
    public ResponseEntity<FindAllExpensesResponse> findAll() {
        FindAllExpensesResponse response = expenseAPI.findAllExpenses();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FindExpenseResponse> findById(@PathVariable String id) {
        return ResponseEntity.ok(expenseAPI.findExpenseById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no any expense with requested id: " + id)
                )
        );
    }

    @PatchMapping()
    public ResponseEntity<UpdateExpenseResponse> updateById(@RequestBody @Valid UpdateExpenseRequest updateExpenseRequest) {
        UpdateExpenseResponse response = expenseAPI.updateById(updateExpenseRequest);
        return ResponseEntity.ok(response);
    }


}
