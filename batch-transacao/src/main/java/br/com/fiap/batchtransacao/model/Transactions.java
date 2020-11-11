package br.com.fiap.batchtransacao.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Document(collection = "transacao")
public class Transactions {

    @Id
    private String id;
    private String numeroCartao;
    private String data;
    private String local;
    private BigDecimal valor;
}

