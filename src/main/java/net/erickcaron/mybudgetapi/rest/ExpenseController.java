package net.erickcaron.mybudgetapi.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.erickcaron.mybudgetapi.expenses.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor

@Slf4j
@RestController
@RequestMapping("/api/v1/expenses")
public class ExpenseController {

    private final ExpenseAPI expenseAPI;

    @PostMapping("/save")
    public ResponseEntity<CreateExpenseResponse> create(@RequestBody @Valid CreateExpenseRequest createExpenseRequest){
        CreateExpenseResponse response = expenseAPI.create(createExpenseRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping()
    public ResponseEntity<FindAllExpensesResponse> findAll(){
        FindAllExpensesResponse response = expenseAPI.findAllExpenses();
        return ResponseEntity.ok(response);
    }

}
