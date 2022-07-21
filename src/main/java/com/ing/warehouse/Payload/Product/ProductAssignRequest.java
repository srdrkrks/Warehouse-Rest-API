package com.ing.warehouse.Payload.Product;

import com.ing.warehouse.Payload.Common.RequestHeader;

public class ProductAssignRequest {

    private RequestHeader requestHeader;

    private long product_id;
    private long box_id;

    public RequestHeader getRequestHeader() {
        if (requestHeader == null) {
            requestHeader = new RequestHeader();
        }
        return requestHeader;
    }

    public void setRequestHeader(RequestHeader requestHeader) {
        this.requestHeader = requestHeader;
    }

    public long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(long product_id) {
        this.product_id = product_id;
    }

    public long getBox_id() {
        return box_id;
    }

    public void setBox_id(long box_id) {
        this.box_id = box_id;
    }
}
