package com.expleo.notification_service.dto;

public record ExpenseMessageDto(String userName, String emailId, String phoneNumber,
                                Double remainingBudget,
                                String expenseCategory,
                                String expenseDescription,
                                Double expenseAmount
) {

}
