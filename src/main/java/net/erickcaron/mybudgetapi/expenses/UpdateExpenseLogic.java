package net.erickcaron.mybudgetapi.expenses;

import lombok.extern.slf4j.Slf4j;
import net.erickcaron.mybudgetapi.entity.ExpenseEntity;
import net.erickcaron.mybudgetapi.expenses.exception.ExpenseNotFoundException;
import net.erickcaron.mybudgetapi.expenses.request.UpdateExpenseRequest;
import net.erickcaron.mybudgetapi.expenses.response.UpdateExpenseResponse;
import net.erickcaron.mybudgetapi.repository.ExpenseRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Slf4j
@Component
public class UpdateExpenseLogic {

    private final ExpenseRepository expenseRepository;

    public UpdateExpenseLogic(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public UpdateExpenseResponse updateById(String id, UpdateExpenseRequest updateExpenseRequest) {
        ExpenseEntity expenseFromDataBase = expenseRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> {
                    log.error("There is no expense with the id: " + id);
                    throw new ExpenseNotFoundException("There is no expense with the id: " + id);
                });

        return !isEntityCommonWithIncomingValues(expenseFromDataBase, updateExpenseRequest)
                ? handleUpdateWithSuccess(expenseFromDataBase, updateExpenseRequest)
                : handleUpdateWithoutSuccess(id);
    }

    private UpdateExpenseResponse handleUpdateWithSuccess(ExpenseEntity expenseFromDataBase, UpdateExpenseRequest updateExpenseRequest) {
        ExpenseEntity expenseToSave = buildExpenseEntityToSave(expenseFromDataBase, updateExpenseRequest);
        String id = expenseFromDataBase.getId().toString();
        expenseRepository.save(expenseToSave);
        log.info("Expense with id " + id + "successfully updated");
        return buildUpdateExpenseResponse(id, true);
    }

    private UpdateExpenseResponse handleUpdateWithoutSuccess(String id) {
        log.info("Expense with id " + id + "not updated");
        return buildUpdateExpenseResponse(id, false);
    }

    private UpdateExpenseResponse buildUpdateExpenseResponse(String id, boolean isUpdated) {
        return UpdateExpenseResponse.builder()
                .id(id)
                .isUpdated(isUpdated)
                .build();
    }


    private ExpenseEntity buildExpenseEntityToSave(ExpenseEntity expenseFromDatabase, UpdateExpenseRequest incomingValues) {
        return ExpenseEntity.builder()
                .id(expenseFromDatabase.getId())
                .amount(settleAmount(expenseFromDatabase.getAmount(), incomingValues.getAmount()))
                .comment(settleStringElement(expenseFromDatabase.getCurrency(), incomingValues.getCurrency()))
                .shop(settleStringElement(expenseFromDatabase.getShop(), incomingValues.getShop()))
                .comment(settleStringElement(expenseFromDatabase.getComment(), incomingValues.getComment()))
                .build();
    }

    private BigDecimal settleAmount(BigDecimal fromDB, BigDecimal fromIncoming) {
        return fromDB.compareTo(fromIncoming) == 0
                ? fromDB
                : fromIncoming;
    }

    private String settleStringElement(String fromDB, String fromIncoming) {
        return fromDB.equalsIgnoreCase(fromIncoming)
                ? fromDB
                : fromIncoming;
    }

    private boolean isEntityCommonWithIncomingValues(ExpenseEntity expenseFromDatabase, UpdateExpenseRequest incomingValues) {
        return expenseFromDatabase.getAmount().equals(incomingValues.getAmount())
                && expenseFromDatabase.getCurrency().equalsIgnoreCase(incomingValues.getCurrency())
                && expenseFromDatabase.getShop().equalsIgnoreCase(incomingValues.getShop())
                && expenseFromDatabase.getPayer().equalsIgnoreCase(incomingValues.getPayer())
                && areCommentsSimilar(expenseFromDatabase.getComment(), incomingValues.getComment());
    }

    private boolean areCommentsSimilar(String commentFromDatabase, String incomingComment) {
        return isIncomingCommentEligibleForUpdate(incomingComment) && commentFromDatabase.equalsIgnoreCase(incomingComment);

    }

    private boolean isIncomingCommentEligibleForUpdate(String incomingComment) {
        return incomingComment != null && !incomingComment.equalsIgnoreCase("");

    }
}
