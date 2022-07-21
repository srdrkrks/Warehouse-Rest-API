package com.ing.warehouse.Payload.Product;

import com.ing.warehouse.Entity.Product;
import com.ing.warehouse.Payload.Common.ResponseHeader;


public class ProductResponse {

    private ResponseHeader responseHeader;
    private Product product;


    public ResponseHeader getResponseHeader() {
        if (responseHeader == null) {
            responseHeader = new ResponseHeader();
        }
        return responseHeader;
    }

    public void setResponseHeader(ResponseHeader responseHeader) {
        this.responseHeader = responseHeader;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
