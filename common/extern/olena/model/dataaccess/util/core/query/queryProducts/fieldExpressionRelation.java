package common.extern.olena.model.dataaccess.util.core.query.queryProducts;

import java.sql.Types;

import common.extern.olena.model.dataaccess.util.core.query.base.relationBase;

/**
 *
 * @author Olena.Zagreba
 * @version ModelWeb 1.0, 2012. 09. 03 PM 1:36:56
 * @since ModelWeb 1.0
 */
public class fieldExpressionRelation extends relationBase
{
	public static Object INVALID_NUMBER_INIT = (long)0;
	public static Object INVALID_STRING_INIT = "";
	
	public fieldExpressionRelation() { }
	
	private boolean m_bIsPrimaryAutoIncrease = true;
	private Object m_objInvalid = null;
	private int	m_nStringLength = 256;	
	private String m_strDateFormat = "y-m-d";
	private String m_strTimeFormat = "H:M:S";
	private String m_strDateTimeFormat = "y-m-d H:M:S";
	/**
	 * @param nFieldIndex
	 * @param nFieldType
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 09. 03 PM 1:36:56
	 * @since ModelWeb 1.0
	 */
	private void initNumber(int nFieldIndex, int nFieldType, long nInitVal) {
		switch(nFieldType){
			case Types.BIGINT:{
				m_objInvalid = (long)nInitVal;
				break;
			}
			case Types.INTEGER:{
				m_objInvalid = (int)nInitVal;
				break;
			}
			case Types.SMALLINT:{
				m_objInvalid = (short)nInitVal;
				break;
			}
			case Types.TINYINT:{
				m_objInvalid = (byte)nInitVal;
				break;
			}
			case Types.FLOAT:{
				m_objInvalid = (float)nInitVal;
				break;
			}
			case Types.DOUBLE:{
				m_objInvalid = (float)nInitVal;
				break;
			}
		}
	}
	/**
	 * @param nFieldIndex
	 * @param nFieldType
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 09. 03 PM 1:36:56
	 * @since ModelWeb 1.0
	 */
	private void initString(int nFieldIndex, int nFieldType, String strInitVal) {
		switch(nFieldType){
			case Types.VARCHAR:{
				m_objInvalid = strInitVal;
				break;
			}
			case Types.LONGVARCHAR:{
				m_objInvalid = strInitVal;
				break;
			}
		}
	}
	/**
	 * 
	 * @param nFieldIndex
	 * @param nFieldType
	 * @author Olena.Zagreba
	 * @version ModelWeb 2013. 05. 08 AM 11:00:57
	 * @since ModelWeb
	 */
	public void init(int nFieldIndex, int nFieldType, boolean bInit) {
		if(nFieldType==Types.BIGINT || nFieldType==Types.INTEGER || nFieldType==Types.SMALLINT || nFieldType==Types.TINYINT ||  nFieldType==Types.FLOAT || nFieldType==Types.DOUBLE){
			if(bInit) {
				if(m_objInvalid==null) {
					boolean bInitLong = false;
					if(INVALID_NUMBER_INIT!=null) {
						if(INVALID_NUMBER_INIT instanceof Long) {
							bInitLong = true;
						}
						else if(INVALID_NUMBER_INIT instanceof Integer) {
							bInitLong = true;
						}
						else if(INVALID_NUMBER_INIT instanceof Short) {
							bInitLong = true;
						}
						else if(INVALID_NUMBER_INIT instanceof Byte) {
							bInitLong = true;
						}
					}
					if(bInitLong) {
						initNumber(nFieldIndex, nFieldType, (Long)INVALID_NUMBER_INIT);
					}
				}
			}
		}
		
		else {
			m_bIsPrimaryAutoIncrease = false;
			switch(nFieldType){
				case Types.VARCHAR: 
				case Types.LONGVARCHAR:{
					if(m_objInvalid==null) {
						boolean bInitString = false;
						if(INVALID_STRING_INIT!=null) {
							if(INVALID_NUMBER_INIT instanceof String) {
								bInitString = true;
							}
						}
						if(bInitString) {
							initString(nFieldIndex, nFieldType, (String)INVALID_NUMBER_INIT);
						}
					}
					break;
				}
				case Types.BIT:{
					m_objInvalid = false;
					break;
				}
				case Types.DATE:{
					break;
				}
				case Types.TIME:{
					break;
				}
				case Types.TIMESTAMP:{
					break;
				}
			}
		}
	}
	
	public boolean isPrimaryAutoIncrease() { return m_bIsPrimaryAutoIncrease; }
	public void setPrimaryAutoIncrease(boolean isPrimaryAutoIncrease) { m_bIsPrimaryAutoIncrease = isPrimaryAutoIncrease; }

	//Iavalid Values
	public boolean isInvalidNull() { return (m_objInvalid==null); }
	public Object getInvalidObject() { return m_objInvalid; }

	public long getLongInvalidValue() { return (Long)m_objInvalid; }
	public void setLongInvalidValue(long longalidValue) { m_objInvalid = longalidValue; }

	public int getIntInvalidValue() { return (Integer)m_objInvalid; }
	public void setIntInvalidValue(int intValidValue) { m_objInvalid = intValidValue; }

	public short getShortInvalidValue() { return (Short)m_objInvalid; }
	public void setShortInvalidValue(short shortValidValue) { m_objInvalid = shortValidValue; }
	
	public byte getByteInvalidValue() { return (Byte)m_objInvalid; }
	public void setByteInvalidValue(byte byteValidValue) { m_objInvalid = byteValidValue; }

	public boolean getBoolDefaultValue() { return (Boolean)m_objInvalid; }
	public void setBoolDefaultValue(boolean boolValidValue) { m_objInvalid = boolValidValue; }
	
	public double getDoubleInvalidValue() { return (Double)m_objInvalid; }
	public void setDoubleInvalidValue(double doubleInvalidValue) { m_objInvalid = doubleInvalidValue; }
	
	public float getFloatInvalidValue() { return (Float)m_objInvalid; }
	public void setFloatInvalidValue(float floatInvalidValue) { m_objInvalid = floatInvalidValue; }
	
	public String getStringInvalidValue() { return (String)m_objInvalid; }
	public void setStringInvalidValue(String invalidValue) { m_objInvalid = invalidValue; }
	
	//Other
	public int getStringLength() { return m_nStringLength; }
	public void setStringLength(int stringLength) { m_nStringLength = stringLength; }

	public String getDateFormat() { return m_strDateFormat; }
	public void setDateFormat(String dateFormat) { m_strDateFormat = dateFormat; }

	public String getDateTimeFormat() { return m_strDateTimeFormat; }
	public void setDateTimeFormat(String dateTimeFormat) { m_strDateTimeFormat = dateTimeFormat; }

	public String getTimeFormat() { return m_strTimeFormat; }
	public void setTimeFormat(String timeFormat) { m_strTimeFormat = timeFormat; }
	
}
