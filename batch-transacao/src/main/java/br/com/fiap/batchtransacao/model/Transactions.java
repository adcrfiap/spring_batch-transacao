package br.com.fiap.batchtransacao.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@Document(collection = "transactions")
public class Transactions {

    @Id
    private String id;

    private String cardNumber;

    @CreatedDate
    private Date date;

    private String locale;

    private BigDecimal value;

}

