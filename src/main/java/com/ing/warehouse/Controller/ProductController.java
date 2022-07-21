package com.ing.warehouse.Controller;

import com.ing.warehouse.Payload.Product.*;
import com.ing.warehouse.Service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/product")
@Tag(name="Product Capability")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/list/retrieve/v1")
    public ProductListResponse getList() {
        var response = new ProductListResponse();
        try {
            var productList = productService.getList();
            response.setProductList(productList);
        } catch (Exception ex) {
            response.getResponseHeader().setSuccess(false);
        }
        return response;
    }

    @PostMapping("/create/v1")
    public ProductResponse create(@Valid @RequestBody ProductRequest request) {
        var serviceResponse = productService.create(request);
        return serviceResponse;
    }

    @PutMapping("/update/v1")
    public ProductResponse update(@Valid @RequestBody ProductRequest request) {
        var serviceResponse = new ProductResponse();
        if (request.getId() == 0) {
            serviceResponse.getResponseHeader().setSuccess(false);
            serviceResponse.getResponseHeader().setDetail("You must give an id of product");
        } else {
            serviceResponse = productService.update(request);
        }
        return serviceResponse;
    }

    @DeleteMapping("/remove/v1")
    public ProductResponse remove(@RequestBody ProductRequest request) {
        ProductResponse productResponse = new ProductResponse();
        var id = request.getId();
        if (id == 0) {
            productResponse.getResponseHeader().setSuccess(false);
            productResponse.getResponseHeader().setDetail("You must give an id of product");
        } else {
            productResponse = productService.remove(id, request.getRequestHeader());
        }
        return productResponse;
    }

    @PutMapping("/assign/v1")
    public ProductAssignResponse assign(@Valid @RequestBody ProductAssignRequest request) {
        var serviceResponse = new ProductAssignResponse();
        if (request.getProduct_id() == 0 || request.getBox_id() == 0) {
            serviceResponse.getResponseHeader().setSuccess(false);
            serviceResponse.getResponseHeader().setDetail("You must give an id of product");
        } else {
            serviceResponse = productService.assign(request);
        }
        return serviceResponse;
    }

    @PostMapping("/search/v1")
    public ProductListResponse search(@Valid @RequestBody ProductRequest request) {
        var response = new ProductListResponse();
        try {
            response = productService.search(request);
        } catch (Exception ex) {
            response.getResponseHeader().setSuccess(false);
            response.getResponseHeader().setDetail("Technical Error");
        }
        return response;
    }


}
