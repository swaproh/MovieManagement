package in.perpixl.movie.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import in.perpixl.movie.annotations.SwarConstraint;
import in.perpixl.movie.service.RaagInfoService;

public class SwarValidator implements ConstraintValidator<SwarConstraint, String>
{
	@Autowired
	RaagInfoService raagInfoService;
	
	@Override
	public void initialize(SwarConstraint constraintAnnotation) 
	{
		ConstraintValidator.super.initialize(constraintAnnotation);
	}
	
	@Override
	public boolean isValid(String swarData, ConstraintValidatorContext context) 
	{
		String[] swarDataSplit = swarData.split(" ");
		for(String swar: swarDataSplit)
		{
			if(!raagInfoService.getAllSwaras().contains(swar))
			{
				return false;
			}
		}
		return true;
	}

}
