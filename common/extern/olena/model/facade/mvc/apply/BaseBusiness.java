package common.extern.olena.model.facade.mvc.apply;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Vector;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;

import common.extern.olena.model.dataaccess.daFatherModel;
import common.extern.olena.model.dataaccess.mvc.apply.BaseDA;
import common.extern.olena.model.dataaccess.mvc.base.IItem_Model;
import common.extern.olena.model.dataaccess.util.core.query.queryProducts.executeExpressionRelation;
import common.extern.olena.model.dataaccess.util.core.query.queryProducts.orderExpressionRelation;
import common.extern.olena.model.dataaccess.util.core.query.queryProducts.pageExpressionRelation;
import common.extern.olena.model.dataaccess.util.core.query.queryProducts.selectExpressionRelation;
import common.extern.olena.model.dataaccess.util.core.query.queryProducts.setNullExpressionRelation;
import common.extern.olena.model.dataaccess.util.core.query.queryProducts.updateExpressionRelation;
import common.extern.olena.model.dataaccess.util.core.query.queryProducts.whereExpressionRelation;
import common.extern.olena.model.error.TruegardenerException;
import common.extern.olena.model.facade.facadeFatherModel;
import common.extern.olena.model.facade.mvc.base.IFa_Model;
import common.extern.olena.model.infos.dataTransport.key_WebDataProtocol_Model;
import common.extern.olena.model.infos.universalResult.infoUniversalReturnCode;
import common.extern.olena.model.rule.middleFatherModel;
import common.extern.olena.model.rule.mvc.apply.mapping_Model;
import common.extern.olena.model.rule.mvc.apply.inter.IBaseRule;
import common.extern.olena.model.rule.mvc.apply.inter.IMapping_Model;
import common.extern.olena.model.systemframework.Applicationlog;
import common.extern.utils.Json.TruegardenerJSONWriter;
/**
 * @author Olena.Zagreba 
 * @version  2013-03-30 10:14:10
 */
public abstract class BaseBusiness extends mapping_Model implements IFa_Model, IMapping_Model
{
	public BaseBusiness(facadeFatherModel parentFacade, middleFatherModel parentMiddle, daFatherModel parentDA) {
		super();
		m_faModel = new fa_Model(parentFacade, parentMiddle, parentDA);
	}
	public BaseBusiness(facadeFatherModel parentFacade, middleFatherModel middleFatherModel, daFatherModel parentDA,
			IItem_Model itemModel) {
		super(itemModel);
		m_faModel = new fa_Model(parentFacade, middleFatherModel, parentDA);
	}
	public BaseBusiness(facadeFatherModel parentFacade, middleFatherModel middleFatherModel, daFatherModel parentDA,
			IItem_Model itemModel,
			String strXmlRoot, String strXmlNode, String strNodeCount, String strXmlException) {
		super(itemModel, strXmlRoot, strXmlNode, strNodeCount, strXmlException);
		m_faModel = new fa_Model(parentFacade, middleFatherModel, parentDA);
	}
	/** facadde모델 */
	private fa_Model m_faModel = null;

	public void setError(String strErrorMsg) {
    	Applicationlog.LogTruegardenerError(strErrorMsg);
    }
	public daFatherModel getDAModel() {
		return m_faModel.getDAModel();
	}
	public facadeFatherModel getFacadeModel() {
		return m_faModel.getFacadeModel();
	}
	public middleFatherModel getMiddleModel() {
		return m_faModel.getMiddleModel();
	}
	/**
	 * Bean 대면부의 클라스를 얻기
	 *   insertData(...) 나 updataData(..)등에서 호출된다.
	 * 	 새끼들에서 덧 선언된다.
	 * 
	 * @author Olena.Zagreba 
	 * @version  2013-03-30 10:14:11
	 * @throws Throwable 
	 */
	protected abstract IBaseRule getRuleClient();
	/**
	 * Bean 대면부의 클라스를 얻기
	 *   insertData(...) 나 updataData(..)등에서 호출된다.
	 * 	 새끼들에서 덧 선언된다.
	 * 
	 * @author Olena.Zagreba 
	 * @version  2013-03-30 10:14:11
	 * @throws Throwable 
	 */
	protected abstract BaseDA getDAClient();
	/********************************public***********************/
	/********************************public***********************/
	/********************************public***********************/
	/**
	 * 자료추가함수
	 * 추가시 자료검사를 위한 Rule층의 insertData함수를 호출한다.
	 * @param data 추가할 표현층의 자료표객체 
	 * @return 추가된 정보의 주아이디
	 * @throws Throwable
	 * @author Olena.Zagreba 
	 * @version  2013-03-31 10:14:10
	 */
	public long insertData(Object data, Object mappingOption)throws Throwable {
		if(data == null) { throw new TruegardenerException("삽입할 자료가 <null> 입니다. - BusinessFacade층  insertData()에서...", infoUniversalReturnCode.RESULT_ERROR_NULL); }
		return (long)getRuleClient().insertData(data, mappingOption);
	}
	public long insertData(Object data)throws Throwable {
		return insertData(data, null);
	}
	/**
	 * Xml자료추가함수
	 * 추가시 자료검사를 위한 Rule층의 insertXml함수를 호출한다.
	 * @param strXml
	 * @param dataProtocol 자료변환시 통신규약
	 * @param mappingOption 자료변환시 옵션변수
	 * @return
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @version ModelWeb 2013. 05. 11 AM 8:36:51
	 * @since ModelWeb
	 */
	public long insertXml(String strXml, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		if(strXml== null) { throw new TruegardenerException("삽입할 자료가 <null> 입니다. - BusinessFacade층  insertData()에서...", infoUniversalReturnCode.RESULT_ERROR_NULL); }
		return (long)getRuleClient().insertXml(strXml, dataProtocol, mappingOption);
	}
	public long insertXml(String strXml, Object mappingOption) throws Throwable {
		return insertXml(strXml, getDefaultDataProtocol(), mappingOption);
	}
	public long insertXml(String strXml) throws Throwable {
		return insertXml(strXml, null);
	}
	/**
	 * Xml마디자료추가함수
	 * 추가시 자료검사를 위한 Rule층의 insertXml함수를 호출한다.
	 * @param nodeXml
	 * @param dataProtocol 자료변환시 통신규약
	 * @param mappingOption 자료변환시 옵션변수
	 * @return
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @version ModelWeb 2013. 05. 11 AM 8:37:02
	 * @since ModelWeb
	 */
	public long insertXml(Node nodeXml, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		if(nodeXml== null) { throw new TruegardenerException("삽입할 자료가 <null> 입니다. - BusinessFacade층  insertData()에서...", infoUniversalReturnCode.RESULT_ERROR_NULL); }
		return (long)getRuleClient().insertXml(nodeXml, dataProtocol, mappingOption);
	}
	public long insertXml(Node nodeXml, Object mappingOption) throws Throwable {
		return insertXml(nodeXml, getDefaultDataProtocol(), mappingOption);
	}
	public long insertXml(Node nodeXml) throws Throwable {
		return insertXml(nodeXml, null);
	}
	/**
	 * JSon자료추가함수
	 * 추가시 자료검사를 위한 Rule층의 insertJSon함수를 호출한다.
	 * @param strJSon
	 * @param mappingOption 자료변환시 옵션변수
	 * @return
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @version ModelWeb 2013. 05. 11 AM 8:37:08
	 * @since ModelWeb
	 */
	public long insertJSon(String strJSon, Object mappingOption) throws Throwable {
		if(strJSon== null) { throw new TruegardenerException("삽입할 자료가 <null> 입니다. - BusinessFacade층  insertData()에서...", infoUniversalReturnCode.RESULT_ERROR_NULL); }
		return (long)getRuleClient().insertJSon(strJSon, mappingOption);
	}
	public long insertJSon(String strJSon) throws Throwable {
		return insertJSon(strJSon, null);
	}
	/**
	 * JSon하쉬맵자료추가함수
	 * 추가시 자료검사를 위한 Rule층의 insertJSon함수를 호출한다.
	 * @param hashInfo
	 * @param mappingOption 자료변환시 옵션변수
	 * @return
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @version ModelWeb 2013. 05. 11 AM 8:37:12
	 * @since ModelWeb
	 */
	public long insertHashMap(HashMap hashInfo, Object mappingOption) throws Throwable {
		if(hashInfo== null) { throw new TruegardenerException("삽입할 자료가 <null> 입니다. - BusinessFacade층  insertData()에서...", infoUniversalReturnCode.RESULT_ERROR_NULL); }
		return (long)getRuleClient().insertHashMap(hashInfo, mappingOption);
	}
	public long insertHashMap(HashMap hashInfo) throws Throwable {
		return insertHashMap(hashInfo, null);
	}
	/**
	 * 자료변경함수
	 * 변경시 자료검사를 위한 Rule층의 updateData 호출한다.
	 * @param data 변경할 표현층의 자료표객체
	 * @param mappingOption 자료변환시 옵션변수 
	 * @return 변경된 정보의 개수 = 1
	 * @throws Throwable
	 * @author Olena.Zagreba 
	 * @version  2013-03-31 10:14:10
	 */
	public boolean updateData(Object data, Object mappingOption)throws Throwable {
		if(data == null) { throw new TruegardenerException("갱신할 자료가 <null>입니다. - BusinessFacade층  updateData()에서..."); }
		return getRuleClient().updateData(data, mappingOption);
	}
	public boolean updateData(Object data)throws Throwable {
		return updateData(data, null);
	}
	/**
	 * Xml자료변경함수
	 * 변경시 자료검사를 위한 Rule층의 updateXml 호출한다.
	 * @param strXml
	 * @param dataProtocol 자료변환시 통신규약
	 * @param mappingOption 자료변환시 옵션변수
	 * @return
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @version ModelWeb 2013. 05. 11 AM 8:37:20
	 * @since ModelWeb
	 */
	public boolean updateXml(String strXml, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		if(strXml == null) { throw new TruegardenerException("갱신할 자료가 <null>입니다. - BusinessFacade층  updateData()에서..."); }
		return getRuleClient().updateXml(strXml, dataProtocol, mappingOption);
	}
	public boolean updateXml(String strXml, Object mappingOption) throws Throwable {
		return updateXml(strXml, getDefaultDataProtocol(), mappingOption);
	}
	public boolean updateXml(String strXml) throws Throwable {
		return updateXml(strXml, null);
	}
	/**
	 * Xml마디자료변경함수
	 * 변경시 자료검사를 위한 Rule층의 updateXml 호출한다.
	 * @param nodeXml
	 * @param dataProtocol 자료변환시 통신규약
	 * @param mappingOption 자료변환시 옵션변수
	 * @return
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @version ModelWeb 2013. 05. 11 AM 8:37:23
	 * @since ModelWeb
	 */
	public boolean updateXml(Node nodeXml, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		if(nodeXml == null) { throw new TruegardenerException("갱신할 자료가 <null>입니다. - BusinessFacade층  updateData()에서..."); }
		return getRuleClient().updateXml(nodeXml, dataProtocol, mappingOption);
	}
	public boolean updateXml(Node nodeXml, Object mappingOption) throws Throwable {
		return updateXml(nodeXml, getDefaultDataProtocol(), mappingOption);
	}
	public boolean updateXml(Node nodeXml) throws Throwable {
		return updateXml(nodeXml, null);
	}
	/**
	 * JSon자료변경함수
	 * 변경시 자료검사를 위한 Rule층의 insertXml함수를 호출한다.
	 * @param strJSon
	 * @param mappingOption 자료변환시 옵션변수
	 * @return
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @version ModelWeb 2013. 05. 11 AM 8:37:25
	 * @since ModelWeb
	 */
	public boolean updateJSon(String strJSon, Object mappingOption) throws Throwable {
		if(strJSon == null) { throw new TruegardenerException("갱신할 자료가 <null>입니다. - BusinessFacade층  updateData()에서..."); }
		return getRuleClient().updateJSon(strJSon, mappingOption);
	}
	public boolean updateJSon(String strJSon) throws Throwable {
		return updateJSon(strJSon, null);
	}
	/**
	 * JSon하쉬맵자료변경함수
	 * 변경시 자료검사를 위한 Rule층의 updateJSon함수를 호출한다.
	 * @param hashInfo JSon하쉬맵
	 * @param mappingOption 자료변환시 옵션변수
	 * @return
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @version ModelWeb 2013. 05. 11 AM 8:37:27
	 * @since ModelWeb
	 */
	public boolean updateHashMap(HashMap hashInfo, Object mappingOption) throws Throwable {
		if(hashInfo == null) { throw new TruegardenerException("갱신할 자료가 <null>입니다. - BusinessFacade층  updateData()에서..."); }
		return getRuleClient().updateHashMap(hashInfo, mappingOption);
	}
	public boolean updateHashMap(HashMap hashInfo) throws Throwable {
		return updateHashMap(hashInfo, null);
	}
	/**
	 * 자료삭제함수
	 * @param nNo 삭제할 자료표의 주아이디 
	 * @return 성공: true ? 실패: false
	 * @throws Throwable
	 * @author Olena.Zagreba 
	 * @version  2013-03-31 10:14:10
	 */
	public boolean deleteData(long nNo)throws Throwable {
		return getRuleClient().deleteData(nNo);
	}
	/**
	 * 개별자료얻어내기
	 * @param nNo 등록번호
	 * @param beanClass 얻어낼 bean형식의 자료클라스객체
	 * @param mappingOption 자료변환시 옵션변수
	 * @return
	 * @throws Throwable
	 */
	public Object getData(long nNo, Class beanClass, Object mappingOption)throws Throwable { 
		return XgetDataFromItem(getDAClient().get_PrimaryInfo(null, nNo), beanClass, 0, mappingOption);
	}
	public Object getData(long nNo, Class beanClass)throws Throwable { 
		return getData(nNo, beanClass, null);
	}
	public void setData(long nNo, Object objBean)throws Throwable { 
		XsetDataFromItem(objBean, getDAClient().get_PrimaryInfo(null, nNo));
	}
	/**
	 * 모든 자료얻어내기
	 * @param orderList 정렬하는 관계객체
	 * @param pgRelation 페지정보
	 * @param beanClass bean형식의 자료클라스객체
	 * @param mappingOption 자료변환시 옵션변수
	 * @return
	 * @throws Throwable
	 */
	@SuppressWarnings("unchecked")
	public Vector<Object> getDataListByPage(orderExpressionRelation orderList, pageExpressionRelation pgRelation, Class beanClass, Object mappingOption)throws Throwable {
		return (Vector)XgetDataListFromItemList(getDAClient().get_Info(null, null, orderList, pgRelation), beanClass, mappingOption);
	}
	public Vector<Object> getDataListByPage(orderExpressionRelation orderList, pageExpressionRelation pgRelation, Class beanClass)throws Throwable {
		return getDataListByPage(orderList, pgRelation, beanClass, null);
	}
	/**
	 * 모든 자료를 xml정보로 얻어내기
	 * @param orderList 정렬하는 관계객체
	 * @param pgRelation 페지정보
	 * @param dataProtocol 자료변환시 통신규약
	 * @param mappingOption 자료변환시 옵션변수
	 * @return 자료표의 Entity객체들의 묶음
	 * @throws Throwable
	 * @author Olena.Zagreba 
	 * @version  2013-03-31 10:14:10
	 */
	public String getXmlListByPage(orderExpressionRelation orderList, pageExpressionRelation pgRelation, key_WebDataProtocol_Model dataProtocol, Object mappingOption)throws Throwable {
		String result = null;
		Vector<IItem_Model> lstItemInfos = getDAClient().get_Info(null, null, orderList, pgRelation);
		if(lstItemInfos != null) {
			result = XgetXmlFromItemList(lstItemInfos, dataProtocol, mappingOption);
		}
		return result;
	}
	public String getXmlListByPage(orderExpressionRelation orderList, pageExpressionRelation pgRelation, Object mappingOption)throws Throwable {
		return getXmlListByPage(orderList, pgRelation, getDefaultDataProtocol(), mappingOption);
	}
	public String getXmlListByPage(orderExpressionRelation orderList, pageExpressionRelation pgRelation, key_WebDataProtocol_Model dataProtocol)throws Throwable {
		return getXmlListByPage(orderList, pgRelation, dataProtocol, null);
	}
	public String getXmlListByPage(orderExpressionRelation orderList, pageExpressionRelation pgRelation)throws Throwable {
		return getXmlListByPage(orderList, pgRelation, getDefaultDataProtocol(), null);
	}
	/**
	 * 모든 자료를 xml정보로 얻어내기
	 * @param orderList 정렬하는 관계객체
	 * @param pgRelation 페지정보
	 * @param dataProtocol 자료변환시 통신규약
	 * @param mappingOption 자료변환시 옵션변수
	 * @return 자료표의 Entity객체들의 묶음
	 * @throws Throwable
	 * @author Olena.Zagreba 
	 * @version  2013-03-31 10:14:10
	 */
	public Node getXmlDocByPage(orderExpressionRelation orderList, pageExpressionRelation pgRelation, key_WebDataProtocol_Model dataProtocol, Object mappingOption)throws Throwable {
		Node result = null;
		Vector<IItem_Model> lstItemInfos = getDAClient().get_Info(null, null, orderList, pgRelation);
		if(lstItemInfos != null) {
			result = XgetXmlDocFromItemList(lstItemInfos, dataProtocol, mappingOption);
		}
		return result;
	}
	public Node getXmlDocByPage(orderExpressionRelation orderList, pageExpressionRelation pgRelation, Object mappingOption)throws Throwable {
		return getXmlDocByPage(orderList, pgRelation, getDefaultDataProtocol(), mappingOption);
	}
	public Node getXmlDocByPage(orderExpressionRelation orderList, pageExpressionRelation pgRelation, key_WebDataProtocol_Model dataProtocol)throws Throwable {
		return getXmlDocByPage(orderList, pgRelation, dataProtocol, null);
	}
	public Node getXmlDocByPage(orderExpressionRelation orderList, pageExpressionRelation pgRelation)throws Throwable {
		return getXmlDocByPage(orderList, pgRelation, getDefaultDataProtocol(), null);
	}
	/**
	 * 모든 자료를 JSon정보로 얻어내기
	 * @param orderList 정렬하는 관계객체
	 * @param pgRelation 페지정보
	 * @param dataProtocol 자료변환시 통신규약
	 * @param mappingOption 자료변환시 옵션변수
	 * @return 자료표의 Entity객체들의 묶음
	 * @throws Throwable
	 * @author Olena.Zagreba 
	 * @version  2013-03-31 10:14:10
	 */
	public String getJSonListByPage(orderExpressionRelation orderList, pageExpressionRelation pgRelation, key_WebDataProtocol_Model dataProtocol, Object mappingOption)throws Throwable {
		String result = null;
		Vector<IItem_Model> listItemInfos = getDAClient().get_Info(null, null, orderList, pgRelation);
		if(listItemInfos != null) {
			result = XgetJSonFromItemList(listItemInfos, dataProtocol, mappingOption);
		}
		return result;
	}
	public String getJSonListByPage(orderExpressionRelation orderList, pageExpressionRelation pgRelation, Object mappingOption)throws Throwable {
		return getJSonListByPage(orderList, pgRelation, getDefaultDataProtocol(), mappingOption);
	}
	public String getJSonListByPage(orderExpressionRelation orderList, pageExpressionRelation pgRelation, key_WebDataProtocol_Model dataProtocol)throws Throwable {
		return getJSonListByPage(orderList, pgRelation, dataProtocol, null);
	}
	public String getJSonListByPage(orderExpressionRelation orderList, pageExpressionRelation pgRelation)throws Throwable {
		return getJSonListByPage(orderList, pgRelation, getDefaultDataProtocol(), null);
	}
	/**
	 * 모든 자료를 하쉬맵정보로 얻어내기
	 * @param orderList 정렬하는 관계객체
	 * @param pgRelation 페지정보
	 * @param dataProtocol 자료변환시 통신규약
	 * @param mappingOption 자료변환시 옵션변수
	 * @return 자료표의 Entity객체들의 묶음
	 * @throws Throwable
	 * @author Olena.Zagreba 
	 * @version  2013-03-31 10:14:10
	 */
	public HashMap getHashMapByPage(orderExpressionRelation orderList, pageExpressionRelation pgRelation, key_WebDataProtocol_Model dataProtocol, Object mappingOption)throws Throwable {
		HashMap result = null;
		Vector<IItem_Model> listItemInfos = getDAClient().get_Info(null, null, orderList, pgRelation);
		if(listItemInfos != null) {
			result = XgetHashMapFromItemList(listItemInfos, dataProtocol, mappingOption);
		}
		return result;
	}
	public HashMap getHashMapByPage(orderExpressionRelation orderList, pageExpressionRelation pgRelation, Object mappingOption)throws Throwable {
		return getHashMapByPage(orderList, pgRelation, getDefaultDataProtocol(), mappingOption);
	}
	public HashMap getHashMapByPage(orderExpressionRelation orderList, pageExpressionRelation pgRelation, key_WebDataProtocol_Model dataProtocol)throws Throwable {
		return getHashMapByPage(orderList, pgRelation, dataProtocol, null);
	}
	public HashMap getHashMapByPage(orderExpressionRelation orderList, pageExpressionRelation pgRelation)throws Throwable {
		return getHashMapByPage(orderList, pgRelation, getDefaultDataProtocol(), null);
	}
	/**
	 * 개별자료를 Xml형식으로 얻어내기
	 * @param nNo 등록번호
	 * @param dataProtocol 자료변환시 통신규약
	 * @param mappingOption 자료변환시 옵션변수
	 * @return
	 * @throws Throwable
	 */
	public String getXmlData(long nNo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable{
		IItem_Model item = getDAClient().get_PrimaryInfo(null, nNo);
		if(item == null) { throw new TruegardenerException("자료기지의 항목에서  Xml자료를 얻을수 없습니다!! - BusinessFacade층  getJSonDataInfo()에서..."); }
		return XgetXmlFromItem(item, dataProtocol, mappingOption);
	}
	public String getXmlData(long nNo, Object mappingOption) throws Throwable{
		return getXmlData(nNo, getDefaultDataProtocol(), mappingOption);
	}
	public String getXmlData(long nNo, key_WebDataProtocol_Model dataProtocol) throws Throwable{
		return getXmlData(nNo, dataProtocol, null);
	}
	public String getXmlData(long nNo) throws Throwable{
		return getXmlData(nNo, getDefaultDataProtocol(), null);
	}
	/**
	 * 개별자료를 Xml형식으로 얻어내기
	 * @param nNo 등록번호
	 * @param dataProtocol 자료변환시 통신규약
	 * @param mappingOption 자료변환시 옵션변수
	 * @return
	 * @throws Throwable
	 */
	public Node getXmlNodeData(long nNo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable{
		IItem_Model item = getDAClient().get_PrimaryInfo(null, nNo);
		if(item == null) { throw new TruegardenerException("자료기지의 항목에서  Xml자료를 얻을수 없습니다!! - BusinessFacade층  getJSonDataInfo()에서..."); }
		return XgetXmlDocFromItem(item, null, -1, dataProtocol, mappingOption);
	}
	public Node getXmlNodeData(long nNo, Object mappingOption) throws Throwable{
		return getXmlNodeData(nNo, getDefaultDataProtocol(), mappingOption);
	}
	public Node getXmlNodeData(long nNo, key_WebDataProtocol_Model dataProtocol) throws Throwable{
		return getXmlNodeData(nNo, dataProtocol, null);
	}
	public Node getXmlNodeData(long nNo) throws Throwable{
		return getXmlNodeData(nNo, getDefaultDataProtocol(), null);
	}
	/**
	 * 개별자료를 JSon형식으로 얻어내기
	 * @param nNo 등록번호
	 * @param mappingOption 자료변환시 옵션변수 
	 * @return 자료표객체
	 * @throws Throwable
	 * @author Olena.Zagreba 
	 * @version  2013-03-31 10:14:10
	 */
	public String getJSonData(long nNo, Object mappingOption)throws Throwable {
		IItem_Model item = getDAClient().get_PrimaryInfo(null, nNo);
		if(item == null) { throw new TruegardenerException("자료기지의 항목에서  JSon 자료를 얻을수 없습니다!! - BusinessFacade층  getJSonDataInfo()에서..."); }
		return XgetJSonFromItem(item, mappingOption);
	}
	public String getJSonData(long nNo)throws Throwable {
		return getJSonData(nNo, null);
	}
	/**
	 * 개별자료를 JSon형식으로 얻어내기
	 * @param nNo 등록번호
	 * @param mappingOption 자료변환시 옵션변수 
	 * @return 자료표객체
	 * @throws Throwable
	 * @author Olena.Zagreba 
	 * @version  2013-03-31 10:14:10
	 */
	public HashMap getHashMapData(long nNo, Object mappingOption)throws Throwable {
		IItem_Model item = getDAClient().get_PrimaryInfo(null, nNo);
		if(item == null) { throw new TruegardenerException("자료기지의 항목에서  JSon 자료를 얻을수 없습니다!! - BusinessFacade층  getJSonDataInfo()에서..."); }
		return XgetHashMapFromItem(item, 0, mappingOption);
	}
	public HashMap getHashMapData(long nNo)throws Throwable {
		return getHashMapData(nNo, null);
	}
	/********************************utils***********************/
	/********************************utils***********************/
	/********************************utils***********************/
	/**
	 * 테블만들기
	 * @return
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2011. 08. 30 AM 10:51:16
	 * @since ModelWeb 1.0
	 */
	public boolean createTable()throws Throwable {
		return getDAClient().create_Table();
	}
	/**
	 * 테블지우기
	 * @return
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2011. 08. 30 AM 10:51:18
	 * @since ModelWeb 1.0
	 */
	public boolean dropTable()throws Throwable {
		return getDAClient().drop_Table();
	}
	/**
	 * 일반(Table(표))의 같기질문에 어떤 field의 구별가능한 값목록 얻기
	 * @param nDistinctField 구별하여야 할 마당
	 * @param whereList 검색하는 관계객체
	 * @param bAndOr And 이냐 Or
	 * @param orderList 정렬하는 관계객체
	 * @param pgInfo 페지정보 
	 * @return 성공: 구별되는 객체배렬, 실패:null
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 12 PM 2:30:04
	 * @since ModelWeb 1.0
	 */
	public Vector<Object> getDistinctListbyEqu(int nDistinctField, whereExpressionRelation whereList, boolean bAndOr, orderExpressionRelation orderList, pageExpressionRelation pgInfo)throws Throwable {
		return getDAClient().get_DistinctListByEqu(nDistinctField, whereList, bAndOr, orderList, pgInfo);
	}
	/**
	 * 일반(Table(표))의 like질문에 어떤 field의 구별가능한 값목록 얻기
	 * @param nDistinctField 구별하여야 할 마당
	 * @param likeList like할 마당이름
	 * @param bAndOr And 이냐 Or
	 * @param orderList 정렬하는 관계객체
	 * @param pgInfo 페지정보
	 * @return 성공:자료기지객체배렬, 실패:null
	 * @throws Throwable
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 25 PM 5:34:31
	 * @since ModelWeb 1.0
	 */
	public Vector<Object> getDistinctListbyLike(int nDistinctField, whereExpressionRelation likeList, boolean bAndOr, orderExpressionRelation orderList, pageExpressionRelation pgInfo)throws Throwable {
		return getDAClient().get_DistinctListByLike(nDistinctField, likeList, bAndOr, orderList, pgInfo);
	}
	/**
	 * 질의식에 의한 구별되는 정보 얻기
	 * @param query
	 * @param executeList
	 * @param pgInfo
	 * @return
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @version ModelWeb 2013. 05. 11 PM 1:07:17
	 * @since ModelWeb
	 */
	public Vector<Object> getDistinctListbyQuery(String query, executeExpressionRelation executeList, pageExpressionRelation pgInfo) throws Throwable{
		return getDAClient().get_DistinctListByQuery(query, executeList, pgInfo);
	}
	/**
	 * 일반(Table(표))의 행개수를 얻기
	 * @param whereList 검색하는 관계객체
	 * @param bAndOr
	 * @return
	 * @throws Throwable
	 */
	public long getListCountByEqu(whereExpressionRelation whereList, boolean bAndOr) throws Throwable {
		return getDAClient().get_ListCountByEqu(whereList, bAndOr);
	}
	/**
	 * 일반(Table(표))의 검색한 개수얻기
	 * @param whereList 검색하는 관계객체
	 * @param strCountNum
	 * @return
	 * @throws Throwable
	 */
	public long getListCountByInfo(whereExpressionRelation whereList, String strCountNum) throws Throwable {
		return getDAClient().get_ListCountByInfo(whereList, strCountNum);
	}
	/**
	 * 일반(Table(표))의 행개수를 얻기
	 * @param likeList
	 * @param bAndOr
	 * @return
	 * @throws Throwable
	 */
	public long getListCountByLike(whereExpressionRelation likeList, boolean bAndOr) throws Throwable {
		return getDAClient().get_ListCountByLike(likeList, bAndOr);
	}
	/**
	 * 일반(Table(표))의 최고값을 얻기
	 * @param nMaxField
	 * @param whereList 검색하는 관계객체
	 * @param bAndOr
	 * @return
	 * @throws Throwable
	 */
	public long getListMaxByEqu(int nMaxField, whereExpressionRelation whereList, boolean bAndOr) throws Throwable {
		return getDAClient().get_ListMaxByEqu(nMaxField, whereList, bAndOr);
	}
	/**
	 * 일반(Table(표))의 검색한 마당에서 최고값 얻기
	 * @param nMaxField
	 * @param whereList 검색하는 관계객체
	 * @param strMaxNum
	 * @return
	 * @throws Throwable
	 */
	public long getListMaxByInfo(int nMaxField, whereExpressionRelation whereList, String strMaxNum) throws Throwable {
		return getDAClient().get_ListMaxByInfo(nMaxField, whereList, strMaxNum);
	}
	/**
	 * 일반(Table(표))의 최고값을 얻기
	 * @param nMaxField
	 * @param likeList
	 * @param bAndOr
	 * @return
	 * @throws Throwable
	 */
	public long getListMaxByLike(int nMaxField, whereExpressionRelation likeList, boolean bAndOr) throws Throwable {
		return getDAClient().get_ListMaxByLike(nMaxField, likeList, bAndOr);
	}
	/**
	 * 일반(Table(표))의 최소값을 얻기
	 * @param nMinField
	 * @param whereList 검색하는 관계객체
	 * @param bAndOr
	 * @return
	 * @throws Throwable
	 */
	public long getListMinByEqu(int nMinField, whereExpressionRelation whereList, boolean bAndOr) throws Throwable {
		return getDAClient().get_ListMinByEqu(nMinField, whereList, bAndOr);
	}
	/**
	 * 일반(Table(표))의 검색한 마당에서 최소값 얻기
	 * @param nMinField
	 * @param whereList 검색하는 관계객체
	 * @param strMinNum
	 * @return
	 * @throws Throwable
	 */
	public long getListMinByInfo(int nMinField, whereExpressionRelation whereList, String strMinNum) throws Throwable {
		return getDAClient().get_ListMinByInfo(nMinField, whereList, strMinNum);
	}
	/**
	 * 일반(Table(표))의 최소값을 얻기
	 * @param nMinField
	 * @param likeList
	 * @param bAndOr
	 * @return
	 * @throws Throwable
	 */
	public long getListMinByLike(int nMinField, whereExpressionRelation likeList, boolean bAndOr) throws Throwable {
		return getDAClient().get_ListMinByLike(nMinField, likeList, bAndOr);
	}
	/**
	 * 일반(Table(표))의 최소값을 얻기
	 * @param query
	 * @param executeList
	 * @param minStr
	 * @return
	 * @throws Throwable
	 */
	public long getNumValueByQuery(String query, executeExpressionRelation executeList, String minStr) throws Throwable {
		return getDAClient().get_NumValueByQuery(query, executeList, minStr);
	}
	/**
	 * 일반(Table(표))의 합계를 얻기
	 * @param nSumField
	 * @param whereList 검색하는 관계객체
	 * @param bAndOr
	 * @return
	 * @throws Throwable
	 */
	public long getListSumByEqu(int nSumField, whereExpressionRelation whereList, boolean bAndOr) throws Throwable {
		return getDAClient().get_ListSumByEqu(nSumField, whereList, bAndOr);
	}
	/**
	 * 일반(Table(표))의 합계 구하기
	 * @param nSumField
	 * @param whereList 검색하는 관계객체
	 * @param strSumNum
	 * @return
	 * @throws Throwable
	 */
	public long getListSumByInfo(int nSumField, whereExpressionRelation whereList, String strSumNum) throws Throwable {
		return getDAClient().get_ListSumByInfo(nSumField, whereList, strSumNum);
	}
	/**
	 * 일반(Table(표))의 합계를 얻기
	 * @param nSumField
	 * @param likeList
	 * @param bAndOr
	 * @return
	 * @throws Throwable
	 */
	public long getListSumByLike(int nSumField, whereExpressionRelation likeList, boolean bAndOr) throws Throwable {
		return getDAClient().get_ListSumByLike(nSumField, likeList, bAndOr);
	}
	/*******************************geberal method************************/
	/*******************************geberal method************************/
	/*******************************geberal method************************/
	//
	/**
	 * 일반(Table(표))의 같기질문에 의하여 삭제
	 * @param whereList 검색하는 관계객체
	 * @param bAndOr
	 * @return
	 * @throws Throwable
	 */
	public int deleteByEqu(whereExpressionRelation whereList, boolean bAndOr) throws Throwable {
		return getDAClient().set_DeleteByEqu(whereList, bAndOr);
	}
	/**
	 * 일반(Table(표))의 id 에 해당한 정보를 지우기
	 * @param whereList 검색하는 관계객체
	 * @return
	 * @throws Throwable
	 */
	public int deleteByInfo(whereExpressionRelation whereList) throws Throwable {
		return getDAClient().set_DeleteByInfo(whereList);
	}
	/**
	 * 일반(Table(표))의 같기질문에 의하여 삭제
	 * @param likeList
	 * @param bAndOr
	 * @return
	 * @throws Throwable
	 */
	public int deleteByLike(whereExpressionRelation likeList, boolean bAndOr) throws Throwable {
		return getDAClient().set_DeleteByLike(likeList, bAndOr);
	}
	/**
	 * 일반(Table(표))의 특정한 항목들의 해당한 정보들을 같기질문에 의하여 NULL로 만들기
	 * @param setNullList
	 * @param whereList 검색하는 관계객체
	 * @param bAndOr
	 * @return
	 * @throws Throwable
	 */
	public int setNullByEqu(setNullExpressionRelation setNullList, whereExpressionRelation whereList, boolean bAndOr) throws Throwable {
		return getDAClient().set_NullByEqu(setNullList, whereList, bAndOr);
	}
	/**
	 * 일반(Table(표))의 항목들을 NULL로 설정하기
	 * @param setNullList
	 * @param whereExpression
	 * @return
	 * @throws Throwable
	 */
	public int setNullByInfo(setNullExpressionRelation setNullList, whereExpressionRelation whereExpression) throws Throwable {
		return getDAClient().set_NullByInfo(setNullList, whereExpression);
	}
	/**
	 *  일반(Table(표))의 특정한 항목들의 해당한 정보들을 like질문에 의하여 NULL로 만들기
	 * @param setNullList
	 * @param likeList
	 * @param bAndOr
	 * @return
	 * @throws Throwable
	 */
	public int setNullByLike(setNullExpressionRelation setNullList, whereExpressionRelation likeList, boolean bAndOr) throws Throwable {
		return getDAClient().set_NullByLike(setNullList, likeList, bAndOr);
	}
	/**
	 * 일반(Table(표))의 같기질문에 의하여 변경
	 * @param setUpdateList 변경할 관계객체
	 * @param whereList 검색하는 관계객체
	 * @param bAndOr
	 * @return
	 * @throws Throwable
	 */
	public int updateByEqu(updateExpressionRelation setUpdateList, whereExpressionRelation whereList, boolean bAndOr) throws Throwable {
		return getDAClient().set_UpdateByEqu(setUpdateList, whereList, bAndOr);
	}
	/**
	 * 일반(Table(표))의 특정한 항목들의 변경
	 * @param setUpdateList 변경할 관계객체
	 * @param whereList 검색하는 관계객체
	 * @return
	 * @throws Throwable
	 */
	public int updateByInfo(updateExpressionRelation setUpdateList, whereExpressionRelation whereList) throws Throwable {
		return getDAClient().set_UpdateByInfo(setUpdateList, whereList);
	}
	/**
	 * 일반(Table(표))의 like질문에 의하여 변경
	 * @param setUpdateList 변경할 관계객체
	 * @param likeList
	 * @param bAndOr
	 * @return
	 * @throws Throwable
	 */
	public int updateByLike(updateExpressionRelation setUpdateList, whereExpressionRelation likeList, boolean bAndOr) throws Throwable {
		return getDAClient().set_UpdateByLike(setUpdateList, likeList, bAndOr);
	}
	/*******************************Bean************************/
	/*******************************Bean************************/
	/*******************************Bean************************/
	/**
	 * 일반(Table(표))의 모든형태의 select명령실행하여 정보를 얻기
	 * @param whereList 검색하는 관계객체
	 * @param orderList 정렬하는 관계객체
	 * @param pgInfo
	 * @param beanClass 얻어낼 bean형식의 자료클라스객체
	 * @param mappingOption 자료변환시 옵션변수
	 * @throws Throwable
	 */
	@SuppressWarnings("unchecked")
	public Vector<Object> getDataListByInfo(whereExpressionRelation whereList, orderExpressionRelation orderList, pageExpressionRelation pgInfo, Class beanClass, Object mappingOption)throws Throwable {
		Vector<IItem_Model>resultObj =  getDAClient().get_Info(null, whereList, orderList, pgInfo);
		return (Vector)XgetDataListFromItemList(resultObj, beanClass, mappingOption);
	}
	public Vector<Object> getDataListByInfo(whereExpressionRelation whereList, orderExpressionRelation orderList, pageExpressionRelation pgInfo, Class beanClass)throws Throwable {
		return getDataListByInfo(whereList, orderList, pgInfo, beanClass, null);
	}
	/**
	 * 자료기지의 항목들을 비교하여 AND연산으로서 같은 자료를 얻기
	 * @param selectList 얻으려는 마당정보들(null이면 모두 얻기)
	 * @param info
	 * @param orderList 정렬하는 관계객체
	 * @param pgInfo
	 * @param beanClass 얻어낼 bean형식의 자료클라스객체
	 * @param mappingOption 자료변환시 옵션변수
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 11. 26 PM 5:22:03
	 * @since ModelWeb 1.0
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public Vector<Object> getDataListByAndEquByBean(Object info, orderExpressionRelation orderList, pageExpressionRelation pgInfo, Class beanClass, Object mappingOption)throws Throwable {
		IItem_Model itemInfo = XgetItemFromData(info, mappingOption);
		Vector<IItem_Model> resultObj = getDAClient().get_InfoByAndEquByItem(null, itemInfo, orderList, pgInfo);
		return (Vector)XgetDataListFromItemList(resultObj, beanClass, mappingOption);
	}
	@Deprecated
	public Vector<Object> getDataListByAndEquByBean(Object info, orderExpressionRelation orderList, pageExpressionRelation pgInfo, Class beanClass)throws Throwable {
		return getDataListByAndEquByBean(info, orderList, pgInfo, beanClass, null);
	}
	/**
	 * 자료기지의 항목들을 비교하여 And연산으로서 ((문자마당인경우)like)연산의 자료를 얻기
	 * @param info
	 * @param orderList 정렬하는 관계객체
	 * @param pgInfo
	 * @param beanClass 얻어낼 bean형식의 자료클라스객체
	 * @param mappingOption 자료변환시 옵션변수
	 * @throws Throwable
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public Vector<Object> getDataListByAndLikeByBean(Object info, orderExpressionRelation orderList, pageExpressionRelation pgInfo, Class beanClass, Object mappingOption) throws Throwable {
		IItem_Model itemInfo = XgetItemFromData(info, mappingOption);
		Vector<IItem_Model> resultObj = getDAClient().get_InfoByAndLikeByItem(null, itemInfo, orderList, pgInfo);
		return (Vector)XgetDataListFromItemList(resultObj, beanClass, mappingOption);
	}
	@Deprecated
	public Vector<Object> getDataListByAndLikeByBean(Object info, orderExpressionRelation orderList, pageExpressionRelation pgInfo, Class beanClass) throws Throwable {
		return getDataListByAndLikeByBean(info, orderList, pgInfo, beanClass, null);
	}
	/**
	 * 자료기지의 항목들을 비교하여 Or연산으로서 같은 자료를 얻기
	 * @param info
	 * @param orderList 정렬하는 관계객체
	 * @param pgInfo
	 * @param beanClass 얻어낼 bean형식의 자료클라스객체
	 * @param mappingOption 자료변환시 옵션변수
	 * @throws Throwable
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public Vector<Object> getDataListByOrEquByBean(Object info, orderExpressionRelation orderList, pageExpressionRelation pgInfo, Class beanClass, Object mappingOption) throws Throwable {
		IItem_Model itemInfo = XgetItemFromData(info, mappingOption);
		Vector<IItem_Model> resultObj = getDAClient().get_InfoByOrEquByItem(null, itemInfo, orderList, pgInfo);
		return (Vector)XgetDataListFromItemList(resultObj, beanClass, mappingOption);
	}
	@SuppressWarnings("unchecked")
	@Deprecated
	public Vector<Object> getDataListByOrEquByBean(Object info, orderExpressionRelation orderList, pageExpressionRelation pgInfo, Class beanClass) throws Throwable {
		return (Vector)getDataListByOrEquByBean(info, orderList, pgInfo, beanClass, null);
	}
	/**
	 * 자료기지의 항목들을 비교하여 Or연산으로서 ((문자마당인경우)like)연산의 자료를 얻기
	 * @param info
	 * @param orderList 정렬하는 관계객체
	 * @param pgInfo
	 * @param beanClass 얻어낼 bean형식의 자료클라스객체
	 * @param mappingOption 자료변환시 옵션변수
	 * @throws Throwable
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public Vector<Object> getDataListByOrLikeByBean(Object info, orderExpressionRelation orderList, pageExpressionRelation pgInfo, Class beanClass, Object mappingOption) throws Throwable {
		IItem_Model itemWhereList = XgetItemFromData(info, mappingOption);
		Vector<IItem_Model> resultObj = getDAClient().get_InfoByOrLikeByItem(null, itemWhereList, orderList, pgInfo);
		return (Vector)XgetDataListFromItemList(resultObj, beanClass, mappingOption);
	}
	@Deprecated
	public Vector<Object> getDataListByOrLikeByBean(Object info, orderExpressionRelation orderList, pageExpressionRelation pgInfo, Class beanClass) throws Throwable {
		return getDataListByOrLikeByBean(info, orderList, pgInfo, beanClass, null);
	}
	/**
	 * 일반(Table(표))의 같기질문에 의하여 해당한 정보들을 얻기
	 * @param selectList 얻으려는 마당정보들(null이면 모두 얻기)
	 * @param whereList 검색하는 관계객체
	 * @param bAndOr
	 * @param orderList 정렬하는 관계객체
	 * @param pgInfo
	 * @param beanClass 얻어낼 bean형식의 자료클라스객체
	 * @param mappingOption 자료변환시 옵션변수
	 * @throws Throwable
	 */
	@SuppressWarnings("unchecked")
	public Vector<Object> getDataListByEqu(whereExpressionRelation whereList, boolean bAndOr, orderExpressionRelation orderList, pageExpressionRelation pgInfo, Class beanClass, Object mappingOption) throws Throwable {
		Vector<IItem_Model> resultObj = getDAClient().get_ListByEqu(null, whereList, bAndOr, orderList, pgInfo); 
		return (Vector)XgetDataListFromItemList(resultObj, beanClass, mappingOption);
	}
	public Vector<Object> getDataListByEqu(whereExpressionRelation whereList, boolean bAndOr, orderExpressionRelation orderList, pageExpressionRelation pgInfo, Class beanClass) throws Throwable {
		return getDataListByEqu(whereList, bAndOr, orderList, pgInfo, beanClass, null);
	}
	/**
	 * 일반(Table(표))의 like질문에 의하여 해당한 정보들을 얻기
	 * @param selectList 얻으려는 마당정보들(null이면 모두 얻기)
	 * @param likeList
	 * @param bAndOr
	 * @param orderList 정렬하는 관계객체
	 * @param pgInfo
	 * @param beanClass 얻어낼 bean형식의 자료클라스객체
	 * @param mappingOption 자료변환시 옵션변수
	 * @throws Throwable
	 */
	@SuppressWarnings("unchecked")
	public Vector<Object> getDataListByLike(whereExpressionRelation likeList, boolean bAndOr, orderExpressionRelation orderList, pageExpressionRelation pgInfo, Class beanClass, Object mappingOption) throws Throwable {
		Vector<IItem_Model> resultObj = getDAClient().get_ListByLike(null, likeList, bAndOr, orderList, pgInfo);
		return (Vector)XgetDataListFromItemList(resultObj, beanClass, mappingOption);
	}
	public Vector<Object> getDataListByLike(whereExpressionRelation likeList, boolean bAndOr, orderExpressionRelation orderList, pageExpressionRelation pgInfo, Class beanClass) throws Throwable {
		return getDataListByLike(likeList, bAndOr, orderList, pgInfo, beanClass, null);
	}
	/*******************************Xml************************/
	/*******************************Xml************************/
	/*******************************Xml************************/
	/**
	 * 일반(Table(표))의 모든형태의 select명령실행하여 정보를 얻기
	 * @param whereList 검색하는 관계객체
	 * @param orderList 정렬하는 관계객체
	 * @param pgInfo
	 * @param dataProtocol 자료변환시 통신규약
	 * @param mappingOption 자료변환시 옵션변수
	 * @throws Throwable 2012. 11. 26 PM 5:22:03
	 * @since ModelWeb1.0
	 */
	@SuppressWarnings("unchecked")
	public String getXmlListByInfo(whereExpressionRelation whereList, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption)throws Throwable {
		Vector<IItem_Model>resultObj =  getDAClient().get_Info(null, whereList, orderList, pgInfo);
		return XgetXmlFromItemList(resultObj, dataProtocol, mappingOption);
	}
	public String getXmlListByInfo(whereExpressionRelation whereList, orderExpressionRelation orderList, pageExpressionRelation pgInfo, Object mappingOption)throws Throwable {
		return getXmlListByInfo(whereList, orderList, pgInfo, getDefaultDataProtocol(), mappingOption);
	}
	public String getXmlListByInfo(whereExpressionRelation whereList, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol)throws Throwable {
		return getXmlListByInfo(whereList, orderList, pgInfo, dataProtocol, null);
	}
	public String getXmlListByInfo(whereExpressionRelation whereList, orderExpressionRelation orderList, pageExpressionRelation pgInfo)throws Throwable {
		return getXmlListByInfo(whereList, orderList, pgInfo, getDefaultDataProtocol(), null);
	}
	/**
	 * 일반(Table(표))의 모든형태의 select명령실행하여 정보를 얻기
	 * @param whereList 검색하는 관계객체
	 * @param orderList 정렬하는 관계객체
	 * @param pgInfo
	 * @param dataProtocol 자료변환시 통신규약
	 * @param mappingOption 자료변환시 옵션변수
	 * @throws Throwable 2012. 11. 26 PM 5:22:03
	 * @since ModelWeb1.0
	 */
	@SuppressWarnings("unchecked")
	public Node getXmlDocByInfo(whereExpressionRelation whereList, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption)throws Throwable {
		Vector<IItem_Model>resultObj =  getDAClient().get_Info(null, whereList, orderList, pgInfo);
		return XgetXmlDocFromItemList(resultObj, dataProtocol, mappingOption);
	}
	public Node getXmlDocByInfo(whereExpressionRelation whereList, orderExpressionRelation orderList, pageExpressionRelation pgInfo, Object mappingOption)throws Throwable {
		return getXmlDocByInfo(whereList, orderList, pgInfo, getDefaultDataProtocol(), mappingOption);
	}
	public Node getXmlDocByInfo(whereExpressionRelation whereList, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol)throws Throwable {
		return getXmlDocByInfo(whereList, orderList, pgInfo, dataProtocol, null);
	}
	public Node getXmlDocByInfo(whereExpressionRelation whereList, orderExpressionRelation orderList, pageExpressionRelation pgInfo)throws Throwable {
		return getXmlDocByInfo(whereList, orderList, pgInfo, getDefaultDataProtocol(), null);
	}
	/**
	 * 자료기지의 항목들을 비교하여 AND연산으로서 같은 자료를 얻기
	 * @param info
	 * @param orderList 정렬하는 관계객체
	 * @param pgInfo
	 * @param dataProtocol 자료변환시 통신규약
	 * @param mappingOption 자료변환시 옵션변수
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 11. 26 PM 5:22:03
	 * @throws Throwable 2012. 11. 26 PM 5:22:03
	 * @since ModelWeb1.0
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public String getXmlByAndEquByXml(String info, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption)throws Throwable {
		IItem_Model itemInfo = XgetItemFromXml(info, dataProtocol, mappingOption);
		Vector<IItem_Model>resultObj =  getDAClient().get_InfoByAndEquByItem(null, itemInfo, orderList, pgInfo);
		return XgetXmlFromItemList(resultObj, dataProtocol, mappingOption);
	}
	@Deprecated
	public String getXmlByAndEquByXml(String info, orderExpressionRelation orderList, pageExpressionRelation pgInfo, Object mappingOption)throws Throwable {
		return getXmlByAndEquByXml(info, orderList, pgInfo, getDefaultDataProtocol(), mappingOption);
	}
	@Deprecated
	public String getXmlByAndEquByXml(String info, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol)throws Throwable {
		return getXmlByAndEquByXml(info, orderList, pgInfo, dataProtocol, null);
	}
	@Deprecated
	public String getXmlByAndEquByXml(String info, orderExpressionRelation orderList, pageExpressionRelation pgInfo)throws Throwable {
		return getXmlByAndEquByXml(info, orderList, pgInfo, getDefaultDataProtocol(), null);
	}
	/**
	 * 자료기지의 항목들을 비교하여 AND연산으로서 같은 자료를 얻기
	 * @param info
	 * @param orderList 정렬하는 관계객체
	 * @param pgInfo
	 * @param dataProtocol 자료변환시 통신규약
	 * @param mappingOption 자료변환시 옵션변수
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 11. 26 PM 5:22:03
	 * @throws Throwable 2012. 11. 26 PM 5:22:03
	 * @since ModelWeb1.0
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public Node getXmlDocByAndEquByXml(String info, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption)throws Throwable {
		IItem_Model itemInfo = XgetItemFromXml(info, dataProtocol, mappingOption);
		Vector<IItem_Model>resultObj =  getDAClient().get_InfoByAndEquByItem(null, itemInfo, orderList, pgInfo);
		return XgetXmlDocFromItemList(resultObj, dataProtocol, mappingOption);
	}
	@Deprecated
	public Node getXmlDocByAndEquByXml(String info, orderExpressionRelation orderList, pageExpressionRelation pgInfo, Object mappingOption)throws Throwable {
		return getXmlDocByAndEquByXml(info, orderList, pgInfo, getDefaultDataProtocol(), mappingOption);
	}
	@Deprecated
	public Node getXmlDocByAndEquByXml(String info, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol)throws Throwable {
		return getXmlDocByAndEquByXml(info, orderList, pgInfo, dataProtocol, null);
	}
	@Deprecated
	public Node getXmlDocByAndEquByXml(String info, orderExpressionRelation orderList, pageExpressionRelation pgInfo)throws Throwable {
		return getXmlDocByAndEquByXml(info, orderList, pgInfo, getDefaultDataProtocol(), null);
	}
	/**
	 * 자료기지의 항목들을 비교하여 And연산으로서 ((문자마당인경우)like)연산의 자료를 얻기
	 * @param info
	 * @param orderList 정렬하는 관계객체
	 * @param pgInfo
	 * @param dataProtocol 자료변환시 통신규약
	 * @param mappingOption 자료변환시 옵션변수
	 * @throws Throwable
	 * @since ModelWeb1.0
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public String getXmlByAndLikeByXml(String info, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		IItem_Model itemInfo = XgetItemFromXml(info, dataProtocol, mappingOption);
		Vector<IItem_Model>resultObj =  getDAClient().get_InfoByAndLikeByItem(null, itemInfo, orderList, pgInfo);
		return XgetXmlFromItemList(resultObj, dataProtocol, mappingOption);
	}
	@Deprecated
	public String getXmlByAndLikeByXml(String info, orderExpressionRelation orderList, pageExpressionRelation pgInfo, Object mappingOption) throws Throwable {
		return getXmlByAndLikeByXml(info, orderList, pgInfo, getDefaultDataProtocol(), mappingOption);
	}
	@Deprecated
	public String getXmlByAndLikeByXml(String info, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return getXmlByAndLikeByXml(info, orderList, pgInfo, dataProtocol, null);
	}
	@Deprecated
	public String getXmlByAndLikeByXml(String info, orderExpressionRelation orderList, pageExpressionRelation pgInfo) throws Throwable {
		return getXmlByAndLikeByXml(info, orderList, pgInfo, getDefaultDataProtocol(), null);
	}
	/**
	 * 자료기지의 항목들을 비교하여 And연산으로서 ((문자마당인경우)like)연산의 자료를 얻기
	 * @param info
	 * @param orderList 정렬하는 관계객체
	 * @param pgInfo
	 * @param dataProtocol 자료변환시 통신규약
	 * @param mappingOption 자료변환시 옵션변수
	 * @throws Throwable
	 * @since ModelWeb1.0
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public Node getXmlDocByAndLikeByXml(String info, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		IItem_Model itemInfo = XgetItemFromXml(info, dataProtocol, mappingOption);
		Vector<IItem_Model>resultObj =  getDAClient().get_InfoByAndLikeByItem(null, itemInfo, orderList, pgInfo);
		return XgetXmlDocFromItemList(resultObj, dataProtocol, mappingOption);
	}
	@Deprecated
	public Node getXmlDocByAndLikeByXml(String info, orderExpressionRelation orderList, pageExpressionRelation pgInfo, Object mappingOption) throws Throwable {
		return getXmlDocByAndLikeByXml(info, orderList, pgInfo, getDefaultDataProtocol(), mappingOption);
	}
	@Deprecated
	public Node getXmlDocByAndLikeByXml(String info, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return getXmlDocByAndLikeByXml(info, orderList, pgInfo, dataProtocol, null);
	}
	@Deprecated
	public Node getXmlDocByAndLikeByXml(String info, orderExpressionRelation orderList, pageExpressionRelation pgInfo) throws Throwable {
		return getXmlDocByAndLikeByXml(info, orderList, pgInfo, getDefaultDataProtocol(), null);
	}
	/**
	 * 자료기지의 항목들을 비교하여 Or연산으로서 같은 자료를 얻기
	 * @param info
	 * @param orderList 정렬하는 관계객체
	 * @param pgInfo
	 * @param dataProtocol 자료변환시 통신규약
	 * @param mappingOption 자료변환시 옵션변수
	 * @throws Throwable
	 * @since ModelWeb1.0
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public String getXmlByOrEquByXml(String info, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		IItem_Model itemInfo = XgetItemFromXml(info, dataProtocol, mappingOption);
		Vector<IItem_Model>resultObj =  getDAClient().get_InfoByOrEquByItem(null, itemInfo, orderList, pgInfo);
		return XgetXmlFromItemList(resultObj, dataProtocol, mappingOption);
	}
	@Deprecated
	public String getXmlByOrEquByXml(String info, orderExpressionRelation orderList, pageExpressionRelation pgInfo, Object mappingOption) throws Throwable {
		return getXmlByOrEquByXml(info, orderList, pgInfo, getDefaultDataProtocol(), mappingOption);
	}
	@Deprecated
	public String getXmlByOrEquByXml(String info, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return getXmlByOrEquByXml(info, orderList, pgInfo, dataProtocol, null);
	}
	@Deprecated
	public String getXmlByOrEquByXml(String info, orderExpressionRelation orderList, pageExpressionRelation pgInfo) throws Throwable {
		return getXmlByOrEquByXml(info, orderList, pgInfo, getDefaultDataProtocol(), null);
	}
	/**
	 * 자료기지의 항목들을 비교하여 Or연산으로서 같은 자료를 얻기
	 * @param info
	 * @param orderList 정렬하는 관계객체
	 * @param pgInfo
	 * @param dataProtocol 자료변환시 통신규약
	 * @param mappingOption 자료변환시 옵션변수
	 * @throws Throwable
	 * @since ModelWeb1.0
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public Node getXmlDocByOrEquByXml(String info, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		IItem_Model itemInfo = XgetItemFromXml(info, dataProtocol, mappingOption);
		Vector<IItem_Model>resultObj =  getDAClient().get_InfoByOrEquByItem(null, itemInfo, orderList, pgInfo);
		return XgetXmlDocFromItemList(resultObj, dataProtocol, mappingOption);
	}
	@Deprecated
	public Node getXmlDocByOrEquByXml(String info, orderExpressionRelation orderList, pageExpressionRelation pgInfo, Object mappingOption) throws Throwable {
		return getXmlDocByOrEquByXml(info, orderList, pgInfo, getDefaultDataProtocol(), mappingOption);
	}
	@Deprecated
	public Node getXmlDocByOrEquByXml(String info, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return getXmlDocByOrEquByXml(info, orderList, pgInfo, dataProtocol, null);
	}
	@Deprecated
	public Node getXmlDocByOrEquByXml(String info, orderExpressionRelation orderList, pageExpressionRelation pgInfo) throws Throwable {
		return getXmlDocByOrEquByXml(info, orderList, pgInfo, getDefaultDataProtocol(), null);
	}
	/**
	 * 자료기지의 항목들을 비교하여 Or연산으로서 ((문자마당인경우)like)연산의 자료를 얻기
	 * @param info
	 * @param orderList 정렬하는 관계객체
	 * @param pgInfo
	 * @param dataProtocol 자료변환시 통신규약
	 * @param mappingOption 자료변환시 옵션변수
	 * @throws Throwable
	 * @since ModelWeb1.0
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public String getXmlByOrLikeByXml(String info, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		IItem_Model itemInfo = XgetItemFromXml(info, dataProtocol, mappingOption);
		Vector<IItem_Model>resultObj =  getDAClient().get_InfoByOrLikeByItem(null, itemInfo, orderList, pgInfo);
		return XgetXmlFromItemList(resultObj, dataProtocol, mappingOption);
	}
	@Deprecated
	public String getXmlByOrLikeByXml(String info, orderExpressionRelation orderList, pageExpressionRelation pgInfo, Object mappingOption) throws Throwable {
		return getXmlByOrLikeByXml(info, orderList, pgInfo, getDefaultDataProtocol(), mappingOption);
	}
	@Deprecated
	public String getXmlByOrLikeByXml(String info, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return getXmlByOrLikeByXml(info, orderList, pgInfo, dataProtocol, null);
	}
	@Deprecated
	public String getXmlByOrLikeByXml(String info, orderExpressionRelation orderList, pageExpressionRelation pgInfo) throws Throwable {
		return getXmlByOrLikeByXml(info, orderList, pgInfo, getDefaultDataProtocol(), null);
	}
	/**
	 * 자료기지의 항목들을 비교하여 Or연산으로서 ((문자마당인경우)like)연산의 자료를 얻기
	 * @param info
	 * @param orderList 정렬하는 관계객체
	 * @param pgInfo
	 * @param dataProtocol 자료변환시 통신규약
	 * @param mappingOption 자료변환시 옵션변수
	 * @throws Throwable
	 * @since ModelWeb1.0
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public Node getXmlDocByOrLikeByXml(String info, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		IItem_Model itemInfo = XgetItemFromXml(info, dataProtocol, mappingOption);
		Vector<IItem_Model>resultObj =  getDAClient().get_InfoByOrLikeByItem(null, itemInfo, orderList, pgInfo);
		return XgetXmlDocFromItemList(resultObj, dataProtocol, mappingOption);
	}
	@Deprecated
	public Node getXmlDocByOrLikeByXml(String info, orderExpressionRelation orderList, pageExpressionRelation pgInfo, Object mappingOption) throws Throwable {
		return getXmlDocByOrLikeByXml(info, orderList, pgInfo, getDefaultDataProtocol(), mappingOption);
	}
	@Deprecated
	public Node getXmlDocByOrLikeByXml(String info, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return getXmlDocByOrLikeByXml(info, orderList, pgInfo, dataProtocol, null);
	}
	@Deprecated
	public Node getXmlDocByOrLikeByXml(String info, orderExpressionRelation orderList, pageExpressionRelation pgInfo) throws Throwable {
		return getXmlDocByOrLikeByXml(info, orderList, pgInfo, getDefaultDataProtocol(), null);
	}
	/**
	 * 자료기지의 항목들을 비교하여 AND연산으로서 같은 자료를 얻기
	 * @param info
	 * @param orderList 정렬하는 관계객체
	 * @param pgInfo
	 * @param dataProtocol 자료변환시 통신규약
	 * @param mappingOption 자료변환시 옵션변수
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 11. 26 PM 5:22:03
	 * @throws Throwable 2012. 11. 26 PM 5:22:03
	 * @since ModelWeb1.0
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public String getXmlByAndEquByItem(IItem_Model itemInfo, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption)throws Throwable {
		Vector<IItem_Model>resultObj =  getDAClient().get_InfoByAndEquByItem(null, itemInfo, orderList, pgInfo);
		return XgetXmlFromItemList(resultObj, dataProtocol, mappingOption);
	}
	@Deprecated
	public String getXmlByAndEquByItem(IItem_Model itemInfo, orderExpressionRelation orderList, pageExpressionRelation pgInfo, Object mappingOption)throws Throwable {
		return getXmlByAndEquByItem(itemInfo, orderList, pgInfo, getDefaultDataProtocol(), mappingOption);
	}
	@Deprecated
	public String getXmlByAndEquByItem(IItem_Model itemInfo, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol)throws Throwable {
		return getXmlByAndEquByItem(itemInfo, orderList, pgInfo, dataProtocol, null);
	}
	@Deprecated
	public String getXmlByAndEquByItem(IItem_Model itemInfo, orderExpressionRelation orderList, pageExpressionRelation pgInfo)throws Throwable {
		return getXmlByAndEquByItem(itemInfo, orderList, pgInfo, getDefaultDataProtocol(), null);
	}
	/**
	 * 자료기지의 항목들을 비교하여 AND연산으로서 같은 자료를 얻기
	 * @param info
	 * @param orderList 정렬하는 관계객체
	 * @param pgInfo
	 * @param dataProtocol 자료변환시 통신규약
	 * @param mappingOption 자료변환시 옵션변수
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 11. 26 PM 5:22:03
	 * @throws Throwable 2012. 11. 26 PM 5:22:03
	 * @since ModelWeb1.0
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public Node getXmlDocByAndEquByItem(IItem_Model itemInfo, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption)throws Throwable {
		Vector<IItem_Model>resultObj =  getDAClient().get_InfoByAndEquByItem(null, itemInfo, orderList, pgInfo);
		return XgetXmlDocFromItemList(resultObj, dataProtocol, mappingOption);
	}
	@Deprecated
	public Node getXmlDocByAndEquByItem(IItem_Model itemInfo, orderExpressionRelation orderList, pageExpressionRelation pgInfo, Object mappingOption)throws Throwable {
		return getXmlDocByAndEquByItem(itemInfo, orderList, pgInfo, getDefaultDataProtocol(), mappingOption);
	}
	@Deprecated
	public Node getXmlDocByAndEquByItem(IItem_Model itemInfo, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol)throws Throwable {
		return getXmlDocByAndEquByItem(itemInfo, orderList, pgInfo, dataProtocol, null);
	}
	@Deprecated
	public Node getXmlDocByAndEquByItem(IItem_Model itemInfo, orderExpressionRelation orderList, pageExpressionRelation pgInfo)throws Throwable {
		return getXmlDocByAndEquByItem(itemInfo, orderList, pgInfo, getDefaultDataProtocol(), null);
	}
	/**
	 * 자료기지의 항목들을 비교하여 And연산으로서 ((문자마당인경우)like)연산의 자료를 얻기
	 * @param info
	 * @param orderList 정렬하는 관계객체
	 * @param pgInfo
	 * @param dataProtocol 자료변환시 통신규약
	 * @param mappingOption 자료변환시 옵션변수
	 * @throws Throwable
	 * @since ModelWeb1.0
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public String getXmlByAndLikeByItem(IItem_Model itemInfo, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		Vector<IItem_Model>resultObj =  getDAClient().get_InfoByAndLikeByItem(null, itemInfo, orderList, pgInfo);
		return XgetXmlFromItemList(resultObj, dataProtocol, mappingOption);
	}
	@Deprecated
	public String getXmlByAndLikeByItem(IItem_Model itemInfo, orderExpressionRelation orderList, pageExpressionRelation pgInfo, Object mappingOption) throws Throwable {
		return getXmlByAndLikeByItem(itemInfo, orderList, pgInfo, getDefaultDataProtocol(), mappingOption);
	}
	@Deprecated
	public String getXmlByAndLikeByItem(IItem_Model itemInfo, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return getXmlByAndLikeByItem(itemInfo, orderList, pgInfo, dataProtocol, null);
	}
	@Deprecated
	public String getXmlByAndLikeByItem(IItem_Model itemInfo, orderExpressionRelation orderList, pageExpressionRelation pgInfo) throws Throwable {
		return getXmlByAndLikeByItem(itemInfo, orderList, pgInfo, getDefaultDataProtocol(), null);
	}
	/**
	 * 자료기지의 항목들을 비교하여 And연산으로서 ((문자마당인경우)like)연산의 자료를 얻기
	 * @param info
	 * @param orderList 정렬하는 관계객체
	 * @param pgInfo
	 * @param dataProtocol 자료변환시 통신규약
	 * @param mappingOption 자료변환시 옵션변수
	 * @throws Throwable
	 * @since ModelWeb1.0
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public Node getXmlDocByAndLikeByItem(IItem_Model itemInfo, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		Vector<IItem_Model>resultObj =  getDAClient().get_InfoByAndLikeByItem(null, itemInfo, orderList, pgInfo);
		return XgetXmlDocFromItemList(resultObj, dataProtocol, mappingOption);
	}
	@Deprecated
	public Node getXmlDocByAndLikeByItem(IItem_Model itemInfo, orderExpressionRelation orderList, pageExpressionRelation pgInfo, Object mappingOption) throws Throwable {
		return getXmlDocByAndLikeByItem(itemInfo, orderList, pgInfo, getDefaultDataProtocol(), mappingOption);
	}
	@Deprecated
	public Node getXmlDocByAndLikeByItem(IItem_Model itemInfo, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return getXmlDocByAndLikeByItem(itemInfo, orderList, pgInfo, dataProtocol, null);
	}
	@Deprecated
	public Node getXmlDocByAndLikeByItem(IItem_Model itemInfo, orderExpressionRelation orderList, pageExpressionRelation pgInfo) throws Throwable {
		return getXmlDocByAndLikeByItem(itemInfo, orderList, pgInfo, getDefaultDataProtocol(), null);
	}
	/**
	 * 자료기지의 항목들을 비교하여 Or연산으로서 같은 자료를 얻기
	 * @param info
	 * @param orderList 정렬하는 관계객체
	 * @param pgInfo
	 * @param dataProtocol 자료변환시 통신규약
	 * @param mappingOption 자료변환시 옵션변수
	 * @throws Throwable
	 * @since ModelWeb1.0
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public String getXmlByOrEquByItem(IItem_Model itemInfo, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		Vector<IItem_Model>resultObj =  getDAClient().get_InfoByOrEquByItem(null, itemInfo, orderList, pgInfo);
		return XgetXmlFromItemList(resultObj, dataProtocol, mappingOption);
	}
	@Deprecated
	public String getXmlByOrEquByItem(IItem_Model itemInfo, orderExpressionRelation orderList, pageExpressionRelation pgInfo, Object mappingOption) throws Throwable {
		return getXmlByOrEquByItem(itemInfo, orderList, pgInfo, getDefaultDataProtocol(), mappingOption);
	}
	@Deprecated
	public String getXmlByOrEquByItem(IItem_Model itemInfo, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return getXmlByOrEquByItem(itemInfo, orderList, pgInfo, dataProtocol, null);
	}
	@Deprecated
	public String getXmlByOrEquByItem(IItem_Model itemInfo, orderExpressionRelation orderList, pageExpressionRelation pgInfo) throws Throwable {
		return getXmlByOrEquByItem(itemInfo, orderList, pgInfo, getDefaultDataProtocol(), null);
	}
	/**
	 * 자료기지의 항목들을 비교하여 Or연산으로서 같은 Xml자료를 얻기
	 * @param info
	 * @param orderList 정렬하는 관계객체
	 * @param pgInfo
	 * @param dataProtocol 자료변환시 통신규약
	 * @param mappingOption 자료변환시 옵션변수
	 * @throws Throwable
	 * @since ModelWeb1.0
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public Node getXmlDocInfoOrEquByItem(IItem_Model itemInfo, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		Vector<IItem_Model>resultObj =  getDAClient().get_InfoByOrEquByItem(null, itemInfo, orderList, pgInfo);
		return XgetXmlDocFromItemList(resultObj, dataProtocol, mappingOption);
	}
	@Deprecated
	public Node getXmlDocInfoOrEquByItem(IItem_Model itemInfo, orderExpressionRelation orderList, pageExpressionRelation pgInfo, Object mappingOption) throws Throwable {
		return getXmlDocInfoOrEquByItem(itemInfo, orderList, pgInfo, getDefaultDataProtocol(), mappingOption);
	}
	@Deprecated
	public Node getXmlDocInfoOrEquByItem(IItem_Model itemInfo, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return getXmlDocInfoOrEquByItem(itemInfo, orderList, pgInfo, dataProtocol, null);
	}
	@Deprecated
	public Node getXmlDocInfoOrEquByItem(IItem_Model itemInfo, orderExpressionRelation orderList, pageExpressionRelation pgInfo) throws Throwable {
		return getXmlDocInfoOrEquByItem(itemInfo, orderList, pgInfo, getDefaultDataProtocol(), null);
	}
	/**
	 * 자료기지의 항목들을 비교하여 Or연산으로서 ((문자마당인경우)like)연산의 자료를 얻기
	 * @param info
	 * @param orderList 정렬하는 관계객체
	 * @param pgInfo
	 * @param dataProtocol 자료변환시 통신규약
	 * @param mappingOption 자료변환시 옵션변수
	 * @throws Throwable
	 * @since ModelWeb1.0
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public String getXmlByOrLikeByItem(IItem_Model itemInfo, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		Vector<IItem_Model>resultObj =  getDAClient().get_InfoByOrLikeByItem(null, itemInfo, orderList, pgInfo);
		return XgetXmlFromItemList(resultObj, dataProtocol, mappingOption);
	}
	@Deprecated
	public String getXmlByOrLikeByItem(IItem_Model itemInfo, orderExpressionRelation orderList, pageExpressionRelation pgInfo, Object mappingOption) throws Throwable {
		return getXmlByOrLikeByItem(itemInfo, orderList, pgInfo, getDefaultDataProtocol(), mappingOption);
	}
	@Deprecated
	public String getXmlByOrLikeByItem(IItem_Model itemInfo, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return getXmlByOrLikeByItem(itemInfo, orderList, pgInfo, dataProtocol, null);
	}
	@Deprecated
	public String getXmlByOrLikeByItem(IItem_Model itemInfo, orderExpressionRelation orderList, pageExpressionRelation pgInfo) throws Throwable {
		return getXmlByOrLikeByItem(itemInfo, orderList, pgInfo, getDefaultDataProtocol(), null);
	}
	/**
	 * 자료기지의 항목들을 비교하여 Or연산으로서 ((문자마당인경우)like)연산의 자료를 얻기
	 * @param info
	 * @param orderList 정렬하는 관계객체
	 * @param pgInfo
	 * @param dataProtocol 자료변환시 통신규약
	 * @param mappingOption 자료변환시 옵션변수
	 * @throws Throwable
	 * @since ModelWeb1.0
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public Node getXmlDocByOrLikeByItem(IItem_Model itemInfo, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		Vector<IItem_Model>resultObj =  getDAClient().get_InfoByOrLikeByItem(null, itemInfo, orderList, pgInfo);
		return XgetXmlDocFromItemList(resultObj, dataProtocol, mappingOption);
	}
	@Deprecated
	public Node getXmlDocByOrLikeByItem(IItem_Model itemInfo, orderExpressionRelation orderList, pageExpressionRelation pgInfo, Object mappingOption) throws Throwable {
		return getXmlDocByOrLikeByItem(itemInfo, orderList, pgInfo, getDefaultDataProtocol(), mappingOption);
	}
	@Deprecated
	public Node getXmlDocByOrLikeByItem(IItem_Model itemInfo, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return getXmlDocByOrLikeByItem(itemInfo, orderList, pgInfo, dataProtocol, null);
	}
	@Deprecated
	public Node getXmlDocByOrLikeByItem(IItem_Model itemInfo, orderExpressionRelation orderList, pageExpressionRelation pgInfo) throws Throwable {
		return getXmlDocByOrLikeByItem(itemInfo, orderList, pgInfo, getDefaultDataProtocol(), null);
	}
	/**
	 * 일반(Table(표))의 같기질문에 의하여 해당한 정보들을 얻기
	 * @param whereList 검색하는 관계객체
	 * @param bAndOr
	 * @param orderList 정렬하는 관계객체
	 * @param pgInfo
	 * @param dataProtocol 자료변환시 통신규약
	 * @param mappingOption 자료변환시 옵션변수
	 * @throws Throwable
	 * @since ModelWeb1.0
	 */
	@SuppressWarnings("unchecked")
	public String getXmlListByEqu(whereExpressionRelation whereList, boolean bAndOr, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		Vector<IItem_Model>resultObj =  getDAClient().get_ListByEqu(null, whereList, bAndOr, orderList, pgInfo);
		return XgetXmlFromItemList(resultObj, dataProtocol, mappingOption);
	}
	public String getXmlListByEqu(whereExpressionRelation whereList, boolean bAndOr, orderExpressionRelation orderList, pageExpressionRelation pgInfo, Object mappingOption) throws Throwable {
		return getXmlListByEqu(whereList, bAndOr, orderList, pgInfo, getDefaultDataProtocol(), mappingOption);
	}
	public String getXmlListByEqu(whereExpressionRelation whereList, boolean bAndOr, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return getXmlListByEqu(whereList, bAndOr, orderList, pgInfo, dataProtocol, null);
	}
	public String getXmlListByEqu(whereExpressionRelation whereList, boolean bAndOr, orderExpressionRelation orderList, pageExpressionRelation pgInfo) throws Throwable {
		return getXmlListByEqu(whereList, bAndOr, orderList, pgInfo, getDefaultDataProtocol(), null);
	}
	/**
	 * 일반(Table(표))의 같기질문에 의하여 해당한 정보들을 얻기
	 * @param whereList 검색하는 관계객체
	 * @param bAndOr
	 * @param orderList 정렬하는 관계객체
	 * @param pgInfo
	 * @param dataProtocol 자료변환시 통신규약
	 * @param mappingOption 자료변환시 옵션변수
	 * @throws Throwable
	 * @since ModelWeb1.0
	 */
	@SuppressWarnings("unchecked")
	public Node getXmlDocInfoByEqu(whereExpressionRelation whereList, boolean bAndOr, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		Vector<IItem_Model>resultObj =  getDAClient().get_ListByEqu(null, whereList, bAndOr, orderList, pgInfo);
		return XgetXmlDocFromItemList(resultObj, dataProtocol, mappingOption);
	}
	public Node getXmlDocInfoByEqu(whereExpressionRelation whereList, boolean bAndOr, orderExpressionRelation orderList, pageExpressionRelation pgInfo, Object mappingOption) throws Throwable {
		return getXmlDocInfoByEqu(whereList, bAndOr, orderList, pgInfo, getDefaultDataProtocol(), mappingOption);
	}
	public Node getXmlDocInfoByEqu(whereExpressionRelation whereList, boolean bAndOr, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return getXmlDocInfoByEqu(whereList, bAndOr, orderList, pgInfo, dataProtocol, null);
	}
	public Node getXmlDocInfoByEqu(whereExpressionRelation whereList, boolean bAndOr, orderExpressionRelation orderList, pageExpressionRelation pgInfo) throws Throwable {
		return getXmlDocInfoByEqu(whereList, bAndOr, orderList, pgInfo, getDefaultDataProtocol(), null);
	}
	/**
	 * 일반(Table(표))의 like질문에 의하여 해당한 정보들을 얻기
	 * @param selectList 얻으려는 마당정보들(null이면 모두 얻기)
	 * @param likeList
	 * @param bAndOr
	 * @param orderList 정렬하는 관계객체
	 * @param pgInfo
	 * @param dataProtocol 자료변환시 통신규약
	 * @param mappingOption 자료변환시 옵션변수
	 * @throws Throwable
	 * @since ModelWeb1.0
	 */
	@SuppressWarnings("unchecked")
	public String getXmlListByLike(whereExpressionRelation likeList, boolean bAndOr, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		Vector<IItem_Model>resultObj =  getDAClient().get_ListByLike(null, likeList, bAndOr, orderList, pgInfo);
		return XgetXmlFromItemList(resultObj, dataProtocol, mappingOption);
	}
	public String getXmlListByLike(whereExpressionRelation likeList, boolean bAndOr, orderExpressionRelation orderList, pageExpressionRelation pgInfo, Object mappingOption) throws Throwable {
		return getXmlListByLike(likeList, bAndOr, orderList, pgInfo, getDefaultDataProtocol(), mappingOption);
	}
	public String getXmlListByLike(whereExpressionRelation likeList, boolean bAndOr, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return getXmlListByLike(likeList, bAndOr, orderList, pgInfo, dataProtocol, null);
	}
	public String getXmlListByLike(whereExpressionRelation likeList, boolean bAndOr, orderExpressionRelation orderList, pageExpressionRelation pgInfo) throws Throwable {
		return getXmlListByLike(likeList, bAndOr, orderList, pgInfo, getDefaultDataProtocol(), null);
	}
	/**
	 * 일반(Table(표))의 like질문에 의하여 해당한 정보들을 얻기
	 * @param selectList 얻으려는 마당정보들(null이면 모두 얻기)
	 * @param likeList
	 * @param bAndOr
	 * @param orderList 정렬하는 관계객체
	 * @param pgInfo
	 * @param dataProtocol 자료변환시 통신규약
	 * @param mappingOption 자료변환시 옵션변수
	 * @throws Throwable
	 * @since ModelWeb1.0
	 */
	@SuppressWarnings("unchecked")
	public Node getXmlDocInfoByLike(whereExpressionRelation likeList, boolean bAndOr, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		Vector<IItem_Model>resultObj =  getDAClient().get_ListByLike(null, likeList, bAndOr, orderList, pgInfo);
		return XgetXmlDocFromItemList(resultObj, dataProtocol, mappingOption);
	}
	public Node getXmlDocInfoByLike(whereExpressionRelation likeList, boolean bAndOr, orderExpressionRelation orderList, pageExpressionRelation pgInfo, Object mappingOption) throws Throwable {
		return getXmlDocInfoByLike(likeList, bAndOr, orderList, pgInfo, getDefaultDataProtocol(), mappingOption);
	}
	public Node getXmlDocInfoByLike(whereExpressionRelation likeList, boolean bAndOr, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return getXmlDocInfoByLike(likeList, bAndOr, orderList, pgInfo, dataProtocol, null);
	}
	public Node getXmlDocInfoByLike(whereExpressionRelation likeList, boolean bAndOr, orderExpressionRelation orderList, pageExpressionRelation pgInfo) throws Throwable {
		return getXmlDocInfoByLike(likeList, bAndOr, orderList, pgInfo, getDefaultDataProtocol(), null);
	}
	/*******************************json***********************/
	/*******************************json***********************/
	/*******************************json***********************/
	/**
	 * 일반(Table(표))의 모든형태의 select명령실행하여 정보를 얻기
	 * @param whereList 검색하는 관계객체
	 * @param orderList 정렬하는 관계객체
	 * @param pgInfo
	 * @param dataProtocol 자료변환시 통신규약
	 * @param mappingOption 자료변환시 옵션변수
	 * @throws Throwable
	 * @since ModelWeb1.0
	 */
	@SuppressWarnings("unchecked")
	public String getJSonByInfo(whereExpressionRelation whereList, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption)throws Throwable {
		Vector<IItem_Model>resultObj =  getDAClient().get_Info(null, whereList, orderList, pgInfo);
		return XgetJSonFromItemList(resultObj, dataProtocol, mappingOption);
	}
	public String getJSonByInfo(whereExpressionRelation whereList, orderExpressionRelation orderList, pageExpressionRelation pgInfo, Object mappingOption)throws Throwable {
		return getJSonByInfo(whereList, orderList, pgInfo, getDefaultDataProtocol(), mappingOption);
	}
	public String getJSonByInfo(whereExpressionRelation whereList, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol)throws Throwable {
		return getJSonByInfo(whereList, orderList, pgInfo, dataProtocol, null);
	}
	public String getJSonByInfo(whereExpressionRelation whereList, orderExpressionRelation orderList, pageExpressionRelation pgInfo)throws Throwable {
		return getJSonByInfo(whereList, orderList, pgInfo, getDefaultDataProtocol(), null);
	}
	/**
	 * 일반(Table(표))의 모든형태의 select명령실행하여 정보를 얻기
	 * @param whereList 검색하는 관계객체
	 * @param orderList 정렬하는 관계객체
	 * @param pgInfo
	 * @param dataProtocol 자료변환시 통신규약
	 * @param mappingOption 자료변환시 옵션변수
	 * @throws Throwable
	 * @since ModelWeb1.0
	 */
	@SuppressWarnings("unchecked")
	public HashMap getHashMapByInfo(whereExpressionRelation whereList, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption)throws Throwable {
		Vector<IItem_Model>resultObj =  getDAClient().get_Info(null, whereList, orderList, pgInfo);
		return XgetHashMapFromItemList(resultObj, dataProtocol, mappingOption);
	}
	public HashMap getHashMapByInfo(whereExpressionRelation whereList, orderExpressionRelation orderList, pageExpressionRelation pgInfo, Object mappingOption)throws Throwable {
		return getHashMapByInfo(whereList, orderList, pgInfo, getDefaultDataProtocol(), mappingOption);
	}
	public HashMap getHashMapByInfo(whereExpressionRelation whereList, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol)throws Throwable {
		return getHashMapByInfo(whereList, orderList, pgInfo, dataProtocol, null);
	}
	public HashMap getHashMapByInfo(whereExpressionRelation whereList, orderExpressionRelation orderList, pageExpressionRelation pgInfo)throws Throwable {
		return getHashMapByInfo(whereList, orderList, pgInfo, getDefaultDataProtocol(), null);
	}
	/**
	 * 자료의 항목들을 비교하여 And연산으로서 ((문자마당인경우)같기)연산의 자료를 얻기
	 * @param selectList 얻으려는 마당정보들(null이면 모두 얻기)
	 * @param info
	 * @param orderList 정렬하는 관계객체
	 * @param pgInfo
	 * @param dataProtocol 자료변환시 통신규약
	 * @param mappingOption 자료변환시 옵션변수
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 11. 26 PM 5:22:03
	 * @since ModelWeb1.0
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public String getJSonByAndEquByJSon(String info, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption)throws Throwable {
		IItem_Model itemInfo = XgetItemFromJSon(info, mappingOption);
		Vector<IItem_Model>resultObj =  getDAClient().get_InfoByAndEquByItem(null, itemInfo, orderList, pgInfo);
		return XgetJSonFromItemList(resultObj, dataProtocol, mappingOption);
	}
	@Deprecated
	public String getJSonByAndEquByJSon(String info, orderExpressionRelation orderList, pageExpressionRelation pgInfo, Object mappingOption)throws Throwable {
		return getJSonByAndEquByJSon(info, orderList, pgInfo, getDefaultDataProtocol(), mappingOption);
	}
	@Deprecated
	public String getJSonByAndEquByJSon(String info, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol)throws Throwable {
		return getJSonByAndEquByJSon(info, orderList, pgInfo, dataProtocol, null);
	}
	@Deprecated
	public String getJSonByAndEquByJSon(String info, orderExpressionRelation orderList, pageExpressionRelation pgInfo)throws Throwable {
		return getJSonByAndEquByJSon(info, orderList, pgInfo, getDefaultDataProtocol(), null);
	}
	/**
	 * 자료의 항목들을 비교하여 And연산으로서 ((문자마당인경우)같기)연산의 자료를 얻기
	 * @param selectList 얻으려는 마당정보들(null이면 모두 얻기)
	 * @param info
	 * @param orderList 정렬하는 관계객체
	 * @param pgInfo
	 * @param dataProtocol 자료변환시 통신규약
	 * @param mappingOption 자료변환시 옵션변수
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 11. 26 PM 5:22:03
	 * @since ModelWeb1.0
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public HashMap getHashMapByAndEquByJSon(String info, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption)throws Throwable {
		IItem_Model itemInfo = XgetItemFromJSon(info, mappingOption);
		Vector<IItem_Model>resultObj =  getDAClient().get_InfoByAndEquByItem(null, itemInfo, orderList, pgInfo);
		return XgetHashMapFromItemList(resultObj, dataProtocol, mappingOption);
	}
	@Deprecated
	public HashMap getHashMapByAndEquByJSon(String info, orderExpressionRelation orderList, pageExpressionRelation pgInfo, Object mappingOption)throws Throwable {
		return getHashMapByAndEquByJSon(info, orderList, pgInfo, getDefaultDataProtocol(), mappingOption);
	}
	@Deprecated
	public HashMap getHashMapByAndEquByJSon(String info, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol)throws Throwable {
		return getHashMapByAndEquByJSon(info, orderList, pgInfo, dataProtocol, null);
	}
	@Deprecated
	public HashMap getHashMapByAndEquByJSon(String info, orderExpressionRelation orderList, pageExpressionRelation pgInfo)throws Throwable {
		return getHashMapByAndEquByJSon(info, orderList, pgInfo, getDefaultDataProtocol(), null);
	}
	/**
	 * 자료의 항목들을 비교하여 And연산으로서 ((문자마당인경우)like)연산의 자료를 얻기
	 * @param info
	 * @param orderList 정렬하는 관계객체
	 * @param pgInfo
	 * @param dataProtocol 자료변환시 통신규약
	 * @param mappingOption 자료변환시 옵션변수
	 * @throws Throwable
	 * @since ModelWeb1.0
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public String getJSonByAndLikeByJSon(String info, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		IItem_Model itemInfo = XgetItemFromJSon(info, mappingOption);
		Vector<IItem_Model>resultObj =  getDAClient().get_InfoByAndLikeByItem(null, itemInfo, orderList, pgInfo);
		return XgetJSonFromItemList(resultObj, dataProtocol, mappingOption);
	}
	@Deprecated
	public String getJSonByAndLikeByJSon(String info, orderExpressionRelation orderList, pageExpressionRelation pgInfo, Object mappingOption) throws Throwable {
		return getJSonByAndLikeByJSon(info, orderList, pgInfo, getDefaultDataProtocol(), mappingOption);
	}
	@Deprecated
	public String getJSonByAndLikeByJSon(String info, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return getJSonByAndLikeByJSon(info, orderList, pgInfo, dataProtocol, null);
	}
	@Deprecated
	public String getJSonByAndLikeByJSon(String info, orderExpressionRelation orderList, pageExpressionRelation pgInfo) throws Throwable {
		return getJSonByAndLikeByJSon(info, orderList, pgInfo, getDefaultDataProtocol(), null);
	}
	/**
	 * 자료의 항목들을 비교하여 And연산으로서 ((문자마당인경우)like)연산의 자료를 얻기
	 * @param info
	 * @param orderList 정렬하는 관계객체
	 * @param pgInfo
	 * @param dataProtocol 자료변환시 통신규약
	 * @param mappingOption 자료변환시 옵션변수
	 * @throws Throwable
	 * @since ModelWeb1.0
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public HashMap getHashMapByAndLikeByJSon(String info, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		IItem_Model itemInfo = XgetItemFromJSon(info, mappingOption);
		Vector<IItem_Model>resultObj =  getDAClient().get_InfoByAndLikeByItem(null, itemInfo, orderList, pgInfo);
		return XgetHashMapFromItemList(resultObj, dataProtocol, mappingOption);
	}
	@Deprecated
	public HashMap getHashMapByAndLikeByJSon(String info, orderExpressionRelation orderList, pageExpressionRelation pgInfo, Object mappingOption) throws Throwable {
		return getHashMapByAndLikeByJSon(info, orderList, pgInfo, getDefaultDataProtocol(), mappingOption);
	}
	@Deprecated
	public HashMap getHashMapByAndLikeByJSon(String info, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return getHashMapByAndLikeByJSon(info, orderList, pgInfo, dataProtocol, null);
	}
	@Deprecated
	public HashMap getHashMapByAndLikeByJSon(String info, orderExpressionRelation orderList, pageExpressionRelation pgInfo) throws Throwable {
		return getHashMapByAndLikeByJSon(info, orderList, pgInfo, getDefaultDataProtocol(), null);
	}
	/**
	 * 자료의 항목들을 비교하여 Or연산으로서 ((문자마당인경우)같기)연산의 자료를 얻기
	 * @param info
	 * @param orderList 정렬하는 관계객체
	 * @param pgInfo
	 * @param dataProtocol 자료변환시 통신규약
	 * @param mappingOption 자료변환시 옵션변수
	 * @throws Throwable
	 * @since ModelWeb1.0
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public String getJSonByOrEquByJSon(String info, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		IItem_Model itemInfo = XgetItemFromJSon(info, mappingOption);
		Vector<IItem_Model>resultObj =  getDAClient().get_InfoByOrEquByItem(null, itemInfo, orderList, pgInfo);
		return XgetJSonFromItemList(resultObj, dataProtocol, mappingOption);
	}
	@Deprecated
	public String getJSonByOrEquByJSon(String info, orderExpressionRelation orderList, pageExpressionRelation pgInfo, Object mappingOption) throws Throwable {
		return getJSonByOrEquByJSon(info, orderList, pgInfo, getDefaultDataProtocol(), mappingOption);
	}
	@Deprecated
	public String getJSonByOrEquByJSon(String info, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return getJSonByOrEquByJSon(info, orderList, pgInfo, dataProtocol, null);
	}
	@Deprecated
	public String getJSonByOrEquByJSon(String info, orderExpressionRelation orderList, pageExpressionRelation pgInfo) throws Throwable {
		return getJSonByOrEquByJSon(info, orderList, pgInfo, getDefaultDataProtocol(), null);
	}
	/**
	 * 자료의 항목들을 비교하여 Or연산으로서 ((문자마당인경우)같기)연산의 자료를 얻기
	 * @param info
	 * @param orderList 정렬하는 관계객체
	 * @param pgInfo
	 * @param dataProtocol 자료변환시 통신규약
	 * @param mappingOption 자료변환시 옵션변수
	 * @throws Throwable
	 * @since ModelWeb1.0
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public HashMap getHashMapByOrEquByJSon(String info, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		IItem_Model itemInfo = XgetItemFromJSon(info, mappingOption);
		Vector<IItem_Model>resultObj =  getDAClient().get_InfoByOrEquByItem(null, itemInfo, orderList, pgInfo);
		return XgetHashMapFromItemList(resultObj, dataProtocol, mappingOption);
	}
	@Deprecated
	public HashMap getHashMapByOrEquByJSon(String info, orderExpressionRelation orderList, pageExpressionRelation pgInfo, Object mappingOption) throws Throwable {
		return getHashMapByOrEquByJSon(info, orderList, pgInfo, getDefaultDataProtocol(), mappingOption);
	}
	@Deprecated
	public HashMap getHashMapByOrEquByJSon(String info, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return getHashMapByOrEquByJSon(info, orderList, pgInfo, dataProtocol, null);
	}
	@Deprecated
	public HashMap getHashMapByOrEquByJSon(String info, orderExpressionRelation orderList, pageExpressionRelation pgInfo) throws Throwable {
		return getHashMapByOrEquByJSon(info, orderList, pgInfo, getDefaultDataProtocol(), null);
	}
	/**
	 * 자료의 항목들을 비교하여 Or연산으로서 ((문자마당인경우)like)연산의 자료를 얻기
	 * @param info
	 * @param orderList 정렬하는 관계객체
	 * @param pgInfo
	 * @param dataProtocol 자료변환시 통신규약
	 * @param mappingOption 자료변환시 옵션변수
	 * @throws Throwable
	 * @since ModelWeb1.0
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public String getJSonByOrLikeByJSon(String info, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		IItem_Model itemInfo = XgetItemFromJSon(info, mappingOption);
		Vector<IItem_Model>resultObj =  getDAClient().get_InfoByOrLikeByItem(null, itemInfo, orderList, pgInfo);
		return XgetJSonFromItemList(resultObj, dataProtocol, mappingOption);
	}
	@Deprecated
	public String getJSonByOrLikeByJSon(String info, orderExpressionRelation orderList, pageExpressionRelation pgInfo, Object mappingOption) throws Throwable {
		return getJSonByOrLikeByJSon(info, orderList, pgInfo, getDefaultDataProtocol(), mappingOption);
	}
	@Deprecated
	public String getJSonByOrLikeByJSon(String info, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return getJSonByOrLikeByJSon(info, orderList, pgInfo, dataProtocol, null);
	}
	@Deprecated
	public String getJSonByOrLikeByJSon(String info, orderExpressionRelation orderList, pageExpressionRelation pgInfo) throws Throwable {
		return getJSonByOrLikeByJSon(info, orderList, pgInfo, getDefaultDataProtocol(), null);
	}
	/**
	 * 자료의 항목들을 비교하여 Or연산으로서 ((문자마당인경우)like)연산의 자료를 얻기
	 * @param info
	 * @param orderList 정렬하는 관계객체
	 * @param pgInfo
	 * @param dataProtocol 자료변환시 통신규약
	 * @param mappingOption 자료변환시 옵션변수
	 * @throws Throwable
	 * @since ModelWeb1.0
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public HashMap getHashMapByOrLikeByJSon(String info, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		IItem_Model itemInfo = XgetItemFromJSon(info, mappingOption);
		Vector<IItem_Model>resultObj =  getDAClient().get_InfoByOrLikeByItem(null, itemInfo, orderList, pgInfo);
		return XgetHashMapFromItemList(resultObj, dataProtocol, mappingOption);
	}
	@Deprecated
	public HashMap getHashMapByOrLikeByJSon(String info, orderExpressionRelation orderList, pageExpressionRelation pgInfo, Object mappingOption) throws Throwable {
		return getHashMapByOrLikeByJSon(info, orderList, pgInfo, getDefaultDataProtocol(), mappingOption);
	}
	@Deprecated
	public HashMap getHashMapByOrLikeByJSon(String info, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return getHashMapByOrLikeByJSon(info, orderList, pgInfo, dataProtocol, null);
	}
	@Deprecated
	public HashMap getHashMapByOrLikeByJSon(String info, orderExpressionRelation orderList, pageExpressionRelation pgInfo) throws Throwable {
		return getHashMapByOrLikeByJSon(info, orderList, pgInfo, getDefaultDataProtocol(), null);
	}
	/**
	 * 자료의 항목들을 비교하여 And연산으로서 ((문자마당인경우)같기)연산의 자료를 얻기
	 * @param selectList 얻으려는 마당정보들(null이면 모두 얻기)
	 * @param info
	 * @param orderList 정렬하는 관계객체
	 * @param pgInfo
	 * @param dataProtocol 자료변환시 통신규약
	 * @param mappingOption 자료변환시 옵션변수
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 11. 26 PM 5:22:03
	 * @since ModelWeb1.0
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public String getJSonByAndEquByItem(IItem_Model itemInfo, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption)throws Throwable {
		Vector<IItem_Model>resultObj =  getDAClient().get_InfoByAndEquByItem(null, itemInfo, orderList, pgInfo);
		return XgetJSonFromItemList(resultObj, dataProtocol, mappingOption);
	}
	@Deprecated
	public String getJSonByAndEquByItem(IItem_Model itemInfo, orderExpressionRelation orderList, pageExpressionRelation pgInfo, Object mappingOption)throws Throwable {
		return getJSonByAndEquByItem(itemInfo, orderList, pgInfo, getDefaultDataProtocol(), mappingOption);
	}
	@Deprecated
	public String getJSonByAndEquByItem(IItem_Model itemInfo, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol)throws Throwable {
		return getJSonByAndEquByItem(itemInfo, orderList, pgInfo, dataProtocol, null);
	}
	@Deprecated
	public String getJSonByAndEquByItem(IItem_Model itemInfo, orderExpressionRelation orderList, pageExpressionRelation pgInfo)throws Throwable {
		return getJSonByAndEquByItem(itemInfo, orderList, pgInfo, getDefaultDataProtocol(), null);
	}
	/**
	 * 자료의 항목들을 비교하여 And연산으로서 ((문자마당인경우)같기)연산의 자료를 얻기
	 * @param selectList 얻으려는 마당정보들(null이면 모두 얻기)
	 * @param info
	 * @param orderList 정렬하는 관계객체
	 * @param pgInfo
	 * @param dataProtocol 자료변환시 통신규약
	 * @param mappingOption 자료변환시 옵션변수
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 11. 26 PM 5:22:03
	 * @since ModelWeb1.0
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public HashMap getHashMapByAndEquByItem(IItem_Model itemInfo, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption)throws Throwable {
		Vector<IItem_Model>resultObj =  getDAClient().get_InfoByAndEquByItem(null, itemInfo, orderList, pgInfo);
		return XgetHashMapFromItemList(resultObj, dataProtocol, mappingOption);
	}
	@Deprecated
	public HashMap getHashMapByAndEquByItem(IItem_Model itemInfo, orderExpressionRelation orderList, pageExpressionRelation pgInfo, Object mappingOption)throws Throwable {
		return getHashMapByAndEquByItem(itemInfo, orderList, pgInfo, getDefaultDataProtocol(), mappingOption);
	}
	@Deprecated
	public HashMap getHashMapByAndEquByItem(IItem_Model itemInfo, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol)throws Throwable {
		return getHashMapByAndEquByItem(itemInfo, orderList, pgInfo, dataProtocol, null);
	}
	@Deprecated
	public HashMap getHashMapByAndEquByItem(IItem_Model itemInfo, orderExpressionRelation orderList, pageExpressionRelation pgInfo)throws Throwable {
		return getHashMapByAndEquByItem(itemInfo, orderList, pgInfo, getDefaultDataProtocol(), null);
	}
	/**
	 * 자료의 항목들을 비교하여 And연산으로서 ((문자마당인경우)like)연산의 자료를 얻기
	 * @param info
	 * @param orderList 정렬하는 관계객체
	 * @param pgInfo
	 * @param dataProtocol 자료변환시 통신규약
	 * @param mappingOption 자료변환시 옵션변수
	 * @throws Throwable
	 * @since ModelWeb1.0
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public String getJSonByAndLikeByItem(IItem_Model itemInfo, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		Vector<IItem_Model>resultObj =  getDAClient().get_InfoByAndLikeByItem(null, itemInfo, orderList, pgInfo);
		return XgetJSonFromItemList(resultObj, dataProtocol, mappingOption);
	}
	@Deprecated
	public String getJSonByAndLikeByItem(IItem_Model itemInfo, orderExpressionRelation orderList, pageExpressionRelation pgInfo, Object mappingOption) throws Throwable {
		return getJSonByAndLikeByItem(itemInfo, orderList, pgInfo, getDefaultDataProtocol(), mappingOption);
	}
	@Deprecated
	public String getJSonByAndLikeByItem(IItem_Model itemInfo, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return getJSonByAndLikeByItem(itemInfo, orderList, pgInfo, dataProtocol, null);
	}
	@Deprecated
	public String getJSonByAndLikeByItem(IItem_Model itemInfo, orderExpressionRelation orderList, pageExpressionRelation pgInfo) throws Throwable {
		return getJSonByAndLikeByItem(itemInfo, orderList, pgInfo, getDefaultDataProtocol(), null);
	}
	/**
	 * 자료의 항목들을 비교하여 And연산으로서 ((문자마당인경우)like)연산의 자료를 얻기
	 * @param info
	 * @param orderList 정렬하는 관계객체
	 * @param pgInfo
	 * @param dataProtocol 자료변환시 통신규약
	 * @param mappingOption 자료변환시 옵션변수
	 * @throws Throwable
	 * @since ModelWeb1.0
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public HashMap getHashMapByAndLikeByItem(IItem_Model itemInfo, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		Vector<IItem_Model>resultObj =  getDAClient().get_InfoByAndLikeByItem(null, itemInfo, orderList, pgInfo);
		return XgetHashMapFromItemList(resultObj, dataProtocol, mappingOption);
	}
	@Deprecated
	public HashMap getHashMapByAndLikeByItem(IItem_Model itemInfo, orderExpressionRelation orderList, pageExpressionRelation pgInfo, Object mappingOption) throws Throwable {
		return getHashMapByAndLikeByItem(itemInfo, orderList, pgInfo, getDefaultDataProtocol(), mappingOption);
	}
	@Deprecated
	public HashMap getHashMapByAndLikeByItem(IItem_Model itemInfo, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return getHashMapByAndLikeByItem(itemInfo, orderList, pgInfo, dataProtocol, null);
	}
	@Deprecated
	public HashMap getHashMapByAndLikeByItem(IItem_Model itemInfo, orderExpressionRelation orderList, pageExpressionRelation pgInfo) throws Throwable {
		return getHashMapByAndLikeByItem(itemInfo, orderList, pgInfo, getDefaultDataProtocol(), null);
	}
	/**
	 * 자료의 항목들을 비교하여 Or연산으로서 ((문자마당인경우)같기)연산의 자료를 얻기
	 * @param info
	 * @param orderList 정렬하는 관계객체
	 * @param pgInfo
	 * @param dataProtocol 자료변환시 통신규약
	 * @param mappingOption 자료변환시 옵션변수
	 * @throws Throwable
	 * @since ModelWeb1.0
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public String getJSonByOrEquByItem(IItem_Model itemInfo, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		Vector<IItem_Model>resultObj =  getDAClient().get_InfoByOrEquByItem(null, itemInfo, orderList, pgInfo);
		return XgetJSonFromItemList(resultObj, dataProtocol, mappingOption);
	}
	@Deprecated
	public String getJSonByOrEquByItem(IItem_Model itemInfo, orderExpressionRelation orderList, pageExpressionRelation pgInfo, Object mappingOption) throws Throwable {
		return getJSonByOrEquByItem(itemInfo, orderList, pgInfo, getDefaultDataProtocol(), mappingOption);
	}
	@Deprecated
	public String getJSonByOrEquByItem(IItem_Model itemInfo, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return getJSonByOrEquByItem(itemInfo, orderList, pgInfo, dataProtocol, null);
	}
	@Deprecated
	public String getJSonByOrEquByItem(IItem_Model itemInfo, orderExpressionRelation orderList, pageExpressionRelation pgInfo) throws Throwable {
		return getJSonByOrEquByItem(itemInfo, orderList, pgInfo, getDefaultDataProtocol(), null);
	}
	/**
	 * 자료의 항목들을 비교하여 Or연산으로서 ((문자마당인경우)같기)연산의 자료를 얻기
	 * @param info
	 * @param orderList 정렬하는 관계객체
	 * @param pgInfo
	 * @param dataProtocol 자료변환시 통신규약
	 * @param mappingOption 자료변환시 옵션변수
	 * @throws Throwable
	 * @since ModelWeb1.0
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public HashMap getHashMapByOrEquByItem(IItem_Model itemInfo, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		Vector<IItem_Model>resultObj =  getDAClient().get_InfoByOrEquByItem(null, itemInfo, orderList, pgInfo);
		return XgetHashMapFromItemList(resultObj, dataProtocol, mappingOption);
	}
	@Deprecated
	public HashMap getHashMapByOrEquByItem(IItem_Model itemInfo, orderExpressionRelation orderList, pageExpressionRelation pgInfo, Object mappingOption) throws Throwable {
		return getHashMapByOrEquByItem(itemInfo, orderList, pgInfo, getDefaultDataProtocol(), mappingOption);
	}
	@Deprecated
	public HashMap getHashMapByOrEquByItem(IItem_Model itemInfo, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return getHashMapByOrEquByItem(itemInfo, orderList, pgInfo, dataProtocol, null);
	}
	@Deprecated
	public HashMap getHashMapByOrEquByItem(IItem_Model itemInfo, orderExpressionRelation orderList, pageExpressionRelation pgInfo) throws Throwable {
		return getHashMapByOrEquByItem(itemInfo, orderList, pgInfo, getDefaultDataProtocol(), null);
	}
	/**
	 * 자료의 항목들을 비교하여 Or연산으로서 ((문자마당인경우)like)연산의 자료를 얻기
	 * @param info
	 * @param orderList 정렬하는 관계객체
	 * @param pgInfo
	 * @param dataProtocol 자료변환시 통신규약
	 * @param mappingOption 자료변환시 옵션변수
	 * @throws Throwable
	 * @since ModelWeb1.0
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public String getJSonByOrLikeByItem(IItem_Model itemInfo, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		Vector<IItem_Model>resultObj =  getDAClient().get_InfoByOrLikeByItem(null, itemInfo, orderList, pgInfo);
		return XgetJSonFromItemList(resultObj, dataProtocol, mappingOption);
	}
	@Deprecated
	public String getJSonByOrLikeByItem(IItem_Model itemInfo, orderExpressionRelation orderList, pageExpressionRelation pgInfo, Object mappingOption) throws Throwable {
		return getJSonByOrLikeByItem(itemInfo, orderList, pgInfo, getDefaultDataProtocol(), mappingOption);
	}
	@Deprecated
	public String getJSonByOrLikeByItem(IItem_Model itemInfo, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return getJSonByOrLikeByItem(itemInfo, orderList, pgInfo, dataProtocol, null);
	}
	@Deprecated
	public String getJSonByOrLikeByItem(IItem_Model itemInfo, orderExpressionRelation orderList, pageExpressionRelation pgInfo) throws Throwable {
		return getJSonByOrLikeByItem(itemInfo, orderList, pgInfo, getDefaultDataProtocol(), null);
	}
	/**
	 * 자료의 항목들을 비교하여 Or연산으로서 ((문자마당인경우)like)연산의 자료를 얻기
	 * @param info
	 * @param orderList 정렬하는 관계객체
	 * @param pgInfo
	 * @param dataProtocol 자료변환시 통신규약
	 * @param mappingOption 자료변환시 옵션변수
	 * @throws Throwable
	 * @since ModelWeb1.0
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public HashMap getHashMapByOrLikeByItem(IItem_Model itemInfo, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		Vector<IItem_Model>resultObj =  getDAClient().get_InfoByOrLikeByItem(null, itemInfo, orderList, pgInfo);
		return XgetHashMapFromItemList(resultObj, dataProtocol, mappingOption);
	}
	@Deprecated
	public HashMap getHashMapByOrLikeByItem(IItem_Model itemInfo, orderExpressionRelation orderList, pageExpressionRelation pgInfo, Object mappingOption) throws Throwable {
		return getHashMapByOrLikeByItem(itemInfo, orderList, pgInfo, getDefaultDataProtocol(), mappingOption);
	}
	@Deprecated
	public HashMap getHashMapByOrLikeByItem(IItem_Model itemInfo, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return getHashMapByOrLikeByItem(itemInfo, orderList, pgInfo, dataProtocol, null);
	}
	@Deprecated
	public HashMap getHashMapByOrLikeByItem(IItem_Model itemInfo, orderExpressionRelation orderList, pageExpressionRelation pgInfo) throws Throwable {
		return getHashMapByOrLikeByItem(itemInfo, orderList, pgInfo, getDefaultDataProtocol(), null);
	}
	/**
	 * 일반(Table(표))의 같기질문에 의하여 해당한 정보들을 얻기
	 * @param selectList 얻으려는 마당정보들(null이면 모두 얻기)
	 * @param whereList 검색하는 관계객체
	 * @param bAndOr
	 * @param orderList 정렬하는 관계객체
	 * @param pgInfo
	 * @param dataProtocol 자료변환시 통신규약
	 * @param mappingOption 자료변환시 옵션변수
	 * @throws Throwable
	 * @since ModelWeb1.0
	 */
	@SuppressWarnings("unchecked")
	public String getJSonListByEqu(whereExpressionRelation whereList, boolean bAndOr, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		Vector<IItem_Model>resultObj =  getDAClient().get_ListByEqu(null, whereList, bAndOr, orderList, pgInfo);
		return XgetJSonFromItemList(resultObj, dataProtocol, mappingOption);
	}
	public String getJSonListByEqu(whereExpressionRelation whereList, boolean bAndOr, orderExpressionRelation orderList, pageExpressionRelation pgInfo, Object mappingOption) throws Throwable {
		return getJSonListByEqu(whereList, bAndOr, orderList, pgInfo, getDefaultDataProtocol(), mappingOption);
	}
	public String getJSonListByEqu(whereExpressionRelation whereList, boolean bAndOr, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return getJSonListByEqu(whereList, bAndOr, orderList, pgInfo, dataProtocol, null);
	}
	public String getJSonListByEqu(whereExpressionRelation whereList, boolean bAndOr, orderExpressionRelation orderList, pageExpressionRelation pgInfo) throws Throwable {
		return getJSonListByEqu(whereList, bAndOr, orderList, pgInfo, getDefaultDataProtocol(), null);
	}
	/**
	 * 일반(Table(표))의 같기질문에 의하여 해당한 정보들을 얻기
	 * @param selectList 얻으려는 마당정보들(null이면 모두 얻기)
	 * @param whereList 검색하는 관계객체
	 * @param bAndOr
	 * @param orderList 정렬하는 관계객체
	 * @param pgInfo
	 * @param dataProtocol 자료변환시 통신규약
	 * @param mappingOption 자료변환시 옵션변수
	 * @throws Throwable
	 * @since ModelWeb1.0
	 */
	@SuppressWarnings("unchecked")
	public HashMap getHashMapInfoByEqu(whereExpressionRelation whereList, boolean bAndOr, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		Vector<IItem_Model>resultObj =  getDAClient().get_ListByEqu(null, whereList, bAndOr, orderList, pgInfo);
		return XgetHashMapFromItemList(resultObj, dataProtocol, mappingOption);
	}
	public HashMap getHashMapInfoByEqu(whereExpressionRelation whereList, boolean bAndOr, orderExpressionRelation orderList, pageExpressionRelation pgInfo, Object mappingOption) throws Throwable {
		return getHashMapInfoByEqu(whereList, bAndOr, orderList, pgInfo, getDefaultDataProtocol(), mappingOption);
	}
	public HashMap getHashMapInfoByEqu(whereExpressionRelation whereList, boolean bAndOr, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return getHashMapInfoByEqu(whereList, bAndOr, orderList, pgInfo, dataProtocol, null);
	}
	public HashMap getHashMapInfoByEqu(whereExpressionRelation whereList, boolean bAndOr, orderExpressionRelation orderList, pageExpressionRelation pgInfo) throws Throwable {
		return getHashMapInfoByEqu(whereList, bAndOr, orderList, pgInfo, getDefaultDataProtocol(), null);
	}
	/**
	 * 일반(Table(표))의 like질문에 의하여 해당한 정보들을 얻기
	 * @param selectList 얻으려는 마당정보들(null이면 모두 얻기)
	 * @param likeList
	 * @param bAndOr
	 * @param orderList 정렬하는 관계객체
	 * @param pgInfo
	 * @param dataProtocol 자료변환시 통신규약
	 * @param mappingOption 자료변환시 옵션변수
	 * @throws Throwable
	 * @since ModelWeb1.0
	 */
	@SuppressWarnings("unchecked")
	public String getJSonListByLike(whereExpressionRelation likeList, boolean bAndOr, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		Vector<IItem_Model>resultObj =  getDAClient().get_ListByLike(null, likeList, bAndOr, orderList, pgInfo);
		return XgetJSonFromItemList(resultObj, dataProtocol, mappingOption);
	}
	public String getJSonListByLike(whereExpressionRelation likeList, boolean bAndOr, orderExpressionRelation orderList, pageExpressionRelation pgInfo, Object mappingOption) throws Throwable {
		return getJSonListByLike(likeList, bAndOr, orderList, pgInfo, getDefaultDataProtocol(), mappingOption);
	}
	public String getJSonListByLike(whereExpressionRelation likeList, boolean bAndOr, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return getJSonListByLike(likeList, bAndOr, orderList, pgInfo, dataProtocol, null);
	}
	public String getJSonListByLike(whereExpressionRelation likeList, boolean bAndOr, orderExpressionRelation orderList, pageExpressionRelation pgInfo) throws Throwable {
		return getJSonListByLike(likeList, bAndOr, orderList, pgInfo, getDefaultDataProtocol(), null);
	}
	/**
	 * 일반(Table(표))의 like질문에 의하여 해당한 정보들을 얻기
	 * @param selectList 얻으려는 마당정보들(null이면 모두 얻기)
	 * @param likeList
	 * @param bAndOr
	 * @param orderList 정렬하는 관계객체
	 * @param pgInfo
	 * @param dataProtocol 자료변환시 통신규약
	 * @param mappingOption 자료변환시 옵션변수
	 * @throws Throwable
	 * @since ModelWeb1.0
	 */
	@SuppressWarnings("unchecked")
	public HashMap getHashMapInfoByLike(whereExpressionRelation likeList, boolean bAndOr, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		Vector<IItem_Model>resultObj =  getDAClient().get_ListByLike(null, likeList, bAndOr, orderList, pgInfo);
		return XgetHashMapFromItemList(resultObj, dataProtocol, mappingOption);
	}
	public HashMap getHashMapInfoByLike(whereExpressionRelation likeList, boolean bAndOr, orderExpressionRelation orderList, pageExpressionRelation pgInfo, Object mappingOption) throws Throwable {
		return getHashMapInfoByLike(likeList, bAndOr, orderList, pgInfo, getDefaultDataProtocol(), mappingOption);
	}
	public HashMap getHashMapInfoByLike(whereExpressionRelation likeList, boolean bAndOr, orderExpressionRelation orderList, pageExpressionRelation pgInfo, key_WebDataProtocol_Model dataProtocol) throws Throwable {
		return getHashMapInfoByLike(likeList, bAndOr, orderList, pgInfo, dataProtocol, null);
	}
	public HashMap getHashMapInfoByLike(whereExpressionRelation likeList, boolean bAndOr, orderExpressionRelation orderList, pageExpressionRelation pgInfo) throws Throwable {
		return getHashMapInfoByLike(likeList, bAndOr, orderList, pgInfo, getDefaultDataProtocol(), null);
	}
	/**
	 * 일반(Table(표))의 모든형태의 select명령실행하여 hash에의한 vector정보로서 얻기
	 * @param whereList 검색하는 관계객체
	 * @param orderList 정렬하는 관계객체
	 * @param pgInfo
	 * @param mappingOption 자료변환시 옵션변수
	 * @param beanClass 얻어낼 bean형식의 자료클라스객체
	 * @throws Throwable
	 */
	@SuppressWarnings("unchecked")
	public Vector<HashMap> getVHInfo(selectExpressionRelation selectList, whereExpressionRelation whereList, orderExpressionRelation orderList, pageExpressionRelation pgInfo)throws Throwable {
		return (Vector)getDAClient().getVH_Info(selectList, whereList, orderList, pgInfo);
	}
	/**
	 * 일반(Table(표))의 모든형태의 select명령실행하여 hash에의한 vector정보로서 얻기
	 * @param whereList 검색하는 관계객체
	 * @param orderList 정렬하는 관계객체
	 * @param pgInfo
	 * @param mappingOption 자료변환시 옵션변수
	 * @param beanClass 얻어낼 bean형식의 자료클라스객체
	 * @throws Throwable
	 */
	@SuppressWarnings("unchecked")
	public Document getXDInfo(selectExpressionRelation selectList, whereExpressionRelation whereList, orderExpressionRelation orderList, pageExpressionRelation pgInfo, String strXmlRoot, String strXmlRow)throws Throwable {
		return getDAClient().getXD_Info(selectList, whereList, orderList, pgInfo, strXmlRoot, strXmlRow);
	}
	/**
	 * SQL문을 실행하기
	 * @param query 질의식
	 * @param setExecuteList 설정한 변수들
	 * @param bSelectOrInsertOrUpdate Get Or Set?
	 * @return 실행한 결과 
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 18 AM 10:32:58
	 * @since ModelWeb 1.0
	 */
	@SuppressWarnings("unchecked")
	public Object execute_Query(String query, executeExpressionRelation setExecuteList, int bSelectOrInsertOrUpdate, Class beanClass, Object mappingOption)throws Throwable {
		Object result = getDAClient().execute_Query(query, setExecuteList, bSelectOrInsertOrUpdate);
		if(result!=null) {
			if(result instanceof Vector) {
				if(((Vector)(result)).size() > 0 && ((Vector)(result)).get(0).getClass().equals(getItemModel().getClass())){
					return (Vector)XgetDataListFromItemList(getDAClient().get_Info(null, null, null, null), beanClass, mappingOption);
				}
			}
		}
		return result;
	}
	public Object execute_Query(String query, executeExpressionRelation setExecuteList, int bSelectOrInsertOrUpdate, Class beanClass)throws Throwable {
		return execute_Query(query, setExecuteList, bSelectOrInsertOrUpdate, beanClass, null);
	}
	/**
	 * SQL문을 실행하기
	 * @param query 질의식
	 * @param setExecuteList 설정한 변수들
	 * @param bSelectOrInsertOrUpdate Get Or Set?
	 * @return 실행한 결과 
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 18 AM 10:32:58
	 * @since ModelWeb 1.0
	 */
	@SuppressWarnings("unchecked")
	public Object execute_Query(String query, executeExpressionRelation setExecuteList, int bSelectOrInsertOrUpdate)throws Throwable {
		Object result = getDAClient().execute_Query(query, setExecuteList, bSelectOrInsertOrUpdate);
		return result;
	}
	/**
	 * SQL문을 실행하기
	 * @param query 질의식
	 * @param bSelectOrInsertOrUpdate Get Or Set?
	 * @return 실행한 결과 
	 * @throws Throwable
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 26 PM 8:40:59
	 * @since ModelWeb 1.0
	 */
	@SuppressWarnings("unchecked")
	public Object execute_Query(String query, int bSelectOrInsertOrUpdate, Class beanClass, Object mappingOption)throws Throwable {
		Object result = getDAClient().execute_Query(query, bSelectOrInsertOrUpdate);
		if(result!=null) {
			if(result instanceof Vector) {
				if(((Vector)(result)).size() > 0 && ((Vector)(result)).get(0).getClass().equals(getItemModel().getClass())){
					return (Vector)XgetDataListFromItemList(getDAClient().get_Info(null, null, null, null), beanClass, mappingOption);
				}
			}
		}
		return result;
	}
	public Object execute_Query(String query, int bSelectOrInsertOrUpdate, Class beanClass)throws Throwable {
		return execute_Query(query, bSelectOrInsertOrUpdate, beanClass, null);
	}
	/**
	 * SQL문을 실행하기
	 * @param query 질의식
	 * @param bSelectOrInsertOrUpdate Get Or Set?
	 * @return 실행한 결과 
	 * @throws Throwable
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 26 PM 8:40:59
	 * @since ModelWeb 1.0
	 */
	@SuppressWarnings("unchecked")
	public /*synchronized//*/ Object execute_Query(String query, int bSelectOrInsertOrUpdate)throws Throwable {
		Object result = getDAClient().execute_Query(query, bSelectOrInsertOrUpdate);
		return result;
	}
	/***************************mapping************************/
	/***************************mapping************************/
	/***************************mapping************************/
	//	bean
	// -- getData
	// -- 		set
	@SuppressWarnings("unchecked")
	public Vector<Object> XgetDataListFromItemList(Vector<IItem_Model> itemList, Class beanClass, Object mappingOption)throws Throwable {
		return (Vector)get_BeanObjListFromItem((Vector)itemList, beanClass, mappingOption, "XgetDataFromItem");
	}
	public void XsetDataFromItem(Object data, IItem_Model item)throws Throwable {
		set_BeanObjFromItem(data, item);
	}
	public Object XgetDataFromItem(IItem_Model item, Class beanClass, int nArrayIndex, Object mappingOption)throws Throwable {
		return (Object)get_BeanObjFromItem(item, beanClass);
	}
	// -- getItem
	@SuppressWarnings("unchecked")
	public Vector<IItem_Model> XgetItemListFromDataList(Vector<Object> dataList, Object mappingOption)throws Throwable {
		return (Vector)get_ItemListFromBean(dataList, mappingOption, "XgetItemFromData");
	}
	public IItem_Model XgetItemFromData(Object data, Object mappingOption)throws Throwable {
		return(IItem_Model)get_ItemFromBean(data);
	}
	// --getXml
	@SuppressWarnings("unchecked")
	public String XgetXmlFromDataList(Vector<Object> listObj, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		Vector<IItem_Model> result = (Vector)XgetItemListFromDataList(listObj, mappingOption);
		return XgetXmlFromItemList((Vector)result, dataProtocol, mappingOption);
	}
	public Node XgetXmlDocFromData(Object obj, Document doc, int nArrayIndex, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		IItem_Model item = XgetItemFromData(obj, mappingOption);
		return XgetXmlDocFromItem(item, doc, nArrayIndex, dataProtocol, mappingOption);
	}
	public String XgetXmlFromData(Object obj, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		IItem_Model result = XgetItemFromData(obj, mappingOption);
		return XgetXmlFromItem(result, dataProtocol, mappingOption);
	}
	@SuppressWarnings("unchecked")
	public String XgetXmlFromItemList(Vector<IItem_Model> itemList, key_WebDataProtocol_Model dataProtocol, Object mappingOption)throws Throwable {
		Document doc = XgetXmlDocFromItemList((Vector)itemList, dataProtocol, mappingOption);
		return get_XmlFromXmlNode(doc);
	}
	public Document XgetXmlDocFromItemList(Vector<IItem_Model> itemList, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		return get_XmlDocFromItemList(itemList, dataProtocol, mappingOption, "XgetXmlDocFromItem");
	}
	public String XgetXmlFromItem(IItem_Model item, key_WebDataProtocol_Model dataProtocol, Object mappingOption)throws Throwable {
		return get_XmlFromItem(item, null, dataProtocol, mappingOption, "XgetXmlDocFromItem");
	}
	public Node XgetXmlDocFromItem(IItem_Model item, Document doc, int nArrayIndex, key_WebDataProtocol_Model dataProtocol, Object mappingOption)throws Throwable {
		return get_XmlNodeFromItem(item, doc, dataProtocol);
	}
	// --getData
	@SuppressWarnings("unchecked")
	public Vector<Object> XgetDataListFromXml(String strXml, Class beanClass, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable{
		Vector<IItem_Model> itemList = XgetItemListFromXml(strXml, dataProtocol, beanClass);
		if(itemList == null) { throw new TruegardenerException("Xml자료를 얻을수 없습니다!! - BusinessFacade층  getDataListFromXml()에서..."); }
		return (Vector)XgetDataListFromItemList(itemList, beanClass, mappingOption);
	}
	public Object XgetDataFromXml(String strXml, Class beanClass, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable{
		IItem_Model item = XgetItemFromXml(strXml, dataProtocol, mappingOption);
		if(item == null) { throw new TruegardenerException("Xml자료를 얻을수 없습니다!! - BusinessFacade층  XgetDataFromXml()에서..."); }
		return XgetDataFromItem(item, beanClass, 0, mappingOption);
	}
	public Object XgetDataFromXmlNode(Node nodeXml, Class beanClass, key_WebDataProtocol_Model dataProtocol, Object mappingOption)throws Throwable {
		IItem_Model item = XgetItemFromXmlNode(nodeXml, dataProtocol, mappingOption);
		if(item == null) { throw new TruegardenerException("Xml자료에 해당한 자료기지자료를 얻을수 없습니다!! - BusinessFacade층  XgetDataFromXmlNode()에서..."); }
		return XgetDataFromItem(item, beanClass, 0, mappingOption);
	}
	@SuppressWarnings("unchecked")
	public Vector<IItem_Model> XgetItemListFromXml(String strXml, key_WebDataProtocol_Model dataProtocol, Object mappingOption)throws Throwable {
		return (Vector)get_ItemListFromXml(strXml, dataProtocol, mappingOption, "XgetItemFromXmlNode");
	}
	public IItem_Model XgetItemFromXml(String strXml, key_WebDataProtocol_Model dataProtocol, Object mappingOption)throws Throwable {
		DOMParser dom = new DOMParser();
		StringReader readerStr = new StringReader(strXml);
		InputSource source = new InputSource(readerStr);
		dom.parse(source);
		Document docItem = dom.getDocument();
		Element eleDoc = docItem.getDocumentElement();
		//여러가지 방법으로 얻을수 있음
		IItem_Model result = (IItem_Model)XgetItemFromXmlNode(eleDoc, dataProtocol, mappingOption);
		return result;
	}
	public IItem_Model XgetItemFromXmlNode(Node nodeXml, key_WebDataProtocol_Model dataProtocol, Object mappingOption)throws Throwable {
		return (IItem_Model)get_ItemFromXmlNode(nodeXml, dataProtocol);
	}
	//getJSon
	//getData
	public String XgetJSonFromData(Object obj, Object mappingOption) throws Throwable {
		IItem_Model result = XgetItemFromData(obj, mappingOption);
		return XgetJSonFromItem(result, mappingOption);
	}
	public String XgetJSonFromItem(IItem_Model item, Object mappingOption)throws Throwable {
		return get_JSonFromItem(item, mappingOption, "XgetHashMapFromItem");
	}
	@SuppressWarnings("unchecked")
	public Vector<Object> XgetDataListFromJSon(String strJSon, Class beanClass, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable{
		Vector<IItem_Model> itemList = XgetItemListFromJSon(strJSon, dataProtocol, mappingOption);
		if(itemList == null) { throw new TruegardenerException("JSon에 해당한 자료목록을 얻을수 없습니다!! - BusinessFacade층  XgetDataListFromJSon()에서..."); }
		return (Vector)XgetDataListFromItemList(itemList, beanClass, mappingOption);
	}
	@SuppressWarnings("unchecked")
	public String XgetJSonFromDataList(Vector<Object> listObj, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
		Vector<IItem_Model> result = (Vector)XgetItemListFromDataList(listObj, mappingOption);
		return XgetJSonFromItemList((Vector)result, dataProtocol, mappingOption);
	}
	@SuppressWarnings("unchecked")
	public String XgetJSonFromItemList(Vector<IItem_Model> itemList, key_WebDataProtocol_Model dataProtocol, Object mappingOption)throws Throwable {
		HashMap result = XgetHashMapFromItemList((Vector)itemList, dataProtocol, mappingOption);
		TruegardenerJSONWriter write = new TruegardenerJSONWriter();
		return write.write(result);
	}
	public HashMap XgetHashMapFromItemList(Vector<IItem_Model> itemList, key_WebDataProtocol_Model dataProtocol, Object mappingOption)throws Throwable {
		return get_HashMapFromItemList(itemList, dataProtocol, mappingOption, "XgetHashMapFromItem");
	}
	public HashMap XgetHashMapFromItem(IItem_Model item, int nArrayIndex, Object mappingOption) throws Throwable {
		HashMap result = get_HashMapFromItem(item);
		return result;
	}
	public HashMap XgetHashMapFromData(Object obj, int nArrayIndex, Object mappingOption) throws Throwable {
		IItem_Model item = XgetItemFromData(obj, mappingOption);
		return XgetHashMapFromItem(item, nArrayIndex, mappingOption);
	}
	public Object XgetDataFromJSon(String strJSon, Class beanClass, Object mappingOption) throws Throwable{
		IItem_Model item = XgetItemFromJSon(strJSon, mappingOption);
		if(item == null) { throw new TruegardenerException("JSon자료에 해당한 자료를 얻을수 없습니다!! - BusinessFacade층  XgetDataFromJSon()에서..."); }
		return XgetDataFromItem(item, beanClass, 0, mappingOption);
	}
	public Object XgetDataFromHashMap(HashMap hashitem, Class beanClass, Object mappingOption)throws Throwable{ 
		IItem_Model result = (IItem_Model)XgetItemFromHashMap(hashitem, mappingOption);
		if(hashitem == null) { throw new TruegardenerException("하쉬맵에 해당한 자료를 얻을수 없습니다!! - BusinessFacade층  XgetDataFromHashMap()에서..."); }
		return XgetDataFromItem(result, beanClass, 0, mappingOption);
	}
	@SuppressWarnings("unchecked")
	public Vector<IItem_Model> XgetItemListFromJSon(String strJSon, key_WebDataProtocol_Model dataProtocol, Object mappingOption)throws Throwable {
		return (Vector)get_ItemListFromJSon(strJSon, dataProtocol, mappingOption, "XgetItemFromHashMap");
	}
	public IItem_Model XgetItemFromJSon(String strJSon, Object mappingOption)throws Throwable {
		HashMap resultJSon = get_HashMapFromString(strJSon);
		IItem_Model result = (IItem_Model)XgetItemFromHashMap(resultJSon, mappingOption);
		return result;
	}
	public IItem_Model XgetItemFromHashMap(HashMap hashitem, Object mappingOption)throws Throwable {
		return (IItem_Model)get_ItemFromHashMapNode(hashitem);
	}
}

