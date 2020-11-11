package br.com.fiap.batchtransacao.writer;

import br.com.fiap.batchtransacao.model.Transactions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class Writer {

    Logger logger = LoggerFactory.getLogger(Writer.class);

    @Bean
    public MongoItemWriter<Transactions> itemWriter(MongoTemplate mongoTemplate) {

        logger.info("Persistindo os dados do arquivo na colletion {}", mongoTemplate.getCollectionName(Transactions.class));

        return new MongoItemWriterBuilder<Transactions>()
                .template(mongoTemplate)
                .build();
    }
}
