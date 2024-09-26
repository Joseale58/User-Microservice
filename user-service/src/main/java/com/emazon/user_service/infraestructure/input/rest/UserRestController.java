package com.emazon.user_service.infraestructure.input.rest;


import com.emazon.user_service.application.dto.RegisterDtoRequest;
import com.emazon.user_service.application.handler.IUserHandler;
import com.emazon.user_service.utils.Constants;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserRestController {

    private final IUserHandler userHandler;

    @Operation(summary = "Register a new user", description = "Register a new user Warehouse assitant (done thru admin privileges")
    @PostMapping("/warehouse_aux")
    public ResponseEntity<String> saveAuxbodega(@RequestBody RegisterDtoRequest registerDtoRequest) {
        userHandler.registerWarehouseAux(registerDtoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(Constants.SAVE_USER_SUCCESS);
    }

    @Operation(summary = "Register a new user", description = "Register a new user client (done by himself)")
    @PostMapping("/client")
    public ResponseEntity<String> saveClient(@RequestBody RegisterDtoRequest registerDtoRequest) {
        userHandler.registerClient(registerDtoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(Constants.SAVE_USER_SUCCESS);
    }


}
