package in.perpixl.movie.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import in.perpixl.movie.entity.CompanyEntity;
import in.perpixl.movie.entity.LanguageEntity;
import in.perpixl.movie.entity.MovieEntity;

@Service
public class MovieServiceImplEx {

	public void removeExistingCompanyList(MovieEntity entity) {
		// production companies
		Set<CompanyEntity> prodCompanies = entity.getProductionCompany();
		for(CompanyEntity c: prodCompanies)
		{
			entity.removeProductionCompany(c);
		}
		
		Set<CompanyEntity> distCompanies = entity.getDistributedBy(); 
		for(CompanyEntity c: distCompanies)
		{
			entity.removeProductionCompany(c);
		}
	}
	
	public void removeExistingLanguageList(MovieEntity entity) {
		// languages
		
		  Set<LanguageEntity> languages = entity.getLanguage();
		  for(LanguageEntity c: languages)
			{
				entity.removeLanguage(c);
			}
	}

}
