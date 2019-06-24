import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ProcessingQueryResult3 {
	
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
        
        
        
        
/*        
        Map<String, Object> map2 = new HashMap<String, Object>();	//map = 쿼리 gird-data 한줄씩 넣기 위해
        for (Map<String, Object> m : gridData) {
        	map2 = new HashMap<String, Object>();
        	map2.put("gridData", m);
        	result.add(map2);
//        	System.out.println("### map2  :  "+map2);
//        	System.out.println("### result  :  "+result);
		}*/
        
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
        
        for (int i=0; i<list.size()-1; i++) {
        	gridData.add(list.get(i));
        }
        Integer totalCnt = (int) list.get(list.size()-1).get("totalCount");
        
        System.out.println(gridData);
        System.out.println(totalCnt);
        
	}
}