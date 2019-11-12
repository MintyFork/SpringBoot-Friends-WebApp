package kamil.appdemo.user;

import java.util.List;
import java.util.Locale;

import javax.ws.rs.GET;
import javax.ws.rs.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kamil.appdemo.user.UserService;
import kamil.appdemo.validators.EditUserProfileValidator;
import kamil.appdemo.user.User;
import kamil.appdemo.validators.ChangePasswordValidator;
import kamil.appdemo.utilities.UserUtilities;

@Controller
public class ProfilController {
	
	@Autowired
	private UserService userService;
	
	
	@Autowired
	MessageSource messageSource;
	
	@GET
	@RequestMapping(value = "/profil")
	public String showUserProfilePage(Model model) {	//Model uzywamy zawsze do przesyłania danych do .jsp
		String username = UserUtilities.getLoggedUser(); //Pobieranie nazwy aktualnego uzytkonika
		User user = userService.findUserByEmail(username); 
		model.addAttribute("user", user);
		return "profil";
	}
	
	@GET
	@RequestMapping(value = "/hotornot")
	public String hotornot() {	//Model uzywamy zawsze do przesyłania danych do .jsp
		return "hotornot";
	}
	
	@GET
	@RequestMapping(value = "/a")
	public String a() {	//Model uzywamy zawsze do przesyłania danych do .jsp
		return "a";
	}
	
	//Pobiera aktualnego uzytkownika i wysyła dane do modelu
	@GET
	@RequestMapping(value = "/editpassword")
	public String editUserPassword(Model model) {
		String username = UserUtilities.getLoggedUser();
		User user = userService.findUserByEmail(username);
		model.addAttribute("user", user);
		return "editpassword";
	}
	
	@POST
	@RequestMapping(value = "/updatepass")
	public String changeUserPassword(User user, BindingResult result, Model model, Locale locale) {
		String returnPage = null;
		new ChangePasswordValidator().validate(user, result);
		new ChangePasswordValidator().checkPasswords(user.getNewPass(), result);	//user z GET z formularza, dokladnie z metody powyzej
		if (result.hasErrors()) {
			returnPage = "editpassword";
		} else {
			userService.updateUserPassword(user.getNewPass(), user.getEmail());		//Wywolujemy metode z UserService
			returnPage = "afteredit";
			model.addAttribute("message", messageSource.getMessage("passwordChange.success", null, locale)); //Wyswietlamy w gornej linijce ze haslo zostalo pomyslnie zmienione
		}
		return returnPage;
	}
	
	@GET
	@RequestMapping(value = "/editprofil")
	public String changeUserData(Model model) {
		String username = UserUtilities.getLoggedUser();
		User user = userService.findUserByEmail(username);
		model.addAttribute("user", user);
		return "editprofil";
	}
	
	@POST
	@RequestMapping(value = "/updateprofil")
	public String changeUserDataAction(User user, BindingResult result, Model model, Locale locale) {
		String returnPage = null;
		new EditUserProfileValidator().validate(user, result);
		if (result.hasErrors()) {
			returnPage = "editprofil";
		} else {
			userService.updateUserProfile(user.getAge(), user.getId(), user.getAbout());	//user z GET z formularza, dokladnie z metody powyzej
			model.addAttribute("message", messageSource.getMessage("profilEdit.success", null, locale));
			returnPage = "afteredit";
		}
		return returnPage;
	}
	
	//Wiem nie powinno byc tutaj!
	@GET
	@RequestMapping(value = "/users")
	public String allUsersPage(Model model) {
		List<User> usersList = getAllUsers();
		model.addAttribute("userList", usersList);
		return "users";
	}
	
	@PostMapping(value = "/uploadImage")
	public String uploadImage(@RequestParam("imageFile") MultipartFile imageFile) {
		String returnValue = "";
			userService.saveImage(imageFile);
		return "afteredit";
	}
	
	private List<User> getAllUsers(){
		List<User> usersList = userService.findAll();
		return usersList;
	}
}