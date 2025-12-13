package br.com.glenio.montovani.infrastructure.handler;

import br.com.glenio.montovani.core.exception.ProductNotFoundException;
import br.com.glenio.montovani.infrastructure.exception.ErrorDetails;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

class RestHandlerExceptionTest {

    private final RestHandlerException handler = new RestHandlerException();

    @Test
    void shouldHandleRuntimeException() {
        var exception = new ProductNotFoundException(1L);

        ResponseEntity<Object> response =
                handler.handleProductNotFound(exception);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

        var body = (ErrorDetails) response.getBody();
        assertThat(body).isNotNull();
        assertThat(body.status()).isEqualTo(404);
        assertThat(body.message()).isEqualTo("Product not found with id: 1");
    }
}
