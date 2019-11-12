package kamil.appdemo.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import kamil.appdemo.constants.AppDemoConstants;
import kamil.appdemo.user.User;
import kamil.appdemo.utilities.AppdemoUtils;

//Validator rejestracji, sprawdza poprawność.
public class UserRegisterValidator implements Validator {

	@Override
	public boolean supports(Class<?> cls) {
		return User.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors errors) { //Pole errors wyszukuje path:errors w rejestracja.jsp
		User u = (User) obj;
		
		ValidationUtils.rejectIfEmpty(errors, "name", "error.userName.empty");
		ValidationUtils.rejectIfEmpty(errors, "email", "error.userEmail.empty");
		ValidationUtils.rejectIfEmpty(errors, "pass", "error.userPassword.empty");
		
		//Sprawdzamy czy podany email jest prawidlowy
		if (!u.getEmail().equals(null)) { 
			boolean isMatch = AppdemoUtils.checkEmailOrPassword(AppDemoConstants.EMAIL_PATTERN, u.getEmail());
			if(!isMatch) {
				errors.rejectValue("email", "error.userEmailIsNotMatch");
			}
		}
		
		//Sprawdzamy czy podane haslo jest prawidlowe
		if (!u.getPass().equals(null)) {
			boolean isMatch = AppdemoUtils.checkEmailOrPassword(AppDemoConstants.PASSWORD_PATTERN, u.getPass());
			if(!isMatch) {
				errors.rejectValue("pass", "error.userPasswordIsNotMatch");
			}
		}
		
	}
	
	public void validateEmailExist(User user, Errors errors) {
		if (user != null) {
			errors.rejectValue("email", "error.userEmailExist");
		}
	}

}
