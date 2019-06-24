import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Filtering {
	
	/*인자길이, 알파벳/숫자, SQL예약어 로 블랙리스트를 만들어서 SQL인젝션 대비*/
	public static String doFiltering(String param) {
		
		//길이제한
		final int maxLength = 20;
		//숫자&알파벳 제외한 문자, SQL예약어
		final String blackList = "[^\\p{Alnum}]|select|drop|delete|update|insert|create|alter|drop|from|where|or|and";

		String maxLengthString = param;
		if(param.length() > maxLength) {
			maxLengthString = param.substring(0, maxLength);
		}
		
		Pattern pBlackList = Pattern.compile(blackList, Pattern.CASE_INSENSITIVE);
		
		Matcher mBlackList = pBlackList.matcher(maxLengthString);
		
		return mBlackList.replaceAll("");
	}

	
	
	public static void main(String[] args) {
		System.out.println("1 :  "+doFiltering("100010001008"));
		System.out.println("2 :  "+doFiltering("asdf' or 1=1 --'select FRom 123456789"));		
		//asdf' or 1=1 --'select from 12(~30)
		System.out.println("3 :  "+doFiltering("1 or 1='1 --"));
		System.out.println("4 :  "+doFiltering("1' or 1=1 --'SELECT * from meter where id =3"));		
		
		String a="12345678901234567890";
		System.out.println("5 :  "+doFiltering(a));
	}
		
}
