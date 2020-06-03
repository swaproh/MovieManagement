package in.perpixl.movie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.perpixl.movie.dao.IDao;
import in.perpixl.movie.model.PersonDTO;

@Service
public class PersonServiceImpl implements IService<PersonDTO>{
	
	@Autowired
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

}
