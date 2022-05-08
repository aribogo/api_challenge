package api.gft.entity.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import api.gft.entity.Politician;

public class PoliticianValidator implements ConstraintValidator<IsLeaderValidation, Politician> {

	@Override
	public boolean isValid(Politician politician, ConstraintValidatorContext context) {
		if(
				!politician.getPosition().getDisplayValue().equals("Congressman") && 
				!politician.getPosition().getDisplayValue().equals("State deputy") &&
				!politician.getPosition().getDisplayValue().equals("Senator") && 
				politician.getIsLeader() == true
			) {
		return false;
	} 
		
		return true;

}}
