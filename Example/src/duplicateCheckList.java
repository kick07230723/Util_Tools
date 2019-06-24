
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class duplicateCheckList {
	public static void duplicateCheckList() {
		
		List<String> resultList1 = new ArrayList<String>(Arrays.asList("1", "2","3", "4","11", "12","13", "14","21", "22","23", "24"));
		
		List<String> resultList2 = new ArrayList<String>(Arrays.asList("1", "2","3", "4", "21", "22","23", "24"));
		
		resultList1.remove(resultList2);
		
		System.out.println(resultList1);
		
		
	}



	public static void main(String[] args) {

		duplicateCheckList();

	}
}