package com.prince.Personal.Finance.Manager.API.controller;

import com.prince.Personal.Finance.Manager.API.dto.TransactionRequest;
import com.prince.Personal.Finance.Manager.API.model.Transaction;
import com.prince.Personal.Finance.Manager.API.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    private String getEmail(Principal principal) {
        return principal.getName();
    }

    @PostMapping("/add")
    public ResponseEntity<?> addTransaction(@RequestBody TransactionRequest request,
                                            Principal principal) {
        Transaction transaction = transactionService
                .addtransaction(request, getEmail(principal));
        return ResponseEntity.ok(transaction);
    }

    @GetMapping("getalltransaction")
    public ResponseEntity<?> getAllTransactions(Principal principal) {
        List<Transaction> transactions = transactionService
                .getAllTransaction(getEmail(principal));
        return ResponseEntity.ok(transactions);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTransaction(@PathVariable Long id,
                                               @RequestBody TransactionRequest request,
                                               Principal principal) {
        Transaction transaction = transactionService
                .updateTransaction(id, request, getEmail(principal));
        return ResponseEntity.ok(transaction);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.ok("Transaction deleted successfully");
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<?> getByType(@PathVariable String type,
                                       Principal principal) {
        List<Transaction> transactions = transactionService
                .getType(getEmail(principal), type);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<?> getByCategory(@PathVariable String category,
                                           Principal principal) {
        List<Transaction> transactions = transactionService
                .getCategory(getEmail(principal), category);
        return ResponseEntity.ok(transactions);
    }
    @GetMapping("/summary/{year}/{month}")
    public ResponseEntity<?> getMonthlySummary(@PathVariable int year,
                                               @PathVariable int month,
                                               Principal principal) {
        Map<String, Object> summary = transactionService
                .getMonthlySummary(getEmail(principal), month, year);
        return ResponseEntity.ok(summary);
    }
}
