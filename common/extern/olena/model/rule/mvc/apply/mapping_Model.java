package common.extern.olena.model.rule.mvc.apply;

import java.io.StringReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Vector;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;

import common.extern.olena.model.dataaccess.mvc.base.IItem_Model;
import common.extern.olena.model.dataaccess.mvc.base.tbl_Model;
import common.extern.olena.model.infos.dataTransport.key_WebDataProtocol_Model;
import common.extern.utils.DateTime;
import common.extern.utils.ReflectionUtil;
import common.extern.utils.Json.TruegardenerJSONReader;
import common.extern.utils.Json.TruegardenerJSONWriter;
import common.extern.utils.xml.TruegardenerOutputFormat;
import common.extern.utils.xml.TruegardenerXMLSerializer;

@SuppressWarnings("unchecked")
public class mapping_Model
{
	protected mapping_Model()  {
		mDefaultDataProtocol = new key_WebDataProtocol_Model();
	}
	protected mapping_Model(IItem_Model itemModel) {
		m_ItemModel = itemModel;
		mDefaultDataProtocol = new key_WebDataProtocol_Model();
	}
	protected mapping_Model(IItem_Model itemModel, String strRootKey, String strNodeKey, String strCountKey, String strExceptionKey) {
		m_ItemModel = itemModel;
		mDefaultDataProtocol = new key_WebDataProtocol_Model();
		mDefaultDataProtocol.setRootKey(strRootKey);
		mDefaultDataProtocol.setNodeKey(strNodeKey);
		mDefaultDataProtocol.setCountKey(strCountKey);
		mDefaultDataProtocol.setHasExceptionKey(strExceptionKey);
	}
	private IItem_Model m_ItemModel = null;
	
	private key_WebDataProtocol_Model mDefaultDataProtocol = null;
	public key_WebDataProtocol_Model getDefaultDataProtocol()  { return mDefaultDataProtocol; }
	
	public IItem_Model getItemModel() { return m_ItemModel; }
	public void setItemModel(IItem_Model itemModel) { m_ItemModel = itemModel; }

	public String getHasExceptionKey() { return mDefaultDataProtocol.getHasExceptionKey(); }
	public void setHasExceptionKey(String xmlHasException) { mDefaultDataProtocol.setHasExceptionKey(xmlHasException); }
	
	public String getNodeKey() { return mDefaultDataProtocol.getNodeKey(); }
	public void setNodeKey(String xmlNode) { mDefaultDataProtocol.setNodeKey(xmlNode); }
	
	public String getCountKey() { return mDefaultDataProtocol.getCountKey(); }
	public void setCountKey(String xmlNodeCount) { mDefaultDataProtocol.setCountKey(xmlNodeCount); }
	
	public String getRootKey() { return mDefaultDataProtocol.getRootKey(); }
	public void setRootKey(String xmlRoot) { mDefaultDataProtocol.setRootKey(xmlRoot); }
	
	/**
	 *--------------------------------------Bean-----------------------------------------------
	 *--------------------------------------Bean-----------------------------------------------
	 *--------------------------------------Bean-----------------------------------------------
	 */
	protected Object get_BeanObjFromItem(IItem_Model info, Class beanClass)throws Throwable {
		Object result = null;
		try	{
			if(info!=null && beanClass!=null) {
				result = beanClass.newInstance();
				set_BeanObjFromItem(result, info);
			}
		}
		catch (Throwable e) { throw e; }
		return result;
	}
	protected void set_BeanObjFromItem(Object result, IItem_Model info)throws Throwable {
		try {
			if(result!= null && info!=null) {
				int nFieldIndex;
				int nLength = info.getFieldLength();
				for(nFieldIndex = tbl_Model.field_id; nFieldIndex < nLength; nFieldIndex++) {
					if(info.isAvaiable(nFieldIndex)) {
						switch(info.getFieldType(nFieldIndex)) {
							case Types.BIGINT:  {
								long resultObj = (Long)info.getAvaiable(nFieldIndex);
								String strMethodName = ReflectionUtil.makeSetBean(info.getBeanNode(nFieldIndex));
								try {
									Method method = result.getClass().getMethod(strMethodName, long.class);
									if(method!=null) {
										method.invoke(result, resultObj);
										continue;
									}
								}
								catch(NoSuchMethodException ex){ }
								try {
									Method method = result.getClass().getMethod(strMethodName, java.lang.Long.class);
									if(method!=null) {
										method.invoke(result, resultObj);
										continue;
									}
								}
								catch(NoSuchMethodException ex){ }
								try {
									Method method = result.getClass().getMethod(strMethodName, int.class);
									if(method!=null) {
										int resultConv = (int)resultObj;
										method.invoke(result, resultConv);
										continue;
									}
								}
								catch(NoSuchMethodException ex){ }
								try {
									Method method = result.getClass().getMethod(strMethodName, java.lang.Integer.class);
									if(method!=null) {
										Integer resultConv = (int)resultObj;
										method.invoke(result, resultConv);
										continue;
									}
								}
								catch(NoSuchMethodException ex){ }
								try {
									Method method = result.getClass().getMethod(strMethodName, short.class);
									if(method!=null) {
										int resultConv = (int)resultObj;
										method.invoke(result, resultConv);
										continue;
									}
								}
								catch(NoSuchMethodException ex){ }
								try {
									Method method = result.getClass().getMethod(strMethodName, java.lang.Short.class);
									if(method!=null) {
										Integer resultConv = (int)resultObj;
										method.invoke(result, resultConv);
										continue;
									}
								}
								catch(NoSuchMethodException ex){ }
								try {
									Method method = result.getClass().getMethod(strMethodName, byte.class);
									if(method!=null) {
										byte resultConv = (byte)resultObj;
										method.invoke(result, resultConv);
										continue;
									}
								}
								catch(NoSuchMethodException ex){ }
								try {
									Method method = result.getClass().getMethod(strMethodName, java.lang.Byte.class);
									if(method!=null) {
										Byte resultConv = (byte)resultObj;
										method.invoke(result, resultConv);
										continue;
									}
								}
								catch(NoSuchMethodException ex){ }
								try {
									Method method = result.getClass().getMethod(strMethodName, String.class);
									if(method!=null) {
										String obj = String.valueOf(((Long)resultObj));
										method.invoke(result, obj);
										continue;
									}
								}
								catch(NoSuchMethodException ex){}
								String strField = info.getBeanNode(nFieldIndex).trim();
								try {
									Field field = result.getClass().getField(strField);
									if(field!=null) {
										if(field.getType()==long.class) {
											field.set(result, resultObj);
											continue;
										}
									}
									if(field!=null) { 
										if(field.getType()==java.lang.Long.class) {
											field.set(result, resultObj);
											continue;
										}
									}
									if(field!=null) { 
										if(field.getType()==int.class) {
											int resultConv = (int)resultObj;
											field.set(result, resultConv);
											continue;
										}
									}
									if(field!=null) { 
										if(field.getType()==java.lang.Integer.class) {
											Integer resultConv = (int)resultObj;
											field.set(result, resultConv);
											continue;
										}
									}
									if(field!=null) {
										if(field.getType()==short.class) {
											short resultShort = (short)resultObj;
											field.set(result, resultShort);
											continue;
										}
									}
									if(field!=null) {
										if(field.getType()==java.lang.Short.class) {
											Short resultShort = (short)resultObj;
											field.set(result, resultShort);
											continue;
										}
									}
									if(field!=null) {
										if(field.getType()==byte.class) {
											byte resultByte = (byte)resultObj;
											field.set(result, resultByte);
											continue;
										}
									}
									if(field!=null) {
										if(field.getType()==java.lang.Byte.class) {
											Byte resultByte = (byte)resultObj;
											field.set(result, resultByte);
											continue;
										}
									}
									if(field!=null) {
										if(field.getType()==String.class) {
											String obj = String.valueOf(((Long)resultObj));
											field.set(result, obj);
											continue;
										}
									}
								}
								catch(NoSuchFieldException ex) {}
								break;
							}
							case Types.INTEGER: { 
								int resultObj = (Integer)info.getAvaiable(nFieldIndex);
								String strMethodName = ReflectionUtil.makeSetBean(info.getBeanNode(nFieldIndex));
								try {
									Method method = result.getClass().getMethod(strMethodName, long.class);
									if(method!=null) {
										long resultConv = resultObj;
										method.invoke(result, resultConv);
										continue;
									}
								}
								catch(NoSuchMethodException ex){ }
								try {
									Method method = result.getClass().getMethod(strMethodName, java.lang.Long.class);
									if(method!=null) {
										Long resultConv = (long)resultObj;
										method.invoke(result, resultConv);
										continue;
									}
								}
								catch(NoSuchMethodException ex){ }
								try {
									Method method = result.getClass().getMethod(strMethodName, int.class);
									if(method!=null) {
										method.invoke(result, info.getAvaiable(nFieldIndex));
										continue;
									}
								}
								catch(NoSuchMethodException ex){ }
								try {
									Method method = result.getClass().getMethod(strMethodName, java.lang.Integer.class);
									if(method!=null) {
										method.invoke(result, info.getAvaiable(nFieldIndex));
										continue;
									}
								}
								catch(NoSuchMethodException ex){ }
								try {
									Method method = result.getClass().getMethod(strMethodName, short.class);
									if(method!=null) {
										short resultConv = (short)resultObj;
										method.invoke(result, resultConv);
										continue;
									}
								}
								catch(NoSuchMethodException ex){ }
								try {
									Method method = result.getClass().getMethod(strMethodName, java.lang.Short.class);
									if(method!=null) {
										Short resultConv = (short)resultObj;
										method.invoke(result, resultConv);
										continue;
									}
								}
								catch(NoSuchMethodException ex){ }
								try {
									Method method = result.getClass().getMethod(strMethodName, byte.class);
									if(method!=null) {
										byte resultConv = (byte)resultObj;
										method.invoke(result, resultConv);
										continue;
									}
								}
								catch(NoSuchMethodException ex){ }
								try {
									Method method = result.getClass().getMethod(strMethodName, java.lang.Byte.class);
									if(method!=null) {
										Byte resultConv = (byte)resultObj;
										method.invoke(result, resultConv);
										continue;
									}
								}
								catch(NoSuchMethodException ex){ }
								try {
									Method method = result.getClass().getMethod(strMethodName, String.class);
									if(method!=null) {
										String obj = String.valueOf(((Integer)resultObj));
										method.invoke(result, obj);
										continue;
									}
								}
								catch(NoSuchMethodException ex){ }
								String strField = info.getBeanNode(nFieldIndex).trim();
								try {
									Field field = result.getClass().getField(strField);
									if(field!=null) {
										if(field.getType()==long.class) {
											long resultConv = resultObj;
											field.set(result, resultConv);
											continue;
										}
									}
									if(field!=null) {
										if(field.getType()==java.lang.Long.class) {
											Long resultConv = (long)resultObj;
											field.set(result, resultConv);
											continue;
										}
									}
									if(field!=null) {
										if(field.getType()==int.class) {
											field.set(result, resultObj);
											continue;
										}
									}
									if(field!=null) {
										if(field.getType()==java.lang.Integer.class) {
											field.set(result, resultObj);
											continue;
										}
									}
									if(field!=null) {
										if(field.getType()==short.class) {
											short resultConv = (short)resultObj;
											field.set(result, resultConv);
											continue;
										}
									}
									if(field!=null) {
										if(field.getType()==java.lang.Short.class) {
											Short resultConv = (short)resultObj;
											field.set(result, resultConv);
											continue;
										}
									}
									if(field!=null) {
										if(field.getType()==byte.class) {
											byte resultConv = (byte)resultObj;
											field.set(result, resultConv);
											continue;
										}
									}
									if(field!=null) {
										if(field.getType()==java.lang.Byte.class) {
											Byte resultConv = (byte)resultObj;
											field.set(result, resultConv);
											continue;
										}
									}
									if(field!=null) {
										if(field.getType()==String.class) {
											String obj = String.valueOf(((Integer)resultObj));
											field.set(result, obj);
											continue;
										}
									}
								}
								catch(NoSuchFieldException ex){}
								break;
							}
							case Types.SMALLINT: {
								short resultObj = (Short)info.getAvaiable(nFieldIndex);
								String strMethodName = ReflectionUtil.makeSetBean(info.getBeanNode(nFieldIndex));
								try {
									Method method = result.getClass().getMethod(strMethodName, long.class);
									if(method!=null) {
										long resultConv = resultObj;
										method.invoke(result, resultConv);
										continue;
									}
								}
								catch(NoSuchMethodException ex){ }
								try {
									Method method = result.getClass().getMethod(strMethodName, java.lang.Long.class);
									if(method!=null) {
										Long resultConv = (long)resultObj;
										method.invoke(result, resultConv);
										continue;
									}
								}
								catch(NoSuchMethodException ex){ }
								try {
									Method method = result.getClass().getMethod(strMethodName, int.class);
									if(method!=null) {
										int resultConv = resultObj;
										method.invoke(result, resultConv);
										continue;
									}
								}
								catch(NoSuchMethodException ex){ }
								try {
									Method method = result.getClass().getMethod(strMethodName, java.lang.Integer.class);
									if(method!=null) {
										Integer resultConv = (int)resultObj;
										method.invoke(result, resultConv);
										continue;
									}
								}
								catch(NoSuchMethodException ex){ }
								try {
									Method method = result.getClass().getMethod(strMethodName, short.class);
									if(method!=null) {
										short resultConv = resultObj;
										method.invoke(result, resultConv);
										continue;
									}
								}
								catch(NoSuchMethodException ex){ }
								try {
									Method method = result.getClass().getMethod(strMethodName, java.lang.Short.class);
									if(method!=null) {
										Short resultConv = resultObj;
										method.invoke(result, resultConv);
										continue;
									}
								}
								catch(NoSuchMethodException ex){ }
								try {
									Method method = result.getClass().getMethod(strMethodName, byte.class);
									if(method!=null) {
										byte resultConv = (byte)resultObj;
										method.invoke(result, resultConv);
										continue;
									}
								}
								catch(NoSuchMethodException ex){ }
								try {
									Method method = result.getClass().getMethod(strMethodName, java.lang.Byte.class);
									if(method!=null) {
										Byte resultConv = (byte)resultObj;
										method.invoke(result, resultConv);
										continue;
									}
								}
								catch(NoSuchMethodException ex){ }
								try {
									Method method = result.getClass().getMethod(strMethodName, String.class);
									if(method!=null) {
										String obj = String.valueOf(((Short)resultObj));
										method.invoke(result, obj);
										continue;
									}
								}
								catch(NoSuchMethodException ex){ }
								String strField = info.getBeanNode(nFieldIndex).trim();
								try {
									Field field = result.getClass().getField(strField);
									if(field!=null) {
										if(field.getType()==long.class) {
											long resultConv = resultObj;
											field.set(result, resultConv);
											continue;
										}
									}
									if(field!=null) {
										if(field.getType()==java.lang.Long.class) {
											Long resultConv = (long)resultObj;
											field.set(result, resultConv);
											continue;
										}
									}
									if(field!=null) {
										if(field.getType()==int.class) {
											int resultConv = resultObj;
											field.set(result, resultConv);
											continue;
										}
									}
									if(field!=null) {
										if(field.getType()==java.lang.Integer.class) {
											Integer resultConv = (int)resultObj;
											field.set(result, resultConv);
											continue;
										}
									}
									if(field!=null) {
										if(field.getType()==short.class) {
											short resultConv = resultObj;
											field.set(result, resultConv);
											continue;
										}
									}
									if(field!=null) {
										if(field.getType()==java.lang.Short.class) {
											Short resultConv = resultObj;
											field.set(result, resultConv);
											continue;
										}
									}
									if(field!=null) {
										if(field.getType()==byte.class) {
											byte resultConv = (byte)resultObj;
											field.set(result, resultConv);
											continue;
										}
									}
									if(field!=null) {
										if(field.getType()==java.lang.Byte.class) {
											Byte resultConv = (byte)resultObj;
											field.set(result, resultConv);
											continue;
										}
									}
									if(field!=null) {
										if(field.getType()==String.class) {
											String obj = String.valueOf(((Short)resultObj));
											field.set(result, obj);
											continue;
										}
									}
								}
								catch(NoSuchFieldException ex){}
								break;
							}
							case Types.TINYINT: {
								byte resultObj = (Byte)info.getAvaiable(nFieldIndex);
								String strMethodName = ReflectionUtil.makeSetBean(info.getBeanNode(nFieldIndex));
								try {
									Method method = result.getClass().getMethod(strMethodName, long.class);
									if(method!=null) {
										long resultConv = (long)resultObj;
										method.invoke(result, resultConv);
										continue;
									}
								}
								catch(NoSuchMethodException ex){ }
								try {
									Method method = result.getClass().getMethod(strMethodName, java.lang.Long.class);
									if(method!=null) {
										Long resultConv = (long)resultObj;
										method.invoke(result, resultConv);
										continue;
									}
								}
								catch(NoSuchMethodException ex){ }
								try {
									Method method = result.getClass().getMethod(strMethodName, int.class);
									if(method!=null) {
										int resultConv = (int)resultObj;
										method.invoke(result, resultConv);
										continue;
									}
								}
								catch(NoSuchMethodException ex){ }
								try {
									Method method = result.getClass().getMethod(strMethodName, java.lang.Integer.class);
									if(method!=null) {
										Integer resultConv = (int)resultObj;
										method.invoke(result, resultConv);
										continue;
									}
								}
								catch(NoSuchMethodException ex){ }
								try {
									Method method = result.getClass().getMethod(strMethodName, short.class);
									if(method!=null) {
										short resultConv = (short)resultObj;
										method.invoke(result, resultConv);
										continue;
									}
								}
								catch(NoSuchMethodException ex){ }
								try {
									Method method = result.getClass().getMethod(strMethodName, java.lang.Short.class);
									if(method!=null) {
										Short resultConv = (short)resultObj;
										method.invoke(result, resultConv);
										continue;
									}
								}
								catch(NoSuchMethodException ex){ }
								try {
									Method method = result.getClass().getMethod(strMethodName, byte.class);
									if(method!=null) {
										byte resultConv = (byte)resultObj;
										method.invoke(result, resultConv);
										continue;
									}
								}
								catch(NoSuchMethodException ex){ }
								try {
									Method method = result.getClass().getMethod(strMethodName, java.lang.Byte.class);
									if(method!=null) {
										Byte resultConv = (byte)resultObj;
										method.invoke(result, resultConv);
										continue;
									}
								}
								catch(NoSuchMethodException ex){ }
								try {
									Method method = result.getClass().getMethod(strMethodName, String.class);
									if(method!=null) {
										String obj = String.valueOf(((Byte)resultObj));
										method.invoke(result, obj);
										continue;
									}
								}
								catch(NoSuchMethodException ex){ }
								String strField = info.getBeanNode(nFieldIndex).trim();
								try {
									Field field = result.getClass().getField(strField);
									if(field!=null) {
										if(field.getType()==long.class) {
											long resultConv = (long)resultObj;
											field.set(result, resultConv);
											continue;
										}
									}
									if(field!=null) {
										if(field.getType()==java.lang.Long.class) {
											Long resultConv = (long)resultObj;
											field.set(result, resultConv);
											continue;
										}
									}
									if(field!=null) {
										if(field.getType()==int.class) {
											int resultConv = (int)resultObj;
											field.set(result, resultConv);
											continue;
										}
									}
									if(field!=null) {
										if(field.getType()==java.lang.Integer.class) {
											Integer resultConv = (int)resultObj;
											field.set(result, resultConv);
											continue;
										}
									}
									if(field!=null) {
										if(field.getType()==short.class) {
											short resultConv = (short)resultObj;
											field.set(result, resultConv);
											continue;
										}
									}
									if(field!=null) {
										if(field.getType()==java.lang.Short.class) {
											Short resultConv = (short)resultObj;
											field.set(result, resultConv);
											continue;
										}
									}
									if(field!=null) {
										if(field.getType()==byte.class) {
											byte resultConv = (byte)resultObj;
											field.set(result, resultConv);
											continue;
										}
									}
									if(field!=null) {
										if(field.getType()==java.lang.Byte.class) {
											Byte resultConv = (byte)resultObj;
											field.set(result, resultConv);
											continue;
										}
									}
									if(field!=null) {
										if(field.getType()==String.class) {
											String obj = String.valueOf(((Byte)resultObj));
											field.set(result, obj);
											continue;
										}
									}
								}
								catch(NoSuchFieldException ex){}
								break;
							}
							case Types.FLOAT: {
								String strMethodName = ReflectionUtil.makeSetBean(info.getBeanNode(nFieldIndex));
								try {
									Method method = result.getClass().getMethod(strMethodName, float.class);
									if(method!=null) {
										method.invoke(result, info.getAvaiable(nFieldIndex));
										continue;
									}
								}
								catch(NoSuchMethodException ex){ }
								try
								{
									Method method = result.getClass().getMethod(strMethodName, java.lang.Float.class);
									if(method!=null)
									{
										method.invoke(result, info.getAvaiable(nFieldIndex));
										continue;
									}
								}
								catch(NoSuchMethodException ex){ }
								try
								{
									Method method = result.getClass().getMethod(strMethodName, String.class);
									if(method!=null)
									{
										String obj = String.valueOf(((Float)info.getAvaiable(nFieldIndex)));
										method.invoke(result, obj);
										continue;
									}
								}
								catch(NoSuchMethodException ex){ }
								String strField = info.getBeanNode(nFieldIndex).trim();
								try {
									Field field = result.getClass().getField(strField);
									if(field!=null) {
										if(field.getType()==float.class) {
											field.set(result, info.getAvaiable(nFieldIndex));
											continue;
										}
									}
									if(field!=null) {
										if(field.getType()==java.lang.Float.class) {
											field.set(result, info.getAvaiable(nFieldIndex));
											continue;
										}
									}
									if(field!=null) {
										if(field.getType()==String.class) {
											String obj = String.valueOf(((Float)info.getAvaiable(nFieldIndex)));
											field.set(result, obj);
											continue;
										}
									}
								}
								catch(NoSuchFieldException ex){}
								break;
							}
							case Types.DOUBLE: {
								String strMethodName = ReflectionUtil.makeSetBean(info.getBeanNode(nFieldIndex));
								try {
									Method method = result.getClass().getMethod(strMethodName, double.class);
									if(method!=null) {
										method.invoke(result, info.getAvaiable(nFieldIndex));
										continue;
									}
								}
								catch(NoSuchMethodException ex){ }
								try {
									Method method = result.getClass().getMethod(strMethodName, java.lang.Double.class);
									if(method!=null) {
										method.invoke(result, info.getAvaiable(nFieldIndex));
										continue;
									}
								}
								catch(NoSuchMethodException ex){ }
								try
								{
									Method method = result.getClass().getMethod(strMethodName, String.class);
									if(method!=null)
									{
										String obj = String.valueOf(((Double)info.getAvaiable(nFieldIndex)));
										method.invoke(result, obj);
										continue;
									}
								}
								catch(NoSuchMethodException ex){ }
								String strField = info.getBeanNode(nFieldIndex).trim();
								try {
									Field field = result.getClass().getField(strField);
									if(field!=null) {
										if(field.getType()==double.class) {
											field.set(result, info.getAvaiable(nFieldIndex));
											continue;
										}
									}
									if(field!=null) {
										if(field.getType()==java.lang.Double.class) {
											field.set(result, info.getAvaiable(nFieldIndex));
											continue;
										}
									}
									if(field!=null) {
										if(field.getType()==String.class) {
											String obj = String.valueOf(((Double)info.getAvaiable(nFieldIndex)));
											field.set(result, obj);
											continue;
										}
									}
								}
								catch(NoSuchFieldException ex){}
								break;
							}
							case Types.BIT: {
								String strMethodName = ReflectionUtil.makeSetBean(info.getBeanNode(nFieldIndex));
								try
								{
									Method method = result.getClass().getMethod(strMethodName, boolean.class);
									if(method!=null)
									{
										method.invoke(result, info.getAvaiable(nFieldIndex));
										continue;
									}
								}
								catch(NoSuchMethodException ex){ }
								try
								{
									Method method = result.getClass().getMethod(strMethodName, java.lang.Boolean.class);
									if(method!=null)
									{
										method.invoke(result, info.getAvaiable(nFieldIndex));
										continue;
									}
								}
								catch(NoSuchMethodException ex){ }
								try
								{
									Method method = result.getClass().getMethod(strMethodName, String.class);
									if(method!=null)
									{
										String obj = String.valueOf(((Boolean)info.getAvaiable(nFieldIndex)));
										method.invoke(result, obj);
										continue;
									}
								}
								catch(NoSuchMethodException ex){ }
								String strField = info.getBeanNode(nFieldIndex).trim();
								try {
									Field field = result.getClass().getField(strField);
									if(field!=null) { 
										if(field.getType()==boolean.class) {
											field.set(result, info.getAvaiable(nFieldIndex));
											continue;
										}
									}
									if(field!=null) {
										if(field.getType()==java.lang.Boolean.class){
											field.set(result, info.getAvaiable(nFieldIndex));
											continue;
										}
									}
									if(field!=null) {
										if(field.getType()==String.class) {
											String obj = String.valueOf(((Boolean)info.getAvaiable(nFieldIndex)));
											field.set(result, obj);
											continue;
										}
									}
								}
								catch(NoSuchFieldException ex){}
								break;
							}
							case Types.VARCHAR: {
								String strMethodName = ReflectionUtil.makeSetBean(info.getBeanNode(nFieldIndex));
								try
								{
									Method method = result.getClass().getMethod(strMethodName, String.class);
									if(method!=null)
									{
										method.invoke(result, info.getAvaiable(nFieldIndex));
										continue;
									}
								}
								catch(NoSuchMethodException ex){ }
								String strField = info.getBeanNode(nFieldIndex).trim();
								try {
									Field field = result.getClass().getField(strField);
									if(field!=null) {
										if(field.getType()==String.class) {
											field.set(result, info.getAvaiable(nFieldIndex));
											continue;
										}
									}
								}
								catch(NoSuchFieldException ex){}
								break;
							}
							case Types.LONGVARCHAR: {
								String strMethodName = ReflectionUtil.makeSetBean(info.getBeanNode(nFieldIndex));
								try {
									Method method = result.getClass().getMethod(strMethodName, String.class);
									if(method!=null)
									{
										method.invoke(result, info.getAvaiable(nFieldIndex));
										continue;
									}
								}
								catch(NoSuchMethodException ex){ }
								String strField = info.getBeanNode(nFieldIndex).trim();
								try {
									Field field = result.getClass().getField(strField);
									if(field!=null) {
										if(field.getType()==String.class) {
											field.set(result, info.getAvaiable(nFieldIndex));
											continue;
										}
									}
								}
								catch(NoSuchFieldException ex){}
								break;
							}
							case Types.DATE: {
								String strMethodName = ReflectionUtil.makeSetBean(info.getBeanNode(nFieldIndex));
								try {
									Method method = result.getClass().getMethod(strMethodName, Calendar.class);
									if(method!=null) {
										Date dt = (Date)info.getAvaiable(nFieldIndex);
										Calendar obj = Calendar.getInstance();
										obj.setTimeInMillis(dt.getTime());
										method.invoke(result, obj);
										continue;
									}
								}
								catch(NoSuchMethodException ex){ }
								try
								{
									Method method = result.getClass().getMethod(strMethodName, String.class);
									if(method!=null)
									{
										String strFormat = info.getFieldInfo(nFieldIndex).getDateFormat();
										String obj = DateTime.GetDateStringFromObj(info.getAvaiable(nFieldIndex), strFormat);
										method.invoke(result, obj);
										continue;
									}
								}
								catch(NoSuchMethodException ex){ }
								try
								{
									Method method = result.getClass().getMethod(strMethodName, Date.class);
									if(method!=null)
									{
										method.invoke(result, info.getAvaiable(nFieldIndex));
										continue;
									}
								}
								catch(NoSuchMethodException ex){ }
								String strField = info.getBeanNode(nFieldIndex).trim();
								try {
									Field field = result.getClass().getField(strField);
									if(field!=null) {
										if(field.getType()==Calendar.class) {
											Date dt = (Date)info.getAvaiable(nFieldIndex);
											Calendar obj = Calendar.getInstance();
											obj.setTimeInMillis(dt.getTime());
											field.set(result, obj);
											continue;
										}
									}
									if(field!=null) {
										if(field.getType()==String.class) {
											String strFormat = info.getFieldInfo(nFieldIndex).getDateFormat();
											String obj = DateTime.GetDateStringFromObj(info.getAvaiable(nFieldIndex), strFormat);
											field.set(result, obj);
											continue;
										}
									}
									if(field!=null) {
										if(field.getType()==Date.class) {
											field.set(result, info.getAvaiable(nFieldIndex));
											continue;
										}
									}
								}
								catch(NoSuchFieldException ex){}
								break;
							}
							case Types.TIME: {
								String strMethodName = ReflectionUtil.makeSetBean(info.getBeanNode(nFieldIndex));
								try {
									Method method = result.getClass().getMethod(strMethodName, Calendar.class);
									if(method!=null) {
										Time dt = (Time)info.getAvaiable(nFieldIndex);
										Calendar obj = Calendar.getInstance();
										obj.setTimeInMillis(dt.getTime());
										method.invoke(result, obj);
										continue;
									}
								}
								catch(NoSuchMethodException ex){ }
								try
								{
									Method method = result.getClass().getMethod(strMethodName, String.class);
									if(method!=null)
									{
										String strFormat = info.getFieldInfo(nFieldIndex).getTimeFormat();
										String obj = DateTime.GetTimeStringFromObj(info.getAvaiable(nFieldIndex), strFormat);
										method.invoke(result, obj);
										continue;
									}
								}
								catch(NoSuchMethodException ex){ }
								try
								{
									Method method = result.getClass().getMethod(strMethodName, Time.class);
									if(method!=null)
									{
										method.invoke(result, info.getAvaiable(nFieldIndex));
										continue;
									}
								}
								catch(NoSuchMethodException ex){ }
								String strField = info.getBeanNode(nFieldIndex).trim();
								try {
									Field field = result.getClass().getField(strField);
									if(field!=null) {
										if(field.getType()==Calendar.class) {
											Time dt = (Time)info.getAvaiable(nFieldIndex);
											Calendar obj = Calendar.getInstance();
											obj.setTimeInMillis(dt.getTime());
											field.set(result, obj);
											continue;
										}
									}
									if(field!=null) {
										if(field.getType()==String.class) {
											String strFormat = info.getFieldInfo(nFieldIndex).getTimeFormat();
											String obj = DateTime.GetTimeStringFromObj(info.getAvaiable(nFieldIndex), strFormat);
											field.set(result, obj);
											continue;
										}
									}
									if(field!=null) {
										if(field.getType()==Date.class) {
											field.set(result, info.getAvaiable(nFieldIndex));
											continue;
										}
									}
								}
								catch(NoSuchFieldException ex){}
								break;
							}
							case Types.TIMESTAMP: {
								String strMethodName = ReflectionUtil.makeSetBean(info.getBeanNode(nFieldIndex));
								try
								{
									Method method = result.getClass().getMethod(strMethodName, Calendar.class);
									if(method!=null)
									{
										Timestamp dt = (Timestamp)info.getAvaiable(nFieldIndex);
										Calendar obj = Calendar.getInstance();
										obj.setTimeInMillis(dt.getTime());
										method.invoke(result, obj);
										continue;
									}
								}
								catch(NoSuchMethodException ex){ }
								try
								{
									Method method = result.getClass().getMethod(strMethodName, String.class);
									if(method!=null)
									{
										String strFormat = info.getFieldInfo(nFieldIndex).getDateTimeFormat();
										String obj = DateTime.GetDateTimeStringFromObj(info.getAvaiable(nFieldIndex), strFormat);
										method.invoke(result, obj);
										continue;
									}
								}
								catch(NoSuchMethodException ex){ }
								try
								{
									Method method = result.getClass().getMethod(strMethodName, Time.class);
									if(method!=null)
									{
										method.invoke(result, info.getAvaiable(nFieldIndex));
										continue;
									}
								}
								catch(NoSuchMethodException ex){ }
								String strField = info.getBeanNode(nFieldIndex).trim();
								try {
									Field field = result.getClass().getField(strField);
									if(field!=null) {
										if(field.getType()==Calendar.class) {
											Timestamp dt = (Timestamp)info.getAvaiable(nFieldIndex);
											Calendar obj = Calendar.getInstance();
											obj.setTimeInMillis(dt.getTime());
											field.set(result, obj);
											continue;
										}
									}
									if(field!=null) {
										if(field.getType()==String.class) {
											String strFormat = info.getFieldInfo(nFieldIndex).getDateTimeFormat();
											String obj = DateTime.GetDateTimeStringFromObj(info.getAvaiable(nFieldIndex), strFormat);
											field.set(result, obj);
											continue;
										}
									}
									if(field!=null) {
										if(field.getType()==Date.class) {
											field.set(result, info.getAvaiable(nFieldIndex));
											continue;
										}
									}
								}
								catch(NoSuchFieldException ex){}
								break;
							}						
						}
					}
				}
			}
		}
		catch (Throwable e)
		{
			throw e;
		}
	}
	protected Vector<Object> get_BeanObjListFromItem(Vector<IItem_Model> infoList, Class beanClass, Object mappingOption, String strMethod_getBeanObjFromItem)throws Throwable {
		Vector<Object> result = null; Object objBean = null;
		try {
			if(infoList!=null && beanClass!=null) {
				result = new Vector<Object>();
				int nSize = infoList.size();
				for(int nIndex = 0; nIndex < nSize; nIndex++) {
					IItem_Model itemInfo = infoList.get(nIndex);
					if(strMethod_getBeanObjFromItem!=null) {
						try {
							Method method = this.getClass().getMethod(strMethod_getBeanObjFromItem, IItem_Model.class, Class.class, int.class, Object.class);
							if(method!=null) {
								objBean = method.invoke(this, itemInfo, beanClass, nIndex, mappingOption);
								if(objBean!=null) {
									result.add(objBean);
									continue;
								}
								continue;
							}
						}
						catch(NoSuchMethodException ex){ }
						try {
							Method method = this.getClass().getMethod(strMethod_getBeanObjFromItem, IItem_Model.class, Class.class, Object.class);
							if(method!=null) {
								objBean = method.invoke(this, itemInfo, beanClass, mappingOption);
								if(objBean!=null) {
									result.add(objBean);
									continue;
								}
								continue;
							}
						}
						catch(NoSuchMethodException ex){ }
						try {
							Method method = this.getClass().getMethod(strMethod_getBeanObjFromItem, IItem_Model.class, int.class, Object.class);
							if(method!=null) {
								objBean = method.invoke(this, itemInfo, nIndex, mappingOption);
								if(objBean!=null) {
									result.add(objBean);
									continue;
								}
								continue;
							}
						}
						catch(NoSuchMethodException ex){ }
						try {
							Method method = this.getClass().getMethod(strMethod_getBeanObjFromItem, IItem_Model.class, Object.class);
							if(method!=null) {
								objBean = method.invoke(this, itemInfo, mappingOption);
								if(objBean!=null) {
									result.add(objBean);
									continue;
								}
								continue;
							}
						}
						catch(NoSuchMethodException ex){ }
						try {
							Method method = this.getClass().getMethod(strMethod_getBeanObjFromItem, itemInfo.getClass(), Class.class, int.class, Object.class);
							if(method!=null) {
								objBean = method.invoke(this, itemInfo, beanClass, nIndex, mappingOption);
								if(objBean!=null) {
									result.add(objBean);
									continue;
								}
								continue;
							}
						}
						catch(NoSuchMethodException ex){ }
						try {
							Method method = this.getClass().getMethod(strMethod_getBeanObjFromItem, itemInfo.getClass(), Class.class, Object.class);
							if(method!=null) {
								objBean = method.invoke(this, itemInfo, beanClass, mappingOption);
								if(objBean!=null) {
									result.add(objBean);
									continue;
								}
								continue;
							}
						}
						catch(NoSuchMethodException ex){ }
						try {
							Method method = this.getClass().getMethod(strMethod_getBeanObjFromItem, itemInfo.getClass(), int.class, Object.class);
							if(method!=null) {
								objBean = method.invoke(this, itemInfo, nIndex, mappingOption);
								if(objBean!=null) {
									result.add(objBean);
									continue;
								}
								continue;
							}
						}
						catch(NoSuchMethodException ex){ }
						try {
							Method method = this.getClass().getMethod(strMethod_getBeanObjFromItem, itemInfo.getClass(), Object.class);
							if(method!=null) {
								objBean = method.invoke(this, itemInfo, mappingOption);
								if(objBean!=null) {
									result.add(objBean);
									continue;
								}
								continue;
							}
						}
						catch(NoSuchMethodException ex){ }
					}
					objBean = get_BeanObjFromItem(itemInfo, beanClass);
					if(objBean!=null) {
						result.add(objBean);
						continue;
					}
				}
			}
		}
		catch (Throwable e)
		{
			throw e;
		}
		return result;
	}
	protected Vector<Object> get_BeanObjListFromItem(Vector<IItem_Model> infoList, Class beanClass, String strMethod_getBeanObjFromItem)throws Throwable {
		return get_BeanObjListFromItem(infoList, beanClass, null, strMethod_getBeanObjFromItem);
	}
	/**
	 * @param info
	 * @return
	 * @throws Throwable
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 09. 10 AM 9:04:13
	 * @since ModelWeb 1.0
	 */
	protected IItem_Model get_ItemFromBean(Object info)throws Throwable {
		IItem_Model result = null;
		try {
			if(info!=null && m_ItemModel!=null) {
				result = m_ItemModel.getClass().newInstance();
				int nFieldIndex;
				int nLength = m_ItemModel.getFieldLength();
				for(nFieldIndex = tbl_Model.field_id; nFieldIndex < nLength; nFieldIndex++) {
					try {
						String strField = m_ItemModel.getBeanNode(nFieldIndex);
						Field field = info.getClass().getField(strField);
						if(field!=null) {
							result.setAvaiable(nFieldIndex, field.get(info));
							continue;
						}
					}
					catch(NoSuchFieldException ex){}					
					try {
						String strMethodName = ReflectionUtil.makeGetBean(m_ItemModel.getBeanNode(nFieldIndex));
						Method method = info.getClass().getMethod(strMethodName);
						if(method!=null) {
							result.setAvaiable(nFieldIndex, method.invoke(info));
						}
					}
					catch(NoSuchMethodException ex) {
						continue;
					}
				}
			}
		}
		catch (Throwable e) { throw e; }
		return result;
	}
	/**
	 * @param infoList
	 * @return
	 * @throws Throwable
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 09. 10 AM 9:04:08
	 * @since ModelWeb 1.0
	 */
	protected Vector<IItem_Model> get_ItemListFromBean(Vector infoList, Object mappingOption, String strMethod_getItemFromBean)throws Throwable {
		Vector<IItem_Model> result = null; IItem_Model resultModel = null;
		try {
			if(infoList!=null) {
				if(infoList!=null) {					
					result = new Vector<IItem_Model>();
					int nSize = infoList.size();
					for(int nIndex = 0; nIndex < nSize; nIndex++) {
						Object objBean = infoList.get(nIndex);
						if(strMethod_getItemFromBean!=null) {
							try {
								Method method = this.getClass().getMethod(strMethod_getItemFromBean, objBean.getClass(), int.class, Object.class);
								if(method!=null) {
									resultModel = (IItem_Model)method.invoke(this, objBean, nIndex, mappingOption);
									if(resultModel!=null) {
										result.add(resultModel);
										continue;
									}
									continue;
								}
							}
							catch(NoSuchMethodException ex){ }
							try {
								Method method = this.getClass().getMethod(strMethod_getItemFromBean, Object.class, int.class, Object.class);
								if(method!=null) {
									resultModel = (IItem_Model)method.invoke(this, objBean, nIndex, mappingOption);
									if(resultModel!=null) {
										result.add(resultModel);
										continue;
									}
									continue;
								}
							}
							catch(NoSuchMethodException ex){ }
							try {
								Method method = this.getClass().getMethod(strMethod_getItemFromBean, objBean.getClass(), Object.class);
								if(method!=null) {
									resultModel = (IItem_Model)method.invoke(this, objBean, mappingOption);
									if(resultModel!=null) {
										result.add(resultModel);
										continue;
									}
									continue;
								}
							}
							catch(NoSuchMethodException ex){ }
							try {
								Method method = this.getClass().getMethod(strMethod_getItemFromBean, Object.class, Object.class);
								if(method!=null) {
									resultModel = (IItem_Model)method.invoke(this, objBean, mappingOption);
									if(resultModel!=null) {
										result.add(resultModel);
										continue;
									}
									continue;
								}
							}
							catch(NoSuchMethodException ex){ }
						}
						resultModel = get_ItemFromBean(objBean);
						if(resultModel!=null) {
							result.add(resultModel);
							continue;
						}
					}
				}
			}
		}
		catch (Throwable e)
		{
			throw e;
		}
		return result;
	}
	protected Vector<IItem_Model> get_ItemListFromBean(Vector infoList, String strMethod_getItemFromBean)throws Throwable {
		return get_ItemListFromBean(infoList, null, strMethod_getItemFromBean);
	}
	/**
	 *--------------------------------------Xml-----------------------------------------------
	 *--------------------------------------Xml-----------------------------------------------
	 *--------------------------------------Xml----------------------------------------------- 
	 */
	/**
	 * @return
	 * @throws Throwable
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 11. 21 AM 9:24:09
	 * @since ModelWeb 1.0
	 */
	protected String get_XmlFromXmlNode(Node node) throws Throwable {
		String result = null;
		if(node!=null) {
			TruegardenerXMLSerializer serial;
			if(node instanceof Document) {
				TruegardenerOutputFormat of = new TruegardenerOutputFormat((Document)node);
				of.setOmitXMLDeclaration(true);
				serial = new TruegardenerXMLSerializer(of);
			}
			else {
				TruegardenerOutputFormat of = new TruegardenerOutputFormat();
				of.setOmitXMLDeclaration(true);
				serial = new TruegardenerXMLSerializer(of);
			}
			result = serial.writeToString(node);
		}
		return result;
	}
	/**
	 * @param info
	 * @return
	 * @throws Throwable
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 11. 21 AM 9:15:27
	 * @since ModelWeb 1.0
	 */
	protected String get_XmlFromItem(IItem_Model itemInfo, key_WebDataProtocol_Model dataProtocol, String strMethod_getXmlNodeFromItem) throws Throwable {
		return get_XmlFromItem(itemInfo, null, dataProtocol, null, strMethod_getXmlNodeFromItem);
	}
	/**
	 * @param info
	 * @return
	 * @throws Throwable
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 11. 21 AM 9:15:27
	 * @since ModelWeb 1.0
	 */
	protected String get_XmlFromItem(IItem_Model itemInfo, Document document, key_WebDataProtocol_Model dataProtocol, Object mappingOption, String strMethod_getXmlNodeFromItem) throws Throwable {
		String result = null;
		try {
			Method method = this.getClass().getMethod(strMethod_getXmlNodeFromItem, IItem_Model.class, Document.class, int.class, key_WebDataProtocol_Model.class, Object.class);
			if(method!=null) {
				Node docItem = (Node)method.invoke(this, itemInfo, document, -1, dataProtocol, mappingOption);
				if(docItem!=null) {
					result = get_XmlFromXmlNode(docItem);
					return result;
				}
			}
		}
		catch(NoSuchMethodException ex){ }
		try {
			Method method = this.getClass().getMethod(strMethod_getXmlNodeFromItem, IItem_Model.class, Document.class, key_WebDataProtocol_Model.class, Object.class);
			if(method!=null) {
				Node docItem = (Node)method.invoke(this, itemInfo, document, dataProtocol, mappingOption);
				if(docItem!=null) {
					result = get_XmlFromXmlNode(docItem);
					return result;
				}
			}
		}
		catch(NoSuchMethodException ex){ }
		try {
			Method method = this.getClass().getMethod(strMethod_getXmlNodeFromItem, IItem_Model.class, int.class, key_WebDataProtocol_Model.class, Object.class);
			if(method!=null) {
				Node docItem = (Node)method.invoke(this, itemInfo, -1, dataProtocol, mappingOption);
				if(docItem!=null) {
					result = get_XmlFromXmlNode(docItem);
					return result;
				}
			}
		}
		catch(NoSuchMethodException ex){ }
		try {
			Method method = this.getClass().getMethod(strMethod_getXmlNodeFromItem, IItem_Model.class, key_WebDataProtocol_Model.class, Object.class);
			if(method!=null) {
				Node docItem = (Node)method.invoke(this, itemInfo, dataProtocol, mappingOption);
				if(docItem!=null) {
					result = get_XmlFromXmlNode(docItem);
					return result;
				}
			}
		}
		catch(NoSuchMethodException ex){ }

		try {
			Method method = this.getClass().getMethod(strMethod_getXmlNodeFromItem, itemInfo.getClass(), Document.class, int.class, key_WebDataProtocol_Model.class, Object.class);
			if(method!=null) {
				Node docItem = (Node)method.invoke(this, itemInfo, document, -1, dataProtocol, mappingOption);
				if(docItem!=null) {
					result = get_XmlFromXmlNode(docItem);
					return result;
				}
			}
		}
		catch(NoSuchMethodException ex){ }
		try {
			Method method = this.getClass().getMethod(strMethod_getXmlNodeFromItem, itemInfo.getClass(), Document.class, key_WebDataProtocol_Model.class, Object.class);
			if(method!=null) {
				Node docItem = (Node)method.invoke(this, itemInfo, document, dataProtocol, mappingOption);
				if(docItem!=null) {
					result = get_XmlFromXmlNode(docItem);
					return result;
				}
			}
		}
		catch(NoSuchMethodException ex){ }
		try {
			Method method = this.getClass().getMethod(strMethod_getXmlNodeFromItem, itemInfo.getClass(), int.class, key_WebDataProtocol_Model.class, Object.class);
			if(method!=null) {
				Node docItem = (Node)method.invoke(this, itemInfo, -1, dataProtocol, mappingOption);
				if(docItem!=null) {
					result = get_XmlFromXmlNode(docItem);
					return result;
				}
			}
		}
		catch(NoSuchMethodException ex){ }
		try {
			Method method = this.getClass().getMethod(strMethod_getXmlNodeFromItem, itemInfo.getClass(), key_WebDataProtocol_Model.class, Object.class);
			if(method!=null) {
				Node docItem = (Node)method.invoke(this, itemInfo, mappingOption, dataProtocol);
				if(docItem!=null) {
					result = get_XmlFromXmlNode(docItem);
					return result;
				}
			}
		}
		catch(NoSuchMethodException ex){ }
		Node docItem = get_XmlNodeFromItem(itemInfo, document, dataProtocol);
		if(docItem!=null) {
			result = get_XmlFromXmlNode(docItem);
		}
		return result;
	}
	protected String get_XmlFromItem(IItem_Model itemInfo, Document document, key_WebDataProtocol_Model dataProtocol, String strMethod_getXmlNodeFromItem) throws Throwable {
		return get_XmlFromItem(itemInfo, document, dataProtocol, null, strMethod_getXmlNodeFromItem);
	}
	/**
	 * @param info
	 * @return
	 * @throws Throwable
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 09. 28 PM 12:41:00
	 * @since ModelWeb 1.0
	 */
	protected Node get_XmlNodeFromItem(IItem_Model info, key_WebDataProtocol_Model dataProtocol)throws Throwable {
		Node result = null;
		try { 
			if(info!=null) {
				result = get_DocumentItemFormDocument(null, dataProtocol); 
				if(result!=null) {
					set_XmlDocFromItem(result, info);
				}
			}
		}
		catch (Throwable e) { throw e; }
		return result;
	}
	/**
	 * @param info
	 * @return
	 * @throws Throwable
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 09. 28 PM 12:41:00
	 * @since ModelWeb 1.0
	 */
	protected Node get_XmlNodeFromItem(IItem_Model info, Document document, key_WebDataProtocol_Model dataProtocol)throws Throwable {
		Node result = null;
		try {
			if(info!=null) {
				result = get_DocumentItemFormDocument(document, dataProtocol); 
				if(result!=null) {
					set_XmlDocFromItem(result, info);
				}
			}
		}
		catch (Throwable e) { throw e; }
		return result;
	}
	/**
	 * 
	 * @param document
	 * @return
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @version ModelWeb 2013. 03. 04 AM 9:21:37
	 * @since ModelWeb
	 */
	private Node get_DocumentItemFormDocument(Document document, key_WebDataProtocol_Model dataProtocol)throws Throwable {
		Node result = null;
		try {
			if(document!=null) {
				result = document.createElement(dataProtocol.getNodeKey());
			}
			else {
				DOMParser dom; StringReader readerStr; InputSource source;
				String strXml = "<" + dataProtocol.getNodeKey() + "/>";
				dom = new DOMParser();
				readerStr = new StringReader(strXml);
				source = new InputSource(readerStr);
				dom.parse(source);
				result = dom.getDocument().getDocumentElement();
			}
		}
		catch (Throwable e) { throw e; }
		return result;
	}
	/**
	 * @param info 자료기지의 Entity객체 
	 * @return
	 * @throws Throwable
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 08. 23 PM 1:28:31
	 * @since ModelWeb 1.0
	 */
	protected void set_XmlDocFromItem(Node result, IItem_Model info)throws Throwable
	{
		Document doc = null;
		Element elementNode = null; 
		try {
			if(result!=null && info!=null) {
				doc = result.getOwnerDocument();
				int nFieldIndex;
				int nLength = info.getFieldLength();
				for(nFieldIndex = tbl_Model.field_id; nFieldIndex < nLength; nFieldIndex++) {
					elementNode = doc.createElement(info.getXmlNode(nFieldIndex));
					elementNode.setTextContent(info.getAvaiableXml(nFieldIndex));
					result.appendChild(elementNode);
				}
			}
		}
		catch (Throwable e) { throw e; }
	}
	/**
	 * @param info
	 * @return
	 * @throws Throwable
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 11. 21 AM 9:15:27
	 * @since ModelWeb 1.0
	 */
	protected String get_XmlFromItemList(Vector<IItem_Model> infoList, key_WebDataProtocol_Model dataProtocol, Object mappingOption, String strMethod_getXmlNodeFromItem) throws Throwable {
		String result = null;
		Document doc = get_XmlDocFromItemList(infoList, dataProtocol, mappingOption, strMethod_getXmlNodeFromItem);
		result = get_XmlFromXmlNode(doc);
		return result;
	}
	protected String get_XmlFromItemList(Vector<IItem_Model> infoList, key_WebDataProtocol_Model dataProtocol, String strMethod_getXmlNodeFromItem) throws Throwable {
		return get_XmlFromItemList(infoList, null, dataProtocol, strMethod_getXmlNodeFromItem);
	}
	/**
	 * @return
	 * @throws Throwable
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 08. 23 PM 1:28:25
	 * @since ModelWeb 1.0
	 */
	protected Document get_XmlDocFromItemList(Vector<IItem_Model> infoList, key_WebDataProtocol_Model dataProtocol, Object mappingOption, String strMethod_getXmlNodeFromItem)throws Throwable {
		Document result = null; Node docItem = null;
		DOMParser dom;  StringReader readerStr; InputSource source;
		Element eleDoc = null, elementNode, elementItemInfoList = null;
		NodeList nodeList = null; 
		Node nodeItem = null, childNode = null; 
		try {
			if(infoList!=null) {
				String strXml = "<" + dataProtocol.getRootKey() + "/>";
				dom = new DOMParser();
				readerStr = new StringReader(strXml);
				source = new InputSource(readerStr);
				dom.parse(source);
				result = dom.getDocument();
				eleDoc = result.getDocumentElement();
				
				elementNode = result.createElement(dataProtocol.getCountKey());
				elementNode.setTextContent(String.valueOf(infoList.size()));
				eleDoc.appendChild(elementNode);
				
				int nItemIndex;
				int nSize = infoList.size();
				for(nItemIndex = 0; nItemIndex < nSize; nItemIndex++) {
					IItem_Model itemInfo = infoList.get(nItemIndex);
					if(strMethod_getXmlNodeFromItem!=null) {
						try {
							Method method = this.getClass().getMethod(strMethod_getXmlNodeFromItem, IItem_Model.class, Document.class, int.class, key_WebDataProtocol_Model.class, Object.class);
							if(method!=null) {
								docItem = (Node)method.invoke(this, itemInfo, result, nItemIndex, dataProtocol, mappingOption);
								if(docItem!=null) {
									eleDoc.appendChild(result.importNode(docItem, true));
									continue;
								}
								continue;
							}
						}
						catch(NoSuchMethodException ex){ }
						try {
							Method method = this.getClass().getMethod(strMethod_getXmlNodeFromItem, IItem_Model.class, int.class, key_WebDataProtocol_Model.class, Object.class);
							if(method!=null) {
								docItem = (Document)method.invoke(this, itemInfo, nItemIndex, dataProtocol, mappingOption);
								if(docItem!=null) {
									elementNode = result.createElement(dataProtocol.getNodeKey());
									nodeItem = ((Document)docItem).getDocumentElement();
									int nFieldIndex;									
									nodeList = nodeItem.getChildNodes();
									int nLength = nodeList.getLength();
									for(nFieldIndex = 0; nFieldIndex < nLength; nFieldIndex++) {
										elementItemInfoList = result.createElement(nodeList.item(nFieldIndex).getNodeName());
										elementItemInfoList.setTextContent(nodeList.item(nFieldIndex).getTextContent());
										elementNode.appendChild(elementItemInfoList);
									}
									eleDoc.appendChild(elementNode);
									continue;
								}
								continue;
							}
						}
						catch(NoSuchMethodException ex){ }
						try {
							Method method = this.getClass().getMethod(strMethod_getXmlNodeFromItem, IItem_Model.class, Document.class, key_WebDataProtocol_Model.class, Object.class);
							if(method!=null) {
								docItem = (Node)method.invoke(this, itemInfo, result, dataProtocol, mappingOption);
								if(docItem!=null) {
									try {
										eleDoc.appendChild(docItem);
									}
									catch(Throwable e) {
										eleDoc.appendChild(result.importNode(docItem, true));
									}
									continue;
								}
								continue;
							}
						}
						catch(NoSuchMethodException ex){ }
						try {
							Method method = this.getClass().getMethod(strMethod_getXmlNodeFromItem, itemInfo.getClass(), Document.class, int.class, key_WebDataProtocol_Model.class, Object.class);
							if(method!=null) {
								docItem = (Node)method.invoke(this, itemInfo, result, nItemIndex, dataProtocol, mappingOption);
								if(docItem!=null) {
									eleDoc.appendChild(result.importNode(docItem, true));
									continue;
								}
								continue;
							}
						}
						catch(NoSuchMethodException ex){ }
						try {
							Method method = this.getClass().getMethod(strMethod_getXmlNodeFromItem, itemInfo.getClass(), int.class, key_WebDataProtocol_Model.class, Object.class);
							if(method!=null) {
								docItem = (Document)method.invoke(this, itemInfo, nItemIndex, dataProtocol, mappingOption);
								if(docItem!=null) {
									elementNode = result.createElement(dataProtocol.getNodeKey());
									nodeItem = ((Document)docItem).getDocumentElement();
									int nFieldIndex;									
									nodeList = nodeItem.getChildNodes();
									int nLength = nodeList.getLength();
									for(nFieldIndex = 0; nFieldIndex < nLength; nFieldIndex++) {
										elementItemInfoList = result.createElement(nodeList.item(nFieldIndex).getNodeName());
										elementItemInfoList.setTextContent(nodeList.item(nFieldIndex).getTextContent());
										elementNode.appendChild(elementItemInfoList);
									}
									eleDoc.appendChild(elementNode);
									continue;
								}
								continue;
							}
						}
						catch(NoSuchMethodException ex){ }
						try {
							Method method = this.getClass().getMethod(strMethod_getXmlNodeFromItem, itemInfo.getClass(), Document.class, key_WebDataProtocol_Model.class, Object.class);
							if(method!=null) {
								docItem = (Node)method.invoke(this, itemInfo, result, dataProtocol, mappingOption);
								if(docItem!=null) {
									try {
										eleDoc.appendChild(docItem);
									}
									catch(Throwable e) {
										eleDoc.appendChild(result.importNode(docItem, true));
									}
									continue;
								}
								continue;
							}
						}
						catch(NoSuchMethodException ex){ }
					}
					childNode = get_XmlNodeFromItem(itemInfo, result, dataProtocol);
					eleDoc.appendChild(childNode);
				}
				elementNode = result.createElement(dataProtocol.getHasExceptionKey());
				elementNode.setTextContent("0");
				eleDoc.appendChild(elementNode);
			}			
		}
		catch (Throwable e) { throw e; }
		return result;
	}
	protected Document get_XmlDocFromItemList(Vector<IItem_Model> infoList, key_WebDataProtocol_Model dataProtocol, String strMethod_getXmlNodeFromItem)throws Throwable {
		return get_XmlDocFromItemList(infoList, null, dataProtocol, strMethod_getXmlNodeFromItem);
	}
	/**
	 * @param info
	 * @return
	 * @throws Throwable
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 11. 23 AM 9:24:53
	 * @since ModelWeb 1.0
	 */
	protected Node get_XmlNodeFromString(String info) throws Throwable {
		Node result = null;
		if(info!=null) {
			DOMParser dom; Document doc; StringReader readerStr; InputSource source;
			try {
				dom = new DOMParser();
				readerStr = new StringReader(info);
				source = new InputSource(readerStr);
				dom.parse(source);
				doc = dom.getDocument();
				result = doc.getDocumentElement();
			}
			catch (Throwable e) { throw e; }
		}
		return result;
	}
	/**
	 * @param info
	 * @return
	 * @throws Throwable
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 08. 23 PM 1:29:48
	 * @since ModelWeb 1.0
	 */
	protected IItem_Model get_ItemFromXml(String info, key_WebDataProtocol_Model dataProtocol, Object mappingOption, String strMethod_getItemFromXmlNode)throws Throwable {
		IItem_Model result = null;
		Node nodeItem = null;
		try {
			if(info!=null) {
				nodeItem = get_XmlNodeFromString(info);
				if(nodeItem!=null) {
					if(nodeItem instanceof Document) {
						nodeItem = ((Document)nodeItem).getDocumentElement();
					}
					if(dataProtocol.getRootKey().equals(nodeItem.getNodeName())) {
						nodeItem = nodeItem.getChildNodes().item(0);
					}
					else if(dataProtocol.getNodeKey().equals(nodeItem.getNodeName())) { }
				}
			}
			try {
				Method method = this.getClass().getMethod(strMethod_getItemFromXmlNode, Node.class, Object.class);
				if(method!=null) {
					result = (IItem_Model)method.invoke(this, nodeItem, mappingOption);
					return result;
				}
			}
			catch(NoSuchMethodException ex){ }
			result = get_ItemFromXmlNode(nodeItem, dataProtocol);
		}
		catch (Throwable e) { throw e; }
		return result;
	}
	protected IItem_Model get_ItemFromXml(String info, key_WebDataProtocol_Model dataProtocol, String strMethod_getItemFromXmlNode)throws Throwable {
		return get_ItemFromXml(info, dataProtocol, null, strMethod_getItemFromXmlNode);
	}
	/**
	 * @param nodeInfo
	 * @return
	 * @throws Throwable
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 09. 28 PM 12:40:16
	 * @since ModelWeb 1.0
	 */
	protected IItem_Model get_ItemFromXmlNode(Node nodeInfo, key_WebDataProtocol_Model dataProtocol)throws Throwable {
		IItem_Model result = null;
		NodeList nodeFieldInfoLists = null;
		Node nodeItem = null;
		int nFieldIndex = 0;
		try {
			if(nodeInfo!=null && m_ItemModel!=null) {
				if(nodeInfo instanceof Document) {
					Element eleDoc = ((Document)nodeInfo).getDocumentElement();
					if(dataProtocol.getRootKey().equals(eleDoc.getNodeName())) {
						nodeInfo = eleDoc.getElementsByTagName(dataProtocol.getNodeKey()).item(0); 
					}
				}
				else if(nodeInfo instanceof Element) {
					if(dataProtocol.getRootKey().equals(nodeInfo.getNodeName())) {
						nodeInfo = ((Element)nodeInfo).getElementsByTagName(dataProtocol.getNodeKey()).item(0);
					}
				}
				result = m_ItemModel.getClass().newInstance();
				nodeFieldInfoLists = nodeInfo.getChildNodes();
				int nLength = nodeFieldInfoLists.getLength();
				for(nFieldIndex = tbl_Model.field_id; nFieldIndex < nLength; nFieldIndex++) {
					nodeItem = nodeFieldInfoLists.item(nFieldIndex);
					String strNode = nodeItem.getNodeName();					
					String strValue = nodeItem.getTextContent();
					result.setAvaiableXml(strNode, strValue);
				}
			}
		}
		catch (Throwable e) { throw e; }
		return result;
	}
	/**
	 * @param info
	 * @param strMethod_getItemFromXml
	 * @return
	 * @throws Throwable
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 09. 28 PM 12:40:03
	 * @since ModelWeb 1.0
	 */
	protected Vector<IItem_Model> get_ItemListFromXml(String info, key_WebDataProtocol_Model dataProtocol, Object mappingOption, String strMethod_getItemFromXml)throws Throwable {
		Vector<IItem_Model> result = null; IItem_Model resultItem = null;
		NodeList nodeItems = null;
		Node eleDoc, nodeItem = null;
		try {
			if(info!=null) {
				eleDoc = get_XmlNodeFromString(info);
				if(eleDoc!=null) {
					if(dataProtocol.getRootKey().equals(eleDoc.getNodeName())) {
						result = new Vector<IItem_Model>();
						int nItemIndex;
						nodeItems = eleDoc.getChildNodes();
						int nLength = nodeItems.getLength();
						for(nItemIndex = 0; nItemIndex < nLength; nItemIndex++) {
							nodeItem = nodeItems.item(nItemIndex);
							if(dataProtocol.getNodeKey().equals(nodeItem.getNodeName())) {
								if(strMethod_getItemFromXml!=null) {
									try {
										Method method = this.getClass().getMethod(strMethod_getItemFromXml, org.w3c.dom.Node.class, int.class, key_WebDataProtocol_Model.class, Object.class);
										if(method!=null) {
											resultItem = (IItem_Model)method.invoke(this, nodeItem, nItemIndex, dataProtocol, mappingOption);
											if(resultItem!=null) {
												result.add(resultItem);
												continue;
											}
											continue;
										}
									}
									catch(NoSuchMethodException ex){ }
									try {
										Method method = this.getClass().getMethod(strMethod_getItemFromXml, org.w3c.dom.Node.class, key_WebDataProtocol_Model.class, Object.class);
										if(method!=null) {
											resultItem = (IItem_Model)method.invoke(this, nodeItem, dataProtocol, mappingOption);
											if(resultItem!=null) {
												result.add(resultItem);
												continue;
											}
											continue;
										}
									}
									catch(NoSuchMethodException ex){ }
								}
								IItem_Model resultModel = get_ItemFromXmlNode(nodeItem, dataProtocol);
								result.add(resultModel);
							}
						}
					}
				}
			}
		}
		catch (Throwable e)
		{
			throw e;
		}
		return result;
	}
	protected Vector<IItem_Model> get_ItemListFromXml(String info, key_WebDataProtocol_Model dataProtocol, String strMethod_getItemFromXml)throws Throwable {
		return get_ItemListFromXml(info, dataProtocol, null, strMethod_getItemFromXml);
	}
	/**
	 *--------------------------------------JSon-----------------------------------------------
	 *--------------------------------------JSon-----------------------------------------------
	 *--------------------------------------JSon----------------------------------------------- 
	 */
	/**
	 * @param info
	 * @return
	 * @throws Throwable
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 11. 21 AM 10:57:20
	 * @since ModelWeb 1.0
	 */
	protected String get_JSonFromHashMap(HashMap info)throws Throwable{
		String result = null;
		if(info!=null) {
			TruegardenerJSONWriter write = new TruegardenerJSONWriter();
			result = write.write(info);
		}
		return result;
	}
	/**
	 * @return
	 * @throws Throwable
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 08. 23 PM 1:28:31
	 * @since ModelWeb 1.0
	 */
	@SuppressWarnings("unchecked")
	protected String get_JSonFromItem(IItem_Model itemInfo, Object mappingOption, String strMethod_getHashMapFromItem)throws Throwable {
		String result = null;
		try {
			try {
				Method method = this.getClass().getMethod(strMethod_getHashMapFromItem, IItem_Model.class, int.class, Object.class);
				if(method!=null) {
					HashMap hashItem = (HashMap)method.invoke(this, itemInfo, -1, mappingOption);
					if(hashItem!=null) {
						result = get_JSonFromHashMap(hashItem);
						return result;
					}
				}
			}
			catch(NoSuchMethodException ex){ }
			try {
				Method method = this.getClass().getMethod(strMethod_getHashMapFromItem, IItem_Model.class, Object.class);
				if(method!=null) {
					HashMap hashItem = (HashMap)method.invoke(this, itemInfo, mappingOption);
					if(hashItem!=null) 				{
						result = get_JSonFromHashMap(hashItem);
						return result;
					}
				}
			}
			catch(NoSuchMethodException ex){ }
			try {
				Method method = this.getClass().getMethod(strMethod_getHashMapFromItem, itemInfo.getClass(), int.class, Object.class);
				if(method!=null) {
					HashMap hashItem = (HashMap)method.invoke(this, itemInfo, -1, mappingOption);
					if(hashItem!=null) {
						result = get_JSonFromHashMap(hashItem);
						return result;
					}
				}
			}
			catch(NoSuchMethodException ex){ }
			try {
				Method method = this.getClass().getMethod(strMethod_getHashMapFromItem, itemInfo.getClass(), Object.class);
				if(method!=null) {
					HashMap hashItem = (HashMap)method.invoke(this, itemInfo, mappingOption);
					if(hashItem!=null) 				{
						result = get_JSonFromHashMap(hashItem);
						return result;
					}
				}
			}
			catch(NoSuchMethodException ex){ }
			if(itemInfo!=null) {
				HashMap hashItem = get_HashMapFromItem(itemInfo);
				if(hashItem!=null) {
					result = get_JSonFromHashMap(hashItem);
				}
			}
		}
		catch (Throwable e) {
			throw e;
		}
		return result;
	}
	protected String get_JSonFromItem(IItem_Model itemInfo, String strMethod_getHashMapFromItem)throws Throwable {
		return get_JSonFromItem(itemInfo, null, strMethod_getHashMapFromItem);
	}
	/**
	 * @param info
	 * @return
	 * @throws Throwable
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 09. 28 AM 11:25:30
	 * @since ModelWeb 1.0
	 */
	@SuppressWarnings("unchecked")
	protected HashMap get_HashMapFromItem(IItem_Model info)throws Throwable {
		HashMap result = null;
		try {
			if(info!=null) {
				result = new HashMap();
				set_HashMapFromItem(result, info);
			}
		}
		catch (Throwable e) { throw e; }
		return result;
	}
	/**
	 * @param result
	 * @param info
	 * @throws Throwable
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 09. 28 PM 12:39:11
	 * @since ModelWeb 1.0
	 */
	@SuppressWarnings("unchecked")
	protected void set_HashMapFromItem(HashMap result, IItem_Model info)throws Throwable {
		try {
			if(result!=null && info!=null) {
				int nFieldIndex;
				int nLength = info.getFieldLength();
				for(nFieldIndex = tbl_Model.field_id; nFieldIndex < nLength; nFieldIndex++) {
					if(info.isAvaiable(nFieldIndex)) {
						result.put(info.getXmlNode(nFieldIndex), info.getAvaiableXml(nFieldIndex));
					}
				}
			}
		}
		catch (Throwable e) { throw e; }
	}
	/**
	 * @param infoList
	 * @param strMethod_getHashMapFromItem
	 * @return
	 * @throws Throwable
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 09. 28 PM 12:38:55
	 * @since ModelWeb 1.0
	 */
	@SuppressWarnings("unchecked")
	protected String get_JSonFromItemList(Vector<IItem_Model> infoList, key_WebDataProtocol_Model dataProtocol, Object mappingOption, String strMethod_getHashMapFromItem)throws Throwable {
		String result = null;
		try {
			if(infoList!=null) {
				HashMap hashMapInfo = get_HashMapFromItemList(infoList, dataProtocol, mappingOption, strMethod_getHashMapFromItem);
				if(result!=null) {
					TruegardenerJSONWriter write = new TruegardenerJSONWriter();
					result = write.write(hashMapInfo);
				}
			}
		}
		catch (Throwable e) { throw e; }
		return result;
	}
	protected String get_JSonFromItemList(Vector<IItem_Model> infoList, key_WebDataProtocol_Model dataProtocol, String strMethod_getHashMapFromItem)throws Throwable {
		return get_JSonFromItemList(infoList, dataProtocol, null, strMethod_getHashMapFromItem);
	}
	/**
	 * @param infoList
	 * @param strMethod_getHashMapFromItem
	 * @return
	 * @throws Throwable
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 09. 28 PM 12:38:55
	 * @since ModelWeb 1.0
	 */
	@SuppressWarnings("unchecked")
	protected HashMap get_HashMapFromItemList(Vector<IItem_Model> infoList, key_WebDataProtocol_Model dataProtocol, Object mappingOption, String strMethod_getHashMapFromItem)throws Throwable {
		HashMap result = null;
		try {
			if(infoList!=null) {
				result = new HashMap();
				HashMap jSonRoot = new HashMap();
				jSonRoot.put(dataProtocol.getCountKey(), infoList.size());
				Vector<HashMap> objNodeList = new Vector<HashMap>();
				int nSize = infoList.size();
				for(int nItemIndex = 0; nItemIndex < nSize; nItemIndex++) {
					IItem_Model itemInfo =  infoList.get(nItemIndex);
					try {
						Method method = this.getClass().getMethod(strMethod_getHashMapFromItem, IItem_Model.class, int.class, Object.class);
						HashMap jSonNode = null;
						if(method!=null) {
							jSonNode = (HashMap)method.invoke(this, itemInfo, nItemIndex, mappingOption);
							if(jSonNode!=null) {
								objNodeList.add(jSonNode);
								continue;
							}
						}
					}
					catch(NoSuchMethodException ex){ }
					try {
						Method method = this.getClass().getMethod(strMethod_getHashMapFromItem, IItem_Model.class, Object.class);
						HashMap jSonNode = null;
						if(method!=null) {
							jSonNode = (HashMap)method.invoke(this, itemInfo, mappingOption);
							if(jSonNode!=null) {
								objNodeList.add(jSonNode);
								continue;
							}
						}
					}
					catch(NoSuchMethodException ex){ }
					
					try {
						Method method = this.getClass().getMethod(strMethod_getHashMapFromItem, itemInfo.getClass(), int.class, Object.class);
						HashMap jSonNode = null;
						if(method!=null) {
							jSonNode = (HashMap)method.invoke(this, itemInfo, nItemIndex, mappingOption);
							if(jSonNode!=null) {
								objNodeList.add(jSonNode);
								continue;
							}
						}
					}
					catch(NoSuchMethodException ex){ }
					try {
						Method method = this.getClass().getMethod(strMethod_getHashMapFromItem, itemInfo.getClass(), Object.class);
						HashMap jSonNode = null;
						if(method!=null) {
							jSonNode = (HashMap)method.invoke(this, itemInfo, mappingOption);
							if(jSonNode!=null) {
								objNodeList.add(jSonNode);
								continue;
							}
						}
					}
					catch(NoSuchMethodException ex){ }
					HashMap jSonNode = get_HashMapFromItem(itemInfo);
					objNodeList.add(jSonNode);
				}
				jSonRoot.put(dataProtocol.getNodeKey(), objNodeList);
				jSonRoot.put(dataProtocol.getHasExceptionKey(), 0);
				result.put(dataProtocol.getRootKey(), jSonRoot);
			}
		}
		catch (Throwable e) { throw e; }
		return result;
	}
	protected HashMap get_HashMapFromItemList(Vector<IItem_Model> infoList, key_WebDataProtocol_Model dataProtocol, String strMethod_getHashMapFromItem)throws Throwable {
		return get_HashMapFromItemList(infoList, dataProtocol, null, strMethod_getHashMapFromItem);
	}
	/**
	 * @param info
	 * @return
	 * @throws Throwable
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 11. 23 AM 9:42:17
	 * @since ModelWeb 1.0
	 */
	protected HashMap get_HashMapFromString(String info) throws Throwable {
		HashMap result = null;
		try {
			if(info!=null) {
				TruegardenerJSONReader reader = new TruegardenerJSONReader();
				result = (HashMap)reader.read(info);
			}
		}
		catch (Throwable e) { throw e; }
		return result;
	}
	/**
	 * @param info
	 * @return
	 * @throws Throwable
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 08. 23 PM 1:29:48
	 * @since ModelWeb 1.0
	 */
	protected IItem_Model get_ItemFromJSon(String info, key_WebDataProtocol_Model dataProtocol, Object mappingOption, String strMethod_getItemFromHashMapNode)throws Throwable {
		IItem_Model result = null;
		HashMap hashItem = null;
		try {
			if(info!=null) {
				hashItem = get_HashMapFromString(info);
				if(hashItem!=null) {
					HashMap rootJSon = null;
					rootJSon = (HashMap)hashItem.get(dataProtocol.getRootKey());
					if(rootJSon!=null) {
						hashItem = (HashMap)((Vector)(rootJSon.get(dataProtocol.getNodeKey()))).get(0);
					}
					else { hashItem = (HashMap)(rootJSon.get(dataProtocol.getNodeKey())); }
				}
				try {
					Method method = this.getClass().getMethod(strMethod_getItemFromHashMapNode, HashMap.class, Object.class);
					if(method!=null) {
						result = (IItem_Model)method.invoke(this, hashItem, mappingOption);
						return result;
					}
				}
				catch(NoSuchMethodException ex){ }
				result = get_ItemFromHashMapNode(hashItem);
			}
		}
		catch (Throwable e) { throw e; }
		return result;
	}
	protected IItem_Model get_ItemFromJSon(String info, key_WebDataProtocol_Model dataProtocol, String strMethod_getItemFromHashMapNode)throws Throwable {
		return get_ItemFromJSon(info, dataProtocol, null, strMethod_getItemFromHashMapNode);
	}
	/**
	 * @param hashInfo
	 * @return
	 * @throws Throwable
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 09. 28 PM 12:18:17
	 * @since ModelWeb 1.0
	 */
	protected IItem_Model get_ItemFromHashMapNode(HashMap hashInfo)throws Throwable {
		IItem_Model result = null;
		try {
			if(hashInfo!=null && m_ItemModel!=null) {
				result = m_ItemModel.getClass().newInstance();
				int nFieldIndex;
				int nLength = m_ItemModel.getFieldLength();
				for(nFieldIndex = tbl_Model.field_id; nFieldIndex < nLength; nFieldIndex++) {
					String strNode = m_ItemModel.getXmlNode(nFieldIndex);
					String strValue = (String)hashInfo.get(strNode);
					result.setAvaiableXml(strNode, strValue);
				}
			}
		}
		catch (Throwable e) { throw e; }
		return result;
	}
	/**
	 * @param info
	 * @param strMethod_getItemFromHashMap
	 * @return
	 * @throws Throwable
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 09. 28 PM 12:38:24
	 * @since ModelWeb 1.0
	 */
	@SuppressWarnings("unchecked")
	protected Vector<IItem_Model> get_ItemListFromJSon(String info, key_WebDataProtocol_Model dataProtocol, Object mappingOption, String strMethod_getItemFromHashMap)throws Throwable {
		Vector<IItem_Model> result = null; IItem_Model resultItem = null;
		try {
			if(info!=null && m_ItemModel!=null) {
				HashMap resultJSon = get_HashMapFromString(info);
				if(resultJSon!=null) {
					HashMap rootJSon = (HashMap)resultJSon.get(dataProtocol.getRootKey());
					if(rootJSon!=null) {
						result = new Vector<IItem_Model>();
						int nItemIndex;
						Vector<HashMap> vcJSonNodeList = (Vector)(rootJSon.get(dataProtocol.getNodeKey()));
						int nLength = vcJSonNodeList.size();
						for(nItemIndex = 0; nItemIndex < nLength; nItemIndex++) {
							HashMap nodeJSon = vcJSonNodeList.get(nItemIndex);
							if(strMethod_getItemFromHashMap!=null) {
								try {
									Method method = this.getClass().getMethod(strMethod_getItemFromHashMap, java.util.HashMap.class, int.class, key_WebDataProtocol_Model.class, Object.class);
									if(method!=null) {
										resultItem = (IItem_Model)method.invoke(this, nodeJSon, nItemIndex, dataProtocol, mappingOption);
										if(resultItem!=null) {
											result.add(resultItem);
											continue;
										}
										continue;
									}
								}
								catch(NoSuchMethodException ex){ }
								try {
									Method method = this.getClass().getMethod(strMethod_getItemFromHashMap, java.util.HashMap.class, key_WebDataProtocol_Model.class, Object.class);
									if(method!=null) {
										resultItem = (IItem_Model)method.invoke(this, nodeJSon, dataProtocol, mappingOption);
										if(resultItem!=null) {
											result.add(resultItem);
											continue;
										}
										continue;
									}
								}
								catch(NoSuchMethodException ex){ }
							}
							if(nodeJSon!=null) {
								resultItem = m_ItemModel.getClass().newInstance();
								int nFieldIndex;
								int nFieldLength = m_ItemModel.getFieldLength();
								for(nFieldIndex = tbl_Model.field_id; nFieldIndex < nFieldLength; nFieldIndex++) {
									String strNode = m_ItemModel.getXmlNode(nFieldIndex);
									String strValue = (String)nodeJSon.get(strNode);
									resultItem.setAvaiableXml(strNode, strValue);
								}
								result.add(resultItem);
							}
						}
					}
				}
			}
		}
		catch (Throwable e) { throw e; }
		return result;
	}
	protected Vector<IItem_Model> get_ItemListFromJSon(String info, key_WebDataProtocol_Model dataProtocol, String strMethod_getItemFromHashMap)throws Throwable {
		return get_ItemListFromJSon(info, dataProtocol, null, strMethod_getItemFromHashMap);
	}
}
