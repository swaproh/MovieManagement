package in.perpixl.movie.controller;

import java.util.List;

import javax.validation.Valid;

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

import in.perpixl.movie.model.RaagDTO;
import in.perpixl.movie.service.ICRUDService;
import in.perpixl.movie.service.RaagInfoService;

@RestController
@RequestMapping("raag")
@CrossOrigin("*")
public class RaagController {
	
	@Autowired
	@Qualifier("raagservice")
	private ICRUDService<RaagDTO> serviceI;
	
	@Autowired
	private RaagInfoService raagInfoService;
	
	@PostMapping("/create")
	public ResponseEntity<String> create(@RequestBody @Valid RaagDTO m)
	{
		ResponseEntity<String> response = new ResponseEntity<String>(HttpStatus.OK);
		System.out.println("In Controller");
		serviceI.create(m);
		System.out.println(m);
		return response;
	}

	@GetMapping("/read/{id}")
	public RaagDTO read(@PathVariable(name="id") long raagId) {
	RaagDTO m=	serviceI.read(raagId);
	 return m;	
	}
	
	@GetMapping("/readAll")
	public ResponseEntity<List<RaagDTO>> readAll(){
		ResponseEntity<List<RaagDTO>> response = new ResponseEntity<List<RaagDTO>>(HttpStatus.OK);
		List<RaagDTO> raagDTOList=serviceI.readAll();
		response = response.ok(raagDTOList);
		return response;
	}

	@PutMapping("/update")
	public void update(@RequestBody @Valid RaagDTO m) {
		System.out.println(m);
		serviceI.update(m);
		
	}

	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable(name="id") Long raagId) {
		serviceI.delete(raagId);
	}
	
	@GetMapping("/thaat/readAll")
	public ResponseEntity<List<String>> readAllThaat(){
		ResponseEntity<List<String>> response = new ResponseEntity<List<String>>(HttpStatus.OK);
		List<String> raagDTOList=raagInfoService.getAllThaatas();
		response = response.ok(raagDTOList);
		return response;
	}
	
	@GetMapping("/jati/readAll")
	public ResponseEntity<List<String>> readAllJati(){
		ResponseEntity<List<String>> response = new ResponseEntity<List<String>>(HttpStatus.OK);
		List<String> raagDTOList=raagInfoService.getAllJatis();
		response = response.ok(raagDTOList);
		return response;
	}
	
	@GetMapping("/samay/readAll")
	public ResponseEntity<List<String>> readAllSamay(){
		ResponseEntity<List<String>> response = new ResponseEntity<List<String>>(HttpStatus.OK);
		List<String> raagDTOList=raagInfoService.getAllSamay();
		response = response.ok(raagDTOList);
		return response;
	}

}
