package com.riwi.prueba_desempeno.api.controllers;

import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.prueba_desempeno.api.dto.errors.ErrorResp;
import com.riwi.prueba_desempeno.api.dto.request.UserRequest;
import com.riwi.prueba_desempeno.api.dto.response.UserReponse;
import com.riwi.prueba_desempeno.api.dto.response.UserResponseSurveys;
import com.riwi.prueba_desempeno.infraestructure.services.UserService;
import com.riwi.prueba_desempeno.util.enums.SortType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/users")
@AllArgsConstructor
public class UserController {

  private final UserService userService;

  // Colocar una descripcion individual
  @Operation(summary = "Get all paginated users")

  @GetMapping
  public ResponseEntity<Page<UserReponse>> getAll(
      @RequestParam(defaultValue = "1") int page,
      @RequestParam(defaultValue = "10") int size,
      @RequestHeader(required = false) SortType sortType) {
    if (Objects.isNull(sortType))
      sortType = SortType.NONE;

    return ResponseEntity.ok(this.userService.getAll(page - 1, size, sortType));
  }

  @Operation(summary = "Get user by ID")
  @ApiResponse(responseCode = "400", description = "Cuando el usuario no es encontrado", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResp.class))
  })
  @GetMapping("/{id}")
  public ResponseEntity<UserReponse> getById(@PathVariable Long id) {
    return ResponseEntity.ok(userService.getById(id));
  }

  @Operation(summary = "Get an user with surveys by its ID number")
  @ApiResponse(responseCode = "400", description = "When the ID is not found", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResp.class))
  })

  @GetMapping("/{id}/surveys")
  public ResponseEntity<UserResponseSurveys> getbyIdWithSurveys(@PathVariable Long id) {
    return ResponseEntity.ok(userService.getUsersSurveys(id));
  }

  @Operation(summary = "Create an user")
  @ApiResponse(responseCode = "400", description = "When the request is not valid", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResp.class))
  })
  @PostMapping
  public ResponseEntity<UserReponse> createUser(@Validated @RequestBody UserRequest userReq) {
    return ResponseEntity.ok(userService.create(userReq));
  }

  @Operation(summary = "Update an user by its ID number")
  @ApiResponse(responseCode = "400", description = "When the request is not valid", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResp.class))
  })
  @PutMapping("{id}")
  public ResponseEntity<UserReponse> updateUser(@Validated @RequestBody UserRequest userReq, @PathVariable Long id) {
    return ResponseEntity.ok(userService.update(userReq, id));
  }

  @Operation(summary = "Delete an user by its ID number")
  @ApiResponse(responseCode = "204", description = "User deleted successfully")
  @ApiResponse(responseCode = "400", description = "When the ID is not found", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResp.class))
  })
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    userService.delete(id);
    return ResponseEntity.noContent().build();
  }

}
