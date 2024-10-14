package base.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "INVC")
public class InvoiceEntity {

    @Id
    @GeneratedValue
    @Column(name = "INVC_ID")
    private Long invoiceId;

    @Column(name = "CSTMR_NM")
    private String customerName;

    @Column(name = "PRDCT_NM")
    private String productName;

    @Column(name = "AMT")
    private double amount;

    private boolean isValid;

    @Column(name = "PRCHSE_DT")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date purchaseDate;


}
