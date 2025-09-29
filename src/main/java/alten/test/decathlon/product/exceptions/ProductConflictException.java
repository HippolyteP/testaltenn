package alten.test.decathlon.product.exceptions;

public class ProductConflictException extends RuntimeException{
    public ProductConflictException(String code){
        super("conflict code product creation " + code );
    }
}
