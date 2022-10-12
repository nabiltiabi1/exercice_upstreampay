package com.upstreampay.paiements_exercice.repository;

import com.upstreampay.paiements_exercice.config.TransactionState;
import com.upstreampay.paiements_exercice.entity.Transaction;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface TransactionRepository extends ReactiveMongoRepository<Transaction, String> {

    Mono<Transaction> findByIdAndState(String id, TransactionState state);
}
