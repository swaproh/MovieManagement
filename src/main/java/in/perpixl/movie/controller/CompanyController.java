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

import in.perpixl.movie.model.CompanyDTO;
import in.perpixl.movie.service.ICRUDService;

@RestController
@RequestMapping("company")
@CrossOrigin("*")
public class CompanyController {
	
	@Autowired
	@Qualifier("companyservice")
	private ICRUDService<CompanyDTO> serviceI;
	
	ResponseEntity<String> response = new ResponseEntity<String>(HttpStatus.OK);

	@PostMapping("/create")
	public ResponseEntity<String> create(@RequestBody CompanyDTO m)
	{
		System.out.println("In Controller");
		serviceI.create(m);
		System.out.println(m);
		return response;
	}

	@GetMapping("/read/{id}")
	public CompanyDTO read(@PathVariable(name="id") long companyId) {
		CompanyDTO m=	serviceI.read(companyId);
	 return m;	
	}
	
	@GetMapping("/readAll")
	public ResponseEntity<List<CompanyDTO>> readAll(){
		ResponseEntity<List<CompanyDTO>> response = new ResponseEntity<List<CompanyDTO>>(HttpStatus.OK);
		List<CompanyDTO> companyDTOList=serviceI.readAll();
		response = response.ok(companyDTOList);
		return response;
	}

	@PutMapping("/update")
	public void update(@RequestBody CompanyDTO m) {
		System.out.println(m);
		serviceI.update(m);
		
	}

	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable(name="id") Long companyId) {
		serviceI.delete(companyId);
	}

}
