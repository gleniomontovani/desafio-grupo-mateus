package br.com.glenio.montovani.infrastructure.exception;

public record ErrorDetails(
        int status,
        String message
) {}

