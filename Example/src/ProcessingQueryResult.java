import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ProcessingQueryResult {
	
	public static List<Map<String, Object>> processing() {
		//return값
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		
		//query.list();
		List<Map<String, Object>> gridData = new ArrayList<Map<String,Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        for(int i=0; i<5; i++) {
        	Random random = new Random();
        	map = new HashMap<String, Object>();
        	map.put("id",i);
        	map.put("count",random.nextInt(10)+40);
//        	map.put("dailyBillingCount",random.nextInt(20)+20);
//        	map.put("powerQualityCount",random.nextInt(20)+20);
//        	map.put("date",(i<10)? "2019010"+i : "201901"+i);
        	gridData.add(map);
        }
        System.out.println("### gridData  :  "+gridData);	//grid data =쿼리결과
        
        Map<String, Object> map2 = new HashMap<String, Object>();	//map = 쿼리 gird-data 한줄씩 넣기 위해
        
        for (Map<String, Object> m : gridData) {
        	map2 = new HashMap<String, Object>();
        	map2.put("gridData", m);
        	result.add(map2);
//        	System.out.println("### map2  :  "+map2);
//        	System.out.println("### result  :  "+result);
		}
        
	    int totalCount =5;
	    map2 = new HashMap<String, Object>();
	   	map2.put("totalCount", totalCount);
	   	result.add(map2);
	
	   	System.out.println("### map2  :  "+map2);
	   	System.out.println("### result  :  "+result);
	
	   	return result;
	}

	public static void main(String[] args) {
		List<Map<String, Object>> gridObject = processing();
		System.out.println("gridObject 0 ###  :"+gridObject.get(0).values());
		System.out.println("gridObject 1 ###  :"+gridObject.get(1).get("gridData"));
		System.out.println("gridObject 2 ###  :"+gridObject.get(2));
		System.out.println("gridObject 3 ###  :"+gridObject.get(3));
		System.out.println("gridObject 4 ###  :"+gridObject.get(4));
		System.out.println("gridObject 5 ###  :"+gridObject.get(5).get("totalCount"));
		System.out.println(gridObject);
		

	}
}