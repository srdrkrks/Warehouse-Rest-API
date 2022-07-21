package com.ing.warehouse.Payload.Product;

import com.ing.warehouse.Payload.Common.RequestHeader;

public class ProductDeleteRequest {
    private RequestHeader requestHeader;

    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public RequestHeader getRequestHeader() {
        if (requestHeader == null) {
            requestHeader = new RequestHeader();
        }
        return requestHeader;
    }

    public void setRequestHeader(RequestHeader requestHeader) {
        this.requestHeader = requestHeader;
    }
}
