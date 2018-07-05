package common.extern.olena.model.dataaccess.mvc.base;

import java.sql.Types;
import java.util.Vector;

import common.extern.olena.model.dataaccess.util.core.query.queryProducts.fieldExpressionRelation;

public abstract class tbl_Model
{
	/**Primary Key Field Index*/
	public final static int field_id = 0;
	
	/***Table Name*/
	protected String m_strTable_name;
	/***Field Info's (Field Name, Field Type)*/
	protected Vector<fieldColumn_Model> m_arrFields = new Vector<fieldColumn_Model>();
	/**
	 * return : Field Type
	 * 
	 * @return
	 * @author Olena.Zagreba
	 * @version ModelWeb 2013. 04. 25 PM 5:38:35
	 * @since ModelWeb
	 */
	public Vector<fieldColumn_Model> getArrayFields() {
		return m_arrFields; 
	}
	/**
	 * Get Table Name
	 * @param kind
	 * @return
	 * 
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 25 PM 4:52:12
	 * @since ModelWeb 1.0
	 */
	public String getName(int kind) { return m_strTable_name; }
	/**
	 * Set Table Name
	 * @param strTableName
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 12. 03 PM 5:59:57
	 * @since ModelWeb 1.0
	 */
	public void setName(String strTableName) { m_strTable_name = strTableName; }
	/**
	 * Get Field Name
	 * @param nIndex
	 * @param kind
	 * @return
	 * 
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 25 PM 4:52:50
	 * @since ModelWeb 1.0
	 */
	public String getLabel(int nIndex, int kind) {
		if(nIndex >= tbl_Model.field_id && nIndex < m_arrFields.size()) { return m_arrFields.get(nIndex).getFieldLabel(); }
		else { return null; }
	}
	/**
	 * Get bean field name
	 * @param nIndex
	 * @param kind
	 * @return
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 08. 23 PM 1:36:46
	 * @since ModelWeb 1.0
	 */
	public String getBean(int nIndex, int kind) {
		if(nIndex >= tbl_Model.field_id && nIndex < m_arrFields.size()) { return m_arrFields.get(nIndex).getBeanField(); }
		else { return null; }
	}
	/**
	 * get xml's field name 
	 * @param nIndex
	 * @param kind
	 * @return
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 09. 12 PM 1:20:13
	 * @since ModelWeb 1.0
	 */
	public String getXml(int nIndex, int kind) {
		if(nIndex >= tbl_Model.field_id && nIndex < m_arrFields.size()) { return m_arrFields.get(nIndex).getXmlField(); }
		else { return null; }
	}
	/**
	 *  Get Field Type
	 * @param nIndex
	 * @return
	 * 
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 25 PM 4:53:07
	 * @since ModelWeb 1.0
	 */
	public int getType(int nIndex, int kind) { 
		if(nIndex >= tbl_Model.field_id && nIndex < m_arrFields.size()) { return m_arrFields.get(nIndex).getFieldType(); }
		else {return Types.NULL; }
	}
	/**
	 * Get Count of Fields
	 * @return 
	 * 
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 25 PM 4:53:21
	 * @since ModelWeb 1.0
	 */
	public int getLength() { return m_arrFields.size(); }
	/**
	 * Get expression related with field type
	 * @param nIndex
	 * @param kind
	 * @return
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 09. 03 PM 1:43:22
	 * @since ModelWeb 1.0
	 */
	public fieldExpressionRelation getFieldExpressionRelation(int nIndex, int kind) {
		if(nIndex >= tbl_Model.field_id && nIndex < m_arrFields.size()) { return m_arrFields.get(nIndex).getFieldInfo(); }
		else {return null; }
	}
	/**
	 * @param nIndex
	 * @param fieldInfo
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 09. 10 PM 1:37:25
	 * @since ModelWeb 1.0
	 */
	protected void setFieldExpressionRelation(int nIndex, fieldExpressionRelation fieldInfo) {
		if(nIndex >= tbl_Model.field_id && nIndex < m_arrFields.size()) { m_arrFields.get(nIndex).setFieldInfo(fieldInfo); }
		else {return; }
	}
	/**
	 * @param fieldInfo
	 * @return
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 12. 03 PM 6:20:43
	 * @since ModelWeb 1.0
	 */
	protected int addFieldColumn(fieldColumn_Model fieldInfo) {
		int result = -1;
		result = m_arrFields.size();
		m_arrFields.add(fieldInfo);
		return result;
	}
	/**
	 * Add Field
	 * @param strFieldLabel 
	 * @param nFieldType
	 * @return
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 08. 23 PM 1:34:07
	 * @since ModelWeb 1.0
	 */
	protected int addFieldColumn(String strFieldLabel, int nFieldType) {
		int result = -1;
		result = m_arrFields.size();
		fieldColumn_Model itemField = new fieldColumn_Model();
		itemField.setFieldLabel(strFieldLabel);
		itemField.setFieldType(nFieldType);
		itemField.setBeanField(strFieldLabel);
		itemField.setXmlField(strFieldLabel);
		
		fieldExpressionRelation fieldInfo = new fieldExpressionRelation();
		fieldInfo.init(m_arrFields.size(), nFieldType, true);
		itemField.setFieldInfo(fieldInfo);
		
		m_arrFields.add(itemField);
		return result;
	}
	
	/**
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 09. 03 AM 11:17:46
	 * @since ModelWeb 1.0
	 */
	protected int addFieldColumn(String strFieldLabel, int nFieldType, fieldExpressionRelation fieldInfo) {
		int result = -1;
		result = m_arrFields.size();
		fieldColumn_Model itemField = new fieldColumn_Model();
		itemField.setFieldLabel(strFieldLabel);
		itemField.setFieldType(nFieldType);
		itemField.setBeanField(strFieldLabel);
		itemField.setXmlField(strFieldLabel);
		
		if(fieldInfo==null) {
			fieldInfo = new fieldExpressionRelation();
			fieldInfo.init(m_arrFields.size(), nFieldType, true);
		}
		else {
			fieldInfo.init(m_arrFields.size(), nFieldType, false);
		}
		itemField.setFieldInfo(fieldInfo);
		m_arrFields.add(itemField);
		
		return result;
	}
	
	/**
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 08. 23 PM 1:34:07
	 * @since ModelWeb 1.0
	 */
	protected int addFieldColumn(String strFieldLabel, int nFieldType, String strBeanNode, String strXmlNode) {
		int result = -1;
		result = m_arrFields.size();
		fieldColumn_Model itemField = new fieldColumn_Model();
		itemField.setFieldLabel(strFieldLabel);
		itemField.setFieldType(nFieldType);
		if(strBeanNode!=null) { itemField.setBeanField(strBeanNode); }
		else { itemField.setBeanField(strFieldLabel); }
		
		if(strXmlNode!=null) { itemField.setXmlField(strXmlNode); } 
		else { itemField.setXmlField(strFieldLabel); }
			
		fieldExpressionRelation fieldInfo = new fieldExpressionRelation();
		fieldInfo.init(m_arrFields.size(), nFieldType, true);
		itemField.setFieldInfo(fieldInfo);
		
		m_arrFields.add(itemField);
		return result;
	}
	
	/**
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 09. 03 AM 11:18:27
	 * @since ModelWeb 1.0
	 */
	protected int addFieldColumn(String strFieldLabel, int nFieldType, String strBeanNode, String strXmlNode, fieldExpressionRelation fieldInfo) {
		int result = -1;
		result = m_arrFields.size();
		fieldColumn_Model itemField = new fieldColumn_Model();
		itemField.setFieldLabel(strFieldLabel);
		itemField.setFieldType(nFieldType);
		
		if(strBeanNode!=null) { itemField.setBeanField(strBeanNode); }
		else { itemField.setBeanField(strFieldLabel); }
		if(strXmlNode!=null) { itemField.setXmlField(strXmlNode); }
		else { itemField.setXmlField(strFieldLabel); }
		
		if(fieldInfo==null) {
			fieldInfo = new fieldExpressionRelation();
			fieldInfo.init(m_arrFields.size(), nFieldType, true);
		}
		else {
			fieldInfo.init(m_arrFields.size(), nFieldType, false);
		}
		itemField.setFieldInfo(fieldInfo);
		m_arrFields.add(itemField);
		return result;
	}
	
	public int addViewFieldColumn(tbl_Model tblInfo, int nFieldIndex) {
		return addFieldColumn(tblInfo.getLabel(nFieldIndex, 0), tblInfo.getType(nFieldIndex, 0), 
							  tblInfo.getBean(nFieldIndex, 0), tblInfo.getXml(nFieldIndex, 0));
	}
}
