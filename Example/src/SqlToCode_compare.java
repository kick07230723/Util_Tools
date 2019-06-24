
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/*SQL문을 query에 넣고 돌리면 이클립스에서 돌리는 코드로 만들어서 toCode로 저장*/
public class SqlToCode_compare {
	public static void SqlToCode() {

		//file위치= 바탕화면
		String fileLoc = "C:\\Users\\admin\\Desktop\\query\\";

		try {
			File file = new File(fileLoc + "mark.txt");
			BufferedReader br = null;

			BufferedWriter out;
			out = new BufferedWriter(new FileWriter(fileLoc + "SQLtoCode_compare.txt", true));
			br = new BufferedReader(new FileReader(file));

			String line;
			String writeLine;
			while ((line = br.readLine()) != null) {
				writeLine = "select * from( ";
				for(int i=0; i<9; i++) {
					writeLine += "            select lp.MDEV_ID, day.YYYYMMDD, substr(lp.YYYYMMDDHHMISS,9,2) hour, lp.VALUE as LP_VAL, day.VALUE_1"+i+" as DAY_VAL "+
							"            from DAY_EM_TOBE day " + 
							"                inner join LP_EM_TOBE lp " + 
							"                    on lp.MDEV_ID = day.MDEV_ID " + 
							"                        and substr(lp.YYYYMMDDHHMISS,0,8) = day.YYYYMMDD " + 
							"                        and day.MDEV_ID = '"+line+"' " + 
							"                        and lp.CHANNEL = day.CHANNEL " + 
							"                        and substr(lp.YYYYMMDDHHMISS,9,2) = '1"+i+"' " + 
							"            where YYYYMMDDHHMISS like '%4500' " + 
							"                 and lp.CHANNEL = 1 ";
					if(i != 8)
						writeLine += " union all ";
				}
				writeLine += " );";
				
				out.write(writeLine);
				out.newLine();
			}
			//닫기
			br.close();
			out.flush();
			out.close();

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("실패");
		}
		System.out.println("sql -> code 변환 성공!");
	}

	public static void main(String[] args) {

		SqlToCode();

	}
}