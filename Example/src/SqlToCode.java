
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/*SQL문을 query에 넣고 돌리면 이클립스에서 돌리는 코드로 만들어서 toCode로 저장*/
public class SqlToCode {
	public static void SqlToCode() {

		//file위치= 바탕화면
		String fileLoc = "C:\\Users\\admin\\Desktop\\query\\";

		try {
			File file = new File(fileLoc + "query.txt");
			BufferedReader br = null;

			BufferedWriter out;
			out = new BufferedWriter(new FileWriter(fileLoc + "SQLtoCode.txt", true));
			br = new BufferedReader(new FileReader(file));

			String line;
			String writeLine;
			while ((line = br.readLine()) != null) {
				writeLine = "sb.append(\"\\n" + line + " \");";
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