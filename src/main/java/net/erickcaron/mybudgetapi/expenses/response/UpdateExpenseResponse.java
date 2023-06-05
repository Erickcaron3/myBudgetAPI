package net.erickcaron.mybudgetapi.expenses.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UpdateExpenseResponse {
    private String id;
    private boolean isUpdated;
    //cos co okresla zmiany wykonane
}
