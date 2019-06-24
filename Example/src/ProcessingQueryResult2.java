import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ProcessingQueryResult2 {
	
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
        	gridData.add(map);
        }
        System.out.println("### gridData  :  "+gridData);	//grid data =쿼리결과
        
        
	    int totalCount =5;
	    Map<String, Object> map2 = new HashMap<String, Object>();
	    map2.put("totalCount", totalCount);
	    gridData.add(map2);
	
	   	System.out.println("### gridData  :  "+gridData);
	
	   	return gridData;
	}

	public static void main(String[] args) {
		List<Map<String, Object>> list = processing();
        List<Map<String, Object>> gridData = new ArrayList<Map<String, Object>>();
        
    	for (Map<String, Object> obj : list) {
    		obj.put("DATE", obj.get("id")+"00");
//    		System.out.println(obj);
		}
    	
        System.out.println(list);
	}
}