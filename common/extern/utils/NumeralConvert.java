package common.extern.utils;

/**
 *
 * @version ModelWeb 1.0, 2011. 12. 25 PM 5:14:21
 * @since ModelWeb 1.0
 */
public class NumeralConvert
{
	/**
	 * String To int
	 * 
	 * @param value
	 * @return
	 */
	public static int GetInt(String value)
	{
		int rt = -1;
		if (value == null)
			return rt;

		try{ rt = Integer.parseInt(value); }
		catch (Exception ex) { rt = -1; }
		return rt;
	}
	/**
	 * String To int 
	 * @param value
	 * @param nDefault
	 * @return
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 09. 10 AM 10:54:58
	 * @since ModelWeb 1.0
	 */
	public static int GetInt(String value, int nDefault)
	{
		int rt = nDefault;
		if (value == null)
			return rt;

		try{ rt = Integer.parseInt(value); }
		catch (Exception ex) { rt = nDefault; }
		return rt;
	}
	
	/**
	 * String To int 
	 * @param value
	 * @param nDefault
	 * @return
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 09. 10 AM 10:54:58
	 * @since ModelWeb 1.0
	 */
	public static Integer GetInt(String value, Object nDefault)
	{
		if (value == null)
			return (Integer)nDefault;

		try{ return Integer.parseInt(value); }
		catch (Exception ex) { return (Integer)nDefault; }
	}
	
	/**
	 * String To long 
	 * 
	 * @param value 
	 */	
	public static long GetLong(String value)
	{
		long rt = -1;
		if (value == null) return rt; 
		try { rt = Long.parseLong(value); }
		catch (Exception ex) { rt = -1; }
		return rt;
	}
	
	/**
	 * @param value
	 * @param nDefault
	 * @return
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 09. 10 AM 10:55:33
	 * @since ModelWeb 1.0
	 */
	public static long GetLong(String value, long nDefault)
	{
		long rt = nDefault;
		if (value == null) return rt; 
		try { rt = Long.parseLong(value); }
		catch (Exception ex) { rt = nDefault; }
		return rt;
	}
	/**
	 * @param value
	 * @param nDefault
	 * @return
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 09. 10 AM 10:55:33
	 * @since ModelWeb 1.0
	 */
	public static Long GetLong(String value, Object nDefault)
	{
		if (value == null) return (Long)nDefault; 
		try { return Long.parseLong(value); }
		catch (Exception ex) { return (Long)nDefault; }
	}
	/**
	 * String To short 변환함수
	 * @param value
	 * @return
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 08. 23 AM 10:42:13
	 * @since ModelWeb 1.0
	 */
	public static short GetShort(String value)
	{
		short rt = -1;
		if (value == null) return rt;
		try { rt = Short.parseShort(value); }
		catch (Exception ex) { rt = -1; }
		return rt;
	}
	/**
	 * @param value
	 * @param shDefault
	 * @return
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 09. 10 AM 10:56:37
	 * @since ModelWeb 1.0
	 */
	public static short GetShort(String value, short shDefault)
	{
		short rt = shDefault;
		if (value == null) return rt;
		try { rt = Short.parseShort(value); }
		catch (Exception ex) { rt = shDefault; }
		return rt;
	}
	
	/**
	 * @param value
	 * @param shDefault
	 * @return
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 09. 10 AM 10:56:37
	 * @since ModelWeb 1.0
	 */
	public static Short GetShort(String value, Object shDefault)
	{
		if (value == null) return (Short)shDefault;
		try { return Short.parseShort(value); }
		catch (Exception ex) { return (Short)shDefault; }
	}
	/**
	 * @param value
	 * @return
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 09. 10 AM 10:56:41
	 * @since ModelWeb 1.0
	 */
	public static byte GetByte(String value)
	{
		byte rt = -1;
		if (value == null) return rt;
		try { rt = Byte.parseByte(value); }
		catch (Exception ex) { rt = -1; }
		return rt;
	}
	/**
	 * @param value
	 * @param btDefault
	 * @return
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 09. 10 AM 10:58:48
	 * @since ModelWeb 1.0
	 */
	public static byte GetByte(String value, byte btDefault)
	{
		byte rt = btDefault;
		if (value == null) return rt;
		try { rt = Byte.parseByte(value); }
		catch (Exception ex) { rt = btDefault; }
		return rt;
	}
	/**
	 * @param value
	 * @param btDefault
	 * @return
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 09. 10 AM 10:58:48
	 * @since ModelWeb 1.0
	 */
	public static Byte GetByte(String value, Object btDefault)
	{
		if (value == null) return (Byte) btDefault;
		try { return Byte.parseByte(value); }
		catch (Exception ex) { return (Byte)btDefault; }
	}
	/**
	 * String To boolean
	 * 
	 * @param value
	 * @return
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 08. 23 AM 10:42:10
	 * @since ModelWeb 1.0
	 */
	public static boolean GetBoolean(String value)
	{
		boolean rt = false;
		if (value == null) return rt;
		try { rt = Boolean.parseBoolean(value); }
		catch (Exception ex) { rt = false; }
		return rt;
	}
	/**
	 * String To boolean
	 * @param value
	 * @param bDefault
	 * @return
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 09. 10 AM 10:57:32
	 * @since ModelWeb 1.0
	 */
	public static boolean GetBoolean(String value, boolean bDefault)
	{
		boolean rt = bDefault;
		if (value == null) return rt;
		try { rt = Boolean.parseBoolean(value); }
		catch (Exception ex) { rt = bDefault; }
		return rt;
	}
	/**
	 * String To boolean
	 * @param value
	 * @param bDefault
	 * @return
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 09. 10 AM 10:57:32
	 * @since ModelWeb 1.0
	 */
	public static Boolean GetBoolean(String value, Object bDefault)
	{
		if (value == null) return (Boolean)bDefault;
		try { return Boolean.parseBoolean(value); }
		catch (Exception ex) { return (Boolean)bDefault; }
	}
	/**
	 * String To float 
	 */	
	public static float GetFloat(String value)
	{
		float rt = -1;
		if (value == null) return rt;
		try { rt = Float.parseFloat(value); }
		catch (Exception ex) { rt = -1; }
		return rt;
	}
	/**
	 * String To float
	 * @param value
	 * @param fDefault
	 * @return
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 09. 10 AM 10:58:04
	 * @since ModelWeb 1.0
	 */
	public static float GetFloat(String value, float fDefault)
	{
		float rt = fDefault;
		if (value == null) return rt;
		try { rt = Float.parseFloat(value); }
		catch (Exception ex) { rt = fDefault; }
		
		return rt;
	}
	/**
	 * String To float
	 * @param value
	 * @param fDefault
	 * @return
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 09. 10 AM 10:58:04
	 * @since ModelWeb 1.0
	 */
	public static Float GetFloat(String value, Object fDefault)
	{
		if (value == null) return (Float)fDefault;
		try { return Float.parseFloat(value); }
		catch (Exception ex) { return (Float)fDefault; }
	}
	/**
	 * String To double 
	 * 
	 * @param value 
	 */	
	public static double GetDouble(String value)
	{
		double rt = -1;
		if (value == null)
			return rt;

		try { rt = Double.parseDouble(value); }
		catch (Exception ex) { rt = -1; }
		return rt;
	}
	/**
	 * String To double
	 * @param value
	 * @param dDefault
	 * @return
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 09. 10 AM 10:58:27
	 * @since ModelWeb 1.0
	 */
	public static double GetDouble(String value, double dDefault)
	{
		double rt = dDefault;
		if (value == null) { return rt; } 
		try { rt = Double.parseDouble(value); }
		catch (Exception ex) { rt = dDefault; }
		return rt;
	}
	/**
	 * String To double
	 * @param value
	 * @param dDefault
	 * @return
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 09. 10 AM 10:58:27
	 * @since ModelWeb 1.0
	 */
	public static Double GetDouble(String value, Object dDefault)
	{
		if (value == null) { return (Double)dDefault; }
		try { return Double.parseDouble(value); }
		catch (Exception ex) { return (Double) dDefault; }
	}
}
