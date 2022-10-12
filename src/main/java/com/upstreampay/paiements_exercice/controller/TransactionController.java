package com.upstreampay.paiements_exercice.controller;

import com.upstreampay.paiements_exercice.config.TransactionState;
import com.upstreampay.paiements_exercice.dto.TransactionDto;
import com.upstreampay.paiements_exercice.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    /**
     * Get all transactions
     * @return
     */
    @RequestMapping("/")
    public Flux<TransactionDto> transactions(){
        return transactionService.getAll();
    }

    /**
     * Get a transaction
     * @param id
     * @return
     */
    @RequestMapping("/{id}")
    public Mono<TransactionDto> getTransaction(@PathVariable String id){
        return transactionService.getById(id);
    }

    /**
     * Add transaction
     * @param transactionDto
     * @return
     */
    @PostMapping
    public Mono<TransactionDto> saveTransaction(@RequestBody TransactionDto transactionDto){
        return transactionService.save(transactionDto);
    }

    /**
     * Update State of transaction
     * @param state
     * @param id
     * @return
     */
    @PutMapping("/update/state/{state}/{id}")
    public Mono<TransactionDto> updateTransaction(@PathVariable TransactionState state, @PathVariable String id){
        TransactionState stateToCheck = null;
        if(state == TransactionState.CAPTURED)
            stateToCheck = TransactionState.AUTHORIZED;
        if(state == TransactionState.AUTHORIZED)
            stateToCheck = TransactionState.NEW;
        return transactionService.update(id, stateToCheck, state);
    }

    /**
     * Delete a transaction
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public Mono<Void> deleteTransaction(@PathVariable String id){
        return transactionService.delete(id);
    }
}
