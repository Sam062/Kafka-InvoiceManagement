package base.service;

import base.entity.InvoiceEntity;
import base.model.InvoiceModel;
import base.model.Order;
import base.repo.InvoiceRepo;
import base.utils.InvoiceEntityConverter;
import base.utils.OrderInvoiceConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class InvoiceManagementService {

    private final String TOPIC_NAME = "MINI_PRO_INVOICES";

    @Autowired
    private InvoiceRepo invoiceRepo;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    public List<InvoiceModel> fetchValidInvoiceList() {
        return invoiceRepo.findByIsValid(true)
                .stream()
                .map(InvoiceEntityConverter::invoiceEntityToModel)
                .toList();
    }

    public List<InvoiceModel> fetchInvalidInvoiceList() {
        return invoiceRepo.findByIsValid(false)
                .stream()
                .map(InvoiceEntityConverter::invoiceEntityToModel)
                .toList();
    }

    public List<InvoiceModel> fetchInvoiceList() {
        return invoiceRepo.findAll()
                .stream()
                .map(InvoiceEntityConverter::invoiceEntityToModel)
                .toList();
    }

    public InvoiceModel publishInvoice(Order orderModel) throws JsonProcessingException {
        //      1. Convert order into INVOICE,
        InvoiceModel invoice = OrderInvoiceConverter.orderToInvoice(orderModel);
        InvoiceEntity invoiceEntity = invoiceRepo.save(InvoiceEntityConverter.invoiceModelToEntity(invoice));
        invoice = InvoiceEntityConverter.invoiceEntityToModel(invoiceEntity);

        //      2. PUBLISH INVOICE TO KAFKA,
        //      3. Validator service will validate and store accordingly
        kafkaTemplate.send(TOPIC_NAME, new ObjectMapper().writeValueAsString(invoice));
        log.info("Publishing invoice : {}, to topic: {}", invoice, TOPIC_NAME);
        return invoice;
    }


}
