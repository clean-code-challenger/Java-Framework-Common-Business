package common.extern.olena.model.facade.mvc.apply;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Vector;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import common.extern.olena.model.dataaccess.mvc.apply.BaseDA;
import common.extern.olena.model.dataaccess.mvc.apply.TruegardenerDA;
import common.extern.olena.model.dataaccess.mvc.base.IItem_Model;
import common.extern.olena.model.dataaccess.util.core.connections.dbConn_Model;
import common.extern.olena.model.dataaccess.util.core.query.queryProducts.executeExpressionRelation;
import common.extern.olena.model.dataaccess.util.core.query.queryProducts.orderExpressionRelation;
import common.extern.olena.model.dataaccess.util.core.query.queryProducts.pageExpressionRelation;
import common.extern.olena.model.dataaccess.util.core.query.queryProducts.setNullExpressionRelation;
import common.extern.olena.model.dataaccess.util.core.query.queryProducts.updateExpressionRelation;
import common.extern.olena.model.dataaccess.util.core.query.queryProducts.whereExpressionRelation;
import common.extern.olena.model.error.TruegardenerException;
import common.extern.olena.model.facade.facadeFatherModel;
import common.extern.olena.model.infos.dataTransport.key_WebDataProtocol_Model;
import common.extern.olena.model.infos.universalResult.infoUniversalReturnCode;
import common.extern.olena.model.model.page.info.pageModel;
import common.extern.olena.model.model.result.univeralResult;
import common.extern.olena.model.rule.mvc.apply.TruegardenerRule;
import common.extern.olena.model.rule.mvc.apply.inter.ITruegardenerRule;
public class TruegardenerBusiness extends BaseBusiness implements ITruegardenerRule
{
	public TruegardenerBusiness(facadeFatherModel parentFacade, dbConn_Model dbConnModel, IItem_Model itemModel, pageModel pageModel) {
		super(parentFacade, null, null, itemModel);
		m_daClient = new TruegardenerDA(dbConnModel, itemModel);
		m_ruleClient = new TruegardenerRule(this, m_daClient, itemModel, pageModel);
	}

	public TruegardenerBusiness(facadeFatherModel parentFacade, dbConn_Model dbConnModel, IItem_Model itemModel, pageModel pageModel, 
			String strXmlRoot, String strXmlNode, String strNodeCount, String strXmlException) throws Throwable {
		super(parentFacade, null, null, itemModel, strXmlRoot, strXmlNode, strNodeCount, strXmlException);
		m_daClient = new TruegardenerDA(dbConnModel, itemModel);
		m_ruleClient = new TruegardenerRule(this, m_daClient, itemModel, pageModel);
	}

	private TruegardenerRule m_ruleClient = null; 
	private TruegardenerDA m_daClient = null;
	
	public univeralResult InsertData(Object data, Object mappingOption) {
		univeralResult result = new univeralResult();
		try{
			if(data == null) {
				result.setErrorMsg("Null Error. - BusinessFacadeLayer  insert()...");
				result.setResult(infoUniversalReturnCode.RESULT_ERROR_NULL);
			}
			else {
				long resultInsert = insertData(data, mappingOption);
				if(resultInsert > 0) {
					result.setResultObj(resultInsert);
				}
				else {
					result.setErrorMsg("Insert Failed.");
					result.setResult(resultInsert);
				}
			}
		}
		catch(Throwable ex){ result.setErrorMsg(ex); }		
		return result;
	}
	public univeralResult InsertData(Object data) {
		return InsertData(data, null);
	}
	/**
	 * @param strXml
	 * @param mappingOption 
	 * @author Olena.Zagreba
	 * @version ModelWeb 2013. 05. 11 PM 1:11:45
	 * @since ModelWeb
	 */
	public univeralResult InsertXml(String strXml, Object mappingOption) {
		univeralResult result = new univeralResult();
		try{
			if(strXml == null) {
				result.setErrorMsg("Null Error. - BusinessFacadeLayer  insert()...");
				result.setResult(infoUniversalReturnCode.RESULT_ERROR_NULL);
			}
			else {
				long resultInsert = insertXml(strXml, mappingOption);
				if(resultInsert > 0) {
					result.setResultObj(resultInsert);
				}
				else {
					result.setErrorMsg("Xml insertion failed.");
					result.setResult(resultInsert);
				}
			}
		}
		catch(Throwable ex){ result.setErrorMsg(ex); }		
		return result;
	}
	public univeralResult InsertXml(String strXml) {
		return InsertXml(strXml, null);
	}
	/**
	 * @param nodeXml
	 * @param mappingOption
	 * @author Olena.Zagreba
	 * @version ModelWeb 2013. 05. 11 PM 1:12:03
	 * @since ModelWeb
	 */
	public univeralResult InsertXml(Node nodeXml, Object mappingOption) {
		univeralResult result = new univeralResult();
		try{
			if(nodeXml == null) {
				result.setErrorMsg("Error");
				result.setResult(infoUniversalReturnCode.RESULT_ERROR_NULL);
			}
			else {
				long resultInsert = insertXml(nodeXml, mappingOption);
				if(resultInsert > 0) {
					result.setResultObj(resultInsert);
				}
				else {
					result.setErrorMsg("Failed Xml Insert.");
					result.setResult(resultInsert);
				}
			}
		}
		catch(Throwable ex){ result.setErrorMsg(ex); }		
		return result;
	}
	public univeralResult InsertXml(Node nodeXml) {
		return InsertXml(nodeXml, null);
	}
	/**
	 * @param strJSon
	 * @param mappingOption 
	 * @author Olena.Zagreba
	 * @version ModelWeb 2013. 05. 11 PM 1:18:39
	 * @since ModelWeb
	 */
	public univeralResult InsertJSon(String strJSon, Object mappingOption) {
		univeralResult result = new univeralResult();
		try{
			if(strJSon == null) {
				result.setErrorMsg("Null Error - BusinessFacadeLayer insert()...");
				result.setResult(infoUniversalReturnCode.RESULT_ERROR_NULL);
			}
			else {
				long resultInsert = insertJSon(strJSon, mappingOption);
				if(resultInsert > 0) {
					result.setResultObj(resultInsert);
				}
				else {
					result.setErrorMsg("Failed JSon Insert.");
					result.setResult(resultInsert);
				}
			}
		}
		catch(Throwable ex){ result.setErrorMsg(ex); }		
		return result;
	}
	public univeralResult InsertJSon(String strJSon) {
		return InsertJSon(strJSon, null);
	}
	/**
	 * @param hashInfo
	 * @param mappingOption 
	 * @author Olena.Zagreba
	 * @version ModelWeb 2013. 05. 11 PM 1:18:13
	 * @since ModelWeb
	 */
	public univeralResult InsertJSon(HashMap hashInfo, Object mappingOption) {
		univeralResult result = new univeralResult();
		try{
			if(hashInfo == null) {
				result.setErrorMsg("Error");
				result.setResult(infoUniversalReturnCode.RESULT_ERROR_NULL);
			}
			else {
				long resultInsert = insertHashMap(hashInfo, mappingOption);
				if(resultInsert > 0) {
					result.setResultObj(result);
				}
				else {
					result.setErrorMsg("Failed Hashmap insert.");
					result.setResult(resultInsert);
				}
			}
		}
		catch(Throwable ex){ result.setErrorMsg(ex); }		
		return result;
	}
	public univeralResult InsertJSon(HashMap hashInfo) {
		return InsertJSon(hashInfo, null);
	}
	/**
	 * @param data
	 * @author Olena.Zagreba
	 * @version ModelWeb 2013. 05. 11 PM 1:12:20
	 * @since ModelWeb
	 */
	public univeralResult InsertItem(IItem_Model data) {
		univeralResult result = new univeralResult();
		try{
			if(data == null) {
				result.setErrorMsg("Error. - BusinessFacadeLayer insert()...");
				result.setResult(infoUniversalReturnCode.RESULT_ERROR_NULL);
			}
			else {
				long resultInsert = insertItem(data);
				if(resultInsert > 0) {
					result.setResultObj(resultInsert);
				}
				else {
					result.setErrorMsg("Failed Database item insert.");
					result.setResult(resultInsert);
				}
			}
		}
		catch(Throwable ex){ result.setErrorMsg(ex); }		
		return result;
	}

	public univeralResult UpdateData(Object data, Object mappingOption){
		univeralResult result = new univeralResult();
		try{
			if(data == null) {
				result.setErrorMsg("Error");
			}
			else {
				boolean resultUpdate = updateData(data, mappingOption);
				if(resultUpdate){
					result.setResultObj(resultUpdate);
				}
				else{
					result.setErrorMsg("Failed Database Update.");
				}
			}
		}
		catch(Throwable ex){ result.setErrorMsg(ex); }	
		return result;
	}
	public univeralResult UpdateData(Object data){
		return UpdateData(data, null);
	}

	public univeralResult UpdateXml(String strXml, Object mappingOption){
		univeralResult result = new univeralResult();
		try{
			if(strXml == null) {
				result.setErrorMsg("Error");
			}
			else {
				boolean resultUpdate = updateXml(strXml, mappingOption);
				if(resultUpdate){
					result.setResultObj(resultUpdate);
				}
				else{
					result.setErrorMsg("Failed Xml Update.");
				}
			}
		}
		catch(Throwable ex){ result.setErrorMsg(ex); }	
		return result;
	}
	public univeralResult UpdateXml(String strXml){
		return UpdateXml(strXml, null);
	}

	public univeralResult UpdateXml(Node nodeXml, Object mappingOption){
		univeralResult result = new univeralResult();
		try{
			if(nodeXml == null) {
				result.setErrorMsg("Error");
			}
			else {
				boolean resultUpdate = updateXml(nodeXml, mappingOption);
				if(resultUpdate){
					result.setResultObj(resultUpdate);
				}
				else{
					result.setErrorMsg("Xml Update Failed.");
				}
			}
		}
		catch(Throwable ex){ result.setErrorMsg(ex); }	
		return result;
	}
	public univeralResult UpdateXml(Node nodeXml){
		return UpdateXml(nodeXml, null);
	}

	public univeralResult UpdateJSon(String strJSon, Object mappingOption){
		univeralResult result = new univeralResult();
		try{
			if(strJSon == null) {
				result.setErrorMsg("Error");
			}
			else {
				boolean resultUpdate = updateData(strJSon, mappingOption);
				if(resultUpdate){
					result.setResultObj(resultUpdate);
				}
				else{
					result.setErrorMsg("JSon Update Failed.");
				}
			}
		}
		catch(Throwable ex){ result.setErrorMsg(ex); }	
		return result;
	}
	public univeralResult UpdateJSon(String strJSon){
		return UpdateJSon(strJSon);
	}

	public univeralResult UpdateJSon(HashMap hashJSon, Object mappingOption){
		univeralResult result = new univeralResult();
		try{
			if(hashJSon == null) {
				result.setErrorMsg("Error");
			}
			else {
				boolean resultUpdate = updateData(hashJSon, mappingOption);
				if(resultUpdate){
					result.setResultObj(resultUpdate);
				}
				else{
					result.setErrorMsg("Hashmap update failed");
				}
			}
		}
		catch(Throwable ex){ result.setErrorMsg(ex); }	
		return result;
	}
	public univeralResult UpdateJSon(HashMap hashJSon){
		return UpdateJSon(hashJSon, null);
	}
	public univeralResult UpdateItem(IItem_Model item){
		univeralResult result = new univeralResult();
		try{
			if(item == null) {
				result.setErrorMsg("Null Error - BusinessFacadeLayer update()...");
			}
			else {
				boolean resultUpdate = updateItem(item);
				if(resultUpdate){
					result.setResultObj(resultUpdate);
				}
				else{
					result.setErrorMsg("Database update failed.");
				}
			}
		}
		catch(Throwable ex){ result.setErrorMsg(ex); }	
		return result;
	}
	public univeralResult DeleteData(long nNo) {
		univeralResult result = new univeralResult();
		try{
			boolean resultDelete = deleteData(nNo);
			if(resultDelete){
				result.setResultObj(resultDelete);
			}
			else{
				result.setErrorMsg("Database remove failed.");
			}
		}
		catch(Throwable ex){ result.setErrorMsg(ex); }	
		return result;
	}
	@SuppressWarnings("unchecked")
	public univeralResult GetDataListByPage(orderExpressionRelation orderList, Object pageInfo, Class beanClass, Object mappingOption) {
		univeralResult result = new univeralResult();
		try{
			pageExpressionRelation pgInfo = getPageInfo(pageInfo);
			if(pgInfo==null) {
				pgInfo = new pageExpressionRelation();
			}
			Vector<Object> resultList = (Vector)getDataListByPage(orderList, pgInfo, beanClass, mappingOption);
			if(resultList!=null){
				result.setPage(getPageInfo(pgInfo));
				result.setResultObj(resultList);
			}
			else{
				result.setErrorMsg("Can't get data list.");
			}
		}
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}	
	public univeralResult GetDataListByPage(orderExpressionRelation orderList, Object pageInfo, Class beanClass) {
		return GetDataListByPage(orderList, pageInfo, beanClass, null);
	}	
	public univeralResult GetXmlListByPage(orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) {
		univeralResult result = new univeralResult();
		try{
			pageExpressionRelation pgInfo = getPageInfo(pageInfo);
			if(pgInfo==null) {
				pgInfo = new pageExpressionRelation();
			}
			String resultString = getXmlListByPage(orderList, pgInfo, dataProtocol, mappingOption);
			if(resultString != null) {
				result.setPage(getPageInfo(pgInfo));
				result.setResultObj(resultString);
			}
			else{
				result.setErrorMsg("Can't get xml string.");
			}
		}
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	public univeralResult GetXmlListByPage(orderExpressionRelation orderList, Object pageInfo, Object mappingOption) {
		return GetXmlListByPage(orderList, pageInfo, getDefaultDataProtocol(), mappingOption);
	}
	public univeralResult GetXmlListByPage(orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol) {
		return GetXmlListByPage(orderList, pageInfo, dataProtocol, null);
	}
	public univeralResult GetXmlListByPage(orderExpressionRelation orderList, Object pageInfo) {
		return GetXmlListByPage(orderList, pageInfo, getDefaultDataProtocol(), null);
	}
	/**
	 * @param orderList 
	 * @param pageInfo 
	 * @param dataProtocol
	 * @param mappingOption
	 * @return
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @version ModelWeb 2013. 04. 27 AM 8:46:59
	 * @since ModelWeb
	 */
	public univeralResult GetXmlDocByPage(orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) {
		univeralResult result = new univeralResult();
		try{
			pageExpressionRelation pgInfo = getPageInfo(pageInfo);
			if(pgInfo==null) {
				pgInfo = new pageExpressionRelation();
			}
			Node resultNode = getXmlDocByPage(orderList, pgInfo, dataProtocol, mappingOption);
			if(resultNode != null) {
				result.setPage(getPageInfo(pgInfo));
				result.setResultObj(resultNode);
			}
			else{
				result.setErrorMsg("Can't get xml string.");
			}
		}
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	public univeralResult GetXmlDocByPage(orderExpressionRelation orderList, Object pageInfo, Object mappingOption) {
		return GetXmlDocByPage(orderList, pageInfo, getDefaultDataProtocol(), mappingOption);
	}
	public univeralResult GetXmlDocByPage(orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol) {
		return GetXmlDocByPage(orderList, pageInfo, dataProtocol, null);
	}
	public univeralResult GetXmlDocByPage(orderExpressionRelation orderList, Object pageInfo) {
		return GetXmlDocByPage(orderList, pageInfo, getDefaultDataProtocol(), null);
	}
	/**
	 * @param orderList 
	 * @param pageInfo
	 * @param dataProtocol 
	 * @param mappingOption
	 * @return
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @version ModelWeb 2013. 04. 27 AM 8:47:24
	 * @since ModelWeb
	 */
	public univeralResult GetJSonListByPage(orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) {
		univeralResult result = new univeralResult();
		try{
			pageExpressionRelation pgInfo = getPageInfo(pageInfo);
			if(pgInfo==null) {
				pgInfo = new pageExpressionRelation();
			}
			String resultString = getJSonListByPage(orderList, pgInfo, dataProtocol, mappingOption);
			if(resultString != null) {
				result.setPage(getPageInfo(pgInfo));
				result.setResultObj(resultString);
			}
			else{
				result.setErrorMsg("Can't get JSon String.");
			}
		}
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	public univeralResult GetJSonListByPage(orderExpressionRelation orderList, Object pageInfo, Object mappingOption) {
		return GetJSonListByPage(orderList, pageInfo, getDefaultDataProtocol(), mappingOption);
	}
	public univeralResult GetJSonListByPage(orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol) {
		return GetJSonListByPage(orderList, pageInfo, dataProtocol, null);
	}
	public univeralResult GetJSonListByPage(orderExpressionRelation orderList, Object pageInfo) {
		return GetJSonListByPage(orderList, pageInfo, getDefaultDataProtocol(), null);
	}
	/**
	 * @param orderList 
	 * @param pageInfo
	 * @param dataProtocol
	 * @param mappingOption 
	 * @return
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @version ModelWeb 2013. 04. 27 AM 8:47:24
	 * @since ModelWeb
	 */
	public univeralResult GetHashMapByPage(orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) {
		univeralResult result = new univeralResult();
		try{
			pageExpressionRelation pgInfo = getPageInfo(pageInfo);
			if(pgInfo==null) {
				pgInfo = new pageExpressionRelation();
			}
			HashMap resultHash = getHashMapByPage(orderList, pgInfo, dataProtocol, mappingOption);
			if(resultHash != null) {
				result.setPage(getPageInfo(pgInfo));
				result.setResultObj(resultHash);
			}
			else{
				result.setErrorMsg("Can't get HashMap info.");
			}
		}
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	public univeralResult GetHashMapByPage(orderExpressionRelation orderList, Object pageInfo, Object mappingOption) {
		return GetHashMapByPage(orderList, pageInfo, getDefaultDataProtocol(), mappingOption);
	}
	public univeralResult GetHashMapByPage(orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol) {
		return GetHashMapByPage(orderList, pageInfo, dataProtocol, null);
	}
	public univeralResult GetHashMapByPage(orderExpressionRelation orderList, Object pageInfo) {
		return GetHashMapByPage(orderList, pageInfo, getDefaultDataProtocol(), null);
	}
	/*******************************Bean************************/
	/**
	 * @param nNo 
	 * @param mappingOption 
	 * @throws Throwable
	 * @author Olena.Zagreba 
	 * @version truegardener, 2013-03-31 10:14:10
	 */
	public univeralResult GetData(long nNo, Class beanClass, Object mappingOption) {
		univeralResult result = new univeralResult();
		try{
			Object resultObj = getData(nNo, beanClass, mappingOption);
			if(resultObj!=null){
				result.setResultObj(resultObj);				
			}
			else{
				result.setErrorMsg("Can't get data");
			}
		}
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	public univeralResult GetData(long nNo, Class beanClass) {
		return GetData(nNo, beanClass, null);
	}
	/*******************************xml***********************/
	/*******************************xml***********************/
	/*******************************xml***********************/
	public univeralResult GetXmlData(long nNo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) {
		univeralResult result = new univeralResult();
		try{
			String resultString = getXmlData(nNo, dataProtocol, mappingOption);
			if(resultString!=null) {
				result.setResultObj(resultString);				
			}
			else {
				result.setErrorMsg("Can't get Xml Data");
			}
		}
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	public univeralResult GetXmlData(long nNo, Object mappingOption) {
		return GetXmlData(nNo, getDefaultDataProtocol(), mappingOption);
	}
	public univeralResult GetXmlData(long nNo, key_WebDataProtocol_Model dataProtocol) {
		return GetXmlData(nNo, dataProtocol, null);
	}
	public univeralResult GetXmlData(long nNo) {
		return GetXmlData(nNo, getDefaultDataProtocol(), null);
	}
	public univeralResult GetXmlNodeData(long nNo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) {
		univeralResult result = new univeralResult();
		try{
			Node resultNode = getXmlNodeData(nNo, dataProtocol, mappingOption);
			if(resultNode!=null) {
				result.setResultObj(resultNode);				
			}
			else {
				result.setErrorMsg("Can't get Xml Node.");
			}
		}
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	public univeralResult GetXmlNodeData(long nNo, Object mappingOption) {
		return GetXmlNodeData(nNo, getDefaultDataProtocol(), mappingOption);
	}
	public univeralResult GetXmlNodeData(long nNo, key_WebDataProtocol_Model dataProtocol) {
		return GetXmlNodeData(nNo, dataProtocol, null);
	}
	public univeralResult GetXmlNodeData(long nNo) {
		return GetXmlNodeData(nNo, getDefaultDataProtocol(), null);
	}
	public univeralResult GetDataFromXml(String strXml, Class beanClass, key_WebDataProtocol_Model dataProtocol, Object mappingOption) {
		univeralResult result = new univeralResult();
		try{
			Object resultObj = XgetDataFromXml(strXml, beanClass, dataProtocol, mappingOption);
			if(result!=null) {
				result.setResultObj(resultObj);				
			}
			else {
				result.setErrorMsg("Can't get Xml Data");
			}
		}
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	public univeralResult GetDataFromXml(String strXml, Class beanClass, Object mappingOption) {
		return GetDataFromXml(strXml, beanClass, getDefaultDataProtocol(), mappingOption);
	}
	public univeralResult GetDataFromXml(String strXml, Class beanClass, key_WebDataProtocol_Model dataProtocol) {
		return GetDataFromXml(strXml, beanClass, dataProtocol, null);
	}
	public univeralResult GetDataFromXml(String strXml, Class beanClass) {
		return GetDataFromXml(strXml, beanClass, getDefaultDataProtocol(), null);
	}
	public univeralResult GetDataListFromXml(String strXml, Class beanClass, key_WebDataProtocol_Model dataProtocol, Object mappingOption) {
		univeralResult result = new univeralResult();
		try{
			Vector<Object> resultList = XgetDataListFromXml(strXml, beanClass, dataProtocol, mappingOption);
			if(result!=null) {
				result.setResultObj(resultList);
			}
			else {
				result.setErrorMsg("Can't get Xml Data");
			}
		}
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	public univeralResult GetDataListFromXml(String strXml, Class beanClass, Object mappingOption) {
		return GetDataListFromXml(strXml, beanClass, getDefaultDataProtocol(), mappingOption);
	}
	public univeralResult GetDataListFromXml(String strXml, Class beanClass, key_WebDataProtocol_Model dataProtocol) {
		return GetDataListFromXml(strXml, beanClass, dataProtocol, null);
	}
	public univeralResult GetDataListFromXml(String strXml, Class beanClass) {
		return GetDataListFromXml(strXml, beanClass, getDefaultDataProtocol(), null);
	}
	public univeralResult GetDataFromXmlNode(Node nodeXml, Class beanClass, key_WebDataProtocol_Model dataProtocol, Object mappingOption) {
		univeralResult result = new univeralResult();
		try{
			Object resultObj = XgetDataFromXmlNode(nodeXml, beanClass, dataProtocol, mappingOption);
			if(result!=null) {
				result.setResultObj(resultObj);
			}
			else {
				result.setErrorMsg("Can't get Xml Node.");
			}
		}
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	public univeralResult GetDataFromXmlNode(Node nodeXml, Class beanClass, Object mappingOption) {
		return GetDataFromXmlNode(nodeXml, beanClass, getDefaultDataProtocol(), mappingOption);
	}
	public univeralResult GetDataFromXmlNode(Node nodeXml, Class beanClass, key_WebDataProtocol_Model dataProtocol) {
		return GetDataFromXmlNode(nodeXml, beanClass, dataProtocol, null);
	}
	public univeralResult GetDataFromXmlNode(Node nodeXml, Class beanClass) {
		return GetDataFromXmlNode(nodeXml, beanClass, getDefaultDataProtocol(), null);
	}
	public univeralResult GetXmlDocFromData(Object obj, Document doc, int nArrayIndex, key_WebDataProtocol_Model dataProtocol, Object mappingOption) {
		univeralResult result = new univeralResult();
		try{
			Node resultHash = XgetXmlDocFromData(obj, doc, nArrayIndex, dataProtocol, mappingOption);
			if(result!=null) {
				result.setResultObj(resultHash);
			}
			else {
				result.setErrorMsg("Can't get Xml Node.");
			}
		}
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	public univeralResult GetXmlDocFromData(Object obj, Document doc, int nArrayIndex, Object mappingOption) {
		return GetXmlDocFromData(obj, doc, nArrayIndex, getDefaultDataProtocol(), mappingOption);
	}
	public univeralResult GetXmlDocFromData(Object obj, Document doc, int nArrayIndex, key_WebDataProtocol_Model dataProtocol) {
		return GetXmlDocFromData(obj, doc, nArrayIndex, dataProtocol, null);
	}
	public univeralResult GetXmlDocFromData(Object obj, Document doc, int nArrayIndex) {
		return GetXmlDocFromData(obj, doc, nArrayIndex, getDefaultDataProtocol(), null);
	}
	/*******************************json***********************/
	/*******************************json***********************/
	/*******************************json***********************/
	public univeralResult GetJSonData(long nNo, Object mappingOption) {
		univeralResult result = new univeralResult();
		try{
			String resultString = getJSonData(nNo, mappingOption);
			if(resultString!=null) {
				result.setResultObj(resultString);				
			}
			else {
				result.setErrorMsg("Can't get JSon Data.");
			}
		}
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	public univeralResult GetJSonData(long nNo) {
		return GetJSonData(nNo, null);
	}
	public univeralResult GetHashMapData(long nNo, Object mappingOption) {
		univeralResult result = new univeralResult();
		try{
			HashMap resultHash = getHashMapData(nNo, mappingOption);
			if(resultHash!=null) {
				result.setResultObj(resultHash);				
			}
			else {
				result.setErrorMsg("Can't get HashMap data.");
			}
		}
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	public univeralResult GetHashMapData(long nNo) {
		return GetHashMapData(nNo, null);
	}
	public univeralResult GetDataFromJSon(String strJSon, Class beanClass, Object mappingOption) {
		univeralResult result = new univeralResult();
		try{
			Object resultObj = XgetDataFromJSon(strJSon, beanClass, mappingOption);
			if(result!=null) {
				result.setResultObj(resultObj);				
			}
			else {
				result.setErrorMsg("Can't get JSon Data.");
			}
		}
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	public univeralResult GetDataFromJSon(String strJSon, Class beanClass) {
		return GetDataFromJSon(strJSon, beanClass, null);
	}
	public univeralResult GetDataListFromJSon(String strJSon, Class beanClass, key_WebDataProtocol_Model dataProtocol, Object mappingOption) {
		univeralResult result = new univeralResult();
		try{
			Vector<Object> resultList = XgetDataListFromJSon(strJSon, beanClass, dataProtocol, mappingOption);
			if(result!=null) {
				result.setResultObj(resultList);
			}
			else {
				result.setErrorMsg("Can't get JSon Data.");
			}
		}
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	public univeralResult GetDataListFromJSon(String strJSon, Class beanClass, Object mappingOption) {
		return GetDataListFromJSon(strJSon, beanClass, getDefaultDataProtocol(), mappingOption);
	}
	public univeralResult GetDataListFromJSon(String strJSon, Class beanClass, key_WebDataProtocol_Model dataProtocol) {
		return GetDataListFromJSon(strJSon, beanClass, dataProtocol, null);
	}
	public univeralResult GetDataListFromJSon(String strJSon, Class beanClass) {
		return GetDataListFromJSon(strJSon, beanClass, getDefaultDataProtocol(), null);
	}
	public univeralResult GetJSonFromData(Object obj, Object mappingOption) {
		univeralResult result = new univeralResult();
		try{
			String resultString = XgetJSonFromData(obj, mappingOption);
			if(result!=null) {
				result.setResultObj(resultString);
			}
			else {
				result.setErrorMsg("Can't get JSon Data.");
			}
		}
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	public univeralResult GetJSonFromData(Object obj) {
		return GetJSonFromData(obj, null);
	}
	public univeralResult GetJSonFromDataList(Vector<Object> listObj, key_WebDataProtocol_Model dataProtocol, Object mappingOption) {
		univeralResult result = new univeralResult();
		try{
			String resultString = XgetJSonFromDataList(listObj, dataProtocol, mappingOption);
			if(result!=null) {
				result.setResultObj(resultString);
			}
			else {
				result.setErrorMsg("Can't get JSon Data.");
			}
		}
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	public univeralResult GetJSonFromDataList(Vector<Object> listObj, Object mappingOption) {
		return GetJSonFromDataList(listObj, getDefaultDataProtocol(), mappingOption);
	}
	public univeralResult GetJSonFromDataList(Vector<Object> listObj, key_WebDataProtocol_Model dataProtocol) {
		return GetJSonFromDataList(listObj, dataProtocol, null);
	}
	public univeralResult GetJSonFromDataList(Vector<Object> listObj) {
		return GetJSonFromDataList(listObj, getDefaultDataProtocol(), null);
	}
	public univeralResult GetXmlFromData(Object obj, key_WebDataProtocol_Model dataProtocol, Object mappingOption) {
		univeralResult result = new univeralResult();
		try{
			String resultString = XgetXmlFromData(obj, dataProtocol, mappingOption);
			if(result!=null) {
				result.setResultObj(resultString);
			}
			else {
				result.setErrorMsg("Can't get JSon Data.");
			}
		}
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	public univeralResult GetXmlFromData(Object obj, Object mappingOption) {
		return GetXmlFromData(obj, getDefaultDataProtocol(), null);
	}
	public univeralResult GetXmlFromData(Object obj, key_WebDataProtocol_Model dataProtocol) {
		return GetXmlFromData(obj, dataProtocol, null);
	}
	public univeralResult GetXmlFromData(Object obj) {
		return GetXmlFromData(obj, getDefaultDataProtocol(), null);
	}
	public univeralResult GetXmlFromDataList(Vector<Object> listObj, key_WebDataProtocol_Model dataProtocol, Object mappingOption) {
		univeralResult result = new univeralResult();
		try{
			String resultString = XgetXmlFromDataList(listObj, dataProtocol, mappingOption);
			if(result!=null) {
				result.setResultObj(resultString);
			}
			else {
				result.setErrorMsg("Can't get JSon Data.");
			}
		}
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	public univeralResult GetXmlFromDataList(Vector<Object> listObj, Object mappingOption) {
		return GetXmlFromDataList(listObj, getDefaultDataProtocol(), mappingOption);
	}
	public univeralResult GetXmlFromDataList(Vector<Object> listObj, key_WebDataProtocol_Model dataProtocol) {
		return GetXmlFromDataList(listObj, dataProtocol, null);
	}
	public univeralResult GetXmlFromDataList(Vector<Object> listObj) {
		return GetXmlFromDataList(listObj, getDefaultDataProtocol(), null);
	}
	public univeralResult GetDataFromHashMap(HashMap hashitem, Class beanClass, Object mappingOption) {
		univeralResult result = new univeralResult();
		try{
			Object resultObj = XgetDataFromHashMap(hashitem, beanClass, mappingOption);
			if(result!=null) {
				result.setResultObj(resultObj);
			}
			else {
				result.setErrorMsg("Can't get data");
			}
		}
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	public univeralResult GetDataFromHashMap(HashMap hashitem, Class beanClass) {
		return GetDataFromHashMap(hashitem, beanClass, null);
	}
	public univeralResult GetHashMapFromData(Object obj, int nArrayIndex, Object mappingOption) {
		univeralResult result = new univeralResult();
		try{
			HashMap resultHash = XgetHashMapFromData(obj, nArrayIndex, mappingOption);
			if(result!=null) {
				result.setResultObj(resultHash);
			}
			else {
				result.setErrorMsg("Can't get Xml Node.");
			}
		}
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	public univeralResult GetHashMapFromData(Object obj, int nArrayIndex) {
		return GetHashMapFromData(obj, nArrayIndex, null);
	}
	/*******************************utils***********************/
	/*******************************utils***********************/
	/*******************************utils***********************/
	/** 
	 * 
	 * @param nDistinctField 
	 * @param whereList
	 * @param bAndOr 
	 * @param orderList 
	 * @throws Throwable
	 * 
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 12 PM 2:30:04
	 * @since ModelWeb 1.0
	 */
	public univeralResult GetDistinctListbyEqu(int nDistinctField, whereExpressionRelation whereList, boolean bAndOr, orderExpressionRelation orderList, Object pageInfo) {
		univeralResult result = new univeralResult();
		try{
			pageExpressionRelation pgInfo = getPageInfo(pageInfo);
			if(pgInfo==null) {
				pgInfo = new pageExpressionRelation();
			}
			Vector<Object> resultList = getDistinctListbyEqu(nDistinctField, whereList, bAndOr, orderList, pgInfo);
			if(resultList!=null){
				result.setPage(getPageInfo(pgInfo));
				result.setResultObj(resultList);
			}
			else{
				result.setErrorMsg("Can't get data list.");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	/**
	 * @param nDistinctField 
	 * @param likeList
	 * @param bAndOr
	 * @param orderList 
	 * @param pageInfo
	 * @return
	 * @author Olena.Zagreba
	 * @version ModelWeb 2013. 05. 21 PM 4:34:00
	 * @since ModelWeb
	 */
	public univeralResult GetDistinctListbyLike(int nDistinctField, whereExpressionRelation likeList, boolean bAndOr, orderExpressionRelation orderList, Object pageInfo) {
		univeralResult result = new univeralResult();
		try{
			pageExpressionRelation pgInfo = getPageInfo(pageInfo);
			if(pgInfo==null) {
				pgInfo = new pageExpressionRelation();
			}
			Vector<Object> resultList = getDistinctListbyLike(nDistinctField, likeList, bAndOr, orderList, pgInfo);
			if(resultList!=null){
				result.setPage(getPageInfo(pgInfo));
				result.setResultObj(resultList);
			}
			else{
				result.setErrorMsg("Can't get data list.");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	/**
	 * 
	 * @param query
	 * @param executeList
	 * @param pageInfo
	 * @return
	 * @author Olena.Zagreba
	 * @version ModelWeb 2013. 05. 21 PM 4:33:45
	 * @since ModelWeb
	 */
	public univeralResult GetDistinctListbyQuery(String query, executeExpressionRelation executeList, Object pageInfo) {
		univeralResult result = new univeralResult();
		try{
			pageExpressionRelation pgInfo = getPageInfo(pageInfo);
			if(pgInfo==null) {
				pgInfo = new pageExpressionRelation();
			}
			Vector<Object> resultList = getDistinctListbyQuery(query, executeList, pgInfo);
			if(resultList!=null){
				result.setPage(getPageInfo(pgInfo));
				result.setResultObj(resultList);
			}
			else{
				result.setErrorMsg("Can't get data list.");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	/**
	 * @param whereList 
	 * @param bAndOr
	 * @return
	 * @throws Throwable
	 */
	public univeralResult GetListCountByEqu(whereExpressionRelation whereList, boolean bAndOr) {
		univeralResult result = new univeralResult();
		try{
			long resultNum = getListCountByEqu(whereList, bAndOr);
			if(resultNum >= 0){
				result.setResultObj(resultNum);
			}
			else{
				result.setErrorMsg("Can't get List count");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	public univeralResult GetListCountByInfo(whereExpressionRelation whereList, String strCountNum) {
		univeralResult result = new univeralResult();
		try{
			long resultNum = getListCountByInfo(whereList, strCountNum);
			if(resultNum >= 0){
				result.setResultObj(resultNum);
			}
			else{
				result.setErrorMsg("Can't get List count");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	public univeralResult GetListCountByLike(whereExpressionRelation likeList, boolean bAndOr) {
		univeralResult result = new univeralResult();
		try{
			long resultNum = getListCountByLike(likeList, bAndOr);
			if(resultNum >= 0){
				result.setResultObj(resultNum);
			}
			else{
				result.setErrorMsg("Can't get List count");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	public univeralResult GetNumValueByQuery(String query, executeExpressionRelation executeList, String countStr) {
		univeralResult result = new univeralResult();
		try{
			long resultNum = getNumValueByQuery(query, executeList, countStr);
			if(resultNum >= 0){
				result.setResultObj(resultNum);
			}
			else{
				result.setErrorMsg("Can't get List count");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	/**
	 * @param nMaxField
	 * @param bAndOr
	 * @return
	 * @throws Throwable
	 */
	public univeralResult GetListMaxByEqu(int nMaxField, whereExpressionRelation whereList, boolean bAndOr) {
		univeralResult result = new univeralResult();
		try{
			long resultNum = getListMaxByEqu(nMaxField, whereList, bAndOr);
			if(resultNum >= 0){
				result.setResultObj(resultNum);
			}
			else{
				result.setErrorMsg("Can't get List max value.");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	public univeralResult GetListMaxByInfo(int nMaxField, whereExpressionRelation whereList, String strMaxNum) {
		univeralResult result = new univeralResult();
		try{
			long resultNum = getListMaxByInfo(nMaxField, whereList, strMaxNum);
			if(resultNum >= 0){
				result.setResultObj(resultNum);
			}
			else{
				result.setErrorMsg("Can't get List max value.");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	public univeralResult GetListMaxByLike(int nMaxField, whereExpressionRelation likeList, boolean bAndOr) {
		univeralResult result = new univeralResult();
		try{
			long resultNum = getListMaxByLike(nMaxField, likeList, bAndOr);
			if(resultNum >= 0){
				result.setResultObj(resultNum);
			}
			else{
				result.setErrorMsg("Can't get List max value.");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	/**
	 * @param nMinField
	 * @param whereList 
	 * @param bAndOr
	 * @return
	 * @throws Throwable
	 */
	public univeralResult GetListMinByEqu(int nMinField, whereExpressionRelation whereList, boolean bAndOr) {
		univeralResult result = new univeralResult();
		try{
			long resultNum = getListMinByEqu(nMinField, whereList, bAndOr);
			if(resultNum >= 0){
				result.setResultObj(resultNum);
			}
			else{
				result.setErrorMsg("Can't get List min value.");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	public univeralResult GetListMinByInfo(int nMinField, whereExpressionRelation whereList, String strMinNum) {
		univeralResult result = new univeralResult();
		try{
			long resultNum = getListMinByInfo(nMinField, whereList, strMinNum);
			if(resultNum >= 0){
				result.setResultObj(resultNum);
			}
			else{
				result.setErrorMsg("Can't get List min value.");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	public univeralResult GetListMinByLike(int nMinField, whereExpressionRelation likeList, boolean bAndOr) {
		univeralResult result = new univeralResult();
		try{
			long resultNum = getListMinByLike(nMinField, likeList, bAndOr);
			if(resultNum >= 0){
				result.setResultObj(resultNum);
			}
			else{
				result.setErrorMsg("Can't get List min value.");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	/**
	 * @param nSumField
	 * @param whereList 
	 * @param bAndOr
	 * @return
	 * @throws Throwable
	 */
	public univeralResult GetListSumByEqu(int nSumField, whereExpressionRelation whereList, boolean bAndOr) {
		univeralResult result = new univeralResult();
		try{
			long resultNum = getListSumByEqu(nSumField, whereList, bAndOr);
			if(resultNum >= 0){
				result.setResultObj(resultNum);
			}
			else{
				result.setErrorMsg("Can't get List sum");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	/**
	 * @param nSumField
	 * @param whereList
	 * @param strSumNum
	 * @return
	 * @throws Throwable
	 */
	public univeralResult GetListSumByInfo(int nSumField, whereExpressionRelation whereList, String strSumNum) {
		univeralResult result = new univeralResult();
		try{
			long resultNum = getListSumByInfo(nSumField, whereList, strSumNum);
			if(resultNum >= 0){
				result.setResultObj(resultNum);
			}
			else{
				result.setErrorMsg("Can't get List sum");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	/**
	 * @param nSumField
	 * @param likeList
	 * @param bAndOr
	 * @return
	 * @throws Throwable
	 */
	public univeralResult GetListSumByLike(int nSumField, whereExpressionRelation likeList, boolean bAndOr) {
		univeralResult result = new univeralResult();
		try{
			long resultNum = getListSumByLike(nSumField, likeList, bAndOr);
			if(resultNum >= 0){
				result.setResultObj(resultNum);
			}
			else{
				result.setErrorMsg("Can't get List sum");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	/*******************************get***********************/
	/*******************************get***********************/
	/*******************************get***********************/
	/**
	 * @param selectList 
	 * @param whereList 
	 * @param orderList 
	 * @param pageInfo
	 * @param mappingOption
	 * @return
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 18 AM 10:23:33
	 * @since ModelWeb 1.0
	 */
	@SuppressWarnings("unchecked")
	public univeralResult GetDataListByInfo(whereExpressionRelation whereList, orderExpressionRelation orderList, Object pageInfo, Class beanClass, Object mappingOption) {
		univeralResult result = new univeralResult();
		try{
			pageExpressionRelation pgInfo = getPageInfo(pageInfo);
			if(pgInfo==null) {
				pgInfo = new pageExpressionRelation();
			}
			Vector<Object> resultList = (Vector)getDataListByInfo(whereList, orderList, pgInfo, beanClass, mappingOption);
			if(resultList!=null){
				result.setPage(getPageInfo(pgInfo));
				result.setResultObj(resultList);
			}
			else{
				result.setErrorMsg("Can't get data list.");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	public univeralResult GetDataListByInfo(whereExpressionRelation whereList, orderExpressionRelation orderList, Object pageInfo, Class beanClass) {
		return GetDataListByInfo(whereList, orderList, pageInfo, beanClass, null);
	}
	/**
	 * @param whereList
	 * @param orderList
	 * @param pageInfo
	 * @param dataProtocol 
	 * @param mappingOption 
	 * @return
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @version ModelWeb 2013. 05. 20 AM 11:14:17
	 * @since ModelWeb
	 */
	public univeralResult GetXmlListByInfo(whereExpressionRelation whereList, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		univeralResult result = new univeralResult();
		try{
			pageExpressionRelation pgInfo = getPageInfo(pageInfo);
			if(pgInfo==null) {
				pgInfo = new pageExpressionRelation();
			}
			String resultString = getXmlListByInfo(whereList, orderList, pgInfo, dataProtocol, mappingOption);
			if(resultString!=null){
				result.setPage(getPageInfo(pgInfo));
				result.setResultObj(resultString);
			}
			else{
				result.setErrorMsg("Can't get XML data list.");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	public univeralResult GetXmlListByInfo(whereExpressionRelation whereList, orderExpressionRelation orderList, Object pageInfo, Object mappingOption) throws Throwable {
		return GetXmlListByInfo(whereList, orderList, pageInfo, getDefaultDataProtocol(), mappingOption);
	}
	public univeralResult GetXmlListByInfo(whereExpressionRelation whereList, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return GetXmlListByInfo(whereList, orderList, pageInfo, dataProtocol, null);
	}
	public univeralResult GetXmlListByInfo(whereExpressionRelation whereList, orderExpressionRelation orderList, Object pageInfo) throws Throwable {
		return GetXmlListByInfo(whereList, orderList, pageInfo, getDefaultDataProtocol(), null);
	}
	/**
	 * @param whereList
	 * @param orderList
	 * @param pageInfo
	 * @param dataProtocol
	 * @param mappingOption
	 * @return
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @version ModelWeb 2013. 05. 20 AM 11:14:17
	 * @since ModelWeb
	 */
	public univeralResult GetXmlDocByInfo(whereExpressionRelation whereList, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		univeralResult result = new univeralResult();
		try{
			pageExpressionRelation pgInfo = getPageInfo(pageInfo);
			if(pgInfo==null) {
				pgInfo = new pageExpressionRelation();
			}
			Node resultDoc = getXmlDocByInfo(whereList, orderList, pgInfo, dataProtocol, mappingOption);
			if(resultDoc!=null){
				result.setPage(getPageInfo(pgInfo));
				result.setResultObj(resultDoc);
			}
			else{
				result.setErrorMsg("Can't get XML Node Data List");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	public univeralResult GetXmlDocByInfo(whereExpressionRelation whereList, orderExpressionRelation orderList, Object pageInfo, Object mappingOption) throws Throwable {
		return GetXmlDocByInfo(whereList, orderList, pageInfo, getDefaultDataProtocol(), mappingOption);
	}
	public univeralResult GetXmlDocByInfo(whereExpressionRelation whereList, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return GetXmlDocByInfo(whereList, orderList, pageInfo, dataProtocol, null);
	}
	public univeralResult GetXmlDocByInfo(whereExpressionRelation whereList, orderExpressionRelation orderList, Object pageInfo) throws Throwable {
		return GetXmlDocByInfo(whereList, orderList, pageInfo, getDefaultDataProtocol(), null);
	}
	/**
	 * @param whereList
	 * @param orderList
	 * @param pageInfo 
	 * @param dataProtocol
	 * @param mappingOption
	 * @return
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @version ModelWeb 2013. 05. 20 AM 11:10:28
	 * @since ModelWeb
	 */
	public univeralResult GetJSonByInfo(whereExpressionRelation whereList, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		univeralResult result = new univeralResult();
		try{
			pageExpressionRelation pgInfo = getPageInfo(pageInfo);
			if(pgInfo==null) {
				pgInfo = new pageExpressionRelation();
			}
			String resultString = getJSonByInfo(whereList, orderList, pgInfo, dataProtocol, mappingOption);
			if(resultString!=null){
				result.setPage(getPageInfo(pgInfo));
				result.setResultObj(resultString);
			}
			else{
				result.setErrorMsg("JSonCan't get data list.");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	public univeralResult GetJSonByInfo(whereExpressionRelation whereList, orderExpressionRelation orderList, Object pageInfo, Object mappingOption) throws Throwable {
		return GetJSonByInfo(whereList, orderList, pageInfo, getDefaultDataProtocol(), mappingOption);
	}
	public univeralResult GetJSonByInfo(whereExpressionRelation whereList, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return GetJSonByInfo(whereList, orderList, pageInfo, dataProtocol, null);
	}
	public univeralResult GetJSonByInfo(whereExpressionRelation whereList, orderExpressionRelation orderList, Object pageInfo) throws Throwable {
		return GetJSonByInfo(whereList, orderList, pageInfo, getDefaultDataProtocol(), null);
	}
	/**
	 * @param whereList
	 * @param orderList 
	 * @param pageInfo 
	 * @param dataProtocol
	 * @param mappingOption 
	 * @return
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @version ModelWeb 2013. 05. 20 AM 11:10:28
	 * @since ModelWeb
	 */
	public univeralResult GetHashMapByInfo(whereExpressionRelation whereList, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		univeralResult result = new univeralResult();
		try{
			pageExpressionRelation pgInfo = getPageInfo(pageInfo);
			if(pgInfo==null) {
				pgInfo = new pageExpressionRelation();
			}
			HashMap resultHash = getHashMapByInfo(whereList, orderList, pgInfo, dataProtocol, mappingOption);
			if(resultHash!=null){
				result.setPage(getPageInfo(pgInfo));
				result.setResultObj(resultHash);
			}
			else{
				result.setErrorMsg("Can't get HashMap data list.");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	public univeralResult GetHashMapByInfo(whereExpressionRelation whereList, orderExpressionRelation orderList, Object pageInfo, Object mappingOption) throws Throwable {
		return GetHashMapByInfo(whereList, orderList, pageInfo, getDefaultDataProtocol(), mappingOption);
	}
	public univeralResult GetHashMapByInfo(whereExpressionRelation whereList, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return GetHashMapByInfo(whereList, orderList, pageInfo, dataProtocol, null);
	}
	public univeralResult GetHashMapByInfo(whereExpressionRelation whereList, orderExpressionRelation orderList, Object pageInfo) throws Throwable {
		return GetHashMapByInfo(whereList, orderList, pageInfo, getDefaultDataProtocol(), null);
	}
	@SuppressWarnings("unchecked")
	@Deprecated
	public univeralResult GetDataListByAndEquByBean(Object info, orderExpressionRelation orderList, Object pageInfo, Class beanClass, Object mappingOption) {
		univeralResult result = new univeralResult();
		try{
			pageExpressionRelation pgInfo = getPageInfo(pageInfo);
			if(pgInfo==null) {
				pgInfo = new pageExpressionRelation();
			}
			Vector<Object> resultList = (Vector)getDataListByAndEquByBean(info, orderList, pgInfo, beanClass, mappingOption);
			if(resultList!=null){
				result.setPage(getPageInfo(pgInfo));
				result.setResultObj(resultList);
			}
			else{
				result.setErrorMsg("Can't get data list.");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	@Deprecated
	public univeralResult GetDataListByAndEquByBean(Object info, orderExpressionRelation orderList, Object pageInfo, Class beanClass) {
		return GetDataListByAndEquByBean(info, orderList, pageInfo, beanClass, null);
	}
	
	@Deprecated
	public univeralResult GetXmlByAndEquByXml(String info, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		univeralResult result = new univeralResult();
		try{
			pageExpressionRelation pgInfo = getPageInfo(pageInfo);
			if(pgInfo==null) {
				pgInfo = new pageExpressionRelation();
			}
			String resultString = getXmlByAndEquByXml(info, orderList, pgInfo, dataProtocol, mappingOption);
			if(resultString!=null){
				result.setPage(getPageInfo(pgInfo));
				result.setResultObj(resultString);
			}
			else{
				result.setErrorMsg("Can't get XML data list.");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	@Deprecated
	public univeralResult GetXmlByAndEquByXml(String info, orderExpressionRelation orderList, Object pageInfo, Object mappingOption) throws Throwable {
		return GetXmlByAndEquByXml(info, orderList, pageInfo, getDefaultDataProtocol(), mappingOption);
	}
	@Deprecated
	public univeralResult GetXmlByAndEquByXml(String info, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return GetXmlByAndEquByXml(info, orderList, pageInfo, dataProtocol, null);
	}
	@Deprecated
	public univeralResult GetXmlByAndEquByXml(String info, orderExpressionRelation orderList, Object pageInfo) throws Throwable {
		return GetXmlByAndEquByXml(info, orderList, pageInfo, getDefaultDataProtocol(), null);
	}
	
	@Deprecated
	public univeralResult GetXmlDocByAndEquByXml(String info, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		univeralResult result = new univeralResult();
		try{
			pageExpressionRelation pgInfo = getPageInfo(pageInfo);
			if(pgInfo==null) {
				pgInfo = new pageExpressionRelation();
			}
			Node resultDoc = getXmlDocByAndEquByXml(info, orderList, pgInfo, dataProtocol, mappingOption);
			if(resultDoc!=null){
				result.setPage(getPageInfo(pgInfo));
				result.setResultObj(resultDoc);
			}
			else{
				result.setErrorMsg("Can't get XML Node Data List");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	@Deprecated
	public univeralResult GetXmlDocByAndEquByXml(String info, orderExpressionRelation orderList, Object pageInfo, Object mappingOption) throws Throwable {
		return GetXmlDocByAndEquByXml(info, orderList, pageInfo, getDefaultDataProtocol(), mappingOption);
	}
	@Deprecated
	public univeralResult GetXmlDocByAndEquByXml(String info, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return GetXmlDocByAndEquByXml(info, orderList, pageInfo, dataProtocol, null);
	}
	@Deprecated
	public univeralResult GetXmlDocByAndEquByXml(String info, orderExpressionRelation orderList, Object pageInfo) throws Throwable {
		return GetXmlDocByAndEquByXml(info, orderList, pageInfo, getDefaultDataProtocol(), null);
	}
	@Deprecated
	public univeralResult GetXmlByAndEquByItem(IItem_Model info, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		univeralResult result = new univeralResult();
		try{
			pageExpressionRelation pgInfo = getPageInfo(pageInfo);
			if(pgInfo==null) {
				pgInfo = new pageExpressionRelation();
			}
			String resultString = getXmlByAndEquByItem(info, orderList, pgInfo, dataProtocol, mappingOption);
			if(resultString!=null){
				result.setPage(getPageInfo(pgInfo));
				result.setResultObj(resultString);
			}
			else{
				result.setErrorMsg("Can't get XML data list.");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	@Deprecated
	public univeralResult GetXmlByAndEquByItem(IItem_Model info, orderExpressionRelation orderList, Object pageInfo, Object mappingOption) throws Throwable {
		return GetXmlByAndEquByItem(info, orderList, pageInfo, getDefaultDataProtocol(), mappingOption);
	}
	@Deprecated
	public univeralResult GetXmlByAndEquByItem(IItem_Model info, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return GetXmlByAndEquByItem(info, orderList, pageInfo, dataProtocol, null);
	}
	@Deprecated
	public univeralResult GetXmlByAndEquByItem(IItem_Model info, orderExpressionRelation orderList, Object pageInfo) throws Throwable {
		return GetXmlByAndEquByItem(info, orderList, pageInfo, getDefaultDataProtocol(), null);
	}
	@Deprecated
	public univeralResult GetXmlDocByAndEquByItem(IItem_Model info, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		univeralResult result = new univeralResult();
		try{
			pageExpressionRelation pgInfo = getPageInfo(pageInfo);
			if(pgInfo==null) {
				pgInfo = new pageExpressionRelation();
			}
			Node resultNode = getXmlDocByAndEquByItem(info, orderList, pgInfo, dataProtocol, mappingOption);
			if(resultNode!=null){
				result.setPage(getPageInfo(pgInfo));
				result.setResultObj(resultNode);
			}
			else{
				result.setErrorMsg("Can't get XML Node Data List");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	@Deprecated
	public univeralResult GetXmlDocByAndEquByItem(IItem_Model info, orderExpressionRelation orderList, Object pageInfo, Object mappingOption) throws Throwable {
		return GetXmlDocByAndEquByItem(info, orderList, pageInfo, getDefaultDataProtocol(), mappingOption);
	}
	@Deprecated
	public univeralResult GetXmlDocByAndEquByItem(IItem_Model info, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return GetXmlDocByAndEquByItem(info, orderList, pageInfo, dataProtocol, null);
	}
	@Deprecated
	public univeralResult GetXmlDocByAndEquByItem(IItem_Model info, orderExpressionRelation orderList, Object pageInfo) throws Throwable {
		return GetXmlDocByAndEquByItem(info, orderList, pageInfo, getDefaultDataProtocol(), null);
	}
	@Deprecated
	public univeralResult GetJSonByAndEquByJSon(String info, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		univeralResult result = new univeralResult();
		try{
			pageExpressionRelation pgInfo = getPageInfo(pageInfo);
			if(pgInfo==null) {
				pgInfo = new pageExpressionRelation();
			}
			String resultString = getJSonByAndEquByJSon(info, orderList, pgInfo, dataProtocol, mappingOption);
			if(resultString!=null){
				result.setPage(getPageInfo(pgInfo));
				result.setResultObj(resultString);
			}
			else{
				result.setErrorMsg("Can't get JSon data list.");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	@Deprecated
	public univeralResult GetJSonByAndEquByJSon(String info, orderExpressionRelation orderList, Object pageInfo, Object mappingOption) throws Throwable {
		return GetJSonByAndEquByJSon(info, orderList, pageInfo, getDefaultDataProtocol(), mappingOption);
	}
	@Deprecated
	public univeralResult GetJSonByAndEquByJSon(String info, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return GetJSonByAndEquByJSon(info, orderList, pageInfo, dataProtocol, null);
	}
	@Deprecated
	public univeralResult GetJSonByAndEquByJSon(String info, orderExpressionRelation orderList, Object pageInfo) throws Throwable {
		return GetJSonByAndEquByJSon(info, orderList, pageInfo, getDefaultDataProtocol(), null);
	}
	@Deprecated
	public univeralResult GetHashMapByAndEquByJSon(String info, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		univeralResult result = new univeralResult();
		try{
			pageExpressionRelation pgInfo = getPageInfo(pageInfo);
			if(pgInfo==null) {
				pgInfo = new pageExpressionRelation();
			}
			HashMap resultHash = getHashMapByAndEquByJSon(info, orderList, pgInfo, dataProtocol, mappingOption);
			if(resultHash!=null){
				result.setPage(getPageInfo(pgInfo));
				result.setResultObj(resultHash);
			}
			else{
				result.setErrorMsg("Can't get Hashmap data list.");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	@Deprecated
	public univeralResult GetHashMapByAndEquByJSon(String info, orderExpressionRelation orderList, Object pageInfo, Object mappingOption) throws Throwable {
		return GetHashMapByAndEquByJSon(info, orderList, pageInfo, getDefaultDataProtocol(), mappingOption);
	}
	@Deprecated
	public univeralResult GetHashMapByAndEquByJSon(String info, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return GetHashMapByAndEquByJSon(info, orderList, pageInfo, dataProtocol, null);
	}
	@Deprecated
	public univeralResult GetHashMapByAndEquByJSon(String info, orderExpressionRelation orderList, Object pageInfo) throws Throwable {
		return GetHashMapByAndEquByJSon(info, orderList, pageInfo, getDefaultDataProtocol(), null);
	}
	@Deprecated
	public univeralResult GetJSonByAndEquByItem(IItem_Model itemInfo, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		univeralResult result = new univeralResult();
		try{
			pageExpressionRelation pgInfo = getPageInfo(pageInfo);
			if(pgInfo==null) {
				pgInfo = new pageExpressionRelation();
			}
			String resultString = getJSonByAndEquByItem(itemInfo, orderList, pgInfo, dataProtocol, mappingOption);
			if(resultString!=null){
				result.setPage(getPageInfo(pgInfo));
				result.setResultObj(resultString);
			}
			else{
				result.setErrorMsg("Can't get JSon data list.");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	@Deprecated
	public univeralResult GetJSonByAndEquByItem(IItem_Model itemInfo, orderExpressionRelation orderList, Object pageInfo, Object mappingOption) throws Throwable {
		return GetJSonByAndEquByItem(itemInfo, orderList, pageInfo, getDefaultDataProtocol(), null);
	}
	@Deprecated
	public univeralResult GetJSonByAndEquByItem(IItem_Model itemInfo, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return GetJSonByAndEquByItem(itemInfo, orderList, pageInfo, dataProtocol, null);
	}
	@Deprecated
	public univeralResult GetJSonByAndEquByItem(IItem_Model itemInfo, orderExpressionRelation orderList, Object pageInfo) throws Throwable {
		return GetJSonByAndEquByItem(itemInfo, orderList, pageInfo, getDefaultDataProtocol(), null);
	}
	@Deprecated
	public univeralResult GetHashMapByAndEquByItem(IItem_Model itemInfo, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		univeralResult result = new univeralResult();
		try{
			pageExpressionRelation pgInfo = getPageInfo(pageInfo);
			if(pgInfo==null) {
				pgInfo = new pageExpressionRelation();
			}
			HashMap resultHash = getHashMapByAndEquByItem(itemInfo, orderList, pgInfo, dataProtocol, mappingOption);
			if(resultHash!=null){
				result.setPage(getPageInfo(pgInfo));
				result.setResultObj(resultHash);
			}
			else{
				result.setErrorMsg("Can't get Hashmap data list.");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	@Deprecated
	public univeralResult GetHashMapByAndEquByItem(IItem_Model itemInfo, orderExpressionRelation orderList, Object pageInfo, Object mappingOption) throws Throwable {
		return GetHashMapByAndEquByItem(itemInfo, orderList, pageInfo, getDefaultDataProtocol(), mappingOption);
	}
	@Deprecated
	public univeralResult GetHashMapByAndEquByItem(IItem_Model itemInfo, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return GetHashMapByAndEquByItem(itemInfo, orderList, pageInfo, dataProtocol, null);
	}
	@Deprecated
	public univeralResult GetHashMapByAndEquByItem(IItem_Model itemInfo, orderExpressionRelation orderList, Object pageInfo) throws Throwable {
		return GetHashMapByAndEquByItem(itemInfo, orderList, pageInfo, getDefaultDataProtocol(), null);
	}
	@SuppressWarnings("unchecked")
	@Deprecated
	public univeralResult GetDataListByAndLikeByBean(Object info, orderExpressionRelation orderList, Object pageInfo, Class beanClass, Object mappingOption) {
		univeralResult result = new univeralResult();
		try{
			pageExpressionRelation pgInfo = getPageInfo(pageInfo);
			if(pgInfo==null) {
				pgInfo = new pageExpressionRelation();
			}
			Vector<Object> resultList = (Vector)getDataListByAndLikeByBean(info, orderList, pgInfo, beanClass, mappingOption);
			if(resultList!=null){
				result.setPage(getPageInfo(pgInfo));
				result.setResultObj(resultList);
			}
			else{
				result.setErrorMsg("Can't get data list.");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	@Deprecated
	public univeralResult GetDataListByAndLikeByBean(Object info, orderExpressionRelation orderList, Object pageInfo, Class beanClass) {
		return GetDataListByAndLikeByBean(info, orderList, pageInfo, beanClass);
	}
	@Deprecated
	public univeralResult GetXmlByAndLikeByXml(String info, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		univeralResult result = new univeralResult();
		try{
			pageExpressionRelation pgInfo = getPageInfo(pageInfo);
			if(pgInfo==null) {
				pgInfo = new pageExpressionRelation();
			}
			String resultString = getXmlByAndLikeByXml(info, orderList, pgInfo, dataProtocol, mappingOption);
			if(resultString!=null){
				result.setPage(getPageInfo(pgInfo));
				result.setResultObj(resultString);
			}
			else{
				result.setErrorMsg("Can't get XML data list.");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	@Deprecated
	public univeralResult GetXmlByAndLikeByXml(String info, orderExpressionRelation orderList, Object pageInfo, Object mappingOption) throws Throwable {
		return GetXmlByAndLikeByXml(info, orderList, pageInfo, getDefaultDataProtocol(), mappingOption);
	}
	@Deprecated
	public univeralResult GetXmlByAndLikeByXml(String info, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return GetXmlByAndLikeByXml(info, orderList, pageInfo, dataProtocol, null);
	}
	@Deprecated
	public univeralResult GetXmlByAndLikeByXml(String info, orderExpressionRelation orderList, Object pageInfo) throws Throwable {
		return GetXmlByAndLikeByXml(info, orderList, pageInfo, getDefaultDataProtocol(), null);
	}
	@Deprecated
	public univeralResult GetXmlDocByAndLikeByXml(String info, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		univeralResult result = new univeralResult();
		try{
			pageExpressionRelation pgInfo = getPageInfo(pageInfo);
			if(pgInfo==null) {
				pgInfo = new pageExpressionRelation();
			}
			Node resultNode = getXmlDocByAndLikeByXml(info, orderList, pgInfo, dataProtocol, mappingOption);
			if(resultNode!=null){
				result.setPage(getPageInfo(pgInfo));
				result.setResultObj(resultNode);
			}
			else{
				result.setErrorMsg("Can't get Xml Node data list.");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	@Deprecated
	public univeralResult GetXmlDocByAndLikeByXml(String info, orderExpressionRelation orderList, Object pageInfo, Object mappingOption) throws Throwable {
		return GetXmlDocByAndLikeByXml(info, orderList, pageInfo, getDefaultDataProtocol(), mappingOption);
	}
	@Deprecated
	public univeralResult GetXmlDocByAndLikeByXml(String info, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return GetXmlDocByAndLikeByXml(info, orderList, pageInfo, dataProtocol, null);
	}
	@Deprecated
	public univeralResult GetXmlDocByAndLikeByXml(String info, orderExpressionRelation orderList, Object pageInfo) throws Throwable {
		return GetXmlDocByAndLikeByXml(info, orderList, pageInfo, getDefaultDataProtocol(), null);
	}
	@Deprecated
	public univeralResult GetXmlByAndLikeByItem(IItem_Model info, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		univeralResult result = new univeralResult();
		try{
			pageExpressionRelation pgInfo = getPageInfo(pageInfo);
			if(pgInfo==null) {
				pgInfo = new pageExpressionRelation();
			}
			String resultString = getXmlByAndLikeByItem(info, orderList, pgInfo, dataProtocol, mappingOption);
			if(resultString!=null){
				result.setPage(getPageInfo(pgInfo));
				result.setResultObj(resultString);
			}
			else{
				result.setErrorMsg("Can't get XML data list.");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	@Deprecated
	public univeralResult GetXmlByAndLikeByItem(IItem_Model info, orderExpressionRelation orderList, Object pageInfo, Object mappingOption) throws Throwable {
		return GetXmlByAndLikeByItem(info, orderList, pageInfo, getDefaultDataProtocol(), mappingOption);
	}
	@Deprecated
	public univeralResult GetXmlByAndLikeByItem(IItem_Model info, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return GetXmlByAndLikeByItem(info, orderList, pageInfo, dataProtocol, null);
	}
	@Deprecated
	public univeralResult GetXmlByAndLikeByItem(IItem_Model info, orderExpressionRelation orderList, Object pageInfo) throws Throwable {
		return GetXmlByAndLikeByItem(info, orderList, pageInfo, getDefaultDataProtocol(), null);
	}
	@Deprecated
	public univeralResult GetXmlDocByAndLikeByItem(IItem_Model info, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		univeralResult result = new univeralResult();
		try{
			pageExpressionRelation pgInfo = getPageInfo(pageInfo);
			if(pgInfo==null) {
				pgInfo = new pageExpressionRelation();
			}
			Node resultNode = getXmlDocByAndLikeByItem(info, orderList, pgInfo, dataProtocol, mappingOption);
			if(resultNode!=null){
				result.setPage(getPageInfo(pgInfo));
				result.setResultObj(resultNode);
			}
			else{
				result.setErrorMsg("Can't get XML Node Data List");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	@Deprecated
	public univeralResult GetXmlDocByAndLikeByItem(IItem_Model info, orderExpressionRelation orderList, Object pageInfo, Object mappingOption) throws Throwable {
		return GetXmlDocByAndLikeByItem(info, orderList, pageInfo, getDefaultDataProtocol(), mappingOption);
	}
	@Deprecated
	public univeralResult GetXmlDocByAndLikeByItem(IItem_Model info, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return GetXmlDocByAndLikeByItem(info, orderList, pageInfo, dataProtocol, null);
	}
	@Deprecated
	public univeralResult GetXmlDocByAndLikeByItem(IItem_Model info, orderExpressionRelation orderList, Object pageInfo) throws Throwable {
		return GetXmlDocByAndLikeByItem(info, orderList, pageInfo, getDefaultDataProtocol(), null);
	}
	@Deprecated
	public univeralResult GetJSonByAndLikeByJSon(String info, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		univeralResult result = new univeralResult();
		try{
			pageExpressionRelation pgInfo = getPageInfo(pageInfo);
			if(pgInfo==null) {
				pgInfo = new pageExpressionRelation();
			}
			String resultString = getJSonByAndLikeByJSon(info, orderList, pgInfo, dataProtocol, mappingOption);
			if(resultString!=null){
				result.setPage(getPageInfo(pgInfo));
				result.setResultObj(resultString);
			}
			else{
				result.setErrorMsg("Can't get JSon data list.");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	@Deprecated
	public univeralResult GetJSonByAndLikeByJSon(String info, orderExpressionRelation orderList, Object pageInfo, Object mappingOption) throws Throwable {
		return GetJSonByAndLikeByJSon(info, orderList, pageInfo, getDefaultDataProtocol(), mappingOption);
	}
	@Deprecated
	public univeralResult GetJSonByAndLikeByJSon(String info, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return GetJSonByAndLikeByJSon(info, orderList, pageInfo, dataProtocol, null);
	}
	@Deprecated
	public univeralResult GetJSonByAndLikeByJSon(String info, orderExpressionRelation orderList, Object pageInfo) throws Throwable {
		return GetJSonByAndLikeByJSon(info, orderList, pageInfo, getDefaultDataProtocol(), null);
	}
	@Deprecated
	public univeralResult GetHashMapByAndLikeByJSon(String info, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		univeralResult result = new univeralResult();
		try{
			pageExpressionRelation pgInfo = getPageInfo(pageInfo);
			if(pgInfo==null) {
				pgInfo = new pageExpressionRelation();
			}
			HashMap resultHash = getHashMapByAndLikeByJSon(info, orderList, pgInfo, dataProtocol, mappingOption);
			if(resultHash!=null){
				result.setPage(getPageInfo(pgInfo));
				result.setResultObj(resultHash);
			}
			else{
				result.setErrorMsg("Can't get Hashmap data list");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	@Deprecated
	public univeralResult GetHashMapByAndLikeByJSon(String info, orderExpressionRelation orderList, Object pageInfo, Object mappingOption) throws Throwable {
		return GetHashMapByAndLikeByJSon(info, orderList, pageInfo, getDefaultDataProtocol(), mappingOption);
	}
	@Deprecated
	public univeralResult GetHashMapByAndLikeByJSon(String info, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return GetHashMapByAndLikeByJSon(info, orderList, pageInfo, dataProtocol, null);
	}
	@Deprecated
	public univeralResult GetHashMapByAndLikeByJSon(String info, orderExpressionRelation orderList, Object pageInfo) throws Throwable {
		return GetHashMapByAndLikeByJSon(info, orderList, pageInfo, getDefaultDataProtocol(), null);
	}
	@Deprecated
	public univeralResult getJSonByAndLikeByItem(IItem_Model info, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		univeralResult result = new univeralResult();
		try{
			pageExpressionRelation pgInfo = getPageInfo(pageInfo);
			if(pgInfo==null) {
				pgInfo = new pageExpressionRelation();
			}
			String resultString = getJSonByAndLikeByItem(info, orderList, pgInfo, dataProtocol, mappingOption);
			if(resultString!=null){
				result.setPage(getPageInfo(pgInfo));
				result.setResultObj(resultString);
			}
			else{
				result.setErrorMsg("JSonCan't get data list.");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	@Deprecated
	public univeralResult getJSonByAndLikeByItem(IItem_Model info, orderExpressionRelation orderList, Object pageInfo, Object mappingOption) throws Throwable {
		return getJSonByAndLikeByItem(info, orderList, pageInfo, getDefaultDataProtocol(), mappingOption);
	}
	@Deprecated
	public univeralResult getJSonByAndLikeByItem(IItem_Model info, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return getJSonByAndLikeByItem(info, orderList, pageInfo, dataProtocol, null);
	}
	@Deprecated
	public univeralResult getJSonByAndLikeByItem(IItem_Model info, orderExpressionRelation orderList, Object pageInfo) throws Throwable {
		return getJSonByAndLikeByItem(info, orderList, pageInfo, getDefaultDataProtocol(), null);
	}
	@Deprecated
	public univeralResult GetHashMapByAndLikeByItem(IItem_Model info, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		univeralResult result = new univeralResult();
		try{
			pageExpressionRelation pgInfo = getPageInfo(pageInfo);
			if(pgInfo==null) {
				pgInfo = new pageExpressionRelation();
			}
			HashMap resultHash = getHashMapByAndLikeByItem(info, orderList, pgInfo, dataProtocol, mappingOption);
			if(resultHash!=null){
				result.setPage(getPageInfo(pgInfo));
				result.setResultObj(resultHash);
			}
			else{
				result.setErrorMsg("Can't get Hashmap data list");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	@Deprecated
	public univeralResult GetHashMapByAndLikeByItem(IItem_Model info, orderExpressionRelation orderList, Object pageInfo, Object mappingOption) throws Throwable {
		return GetHashMapByAndLikeByItem(info, orderList, pageInfo, getDefaultDataProtocol(), mappingOption);
	}
	@Deprecated
	public univeralResult GetHashMapByAndLikeByItem(IItem_Model info, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return GetHashMapByAndLikeByItem(info, orderList, pageInfo, dataProtocol, null);
	}
	@Deprecated
	public univeralResult GetHashMapByAndLikeByItem(IItem_Model info, orderExpressionRelation orderList, Object pageInfo) throws Throwable {
		return GetHashMapByAndLikeByItem(info, orderList, pageInfo, getDefaultDataProtocol(), null);
	}
	@SuppressWarnings("unchecked")
	@Deprecated
	public univeralResult GetDataListByOrEquByBean(Object info, orderExpressionRelation orderList, Object pageInfo, Class beanClass, Object mappingOption) {
		univeralResult result = new univeralResult();
		try{
			pageExpressionRelation pgInfo = getPageInfo(pageInfo);
			if(pgInfo==null) {
				pgInfo = new pageExpressionRelation();
			}
			Vector<Object> resultList = (Vector)getDataListByOrEquByBean(info, orderList, pgInfo, beanClass, mappingOption);
			if(resultList!=null){
				result.setPage(getPageInfo(pgInfo));
				result.setResultObj(resultList);
			}
			else{
				result.setErrorMsg("Can't get data list.");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	@Deprecated
	public univeralResult GetDataListByOrEquByBean(Object info, orderExpressionRelation orderList, Object pageInfo, Class beanClass) {
		return GetDataListByOrEquByBean(info, orderList, pageInfo, beanClass, null);
	}
	@Deprecated
	public univeralResult GetXmlByOrEquByXml(String info, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		univeralResult result = new univeralResult();
		try{
			pageExpressionRelation pgInfo = getPageInfo(pageInfo);
			if(pgInfo==null) {
				pgInfo = new pageExpressionRelation();
			}
			String resultString = getXmlByOrEquByXml(info, orderList, pgInfo, dataProtocol, mappingOption);
			if(resultString!=null){
				result.setPage(getPageInfo(pgInfo));
				result.setResultObj(resultString);
			}
			else{
				result.setErrorMsg("Can't get XML data list.");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	@Deprecated
	public univeralResult GetXmlByOrEquByXml(String info, orderExpressionRelation orderList, Object pageInfo, Object mappingOption) throws Throwable {
		return GetXmlByOrEquByXml(info, orderList, pageInfo, getDefaultDataProtocol(), mappingOption);
	}
	@Deprecated
	public univeralResult GetXmlByOrEquByXml(String info, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return GetXmlByOrEquByXml(info, orderList, pageInfo, dataProtocol, null);
	}
	@Deprecated
	public univeralResult GetXmlByOrEquByXml(String info, orderExpressionRelation orderList, Object pageInfo) throws Throwable {
		return GetXmlByOrEquByXml(info, orderList, pageInfo, getDefaultDataProtocol(), null);
	}
	@Deprecated
	public univeralResult GetXmlDocByOrEquByXml(String info, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		univeralResult result = new univeralResult();
		try{
			pageExpressionRelation pgInfo = getPageInfo(pageInfo);
			if(pgInfo==null) {
				pgInfo = new pageExpressionRelation();
			}
			Node resultNode = getXmlDocByOrEquByXml(info, orderList, pgInfo, dataProtocol, mappingOption);
			if(resultNode!=null){
				result.setPage(getPageInfo(pgInfo));
				result.setResultObj(resultNode);
			}
			else{
				result.setErrorMsg("Can't get XML Node Data List");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	@Deprecated
	public univeralResult GetXmlDocByOrEquByXml(String info, orderExpressionRelation orderList, Object pageInfo, Object mappingOption) throws Throwable {
		return GetXmlDocByOrEquByXml(info, orderList, pageInfo, getDefaultDataProtocol(), mappingOption);
	}
	@Deprecated
	public univeralResult GetXmlDocByOrEquByXml(String info, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return GetXmlDocByOrEquByXml(info, orderList, pageInfo, dataProtocol, null);
	}
	@Deprecated
	public univeralResult GetXmlDocByOrEquByXml(String info, orderExpressionRelation orderList, Object pageInfo) throws Throwable {
		return GetXmlDocByOrEquByXml(info, orderList, pageInfo, getDefaultDataProtocol(), null);
	}
	@Deprecated
	public univeralResult GetXmlByOrEquByItem(IItem_Model info, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		
		univeralResult result = new univeralResult();
		try{
			pageExpressionRelation pgInfo = getPageInfo(pageInfo);
			if(pgInfo==null) {
				pgInfo = new pageExpressionRelation();
			}
			String resultString = getXmlByOrEquByItem(info, orderList, pgInfo, dataProtocol, mappingOption);
			if(resultString!=null){
				result.setPage(getPageInfo(pgInfo));
				result.setResultObj(resultString);
			}
			else{
				result.setErrorMsg("Can't get XML data list.");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	@Deprecated
	public univeralResult GetXmlByOrEquByItem(IItem_Model info, orderExpressionRelation orderList, Object pageInfo, Object mappingOption) throws Throwable {
		return GetXmlByOrEquByItem(info, orderList, pageInfo, getDefaultDataProtocol(), mappingOption);
	}
	@Deprecated
	public univeralResult GetXmlByOrEquByItem(IItem_Model info, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return GetXmlByOrEquByItem(info, orderList, pageInfo, dataProtocol, null);
	}
	@Deprecated
	public univeralResult GetXmlByOrEquByItem(IItem_Model info, orderExpressionRelation orderList, Object pageInfo) throws Throwable {
		return GetXmlByOrEquByItem(info, orderList, pageInfo, getDefaultDataProtocol(), null);
	}
	@Deprecated
	public univeralResult GetXmlDocInfoOrEquByItem(IItem_Model info, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		
		univeralResult result = new univeralResult();
		try{
			pageExpressionRelation pgInfo = getPageInfo(pageInfo);
			if(pgInfo==null) {
				pgInfo = new pageExpressionRelation();
			}
			Node resultNode = getXmlDocInfoOrEquByItem(info, orderList, pgInfo, dataProtocol, mappingOption);
			if(resultNode!=null){
				result.setPage(getPageInfo(pgInfo));
				result.setResultObj(resultNode);
			}
			else{
				result.setErrorMsg("Can't get XML Node Data List");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	@Deprecated
	public univeralResult GetXmlDocInfoOrEquByItem(IItem_Model info, orderExpressionRelation orderList, Object pageInfo, Object mappingOption) throws Throwable {
		return GetXmlDocInfoOrEquByItem(info, orderList, pageInfo, getDefaultDataProtocol(), mappingOption);
	}
	@Deprecated
	public univeralResult GetXmlDocInfoOrEquByItem(IItem_Model info, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return GetXmlDocInfoOrEquByItem(info, orderList, pageInfo, dataProtocol, null);
	}
	@Deprecated
	public univeralResult GetXmlDocInfoOrEquByItem(IItem_Model info, orderExpressionRelation orderList, Object pageInfo) throws Throwable {
		return GetXmlDocInfoOrEquByItem(info, orderList, pageInfo, getDefaultDataProtocol(), null);
	}
	/**
	 * @param info
	 * @param orderList
	 * @param pageInfo 
	 * @param dataProtocol
	 * @param mappingOption
	 * @return
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @version ModelWeb 2013. 05. 20 AM 11:10:59
	 * @since ModelWeb
	 */
	@Deprecated
	public univeralResult GetJSonByOrEquByJSon(String info, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		univeralResult result = new univeralResult();
		try{
			pageExpressionRelation pgInfo = getPageInfo(pageInfo);
			if(pgInfo==null) {
				pgInfo = new pageExpressionRelation();
			}
			String resultString = getJSonByOrEquByJSon(info, orderList, pgInfo, dataProtocol, mappingOption);
			if(resultString!=null){
				result.setPage(getPageInfo(pgInfo));
				result.setResultObj(resultString);
			}
			else{
				result.setErrorMsg("JSonCan't get data list.");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	@Deprecated
	public univeralResult GetJSonByOrEquByJSon(String info, orderExpressionRelation orderList, Object pageInfo, Object mappingOption) throws Throwable {
		return GetJSonByOrEquByJSon(info, orderList, pageInfo, getDefaultDataProtocol(), mappingOption);
	}
	@Deprecated
	public univeralResult GetJSonByOrEquByJSon(String info, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return GetJSonByOrEquByJSon(info, orderList, pageInfo, dataProtocol, null);
	}
	@Deprecated
	public univeralResult GetJSonByOrEquByJSon(String info, orderExpressionRelation orderList, Object pageInfo) throws Throwable {
		return GetJSonByOrEquByJSon(info, orderList, pageInfo, getDefaultDataProtocol(), null);
	}
	/**
	 * @param info
	 * @param orderList
	 * @param pageInfo
	 * @param dataProtocol
	 * @param mappingOption 
	 * @return
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @version ModelWeb 2013. 05. 20 AM 11:10:59
	 * @since ModelWeb
	 */
	@Deprecated
	public univeralResult GetHashMapByOrEquByJSon(String info, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		univeralResult result = new univeralResult();
		try{
			pageExpressionRelation pgInfo = getPageInfo(pageInfo);
			if(pgInfo==null) {
				pgInfo = new pageExpressionRelation();
			}
			HashMap resultHash = getHashMapByOrEquByJSon(info, orderList, pgInfo, dataProtocol, mappingOption);
			if(resultHash!=null){
				result.setPage(getPageInfo(pgInfo));
				result.setResultObj(resultHash);
			}
			else{
				result.setErrorMsg("Can't get Hashmap data list");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	@Deprecated
	public univeralResult GetHashMapByOrEquByJSon(String info, orderExpressionRelation orderList, Object pageInfo, Object mappingOption) throws Throwable {
		return GetHashMapByOrEquByJSon(info, orderList, pageInfo, getDefaultDataProtocol(), mappingOption);
	}
	@Deprecated
	public univeralResult GetHashMapByOrEquByJSon(String info, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return GetHashMapByOrEquByJSon(info, orderList, pageInfo, dataProtocol, null);
	}
	@Deprecated
	public univeralResult GetHashMapByOrEquByJSon(String info, orderExpressionRelation orderList, Object pageInfo) throws Throwable {
		return GetHashMapByOrEquByJSon(info, orderList, pageInfo, getDefaultDataProtocol(), null);
	}
	@Deprecated
	public univeralResult GetJSonByOrEquByItem(IItem_Model info, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		univeralResult result = new univeralResult();
		try{
			pageExpressionRelation pgInfo = getPageInfo(pageInfo);
			if(pgInfo==null) {
				pgInfo = new pageExpressionRelation();
			}
			String resultString = getJSonByOrEquByItem(info, orderList, pgInfo, dataProtocol, mappingOption);
			if(resultString!=null){
				result.setPage(getPageInfo(pgInfo));
				result.setResultObj(resultString);
			}
			else{
				result.setErrorMsg("JSonCan't get data list.");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	@Deprecated
	public univeralResult GetJSonByOrEquByItem(IItem_Model info, orderExpressionRelation orderList, Object pageInfo, Object mappingOption) throws Throwable {
		return GetJSonByOrEquByItem(info, orderList, pageInfo, getDefaultDataProtocol(), mappingOption);
	}
	@Deprecated
	public univeralResult GetJSonByOrEquByItem(IItem_Model info, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return GetJSonByOrEquByItem(info, orderList, pageInfo, dataProtocol, null);
	}
	@Deprecated
	public univeralResult GetJSonByOrEquByItem(IItem_Model info, orderExpressionRelation orderList, Object pageInfo) throws Throwable {
		return GetJSonByOrEquByItem(info, orderList, pageInfo, getDefaultDataProtocol(), null);
	}
	@Deprecated
	public univeralResult GetHashMapByOrEquByItem(IItem_Model info, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		univeralResult result = new univeralResult();
		try{
			pageExpressionRelation pgInfo = getPageInfo(pageInfo);
			if(pgInfo==null) {
				pgInfo = new pageExpressionRelation();
			}
			HashMap resultHash = getHashMapByOrEquByItem(info, orderList, pgInfo, dataProtocol, mappingOption);
			if(resultHash!=null){
				result.setPage(getPageInfo(pgInfo));
				result.setResultObj(resultHash);
			}
			else{
				result.setErrorMsg("Can't get Hashmap data list");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	@Deprecated
	public univeralResult GetHashMapByOrEquByItem(IItem_Model info, orderExpressionRelation orderList, Object pageInfo, Object mappingOption) throws Throwable {
		return GetHashMapByOrEquByItem(info, orderList, pageInfo, getDefaultDataProtocol(), mappingOption);
	}
	@Deprecated
	public univeralResult GetHashMapByOrEquByItem(IItem_Model info, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return GetHashMapByOrEquByItem(info, orderList, pageInfo, dataProtocol, null);
	}
	@Deprecated
	public univeralResult GetHashMapByOrEquByItem(IItem_Model info, orderExpressionRelation orderList, Object pageInfo) throws Throwable {
		return GetHashMapByOrEquByItem(info, orderList, pageInfo, getDefaultDataProtocol(), null);
	}
	@SuppressWarnings("unchecked")
	@Deprecated
	public univeralResult GetDataListByOrLikeByBean(Object info, orderExpressionRelation orderList, Object pageInfo, Class beanClass, Object mappingOption) {
		univeralResult result = new univeralResult();
		try{
			pageExpressionRelation pgInfo = getPageInfo(pageInfo);
			if(pgInfo==null) {
				pgInfo = new pageExpressionRelation();
			}
			Vector<Object> resultList = (Vector)getDataListByOrLikeByBean(info, orderList, pgInfo, beanClass, mappingOption);
			if(resultList!=null){
				result.setPage(getPageInfo(pgInfo));
				result.setResultObj(resultList);
			}
			else{
				result.setErrorMsg("Can't get data list.");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	@Deprecated
	public univeralResult GetDataListByOrLikeByBean(Object info, orderExpressionRelation orderList, Object pageInfo, Class beanClass) {
		return GetDataListByOrLikeByBean(info, orderList, pageInfo, beanClass, null);
	}
	@Deprecated
	public univeralResult GetXmlByOrLikeByXml(String info, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		univeralResult result = new univeralResult();
		try{
			pageExpressionRelation pgInfo = getPageInfo(pageInfo);
			if(pgInfo==null) {
				pgInfo = new pageExpressionRelation();
			}
			String resultString = getXmlByOrLikeByXml(info, orderList, pgInfo, dataProtocol, mappingOption);
			if(resultString!=null){
				result.setPage(getPageInfo(pgInfo));
				result.setResultObj(resultString);
			}
			else{
				result.setErrorMsg("Can't get XML data list.");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	@Deprecated
	public univeralResult GetXmlByOrLikeByXml(String info, orderExpressionRelation orderList, Object pageInfo, Object mappingOption) throws Throwable {
		return GetXmlByOrLikeByXml(info, orderList, pageInfo, getDefaultDataProtocol(), mappingOption);
	}
	@Deprecated
	public univeralResult GetXmlByOrLikeByXml(String info, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return GetXmlByOrLikeByXml(info, orderList, pageInfo, dataProtocol, null);
	}
	@Deprecated
	public univeralResult GetXmlByOrLikeByXml(String info, orderExpressionRelation orderList, Object pageInfo) throws Throwable {
		return GetXmlByOrLikeByXml(info, orderList, pageInfo, getDefaultDataProtocol(), null);
	}
	@Deprecated
	public univeralResult GetXmlDocByOrLikeByXml(String info, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		univeralResult result = new univeralResult();
		try{
			pageExpressionRelation pgInfo = getPageInfo(pageInfo);
			if(pgInfo==null) {
				pgInfo = new pageExpressionRelation();
			}
			Node resultNode = getXmlDocByOrLikeByXml(info, orderList, pgInfo, dataProtocol, mappingOption);
			if(resultNode!=null){
				result.setPage(getPageInfo(pgInfo));
				result.setResultObj(resultNode);
			}
			else{
				result.setErrorMsg("Can't get XML Node Data List");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	@Deprecated
	public univeralResult GetXmlDocByOrLikeByXml(String info, orderExpressionRelation orderList, Object pageInfo, Object mappingOption) throws Throwable {
		return GetXmlDocByOrLikeByXml(info, orderList, pageInfo, getDefaultDataProtocol(), mappingOption);
	}
	@Deprecated
	public univeralResult GetXmlDocByOrLikeByXml(String info, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return GetXmlDocByOrLikeByXml(info, orderList, pageInfo, dataProtocol, null);
	}
	@Deprecated
	public univeralResult GetXmlDocByOrLikeByXml(String info, orderExpressionRelation orderList, Object pageInfo) throws Throwable {
		return GetXmlDocByOrLikeByXml(info, orderList, pageInfo, getDefaultDataProtocol(), null);
	}
	@Deprecated
	public univeralResult GetXmlByOrLikeByItem(IItem_Model info, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		univeralResult result = new univeralResult();
		try{
			pageExpressionRelation pgInfo = getPageInfo(pageInfo);
			if(pgInfo==null) {
				pgInfo = new pageExpressionRelation();
			}
			String resultString = getXmlByOrLikeByItem(info, orderList, pgInfo, dataProtocol, mappingOption);
			if(resultString!=null){
				result.setPage(getPageInfo(pgInfo));
				result.setResultObj(resultString);
			}
			else{
				result.setErrorMsg("Can't get XML data list.");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	@Deprecated
	public univeralResult GetXmlByOrLikeByItem(IItem_Model info, orderExpressionRelation orderList, Object pageInfo, Object mappingOption) throws Throwable {
		return GetXmlByOrLikeByItem(info, orderList, pageInfo, null);
	}
	@Deprecated
	public univeralResult GetXmlByOrLikeByItem(IItem_Model info, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return GetXmlByOrLikeByItem(info, orderList, pageInfo, null);
	}
	@Deprecated
	public univeralResult GetXmlByOrLikeByItem(IItem_Model info, orderExpressionRelation orderList, Object pageInfo) throws Throwable {
		return GetXmlByOrLikeByItem(info, orderList, pageInfo, getDefaultDataProtocol(), null);
	}
	@Deprecated
	public univeralResult GetXmlDocByOrLikeByItem(IItem_Model info, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		univeralResult result = new univeralResult();
		try{
			pageExpressionRelation pgInfo = getPageInfo(pageInfo);
			if(pgInfo==null) {
				pgInfo = new pageExpressionRelation();
			}
			Node resultNode = getXmlDocByOrLikeByItem(info, orderList, pgInfo, dataProtocol, mappingOption);
			if(resultNode!=null){
				result.setPage(getPageInfo(pgInfo));
				result.setResultObj(resultNode);
			}
			else{
				result.setErrorMsg("Can't get XmlNode Data list.");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	@Deprecated
	public univeralResult GetXmlDocByOrLikeByItem(IItem_Model info, orderExpressionRelation orderList, Object pageInfo, Object mappingOption) throws Throwable {
		return GetXmlDocByOrLikeByItem(info, orderList, pageInfo, getDefaultDataProtocol(), mappingOption);
	}
	@Deprecated
	public univeralResult GetXmlDocByOrLikeByItem(IItem_Model info, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return GetXmlDocByOrLikeByItem(info, orderList, pageInfo, dataProtocol, null);
	}
	@Deprecated
	public univeralResult GetXmlDocByOrLikeByItem(IItem_Model info, orderExpressionRelation orderList, Object pageInfo) throws Throwable {
		return GetXmlDocByOrLikeByItem(info, orderList, pageInfo, getDefaultDataProtocol(), null);
	}
	@Deprecated
	public univeralResult GetJSonByOrLikeByJSon(String info, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		univeralResult result = new univeralResult();
		try{
			pageExpressionRelation pgInfo = getPageInfo(pageInfo);
			if(pgInfo==null) {
				pgInfo = new pageExpressionRelation();
			}
			String resultString = getJSonByOrLikeByJSon(info, orderList, pgInfo, dataProtocol, mappingOption);
			if(resultString!=null){
				result.setPage(getPageInfo(pgInfo));
				result.setResultObj(resultString);
			}
			else{
				result.setErrorMsg("Can't get JSon data list.");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	@Deprecated
	public univeralResult GetJSonByOrLikeByJSon(String info, orderExpressionRelation orderList, Object pageInfo, Object mappingOption) throws Throwable {
		return GetJSonByOrLikeByJSon(info, orderList, pageInfo, getDefaultDataProtocol(), mappingOption);
	}
	@Deprecated
	public univeralResult GetJSonByOrLikeByJSon(String info, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return GetJSonByOrLikeByJSon(info, orderList, pageInfo, dataProtocol, null);
	}
	@Deprecated
	public univeralResult GetJSonByOrLikeByJSon(String info, orderExpressionRelation orderList, Object pageInfo) throws Throwable {
		return GetJSonByOrLikeByJSon(info, orderList, pageInfo, getDefaultDataProtocol(), null);
	}
	@Deprecated
	public univeralResult GetHashMapByOrLikeByJSon(String info, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		univeralResult result = new univeralResult();
		try{
			pageExpressionRelation pgInfo = getPageInfo(pageInfo);
			if(pgInfo==null) {
				pgInfo = new pageExpressionRelation();
			}
			HashMap resultHash = getHashMapByOrLikeByJSon(info, orderList, pgInfo, dataProtocol, mappingOption);
			if(resultHash!=null){
				result.setPage(getPageInfo(pgInfo));
				result.setResultObj(resultHash);
			}
			else{
				result.setErrorMsg("Can't get Hashmap data list");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	@Deprecated
	public univeralResult GetHashMapByOrLikeByJSon(String info, orderExpressionRelation orderList, Object pageInfo, Object mappingOption) throws Throwable {
		return GetHashMapByOrLikeByJSon(info, orderList, pageInfo, getDefaultDataProtocol(), mappingOption);
	}
	@Deprecated
	public univeralResult GetHashMapByOrLikeByJSon(String info, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return GetHashMapByOrLikeByJSon(info, orderList, pageInfo, dataProtocol, null);
	}
	@Deprecated
	public univeralResult GetHashMapByOrLikeByJSon(String info, orderExpressionRelation orderList, Object pageInfo) throws Throwable {
		return GetHashMapByOrLikeByJSon(info, orderList, pageInfo, getDefaultDataProtocol(), null);
	}
	@Deprecated
	public univeralResult GetJSonByOrLikeByItem(IItem_Model info, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		univeralResult result = new univeralResult();
		try{
			pageExpressionRelation pgInfo = getPageInfo(pageInfo);
			if(pgInfo==null) {
				pgInfo = new pageExpressionRelation();
			}
			String resultString = getJSonByOrLikeByItem(info, orderList, pgInfo, dataProtocol, mappingOption);
			if(resultString!=null){
				result.setPage(getPageInfo(pgInfo));
				result.setResultObj(resultString);
			}
			else{
				result.setErrorMsg("Can't get JSon data list.");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	@Deprecated
	public univeralResult GetJSonByOrLikeByItem(IItem_Model info, orderExpressionRelation orderList, Object pageInfo, Object mappingOption) throws Throwable {
		return GetJSonByOrLikeByItem(info, orderList, pageInfo, getDefaultDataProtocol(), mappingOption);
	}
	@Deprecated
	public univeralResult GetJSonByOrLikeByItem(IItem_Model info, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return GetJSonByOrLikeByItem(info, orderList, pageInfo, dataProtocol, null);
	}
	@Deprecated
	public univeralResult GetJSonByOrLikeByItem(IItem_Model info, orderExpressionRelation orderList, Object pageInfo) throws Throwable {
		return GetJSonByOrLikeByItem(info, orderList, pageInfo, getDefaultDataProtocol(), null);
	}
	@Deprecated
	public univeralResult GetHashMapByOrLikeByItem(IItem_Model info, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		univeralResult result = new univeralResult();
		try{
			pageExpressionRelation pgInfo = getPageInfo(pageInfo);
			if(pgInfo==null) {
				pgInfo = new pageExpressionRelation();
			}
			HashMap resultHash = getHashMapByOrLikeByItem(info, orderList, pgInfo, dataProtocol, mappingOption);
			if(resultHash!=null){
				result.setPage(getPageInfo(pgInfo));
				result.setResultObj(resultHash);
			}
			else{
				result.setErrorMsg("Can't get Hashmap data list");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	@Deprecated
	public univeralResult GetHashMapByOrLikeByItem(IItem_Model info, orderExpressionRelation orderList, Object pageInfo, Object mappingOption) throws Throwable {
		return GetHashMapByOrLikeByItem(info, orderList, pageInfo, getDefaultDataProtocol(), mappingOption);
	}
	@Deprecated
	public univeralResult GetHashMapByOrLikeByItem(IItem_Model info, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return GetHashMapByOrLikeByItem(info, orderList, pageInfo, dataProtocol, null);
	}
	@Deprecated
	public univeralResult GetHashMapByOrLikeByItem(IItem_Model info, orderExpressionRelation orderList, Object pageInfo) throws Throwable {
		return GetHashMapByOrLikeByItem(info, orderList, pageInfo, getDefaultDataProtocol(), null);
	}
	@SuppressWarnings("unchecked")
	public univeralResult GetDataListByEqu(whereExpressionRelation whereList, boolean bAndOr, orderExpressionRelation orderList, Object pageInfo, Class beanClass, Object mappingOption) {
		univeralResult result = new univeralResult();
		try{
			pageExpressionRelation pgInfo = getPageInfo(pageInfo);
			if(pgInfo==null) {
				pgInfo = new pageExpressionRelation();
			}
			Vector<Object> resultList = (Vector)getDataListByEqu(whereList, bAndOr, orderList, pgInfo, beanClass, mappingOption);
			if(resultList!=null){
				result.setPage(getPageInfo(pgInfo));
				result.setResultObj(resultList);
			}
			else{
				result.setErrorMsg("Can't get data list.");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	public univeralResult GetDataListByEqu(whereExpressionRelation whereList, boolean bAndOr, orderExpressionRelation orderList, Object pageInfo, Class beanClass) {
		return GetDataListByEqu(whereList, bAndOr, orderList, pageInfo, beanClass, null);
	}
	public univeralResult GetXmlListByEqu(whereExpressionRelation whereList, boolean bAndOr, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		univeralResult result = new univeralResult();
		try{
			pageExpressionRelation pgInfo = getPageInfo(pageInfo);
			if(pgInfo==null) {
				pgInfo = new pageExpressionRelation();
			}
			String resultString = getXmlListByEqu(whereList, bAndOr, orderList, pgInfo, dataProtocol, mappingOption);
			if(resultString!=null){
				result.setPage(getPageInfo(pgInfo));
				result.setResultObj(resultString);
			}
			else{
				result.setErrorMsg("Can't get XML data list.");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	public univeralResult GetXmlListByEqu(whereExpressionRelation whereList, boolean bAndOr, orderExpressionRelation orderList, Object pageInfo, Object mappingOption) throws Throwable {
		return GetXmlListByEqu(whereList, bAndOr, orderList, pageInfo, getDefaultDataProtocol(), mappingOption);
	}
	public univeralResult GetXmlListByEqu(whereExpressionRelation whereList, boolean bAndOr, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return GetXmlListByEqu(whereList, bAndOr, orderList, pageInfo, dataProtocol, null);
	}
	public univeralResult GetXmlListByEqu(whereExpressionRelation whereList, boolean bAndOr, orderExpressionRelation orderList, Object pageInfo) throws Throwable {
		return GetXmlListByEqu(whereList, bAndOr, orderList, pageInfo, getDefaultDataProtocol(), null);
	}
	public univeralResult GetXmlDocInfoByEqu(whereExpressionRelation whereList, boolean bAndOr, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		univeralResult result = new univeralResult();
		try{
			pageExpressionRelation pgInfo = getPageInfo(pageInfo);
			if(pgInfo==null) {
				pgInfo = new pageExpressionRelation();
			}
			Node resultNode = getXmlDocInfoByEqu(whereList, bAndOr, orderList, pgInfo, dataProtocol, mappingOption);
			if(resultNode!=null){
				result.setPage(getPageInfo(pgInfo));
				result.setResultObj(resultNode);
			}
			else{
				result.setErrorMsg("Can't get XML Node Data List");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	public univeralResult GetXmlDocInfoByEqu(whereExpressionRelation whereList, boolean bAndOr, orderExpressionRelation orderList, Object pageInfo, Object mappingOption) throws Throwable {
		return GetXmlDocInfoByEqu(whereList, bAndOr, orderList, pageInfo, getDefaultDataProtocol(), mappingOption);
	}
	public univeralResult GetXmlDocInfoByEqu(whereExpressionRelation whereList, boolean bAndOr, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return GetXmlDocInfoByEqu(whereList, bAndOr, orderList, pageInfo, dataProtocol, null);
	}
	public univeralResult GetXmlDocInfoByEqu(whereExpressionRelation whereList, boolean bAndOr, orderExpressionRelation orderList, Object pageInfo) throws Throwable {
		return GetXmlDocInfoByEqu(whereList, bAndOr, orderList, pageInfo, getDefaultDataProtocol(), null);
	}
	public univeralResult GetJSonListByEqu(whereExpressionRelation whereList, boolean bAndOr, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		univeralResult result = new univeralResult();
		try{
			pageExpressionRelation pgInfo = getPageInfo(pageInfo);
			if(pgInfo==null) {
				pgInfo = new pageExpressionRelation();
			}
			String resultString = getJSonListByEqu(whereList, bAndOr, orderList, pgInfo, dataProtocol, mappingOption);
			if(resultString!=null){
				result.setPage(getPageInfo(pgInfo));
				result.setResultObj(resultString);
			}
			else{
				result.setErrorMsg("JSon error .");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	public univeralResult GetJSonListByEqu(whereExpressionRelation whereList, boolean bAndOr, orderExpressionRelation orderList, Object pageInfo, Object mappingOption) throws Throwable {
		return GetJSonListByEqu(whereList, bAndOr, orderList, pageInfo, getDefaultDataProtocol(), mappingOption);
	}
	public univeralResult GetJSonListByEqu(whereExpressionRelation whereList, boolean bAndOr, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return GetJSonListByEqu(whereList, bAndOr, orderList, pageInfo, dataProtocol, null);
	}
	public univeralResult GetJSonListByEqu(whereExpressionRelation whereList, boolean bAndOr, orderExpressionRelation orderList, Object pageInfo) throws Throwable {
		return GetJSonListByEqu(whereList, bAndOr, orderList, pageInfo, getDefaultDataProtocol(), null);
	}
	public univeralResult GetHashMapInfoByEqu(whereExpressionRelation whereList, boolean bAndOr, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		univeralResult result = new univeralResult();
		try{
			pageExpressionRelation pgInfo = getPageInfo(pageInfo);
			if(pgInfo==null) {
				pgInfo = new pageExpressionRelation();
			}
			HashMap resultHash = getHashMapInfoByEqu(whereList, bAndOr, orderList, pgInfo, dataProtocol, mappingOption);
			if(resultHash!=null){
				result.setPage(getPageInfo(pgInfo));
				result.setResultObj(resultHash);
			}
			else{
				result.setErrorMsg("Can't Get Hashmap list.");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	public univeralResult GetHashMapInfoByEqu(whereExpressionRelation whereList, boolean bAndOr, orderExpressionRelation orderList, Object pageInfo, Object mappingOption) throws Throwable {
		return GetHashMapInfoByEqu(whereList, bAndOr, orderList, pageInfo, getDefaultDataProtocol(), mappingOption);
	}
	public univeralResult GetHashMapInfoByEqu(whereExpressionRelation whereList, boolean bAndOr, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return GetHashMapInfoByEqu(whereList, bAndOr, orderList, pageInfo, dataProtocol, null);
	}
	public univeralResult GetHashMapInfoByEqu(whereExpressionRelation whereList, boolean bAndOr, orderExpressionRelation orderList, Object pageInfo) throws Throwable {
		return GetHashMapInfoByEqu(whereList, bAndOr, orderList, pageInfo, getDefaultDataProtocol(), null);
	}
	/**
	 * @param selectList 
	 * @param likeList
	 * @param bAndOr
	 * @param orderList 
	 * @param pageInfo 
	 * @param beanClass
	 * @param mappingOption 
	 * @return
	 * @throws Throwable
	 */
	@SuppressWarnings("unchecked")
	public univeralResult GetDataListByLike(whereExpressionRelation likeList, boolean bAndOr, orderExpressionRelation orderList, Object pageInfo, Class beanClass, Object mappingOption) {
		univeralResult result = new univeralResult();
		try{
			pageExpressionRelation pgInfo = getPageInfo(pageInfo);
			if(pgInfo==null) {
				pgInfo = new pageExpressionRelation();
			}
			Vector<Object> resultList = (Vector)getDataListByLike(likeList, bAndOr, orderList, pgInfo, beanClass, mappingOption);
			if(resultList!=null){
				result.setPage(getPageInfo(pgInfo));
				result.setResultObj(resultList);
			}
			else{
				result.setErrorMsg("Can't get data list.");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	public univeralResult GetDataListByLike(whereExpressionRelation likeList, boolean bAndOr, orderExpressionRelation orderList, Object pageInfo, Class beanClass) {
		return GetDataListByLike(likeList, bAndOr, orderList, pageInfo, beanClass, null);
	}
	/**
	 * @param likeList
	 * @param bAndOr
	 * @param orderList
	 * @param pageInfo
	 * @param dataProtocol 
	 * @param mappingOption 
	 * @return
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @version ModelWeb 2013. 05. 20 AM 11:12:16
	 * @since ModelWeb
	 */
	public univeralResult GetXmlListByLike(whereExpressionRelation likeList, boolean bAndOr, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		univeralResult result = new univeralResult();
		try{
			pageExpressionRelation pgInfo = getPageInfo(pageInfo);
			if(pgInfo==null) {
				pgInfo = new pageExpressionRelation();
			}
			String resultString = getXmlListByLike(likeList, bAndOr, orderList, pgInfo, dataProtocol, mappingOption);
			if(resultString!=null){
				result.setPage(getPageInfo(pgInfo));
				result.setResultObj(resultString);
			}
			else{
				result.setErrorMsg("Can't get Xml Data list.");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	public univeralResult GetXmlListByLike(whereExpressionRelation likeList, boolean bAndOr, orderExpressionRelation orderList, Object pageInfo, Object mappingOption) throws Throwable {
		return GetXmlListByLike(likeList, bAndOr, orderList, pageInfo, getDefaultDataProtocol(), mappingOption);
	}
	public univeralResult GetXmlListByLike(whereExpressionRelation likeList, boolean bAndOr, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return GetXmlListByLike(likeList, bAndOr, orderList, pageInfo, dataProtocol, null);
	}
	public univeralResult GetXmlListByLike(whereExpressionRelation likeList, boolean bAndOr, orderExpressionRelation orderList, Object pageInfo) throws Throwable {
		return GetXmlListByLike(likeList, bAndOr, orderList, pageInfo, getDefaultDataProtocol(), null);
	}
	/**
	 * @param likeList
	 * @param bAndOr
	 * @param orderList
	 * @param pageInfo
	 * @param dataProtocol
	 * @param mappingOption
	 * @return
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @version ModelWeb 2013. 05. 20 AM 11:12:16
	 * @since ModelWeb
	 */
	public univeralResult GetXmlDocInfoByLike(whereExpressionRelation likeList, boolean bAndOr, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		univeralResult result = new univeralResult();
		try{
			pageExpressionRelation pgInfo = getPageInfo(pageInfo);
			if(pgInfo==null) {
				pgInfo = new pageExpressionRelation();
			}
			Node resultNode = getXmlDocInfoByLike(likeList, bAndOr, orderList, pgInfo, dataProtocol, mappingOption);
			if(resultNode!=null){
				result.setPage(getPageInfo(pgInfo));
				result.setResultObj(resultNode);
			}
			else{
				result.setErrorMsg("Can't Xml Node Data List");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	public univeralResult GetXmlDocInfoByLike(whereExpressionRelation likeList, boolean bAndOr, orderExpressionRelation orderList, Object pageInfo, Object mappingOption) throws Throwable {
		return GetXmlDocInfoByLike(likeList, bAndOr, orderList, pageInfo, getDefaultDataProtocol(), mappingOption);
	}
	public univeralResult GetXmlDocInfoByLike(whereExpressionRelation likeList, boolean bAndOr, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return GetXmlDocInfoByLike(likeList, bAndOr, orderList, pageInfo, dataProtocol, null);
	}
	public univeralResult GetXmlDocInfoByLike(whereExpressionRelation likeList, boolean bAndOr, orderExpressionRelation orderList, Object pageInfo) throws Throwable {
		return GetXmlDocInfoByLike(likeList, bAndOr, orderList, pageInfo, getDefaultDataProtocol(), null);
	}
	public univeralResult GetJSonListByLike(whereExpressionRelation likeList, boolean bAndOr, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		univeralResult result = new univeralResult();
		try{
			pageExpressionRelation pgInfo = getPageInfo(pageInfo);
			if(pgInfo==null) {
				pgInfo = new pageExpressionRelation();
			}
			String resultString = getJSonListByLike(likeList, bAndOr, orderList, pgInfo, dataProtocol, mappingOption);
			if(resultString!=null){
				result.setPage(getPageInfo(pgInfo));
				result.setResultObj(resultString);
			}
			else{
				result.setErrorMsg("Can't JSon DataList.");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	public univeralResult GetJSonListByLike(whereExpressionRelation likeList, boolean bAndOr, orderExpressionRelation orderList, Object pageInfo, Object mappingOption) throws Throwable {
		return GetJSonListByLike(likeList, bAndOr, orderList, pageInfo, getDefaultDataProtocol(), mappingOption);
	}
	public univeralResult GetJSonListByLike(whereExpressionRelation likeList, boolean bAndOr, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return GetJSonListByLike(likeList, bAndOr, orderList, pageInfo, dataProtocol, null);
	}
	public univeralResult GetJSonListByLike(whereExpressionRelation likeList, boolean bAndOr, orderExpressionRelation orderList, Object pageInfo) throws Throwable {
		return GetJSonListByLike(likeList, bAndOr, orderList, pageInfo, getDefaultDataProtocol(), null);
	}
	public univeralResult GetHashMapInfoByLike(whereExpressionRelation likeList, boolean bAndOr, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		univeralResult result = new univeralResult();
		try{
			pageExpressionRelation pgInfo = getPageInfo(pageInfo);
			if(pgInfo==null) {
				pgInfo = new pageExpressionRelation();
			}
			HashMap resultHash = getHashMapInfoByLike(likeList, bAndOr, orderList, pgInfo, dataProtocol, mappingOption);
			if(resultHash!=null){
				result.setPage(getPageInfo(pgInfo));
				result.setResultObj(resultHash);
			}
			else{
				result.setErrorMsg("Can't get Hashmap.");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	public univeralResult GetHashMapInfoByLike(whereExpressionRelation likeList, boolean bAndOr, orderExpressionRelation orderList, Object pageInfo, Object mappingOption) throws Throwable {
		return GetHashMapInfoByLike(likeList, bAndOr, orderList, pageInfo, getDefaultDataProtocol(), mappingOption);
	}
	public univeralResult GetHashMapInfoByLike(whereExpressionRelation likeList, boolean bAndOr, orderExpressionRelation orderList, Object pageInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return GetHashMapInfoByLike(likeList, bAndOr, orderList, pageInfo, dataProtocol, null);
	}
	public univeralResult GetHashMapInfoByLike(whereExpressionRelation likeList, boolean bAndOr, orderExpressionRelation orderList, Object pageInfo) throws Throwable {
		return GetHashMapInfoByLike(likeList, bAndOr, orderList, pageInfo, getDefaultDataProtocol(), null);
	}
	/*******************************delete***********************/
	public univeralResult DeleteByEqu(whereExpressionRelation whereList, boolean bAndOr) {
		univeralResult result = new univeralResult();
		try{
			int resultNum = deleteByEqu(whereList, bAndOr);
			if(resultNum>=0){
				result.setResultObj(resultNum);
			}
			else{
				result.setErrorMsg("Can't get data list.");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	public univeralResult DeleteByInfo(whereExpressionRelation whereList) {
		univeralResult result = new univeralResult();
		try{
			int resultNum = deleteByInfo(whereList);
			if(resultNum>=0){
				result.setResultObj(resultNum);
			}
			else{
				result.setErrorMsg("Can't remove.");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	public univeralResult DeleteByLike(whereExpressionRelation likeList, boolean bAndOr) {
		univeralResult result = new univeralResult();
		try{
			int resultNum = deleteByLike(likeList, bAndOr);
			if(resultNum>=0){
				result.setResultObj(resultNum);
			}
			else{
				result.setErrorMsg("Can't remove.");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	/*******************************set null***********************/
	public univeralResult SetNullByEqu(setNullExpressionRelation setNullList, whereExpressionRelation whereList, boolean bAndOr) {
		univeralResult result = new univeralResult();
		try{
			int resultNum = setNullByEqu(setNullList, whereList, bAndOr);
			if(resultNum>=0){
				result.setResultObj(resultNum);
			}
			else{
				result.setErrorMsg("NULL setting unabled.");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	public univeralResult SetNullByInfo(setNullExpressionRelation setNullList, whereExpressionRelation whereExpression) {
		univeralResult result = new univeralResult();
		try{
			int resultNum = setNullByInfo(setNullList, whereExpression);
			if(resultNum>=0){
				result.setResultObj(resultNum);
			}
			else{
				result.setErrorMsg("NULL setting unabled.");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	public univeralResult SetNullByLike(setNullExpressionRelation setNullList, whereExpressionRelation likeList, boolean bAndOr) {
		univeralResult result = new univeralResult();
		try{
			int resultNum = setNullByLike(setNullList, likeList, bAndOr);
			if(resultNum>=0){
				result.setResultObj(resultNum);
			}
			else{
				result.setErrorMsg("NULL setting unabled.");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	/*******************************update***********************/
	public univeralResult UpdateByEqu(updateExpressionRelation setUpdateList, whereExpressionRelation whereList, boolean bAndOr) {
		univeralResult result = new univeralResult();
		try{
			int resultNum = updateByEqu(setUpdateList, whereList, bAndOr);
			if(resultNum>=0){
				result.setResultObj(resultNum);
			}
			else{
				result.setErrorMsg("Unable to change database.");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	public univeralResult UpdateByInfo(updateExpressionRelation setUpdateList, whereExpressionRelation whereList) {
		univeralResult result = new univeralResult();
		try{
			int resultNum = updateByInfo(setUpdateList, whereList);
			if(resultNum>=0){
				result.setResultObj(resultNum);
			}
			else{
				result.setErrorMsg("Unable to change database.");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	public univeralResult UpdateByLike(updateExpressionRelation setUpdateList, whereExpressionRelation likeList, boolean bAndOr) {
		univeralResult result = new univeralResult();
		try{
			int resultNum = updateByLike(setUpdateList, likeList, bAndOr);
			if(resultNum>=0){
				result.setResultObj(resultNum);
			}
			else{
				result.setErrorMsg("Unable to change.");
			}
		}
		catch(TruegardenerException ex){ result.setErrorMsg(ex); result.setResultObj(ex.getErrorCode()); }	
		catch(Throwable ex){ result.setErrorMsg(ex); }
		return result;
	}
	
	/**********************over coding TruegardenerRule**************************/
	protected boolean IsValidEmail(IItem_Model item, int nFieldIndex, int nMaxLen) throws Throwable{
		return m_ruleClient.IsValidEmail(item, nFieldIndex, nMaxLen);
	}
	protected boolean IsValidField(IItem_Model item, int nFieldIndex, int nMaxLen) throws Throwable{
		return m_ruleClient.IsValidField(item, nFieldIndex, nMaxLen);
	}
	protected boolean IsValidField(IItem_Model item, int nFieldIndex, int nMaxLen, int nMinLen) throws Throwable{
		return m_ruleClient.IsValidField(item, nFieldIndex, nMaxLen, nMinLen);
	}
	protected boolean IsValidField(IItem_Model item, int nFieldIndex, Calendar dtMaxDate, boolean bInitHMS) throws Throwable{
		return m_ruleClient.IsValidField(item, nFieldIndex, dtMaxDate, bInitHMS);
	}

	protected boolean IsValidField(IItem_Model item, int nFieldIndex, Calendar dtMaxDate, Calendar dtMinDate, boolean bInitHMS) throws Throwable {
    	return m_ruleClient.IsValidField(item, nFieldIndex, dtMaxDate, dtMinDate, bInitHMS);
	}
	/**********************implements mapping**************************/
	@Override
	protected ITruegardenerRule getRuleClient() {
		return m_ruleClient;
	}
	@Override
	protected BaseDA getDAClient() {
		return m_daClient;
	}
	public long insertItem(IItem_Model item) throws Throwable {
		return m_ruleClient.insertItem(item);
	}
	public boolean updateItem(IItem_Model item) throws Throwable {
		return m_ruleClient.updateItem(item);
	}
	public boolean CheckValidate(IItem_Model item) throws Throwable {
		return m_ruleClient.CheckValidate(item);
	}
	public boolean onCheckValidate(IItem_Model item) throws Throwable {
		return m_ruleClient.onCheckValidate(item);
	}
	public boolean onDeleteItem(long nNo) throws Throwable {
		return m_ruleClient.onDeleteItem(nNo);
	}
	public long onInsertItem(IItem_Model item) throws Throwable {
		return m_ruleClient.onInsertItem(item);
	}
	public boolean onUpdateItem(IItem_Model item) throws Throwable {
		return m_ruleClient.onUpdateItem(item);
	}
	public Object getPageInfo(pageExpressionRelation pageInfo) {
		return m_ruleClient.getPageInfo(pageInfo);
	}
	public pageExpressionRelation getPageInfo(Object pageInfo) {
		return m_ruleClient.getPageInfo(pageInfo);
	}
	/**********************extends facade**************************/
}
