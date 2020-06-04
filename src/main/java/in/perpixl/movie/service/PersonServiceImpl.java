package in.perpixl.movie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import in.perpixl.movie.dao.IDao;
import in.perpixl.movie.model.PersonDTO;

@Service
@Qualifier("personservice")
public class PersonServiceImpl implements IService<PersonDTO>{
	
	@Autowired
	@Qualifier("persondao")
	private IDao<PersonDTO> daoI;

	@Override
	public void create(PersonDTO m)
	{
		System.out.println("In service Layer");
		daoI.create(m);
	}

	@Override
	public PersonDTO read(long personId) {
		System.out.println("In service layer");
		PersonDTO m=daoI.read(personId);
		return m;
	}

	@Override
	public void update(PersonDTO m) {
		daoI.update(m);
	}

	@Override
	public long delete(long personId) {
		long l=daoI.delete(personId);
		return l;
	}

	@Override
	public List<PersonDTO> readAll() {
		return daoI.readAll();
	}

}
