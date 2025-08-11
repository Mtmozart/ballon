package br.com.ballon.application.expenses;

import java.util.List;

public record PageExpenseResponse(
        List<DataExpense> content,
        int pageNumber,
        int pageSize,
        long totalElements,
        int totalPages,
        boolean last
) {}
