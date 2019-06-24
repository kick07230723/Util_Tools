
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SqlToCode2 {
	public static void SqlToCode() {

		//file위치= 바탕화면
		String fileLoc = "C:\\Users\\admin\\Desktop\\query\\";

		try {
			File file = new File(fileLoc + "toSQL.txt");
			BufferedReader br = null;

			BufferedWriter out;
			out = new BufferedWriter(new FileWriter(fileLoc + "resultToSQL.txt", true));
			br = new BufferedReader(new FileReader(file));

			String line;
			String writeLine;
			while ((line = br.readLine()) != null) {
				writeLine = " '" + line + "',";
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