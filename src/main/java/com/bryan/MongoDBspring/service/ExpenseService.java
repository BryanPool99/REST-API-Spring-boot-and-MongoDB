package com.bryan.MongoDBspring.service;

import com.bryan.MongoDBspring.model.Expense;
import com.bryan.MongoDBspring.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public void addExpense(Expense expense){
        expenseRepository.insert(expense);
    }
    public void updateExpense(Expense expense){
        Expense savedExpense=expenseRepository.findById(expense.getId()).orElseThrow(()->new RuntimeException(
                String.format("expense con id %s no existe",expense.getId())
        ));
        savedExpense.setExpenseName(expense.getExpenseName());
        savedExpense.setExpenseCategory(expense.getExpenseCategory());
        savedExpense.setExpenseAmount(expense.getExpenseAmount());
        expenseRepository.save(expense);
    }
    public List<Expense> getAllExpenses(){
        return expenseRepository.findAll();
    }
    public Expense getExpenseByName(String name){
        return expenseRepository.findByName(name).orElseThrow(() -> new RuntimeException(
                String.format("Expense con name %s no encontrado",name)
        ));
    }
    public void deleteExpense(String id){
        expenseRepository.deleteById(id);
    }
}
