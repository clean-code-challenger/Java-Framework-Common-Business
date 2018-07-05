package common.extern.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionUtil {
	/**
	 * @param strLabel
	 * @return
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 12. 05 AM 9:00:44
	 * @since ModelWeb 1.0
	 */
	public static String upperFirst(String strLabel) {
		String result = null;
		if(strLabel!=null) {
			if(strLabel.length() > 1) {
				result = strLabel.substring(0, 1).toUpperCase() + strLabel.substring(1);
			}
		}
		return result;
	}
	
	/**
	 * @param strLabel
	 * @return
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 12. 05 AM 9:00:46
	 * @since ModelWeb 1.0
	 */
	public static String upper(String strLabel) {
		String result = null;
		if(strLabel!=null) {
			if(strLabel.length() > 0) {
				result = strLabel.toUpperCase();
			}
		}
		return result;
	}
	
	/**
	 * @param strLabel
	 * @return
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 12. 05 AM 9:00:48
	 * @since ModelWeb 1.0
	 */
	public static String lowerFirst(String strLabel) {
		String result = null;
		if(strLabel!=null) {
			if(strLabel.length() > 1) {
				result = strLabel.substring(0, 1).toLowerCase() + strLabel.substring(1);
			}
		}
		return result;
	}
	/**
	 * @param strLabel
	 * @return
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 12. 05 AM 9:00:50
	 * @since ModelWeb 1.0
	 */
	public static String lower(String strLabel) {
		String result = null;
		if(strLabel!=null) {
			if(strLabel.length() > 0) {
				result = strLabel.toLowerCase();
			}
		}
		return result;
	}
	
	/**
	 * @param strBean
	 * @return
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 09. 08 PM 2:52:57
	 * @since ModelWeb 1.0
	 */
	public static String makeGetBean(String strBean) {
		String result = null;
		if(strBean!=null) {
			if(strBean.length() > 0) {
				String strFirst = strBean.trim().substring(0, 1).toUpperCase();
				String strBody = strBean.substring(1);
				result = "get" + strFirst + strBody; 
			}
		}
		return result;
	}
	
	/**
	 * @param strBean
	 * @return
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 09. 08 PM 2:53:49
	 * @since ModelWeb 1.0
	 */
	public static String makeSetBean(String strBean) {
		String result = null;
		if(strBean!=null) {
			if(strBean.length() > 0) {
				String strFirst = strBean.trim().substring(0, 1).toUpperCase();
				String strBody = strBean.substring(1);
				result = "set" + strFirst + strBody; 
			}
		}
		return result;
	}
	/**
	 * 
	 * @param strBean
	 * @param resultObj
	 * @param nPutInteger
	 * @author Olena.Zagreba
	 * @version ModelWeb 2013. 04. 25 PM 1:18:22
	 * @since ModelWeb
	 */
	public static void invokePutIntegerMethod(String strBean, Object resultObj, int nPutInteger){
		String strMethod_SetBean = makeSetBean(strBean);
		try {
			Method method = resultObj.getClass().getMethod(strMethod_SetBean, long.class);
			if(method!=null) {
				long number = nPutInteger;
				method.invoke(resultObj, number);
				return;
			}
		}
		catch(NoSuchMethodException ex){ }
		catch (IllegalArgumentException ex) { }
		catch (IllegalAccessException ex) {}
		catch (InvocationTargetException ex) { }	
		try {
			Method method = resultObj.getClass().getMethod(strMethod_SetBean, Long.class);
			if(method!=null) {
				Long number = (long)nPutInteger;
				method.invoke(resultObj, number);
				return;
			}
		}
		catch(NoSuchMethodException ex){ }
		catch (IllegalArgumentException ex) { }
		catch (IllegalAccessException ex) {}
		catch (InvocationTargetException ex) { }
		try {
			Method method = resultObj.getClass().getMethod(strMethod_SetBean, int.class);
			if(method!=null) {
				int number = nPutInteger;
				method.invoke(resultObj, number);
				return;
			}
		}
		catch(NoSuchMethodException ex){ }
		catch (IllegalArgumentException ex) { }
		catch (IllegalAccessException ex) {}
		catch (InvocationTargetException ex) { }
		try {
			Method method = resultObj.getClass().getMethod(strMethod_SetBean, Integer.class);
			if(method!=null) {
				Integer number = nPutInteger;
				method.invoke(resultObj, number);
				return;
			}
		}
		catch(NoSuchMethodException ex){ }
		catch (IllegalArgumentException ex) { }
		catch (IllegalAccessException ex) {}
		catch (InvocationTargetException ex) { }
		try {
			Method method = resultObj.getClass().getMethod(strMethod_SetBean, short.class);
			if(method!=null) {
				short number = (short)nPutInteger;
				method.invoke(resultObj, number);
				return;
			}
		}
		catch(NoSuchMethodException ex){ }
		catch (IllegalArgumentException ex) { }
		catch (IllegalAccessException ex) {}
		catch (InvocationTargetException ex) { }
		try {
			Method method = resultObj.getClass().getMethod(strMethod_SetBean, Short.class);
			if(method!=null) {
				Short number = (short)nPutInteger;
				method.invoke(resultObj, number);
				return;
			}
		}
		catch(NoSuchMethodException ex){ }
		catch (IllegalArgumentException ex) { }
		catch (IllegalAccessException ex) {}
		catch (InvocationTargetException ex) { }
		try {
			Method method = resultObj.getClass().getMethod(strMethod_SetBean, byte.class);
			if(method!=null) {
				byte number = (byte)nPutInteger;
				method.invoke(resultObj, number);
				return;
			}
		}
		catch(NoSuchMethodException ex){ }
		catch (IllegalArgumentException ex) { }
		catch (IllegalAccessException ex) {}
		catch (InvocationTargetException ex) { }
		try {
			Method method = resultObj.getClass().getMethod(strMethod_SetBean, Byte.class);
			if(method!=null) {
				Byte number = (byte)nPutInteger;
				method.invoke(resultObj, number);
				return;
			}
		}
		catch(NoSuchMethodException ex){ }
		catch (IllegalArgumentException ex) { }
		catch (IllegalAccessException ex) {}
		catch (InvocationTargetException ex) { }
	}
	/**
	 * @param strBean
	 * @param resultObj
	 * @param nPutInteger
	 * @author Olena.Zagreba
	 * @version ModelWeb 2013. 04. 25 PM 1:18:22
	 * @since ModelWeb
	 */
	public static int invokeGetIntegerMethod(String strBean, Object resultObj){
		int result = -1;
		String strMethod_GetBean = makeSetBean(strBean);
		try {
			Method method = resultObj.getClass().getMethod(strMethod_GetBean);
			if (method != null) {
				
				Object number = method.invoke(resultObj);
				if(number instanceof Integer)
				result = (Integer)number;
				if(number instanceof Long)
					result = ((Long)number).intValue();
				if(number instanceof Short)
					result = (Short)number;
				if(number instanceof Byte)
					result = (Byte)number;
			}
		} catch (NoSuchMethodException ex) {
		} catch (IllegalArgumentException ex) {
		} catch (IllegalAccessException ex) {
		} catch (InvocationTargetException ex) {
		}
		return result;
	}
	/**
	 * 
	 * @param strBean
	 * @param resultObj
	 * @param nPutLong
	 * @author Olena.Zagreba
	 * @version ModelWeb 2013. 04. 25 PM 1:18:22
	 * @since ModelWeb
	 */
	public static void invokePutLongMethod(String strBean, Object resultObj, long nPutLong){
		String strMethod_SetBean = makeSetBean(strBean);
		try {
			Method method = resultObj.getClass().getMethod(strMethod_SetBean,
					long.class);
			if (method != null) {
				long number = nPutLong;
				method.invoke(resultObj, number);
				return;
			}
		} catch (NoSuchMethodException ex) {
		} catch (IllegalArgumentException ex) {
		} catch (IllegalAccessException ex) {
		} catch (InvocationTargetException ex) {
		}
		try {
			Method method = resultObj.getClass().getMethod(strMethod_SetBean,
					Long.class);
			if (method != null) {
				Long number = (long) nPutLong;
				method.invoke(resultObj, number);
				return;
			}
		} catch (NoSuchMethodException ex) {
		} catch (IllegalArgumentException ex) {
		} catch (IllegalAccessException ex) {
		} catch (InvocationTargetException ex) {
		}
		try {
			Method method = resultObj.getClass().getMethod(strMethod_SetBean,
					int.class);
			if (method != null) {
				int number = (int) nPutLong;
				method.invoke(resultObj, number);
				return;
			}
		} catch (NoSuchMethodException ex) {
		} catch (IllegalArgumentException ex) {
		} catch (IllegalAccessException ex) {
		} catch (InvocationTargetException ex) {
		}
		try {
			Method method = resultObj.getClass().getMethod(strMethod_SetBean,
					Integer.class);
			if (method != null) {
				Integer number = (int) nPutLong;
				method.invoke(resultObj, number);
				return;
			}
		} catch (NoSuchMethodException ex) {
		} catch (IllegalArgumentException ex) {
		} catch (IllegalAccessException ex) {
		} catch (InvocationTargetException ex) {
		}
		try {
			Method method = resultObj.getClass().getMethod(strMethod_SetBean,
					short.class);
			if (method != null) {
				short number = (short) nPutLong;
				method.invoke(resultObj, number);
				return;
			}
		} catch (NoSuchMethodException ex) {
		} catch (IllegalArgumentException ex) {
		} catch (IllegalAccessException ex) {
		} catch (InvocationTargetException ex) {
		}
		try {
			Method method = resultObj.getClass().getMethod(strMethod_SetBean,
					Short.class);
			if (method != null) {
				Short number = (short) nPutLong;
				method.invoke(resultObj, number);
				return;
			}
		} catch (NoSuchMethodException ex) {
		} catch (IllegalArgumentException ex) {
		} catch (IllegalAccessException ex) {
		} catch (InvocationTargetException ex) {
		}
		try {
			Method method = resultObj.getClass().getMethod(strMethod_SetBean,
					byte.class);
			if (method != null) {
				byte number = (byte) nPutLong;
				method.invoke(resultObj, number);
				return;
			}
		} catch (NoSuchMethodException ex) {
		} catch (IllegalArgumentException ex) {
		} catch (IllegalAccessException ex) {
		} catch (InvocationTargetException ex) {
		}
		try {
			Method method = resultObj.getClass().getMethod(strMethod_SetBean,
					Byte.class);
			if (method != null) {
				Byte number = (byte) nPutLong;
				method.invoke(resultObj, number);
				return;
			}
		} catch (NoSuchMethodException ex) {
		} catch (IllegalArgumentException ex) {
		} catch (IllegalAccessException ex) {
		} catch (InvocationTargetException ex) {
		}
	}
	/**
	 * 
	 * @param strBean
	 * @param resultObj
	 * @param nPutInteger
	 * @author Olena.Zagreba
	 * @version ModelWeb 2013. 04. 25 PM 1:18:22
	 * @since ModelWeb
	 */
	public static long invokeGetLongMethod(String strBean, Object resultObj){
		long result = -1;
		String strMethod_GetBean = makeGetBean(strBean);
		
		try {
			Method method = resultObj.getClass().getMethod(strMethod_GetBean);
			if (method != null) {
				Object number = method.invoke(resultObj);
				if(number instanceof Long)
				result = (Long)number;
				if(number instanceof Integer)
					result = ((Integer)number).longValue();
				if(number instanceof Short)
					result = (Short)number;
				if(number instanceof Byte)
					result = (Byte)number;
			}
		} catch (NoSuchMethodException ex) {
		} catch (IllegalArgumentException ex) {
		} catch (IllegalAccessException ex) {
		} catch (InvocationTargetException ex) {
		}
		return result;
	}
}
