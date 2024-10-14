package base.controller;

import base.model.InvoiceModel;
import base.model.Order;
import base.service.InvoiceManagementService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

@RequestMapping("/order")
@RestController
@Slf4j
public class OrderController {

    @Autowired
    private InvoiceManagementService invoiceService;

    @PostMapping("/save")
    public ResponseEntity<InvoiceModel> saveOrderAndGenerateInvoice(@RequestBody Order order) throws JsonProcessingException {
        log.info("Order received {}", order);

        InvoiceModel invoiceModel = invoiceService.publishInvoice(order);

        if (invoiceModel != null && invoiceModel.getInvoiceId() != null)
            return new ResponseEntity<>(invoiceModel, OK);

        return new ResponseEntity<>(invoiceModel, INTERNAL_SERVER_ERROR);
    }

}
