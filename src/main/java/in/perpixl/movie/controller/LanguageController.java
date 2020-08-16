package in.perpixl.movie.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import in.perpixl.movie.model.LanguageDTO;
import in.perpixl.movie.service.ICRUDService;

@RestController(value="language")
@RequestMapping("language")
@CrossOrigin("*")
public class LanguageController {
	
	@Autowired
	@Qualifier("languageservice")
	private ICRUDService<LanguageDTO> serviceI;
	
	@PostMapping("/create")
	public ResponseEntity<LanguageDTO> create(@RequestBody LanguageDTO m)
	{
		Long createdResourceId = serviceI.create(m);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(createdResourceId).toUri();
		return ResponseEntity.created(location).build();
	}

	@GetMapping("/read/{id}")
	public LanguageDTO read(@PathVariable(name="id") Long languageId) {
		return serviceI.read(languageId);
	}
	
	@GetMapping("/readAll")
	public ResponseEntity<List<LanguageDTO>> readAll(
			@RequestParam(name="pageNumber", required=false) Long pageNumber,
			@RequestParam(name="pageSize", required=false) Long pageSize
			){
		List<LanguageDTO> languageDTOList=serviceI.readAll(pageNumber,pageSize);
		return ResponseEntity.ok(languageDTOList);
	}

	@PutMapping("/update")
	public void update(@RequestBody LanguageDTO m) {
		serviceI.update(m);
	}

	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable(name="id") long languageId) {
		serviceI.delete(languageId);
	}

}
