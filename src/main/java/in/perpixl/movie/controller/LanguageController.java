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

import in.perpixl.movie.model.LanguageDTO;
import in.perpixl.movie.service.ICRUDService;

@RestController(value="language")
@RequestMapping("language")
@CrossOrigin("*")
public class LanguageController {
	
	@Autowired
	@Qualifier("languageservice")
	private ICRUDService<LanguageDTO> serviceI;
	
	ResponseEntity<String> response = new ResponseEntity<String>(HttpStatus.OK);

	@PostMapping("/create")
	public ResponseEntity<String> create(@RequestBody LanguageDTO m)
	{
		System.out.println("In Controller");
		serviceI.create(m);
		System.out.println(m);
		return response;
	}

	@GetMapping("/read/{id}")
	public LanguageDTO read(@PathVariable(name="id") long languageId) {
		LanguageDTO m=	serviceI.read(languageId);
	 return m;	
	}
	
	@GetMapping("/readAll")
	public ResponseEntity<List<LanguageDTO>> readAll(){
		ResponseEntity<List<LanguageDTO>> response = new ResponseEntity<List<LanguageDTO>>(HttpStatus.OK);
		List<LanguageDTO> languageDTOList=serviceI.readAll();
		response = response.ok(languageDTOList);
		return response;
	}

	@PutMapping("/update")
	public void update(@RequestBody LanguageDTO m) {
		System.out.println(m);
		serviceI.update(m);
		
	}

	@DeleteMapping("/delete/{id}")
	public long delete(@PathVariable(name="id") long languageId) {
		long l = serviceI.delete(languageId);
		return l;
	}

}
