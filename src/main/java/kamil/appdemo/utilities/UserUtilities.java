package kamil.appdemo.utilities;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


public class UserUtilities {
	
	//Zdobywanie nazwy aktualnego uzytkownika
	public static String getLoggedUser() {
		String username = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication(); //dane autentykacji uzytkownika
		if(!(auth instanceof AnonymousAuthenticationToken)) { //Jeśli uzytkonik nie jest anonimem
			username = auth.getName();						  //pobieramy nazwe uzytkownika (W tym przypadku EMAIL, bo za jego pomocą się logujemy)
		}
		return username;
	}
	
	//Zdobywanie nazwy aktualnego uzytkownika
	public static String getLoggedUserId() {
		String username = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication(); //dane autentykacji uzytkownika
		if(!(auth instanceof AnonymousAuthenticationToken)) { //Jeśli uzytkonik nie jest anonimem
			username = auth.getName();					  //pobieramy nazwe uzytkownika (W tym przypadku EMAIL, bo za jego pomocą się logujemy)
		
		}
		return username;
	}
}

