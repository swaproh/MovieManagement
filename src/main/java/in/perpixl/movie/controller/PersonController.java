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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.perpixl.movie.model.PersonDTO;
import in.perpixl.movie.service.ICRUDService;

@RestController(value="person")
@RequestMapping("person")
@CrossOrigin("*")
public class PersonController {
	
	@Autowired
	@Qualifier("personservice")
	private ICRUDService<PersonDTO> serviceI;
	
	ResponseEntity<String> response = new ResponseEntity<String>(HttpStatus.OK);

	@PostMapping("/create")
	public ResponseEntity<String> create(@RequestBody PersonDTO m)
	{
		System.out.println("In Controller");
		serviceI.create(m);
		System.out.println(m);
		return response;
	}

	@GetMapping("/read/{id}")
	public PersonDTO read(@PathVariable(name="id") long personId) {
	PersonDTO m=serviceI.read(personId);
	 return m;	
	}
	
	@GetMapping("/readAll")
	public ResponseEntity<List<PersonDTO>> readAll(
			@RequestParam(name="pageNumber", required=false) Long pageNumber,
			@RequestParam(name="pageSize", required=false) Long pageSize
			){
		ResponseEntity<List<PersonDTO>> response = new ResponseEntity<List<PersonDTO>>(HttpStatus.OK);
		List<PersonDTO> personDTOList=serviceI.readAll(pageNumber,pageSize);
		response = response.ok(personDTOList);
		return response;
	}

	@PutMapping("/update")
	public void update(@RequestBody PersonDTO m) {
		serviceI.update(m);
		
	}

	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable(name="id") Long personId) {
		serviceI.delete(personId);
	}

}
