package com.upstreampay.paiements_exercice.service;

import com.upstreampay.paiements_exercice.controller.TransactionController;
import com.upstreampay.paiements_exercice.dto.TransactionDto;
import com.upstreampay.paiements_exercice.entity.OrderLine;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@WebFluxTest(TransactionController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TransactionServiceTest {

    @MockBean
    private TransactionService transactionService;


    @BeforeAll
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void shouldAddTransactionTest() {
        final TransactionDto transactionDto= new TransactionDto();
        List<OrderLine> orderLines = new ArrayList<>();
        orderLines.add(new OrderLine("gant de ski",4, BigDecimal.valueOf(10)));
        orderLines.add(new OrderLine("bonnet en laine",1, BigDecimal.valueOf(14.80)));
        transactionDto.setTotal(BigDecimal.valueOf(54.80));
        transactionDto.setPaymentType("card");
        transactionDto.setOrder(orderLines);
        when(transactionService.save(transactionDto)).thenReturn(Mono.just(transactionDto));

        StepVerifier.create(transactionService.save(transactionDto))
                .expectNext(transactionDto)
                .verifyComplete();
    }
}
