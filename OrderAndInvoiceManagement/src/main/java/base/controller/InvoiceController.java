package base.controller;

import base.model.InvoiceModel;
import base.service.InvoiceManagementService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RequestMapping("/invoice")
@RestController
@RequiredArgsConstructor
public class InvoiceController {

    @NonNull
    private InvoiceManagementService invoiceService;

    @GetMapping("/{invoiceId}")
    public ResponseEntity<InvoiceModel> findInvoiceById(@PathVariable Long invoiceId) {
        return new ResponseEntity<>(invoiceService.findInvoiceById(invoiceId), OK);
    }

    @GetMapping("/validateInvoice/{invoiceId}")
    public ResponseEntity<InvoiceModel> validateInvoice(@PathVariable Long invoiceId) {
        log.info("Request received in INV-MANAGER for invoice validation : " + invoiceId);

        InvoiceModel updatedInvoice = invoiceService.validateAndUpdateInvoiceFlag(invoiceId);

        return new ResponseEntity<>(updatedInvoice, updatedInvoice != null ? OK : NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<InvoiceModel>> fetchAllInvoices() {
        return new ResponseEntity<>(invoiceService.fetchInvoiceList(), OK);
    }

    @GetMapping("/valid")
    public ResponseEntity<List<InvoiceModel>> fetchValidInvoices() {
        return new ResponseEntity<>(invoiceService.fetchValidInvoiceList(), OK);
    }

    @GetMapping("/invalid")
    public ResponseEntity<List<InvoiceModel>> fetchInvalidInvoices() {
        return new ResponseEntity<>(invoiceService.fetchInvalidInvoiceList(), OK);
    }
}
