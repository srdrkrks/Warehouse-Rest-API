package com.ing.warehouse.Service;

import com.ing.warehouse.Entity.Box;
import com.ing.warehouse.Payload.Box.BoxRequest;
import com.ing.warehouse.Payload.Box.BoxResponse;
import com.ing.warehouse.Payload.Common.RequestHeader;
import com.ing.warehouse.Payload.Common.ResponseHeader;
import com.ing.warehouse.Payload.Product.ProductResponse;
import com.ing.warehouse.Repository.BoxRepository;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class BoxService {

    private BoxRepository boxRepository;

    @Autowired
    public BoxService(BoxRepository boxRepository) {
        this.boxRepository = boxRepository;
    }

    public List<Box> getList() {
        return boxRepository.findAllBoxes();
    }

    public BoxResponse create(BoxRequest request) {
        var response = new BoxResponse();
        var responseHeader = new ResponseHeader();

        var boxOptional = boxRepository.findByBoxName(request.getName());

        if (boxOptional.isPresent()) {
            responseHeader.setSuccess(false);
            responseHeader.setDetail("Box name already exists");
        } else {
            try {
                var box = new Box();
                box.setName(request.getName());
                box.setDesc(request.getDesc());
                box.setLocation(request.getLocation());
                box.setUpdated_at(OffsetDateTime.now());
                if (request.getRequestHeader() != null && StringUtils.isNotBlank(request.getRequestHeader().getUsername()))
                    box.setUpdated_by(request.getRequestHeader().getUsername());
                var result = boxRepository.save(box);
                response.setBox(result);
            } catch (Exception e) {
                responseHeader.setSuccess(false);
            }
        }

        response.setResponseHeader(responseHeader);
        return response;
    }

    public BoxResponse remove(long id, RequestHeader header) {
        var response = new BoxResponse();
        var boxOptional = boxRepository.findByBoxId(id);
        if (boxOptional.isPresent()) {
            var product = boxOptional.get();
            if (header != null && StringUtils.isNotBlank(header.getUsername())) {
                product.setDeleted_by(header.getUsername());
                product.setDeleted_at(OffsetDateTime.now());
            }

            product.setDeleted(true);
            boxRepository.save(product);
            response.getResponseHeader().setDetail("Deleted");
        } else {
            response.getResponseHeader().setSuccess(false);
            response.getResponseHeader().setDetail("Given id doesnt exist");
        }

        return response;
    }

    public BoxResponse update(BoxRequest request) {
        var response = new BoxResponse();
        var boxOptional = boxRepository.findByBoxId(request.getId());
        if (boxOptional.isPresent()) {
            var box = boxOptional.get();
            if (request.getRequestHeader() != null && StringUtils.isNotBlank(request.getRequestHeader().getUsername())) {
                box.setUpdated_by(request.getRequestHeader().getUsername());
                box.setUpdated_at(OffsetDateTime.now());
            }
            box.setName(request.getName());
            box.setDesc(request.getDesc());
            box.setLocation(request.getLocation());


            boxRepository.save(box);
            response.getResponseHeader().setDetail("Updated");
        } else {
            response.getResponseHeader().setSuccess(false);
            response.getResponseHeader().setDetail("Given id doesnt exist");
        }

        return response;
    }
}
