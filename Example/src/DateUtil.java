import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtil {

    public static void main(String args[]){

    	dateByCalendar();
    	dateByDate();
    }
    
    public static void dateByCalendar() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar c1 = Calendar.getInstance();
        String strToday = sdf.format(c1.getTime());
        System.out.println("### dateByCalendar ###");
        System.out.println(strToday);
    }

    public static void dateByDate() {
    	SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss.SSSSS");
    	SimpleDateFormat format2 = new SimpleDateFormat ( "yyyy년 MM월dd일 HH시mm분ss초");
    			
    	String format_time1 = format1.format (System.currentTimeMillis());
    	String format_time2 = format2.format (System.currentTimeMillis());
    	        				 		
    	System.out.println("### dateByDate ###");
    	System.out.println(format_time1);
    	System.out.println(format_time2);
    }
}
