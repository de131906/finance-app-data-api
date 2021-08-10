package de.gp.finance.data.srv.api;

public class DocumentProcessingException extends Exception {
    
    public DocumentProcessingException(Throwable cause) {
        super(cause);
    }
    public DocumentProcessingException(String message) {
        super(message);
    }
}
