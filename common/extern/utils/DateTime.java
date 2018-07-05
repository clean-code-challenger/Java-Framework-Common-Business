package common.extern.utils;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.Vector;

/**
 * @param date
 * @return
 * @author Olena.Zagreba, IMNG-ComTrans<br>
 * @version ModelWeb 1.0, 2011. 12. 25 PM 5:04:43
 * @since ModelWeb 1.0
 */
public class DateTime
{
	public static final int	QUATER_1_4	= 1;//	
	public static final int	QUATER_2_4	= 2;//	
	public static final int	QUATER_3_4	= 3;//
	public static final int	QUATER_4_4	= 4;//	
	/**
	 * @param obj
	 * @return
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 09. 27 AM 9:24:21
	 * @since ModelWeb 1.0
	 */
	public static Calendar getCalendarFromObj(Object obj) {
		Calendar result = null;
		try {
			if (result instanceof Calendar) {
				result = (Calendar) obj;
			} else if (obj instanceof Date) {
				result = Calendar.getInstance();
				result.setTimeInMillis(((Date) obj).getTime());
			} else if (obj instanceof java.util.Date) {
				result = Calendar.getInstance();
				result.setTimeInMillis(((java.util.Date) obj).getTime());
			} else if (obj instanceof Time) {
				result = Calendar.getInstance();
				result.setTimeInMillis(((Time) obj).getTime());
			} else if (obj instanceof Timestamp) {
				result = Calendar.getInstance();
				result.setTimeInMillis(((Timestamp) obj).getTime());
			} else if (obj instanceof String) {
				long time = GetMilTime((String) obj);
				if (time != -1) {
					result = Calendar.getInstance();
					result.setTimeInMillis(time);
				}
			}
		} catch (Throwable ex) {
		}
		return result;
	}
	/**
	 * @param timeStampInfo
	 * @return
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 25 PM 5:07:17
	 * @since ModelWeb 1.0
	 */
	public static String GetDate(Timestamp timeStampInfo) {
		String result = null;
		if (timeStampInfo != null) {
			try {
				result = String.format("%1$tY-%1$tm-%1$td", timeStampInfo); // String.format("%1$tF",
																			// nowday);
			} catch (IllegalArgumentException ex) {
			}
		}
		return result;
	}
	/**
	 * @param timeInfo
	 * @return
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 09. 03 PM 5:10:39
	 * @since ModelWeb 1.0
	 */
	public static String GetDateStringFromObj(Object timeInfo, String strFormat) {
		String result = null;
		try {
			if (strFormat == null) {
				strFormat = GetFormatString("y-m-d");
			} else {
				strFormat = GetFormatString(strFormat);
			}
			if (timeInfo != null)
				result = String.format(strFormat, timeInfo); // String.format("%1$tF",
																// nowday);
		} catch (IllegalArgumentException ex) {
		}
		return result;
	}
	/**
	 * @param timeInfo
	 * @return
	 * @author Olena.Zagreba
	 * @version ModelWeb 2013. 05. 05 PM 3:34:35
	 * @since ModelWeb
	 */
	public static String GetJucheDateStringFromObj(Object timeInfo) {
		String result = null;
		try {
			String strFormat = "";
			if (timeInfo instanceof Calendar) {
				result = String.format(strFormat, timeInfo,
						((Calendar) timeInfo).get(Calendar.YEAR) - 1911);
			} else if (timeInfo instanceof Date) {
				Calendar dtDate = Calendar.getInstance();
				dtDate.setTimeInMillis(((Date) timeInfo).getTime());
				result = String.format(strFormat, dtDate, dtDate
						.get(Calendar.YEAR) - 1911);
			}
		} catch (IllegalArgumentException ex) {
		}
		return result;
	}
	/**
	 * 
	 * @param timeInfo
	 * @return
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 09. 03 PM 5:10:43
	 * @since ModelWeb 1.0
	 */
	public static String GetTimeStringFromObj(Object timeInfo, String strFormat) {
		String result = null;
		try {
			if (strFormat == null) {
				strFormat = GetFormatString("H:M:S");
			} else {
				strFormat = GetFormatString(strFormat);
			}
			if (timeInfo != null)
				result = String.format(strFormat, timeInfo);
		} catch (IllegalArgumentException ex) {
		}
		return result;
	}
	/**
	 * @param timeInfo
	 * @return
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 09. 03 PM 5:10:46
	 * @since ModelWeb 1.0
	 */
	public static String GetDateTimeStringFromObj(Object timeInfo, String strFormat) {
		String result = null;
		try {
			if (strFormat == null) {
				strFormat = GetFormatString("y-m-d H:M:S");
			} else {
				strFormat = GetFormatString(strFormat);
			}
			if (timeInfo != null)
				result = String.format(strFormat, timeInfo);
		} catch (IllegalArgumentException ex) {
		}
		return result;
	}
	/**
	 * @param strFormat
	 * @return
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 11. 23 AM 11:36:37
	 * @since ModelWeb 1.0
	 */
	private static String GetFormatString(String strFormat) {
		String result = strFormat;
		if (result != null) {
			result = result.replace("y", "%1$tY");
			result = result.replace("m", "%1$tm");
			result = result.replace("d", "%1$td");

			result = result.replace("H", "%1$tH");
			result = result.replace("M", "%1$tM");
			result = result.replace("S", "%1$tS");
		}
		return result;
	}
	/**
	 * @param strDate
	 * @param strFormat
	 * @return
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 09. 03 PM 6:52:46
	 * @since ModelWeb 1.0
	 */
	public static Date GetDateFromString(String strDate, String strFormat) {
		Date result = null;
		try {
			long time = GetMilTime(strDate);
			if (time != -1) {
				result = new Date(time);
			} else {
				Calendar dt = ParseFormat(strDate, strFormat);
				result = new Date(dt.getTimeInMillis());
			}
		} catch (IllegalArgumentException ex) {
		}
		return result;
	}	
	/**
	 * @param strTime
	 * @param strFormat
	 * @return
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 09. 03 PM 6:52:48
	 * @since ModelWeb 1.0
	 */
	public static Time GetTimeFromString(String strTime, String strFormat) {
		Time result = null;
		try {
			long time = GetMilTime(strTime);
			if (time != -1) {
				result = new Time(time);
			} else {
				Calendar dt = ParseFormat(strTime, strFormat);
				result = new Time(dt.getTimeInMillis());
			}

		} catch (IllegalArgumentException ex) {
		}
		return result;
	}
	/**
	 * @param strTime
	 * @param strFormat
	 * @return
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 09. 03 PM 6:53:14
	 * @since ModelWeb 1.0
	 */
	public static Timestamp GetDateTimeFromString(String strTime, String strFormat) {
		Timestamp result = null;
		try {
			long time = GetMilTime(strTime);
			if(time!=-1) {
				result = new Timestamp(time);
			}
			else {
				Calendar dt = ParseFormat(strTime, strFormat);
				result = new Timestamp(dt.getTimeInMillis());
			}
		}
		catch(IllegalArgumentException ex) { }
		return result;
	}
	/**
	 * @param strDate
	 * @param strFormat
	 * @return
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 11. 23 PM 1:38:03
	 * @since ModelWeb 1.0
	 */
	public static Calendar ParseFormat(String strDate, String strFormat) {
		Calendar result = Calendar.getInstance();
		result.set(Calendar.YEAR, 0);
		result.set(Calendar.MONTH, 0);
		result.set(Calendar.DAY_OF_MONTH, 0);
		result.set(Calendar.HOUR_OF_DAY, 0);
		result.set(Calendar.MINUTE, 0);
		result.set(Calendar.SECOND, 0);
		
		Vector<Integer> vcType = new Vector<Integer>();
		Vector<Integer> vcIndex = new Vector<Integer>();
		int nLength = strFormat.length();
		for(int nCharIndex = 0; nCharIndex < nLength; nCharIndex++) {
			switch (strFormat.charAt(nCharIndex)) {
				case 'y': {
					vcType.add(Calendar.YEAR);
					vcIndex.add(nCharIndex);
					break;
				}
				case 'm': {
					vcType.add(Calendar.MONTH);
					vcIndex.add(nCharIndex);
					break;
				}
				case 'd': {
					vcType.add(Calendar.DAY_OF_MONTH);
					vcIndex.add(nCharIndex);
					break;
				}
				case 'H': {
					vcType.add(Calendar.HOUR_OF_DAY);
					vcIndex.add(nCharIndex);
					break;
				}
				case 'M': {
					vcType.add(Calendar.MINUTE);
					vcIndex.add(nCharIndex);
					break;
				}
				case 'S': {
					vcType.add(Calendar.SECOND);
					vcIndex.add(nCharIndex);
					break;
				}
			}
		}
		int nNum;
		int nDateStartSeek = 0, nDateEndSeek = 0;
		int nSize = vcIndex.size();
		for(int nIndex = 0; nIndex < nSize; nIndex++) {
			int nStartSeek, nBetweenSeek, nEndSeek;
			if(nIndex<=0) { nStartSeek = 0; }
			else { nStartSeek = vcIndex.get(nIndex - 1) + 1; }
			nBetweenSeek = vcIndex.get(nIndex);
			if(nIndex + 1 >= vcIndex.size()) { nEndSeek = strFormat.length(); }
			else { nEndSeek = vcIndex.get(nIndex + 1); }
			String strStartToken = strFormat.substring(nStartSeek, nBetweenSeek);
			String strEndToken = strFormat.substring(nBetweenSeek + 1, nEndSeek);
			if(strStartToken!=null) {
				if(strStartToken.length() > 0) {
					nDateStartSeek = strDate.indexOf(strStartToken, nDateEndSeek) + strStartToken.length();
				}
			}
			else {
				nDateStartSeek = 0;
			}
			if(nDateStartSeek!=-1) {
				if(strEndToken!=null) {
					if(strEndToken.length() > 0) {
						nDateEndSeek = strDate.indexOf(strEndToken, nDateStartSeek);
						if(nDateEndSeek==-1) { nNum =  NumeralConvert.GetInt(strDate.substring(nDateStartSeek)); }
						else { nNum =  NumeralConvert.GetInt(strDate.substring(nDateStartSeek, nDateEndSeek)); }
						if (nNum != -1) {
							switch (vcType.get(nIndex)) {
							case Calendar.YEAR: {
								result.set(Calendar.YEAR, nNum);
								break;
							}
							case Calendar.MONTH: {
								result.set(Calendar.MONTH, nNum - 1);
								break;
							}
							case Calendar.DAY_OF_MONTH: {
								result.set(Calendar.DAY_OF_MONTH, nNum);
								break;
							}
							case Calendar.HOUR_OF_DAY: {
								result.set(Calendar.HOUR_OF_DAY, nNum);
								break;
							}
							case Calendar.MINUTE: {
								result.set(Calendar.MINUTE, nNum);
								break;
							}
							case Calendar.SECOND: {
								result.set(Calendar.SECOND, nNum);
								break;
							}
							}
						}
					}
				}
			}
		}
		return result;
	}
	public static String GetDatebySlash(Object timeStampInfo) {
		String result = null;		
		if(timeStampInfo!=null){
			try{
				result = String.format("%1$tY/%1$tm/%1$td", timeStampInfo); // String.format("%1$tF", nowday);
			}
			catch(IllegalArgumentException ex){ }
		}
		return result;
	}
	/**
	 */
	public static String GetDatebyDelimiter(Object dateInfo, String strDelimiter) {
		String result = null;
		if(dateInfo!=null){
			String strFormat = "%1$tY" + strDelimiter +"%1$tm" + strDelimiter + "%1$td";
			try{
				result = String.format(strFormat, dateInfo);
			}
			catch(IllegalArgumentException ex){
			}
		}
		return result;
	}
	public static String GetNowDate() {
		Calendar nowday = Calendar.getInstance();
		String str = String.format("%1$tY-%1$tm-%1$td", nowday);
		return str;
	}
	public static String GetNowDatebySlash() {
		Calendar nowday = Calendar.getInstance();
		String str = String.format("%1$tY/%1$tm/%1$td", nowday);
		return str;
	}
	public static String GetNowDatebyDelimiter(String strDelimiter) {
		String strFormat = "%1$tY" + strDelimiter +"%1$tm" + strDelimiter + "%1$td";
		Calendar nowday = Calendar.getInstance();
		String str = String.format(strFormat, nowday);
		return str;
	}
	public static String GetNowDatebyCorean() {
		Calendar nowday = Calendar.getInstance();
		String str = String.format("%1$tY년%1$tm월%1$td일", nowday);
		return str;
	}
	public static String GetNowTime() {
		Calendar nowday = Calendar.getInstance();
		String str = String.format("%1$tH:%1$tM:%1$tS", nowday);
		return str;
	}
	public static String GetNowTimeByCorean() {
		Calendar nowday = Calendar.getInstance();
		String str = String.format("%1$tH시%1$tM분%1$tS초", nowday);
		return str;
	}
	public static String GetNowDateTime() {
		// 13:20:07
		Calendar nowday = Calendar.getInstance();
		String str = String.format("%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS", nowday); // String.format("%1$tT", nowday);
		return str;
	}
	public static String GetNowMonth() {
		// 2010-01
		Calendar nowday = Calendar.getInstance();
		String str = String.format("%1$tY-%1$tm", nowday);
		return str;
	}
	/**
	*/
	public static String GetJucheNowDate() {
		Calendar nowday = Calendar.getInstance();
		String str = String.format("주체%2$d(%1$tY)년 %1$tm월 %1$td일", nowday, nowday.get(Calendar.YEAR)-1911);
		return str;
	}
	public static String GetYear() {
		Calendar nowday = Calendar.getInstance();
		return String.valueOf(nowday.get(Calendar.YEAR));
	}
	public static String GetMonth() {
		Calendar nowday = Calendar.getInstance();
		return String.valueOf(nowday.get(Calendar.MONTH) + 1);
	}
	public static String GetDay() {
		Calendar nowday = Calendar.getInstance();
		return String.valueOf(nowday.get(Calendar.DAY_OF_MONTH));
	}
	public static String GetQuarter() {
		Calendar nowday = Calendar.getInstance();
		int quarter = (int) (Math.floor((nowday.get(Calendar.MONDAY) + 1) / 3) + 1);
		return String.valueOf(quarter);
	}
	public Calendar getDateFromYearAndMonthAndWeekAndDay(int nYear, int nMonth, int nWeek, int nDay) {
		Calendar dtdate = Calendar.getInstance();
		dtdate.setFirstDayOfWeek(Calendar.MONDAY);
		dtdate.setMinimalDaysInFirstWeek(4);
		dtdate.set(Calendar.YEAR, nYear);
		dtdate.set(Calendar.MONTH, nMonth);
		dtdate.set(Calendar.WEEK_OF_MONTH, nWeek);
		dtdate.set(Calendar.DAY_OF_WEEK, nDay);
		return dtdate;
	}
	public Vector<Calendar> getDatesFromYearAndMonthAndWeek(int nYear, int nMonth, int nWeek) {
		Vector<Calendar>vcDates = new Vector<Calendar>();
		for(int i = Calendar.SUNDAY; i <= Calendar.SATURDAY; i++) {
			Calendar dtdate = Calendar.getInstance();
			dtdate.setFirstDayOfWeek(Calendar.MONDAY);
			dtdate.setMinimalDaysInFirstWeek(4);
			
			dtdate.set(Calendar.YEAR, nYear);
			dtdate.set(Calendar.MONTH, nMonth);
			dtdate.set(Calendar.WEEK_OF_MONTH, nWeek);
			vcDates.add(dtdate);			
		}
		return vcDates;
	}
	/**
	 * @param dtDateTime
	 * @return
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 09. 17 AM 8:45:36
	 * @since ModelWeb 1.0
	 */
	public static Date getDateFromCalendar(Calendar dtDateTime) {
		Date dtDate = new Date(dtDateTime.getTimeInMillis());
		return dtDate;
	}
	/**
	 * @param dtDateTime
	 * @return
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 09. 17 AM 8:45:38
	 * @since ModelWeb 1.0
	 */
	public static Time getTimeFromCalendar(Calendar dtDateTime) {
		Time dtDate = new Time(dtDateTime.getTimeInMillis());
		return dtDate;
	}
	/**
	 * @param dtDateTime
	 * @return
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 09. 17 AM 8:45:40
	 * @since ModelWeb 1.0
	 */
	public static Timestamp getTimeStampFromCalendar(Calendar dtDateTime) {
		Timestamp dtDate = new Timestamp(dtDateTime.getTimeInMillis());
		return dtDate;
	}
	/**
	 * @param dtDate
	 * @return
	 */
	public static Calendar getQuaterFirstDay(Calendar dtDate) {
		int nQuaterKind = getQuaterKind(dtDate);
		Calendar dtRetn = (Calendar)dtDate.clone();
		dtRetn.set(Calendar.DAY_OF_MONTH, 1);
		switch(nQuaterKind) {
			case QUATER_1_4:
				dtRetn.set(Calendar.MONTH, Calendar.JANUARY);
				break;
			case QUATER_2_4:
				dtRetn.set(Calendar.MONTH, Calendar.APRIL);
				break;
			case QUATER_3_4:
				dtRetn.set(Calendar.MONTH, Calendar.JULY);
				break;
			case QUATER_4_4:
				dtRetn.set(Calendar.MONTH, Calendar.OCTOBER);
				break;
		}
		return dtRetn;
	}
	/**
	 * @param dtDate
	 * @return
	 */
	public static Calendar getQuaterLastDay(Calendar dtDate) {
		int nQuaterKind = getQuaterKind(dtDate);
		Calendar dtRetn = (Calendar)dtDate.clone();
		dtRetn.set(Calendar.DAY_OF_MONTH, 1);
		switch (nQuaterKind) {
			case QUATER_1_4: {
				dtRetn.set(Calendar.MONTH, Calendar.APRIL);
				break;
			}
			case QUATER_2_4: {
				dtRetn.set(Calendar.MONTH, Calendar.JULY);
				break;
			}
			case QUATER_3_4: {
				dtRetn.set(Calendar.MONTH, Calendar.OCTOBER);
				break;
			}
			case QUATER_4_4: {
				dtRetn.set(Calendar.MONTH, Calendar.JANUARY);
				dtRetn.add(Calendar.YEAR, 1);
				break;
			}
		}
		dtRetn.add(Calendar.DAY_OF_MONTH, -1);
		return dtRetn;
	}
	/**
	 * @param dtDate:
	 * @return	
	 */
	public static int getQuaterKind(Calendar dtDate) {
		if(dtDate==null) {
			dtDate = Calendar.getInstance(TimeZone.getTimeZone("Asia/Pyongyang"));
		}
		if(dtDate.get(Calendar.MONTH)>=Calendar.JANUARY && dtDate.get(Calendar.MONTH)<=Calendar.MARCH)
			return QUATER_1_4;
		else if(dtDate.get(Calendar.MONTH)>=Calendar.APRIL && dtDate.get(Calendar.MONTH)<=Calendar.JUNE)
			return QUATER_2_4;
		else if(dtDate.get(Calendar.MONTH)>=Calendar.JULY && dtDate.get(Calendar.MONTH)<=Calendar.SEPTEMBER)
			return QUATER_3_4;
		else 
			return QUATER_4_4;
	}
	/**
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2011. 08. 20 PM 2:58:00
	 * @since ModelWeb 1.0
	 */
	public static int getWeekCountFromDate(Calendar dtDate) {
		Calendar dtCalendar = (Calendar)dtDate.clone();
		dtCalendar.setFirstDayOfWeek(Calendar.MONDAY);
		dtCalendar.setMinimalDaysInFirstWeek(4);
		int nMaxWeek = dtCalendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
		int nDaysMonth = dtCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		dtCalendar.set(Calendar.WEEK_OF_MONTH, nMaxWeek);
		dtCalendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		int nDayofLastWeek = dtCalendar.get(Calendar.DAY_OF_MONTH);
		if(nDaysMonth - nDayofLastWeek < 3) { nMaxWeek--; }
		return nMaxWeek;		
	}
	
	/**
	 * @param nYear
	 * @param nMonth
	 * @return
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 09. 17 AM 8:48:15
	 * @since ModelWeb 1.0
	 */
	public static int getWeekCountFromDate(int nYear, int nMonth)
	{
		Calendar dtDate = Calendar.getInstance();
		dtDate.clear();
    	dtDate.set(Calendar.YEAR, nYear);
    	dtDate.set(Calendar.MONTH, nMonth);
    	dtDate.set(Calendar.DAY_OF_MONTH, 1);
		return getWeekCountFromDate(dtDate);
	}
	/**
	 * @param nYear
	 * @param nMonth
	 * @return
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 09. 17 AM 8:48:01
	 * @since ModelWeb 1.0
	 */
	public static int getDayCountFromDate(int nYear, int nMonth) {
		Calendar dtDate = Calendar.getInstance();
		dtDate.clear();
    	dtDate.set(Calendar.YEAR, nYear);
    	dtDate.set(Calendar.MONTH, nMonth);
    	dtDate.set(Calendar.DAY_OF_MONTH, 1);
		return dtDate.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * @param dtDate
	 * @return
	 * @author Olena.Zagreba
	 * @version ModelWeb 2013. 02. 22 AM 10:08:15
	 * @since ModelWeb
	 */
	public static int getDayCountFromDate(Calendar dtDate) {
		return dtDate.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	/**
	 * @param nYear
	 * @return
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 10. 01 PM 3:51:55
	 * @since ModelWeb 1.0
	 */
	public static int getMonthCountFromYear(int nYear) {
		Calendar cal = Calendar.getInstance();
		cal.clear(); cal.set(nYear, 0, 1);
		return cal.getActualMaximum(Calendar.MONTH);	
	}
	/**
	 * @return
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 25 PM 5:06:07
	 * @since ModelWeb 1.0
	 */
	public static long GetMilTime(String date) {
		long time = -1;
		if(date!=null){
			try{ if(time==-1) {time = Timestamp.valueOf(date).getTime();} }
			catch(IllegalArgumentException ex){ time = -1; }
			
			try{ if(time==-1){ time = Date.valueOf(date).getTime();} }
			catch(IllegalArgumentException ex){ }
			
			try{ if(time==-1){ time = Timestamp.valueOf(date.replaceAll("/", "-")).getTime();} }
			catch(IllegalArgumentException ex){ }
			
			try{ if(time==-1){ time = Date.valueOf(date.replaceAll("/", "-")).getTime();} }
			catch(IllegalArgumentException ex){ }
		}
		return time;
	}
    /**
     * @param dtDate
     * @return
     * @author Olena.Zagreba
     * @version ModelWeb 1.0, 2011. 08. 20 PM 4:40:21
     * @since ModelWeb 1.0
     */
    public int GetRoundYear(Calendar dtDate) {
    	Calendar dtCalendar = (Calendar)dtDate.clone();
    	dtCalendar.setFirstDayOfWeek(Calendar.MONDAY);
    	dtCalendar.setMinimalDaysInFirstWeek(4);
    	int nYear = dtCalendar.get(Calendar.YEAR);
    	int nMonth = dtCalendar.get(Calendar.MONTH);
    	int nWeek = dtCalendar.get(Calendar.WEEK_OF_MONTH);
    	if(nWeek==0) {
    		dtCalendar.add(Calendar.DAY_OF_MONTH, -3);
    		nYear = dtCalendar.get(Calendar.YEAR);
    	}
    	else {
    		if(nWeek > getWeekCountFromDate(nYear, nMonth)) {
    			dtCalendar.add(Calendar.DAY_OF_MONTH, 3);
        		nYear = dtCalendar.get(Calendar.YEAR);
    		}
    	}
    	return nYear;
    }
    /**
     * @param dtDate
     * @return
     * @author Olena.Zagreba
     * @version ModelWeb 1.0, 2011. 08. 20 PM 4:40:21
     * @since ModelWeb 1.0
     */
    public int GetRoundMonth(Calendar dtDate) {
    	Calendar dtCalendar = (Calendar)dtDate.clone();
    	dtCalendar.setFirstDayOfWeek(Calendar.MONDAY);
    	dtCalendar.setMinimalDaysInFirstWeek(4);
		
    	int nYear = dtCalendar.get(Calendar.YEAR);
    	int nMonth = dtCalendar.get(Calendar.MONTH);
    	int nWeek = dtCalendar.get(Calendar.WEEK_OF_MONTH);
    	if(nWeek==0) {
    		dtCalendar.add(Calendar.DAY_OF_MONTH, -3);
        	nMonth = dtCalendar.get(Calendar.MONTH);
    	}
    	else {
    		if(nWeek > getWeekCountFromDate(nYear, nMonth)) {
    			dtCalendar.add(Calendar.DAY_OF_MONTH, 3);
    			nMonth = dtCalendar.get(Calendar.MONTH);	
    		}
    	}
    	return nMonth;
    }
    /**
     * @param dtDate
     * @return
     *
     * @author Olena.Zagreba
     * @version ModelWeb 1.0, 2011. 08. 20 PM 4:40:21
     * @since ModelWeb 1.0
     */
    public int GetRoundWeek(Calendar dtDate)
    {
    	Calendar dtCalendar = (Calendar)dtDate.clone();
    	dtCalendar.setFirstDayOfWeek(Calendar.MONDAY);
    	dtCalendar.setMinimalDaysInFirstWeek(4);
		
    	int nYear = dtCalendar.get(Calendar.YEAR);  	
    	int nMonth = dtCalendar.get(Calendar.MONTH);
    	int nWeek = dtCalendar.get(Calendar.WEEK_OF_MONTH);
    	if(nWeek==0)
    	{
    		dtCalendar.add(Calendar.DAY_OF_MONTH, -3);
        	nWeek = dtCalendar.get(Calendar.WEEK_OF_MONTH); 	
    	}
    	else
    	{
    		if(nWeek > getWeekCountFromDate(nYear, nMonth))
    		{
    			dtCalendar.add(Calendar.DAY_OF_MONTH, 3);
    			nWeek = dtCalendar.get(Calendar.WEEK_OF_MONTH);
    		}
    	}
    	return nWeek;
    }
	//////////////////////////////////////Made by Olena//////////////////////
	public static final int TIME_FORMAT_DATE_NO_DAY	= 1;//"2011-02-01"
	public static final int TIME_FORMAT_DATE		= 2;//"2011-02-21"
	public static final int TIME_FORMAT_DATE_TIME	= 3;//"2011-02-21 11:32:04"
	public static String getDateTimeString(Calendar dtDateTime, int nType)
	{
		///////////////////2011/2/21 pui////////////////////////
		if(dtDateTime==null)
			dtDateTime = Calendar.getInstance();//get current time.
		switch(nType)
		{
			
			case TIME_FORMAT_DATE_NO_DAY:
				return String.format("%1$04d-%2$02d-01", 
						dtDateTime.get(Calendar.YEAR),
						dtDateTime.get(Calendar.MONTH)+1
						);
			case TIME_FORMAT_DATE:
				return String.format("%1$04d-%2$02d-%3$02d", 
						dtDateTime.get(Calendar.YEAR),
						dtDateTime.get(Calendar.MONTH)+1,
						dtDateTime.get(Calendar.DAY_OF_MONTH)
						);
			case TIME_FORMAT_DATE_TIME:
				return String.format("%1$04d-%2$02d-%3$02d %4$02d:%5$02d:%6$02d", 
						dtDateTime.get(Calendar.YEAR),
						dtDateTime.get(Calendar.MONTH)+1,
						dtDateTime.get(Calendar.DAY_OF_MONTH),
						dtDateTime.get(Calendar.HOUR_OF_DAY),
						dtDateTime.get(Calendar.MINUTE),
						dtDateTime.get(Calendar.SECOND)
						);
			
			default:
				return String.format("%1$04d-%2$02d-01", 
						dtDateTime.get(Calendar.YEAR),
						dtDateTime.get(Calendar.MONTH)+1
						);
		}
	}	
	/**
	 */
	public static Calendar parseDateFromString(String strDateTime) {
		//strDateTime: YYYY/MM/DD HH:mm:SS, YYYY-MM-DD HH:mm:SS 
		Calendar calDateTime = Calendar.getInstance();
		calDateTime.set(Calendar.YEAR, 0);
		calDateTime.set(Calendar.MONTH, 0);
		calDateTime.set(Calendar.DATE, 0);
		calDateTime.set(Calendar.HOUR_OF_DAY, 0);
		calDateTime.set(Calendar.MINUTE, 0);
		calDateTime.set(Calendar.SECOND, 0);
		//spirit string with " "
		String[] strDT = strDateTime.split(" ");
		if(strDT.length > 0) {
			//get year month day
			String strDate = strDT[0];
			String[] strYMD = strDate.split("/");
			if(strYMD.length < 3) {
				strYMD = strDate.split("-");
			}
			if(strYMD.length < 3) { return null; }
			else {
				calDateTime.set(Calendar.YEAR, Integer.parseInt(strYMD[0]));
				calDateTime.set(Calendar.MONTH, Integer.parseInt(strYMD[1])-1);
				calDateTime.set(Calendar.DATE, Integer.parseInt(strYMD[2]));
			}
		}
		if(strDT.length > 1) {
			String strDate = strDT[1];
			String[] strHMS = strDate.split(":");
			if(strHMS.length < 3) { return null; }
			else {
				calDateTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(strHMS[0]));
				calDateTime.set(Calendar.MINUTE, Integer.parseInt(strHMS[1]));
				calDateTime.set(Calendar.SECOND, Integer.parseInt(strHMS[2]));
			}
		}
		return calDateTime;
	}	
}
