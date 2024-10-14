package base.service;

import base.entity.InvoiceEntity;
import base.model.InvoiceModel;
import base.repo.InvoiceRepo;
import base.utils.InvoiceEntityConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static base.utils.InvoiceEntityConverter.invoiceEntityToModel;

@Slf4j
@Service
public class InvoiceManagementService {

    @Autowired
    private InvoiceRepo invoiceRepo;


    public InvoiceModel validateInvoiceById(Long invoiceId) {
        log.info("validating invoice: {}", invoiceId);

        //TODO: Bug fix to get value here
        Optional<InvoiceEntity> invoiceEntityOptional = invoiceRepo.findById(invoiceId);

        log.info("Invoice by ID: {}", invoiceEntityOptional.orElse(null));
        InvoiceModel invoiceModel = invoiceEntityOptional
                .map(InvoiceEntityConverter::invoiceEntityToModel)
                .orElse(null);
        log.info("Conversion from entity: {}", invoiceModel);

        if (isValidInvoice(invoiceModel)) {
            log.info("Valid Invoice, updating DB");
            InvoiceEntity invoiceEntity = invoiceEntityOptional.get();
            invoiceEntity.setValid(true);

            InvoiceEntity updatedInvoiceEntity = invoiceRepo.save(invoiceEntity);
            log.info("Invoice updated: {}", updatedInvoiceEntity);
            return invoiceEntityToModel(updatedInvoiceEntity);
        }
        log.info("Invalid invoice");
        return null;
    }

    private boolean isValidInvoice(InvoiceModel invoiceModel) {
        return invoiceModel != null &&
                invoiceModel.getInvoiceId() != null &&
                invoiceModel.getCustomerName() != null &&
                invoiceModel.getProductName() != null &&
                invoiceModel.getAmount() <= 0.0 &&
                invoiceModel.getPurchaseDate() != null;
    }


}
