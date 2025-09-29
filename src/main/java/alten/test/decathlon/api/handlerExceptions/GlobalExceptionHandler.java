package alten.test.decathlon.api.handlerExceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import alten.test.decathlon.auth.exceptions.InvalidCredentialException;
import alten.test.decathlon.auth.exceptions.UserCreationException;
import alten.test.decathlon.auth.exceptions.UserNotFoundException;
import alten.test.decathlon.product.exceptions.ProductConflictException;
import alten.test.decathlon.product.exceptions.ProductNotFoundException;
import alten.test.decathlon.shoppingcart.exceptions.ShoppingCartConflictException;
import alten.test.decathlon.shoppingcart.exceptions.ShoppingCartNotFoundException;
import alten.test.decathlon.shoppingcart.exceptions.UserNotFoundInShoppingCartException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFound(ProductNotFoundException ex){
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserCreationException.class)
    public ResponseEntity<Map<String, String>> handleNotFound(UserCreationException ex){
        Map<String, String> error = new HashMap<>();
        error.put("conflict", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ShoppingCartNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFound(ShoppingCartNotFoundException ex){
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundInShoppingCartException.class)
    public ResponseEntity<Map<String, String>> handleNotFound(UserNotFoundInShoppingCartException ex){
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductConflictException.class)
    public ResponseEntity<Map<String, String>> handleNotFound(ProductConflictException ex){
        Map<String, String> error = new HashMap<>();
        error.put("conflict", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ShoppingCartConflictException.class)
    public ResponseEntity<Map<String, String>> handleNotFound(ShoppingCartConflictException ex){
        Map<String, String> error = new HashMap<>();
        error.put("conflict", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFound(UserNotFoundException ex){
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidCredentialException.class)
    public ResponseEntity<Map<String, String>> handleNotFound(InvalidCredentialException ex){
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex){
        Map<String, String> error = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(err -> error.put(err.getField(), err.getDefaultMessage()));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneral(Exception ex){
        Map<String, String> error = new HashMap<>();
        error.put("error", "Intern error : " + ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
