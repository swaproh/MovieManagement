package in.perpixl.movie.handlers;

import javax.persistence.EntityNotFoundException;

import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandlers
{
	/**
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(EntityNotFoundException.class)
	protected ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex)
	{
		System.out.println("Exception Occured: "+ex.getMessage());
		String bodyOfResponse = ex.getMessage();
		return new ResponseEntity<Object>(bodyOfResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidDataAccessApiUsageException.class)
	protected ResponseEntity<Object> handleInvalidDataAccessApiUsageException(InvalidDataAccessApiUsageException ex)
	{
		System.out.println("Exception Occured: "+ex.getMessage());
		String bodyOfResponse = ex.getMessage();
		return new ResponseEntity<Object>(bodyOfResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> handleMethodArgumentNotValidException(Exception ex)
	{
		System.out.println("Exception Occured: "+ex.getMessage());
		String bodyOfResponse = ex.getMessage();
		return new ResponseEntity<Object>(bodyOfResponse, HttpStatus.CONFLICT);
	}
}
