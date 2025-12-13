package br.com.glenio.montovani.infrastructure.controller;

import br.com.glenio.montovani.application.dto.ProductRequestDTO;
import br.com.glenio.montovani.application.dto.ProductResponseDTO;
import br.com.glenio.montovani.application.mapper.ProductMapper;
import br.com.glenio.montovani.core.usecase.ProductUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductUseCase useCase;
    private final ProductMapper mapper;

    @PostMapping
    public ResponseEntity<ProductResponseDTO> create(
            @RequestBody ProductRequestDTO dto) {

        var product = useCase.create(mapper.toDomain(dto));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapper.toResponse(product));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> findAll() {
        var products = useCase.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();

        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> findById(
            @PathVariable Long id) {

        var product = useCase.findById(id);
        return ResponseEntity.ok(mapper.toResponse(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> update(
            @PathVariable Long id,
            @RequestBody ProductRequestDTO dto) {

        var product = useCase.update(id, mapper.toDomain(dto));
        return ResponseEntity.ok(mapper.toResponse(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        useCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}

