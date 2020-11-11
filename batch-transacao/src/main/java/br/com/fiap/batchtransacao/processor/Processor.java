package br.com.fiap.batchtransacao.processor;

import br.com.fiap.batchtransacao.model.Transactions;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Processor {

    @Bean
    public ItemProcessor<Transactions, Transactions> itemProcessor() {
        return (transacao) -> transacao;
    }
}
