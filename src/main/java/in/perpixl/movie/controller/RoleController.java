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

import in.perpixl.movie.model.RoleDTO;
import in.perpixl.movie.service.ICRUDService;

@RestController
@RequestMapping("role")
@CrossOrigin("*")
public class RoleController {
	
	@Autowired
	@Qualifier("roleservice")
	private ICRUDService<RoleDTO> serviceI;
	
	ResponseEntity<String> response = new ResponseEntity<String>(HttpStatus.OK);

	@PostMapping("/create")
	public ResponseEntity<String> create(@RequestBody RoleDTO m)
	{
		System.out.println("In Controller");
		serviceI.create(m);
		System.out.println(m);
		return response;
	}

	@GetMapping("/read/{id}")
	public RoleDTO read(@PathVariable(name="id") long roleId) {
	RoleDTO m=	serviceI.read(roleId);
	 return m;	
	}
	
	@GetMapping("/readAll")
	public ResponseEntity<List<RoleDTO>> readAll(){
		ResponseEntity<List<RoleDTO>> response = new ResponseEntity<List<RoleDTO>>(HttpStatus.OK);
		List<RoleDTO> roleDTOList=serviceI.readAll();
		response = response.ok(roleDTOList);
		return response;
	}

	@PutMapping("/update")
	public void update(@RequestBody RoleDTO m) {
		System.out.println(m);
		serviceI.update(m);
		
	}

	@DeleteMapping("/delete/{id}")
	public long delete(@PathVariable(name="id") long roleId) {
		long l = serviceI.delete(roleId);
		return l;
	}

}
