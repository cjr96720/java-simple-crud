package com.example.simpleapi.controller;

import com.example.simpleapi.exception.AccountNotFoundException;
import com.example.simpleapi.exception.EmailExistsException;
import com.example.simpleapi.model.Account;
import com.example.simpleapi.service.AccountService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {
    final private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> addAccount(@NotNull @Valid @RequestBody Account account) {
        try {
            accountService.addNewAccount(account);
        } catch (EmailExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
        return ResponseEntity.ok(Map.of("message", "New account added"));
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<Map<String, ?>> getAccountById(@PathVariable Long accountId) {
        try {
            Account account = accountService.getAccountById(accountId);
            Map<String, Account> responseBody = Map.of("data", account);
            return ResponseEntity.ok(responseBody);
        } catch (AccountNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/{accountId}")
    public ResponseEntity<Map<String, String>> updateAccount(@PathVariable Long accountId,
                                                             @NotNull @Valid @RequestBody Account updatedAccount){
        try {
            accountService.updateAccount(accountId, updatedAccount);
            return ResponseEntity.ok(Map.of("message", "Account updated"));
        } catch (AccountNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<Map<String, String>> deleteAccount(@PathVariable Long accountId) {
        try {
            accountService.deleteAccount(accountId);
            return ResponseEntity.ok(Map.of("message", "Account deleted"));
        } catch (AccountNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }
}
