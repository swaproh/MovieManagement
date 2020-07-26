package in.perpixl.movie.controller;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import in.perpixl.movie.model.MovieDTO;
import in.perpixl.movie.service.ICRUDService;

@RestController(value="movie")
@RequestMapping("movie")
@CrossOrigin("*")
public class MovieController {

	@Autowired
	@Qualifier("movieservice")
	private ICRUDService<MovieDTO> serviceI;
	
	private static final Logger LOGGER=LogManager.getLogger(MovieController.class);
	
	@PostMapping("/create")
	public ResponseEntity<String> create(@RequestBody MovieDTO m)
	{
		ResponseEntity<String> response = new ResponseEntity<String>(HttpStatus.OK);
		System.out.println("In Controller");
		System.out.println(m);
		serviceI.create(m);
		return response;
	}

	@GetMapping("/read/{id}")
	public MovieDTO read(@PathVariable(name="id") long movieId) {
	MovieDTO m=	serviceI.read(movieId);
	 return m;	
	}
	
	@GetMapping("/readAll")
	public ResponseEntity<List<MovieDTO>> readAll(){
		ResponseEntity<List<MovieDTO>> response = new ResponseEntity<List<MovieDTO>>(HttpStatus.OK);
		List<MovieDTO> movieDTOList=serviceI.readAll();
		response = response.ok(movieDTOList);
		return response;
	}

	@PutMapping("/update")
	public void update(@RequestBody MovieDTO m) {
		System.out.println("In update Controller");
		System.out.println("1>"+m);
		serviceI.update(m);
		System.out.println("In update Controller end");
		
	}

	@DeleteMapping("/delete/{id}")
	public long delete(@PathVariable(name="id") long movieId) {
		long l = serviceI.delete(movieId);
		System.out.println("delete movie controller");
		return l;
		
	}

}
