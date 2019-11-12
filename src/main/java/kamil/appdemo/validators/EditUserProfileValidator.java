package kamil.appdemo.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import kamil.appdemo.constants.AppDemoConstants;
import kamil.appdemo.user.User;
import kamil.appdemo.utilities.AppdemoUtils;

public class EditUserProfileValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> cls) {
		return User.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		User u = (User) obj;
	}

}
