package base.service;

import base.model.InvoiceModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Service
public class InvoiceManagementService {

    @Autowired
    private WebClient webClient;

    public InvoiceModel validateInvoiceById(Long invoiceId) {
        final String URI = "/invoice/validateInvoice/" + invoiceId;

        log.info("Calling Invoice Validator for ID: {} and URI : {}", invoiceId, URI);

        InvoiceModel invoiceModel =
                webClient
                        .get()
                        .uri(URI)
                        .retrieve()
                        .bodyToMono(InvoiceModel.class)
                        .block();

        log.info("Response from invoice validator: {}", invoiceModel);
        return invoiceModel;
    }
}
