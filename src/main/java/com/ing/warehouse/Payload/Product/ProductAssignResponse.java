package com.ing.warehouse.Payload.Product;

import com.ing.warehouse.Payload.Common.ResponseHeader;

public class ProductAssignResponse {

    private ResponseHeader responseHeader;

    public ResponseHeader getResponseHeader() {
        if (responseHeader == null) {
            responseHeader = new ResponseHeader();
        }
        return responseHeader;
    }

}
