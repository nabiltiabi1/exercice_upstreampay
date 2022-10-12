package com.upstreampay.paiements_exercice.dto;

import com.upstreampay.paiements_exercice.config.TransactionState;
import com.upstreampay.paiements_exercice.entity.OrderLine;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class TransactionDto {

    private String id;
    private BigDecimal total;
    private String paymentType;
    private TransactionState state;
    private List<OrderLine> order;

    public TransactionDto() {
        state = TransactionState.NEW;
    }
}
