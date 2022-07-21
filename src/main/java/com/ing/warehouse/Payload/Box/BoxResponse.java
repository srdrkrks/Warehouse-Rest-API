package com.ing.warehouse.Payload.Box;

import com.ing.warehouse.Entity.Box;
import com.ing.warehouse.Payload.Common.ResponseHeader;

public class BoxResponse {
    private ResponseHeader responseHeader;
    private Box box;

    public ResponseHeader getResponseHeader() {
        if (responseHeader == null) {
            responseHeader = new ResponseHeader();
        }
        return responseHeader;
    }

    public void setResponseHeader(ResponseHeader responseHeader) {
        this.responseHeader = responseHeader;
    }

    public Box getBox() {
        return box;
    }

    public void setBox(Box box) {
        this.box = box;
    }
}
