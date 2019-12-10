package com.example.taskmanagementapp.expense_tracking;

import com.google.firebase.database.Exclude;

public class SiemensDetail {

    String siemensId;
    String expenseTypeSpinner_D;
    String expense_site_D;
    String dateExpense_D ;
    String priceEx_D ;
    String description_D ;

    public SiemensDetail(){}

    public SiemensDetail(String siemensId, String expenseTypeSpinner_D, String expense_site_D, String dateExpense_D, String priceEx_D, String description_D) {
        this.siemensId = siemensId;
        this.expenseTypeSpinner_D = expenseTypeSpinner_D;
        this.expense_site_D = expense_site_D;
        this.dateExpense_D = dateExpense_D;
        this.priceEx_D = priceEx_D;
        this.description_D = description_D;
    }

    public String getSiemensId() {
        return siemensId;
    }

    public String getExpenseTypeSpinner_D() {
        return expenseTypeSpinner_D;
    }

    public String getExpense_site_D() {
        return expense_site_D;
    }

    public String getDateExpense_D() {
        return dateExpense_D;
    }

    public String getPriceEx_D() {
        return priceEx_D;
    }

    public String getDescription_D() {
        return description_D;
    }
}
