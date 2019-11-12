package kamil.appdemo.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import kamil.appdemo.constants.AppDemoConstants;
import kamil.appdemo.user.User;
import kamil.appdemo.utilities.AppdemoUtils;

public class ChangePasswordValidator implements Validator {

	@Override
	public boolean supports(Class<?> cls) {
		return User.class.equals(cls);
	}

	//Sprawdza czy haslo jest puste
	@Override
	public void validate(Object obj, Errors errors) {
		
		@SuppressWarnings("unused")
		User u = (User) obj;
		
		ValidationUtils.rejectIfEmpty(errors, "newPass", "error.userPassword.empty");
		
	}
	
	//Sprawdza poprawnosc hasla 	
	public void checkPasswords(String newPassword, Errors errors) {
		
		if (!newPassword.equals(null)) {
			boolean isMatch = AppdemoUtils.checkEmailOrPassword(AppDemoConstants.PASSWORD_PATTERN, newPassword);
			if(!isMatch) {
				errors.rejectValue("newPass", "error.userPasswordIsNotMatch");
			}
		}
	}
}
