package net.atos.api.logistica.conf;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RestResponseExceptionHandler {
	
	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(value= HttpStatus.NOT_FOUND)
    @ResponseBody
    public Message requestHandlingNoHandlerFound(NotFoundException exception ) {
		return new Message("Not Found", exception.getMessage());
	}

	@ExceptionHandler(BadRequestException.class)
	@ResponseStatus(value= HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Message requestHandlingNoHandlerBadRequest(BadRequestException exception ) {
		return new Message("Erro de validação", exception.getMessage());
	}
	
	
	@ExceptionHandler({ HttpMessageNotReadableException.class })
	public ResponseEntity<String> handleHttpMessageNotReadable(
			HttpMessageNotReadableException exMethod, 
			 									WebRequest request) {
		
		

		return ResponseEntity.badRequest().body(exMethod.getMessage());
	}

	
	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<List<String>> handleConstraintViolation(
			                                    ConstraintViolationException exMethod, 
			 									WebRequest request) {
		List<String> errors = new ArrayList<String>();
		for (ConstraintViolation<?> violation : exMethod.getConstraintViolations()) {
			errors.add(violation.getRootBeanClass().getName() + " " + violation.getPropertyPath() + ": "
					+ violation.getMessage());
		}


		return ResponseEntity.badRequest().body(errors);
	}
	
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(value= HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<List<String>>  requestHandlingNoHandlerMethodArgumentNotValidException(MethodArgumentNotValidException exception ) {		
		
		List<String> errors = new ArrayList<String>();
		for (ObjectError violation : exception.getAllErrors()) {
			errors.add(violation.getObjectName() + ": "
					+ violation.getDefaultMessage());
		}	

		return ResponseEntity.badRequest().body(errors);
	}
	
	

}

class Message{
    private String shortMessagem;
    private String detail;
    
    
	public Message(String shortMessagem, String detail) {
		super();
		this.shortMessagem = shortMessagem;
		this.detail = detail;
	}
	public String getShortMessagem() {
		return shortMessagem;
	}
	public void setShortMessagem(String shortMessagem) {
		this.shortMessagem = shortMessagem;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
    
}
