package com.itau.pix.controller;

import com.itau.pix.dto.request.CreateRequestDTO;
import com.itau.pix.dto.request.SearchRequestDTO;
import com.itau.pix.dto.request.UpdateRequestDTO;
import com.itau.pix.dto.response.CreateResponseDTO;
import com.itau.pix.dto.response.SearchResponseDTO;
import com.itau.pix.dto.response.UpdateResponseDTO;
import com.itau.pix.exception.PixAlreadyExistsException;
import com.itau.pix.exception.PixNoExistsException;
import com.itau.pix.service.PixService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController()
public class PixController {

    private final List<String> VALID_SEARCH_FIELDS = Arrays.asList("type", "firstName", "lastName", "account");

    @Autowired
    private PixService pixService;

    @PostMapping("/pix")
    private ResponseEntity create(@Valid @RequestBody CreateRequestDTO request) {
        var pix = pixService.create(request);
        return ResponseEntity.ok(new CreateResponseDTO(pix.getId()));
    }

    @PutMapping("/pix/{id}")
    private ResponseEntity create(@PathVariable Long id, @Valid @RequestBody UpdateRequestDTO request) {
        var pix = pixService.update(id, request);
        return ResponseEntity.ok(new UpdateResponseDTO.Builder().of(pix));
    }

    @GetMapping("/pix/{id}")
    private ResponseEntity get(@PathVariable Long id) {
        var pix = pixService.findById(id);
        if (pix.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(new SearchResponseDTO.Builder().of(pix.get()));
    }

    @GetMapping("/pix")
    private ResponseEntity search(@RequestParam Map<String, String> allParam,
                                  @RequestParam(name = "page", defaultValue = "0") int page,
                                  @RequestParam(name = "size", defaultValue = "10") int size) {

        var fields = allParam
                .entrySet()
                .stream()
                .filter(e -> VALID_SEARCH_FIELDS.contains(e.getKey())
                        & !e.getKey().equals("page")
                        & !e.getKey().equals("size"))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        var pagination = PageRequest.of(page, size);
        var pixList = pixService.findAll(fields, pagination);

        if (pixList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        var responseList = pixList.stream()
                .map(e -> new SearchResponseDTO.Builder().of(e)).collect(Collectors.toList());
        return ResponseEntity.ok(responseList);
    }

    @PostMapping("/pix/search")
    private ResponseEntity search(@RequestBody(required = true) SearchRequestDTO searchRequestDTO) {

        var pixList = pixService.findAll(searchRequestDTO);
        if (pixList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        var responseList = pixList.stream()
                .map(e -> new SearchResponseDTO.Builder().of(e)).collect(Collectors.toList());

        return ResponseEntity.ok(responseList);
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(PixAlreadyExistsException.class)
    public Map<String, String> handleValidationExceptions(PixAlreadyExistsException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", ex.getMessage());
        return errors;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(PixNoExistsException.class)
    public Map<String, String> handleValidationExceptions(PixNoExistsException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", ex.getMessage());
        return errors;
    }

}
