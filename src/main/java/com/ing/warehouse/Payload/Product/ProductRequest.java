package com.ing.warehouse.Payload.Product;


import com.ing.warehouse.Payload.Common.RequestHeader;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProductRequest {

    @NotNull(message = "RequestHeader is mandatory")
    private RequestHeader requestHeader;
    private long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    private String desc;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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
