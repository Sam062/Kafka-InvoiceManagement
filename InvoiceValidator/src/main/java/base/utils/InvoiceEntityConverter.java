package base.utils;

import base.entity.InvoiceEntity;
import base.model.InvoiceModel;

public class InvoiceEntityConverter {

    public static InvoiceModel invoiceEntityToModel(InvoiceEntity entity) {
        return new InvoiceModel(
                entity.getInvoiceId(),
                entity.getCustomerName(),
                entity.getProductName(),
                entity.getAmount(),
                entity.isValid(),
                entity.getPurchaseDate());
    }

    public static InvoiceEntity invoiceModelToEntity(InvoiceModel model) {
        return new InvoiceEntity(
                model.getInvoiceId(),
                model.getCustomerName(),
                model.getProductName(),
                model.getAmount(),
                model.isValid(),
                model.getPurchaseDate());
    }

}
