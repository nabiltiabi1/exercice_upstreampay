package com.upstreampay.paiements_exercice.mapper;

import com.upstreampay.paiements_exercice.dto.TransactionDto;
import com.upstreampay.paiements_exercice.entity.Transaction;
import org.springframework.beans.BeanUtils;

public class TransactionMapper {

    public static TransactionDto entityToDto(Transaction transaction){
        TransactionDto transactionDto = new TransactionDto();
        BeanUtils.copyProperties(transaction, transactionDto);

        return transactionDto;
    }

    public static Transaction dtoToEntity(TransactionDto transactionDto){
        Transaction transaction = new Transaction();
        BeanUtils.copyProperties(transactionDto, transaction);

        return transaction;
    }
}
