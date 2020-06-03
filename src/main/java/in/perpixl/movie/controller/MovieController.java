package in.perpixl.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.perpixl.movie.model.MovieDTO;
import in.perpixl.movie.service.IService;

@RestController(value="movie")
public class MovieController {

	@Autowired
	private IService<MovieDTO> serviceI;
	
	ResponseEntity<String> response = new ResponseEntity<String>(HttpStatus.OK);

	@PostMapping("/create")
	public ResponseEntity<String> create(@RequestBody MovieDTO m)
	{
		System.out.println("In Controller");
		serviceI.create(m);
		System.out.println(m);
		return response;
	}

	@GetMapping("{id}")
	public MovieDTO read(@PathVariable(name="id") long movieId) {
	MovieDTO m=	serviceI.read(movieId);
	 return m;	
	}

	@PutMapping("/update")
	public void update(MovieDTO m) {
		serviceI.update(m);
		
	}

	@DeleteMapping("{id}")
	public long delete(@PathVariable(name="id") long movieId) {
		long l = serviceI.delete(movieId);
		return l;
	}

}
