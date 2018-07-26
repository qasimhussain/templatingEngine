package com.engine.template.models.request;

import java.util.List;
import java.util.Map;

public class InvoicePayload {


    String invoiceLogoImageURL;
    String invoicePayedAmount;
    String invoiceCompanyName;
    String invoiceCustomerName;
    String invoiceNumber;
    String invoiceDate;
    String invoiceTotalAmount;
    List<Map<String,String>> invoiceBillItems;
    String invoiceCompanyAddress;

    public String getInvoicePayedAmount() {
        return invoicePayedAmount;
    }

    public void setInvoicePayedAmount(String invoicePayedAmount) {
        this.invoicePayedAmount = invoicePayedAmount;
    }

    public String getInvoiceCompanyName() {
        return invoiceCompanyName;
    }

    public void setInvoiceCompanyName(String invoiceCompanyName) {
        this.invoiceCompanyName = invoiceCompanyName;
    }

    public String getInvoiceCustomerName() {
        return invoiceCustomerName;
    }

    public void setInvoiceCustomerName(String invoiceCustomerName) {
        this.invoiceCustomerName = invoiceCustomerName;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getInvoiceTotalAmount() {
        return invoiceTotalAmount;
    }

    public void setInvoiceTotalAmount(String invoiceTotalAmount) {
        this.invoiceTotalAmount = invoiceTotalAmount;
    }

    public List <Map <String, String>> getInvoiceBillItems() {
        return invoiceBillItems;
    }

    public void setInvoiceBillItems(List <Map <String, String>> invoiceBillItems) {
        this.invoiceBillItems = invoiceBillItems;
    }

    public String getInvoiceLogoImageURL() {
        return invoiceLogoImageURL;
    }

    public void setInvoiceLogoImageURL(String invoiceLogoImageURL) {
        this.invoiceLogoImageURL = invoiceLogoImageURL;
    }

    public String getInvoiceCompanyAddress() {
        return invoiceCompanyAddress;
    }

    public void setInvoiceCompanyAddress(String invoiceCompanyAddress) {
        this.invoiceCompanyAddress = invoiceCompanyAddress;
    }

}
