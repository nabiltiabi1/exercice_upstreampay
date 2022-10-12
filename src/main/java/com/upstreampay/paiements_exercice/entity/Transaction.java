package com.upstreampay.paiements_exercice.entity;

import com.upstreampay.paiements_exercice.config.TransactionState;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@Document("transactions")
public class Transaction {
    @Id
    private String id;
    private BigDecimal total;
    private String paymentType;
    private TransactionState state;
    private List<OrderLine> order;

    public Transaction() {
        state = TransactionState.NEW;
    }
}
