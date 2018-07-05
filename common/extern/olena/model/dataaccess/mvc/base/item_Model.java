package common.extern.olena.model.dataaccess.mvc.base;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Calendar;
import java.util.Vector;

import common.extern.olena.model.dataaccess.util.core.query.queryProducts.fieldExpressionRelation;
import common.extern.utils.DateTime;
import common.extern.utils.NumeralConvert;

/**
 * Database package model
 * Entity Object at Database layer
 * functionality: This can save the datas to Vector type
 *  
 * @author Olena.Zagreba in Truegardener TEAM
 * @version ModelWeb 1.0, 2011. 12. 26 PM 3:51:07
 * @since ModelWeb 1.0
 */
public abstract class item_Model implements IItem_Model
{
	/***Table Info*/
	protected tbl_Model m_tblModel = null;
	/**Data info*/
	protected Vector<Object> m_arrFieldOb;
	/**Table's Type*/
	private int m_nKind = -1;
	public tbl_Model getTableModel() { return m_tblModel; }
	public void setKind(int kind) { m_nKind = kind; }
	public int getKind() { return m_nKind; }
	public String getTableName() 
	{ 
		return m_tblModel.getName(m_nKind);
	}
	public void setName(String strTableName)
	{
		m_tblModel.setName(strTableName);
	}
	public String getFieldLabel(int nIndex) 
	{
		return m_tblModel.getLabel(nIndex, m_nKind);
	}
	public String getBeanNode(int nIndex) 
	{
		return m_tblModel.getBean(nIndex, m_nKind);
	}
	public String getXmlNode(int nIndex) 
	{
		return m_tblModel.getXml(nIndex, m_nKind);
	}
	public int getFieldLength()
	{
		return m_tblModel.getLength();
	}
	public int getFieldType(int nIndex) 
	{
		return m_tblModel.getType(nIndex, m_nKind);
	}
	public fieldExpressionRelation getFieldInfo(int nIndex) 
	{
		return m_tblModel.getFieldExpressionRelation(nIndex, m_nKind);
	}
	public boolean isAvaiable(int nFieldIndex) {
		boolean result = false;
		Object objField = m_arrFieldOb.get(nFieldIndex);
		if(objField!=null) {
			fieldExpressionRelation fieldExpressionInfo = m_tblModel.getFieldExpressionRelation(nFieldIndex, m_nKind);
			switch(getFieldType(nFieldIndex)) {
				case Types.BIGINT: {
					if(objField instanceof BigDecimal) { result = true; }
					else if(objField instanceof Long) {
						if(fieldExpressionInfo.isInvalidNull()) { 
							result = true;
						}
						else {
							long nInavaiable = fieldExpressionInfo.getLongInvalidValue();
							if((Long)objField!=nInavaiable) { result = true; }
						}
					}
					break;
				}
				case Types.INTEGER: { 
					if(objField instanceof BigDecimal) { result = true; }
					else if(objField instanceof Integer) {
						if(fieldExpressionInfo.isInvalidNull()) { 
							result = true;
						}
						else  {
							int nInavaiable = fieldExpressionInfo.getIntInvalidValue();
							if((Integer)objField!=nInavaiable) { result = true; }					
						}
					}
					break;
					
				}
				case Types.SMALLINT: {
					if(objField instanceof BigDecimal) { result = true; }
					else if(objField instanceof Short) {
						if(fieldExpressionInfo.isInvalidNull()) { 
							result = true;
						}
						else {
							short nInavaiable = fieldExpressionInfo.getShortInvalidValue();
							if((Short)objField!=nInavaiable) { result = true; }
						}
					}
					break;
				}
				case Types.TINYINT: {
					if(objField instanceof BigDecimal) {
						result = true;
					}
					else if(objField instanceof Byte) {
						if(fieldExpressionInfo.isInvalidNull()) { 
							result = true;
						}
						else {
							byte nInavaiable = fieldExpressionInfo.getByteInvalidValue();
							if((Byte)objField!=nInavaiable) { result = true; }
						}
					}
					break;
				}
				case Types.FLOAT: {
					if(objField instanceof BigDecimal) {
						result = true;
					}
					else if(objField instanceof Float) {
						if(fieldExpressionInfo.isInvalidNull()) { 
							result = true;
						}
						else {
							float fInavaiable = fieldExpressionInfo.getFloatInvalidValue();
							if((Float)objField!=fInavaiable) { result = true; }
						}
					}
					break;
				}
				case Types.DOUBLE: {
					if(objField instanceof BigDecimal) {
						result = true;
					}
					else if(objField instanceof Double) {
						if(fieldExpressionInfo.isInvalidNull()) { 
							result = true;
						}
						else {
							double dInavaiable = fieldExpressionInfo.getDoubleInvalidValue();
							if((Double)objField!=dInavaiable) { result = true; }
						}
					}
					break;
				}
				case Types.BIT: {
					if(objField instanceof BigDecimal) {
						result = true;
					}
					else if(objField instanceof Boolean) {
						if(fieldExpressionInfo.isInvalidNull()) { 
							result = true;
						}
						else {
							
						}
						result = true; 
					}
					break;
				}
				case Types.VARCHAR: {
					if(fieldExpressionInfo.isInvalidNull()) { 
						result = true;
					}
					else {
						String strInavaiable = fieldExpressionInfo.getStringInvalidValue();
						if(objField instanceof String) {
							if(strInavaiable==null) { result = true; }
							else {
								if(fieldExpressionInfo.isInvalidNull()) { 
									result = true;
								}
								else {
									if(!strInavaiable.equals(objField)){
										result = true;
									}
								}
							}
						}
					}
					break;
				}
				case Types.LONGVARCHAR: {
					if(fieldExpressionInfo.isInvalidNull()) { 
						result = true;
					}
					else {
						String strInavaiable = fieldExpressionInfo.getStringInvalidValue();
						if(objField instanceof String) {
							if(strInavaiable==null) { result = true; }
							else {
								if(!strInavaiable.equals(objField)){
									result = true;
								}
							}
						}
					}
					break;
				}
				case Types.DATE: {
					if(objField instanceof Date) {
						if(fieldExpressionInfo.isInvalidNull()) { 
							result = true;
						}
						else {
							result = true;
						}
					}
					break;
				}
				case Types.TIME: {
					if(objField instanceof Time) {
						if(fieldExpressionInfo.isInvalidNull()) { 
							result = true;
						}
						else {
							result = true;	
						}
					}
					break;
				}
				case Types.TIMESTAMP: {
					if(objField instanceof Timestamp) {
						if(fieldExpressionInfo.isInvalidNull()) { 
							result = true;
						}
						else {
							result = true;	
						}
					}
					break;
				}
				default: { }
			
			}
		}
		return result;
	}
	@SuppressWarnings("static-access")
	public void init(tbl_Model tbl_Model) {
		if(tbl_Model==null) return;
		m_tblModel = tbl_Model;
		m_arrFieldOb = new Vector<Object>();
		Object objInavaiable = null;
		int nLength = m_tblModel.getLength();
		for(int i = m_tblModel.field_id; i < nLength; i++) {
			objInavaiable = m_tblModel.getFieldExpressionRelation(i, m_nKind).getInvalidObject();
			m_arrFieldOb.add(objInavaiable);
		}
	}
	public Object getAvaiable(int nFieldIndex) {
		Object result = null;
		if(nFieldIndex >= tbl_Model.field_id && nFieldIndex < m_tblModel.getLength()) {
			if(isAvaiable(nFieldIndex)) {
				return m_arrFieldOb.get(nFieldIndex);
			}
			else {
				return m_tblModel.getFieldExpressionRelation(nFieldIndex, m_nKind).getInvalidObject();
			}
		}
		return result;
	}
	public void setAvaiable(int nFieldIndex, Object result) {
		if(result!=null) {
			switch(getFieldType(nFieldIndex)) {
				case Types.BIGINT: {
					if(result instanceof Long) {
						m_arrFieldOb.set(nFieldIndex, result); 
					}
					else if(result instanceof Integer) {
						long Obj = (Integer)result;
						m_arrFieldOb.set(nFieldIndex, Obj); 
					}
					else if(result instanceof Short) {
						long Obj = (Short)result;
						m_arrFieldOb.set(nFieldIndex, Obj); 
					}
					else if(result instanceof Byte) {
						long Obj = (Byte)result;
						m_arrFieldOb.set(nFieldIndex, Obj); 
					}
					else if(result instanceof BigDecimal) {
						m_arrFieldOb.set(nFieldIndex, ((BigDecimal)result).toBigInteger().longValue());
					}
					break;
				}
				case Types.INTEGER: {
					if (result instanceof Integer) {
						m_arrFieldOb.set(nFieldIndex, result);
					}
					else if(result instanceof Long) {
						int Obj = ((Long)result).intValue();
						m_arrFieldOb.set(nFieldIndex, Obj);
					}
					else if(result instanceof Short) {
						int Obj = (Short)result;
						m_arrFieldOb.set(nFieldIndex, Obj); 
					}
					else if(result instanceof Byte) {
						int Obj = (Byte)result;
						m_arrFieldOb.set(nFieldIndex, Obj); 
					}
					else if(result instanceof BigDecimal) {
						m_arrFieldOb.set(nFieldIndex, ((BigDecimal)result).intValueExact());
					}
					break;
				}
				case Types.SMALLINT: {
					if(result instanceof Short) {
						m_arrFieldOb.set(nFieldIndex, result);
					}
					else if(result instanceof Byte) {
						byte Obj = ((Byte)result).byteValue();
						m_arrFieldOb.set(nFieldIndex, Obj);
					}
					else if(result instanceof Integer) {
						short Obj = ((Integer)result).shortValue();
						m_arrFieldOb.set(nFieldIndex, Obj);
					}
					else if(result instanceof Long) {
						short Obj = ((Long)result).shortValue();
						m_arrFieldOb.set(nFieldIndex, Obj);
					}
					else if(result instanceof BigDecimal) {
						m_arrFieldOb.set(nFieldIndex, ((BigDecimal)result).shortValueExact());
					}
					break;
				}
				case Types.TINYINT: {
					if(result instanceof Byte) {
						m_arrFieldOb.set(nFieldIndex, result);
					}
					else if(result instanceof Short) {
						short Obj = ((Short)result).shortValue();
						m_arrFieldOb.set(nFieldIndex, Obj);
					}
					else if(result instanceof Integer) {
						short Obj = ((Integer)result).shortValue();
						m_arrFieldOb.set(nFieldIndex, Obj);
					}
					else if(result instanceof Long) {
						short Obj = ((Long)result).shortValue();
						m_arrFieldOb.set(nFieldIndex, Obj);
					}
					else if(result instanceof BigDecimal) {
						 m_arrFieldOb.set(nFieldIndex, ((BigDecimal)result).byteValueExact());			
					}
					break;
				}
				case Types.FLOAT: {
					if(result instanceof Float) {
						m_arrFieldOb.set(nFieldIndex, result);
					}
					else if(result instanceof Double) {
						float Obj = ((Double)result).floatValue();
						m_arrFieldOb.set(nFieldIndex, Obj);
					}
					else if(result instanceof Byte) {
						float Obj = ((Byte)result).floatValue();
						m_arrFieldOb.set(nFieldIndex, Obj);
					}
					else if(result instanceof Short) {
						float Obj = ((Short)result).floatValue();
						m_arrFieldOb.set(nFieldIndex, Obj);
					}
					else if(result instanceof Integer) {
						float Obj = ((Integer)result).floatValue();
						m_arrFieldOb.set(nFieldIndex, Obj);
					}
					else if(result instanceof Long) {
						float Obj = ((Long)result).floatValue();
						m_arrFieldOb.set(nFieldIndex, Obj);
					}
					else if(result instanceof BigDecimal) {
						m_arrFieldOb.set(nFieldIndex, ((BigDecimal)result).floatValue());
					}
					break;
				}
				case Types.DOUBLE:
				{
					if(result instanceof Double) {
						m_arrFieldOb.set(nFieldIndex, result);
						
					}
					else if(result instanceof Float) {
						double Obj = ((Float)result).doubleValue();
						m_arrFieldOb.set(nFieldIndex, Obj);
					}
					else if(result instanceof Byte) {
						double Obj = ((Byte)result).doubleValue();
						m_arrFieldOb.set(nFieldIndex, Obj);
					}
					else if(result instanceof Short) {
						double Obj = ((Short)result).doubleValue();
						m_arrFieldOb.set(nFieldIndex, Obj);
					}
					else if(result instanceof Integer) {
						double Obj = ((Integer)result).doubleValue();
						m_arrFieldOb.set(nFieldIndex, Obj);
					}
					else if(result instanceof Long) {
						double Obj = ((Long)result).doubleValue();
						m_arrFieldOb.set(nFieldIndex, Obj);
					}
					else if(result instanceof BigDecimal) {
						m_arrFieldOb.set(nFieldIndex, ((BigDecimal)result).doubleValue());
					}
					break;
				}
				case Types.BIT:
				{
					if(result instanceof Boolean) {
						m_arrFieldOb.set(nFieldIndex, result);
					}
					else if(result instanceof BigDecimal) {
						m_arrFieldOb.set(nFieldIndex, ((BigDecimal)result).toBigInteger().longValue() != 0);
					}
					break;
				}
				case Types.VARCHAR: {
					if(result instanceof String) m_arrFieldOb.set(nFieldIndex, result);
					break;
				}
				case Types.LONGVARCHAR: {
					if(result instanceof String) m_arrFieldOb.set(nFieldIndex, result);
					break;
				}
				case Types.DATE:  {
					if(result instanceof Date) {
						m_arrFieldOb.set(nFieldIndex, result);
					}
					if(result instanceof java.util.Date) {
						m_arrFieldOb.set(nFieldIndex, new Date(((java.util.Date)result).getTime()));
					}
					else if(result instanceof Calendar) {
						m_arrFieldOb.set(nFieldIndex, DateTime.getDateFromCalendar(((Calendar)result)));
					}
					else if(result instanceof String) {
						String strDateFormat = getFieldInfo(nFieldIndex).getDateFormat();
						m_arrFieldOb.set(nFieldIndex, DateTime.GetDateFromString((String)result, strDateFormat));
					}
					break;
				}
				case Types.TIME: {
					if(result instanceof Time) {
						m_arrFieldOb.set(nFieldIndex, result);
					}
					if(result instanceof java.util.Date) {
						m_arrFieldOb.set(nFieldIndex, new Time(((java.util.Date)result).getTime()));
					}
					else if(result instanceof Calendar) {
						m_arrFieldOb.set(nFieldIndex, DateTime.getTimeFromCalendar(((Calendar)result)));
					}
					else if(result instanceof String) {
						String strTimeFormat = getFieldInfo(nFieldIndex).getTimeFormat();
						m_arrFieldOb.set(nFieldIndex, DateTime.GetTimeFromString((String)result, strTimeFormat));
					}
					break;
				}
				case Types.TIMESTAMP: {
					if(result instanceof Timestamp) {
						m_arrFieldOb.set(nFieldIndex, result);
					}
					if(result instanceof java.util.Date) {
						m_arrFieldOb.set(nFieldIndex, new Timestamp(((java.util.Date)result).getTime()));
					}
					else if(result instanceof Calendar) {
						m_arrFieldOb.set(nFieldIndex, DateTime.getTimeStampFromCalendar(((Calendar)result)));
					}
					else if(result instanceof String) {
						String strDateTimeFormat = getFieldInfo(nFieldIndex).getDateTimeFormat();
						m_arrFieldOb.set(nFieldIndex, DateTime.GetDateTimeFromString((String)result, strDateTimeFormat));
					}
					break;
				}
				default: { }
			}
		}
	}
	@SuppressWarnings("unused")
	public int getFieldIndexFromField(String strField) {
		int result = -1;
		if(strField!=null) {
			int nIndex;
			tbl_Model tblInfo = getTableModel();
			int nLength = tblInfo.getLength();
			for(nIndex = 0; nIndex < nLength; nIndex++) {
				if(strField.equals(tblInfo.getLabel(nIndex, m_nKind))) {
					return nIndex;
				}
			}
		}
		return result;
	}
	@SuppressWarnings("unused")
	public int getFieldIndexFromBean(String strBean) {
		int result = -1;
		if(strBean!=null) {
			int nIndex;
			tbl_Model tblInfo = getTableModel();
			int nLength = tblInfo.getLength();
			for(nIndex = 0; nIndex < nLength; nIndex++) {
				if(strBean.equals(tblInfo.getBean(nIndex, m_nKind))) {
					return nIndex;
				}
			}
		}
		return result;
	}
	public int getFieldIndexFromXmlNode(String strXmlNode) {
		int result = -1;
		if(strXmlNode!=null) {
			int nIndex;
			tbl_Model tblInfo = getTableModel();
			int nLength = tblInfo.getLength();
			for(nIndex = 0; nIndex < nLength; nIndex++) {
				if(strXmlNode.equals(tblInfo.getXml(nIndex, m_nKind))) {
					return nIndex;
				}
			}
		}
		return result;
	}
	public static String getAvaiableXml(int nDbFieldType, Object resultObj) {
		String result = null;
		switch(nDbFieldType) {
			case Types.BIGINT: {
				result = String.valueOf(resultObj);
				break;
			}
			case Types.INTEGER: {
				result = String.valueOf(resultObj);
				break;
			}
			case Types.SMALLINT: {
				result = String.valueOf(resultObj);
				break;
			}
			case Types.TINYINT: {
				result = String.valueOf(resultObj);
				break;
			}
			case Types.FLOAT: {
				result = String.valueOf(resultObj);
				break;
			}
			case Types.DOUBLE: {
				result = String.valueOf(resultObj);
				break;
			}				
			case Types.BIT: {
				result = String.valueOf(resultObj);
				break;
			}
			case Types.VARCHAR: {
				result = (String)resultObj;
				break;
			}
			case Types.LONGVARCHAR: {
				result = (String)resultObj;
				break;
			}
			case Types.DATE: {
				Object obj = resultObj;
				result = DateTime.GetDateStringFromObj(obj, null);
				break;
			}
			case Types.TIME: {
				Object obj = resultObj;
				result = DateTime.GetTimeStringFromObj(obj, null);
				break;
			}
			case Types.TIMESTAMP: {
				Object obj = resultObj;
				result = DateTime.GetDateTimeStringFromObj(obj, null);
				break;
			}
			default: { }
		}
		return result;
	}
	public String getAvaiableXml(int nFieldIndex) {
		String result = null;
		if(nFieldIndex >= tbl_Model.field_id && nFieldIndex < m_tblModel.getLength()) {
			if(isAvaiable(nFieldIndex)) {
				switch(getFieldType(nFieldIndex)) {
					case Types.BIGINT: {
						result = String.valueOf(m_arrFieldOb.get(nFieldIndex));
						break;
					}
					case Types.INTEGER: {
						result = String.valueOf(m_arrFieldOb.get(nFieldIndex));
						break;
					}
					case Types.SMALLINT: {
						result = String.valueOf(m_arrFieldOb.get(nFieldIndex));
						break;
					}
					case Types.TINYINT: {
						result = String.valueOf(m_arrFieldOb.get(nFieldIndex));
						break;
					}
					case Types.FLOAT: {
						result = String.valueOf(m_arrFieldOb.get(nFieldIndex));
						break;
					}
					case Types.DOUBLE: {
						result = String.valueOf(m_arrFieldOb.get(nFieldIndex));
						break;
					}				
					case Types.BIT: {
						result = String.valueOf(m_arrFieldOb.get(nFieldIndex));
						break;
					}
					case Types.VARCHAR: {
						result = (String)m_arrFieldOb.get(nFieldIndex);
						break;
					}
					case Types.LONGVARCHAR: {
						result = (String)m_arrFieldOb.get(nFieldIndex);
						break;
					}
					case Types.DATE: {
						Object obj = m_arrFieldOb.get(nFieldIndex);
						String strDateFormat = getFieldInfo(nFieldIndex).getDateFormat();
						result = DateTime.GetDateStringFromObj(obj, strDateFormat);
						break;
					}
					case Types.TIME: {
						Object obj = m_arrFieldOb.get(nFieldIndex);
						String strTimeFormat = getFieldInfo(nFieldIndex).getTimeFormat();
						result = DateTime.GetTimeStringFromObj(obj, strTimeFormat);
						break;
					}
					case Types.TIMESTAMP: {
						Object obj = m_arrFieldOb.get(nFieldIndex);
						String strDateTimeFormat = getFieldInfo(nFieldIndex).getTimeFormat();
						result = DateTime.GetDateTimeStringFromObj(obj, strDateTimeFormat);
						break;
					}
					default: { }
				}
			}
		}
		return result;
	}
	public void setAvaiableXml(String strNode, String result) {
		if(strNode!=null && result!=null) {
			int nFieldIndex = getFieldIndexFromXmlNode(strNode);
			if(nFieldIndex!=-1) {
				switch(getFieldType(nFieldIndex)) {
					case Types.BIGINT: {
						m_arrFieldOb.set(nFieldIndex, NumeralConvert.GetLong(result, getFieldInfo(nFieldIndex).getInvalidObject()));
						break;
					}
					case Types.INTEGER: {
						m_arrFieldOb.set(nFieldIndex, NumeralConvert.GetInt(result, getFieldInfo(nFieldIndex).getInvalidObject()));
						break;
					}
					case Types.SMALLINT: {
						m_arrFieldOb.set(nFieldIndex, NumeralConvert.GetShort(result, getFieldInfo(nFieldIndex).getInvalidObject()));
						break;
					}
					case Types.TINYINT: {
						m_arrFieldOb.set(nFieldIndex, NumeralConvert.GetByte(result, getFieldInfo(nFieldIndex).getInvalidObject()));
						break;
					}
					case Types.FLOAT: {
						m_arrFieldOb.set(nFieldIndex, NumeralConvert.GetFloat(result, getFieldInfo(nFieldIndex).getInvalidObject()));
						break;
					}
					case Types.DOUBLE: {
						m_arrFieldOb.set(nFieldIndex, NumeralConvert.GetDouble(result, getFieldInfo(nFieldIndex).getInvalidObject()));
						break;
					}
					case Types.BIT: {
						m_arrFieldOb.set(nFieldIndex, NumeralConvert.GetBoolean(result, getFieldInfo(nFieldIndex).getInvalidObject()));
						break;
					}
					case Types.VARCHAR: {
						m_arrFieldOb.set(nFieldIndex, result);
						break;
					}
					case Types.LONGVARCHAR: {
						m_arrFieldOb.set(nFieldIndex, result);
						break;
					}
					case Types.DATE: {
						Date dt = null;
						String strDateFormat = getFieldInfo(nFieldIndex).getDateFormat();
						dt = DateTime.GetDateFromString(result, strDateFormat);
						m_arrFieldOb.set(nFieldIndex, dt);
						break;
					}
					case Types.TIME: {
						Time ti = null;
						String strTimeFormat = getFieldInfo(nFieldIndex).getTimeFormat();
						ti = DateTime.GetTimeFromString(result, strTimeFormat);	
						m_arrFieldOb.set(nFieldIndex, ti);
						break;
					}
					case Types.TIMESTAMP: {
						Timestamp ts = null;
						String strDateTimeFormat = getFieldInfo(nFieldIndex).getTimeFormat();
						ts = DateTime.GetDateTimeFromString(result, strDateTimeFormat);	
						m_arrFieldOb.set(nFieldIndex, ts);
						break;
					}
					default: { }
				}
			}
		}
	}
}
