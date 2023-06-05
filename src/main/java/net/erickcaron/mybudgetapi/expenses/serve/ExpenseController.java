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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor

@Slf4j
@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseAPI expenseAPI;

    @PostMapping("/save")
    public ResponseEntity<CreateExpenseResponse> create(@RequestBody @Valid CreateExpenseRequest createExpenseRequest){
        CreateExpenseResponse response = expenseAPI.createExpense(createExpenseRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping()
    public ResponseEntity<FindAllExpensesResponse> findAll(){
        FindAllExpensesResponse response = expenseAPI.findAllExpenses();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FindExpenseResponse> findById(@PathVariable String id){
        FindExpenseResponse response = expenseAPI.findExpenseById(id);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UpdateExpenseResponse> updateById(@PathVariable String id, @RequestBody @Valid UpdateExpenseRequest updateExpenseRequest){
        UpdateExpenseResponse response = expenseAPI.updateById(id, updateExpenseRequest);
        return ResponseEntity.ok(response);
    }


}
