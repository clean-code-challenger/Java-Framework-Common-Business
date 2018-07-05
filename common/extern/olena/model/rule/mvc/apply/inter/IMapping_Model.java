package common.extern.olena.model.rule.mvc.apply.inter;

import java.util.HashMap;
import java.util.Vector;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import common.extern.olena.model.dataaccess.mvc.base.IItem_Model;
import common.extern.olena.model.infos.dataTransport.key_WebDataProtocol_Model;
public interface IMapping_Model{
	/**
	 * @param data
	 * @param item 
	 * @author Olena.Zagreba 
	 * @version  2013-03-30 10:14:11
	 */
	public void XsetDataFromItem(Object data, IItem_Model item)throws Throwable;
	/**
	 * @author Olena.Zagreba 
	 * @version  2013-03-30 10:14:11
	 */
	public Object XgetDataFromItem(IItem_Model item, Class objClass, int nArrayIndex, Object mappingOption)throws Throwable;
	/**
	 * @author Olena.Zagreba 
	 * @version  2013-03-30 10:14:11
	 */
	public Vector<Object> XgetDataListFromItemList(Vector<IItem_Model> itemList, Class objClass, Object mappingOption)throws Throwable;
	/**
	 * @author Olena.Zagreba 
	 * @version  2013-03-30 10:14:11
	 */
	public IItem_Model XgetItemFromData(Object data, Object mappingOption)throws Throwable;
	/**
	 * @author Olena.Zagreba 
	 * @version  2013-03-30 10:14:11
	 */
	public Vector<IItem_Model> XgetItemListFromDataList(Vector<Object> dataList, Object mappingOption)throws Throwable;
	/*******************************xml************************/
	/*******************************xml************************/
	/*******************************xml************************/
	/**
	 * @author Olena.Zagreba 
	 * @version  2013-03-30 10:14:12
	 */
	public String XgetXmlFromData(Object obj, key_WebDataProtocol_Model dataProtocol, Object mappingOption)throws Throwable;
	/**
	 * @param listObj
	 * @return
	 * @throws Throwable
	 */
	public String XgetXmlFromDataList(Vector<Object> listObj, key_WebDataProtocol_Model dataProtocol, Object mappingOption)throws Throwable;
	/**
	 * @author Olena.Zagreba 
	 * @version  2013-03-30 10:14:11
	 */
	public String XgetXmlFromItem(IItem_Model item, key_WebDataProtocol_Model dataProtocol, Object mappingOption)throws Throwable;
	/**
	 * @author Olena.Zagreba 
	 * @version  2013-03-30 10:14:11
	 */
	public Node XgetXmlDocFromItem(IItem_Model item, Document doc, int nArrayIndex, key_WebDataProtocol_Model dataProtocol, Object mappingOption)throws Throwable;
	/**
	 * @author Olena.Zagreba 
	 * @version  2013-03-30 10:14:11
	 */
	public String XgetXmlFromItemList(Vector<IItem_Model> itemList, key_WebDataProtocol_Model dataProtocol, Object mappingOption)throws Throwable;
	/**
	 * @param itemList
	 * @return
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @version ModelWeb 2013. 05. 10 PM 5:54:25
	 * @since ModelWeb
	 */
	public Document XgetXmlDocFromItemList(Vector<IItem_Model> itemList, key_WebDataProtocol_Model dataProtocol, Object mappingOption)throws Throwable;
	/**
	 * @author Olena.Zagreba 
	 * @version  2013-03-30 10:14:11
	 */
	public IItem_Model XgetItemFromXml(String strXml, key_WebDataProtocol_Model dataProtocol, Object mappingOption)throws Throwable;
	/**
	 * @author Olena.Zagreba 
	 * @version  2013-03-30 10:14:12
	 */
	public IItem_Model XgetItemFromXmlNode(Node nodeXml, key_WebDataProtocol_Model dataProtocol, Object mappingOption)throws Throwable;
	/**
	 * @author Olena.Zagreba 
	 * @version  2013-03-30 10:14:12
	 */
	public Vector<IItem_Model> XgetItemListFromXml(String strXml, key_WebDataProtocol_Model dataProtocol, Object mappingOption)throws Throwable;
	/**
	 * @param strXml
	 * @return
	 * @throws Throwable
	 */
	public Object XgetDataFromXml(String strXml, Class beanClass, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable;
	/**
	 * @param strXml
	 * @return
	 * @throws Throwable
	 */
	public Vector<Object> XgetDataListFromXml(String strXml, Class beanClass, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable;
	/**
	 * @param nodeXml
	 * @param beanClass
	 * @return
	 * @throws Throwable
	 */
	public Object XgetDataFromXmlNode(Node nodeXml, Class beanClass, key_WebDataProtocol_Model dataProtocol, Object mappingOption)throws Throwable;
	/*******************************json************************/
	/*******************************json************************/
	/*******************************json************************/
	/**
	 * @author Olena.Zagreba 
	 * @version  2013-03-30 10:14:12
	 */
	public String XgetJSonFromData(Object obj, Object mappingOption)throws Throwable;
	/**
	 * @return
	 * @throws Throwable
	 */
	public String XgetJSonFromDataList(Vector<Object> listObj, key_WebDataProtocol_Model dataProtocol, Object mappingOption)throws Throwable;
	/**
	 * @author Olena.Zagreba 
	 * @version  2013-03-30 10:14:12
	 */
	public String XgetJSonFromItem(IItem_Model item, Object mappingOption)throws Throwable;
	public HashMap XgetHashMapFromItem(IItem_Model item, int nArrayIndex, Object mappingOption) throws Throwable;
	public String XgetJSonFromItemList(Vector<IItem_Model> itemList, key_WebDataProtocol_Model dataProtocol, Object mappingOption)throws Throwable;
	public HashMap XgetHashMapFromItemList(Vector<IItem_Model> itemList, key_WebDataProtocol_Model dataProtocol, Object mappingOption)throws Throwable;
	public IItem_Model XgetItemFromJSon(String strJSon, Object mappingOption)throws Throwable;
	public Object XgetDataFromHashMap(HashMap hashitem, Class beanClass, Object mappingOption)throws Throwable;
	public IItem_Model XgetItemFromHashMap(HashMap hashitem, Object mappingOption)throws Throwable;
	public Vector<IItem_Model> XgetItemListFromJSon(String strJSon, key_WebDataProtocol_Model dataProtocol, Object mappingOption)throws Throwable;
	public Object XgetDataFromJSon(String strJSon, Class beanClass, Object mappingOption) throws Throwable;
	public Vector<Object> XgetDataListFromJSon(String strJSon, Class beanClass, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable;
	public Node XgetXmlDocFromData(Object obj, Document doc, int nArrayIndex, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable;
	public HashMap XgetHashMapFromData(Object obj, int nArrayIndex, Object mappingOption) throws Throwable;
}
