package com.ing.warehouse.Controller;

import com.ing.warehouse.Payload.Box.BoxListResponse;
import com.ing.warehouse.Payload.Box.BoxRequest;
import com.ing.warehouse.Payload.Box.BoxResponse;
import com.ing.warehouse.Service.BoxService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/box")
@Tag(name="Box Capability")
public class BoxController {

    private BoxService boxService;

    @Autowired
    public BoxController(BoxService boxService) {
        this.boxService = boxService;
    }

    @GetMapping("/list/retrieve/v1")
    public BoxListResponse getList() {
        var response = new BoxListResponse();
        try {
            var boxList = boxService.getList();
            response.setBoxList(boxList);
        } catch (Exception ex) {
            response.getResponseHeader().setSuccess(false);
        }
        return response;
    }

    @PostMapping("/create/v1")
    public BoxResponse create(@Valid @RequestBody BoxRequest request) {
        var serviceResponse = boxService.create(request);
        return serviceResponse;
    }

    @PutMapping("/update/v1")
    public BoxResponse update(@Valid @RequestBody BoxRequest request) {
        var serviceResponse = new BoxResponse();
        if (request.getId() == 0) {
            serviceResponse.getResponseHeader().setSuccess(false);
            serviceResponse.getResponseHeader().setDetail("You must give an id of box");
        } else {
            serviceResponse = boxService.update(request);
        }
        return serviceResponse;
    }

    @DeleteMapping("/remove/v1")
    public BoxResponse remove(@RequestBody BoxRequest request) {
        var boxResponse = new BoxResponse();
        var id = request.getId();
        if (id == 0) {
            boxResponse.getResponseHeader().setSuccess(false);
            boxResponse.getResponseHeader().setDetail("You must give an id of box");
        } else {
            boxResponse = boxService.remove(id, request.getRequestHeader());
        }
        return boxResponse;
    }
}
