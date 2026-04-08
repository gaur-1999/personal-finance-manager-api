package com.prince.Personal.Finance.Manager.API.service;

import com.prince.Personal.Finance.Manager.API.dto.TransactionRequest;
import com.prince.Personal.Finance.Manager.API.model.Transaction;
import com.prince.Personal.Finance.Manager.API.model.User;
import com.prince.Personal.Finance.Manager.API.repository.TransationRepository;
import com.prince.Personal.Finance.Manager.API.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final UserRepository userRepository;
    private final TransationRepository transationRepository;

    public Transaction addtransaction(TransactionRequest request, String email){
        User user=userRepository.findByEmail(email).orElseThrow(()->new RuntimeException("User not Found"));
        Transaction transaction=new Transaction();
        transaction.setTitle(request.getTitle());
        transaction.setAmount(request.getAmount());
        transaction.setType(request.getType());
        transaction.setCategory(request.getCategory());
        transaction.setDate(request.getDate());
        transaction.setUser(user);

        return transationRepository.save(transaction);
    }

    public List<Transaction> getAllTransaction(String email){
        User user=userRepository.findByEmail(email).orElseThrow(()->new RuntimeException("User not found"));
        return transationRepository.findByUserId(user.getId());
    }

    public Transaction updateTransaction(Long id,TransactionRequest request,String email){
        Transaction transaction=transationRepository.findById(id).orElseThrow(()->new RuntimeException("Transaction not found"));
        transaction.setTitle(request.getTitle());
        transaction.setAmount(request.getAmount());
        transaction.setType(request.getType());
        transaction.setCategory(request.getCategory());
        transaction.setDate(request.getDate());
        return transationRepository.save(transaction);
    }
    public void deleteTransaction(Long id){
        transationRepository.deleteById(id);
    }
    public List<Transaction> getType(String email,String type){
        User user=userRepository.findByEmail(email).orElseThrow(()->new RuntimeException("User not found"));
        List<Transaction> transactions=transationRepository.findByUserIdAndType(user.getId(), type);
        return transactions;
    }
    public List<Transaction> getCategory(String email,String category){
        User user=userRepository.findByEmail(email).orElseThrow(()->new RuntimeException("User not found"));
        return transationRepository.findByUserIdAndCategory(user.getId() ,category);
    }

    public Map<String, Object> getMonthlySummary(String email, int month, int year) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Transaction> all = transationRepository.findByUserId(user.getId());

        List<Transaction> monthly = all.stream()
                .filter(t -> t.getDate().getMonthValue() == month
                        && t.getDate().getYear() == year)
                .collect(Collectors.toList());

        double totalIncome = monthly.stream()
                .filter(t -> t.getType().equals("INCOME"))
                .mapToDouble(Transaction::getAmount)
                .sum();

        double totalExpense = monthly.stream()
                .filter(t -> t.getType().equals("EXPENSE"))
                .mapToDouble(Transaction::getAmount)
                .sum();

        Map<String, Object> summary = new HashMap<>();
        summary.put("month", month);
        summary.put("year", year);
        summary.put("totalIncome", totalIncome);
        summary.put("totalExpense", totalExpense);
        summary.put("netSavings", totalIncome - totalExpense);
        summary.put("transactions", monthly);

        return summary;
    }
}
