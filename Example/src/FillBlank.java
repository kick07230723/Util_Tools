
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FillBlank {
	public static void fillBlank(String text) {

		if(text.length()<255) {
			int blankNum = 255-text.length();
			
			String blank = String.format("%"+ blankNum +"s", " ");
			
			text += blank;
		}
		System.out.println(text);
		
	}

	public static void main(String[] args) {

		fillBlank("123");

	}
}