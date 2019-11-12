package kamil.appdemo.mainController;

import javax.ws.rs.GET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kamil.appdemo.user.User;
import kamil.appdemo.user.UserService;
import kamil.appdemo.utilities.UserUtilities;

@Controller
public class MainPageController {

	@Autowired
	private UserService userService;
	
	@GET
	@RequestMapping(value = {"/", "/index"}) //Jeśli taki będzie URL
	public String showMainPage(Model model) {
		String username = UserUtilities.getLoggedUser(); //Pobieranie nazwy aktualnego uzytkonika
		User user = userService.findUserByEmail(username); 
		model.addAttribute("user", user);
		return "index"; //Wtedy przechodzimy na stronę index, która jest pobierana z folderu src/webapp/...
	}
	
}