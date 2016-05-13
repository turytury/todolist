package todolist;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiBodyObject;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.annotation.ApiResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/todolist", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(description = "To do list controller", name = "Todolist services")
public class TodolistController {

    @Autowired
    private TodolistRepository repository;

    @ApiMethod
    @RequestMapping(method = RequestMethod.GET)
    public @ApiResponseObject ResponseEntity list() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @ApiMethod
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ApiResponseObject ResponseEntity findById(@ApiPathParam(name = "id") @PathVariable String id) {
        return new ResponseEntity<>(repository.findOne(id), HttpStatus.OK);
    }

    @ApiMethod
    @RequestMapping(method = RequestMethod.POST)
    public @ApiResponseObject ResponseEntity add(@ApiBodyObject @RequestBody TodoModel todoModel) {
        repository.save(todoModel);
        return new ResponseEntity("Created successfully", HttpStatus.OK);
    }

    @ApiMethod
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public @ApiResponseObject ResponseEntity edit(@ApiPathParam(name = "id") @PathVariable String id,
                                                  @ApiBodyObject @RequestBody TodoModel todoModel) {
        if(!repository.exists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        todoModel.setId(id);
        return new ResponseEntity<>(repository.save(todoModel), HttpStatus.OK);
    }

    @ApiMethod
    @RequestMapping(value = "/{id}/status/{status}", method = RequestMethod.PUT)
    public @ApiResponseObject ResponseEntity setStatus(@ApiPathParam(name = "id") @PathVariable String id,
                                                       @ApiPathParam(name = "status") @PathVariable String status) {
        TodoModel todoModel = repository.findOne(id);
        if(todoModel == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        todoModel.setStatus(status);
        return new ResponseEntity<>(repository.save(todoModel), HttpStatus.OK);
    }

    @ApiMethod
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public @ApiResponseObject ResponseEntity delete(@ApiPathParam(name = "id") @PathVariable String id) {
        if(!repository.exists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        repository.delete(id);
        return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
    }
}