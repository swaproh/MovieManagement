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

import in.perpixl.movie.model.RoleDTO;
import in.perpixl.movie.service.IService;

@RestController(value="role")
public class RoleController {
	
	@Autowired
	private IService<RoleDTO> serviceI;
	
	ResponseEntity<String> response = new ResponseEntity<String>(HttpStatus.OK);

	@PostMapping("/create")
	public ResponseEntity<String> create(@RequestBody RoleDTO m)
	{
		System.out.println("In Controller");
		serviceI.create(m);
		System.out.println(m);
		return response;
	}

	@GetMapping("{id}")
	public RoleDTO read(@PathVariable(name="id") long roleId) {
	RoleDTO m=	serviceI.read(roleId);
	 return m;	
	}

	@PutMapping("/update")
	public void update(RoleDTO m) {
		serviceI.update(m);
		
	}

	@DeleteMapping("{id}")
	public long delete(@PathVariable(name="id") long roleId) {
		long l = serviceI.delete(roleId);
		return l;
	}

}
