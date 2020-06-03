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

import in.perpixl.movie.model.PersonDTO;
import in.perpixl.movie.service.IService;

@RestController(value="person")
public class PersonController {
	
	@Autowired
	private IService<PersonDTO> serviceI;
	
	ResponseEntity<String> response = new ResponseEntity<String>(HttpStatus.OK);

	@PostMapping("/create")
	public ResponseEntity<String> create(@RequestBody PersonDTO m)
	{
		System.out.println("In Controller");
		serviceI.create(m);
		System.out.println(m);
		return response;
	}

	@GetMapping("{id}")
	public PersonDTO read(@PathVariable(name="id") long personId) {
	PersonDTO m=serviceI.read(personId);
	 return m;	
	}

	@PutMapping("/update")
	public void update(PersonDTO m) {
		serviceI.update(m);
		
	}

	@DeleteMapping("{id}")
	public long delete(@PathVariable(name="id") long personId) {
		long l = serviceI.delete(personId);
		return l;
	}

}
