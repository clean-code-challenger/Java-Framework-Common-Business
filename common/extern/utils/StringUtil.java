package common.extern.utils;


/**
 *
 * @since 2011-10-25
 * @version ModelWeb 1.0
 *
 * @author CH.Kim, IMNG-ComTrans<br>
 */
public class StringUtil 
{
	/**
	 *
	 * @param orgstr
	 * @param defstr 
	 * @return 
	 */	
	public static String GetDefaultString(String orgstr, String defstr) {
		if(orgstr == null) {
			return defstr;
		}
		else if(orgstr.trim().length() == 0)
			return defstr;
		return orgstr;
	}

	/**
	 *
	 * @param orgstr 
	 * @param defstr
	 * @param bconvert
	 * @return 
	 */	
	public static String GetDefaultString(String orgstr, String defstr, boolean bconvert) {
		if(orgstr == null) {
			return defstr;
		}
		else if(orgstr.trim().length() == 0) {
			return defstr;
		}
		return orgstr;
	}
	/**
	 * @param sStr 
	 * @param rSrc 
	 * @param rDest 
	 * @return replace result
	 */	
	public static String replaceAll(String sStr, String rSrc, String rDest) {
		if (sStr == null || rSrc == null) return sStr;
		while (sStr.indexOf(rSrc) != -1)
			sStr = sStr.replace(rSrc, rDest);
		return sStr;
	}
}
