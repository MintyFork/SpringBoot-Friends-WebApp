package kamil.appdemo.user;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

//DAO - Zapewnia dostęp do warstwy danych
//Zewnetrzne metody edycji itp. bazy danych (Bezpieczne)
public interface UserService {
	
	public User findUserByEmail(String email); //Wyszukiwanie uzytkownika
	public void saveUser(User user); //Zapisywanie uzytkonika
	public void updateUserPassword(String newPassword, String email);
	public void updateUserProfile(int newAge, int id, String newAbout);
	public List<User> findAll();
	public void saveImage(MultipartFile imageFile);
}