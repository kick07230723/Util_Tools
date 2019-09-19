import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Example_Pattern {

	public static void main(String[] args) {
		
		Pattern p = Pattern.compile("^\\d{18}\\s\\d{4}-\\d{2}-\\d{2}");
		Pattern pSeq = Pattern.compile("^\\d{18}");
		Pattern pYYYYMMDD = Pattern.compile("\\s\\d{4}-\\d{2}-\\d{2}\\s");
		Pattern pLoglevel = Pattern.compile("\\s[a-zA-Z]{4,5}\\s");
		Pattern pFilename = Pattern.compile("\\s[\\S*]\\s");
		Pattern pMessage = Pattern.compile("\\-\\s\\d{18}$");
        String inputVal;
        
        String[] array = null;
        
        File targetFile = new File("C:\\Users\\admin\\Downloads\\log2.txt");
        
        try{
		        //업로드한 파일 1줄씩 읽기
		        FileReader filereader = new FileReader(targetFile);
		        BufferedReader bufReader = new BufferedReader(filereader);
		        String line = "";
		        while((line = bufReader.readLine()) != null){
		            Matcher m = p.matcher(line);
//		            Matcher mSeq = pSeq.matcher(line);
//		            Matcher mYYYYMMDD = pYYYYMMDD.matcher(line);
//		            Matcher mLoglevel = pLoglevel.matcher(line);
//		            Matcher mFilename = pFilename.matcher(line);
//		            Matcher mMessage = pMessage.matcher(line);
		            
		            if(m.find()){
		            	array = line.split(" ");
//		            	mSeq.replaceAll(" mSeq ");
//		            	mYYYYMMDD.replaceAll(" mYYYYMMDD ");
//		            	mLoglevel.replaceAll(" mLoglevel ");
//		            	mFilename.replaceAll(" mFilename ");
//		            	mMessage.replaceAll(" mMessage ");
//		            	System.out.println("seq : "+mSeq.toString()+"  date : "+mYYYYMMDD.toString()+" loglevel : "+mLoglevel.toString()+"  fileName : "+mFilename.toString()+"  message : "+mMessage.toString());
		            	String YYYY = array[1].substring(0, 4);
		            	String MM = array[1].substring(5, 7);
		            	String DD = array[1].substring(8, 10);
		            	String hh = array[1].substring(11, 13);
		            	String mm = array[1].substring(14, 16);
		            	String ss = array[1].substring(17);
		            	Date today = new Date();
		            	SimpleDateFormat timestamp = new SimpleDateFormat("yyyy-MM-dd,hh:mm:ss");
		            	System.out.println("timestamp : "+timestamp.format(today));
//		            	System.out.println("y:"+YYYY+"  m:"+MM+"  d:"+DD+"  h:"+hh+"  m:"+mm+"  s:"+ss);
//		            	System.out.println("seq : "+array[0]+"  date : "+array[1]+" loglevel : "+array[2]+"  fileName : "+array[3]+"  message : "+line.substring(line.indexOf("- ")));
		            	}
		            else{System.out.println(line);}    
		        }
		        bufReader.close();
		    }catch (Exception e) {
		    	System.out.println(e);
		    }
        
		
	}
}
