package com.ing.warehouse.Payload.Box;

import com.ing.warehouse.Entity.Box;
import com.ing.warehouse.Payload.Common.ResponseHeader;

import java.util.List;

public class BoxListResponse {

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

    private List<Box> boxList;

    public List<Box> getBoxList() {
        return boxList;
    }

    public void setBoxList(List<Box> boxList) {
        this.boxList = boxList;
    }
}
