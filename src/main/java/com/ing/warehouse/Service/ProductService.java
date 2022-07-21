package com.ing.warehouse.Service;

import com.ing.warehouse.Entity.Product;
import com.ing.warehouse.Payload.Common.RequestHeader;
import com.ing.warehouse.Payload.Common.ResponseHeader;
import com.ing.warehouse.Payload.Product.*;
import com.ing.warehouse.Repository.BoxRepository;
import com.ing.warehouse.Repository.ProductRepository;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    private BoxRepository boxRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, BoxRepository boxRepository) {
        this.productRepository = productRepository;
        this.boxRepository = boxRepository;
    }

    public List<Product> getList() {
        return productRepository.findAllProducts();
    }

    public ProductResponse create(ProductRequest request) {
        var response = new ProductResponse();
        var responseHeader = new ResponseHeader();

        var productOptional = productRepository.findByProductName(request.getName());

        if (productOptional.isPresent()) {
            responseHeader.setSuccess(false);
            responseHeader.setDetail("Product name already exists");
        } else {
            try {
                var product = new Product();
                product.setName(request.getName());
                product.setDesc(request.getDesc());
                product.setUpdated_at(OffsetDateTime.now());
                if (request.getRequestHeader() != null && StringUtils.isNotBlank(request.getRequestHeader().getUsername()))
                    product.setUpdated_by(request.getRequestHeader().getUsername());
                var result = productRepository.save(product);
                response.setProduct(result);
            } catch (Exception e) {
                responseHeader.setSuccess(false);
            }
        }

        response.setResponseHeader(responseHeader);
        return response;
    }

    public ProductResponse remove(long id, RequestHeader header) {
        var response = new ProductResponse();
        var productOptional = productRepository.findByProductId(id);
        if (productOptional.isPresent()) {
            var product = productOptional.get();
            if (header != null && StringUtils.isNotBlank(header.getUsername())) {
                product.setDeleted_by(header.getUsername());
                product.setDeleted_at(OffsetDateTime.now());
            }

            product.setDeleted(true);
            productRepository.save(product);
            response.getResponseHeader().setDetail("Deleted");
        } else {
            response.getResponseHeader().setSuccess(false);
            response.getResponseHeader().setDetail("Given id doesnt exist");
        }

        return response;
    }

    public ProductResponse update(ProductRequest request) {
        var response = new ProductResponse();
        var productOptional = productRepository.findByProductId(request.getId());
        if (productOptional.isPresent()) {
            var product = productOptional.get();
            if (request.getRequestHeader() != null && StringUtils.isNotBlank(request.getRequestHeader().getUsername())) {
                product.setUpdated_by(request.getRequestHeader().getUsername());
                product.setUpdated_at(OffsetDateTime.now());
            }
            product.setName(request.getName());
            product.setDesc(request.getDesc());

            productRepository.save(product);
            response.getResponseHeader().setDetail("Updated");
        } else {
            response.getResponseHeader().setSuccess(false);
            response.getResponseHeader().setDetail("Given id doesnt exist");
        }

        return response;
    }

    public ProductAssignResponse assign(ProductAssignRequest request) {
        var response = new ProductAssignResponse();
        var productOptional = productRepository.findByProductId(request.getProduct_id());
        var boxOptional = boxRepository.findByBoxId(request.getBox_id());
        if (productOptional.isPresent() && boxOptional.isPresent()) {
            var product = productOptional.get();
            var box = boxOptional.get();
            product.setBox(box);

            productRepository.save(product);
            response.getResponseHeader().setDetail("Assigned");
        } else {
            response.getResponseHeader().setSuccess(false);
            response.getResponseHeader().setDetail("Given box id or product id doesnt exist");
        }

        return response;

    }

    public ProductListResponse search(ProductRequest request) {
        var productListResponse = new ProductListResponse();

        if (StringUtils.isNotBlank(request.getName())) {
            var productList = productRepository.searchProductName(request.getName());
            productListResponse.setProductList(productList);
        } else {
            productListResponse.getResponseHeader().setSuccess(false);
            productListResponse.getResponseHeader().setDetail("You should enter search value");
        }

        return productListResponse;


    }
}
