package base.controller;

import base.model.InvoiceModel;
import base.service.InvoiceManagementService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/invoice")
@RestController
@RequiredArgsConstructor
public class InvoiceController {

    @NonNull
    private InvoiceManagementService invoiceService;

    @GetMapping("/all")
    public ResponseEntity<List<InvoiceModel>> fetchAllInvoices() {
        return new ResponseEntity<>(invoiceService.fetchInvoiceList(), HttpStatus.OK);
    }

    @GetMapping("/valid")
    public ResponseEntity<List<InvoiceModel>> fetchValidInvoices() {
        return new ResponseEntity<>(invoiceService.fetchValidInvoiceList(), HttpStatus.OK);
    }

    @GetMapping("/invalid")
    public ResponseEntity<List<InvoiceModel>> fetchInvalidInvoices() {
        return new ResponseEntity<>(invoiceService.fetchInvalidInvoiceList(), HttpStatus.OK);
    }
}
