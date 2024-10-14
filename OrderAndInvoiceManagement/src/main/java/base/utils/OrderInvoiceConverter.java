package base.utils;

import base.model.InvoiceModel;
import base.model.Order;

public class OrderInvoiceConverter {

    public static InvoiceModel orderToInvoice(Order order) {
        return new InvoiceModel(
                null,
                order.getCustomerName(),
                order.getProductName(),
                order.getAmount(),
                false,
                order.getOrderDate());
    }
}
