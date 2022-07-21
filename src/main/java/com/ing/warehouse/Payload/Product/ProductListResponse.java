package com.ing.warehouse.Payload.Product;

import com.ing.warehouse.Entity.Product;
import com.ing.warehouse.Payload.Common.ResponseHeader;

import java.util.List;

public class ProductListResponse {

    private ResponseHeader responseHeader;

    public ResponseHeader getResponseHeader() {
        if (responseHeader == null) {
            responseHeader = new ResponseHeader();
        }
        return responseHeader;
    }

    public void setResponseHeader(ResponseHeader responseHeader) {
        this.responseHeader = responseHeader;
    }

    private List<Product> productList;

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
