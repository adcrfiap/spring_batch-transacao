package br.com.fiap.batchtransacao;

import br.com.fiap.batchtransacao.model.Transactions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@EnableBatchProcessing
public class BatchTransacaoApplication {

	Logger logger = LoggerFactory.getLogger(BatchTransacaoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BatchTransacaoApplication.class, args);
	}

	@Bean
	public FixedLengthTokenizer fixedLengthTokenizer() {
		FixedLengthTokenizer tokenizer = new FixedLengthTokenizer();

		tokenizer.setNames("cardNumber", "value", "locale");
		tokenizer.setColumns(
				new Range(1, 13),
				new Range(14, 28),
				new Range(29, 38)
		);

		return tokenizer;
	}

	@Bean
	public Step step(StepBuilderFactory stepBuilderFactory,
					 ItemReader<Transactions> itemReader,
					 ItemProcessor<Transactions, Transactions> itemProcessor,
					 ItemWriter<Transactions> itemWriter) {
		return stepBuilderFactory.get("Step Chunk - Processar arquivo")
				.<Transactions, Transactions>chunk(100)
				.reader(itemReader)
				.processor(itemProcessor)
				.writer(itemWriter)
				.allowStartIfComplete(true)
				.build();
	}

	@Bean
	public Job job(JobBuilderFactory jobBuilderFactory, Step step) {
		return jobBuilderFactory.get("Job - Processar arquivo trasacao")
				.start(step)
				.build();
	}

}
