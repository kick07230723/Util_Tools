
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class MakeSQL {
	
	public static void makeSQL() {

		//file위치= 바탕화면
		String fileLoc = "C:\\Users\\admin\\Desktop\\query\\";
		Random random = new Random();
		try {
			
			String txt ="";
			for (int i=88; i<89; i++) {
				int randomInt = random.nextInt(999);
				int randomInt1 = random.nextInt(999);
				int randomInt2 = random.nextInt(9999);
				int randomInt3 = random.nextInt(9999);
				int randomInt4 = random.nextInt(59);
				double r1 = random.nextDouble()*3;
				double r2 = random.nextDouble()*3;
				double r3 = random.nextDouble()*3;
				double r4 = random.nextDouble()*3;
				double r5 = random.nextDouble()*3;

				
				int modemId = 2000+i;
				String deviceSerial = "6B2300AA2"+randomInt3;
				int meterId = 11000+i;
				int mdsId = 10000000 +i;
				int cusId = 10000+i;
				String cusName = i+" kim ji whan";
				String customerno = "201810-01-"+i;
				int conId = 100100+i;
				String conNum = "nuri_contract"+i;
		
				
				System.out.println("modemId : "+modemId+",   deviceSerial : "+deviceSerial);
				System.out.println("meterId : "+meterId+",   mdsId : "+mdsId);
				System.out.println("cusId : "+cusId+",   cusName : "+cusName);
				System.out.println("conId : "+conId+",   conNum : "+conNum);
				
				txt+="insert into modem (modem, id, comm_state, device_serial, fw_revision, fw_ver, GPIOX, GPIOY, hw_ver, install_date, last_link_time, location_id, \r\n" + 
						"mcu_id, modem_type, modem_status, name_space, protocol_type, protocol_version, supplier_id, sw_ver, ipv6_address, lqi, devicemodel_id, band_plan, modulation ) \r\n" + 
						"values('PLCIU', "+modemId+", 1, '"+deviceSerial+"', 0, 1.1, -2.732582, 5.27823, 0.5, 20180805100530, '20180926123055', 12,\r\n" + 
						"27, 'PLCIU', 48, 'QA', 'IP', 0102, 12, 0.11, 'FD00:0:29B4:0:B:1200:2:0183', 10, 24, 6, 0 );"
						+ "insert into meter (meter, id, gpiox, gpioy, install_date, last_read_date, location_id, mds_id, meter_status, metertype_id, devicemodel_id, modem_id, supplier_id )\r\n" + 
						"values('EnergyMeter', "+meterId+", -2.732582, 5.27823, 20180805100530, '20180929123055', 12, "+mdsId+", 195, 102, 16, "+modemId+", 12);"
						+ "insert into customer (id, email, last_charge_date, mobilenumber, name, smsyn, supplier_id, address, customerno, demandresponse, mobileno )\r\n" + 
						"values("+cusId+", '"+i+"owner@gmail.com', '20181001122852', 01055699878, '"+cusName+"', 0, 12, '3, Yongmin-ro 3beon-gil, Uijeongbu-si, Gyeonggi-do', '"+customerno+"', 0, 0103"+i+"5260433);\r\n" 
						+ "insert into contract (id, charge_available, chargedcredit, contract_date, contract_number, creditstatus_id, credittype_id, currentarrears, currentcredit, customer_id, \r\n" + 
						"firstarrears, is_net_metering, is_sts, location_id, meter_id, notification_interval, notification_period, notification_time, prepay_start_time, prepaymentthreshold, \r\n" + 
						"servicetype2, servicetype_id, sic_id, status_id, supplier_id, tariffindex_id )\r\n" + 
						"values("+conId+", 1, 30, '20180728110533', '"+conNum+"', 273, 265, 0, "+randomInt2+", "+cusId+",\r\n" + 
						"0, 0, 0, 13, "+meterId+", 1, 1, 13, '201810031611"+randomInt4+"', 10,\r\n" + 
						"'NewService', 308, 669, 255, 12,1 ) "
						+ "; \r\n";
				
				for (int j=3; j<10; j++) {
					txt+="insert into day_em (channel, yyyymmdd, dst, mdev_id, mdev_type, basevalue, contract_id, day_type, location_id, meter_id,\r\n" + 
							"meteringtype, modem_id, sic, supplier_id, total,\r\n" + 
							"value_00, value_01, value_02, value_03, value_04, value_05, value_06, value_07, value_08, value_09, value_10, value_11, value_12, value_13, value_14, value_15,\r\n" + 
							"value_16, value_17, value_18, value_19, value_20, value_21, value_22, value_23, writedate )\r\n" + 
							"values(1, '2018092"+j+"', 0, "+mdsId+", 011, 10, "+conId+", 1, 12, "+meterId+",\r\n" + 
							"1001, "+modemId+", 12, 12, 0,\r\n" + 
							"0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '20180928165055')\r\n;"
							+ ""
							+ "";
				}
				txt+="insert into day_em (channel, yyyymmdd, dst, mdev_id, mdev_type, basevalue, contract_id, day_type, location_id, meter_id,\r\n" + 
						"meteringtype, modem_id, sic, supplier_id, total,\r\n" + 
						"value_00, value_01, value_02, value_03, value_04, value_05, value_06, value_07, value_08, value_09, value_10, value_11, value_12, value_13, value_14, value_15,\r\n" + 
						"value_16, value_17, value_18, value_19, value_20, value_21, value_22, value_23, writedate )\r\n" + 
						"values(1, '20180922', 0, "+mdsId+", 011, 10, "+conId+", 1, 12, "+meterId+",\r\n" + 
						"1001, "+modemId+", 12, 12, "+r3+",\r\n" + 
						"0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '20180928165055')\r\n;"
						+ ""
						+ "";
				txt+="insert into day_em (channel, yyyymmdd, dst, mdev_id, mdev_type, basevalue, contract_id, day_type, location_id, meter_id,\r\n" + 
						"meteringtype, modem_id, sic, supplier_id, total,\r\n" + 
						"value_00, value_01, value_02, value_03, value_04, value_05, value_06, value_07, value_08, value_09, value_10, value_11, value_12, value_13, value_14, value_15,\r\n" + 
						"value_16, value_17, value_18, value_19, value_20, value_21, value_22, value_23, writedate )\r\n" + 
						"values(1, '20180921', 0, "+mdsId+", 011, 10, "+conId+", 1, 12, "+meterId+",\r\n" + 
						"1001, "+modemId+", 12, 12, "+r4+",\r\n" + 
						"0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '20180928165055')\r\n;"
						+ ""
						+ "";
				txt+="insert into day_em (channel, yyyymmdd, dst, mdev_id, mdev_type, basevalue, contract_id, day_type, location_id, meter_id,\r\n" + 
						"meteringtype, modem_id, sic, supplier_id, total,\r\n" + 
						"value_00, value_01, value_02, value_03, value_04, value_05, value_06, value_07, value_08, value_09, value_10, value_11, value_12, value_13, value_14, value_15,\r\n" + 
						"value_16, value_17, value_18, value_19, value_20, value_21, value_22, value_23, writedate )\r\n" + 
						"values(1, '20180920', 0, "+mdsId+", 011, 10, "+conId+", 1, 12, "+meterId+",\r\n" + 
						"1001, "+modemId+", 12, 12, "+r2+",\r\n" + 
						"0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '20180928165055')\r\n;"
						+ ""
						+ "";
				txt+="insert into day_em (channel, yyyymmdd, dst, mdev_id, mdev_type, basevalue, contract_id, day_type, location_id, meter_id,\r\n" + 
						"meteringtype, modem_id, sic, supplier_id, total,\r\n" + 
						"value_00, value_01, value_02, value_03, value_04, value_05, value_06, value_07, value_08, value_09, value_10, value_11, value_12, value_13, value_14, value_15,\r\n" + 
						"value_16, value_17, value_18, value_19, value_20, value_21, value_22, value_23, writedate )\r\n" + 
						"values(1, '20180919', 0, "+mdsId+", 011, 10, "+conId+", 1, 12, "+meterId+",\r\n" + 
						"1001, "+modemId+", 12, 12, "+r5+",\r\n" + 
						"0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '20180928165055')\r\n;"
						+ ""
						+ "";
				txt+= "insert into month_em (channel, yyyymm, dst, mdev_id, mdev_type, basevalue, contract_id, location_id, meter_id,\r\n" + 
				"device_id, meteringtype, modem_id, supplier_id, total,\r\n" + 
				"value_01, value_02, value_03, value_04, value_05, value_06, value_07, value_08, value_09, value_10, value_11, value_12, value_13, value_14, value_15,\r\n" + 
				"value_16, value_17, value_18, value_19, value_20, value_21, value_22, value_23, value_24, value_25, value_26, value_27, value_28, value_29, value_30, value_31, writedate )\r\n" + 
				"values(1, '201808', 0, "+mdsId+", 011, 10, "+conId+", 12, "+meterId+",\r\n" + 
				"1001, 1001, "+modemId+", 12, 0,\r\n" + 
				"2.045, "+r1+", 1.556, "+r2+", 1.0, "+r3+", 1.02, "+r4+", 1.25, "+r1+", 1.111, 1.053, "+r1+", "+r5+", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '20180928165055')\r\n;"
				+ "\r\n"
				+ "\r\n ";
				txt+= "insert into month_em (channel, yyyymm, dst, mdev_id, mdev_type, basevalue, contract_id, location_id, meter_id,\r\n" + 
				"device_id, meteringtype, modem_id, supplier_id, total,\r\n" + 
				"value_01, value_02, value_03, value_04, value_05, value_06, value_07, value_08, value_09, value_10, value_11, value_12, value_13, value_14, value_15,\r\n" + 
				"value_16, value_17, value_18, value_19, value_20, value_21, value_22, value_23, value_24, value_25, value_26, value_27, value_28, value_29, value_30, value_31, writedate )\r\n" + 
				"values(1, '201809', 0, "+mdsId+", 011, 10, "+conId+", 12, "+meterId+",\r\n" + 
				"1001, 1001, "+modemId+", 12, 0,\r\n" + 
				"0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '20180928165055')\r\n;"
				+ "\r\n"
				+ "\r\n ";
				
			}
			
            // 파일 객체 생성
            File file = new File(fileLoc + "insertQuery.txt") ;
             
            // true 지정시 파일의 기존 내용에 이어서 작성
            FileWriter fw = new FileWriter(file, true) ;
             
            // 파일안에 문자열 쓰기
            fw.write(txt);
            fw.flush();
 
            // 객체 닫기
            fw.close();

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("실패..");
		}
		System.out.println("성공!");
	}

	public static void main(String[] args) {

		makeSQL();

	}
}