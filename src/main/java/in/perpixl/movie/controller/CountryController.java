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

import in.perpixl.movie.model.CountryDTO;
import in.perpixl.movie.service.ICRUDService;

@RestController(value="country")
@RequestMapping("country")
@CrossOrigin("*")
public class CountryController {
	
	@Autowired
	@Qualifier("countryservice")
	private ICRUDService<CountryDTO> serviceI;
	
	ResponseEntity<String> response = new ResponseEntity<String>(HttpStatus.OK);

	@PostMapping("/create")
	public ResponseEntity<String> create(@RequestBody CountryDTO m)
	{
		System.out.println("In Controller");
		serviceI.create(m);
		System.out.println(m);
		return response;
	}

	@GetMapping("/read/{id}")
	public CountryDTO read(@PathVariable(name="id") long countryId) {
		CountryDTO m=	serviceI.read(countryId);
	 return m;	
	}
	
	@GetMapping("/readAll")
	public ResponseEntity<List<CountryDTO>> readAll(
			@RequestParam(name="pageNumber", required=false) Long pageNumber,
			@RequestParam(name="pageSize", required=false) Long pageSize
			){
		ResponseEntity<List<CountryDTO>> response = new ResponseEntity<List<CountryDTO>>(HttpStatus.OK);
		List<CountryDTO> countryDTOList=serviceI.readAll(pageNumber,pageSize);
		response = response.ok(countryDTOList);
		return response;
	}

	@PutMapping("/update")
	public void update(@RequestBody CountryDTO m) {
		System.out.println(m);
		serviceI.update(m);
		
	}

	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable(name="id") Long countryId) {
		serviceI.delete(countryId);
	}

}
