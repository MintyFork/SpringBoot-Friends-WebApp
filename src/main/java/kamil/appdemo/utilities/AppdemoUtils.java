package kamil.appdemo.utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Klasa sprawdzająca poprawność hasla lub email
public class AppdemoUtils {
	
	public static boolean checkEmailOrPassword(String pattern, String pStr) {
		
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(pStr);
		return m.matches();
	}
}
