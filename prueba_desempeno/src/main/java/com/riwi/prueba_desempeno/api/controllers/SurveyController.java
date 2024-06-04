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
import com.riwi.prueba_desempeno.api.dto.request.SurveyRequest;
import com.riwi.prueba_desempeno.api.dto.response.SurveyResponse;
import com.riwi.prueba_desempeno.api.dto.response.SurveyResponseQuestions;
import com.riwi.prueba_desempeno.infraestructure.services.SurveyService;
import com.riwi.prueba_desempeno.util.enums.SortType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/surveys")
@AllArgsConstructor
public class SurveyController {

  private final SurveyService surveyService;

  // Colocar una descripcion individual
  @Operation(summary = "Get all paginated surveys")

  @GetMapping
  public ResponseEntity<Page<SurveyResponse>> getAll(
      @RequestParam(defaultValue = "1") int page,
      @RequestParam(defaultValue = "10") int size,
      @RequestHeader(required = false) SortType sortType) {
    if (Objects.isNull(sortType))
      sortType = SortType.NONE;

    return ResponseEntity.ok(this.surveyService.getAll(page - 1, size, sortType));
  }

  @Operation(summary = "Get survey by ID")
  @ApiResponse(responseCode = "400", description = "when the survey is not found", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResp.class))
  })
  @GetMapping("/{id}")
  public ResponseEntity<SurveyResponse> getById(@PathVariable Long id) {
    return ResponseEntity.ok(surveyService.getById(id));
  }

  @Operation(summary = "Get an surveys with questions by its ID number")
  @ApiResponse(responseCode = "400", description = "When the ID is not found", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResp.class))
  })

  @GetMapping("/{id}/questions")
  public ResponseEntity<SurveyResponseQuestions> getbyIdWithQuestions(@PathVariable Long id) {
    return ResponseEntity.ok(surveyService.getSurveysQuestions(id));
  }

  @Operation(summary = "Create an survey")
  @ApiResponse(responseCode = "400", description = "When the request is not valid", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResp.class))
  })
  @PostMapping
  public ResponseEntity<SurveyResponse> createSurvey(@Validated @RequestBody SurveyRequest surveyReq) {
    return ResponseEntity.ok(surveyService.create(surveyReq));
  }

  @Operation(summary = "Update an survey by its ID number")
  @ApiResponse(responseCode = "400", description = "When the request is not valid", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResp.class))
  })
  @PutMapping("{id}")
  public ResponseEntity<SurveyResponse> updateSurvey(@Validated @RequestBody SurveyRequest surveyReq, @PathVariable Long id) {
    return ResponseEntity.ok(surveyService.update(surveyReq, id));
  }

  @Operation(summary = "Delete an user by its ID number")
  @ApiResponse(responseCode = "204", description = "Survey deleted successfully")
  @ApiResponse(responseCode = "400", description = "When the ID is not found", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResp.class))
  })
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteSurvey(@PathVariable Long id) {
    surveyService.delete(id);
    return ResponseEntity.noContent().build();
  }

}
