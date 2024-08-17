package com.example.simpleapi.service;

import com.example.simpleapi.exception.AccountNotFoundException;
import com.example.simpleapi.exception.EmailExistsException;
import com.example.simpleapi.model.Account;
import com.example.simpleapi.repository.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {
    final private AccountRepository accountRepository;

    // Autowired allows Spring to resolve and inject collaborating beans into our bean
    // in other word, Spring will instantiate accountRepository and inject it
    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void addNewAccount(Account account) {
        Optional<Account> accountInDb = accountRepository.findAccountByEmail(account.getEmail());

        if (accountInDb.isPresent()) {
            throw new EmailExistsException("Account already exists");
        }
        accountRepository.save(account);
    }

    public Account getAccountById(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));
    }

    @Transactional
    public void updateAccount(Long accountId, Account updatedAccount) {
        Account existringAccount = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        existringAccount.setName(updatedAccount.getName());
        existringAccount.setEmail(updatedAccount.getEmail());
        existringAccount.setRoleId(updatedAccount.getRoleId());
    }

    public void deleteAccount(Long accountId) {
        boolean accountExists = accountRepository.existsById(accountId);

        if (!accountExists) {
            throw new AccountNotFoundException("Account not found");
        }
        accountRepository.deleteById(accountId);
    }
}
