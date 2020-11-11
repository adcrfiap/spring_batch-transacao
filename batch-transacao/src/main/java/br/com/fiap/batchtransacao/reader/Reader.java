package br.com.fiap.batchtransacao.reader;

import br.com.fiap.batchtransacao.model.Transactions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class Reader {
    Logger logger = LoggerFactory.getLogger(Reader.class);
    @Bean
    public FlatFileItemReader<Transactions> itemReader(@Value("${input.file}") Resource resource,
                                                       FixedLengthTokenizer fixedLengthTokenizer) {
        logger.info("Leitura do arquivo {}", resource.getFilename());

        return new FlatFileItemReaderBuilder<Transactions>()
                .name("Read File")
                .resource(resource)
                .targetType(Transactions.class)
                .lineTokenizer(fixedLengthTokenizer)
                .build();
    }
}
