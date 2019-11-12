package kamil.appdemo.user;

import java.util.Locale;

import javax.ws.rs.GET;
import javax.ws.rs.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import kamil.appdemo.user.User;
import kamil.appdemo.utilities.AppdemoUtils;
import kamil.appdemo.validators.UserRegisterValidator;
import kamil.appdemo.user.UserService;

@Controller
public class RegisterController {
	
	@Autowired //Pozwala na deklaracje obiektu klasy bez odnosnika do niej
	private UserService userService;
	
	@Autowired
	MessageSource messageSource;
	
	@GET
	@RequestMapping(value = "/register")
	public String registerForm(Model model) { //"user" -> modelAttribute="user" w register.jsp
		User u = new User(); //  Tworzymy nowy obiekt user.
		model.addAttribute("user", u); // "user" = modelAttribute="user" w register.jsp
		return "register"; //register.jsp
	}
	
	@POST
	@RequestMapping(value = "/adduser") //Metoda obsługująca zapytanie, zapisana w register.jsp (action)
	public String registerAction(User user, BindingResult result, Model model, Locale locale) {
	//BindingResult - Validacja, Model - , Locale - Pozwala na użycie messagesource
		
		String returnPage = null; //Nazwa stony po rejestracji
		
		User userExist = userService.findUserByEmail(user.getEmail()); // Czy email już istnieje w bazie?
		new UserRegisterValidator().validateEmailExist(userExist, result);
		
		new UserRegisterValidator().validate(user, result); //Validator, nadajemy result blendy
		
		if (result.hasErrors()) {
			returnPage = "register";
		} else {
			userService.saveUser(user); // WYWOLANIE METODY ZAPISUJACEJ DO BAZY DANYCH!
			model.addAttribute("message", messageSource.getMessage("user.register.success", null, locale)); // "message" w register.jsp -> wysyłamy komunikat "user.register.success"
			model.addAttribute("user", new User());  //Czyscimy pola danych uzytkownika
			returnPage = "login";
		}
		
		return returnPage;
	}
	
}
