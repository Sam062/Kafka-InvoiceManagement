package base.service;

import base.model.InvoiceModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class InvoiceConsumerService {
    private static final String TOPIC_NAME = "MINI_PRO_INVOICES";

    @Autowired
    private InvoiceManagementService invoiceManagementService;

    @KafkaListener(topics = TOPIC_NAME, groupId = "INV")
    public void consume(String msg) throws JsonProcessingException {

        /*
        {
            "invoiceId":1,
            "customerName":"SHIVAM MISHRA",
            "productName":"HEADSET",
            "amount":124.3,
            "purchaseDate":1728751945244,
            "valid":false
         }
         */
        log.info("Invoice message received: {}", msg);

        InvoiceModel invoiceModel = new ObjectMapper().readValue(msg, InvoiceModel.class);

        InvoiceModel updatedInvoiceModel = invoiceManagementService.validateInvoiceById(invoiceModel.getInvoiceId());

        log.info("Invoice validated : {}", updatedInvoiceModel);


    }


}
