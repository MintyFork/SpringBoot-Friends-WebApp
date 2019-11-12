package kamil.appdemo.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//Nasze repozytorium - zapytania tabeli 'user'
@Repository("userRepository") //Nazwa repozytorium
public interface UserRepository extends JpaRepository<User, Integer>{
	//User - wskazujemy że chodzi o tabele User, Integer - klucz glowny tabeli User; Dostarcza metod.
	
	// select * from user where email = ? 
	public User findByEmail(String email);
	
	//Zmiana hasla uzytkownika  
	@Modifying 																									//Będziemy edytowali baze danych
	@Query("UPDATE User u SET u.pass = :newPass WHERE u.email= :email")									//:nazwaPraemetru (Named Query)
	public void updateUserPass(@Param("newPass") String pass, @Param("email") String email);		//@param - Pozwala na przypisanie wartosci, tutaj zapisujemy bez ':'
	
	//Edycja danych uzytkonika
	@Modifying
	@Query("UPDATE User u SET u.age = :newAge, u.about = :newAbout WHERE u.id= :id")
	public void updateUserProfile(@Param("newAge") int newAge, @Param("id") Integer id, @Param("newAbout") String newAbout);
}	