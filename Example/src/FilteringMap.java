import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilteringMap {
	
	/*인자길이, 알파벳/숫자, SQL예약어 로 블랙리스트를 만들어서 SQL인젝션 대비*/
	public static Map<String, Object> doFiltering(Map<String, Object> conditionMap) {
		
		/*private final static int maxLength = 16;*/
		//길이제한
		final int maxLength = 20;
		//숫자&알파벳 제외한 문자, SQL예약어
//		final String blackList = "[^\\p{Alnum}]|select|drop|delete|update|insert|create|alter|drop"; //or,and,from,where...?
		final String blackList = "[!#$^&*:;= '\"()<>/]|--|select|drop|delete|update|insert|create|alter|drop|from|where|and"; 

		Set key = conditionMap.keySet();
		
		Map<String, Object> condition = new HashMap<String, Object>();
		
		for (Object obj : key) {
			String strObj = obj.toString();

			String param = nullToBlank(conditionMap.get(strObj));
			
			if(!param.equals("") && conditionMap.get(strObj).getClass().getSimpleName().equals("String")) {
				//내용이 있고, string인 파라미터
				
				if(param.length() > maxLength)
					param = param.substring(0, maxLength);
				
				Pattern pBlackList = Pattern.compile(blackList);
				
				Matcher mBlackList = pBlackList.matcher(param);
				
				if(!mBlackList.find()) {
					condition.put(strObj, param);
				}else {
					condition.put(strObj, "");
				}
			}else {
				//내용이 없거나 String이 아닌 타입
				condition.put(strObj, conditionMap.get(strObj));
			}
		}
		
		return condition;
	}

	public static String nullToBlank(Object comment) {
		if (comment == null) return "";
		return String.valueOf(comment).trim();
	}
	
	
	public static void main(String[] args) {
		 Map<String, Object> condition = new HashMap<String, Object>();
	     condition.put("1", "qwer123"); //
	     condition.put("2", "select * from"); //
	     condition.put("3", "' or 1=1--"); //
	     condition.put("4", "like 'some%'"); //
	     condition.put("5", "'or 1=1;*"); //
	     condition.put("6", "A'Or 1=1"); //
	     condition.put("7", "') or (1=1)");
	     condition.put("8", "N'text'="); //
	     condition.put("9", "1234567890abcdefghijklmnop"); 
	     condition.put("10", ":normal");
	     condition.put("limit", 123456);
	     condition.put("page", null);
	     System.out.println(condition);
	     System.out.println(condition.get("limit").getClass().getSimpleName());
//	     System.out.println(filtering(condition));
	     condition = doFiltering(condition);
	     System.out.println(condition);
	     System.out.println(condition.get("limit").getClass().getSimpleName());
	     
//	     condition = StringUtil.doFiltering(condition);
	}
	
}
