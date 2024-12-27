package com.abhishek.practical.exception;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Error;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;


@Singleton
public class GlobalExceptionHandler implements ExceptionHandler<Exception, HttpResponse<String>> {

    @Override
    public HttpResponse<String> handle(HttpRequest request, Exception exception) {
        return HttpResponse.serverError("An unexpected error occured: " + exception.getMessage());
    }

}
