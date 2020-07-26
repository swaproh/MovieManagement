package in.perpixl.movie.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandlers
{
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> handleMethodArgumentNotValidException(Exception ex)
	{
		String bodyOfResponse = ex.getMessage();
		System.out.println("Exception Occured: "+ex.getMessage());
		return new ResponseEntity<Object>(bodyOfResponse, HttpStatus.CONFLICT);
	}
}
