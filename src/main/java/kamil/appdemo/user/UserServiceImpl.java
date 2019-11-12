package kamil.appdemo.user;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kamil.appdemo.user.Role;
import kamil.appdemo.user.User;
import kamil.appdemo.utilities.UserUtilities;

//Implementacja UserSevice (DAO)
//Zamiana wewnętrzynych metod na zewnętrzne
@Service("userService") 
@Transactional
public class UserServiceImpl implements UserService {
	
	//Dodajemy repozytoria
	@Autowired 
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder; //Bean szyfrujacy
	
	//Przyslaniamy metody (Implementujemy tresci zapytan)
	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void saveUser(User user) { //Odbieramy |dane| uzytkownika
		user.setPass(bCryptPasswordEncoder.encode(user.getPass())); //Szyfrujemy je i ustawiamy wartość password
		
		userRepository.save(user); //Gotowa metoda hibernate do zapisu do bazy danych
	}

	@Override
	public void updateUserPassword(String newPass, String email) {
		userRepository.updateUserPass(bCryptPasswordEncoder.encode(newPass), email);
	}

	@Override
	public void updateUserProfile(int newAge, int id, String newAbout) {
		userRepository.updateUserProfile(newAge, id, newAbout);						//Dane z formularza
	}

	@Override
	public List<User> findAll() {
		List<User> userList = userRepository.findAll();			//Gotowa metoda JPA Repository
		return userList;
	}

	@Override
	public void saveImage(MultipartFile imageFile){
		String folder = "src\\main\\resources\\static\\profiles\\"; //Zapisujemy tutaj img 
		byte[] bytes = null;
		try {
			bytes = imageFile.getBytes();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} //Ciąg bajtów reprezentujący img
		Path path = Paths.get(folder + userRepository.findByEmail(UserUtilities.getLoggedUser()).getId() + ".jpg"); //Sciezka do pliku i jego nazwa
		System.out.println(folder + userRepository.findByEmail(UserUtilities.getLoggedUser()).getId() + ".jpg");
		try {
			Files.write(path, bytes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}