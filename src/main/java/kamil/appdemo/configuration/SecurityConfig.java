package kamil.appdemo.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/*
 * Konfiguracja Spring Security
 * Dostępność linków dla określonych grup z uprawnieniami.
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private BCryptPasswordEncoder bcp; //Kodowanie i rozkodowywanie haseł.
	
	@Autowired
	private DataSource ds; //Dostęp do bazy danych
	
	@Value("${spring.queries.users-query}") //Pobieranie użytkownika, hasło i czy jest aktywny. Więcej w application.properties.
	private String usersQuery;
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().usersByUsernameQuery(usersQuery)
		.authoritiesByUsernameQuery(usersQuery) //Ta wiem, nie ma autoryzacji
		.dataSource(ds).passwordEncoder(bcp);
	} //Autoryzacja itp.
	
	protected void configure(HttpSecurity httpSec) throws Exception { //Zasady przkierowywania itp.
		httpSec //Bezpieczeństwo wywoływania, 
		.authorizeRequests()
		.antMatchers("/login").permitAll()
		.antMatchers("/").authenticated()					//Tutaj dopisujemy strony ktore niezalogowaniu uzytkownicy moga odwiedzic
		.antMatchers("/index").authenticated()
		.antMatchers("/register").permitAll()
		.antMatchers("/adduser").permitAll()
		//.antMatchers("/users").permitAll()
		//permitALL - Dostępne dla wszystkich.
		//.antMatchers("/admin").hasAuthority("ROLE_ADMIN") //hasAuthority - Dla określonej roli.
		.anyRequest().authenticated()
		.and().csrf().disable()
		.formLogin() //Formularz logowania
		.loginPage("/login") //Dane w application.properties, tutaj zostanie przekierowany uzytkownik gdy strona dostanie zly url
		.failureUrl("/login?error=true")//Jeśli wystąpi błąd podczas logowania zostaniemy przeniesieni na storne /login?error=true
		.successForwardUrl("/").usernameParameter("email") //Jeśli logowanie przebiegnie pomyślnie zostaniemy przeniesieni na stronę główną.
		.passwordParameter("pass")
		.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")) //Niszczenie sesji, wylogowywanie.
		.logoutSuccessUrl("/")
		.and().exceptionHandling().accessDeniedPage("/denied"); //Dostęp zabronieony, np. Gdy ktoś nie posiada upoważnienia/roli.
	}
	
	public void configure(WebSecurity webSec) throws Exception {
		webSec.ignoring() //Co webSec ma zignorować jeśli chodzi o bezpieczeństwo.
		.antMatchers("/resources/**", "/statics/**", "/css/**", "/js/**", "/images/**", "/incl/**", "/img/**", "/scss/**", "/vendor/**"); //Silnik nie będzie wymagał autentykacji dla katalogów...
	}

}