package br.com.glenio.montovani.infrastructure.controller;

import br.com.glenio.montovani.application.dto.ProductRequestDTO;
import br.com.glenio.montovani.application.dto.ProductResponseDTO;
import br.com.glenio.montovani.application.mapper.ProductMapper;
import br.com.glenio.montovani.core.usecase.ProductUseCase;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Criação de um novo produto.")
    @PostMapping
    public ResponseEntity<ProductResponseDTO> create(
            @RequestBody ProductRequestDTO dto) {

        var product = useCase.create(mapper.toDomain(dto));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapper.toResponse(product));
    }

    @Operation(summary = "Consulta todos os produtos cadastrado.")
    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> findAll() {
        var products = useCase.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();

        return ResponseEntity.ok(products);
    }

    @Operation(summary = "Busca um determinado produto pelo ID.")
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> findById(
            @PathVariable Long id) {

        var product = useCase.findById(id);
        return ResponseEntity.ok(mapper.toResponse(product));
    }

    @Operation(summary = "Atualiza um determinado produto.")
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> update(
            @PathVariable Long id,
            @RequestBody ProductRequestDTO dto) {

        var product = useCase.update(id, mapper.toDomain(dto));
        return ResponseEntity.ok(mapper.toResponse(product));
    }

    @Operation(summary = "Exclui um determinado produto.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        useCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}

