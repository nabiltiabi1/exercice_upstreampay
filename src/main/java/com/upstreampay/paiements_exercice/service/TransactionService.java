package com.upstreampay.paiements_exercice.service;

import com.upstreampay.paiements_exercice.config.TransactionState;
import com.upstreampay.paiements_exercice.dto.TransactionDto;
import com.upstreampay.paiements_exercice.entity.Transaction;
import com.upstreampay.paiements_exercice.exception.UnAuthorizedException;
import com.upstreampay.paiements_exercice.exception.UpdateStateException;
import com.upstreampay.paiements_exercice.repository.TransactionRepository;
import com.upstreampay.paiements_exercice.mapper.TransactionMapper;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository repository;

    public Flux<TransactionDto> getAll() {
        return repository.findAll()
                                    .map(TransactionMapper::entityToDto);
    }

    public Mono<TransactionDto> get(String id) {
        return repository.findById(id)
                .map(TransactionMapper::entityToDto);
    }

    public Mono<TransactionDto> getById(String id) {
        return repository.findById(id)
                .map(TransactionMapper::entityToDto);
    }

    public Mono<TransactionDto> save(TransactionDto transactionDtoMono) {
        return Mono.just(TransactionMapper.dtoToEntity(transactionDtoMono))
                                .flatMap(repository::insert)
                                .map(TransactionMapper::entityToDto);
    }

    public Mono<TransactionDto> update(String id, TransactionState stateToCheck, TransactionState stateToUpdate) {
        return repository.findByIdAndState(id, stateToCheck)
                        .switchIfEmpty(Mono.error(new UpdateStateException("Transaction not found with given id and state")))
                        .map(transaction -> {
                            transaction.setState(stateToUpdate);
                            return transaction;
                        })
                        .flatMap(repository::save)
                        .map(TransactionMapper::entityToDto);
    }

    public Mono<Void> delete(String id) {
        return repository.deleteById(id);
    }

   /* static Mono<Transaction> validateState(Transaction transaction, TransactionDto transactionDto){
        return (transactionDto.getState().equalsIgnoreCase(String.valueOf(TransactionState.NEW)) && transaction.getState().equalsIgnoreCase(String.valueOf(TransactionState.NEW)) )
                || !transaction.getState().equalsIgnoreCase(TransactionState.NEW.toString()) ? Mono.just(transaction) : Mono.error(new UnAuthorizedException("Action not authorized"));
    }*/

}
