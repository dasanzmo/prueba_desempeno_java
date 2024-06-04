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
import com.riwi.prueba_desempeno.api.dto.request.QuestionRequest;
import com.riwi.prueba_desempeno.api.dto.response.QuestionResponse;
import com.riwi.prueba_desempeno.api.dto.response.QuestionResponseOptions;
import com.riwi.prueba_desempeno.infraestructure.services.QuestionService;
import com.riwi.prueba_desempeno.util.enums.SortType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/questions")
@AllArgsConstructor
public class QuestionController {

  private final QuestionService questionService;

  // Colocar una descripcion individual
  @Operation(summary = "Get all paginated questions")

  @GetMapping
  public ResponseEntity<Page<QuestionResponse>> getAll(
      @RequestParam(defaultValue = "1") int page,
      @RequestParam(defaultValue = "10") int size,
      @RequestHeader(required = false) SortType sortType) {
    if (Objects.isNull(sortType))
      sortType = SortType.NONE;

    return ResponseEntity.ok(this.questionService.getAll(page - 1, size, sortType));
  }

  @Operation(summary = "Get question by ID")
  @ApiResponse(responseCode = "400", description = "when the question is not found", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResp.class))
  })
  @GetMapping("/{id}")
  public ResponseEntity<QuestionResponse> getById(@PathVariable Long id) {
    return ResponseEntity.ok(questionService.getById(id));
  }

  @Operation(summary = "Get an questions with options by its ID number")
  @ApiResponse(responseCode = "400", description = "When the ID is not found", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResp.class))
  })

  @GetMapping("/{id}/options")
  public ResponseEntity<QuestionResponseOptions> getbyIdWithQuestions(@PathVariable Long id) {
    return ResponseEntity.ok(questionService.getQuestionOptions(id));
  }

  @Operation(summary = "Create an question")
  @ApiResponse(responseCode = "400", description = "When the request is not valid", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResp.class))
  })
  @PostMapping
  public ResponseEntity<QuestionResponse> createSurvey(@Validated @RequestBody QuestionRequest questionRequest) {
    return ResponseEntity.ok(questionService.create(questionRequest));
  }

  @Operation(summary = "Update an question by its ID number")
  @ApiResponse(responseCode = "400", description = "When the request is not valid", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResp.class))
  })
  @PutMapping("{id}")
  public ResponseEntity<QuestionResponse> updateSurvey(@Validated @RequestBody QuestionRequest questionRequest, @PathVariable Long id) {
    return ResponseEntity.ok(questionService.update(questionRequest, id));
  }

  @Operation(summary = "Delete an question by its ID number")
  @ApiResponse(responseCode = "204", description = "question deleted successfully")
  @ApiResponse(responseCode = "400", description = "When the ID is not found", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResp.class))
  })
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteSurvey(@PathVariable Long id) {
    questionService.delete(id);
    return ResponseEntity.noContent().build();
  }

}
