package in.perpixl.movie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.perpixl.movie.dao.IDao;
import in.perpixl.movie.model.RoleDTO;

@Service
public class RoleServiceImpl implements IService<RoleDTO>{
	
	@Autowired
	private IDao<RoleDTO> daoI;
	
	@Override
	public void create(RoleDTO m)
	{
		System.out.println("In service Layer");
		daoI.create(m);
	}

	@Override
	public RoleDTO read(long roleId) {
		System.out.println("In service layer");
		RoleDTO m=daoI.read(roleId);
		return m;
	}

	@Override
	public void update(RoleDTO m) {
		daoI.update(m);
	}

	@Override
	public long delete(long roleId) {
		long l=daoI.delete(roleId);
		return l;
	}

}
