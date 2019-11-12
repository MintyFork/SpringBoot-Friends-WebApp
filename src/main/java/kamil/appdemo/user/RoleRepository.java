package kamil.appdemo.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Nasze repozytorium - zapytania tabeli 'role' automatyczne.
@Repository("roleRepository") //Nazwa repozytorium
public interface RoleRepository extends JpaRepository<Role, Integer>{ 
	//Role - wskazujemy że chodzi o tabele Role, Integer - klucz glowny tabeli Role; Dostarcza metod.
	
	// select * from role where role = ?
	public Role findByRole(String role); //Generuje się automatycznie

}
