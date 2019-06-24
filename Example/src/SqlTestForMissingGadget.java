
/*toSQL에 있는 ID를 한줄에 하나씩 써서 resultToSQL로 출력*/
public class SqlTestForMissingGadget {
	
	public static String sqlEx(String targetTab) throws Exception {
		
		String result = null;
		
/*		Map<String, Object> conditionMap = new HashMap<String, Object>();
		conditionMap.put("targetTab", "dcu");
		conditionMap.put("isCount", "false");
		conditionMap.put("searchStartDate", "20190221");
		conditionMap.put("deleteStatus", "164");
		
		String deleteStatus = StringUtil.nullToBlank(conditionMap.get("deleteStatus")); //code 1.3.3.9
		String searchStartDate = StringUtil.nullToBlank(conditionMap.get("searchStartDate"));
		String targetTab = StringUtil.nullToBlank(conditionMap.get("targetTab")); //dcu, location, model
*/		
		String deleteStatus = "164"; //code 1.3.3.9
		String searchStartDate = "20190224";
//		String targetTab = "location"; //dcu, location, model
		
		if(searchStartDate.isEmpty() || targetTab.isEmpty())
			return null;
		
		String yyyymmdd = "20190224";
		StringBuilder subQuery = new StringBuilder();
		StringBuilder mainQuery = new StringBuilder();
		
//		Date sDate = DateTimeUtil.getDateFromYYYYMMDD(searchStartDate);
//		Calendar sCalendar = Calendar.getInstance();
//		sCalendar.setTime(sDate);
//		
//		String yyyymmdd = DateTimeUtil.getShortDateString(sCalendar.getTimeInMillis());
		
		subQuery.append("\n 				SELECT ");
		subQuery.append("\n 					METER.ID, METER.METER, ").append(yyyymmdd).append(" YYYYMMDD, METER.MDS_ID METER_ID, METER.LOCATION_ID, ");
		subQuery.append("\n 					METER.MODEM_ID, METER.SUPPLIER_ID, METER.LAST_READ_DATE, NVL(LP_EM.LP_VALUE_CNT,0) LP_VALUE_CNT, 60*24/NVL(METER.LP_INTERVAL, 60) LP_TOTAL_INTERVAL");
		subQuery.append("\n 				FROM ");
		subQuery.append("\n 					AIMIR.METER METER ");		
		subQuery.append("\n 				LEFT JOIN ");
		subQuery.append("\n 				(");
		subQuery.append("\n 					SELECT ");
		subQuery.append("\n 						MDEV_ID, SUM(VALUE_CNT) LP_VALUE_CNT ");
		subQuery.append("\n 					FROM ");
		subQuery.append("\n 						AIMIR.LP_EM ");
		subQuery.append("\n 					WHERE ");
		subQuery.append("\n 						YYYYMMDDHH BETWEEN '").append(yyyymmdd + "00").append("' AND '").append(yyyymmdd + "23").append("' AND CHANNEL = 1 ");
		subQuery.append("\n 					GROUP BY ");
		subQuery.append("\n 						MDEV_ID ");
		subQuery.append("\n 				) LP_EM ON METER.MDS_ID = LP_EM.MDEV_ID ");

		
		if(targetTab.equals("dcu")) {
			StringBuilder mmiuQuery = new StringBuilder();
			mmiuQuery.append("\n 	SELECT ");
			mmiuQuery.append("\n 		'MMIU' SYSID, COUNT(1) MISSINGCNT, ");
			mmiuQuery.append("\n 		(SELECT COUNT(me.mds_id) FROM METER me, modem mo WHERE me.MODEM_ID = mo.ID AND mo.mcu_id is null AND me.METER_STATUS != ").append(deleteStatus).append(") METERCNT ");
			mmiuQuery.append("\n 	FROM ");
			mmiuQuery.append("\n 	( ");
			mmiuQuery.append("\n 		 ").append(subQuery);
			mmiuQuery.append("\n 				INNER JOIN ");
			mmiuQuery.append("\n 					MODEM ON MODEM.ID = METER.MODEM_ID AND MODEM.MCU_ID IS NULL ");
			mmiuQuery.append("\n 				WHERE ");
			mmiuQuery.append("\n 					METER.METER_STATUS != '").append(deleteStatus).append("' ");
			mmiuQuery.append("\n 				ORDER BY ");
			mmiuQuery.append("\n 					YYYYMMDD, METER_ID ");
			mmiuQuery.append("\n 	)M ");
			mmiuQuery.append("\n 	WHERE ");
			mmiuQuery.append("\n 		1 = 1 ");
			mmiuQuery.append("\n 		AND LP_VALUE_CNT < LP_TOTAL_INTERVAL  ");			
			mmiuQuery.append("\n 	GROUP BY ");
			mmiuQuery.append("\n 		YYYYMMDD ");
			
			StringBuilder zruQuery = new StringBuilder();
			
			zruQuery.append("\n 	UNION ALL ");
			zruQuery.append("\n 		SELECT ");
			zruQuery.append("\n 			MCUID, ");
			zruQuery.append("\n 			METERCNT, ");
			zruQuery.append("\n 			( ");
			zruQuery.append("\n 				SELECT COUNT(1) FROM ");
			zruQuery.append("\n 				( ");
			zruQuery.append("\n 					 ").append(subQuery).append(" ");
			zruQuery.append("\n 				INNER JOIN ");
			zruQuery.append("\n 					MODEM ON MODEM.ID = METER.MODEM_ID AND MODEM.MODEM = 'ZRU' ");	
			zruQuery.append("\n 				INNER JOIN ");
			zruQuery.append("\n 					MCU ON MCU.ID = MODEM.MCU_ID ");
			zruQuery.append("\n 				WHERE ");
			zruQuery.append("\n 					METER.METER_STATUS != '").append(deleteStatus).append("' ");
			zruQuery.append("\n 				)SA WHERE LP_VALUE_CNT < LP_TOTAL_INTERVAL AND SA.SYSID = MCUID ");
			zruQuery.append("\n 			) AS MissCnt ");			
			zruQuery.append("\n 		FROM ");
			zruQuery.append("\n 		( ");
			zruQuery.append("\n 			SELECT ");
			zruQuery.append("\n 				SYSID MCUID, COUNT(SYSID) METERCNT ");
			zruQuery.append("\n 			FROM ");
			zruQuery.append("\n 			( ");
			zruQuery.append(subQuery);
			zruQuery.append("\n 				INNER JOIN ");
			zruQuery.append("\n 					MODEM ON MODEM.ID = METER.MODEM_ID AND MODEM.MODEM = 'ZRU' ");	
			zruQuery.append("\n 				INNER JOIN ");
			zruQuery.append("\n 					MCU ON MCU.ID = MODEM.MCU_ID ");
			zruQuery.append("\n 				WHERE ");
			zruQuery.append("\n 					METER.METER_STATUS != '").append(deleteStatus).append("' ");
			zruQuery.append("\n 				ORDER BY ");
			zruQuery.append("\n 					YYYYMMDD, METER_ID ");
			zruQuery.append("\n 			)A GROUP BY SYSID ");
			zruQuery.append("\n 		) ");
			
			result = mmiuQuery.toString() + zruQuery.toString();
			
		}else if(targetTab.equals("location")) {
			
			StringBuilder locQuery = new StringBuilder();
			int offset = subQuery.indexOf("LP_TOTAL_INTERVAL");
			subQuery.insert(offset + "LP_TOTAL_INTERVAL".length() + 1, " 					 , METER.LOCATION_ID LOC_ID \n ");
			
			locQuery.append("\n        SELECT ");
			locQuery.append("\n            (SELECT ");
			locQuery.append("\n                NAME  ");
			locQuery.append("\n            FROM ");
			locQuery.append("\n                LOCATION ");
			locQuery.append("\n            WHERE " );
			locQuery.append("\n                ID = LOC_ID) LOCATION_NAME, ");
			locQuery.append("\n            COUNT(LOC_ID) CNT, ");
			locQuery.append("\n            (SELECT ");
			locQuery.append("\n                COUNT(1) ");
			locQuery.append("\n            FROM ");
			locQuery.append("\n                METER ");
			locQuery.append("\n            WHERE ");
			locQuery.append("\n                LOCATION_ID = LOC_ID ");
			locQuery.append("\n                AND METER.METER_STATUS != '").append(deleteStatus).append("' ");
			locQuery.append("\n                ) METER_CNT ");
			locQuery.append("\n        FROM ");
			locQuery.append("\n            ( ").append(subQuery).append(" ");
			locQuery.append("\n            INNER JOIN ");
			locQuery.append("\n                LOCATION ");
			locQuery.append("\n                    ON LOCATION.ID = METER.LOCATION_ID ");
			locQuery.append("\n            WHERE ");
			locQuery.append("\n                METER.METER_STATUS != '").append(deleteStatus).append("' ");
			locQuery.append("\n            ORDER BY ");
			locQuery.append("\n                YYYYMMDD, ");
			locQuery.append("\n                METER_ID) A ");
			locQuery.append("\n            WHERE ");
			locQuery.append("\n                1 = 1 ");
			locQuery.append("\n                AND (LP_VALUE_CNT < LP_TOTAL_INTERVAL OR LP_TOTAL_INTERVAL IS NULL) ");
			locQuery.append("\n            GROUP BY ");
			locQuery.append("\n                LOC_ID ");
		
			result = locQuery.toString();
		}else if(targetTab.equals("model")) {
			
			StringBuilder modelQuery = new StringBuilder();
			int offset = subQuery.indexOf("LP_TOTAL_INTERVAL");
			subQuery.insert(offset + "LP_TOTAL_INTERVAL".length() + 1, " 					 , METER.DEVICEMODEL_ID MODEL_ID \n ");
			
			modelQuery.append("\n        SELECT ");
			modelQuery.append("\n            (SELECT ");
			modelQuery.append("\n                NAME ");
			modelQuery.append("\n            FROM ");
			modelQuery.append("\n                DEVICEMODEL ");
			modelQuery.append("\n            WHERE ");
			modelQuery.append("\n                ID = MODEL_ID) DEVICEMODEL_NAME, ");
			modelQuery.append("\n            COUNT(MODEL_ID) CNT, ");
			modelQuery.append("\n            (SELECT ");
			modelQuery.append("\n                COUNT(1) ");
			modelQuery.append("\n            FROM ");
			modelQuery.append("\n                METER ");
			modelQuery.append("\n            WHERE ");
			modelQuery.append("\n                DEVICEMODEL_ID = MODEL_ID ");
			modelQuery.append("\n                AND METER.METER_STATUS != '").append(deleteStatus).append("' ");
			modelQuery.append("\n                ) METER_CNT    ");
			modelQuery.append("\n        FROM ");
			modelQuery.append("\n            ( ").append(subQuery).append(" ");
			modelQuery.append("\n            INNER JOIN ");
			modelQuery.append("\n                DEVICEMODEL  ");
			modelQuery.append("\n                    ON DEVICEMODEL.ID = METER.DEVICEMODEL_ID    ");
			modelQuery.append("\n            WHERE ");
			modelQuery.append("\n                METER.METER_STATUS != '").append(deleteStatus).append("' ");
			modelQuery.append("\n            ORDER BY ");
			modelQuery.append("\n                YYYYMMDD, ");
			modelQuery.append("\n                METER_ID) A    ");
			modelQuery.append("\n            WHERE ");
			modelQuery.append("\n                1 = 1    ");
			modelQuery.append("\n                and (LP_VALUE_CNT < LP_TOTAL_INTERVAL OR LP_TOTAL_INTERVAL IS NULL) ");
			modelQuery.append("\n            GROUP BY ");
			modelQuery.append("\n                MODEL_ID  ");

			result = modelQuery.toString();
		}
		
		System.out.println(result);
		return result;
	}

	public static void main(String[] args) throws Exception {

		sqlEx("location");
		System.out.println("-------------------------------------");
		sqlEx("model");

	}
}