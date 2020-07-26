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

import in.perpixl.movie.model.SongDTO;
import in.perpixl.movie.service.ICRUDService;

@RestController
@RequestMapping("song")
@CrossOrigin("*")
public class SongController {
	
	@Autowired
	@Qualifier("songservice")
	private ICRUDService<SongDTO> serviceI;
	
	@PostMapping("/create")
	public ResponseEntity<String> create(@RequestBody SongDTO m)
	{
		ResponseEntity<String> response = new ResponseEntity<String>(HttpStatus.OK);
		System.out.println("In Controller - "+m);
		serviceI.create(m);
		System.out.println(m);
		return response;
	}

	@GetMapping("/read/{id}")
	public SongDTO read(@PathVariable(name="id") long songId) {
	SongDTO m=	serviceI.read(songId);
	 return m;	
	}
	
	@GetMapping("/readAll")
	public ResponseEntity<List<SongDTO>> readAll(){
		ResponseEntity<List<SongDTO>> response = new ResponseEntity<List<SongDTO>>(HttpStatus.OK);
		List<SongDTO> songDTOList=serviceI.readAll();
		response = response.ok(songDTOList);
		return response;
	}

	@PutMapping("/update")
	public void update(@RequestBody SongDTO m) {
		System.out.println(m);
		serviceI.update(m);
		
	}

	@DeleteMapping("/delete/{id}")
	public long delete(@PathVariable(name="id") long songId) {
		long l = serviceI.delete(songId);
		return l;
	}

}
