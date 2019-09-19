import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class MakeFolder {

	  public static void main(String[] args) throws IOException{
		    //해당 경로에 있는 파일에 대한 정보를 담는다.
		    //객체의 인자인 문자열은 디렉토리로 파일을 생성하거나,
		    //원하는 위치의 디렉토리를 설정하면 된다.
		    File fileDir = new File("C:\\Users\\admin\\Desktop\\query\\");
		    //list() 함수를 사용하면 해당 디렉토리의 파일, 디렉토리 정보를 모두 볼 수 있다.
		    String fileList[] = fileDir.list();
		    
		    Arrays.stream(fileList).forEach(System.out::println);
		    
		    File fileList2[] = fileDir.listFiles();
		    Arrays.stream(fileList2).forEach(file -> {
		      if(file.isFile()){
		        System.out.println("[  file   ]\t:"+file);
		      }else if(file.isDirectory()){
		        System.out.println("[directory]\t:"+file);
		      }else{
		        System.out.println("[??????]\t:"+file);
		      }
		    });
		    
		    //폴더 생성
		    File newFile = new File("C:\\Users\\admin\\Desktop\\query\\abc");
		    if(newFile.mkdir()){    //파일이 생성되는 시점
		        System.out.println("파일이 생성되었습니다.");
		      }else {
		        System.out.println("파일을 생성하지 못했습니다.");
		      }
		    
		    //파일 생성
		    
		  }
}
