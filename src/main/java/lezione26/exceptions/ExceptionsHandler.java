package lezione26.exceptions;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lezione26.payloads.errors.ErrorsResponseDTO;
import lezione26.payloads.errors.ErrorsResponseWithListDTO;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(ItemNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorsResponseDTO handleNotFound(ItemNotFoundException e) {
        return new ErrorsResponseDTO(e.getMessage(), new Date());
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsResponseWithListDTO handleBadRequest(BadRequestException e) {
        if (e.getErrorsList() != null) {
            List<String> errorsList = e.getErrorsList().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
            return new ErrorsResponseWithListDTO(e.getMessage(), new Date(), errorsList);
        } else {
            return new ErrorsResponseWithListDTO(e.getMessage(), new Date(), new ArrayList<>());
        }
    }

    @ExceptionHandler(ItemNotAvailableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsResponseDTO handleDeviceNotAvailable(ItemNotAvailableException e) {
        return new ErrorsResponseDTO(e.getMessage(), new Date());
    }

    @ExceptionHandler(InvalidFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsResponseDTO handle(InvalidFormatException e) {
        e.printStackTrace();
        return new ErrorsResponseDTO(e.getMessage(), new Date());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsResponseDTO handle(HttpMessageNotReadableException e) {
        e.printStackTrace();
        return new ErrorsResponseDTO(e.getMessage(), new Date());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorsResponseDTO handleGeneric(Exception e) {
        e.printStackTrace();
        return new ErrorsResponseDTO("Problema lato server...giuro che lo risolveremo presto", new Date());
    }
}