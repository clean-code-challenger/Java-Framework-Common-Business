package common.extern.olena.model.dataaccess.mvc.base;

import common.extern.olena.model.dataaccess.util.core.query.queryProducts.fieldExpressionRelation;

public interface IItem_Model {
	/**
	 * Get Table Model
	 * 
	 * @return
	 * @author Olena.Zagreba
	 * @version ModelWeb 2013. 04. 25 AM 9:23:18
	 * @since ModelWeb
	 */
	public tbl_Model getTableModel();
	/**
	 * Set Table Type
	 * 
	 * @param kind
	 * @author Olena.Zagreba
	 * @version ModelWeb 2013. 04. 25 AM 9:23:41
	 * @since ModelWeb
	 */
	public void setKind(int kind);
	/**
	 * Get Table Type
	 * 
	 * @return
	 * @author Olena.Zagreba
	 * @version ModelWeb 2013. 04. 25 AM 9:23:48
	 * @since ModelWeb
	 */
	public int getKind();
	/**
	 * Get TableName
	 * 
	 * @return
	 * @author Olena.Zagreba
	 * @version ModelWeb 2013. 04. 25 AM 9:23:55
	 * @since ModelWeb
	 */
	public String getTableName();
	/**
	 * Set TableName
	 * 
	 * @param strTableName
	 * @author Olena.Zagreba
	 * @version ModelWeb 2013. 04. 25 AM 9:24:02
	 * @since ModelWeb
	 */
	public void setName(String strTableName);
	/**
	 * Get Table Field
	 * 
	 * @param nIndex
	 * @return
	 * @author Olena.Zagreba
	 * @version ModelWeb 2013. 04. 25 AM 9:24:08
	 * @since ModelWeb
	 */
	public String getFieldLabel(int nIndex);
	/**
	 * Get bean's Field Name
	 * 
	 * @param nIndex
	 * @return
	 * @author Olena.Zagreba
	 * @version ModelWeb 2013. 04. 25 AM 9:24:18
	 * @since ModelWeb
	 */
	public String getBeanNode(int nIndex);
	/**
	 * Get xml's Field Name
	 * 
	 * @param nIndex
	 * @return
	 * @author Olena.Zagreba
	 * @version ModelWeb 2013. 04. 25 AM 9:24:25
	 * @since ModelWeb
	 */
	public String getXmlNode(int nIndex);
	/**
	 * 
	 * @return
	 * @author Olena.Zagreba
	 * @version ModelWeb 2013. 04. 25 AM 9:24:31
	 * @since ModelWeb
	 */
	public int getFieldLength();
	/**
	 * 
	 * @param nIndex
	 * @return
	 * @author Olena.Zagreba
	 * @version ModelWeb 2013. 04. 25 AM 9:24:40
	 * @since ModelWeb
	 */
	public int getFieldType(int nIndex);
	/**
	 * 
	 * @param nIndex
	 * @return
	 * @author Olena.Zagreba
	 * @version ModelWeb 2013. 04. 25 AM 9:15:31
	 * @since ModelWeb
	 */
	public fieldExpressionRelation getFieldInfo(int nIndex);
	/**
	 * @param strXmlNode
	 * @return
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 08. 23 PM 12:01:18
	 * @since ModelWeb 1.0
	 */
	@SuppressWarnings("unused")
	public int getFieldIndexFromField(String strField);
	/**
	 * @param strXmlNode
	 * @return
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 08. 23 PM 12:01:18
	 * @since ModelWeb 1.0
	 */
	@SuppressWarnings("unused")
	public int getFieldIndexFromBean(String strBean);	
	/**
	 * @param strXmlNode
	 * @return
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 08. 23 PM 12:01:18
	 * @since ModelWeb 1.0
	 */
	public int getFieldIndexFromXmlNode(String strXmlNode);	
	/**
	 * 
	 * @param nFieldIndex
	 * @return
	 * @author Olena.Zagreba
	 * @version ModelWeb 2013. 04. 25 AM 9:11:48
	 * @since ModelWeb
	 */
	public String getAvaiableXml(int nFieldIndex);
	/**
	 * 
	 * @param strNode
	 * @param result
	 * @author Olena.Zagreba
	 * @version ModelWeb 2013. 04. 25 AM 9:11:57
	 * @since ModelWeb
	 */
	public void setAvaiableXml(String strNode, String result);
	/**
	 * 
	 * @param nFieldIndex
	 * @return
	 * @author Olena.Zagreba
	 * @version ModelWeb 2013. 04. 25 AM 9:13:49
	 * @since ModelWeb
	 */
	public boolean isAvaiable(int nFieldIndex);
	/**
	 * 
	 * @param tbl_Model
	 * @author Olena.Zagreba
	 * @version ModelWeb 2013. 04. 25 AM 9:13:29
	 * @since ModelWeb
	 */
	@SuppressWarnings("static-access")
	public void init(tbl_Model tbl_Model);
	/**
	 * 
	 * @param nFieldIndex
	 * @return
	 * @author Olena.Zagreba
	 * @version ModelWeb 2013. 04. 25 AM 9:12:42
	 * @since ModelWeb
	 */
	public Object getAvaiable(int nFieldIndex);
	/**
	 * 
	 * @param nFieldIndex
	 * @param result
	 * @author Olena.Zagreba
	 * @version ModelWeb 2013. 04. 25 AM 9:11:37
	 * @since ModelWeb
	 */
	public void setAvaiable(int nFieldIndex, Object result);
}
