package common.extern.utils;

import java.io.UnsupportedEncodingException;

/**
 *
 * @author Olena.Zagreba in Truegardener TEAM
 * @version ModelWeb 1.0, 2011. 12. 25 PM 5:16:27
 * @since ModelWeb 1.0
 */
public class StringEncoder 
{
	public static final String CHARSET_UTF_8 = "UTF-8";
	public static final String CHARSET_ISO_8859_1 = "ISO8859_1";
	public static final String CHARSET_US_ASCII 	= "US-ASCII";
	public static final String CHARSET_UTF_16BE 	= "UTF-16BE";
	public static final String CHARSET_UTF_16LE 	= "UTF-16LE";
	public static final String CHARSET_UTF_16 	= "UTF-16";
	
	public static String UNI_TO_8859_1(String strSrc) {
		if (strSrc == null) {
			return null;
		}
		try {
			return new String(strSrc.getBytes(CHARSET_UTF_8), CHARSET_ISO_8859_1);
		} catch (Exception e) {
			return strSrc;
		}

	}

	public static String UNI_TO_UNI(String strSrc) {
		if (strSrc == null) {
			return null;
		}
		try {
			return new String(strSrc.getBytes(CHARSET_UTF_8), CHARSET_UTF_8);
		} catch (Exception e) {
			return strSrc;
		}

	}
	public static String _8859_1_TO_UNI(String strSrc) 
	{
		if (strSrc == null) {
			return null;
		}
		try {
			return new String(strSrc.getBytes(CHARSET_ISO_8859_1), CHARSET_UTF_8);
		} catch (Exception e) {
			return strSrc;
		}
	}
	
	
	public static String Convert(String str, String srcEncoding, String destEncoding)
	{
		if (str == null)
			return null;
		try
		{
			str = new String( str.getBytes(srcEncoding), destEncoding );
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		return str;
	}
	
	public static byte ConvertHexChar2Byte(char chHexChar)
	{
		byte bytRet = 0;
		if ( chHexChar >= '0' && chHexChar <= '9' )
		{
			bytRet = (byte)(chHexChar - '0');
		}
		else if (chHexChar >= 'A' && chHexChar <= 'F' )
		{
			bytRet = (byte)(chHexChar - 'A' + 0x0A);
		}
		return bytRet;
	}
}