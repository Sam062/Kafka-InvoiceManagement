package base.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceModel {
    private Long invoiceId;
    private String customerName, productName;
    private double amount;
    private boolean isValid;
    private Date purchaseDate;
}
