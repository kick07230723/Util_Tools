import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Filtering2 {
	
	/*인자길이, 알파벳/숫자, SQL예약어 로 블랙리스트를 만들어서 SQL인젝션 대비*/
	public static String filtering(String param) {
		
		/*private final static int maxLength = 16;*/
		//길이제한
		final int maxLength = 30;
		//숫자&알파벳 제외한 문자, SQL예약어
//		final String blackList = "[^\\p{Alnum}]|select|drop|delete|update|insert|create|alter|drop"; //or,and,from,where...?

		final String blackList = "[-!'= *]|select|drop|delete|update|insert|create|alter|drop|from|where|or|and"; 
		
		String maxString = param;
		if(param.length() > maxLength)
			maxString = param.substring(0, maxLength);
		
		Pattern pBlackList = Pattern.compile(blackList);
		
		Matcher mBlackList = pBlackList.matcher(maxString);
		
		if(mBlackList.find())
			return "";
		
		return param;
	}

	
	
	public static void main(String[] args) {
		System.out.println("1 :  "+filtering("mds23904"));
		System.out.println("2 :  "+filtering("s-elect*FROMmeterWhereid='mds-23904'"));		
		//asdf' or 1=1 --'select from 12(~30)
		System.out.println("3 :  "+filtering("1 or 1='1 --"));
		System.out.println("4 :  "+filtering("1' or 1=1 --'select * from cus where id =3"));		
		
	}
		
}
