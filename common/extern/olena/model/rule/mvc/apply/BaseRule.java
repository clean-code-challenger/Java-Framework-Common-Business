package common.extern.olena.model.rule.mvc.apply;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Calendar;
import java.util.HashMap;

import org.w3c.dom.Node;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.RegularExpression;

import common.extern.olena.model.dataaccess.daFatherModel;
import common.extern.olena.model.dataaccess.mvc.apply.BaseDA;
import common.extern.olena.model.dataaccess.mvc.base.IItem_Model;
import common.extern.olena.model.error.TruegardenerException;
import common.extern.olena.model.infos.dataTransport.key_WebDataProtocol_Model;
import common.extern.olena.model.infos.universalResult.infoUniversalReturnCode;
import common.extern.olena.model.rule.middleFatherModel;
import common.extern.olena.model.rule.mvc.apply.inter.IBaseRule;
import common.extern.olena.model.rule.mvc.apply.inter.IMapping_Model;
import common.extern.olena.model.systemframework.Applicationlog;
import common.extern.utils.DateTime;

public abstract class BaseRule extends rule_Model implements IBaseRule {
	
	public BaseRule(middleFatherModel parentRule, daFatherModel parentDA, IItem_Model item) {
		super(parentRule, parentDA, item);
	}
    /**
	 */
	@SuppressWarnings("unused")
	private static String REGEXP_ISVALIDEMAIL = "^\\w+((-\\w+)|(\\.\\w+))*\\@\\w+((\\.|-)\\w+)*\\.\\w+$";

	public static String m_strInvalidField  = "Invalid Field.";
	public static String m_strInvalidFields  = "Invalid Field.";

	public void setError(String strErrorMsg) {
		Applicationlog.LogTruegardenerError(strErrorMsg);
	}
	/**
	 * @author Olena.Zagreba 
	 * @version  2013-04-02 10:14:11
	 * @throws Throwable 
	 */
	protected abstract BaseDA getDAClient() throws Throwable;
	/**
	 */
	protected abstract IMapping_Model getMappingClient() throws Throwable;
    public long insertData(Object data, Object mappingOption) throws Throwable {
    	if (data == null) {
    		return 0; 
    	}
    	IItem_Model item = (IItem_Model)getMappingClient().XgetItemFromData(data, mappingOption);
    	return insertItem(item);
    }
	public long insertXml(String strXml, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
	
    	if (strXml == null) {
    		return 0; 
    	}
    	IItem_Model item = (IItem_Model)getMappingClient().XgetItemFromXml(strXml, dataProtocol, mappingOption);
    	return insertItem(item);
	}
	public long insertXml(Node nodeXml, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
    	if (nodeXml == null) {
    		return 0;
    	}
    	IItem_Model item = (IItem_Model)getMappingClient().XgetItemFromXmlNode(nodeXml, dataProtocol, mappingOption);
    	return insertItem(item);
	}
	public long insertJSon(String strJSon, Object mappingOption) throws Throwable {
    	if (strJSon == null) {
    		return 0; 
    	}
    	IItem_Model item = (IItem_Model)getMappingClient().XgetItemFromJSon(strJSon, mappingOption);
    	return insertItem(item);
	}
	public long insertHashMap(HashMap hashInfo, Object mappingOption) throws Throwable {
    	if (hashInfo == null) {
    		return 0;
    	}
    	IItem_Model item = (IItem_Model)getMappingClient().XgetItemFromHashMap(hashInfo, mappingOption);
		if (item == null) {
			throw new TruegardenerException("삽입할 자료기지대상이 null 입니다. - BusinessRule층  insertData()에서...", infoUniversalReturnCode.RESULT_ERROR_MAPPING);
		}
		return insertItem(item);
	}
	public long insertItem(IItem_Model item) throws Throwable {
		if (item == null) {
			throw new TruegardenerException("삽입할 자료기지대상이 null 입니다. - BusinessRule층  insertData()에서...", infoUniversalReturnCode.RESULT_ERROR_MAPPING);
		}
		boolean bIsValid  = CheckValidate(item);
		if (bIsValid) {
			return onInsertItem(item);
		}
		else {
			throw new TruegardenerException("자료기지에 넣을수 없는 정보입니다. - BusinessRule층  CheckValidateItem()에서...", infoUniversalReturnCode.RESULT_ERROR_INVALID_TABLEITEM);
		}
	}
    public boolean updateData(Object data, Object mappingOption) throws Throwable {
    	if (data == null) {
    		throw new TruegardenerException("갱신할 대상이 null 입니다. - BusinessRule층  insertData()에서...", infoUniversalReturnCode.RESULT_ERROR_NULL);
    	}
    	IItem_Model item = (IItem_Model)getMappingClient().XgetItemFromData(data, mappingOption);
    	return updateItem(item);
    }
    public boolean updateXml(String strXml, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
    	IItem_Model item = (IItem_Model)getMappingClient().XgetItemFromXml(strXml, dataProtocol, mappingOption);
    	return updateItem(item);
    }
    public boolean updateXml(Node nodeXml, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable {
    	IItem_Model item = (IItem_Model)getMappingClient().XgetItemFromXmlNode(nodeXml, dataProtocol, mappingOption);
    	return updateItem(item);
    }
    public boolean updateJSon(String strJSon, Object mappingOption) throws Throwable {
    	IItem_Model item = (IItem_Model)getMappingClient().XgetItemFromJSon(strJSon, mappingOption);
    	return updateItem(item);
    }
    public boolean updateHashMap(HashMap hashInfo, Object mappingOption) throws Throwable {
    	IItem_Model item = (IItem_Model)getMappingClient().XgetItemFromHashMap(hashInfo, mappingOption);
    	return updateItem(item);
	}
    public boolean updateItem(IItem_Model item) throws Throwable {
    	if (item == null) {
    		throw new TruegardenerException("갱신할 자료기지대상이 null 입니다. - BusinessRule층  updateData()에서...", infoUniversalReturnCode.RESULT_ERROR_MAPPING);
    	}
    	// 자료기지항목에 대한 핵심검사를 진행
    	boolean bIsValid  = CheckValidate(item);
    	if (bIsValid) {
    		// 보충적인 검사를 진행
    		// 다른 오유가 없다면 갱신하기.
    		return onUpdateItem(item);
    	}
        return false;
	}
    public boolean deleteData(long nDataNumber) throws Throwable {
        // 예비검사를 진행한다.
    	if (nDataNumber <= 0) {
    		throw new TruegardenerException("삭제할 대상의 주키값이 1이상이여야 합니다. - BusinessRule층  deleteData()에서...", infoUniversalReturnCode.RESULT_ERROR_INVALID_PRIMARYID);
    	}
		//변환된 자료기지항목에 핵심검사를 진행
		boolean result = onDeleteItem(nDataNumber);
		//여러가지 추가설정을 진행하여야 함
        return result;
    }
    public boolean CheckValidate(IItem_Model item) throws Throwable {
        boolean isValid = onCheckValidate(item);
        if (!isValid) {
    		throw new TruegardenerException("무효한 마당입니다. - BusinessRule층  CheckValidate()에서...", infoUniversalReturnCode.RESULT_ERROR_INVALID_DATA);
        }
        return isValid;
    }
    public boolean onCheckValidate(IItem_Model item) throws Throwable {
        return true;
    }
	public long onInsertItem(IItem_Model item) throws Throwable {
		return (Long)getDAClient().set_Insert(item);
	}
	public boolean onUpdateItem(IItem_Model item) throws Throwable {
		return (getDAClient().set_Update(item) == 1) ? true : false;
	}
	public boolean onDeleteItem(long nNo) throws Throwable {
		return (getDAClient().set_Delete(nNo) == 1) ? true : false;
	}
    /**
	 * Function IsValidField:
	 *   정의된 item자료 마당이 규칙에 따르는가를 확인한다.
	 * Returns:
	 *   마당이 규칙에 어긋난다면: False
	 * Parameters:
	 *   (in)  dritem행: 확인할 item표의 item자료행
	 *   (in)  마당이름: 확인할 item자료의 마당
	 *   (in)  최대길이: 마당의 최대길이
	 */
    protected boolean IsValidField(IItem_Model item, int nFieldIndex, int nMaxLen) throws Throwable{
		return IsValidField(item, nFieldIndex, nMaxLen, 0);
	}
    /** 
	 * Function IsValidField:
	 *   정의된 item자료 마당이 규칙에 따르는가를 확인한다.
	 * Returns:
	 *   마당이 규칙에 어긋난다면: False
	 * Parameters:
	 *   (in)  item: 확인할 item표의 item자료행
	 *   (in)  마당이름: 확인할 item자료의 마당
	 *   (in)  최대길이: 마당의 최대길이
	 *   (in)  최소길이: 마당의 최소길이
	 */
	protected boolean IsValidField(IItem_Model item, int nFieldIndex, int nMaxLen, int nMinLen) throws Throwable{
		if(item.isAvaiable(nFieldIndex)) {
			if(item.getFieldType(nFieldIndex)==Types.VARCHAR || item.getFieldType(nFieldIndex)==Types.LONGVARCHAR ) {
				int nLen = ((String)item.getAvaiable(nFieldIndex)).length();
				if ((nLen < nMinLen) || (nLen > nMaxLen)) {
					Applicationlog.LogTruegardener(item.getFieldLabel(nFieldIndex) + "마당의 길이가 무효합니다. - BusinessRule층  CheckValidate()에서...");
					return false;
				}
			}
			else if(item.getFieldType(nFieldIndex)==Types.BIGINT || item.getFieldType(nFieldIndex)==Types.INTEGER || 
					item.getFieldType(nFieldIndex)==Types.SMALLINT || item.getFieldType(nFieldIndex)==Types.TINYINT) {
				int nLen = String.valueOf(((Long)item.getAvaiable(nFieldIndex))).length();
				if ((nLen < nMinLen) || (nLen > nMaxLen)) {
					Applicationlog.LogTruegardener(item.getFieldLabel(nFieldIndex) + "마당의 길이가 무효합니다. - BusinessRule층  CheckValidate()에서...");
					return false;
				}
			}
		}
		return true;
	}
	/**
	 * Function IsValidField:
	 *   정의된 item자료 마당이 규칙에 따르는가를 확인한다.
	 * Returns:
	 *   마당이 규칙에 어긋난다면: False
	 * Parameters:
	 *   (in)  dritem행: 확인할 item표의 item자료행
	 *   (in)  마당이름: 확인할 item자료의 마당
	 *   (in)  최대시간: 마당의 최대유효시간
	 *   (in) 시분초초기화: 최대, 최소값을 시분초를 0으로 설정하겠는가?(마당형이 시간형일때 무효)  
	 */
	protected boolean IsValidField(IItem_Model item, int nFieldIndex, Calendar dtMaxDate, boolean bInitHMS) throws Throwable{
		return IsValidField(item, nFieldIndex, dtMaxDate, null, bInitHMS);
	}
	/** 
	 * Function IsValidField:
	 *   정의된 item자료 마당이 규칙에 따르는가를 확인한다.
	 * Returns:
	 *   마당이 규칙에 어긋난다면: False
	 * Parameters:
	 *   (in)  item: 확인할 item표의 item자료행
	 *   (in)  마당인덱스: 확인할 item자료의 마당
	 *   (in)  최대날자: 마당의 최대유효시간
	 *   (in)  최소날자: 마당의 최소유효시간
	 *   (in)  시분초초기화: 최대, 최소값을 시분초를 0으로 설정하겠는가?(마당형이 시간형일때 무효)
	 */
	protected boolean IsValidField(IItem_Model item, int nFieldIndex, Calendar dtMaxDate, Calendar dtMinDate, boolean bInitHMS) throws Throwable {
		boolean result = true;
		if(item.isAvaiable(nFieldIndex)) {
			switch(item.getFieldType(nFieldIndex)){
				case Types.TIME:{
					Time objResult = (Time)item.getAvaiable(nFieldIndex);
					if(objResult!=null) {
						if(dtMaxDate!=null) {
							if(DateTime.getTimeFromCalendar(dtMaxDate).after(objResult)){
								result = false;
								break;
							}
						}
						if(dtMinDate!=null) {
							if(DateTime.getTimeFromCalendar(dtMinDate).before(objResult)){
								result = false;
								break;
							}
						}
					}
					break;
				}
				case Types.DATE:{
					Date objResult = (Date)item.getAvaiable(nFieldIndex);
					if(objResult!=null) {
						if(dtMaxDate!=null) {
							Calendar dtMaxDateInitHMS = (Calendar)dtMaxDate.clone();
							if(bInitHMS) {
								dtMaxDateInitHMS.set(Calendar.HOUR_OF_DAY, 0);
								dtMaxDateInitHMS.set(Calendar.MINUTE, 0);
								dtMaxDateInitHMS.set(Calendar.SECOND, 0);
								dtMaxDateInitHMS.set(Calendar.MILLISECOND, 0);
							}
							if(objResult.after(DateTime.getDateFromCalendar(dtMaxDateInitHMS))){
								result = false;
								break;
							}
						}
						if(dtMinDate!=null) {
							Calendar dtMinDateInitHMS = (Calendar)dtMinDate.clone();
							if(bInitHMS) {
								dtMinDateInitHMS.set(Calendar.HOUR_OF_DAY, 0);
								dtMinDateInitHMS.set(Calendar.MINUTE, 0);
								dtMinDateInitHMS.set(Calendar.SECOND, 0);
								dtMinDateInitHMS.set(Calendar.MILLISECOND, 0);
							}
							if(objResult.before(DateTime.getDateFromCalendar(dtMinDateInitHMS))){
								result = false;
								break;
							}
						}
					}
					break;
				}
				case Types.TIMESTAMP:{
					Timestamp objResult = (Timestamp)item.getAvaiable(nFieldIndex);
					if(objResult!=null) {
						if(dtMaxDate!=null) {
							Calendar dtMaxDateInitHMS = (Calendar)dtMaxDate.clone();
							if(bInitHMS) {
								dtMaxDateInitHMS.set(Calendar.HOUR_OF_DAY, 0);
								dtMaxDateInitHMS.set(Calendar.MINUTE, 0);
								dtMaxDateInitHMS.set(Calendar.SECOND, 0);
								dtMaxDateInitHMS.set(Calendar.MILLISECOND, 0);
							}
							if(objResult.after(DateTime.getTimeStampFromCalendar(dtMaxDateInitHMS))){
								result = false;
								break;
							}
						}
						if(dtMinDate!=null) {
							Calendar dtMinDateInitHMS = (Calendar)dtMinDate.clone();
							if(bInitHMS) {
								dtMinDateInitHMS.set(Calendar.HOUR_OF_DAY, 0);
								dtMinDateInitHMS.set(Calendar.MINUTE, 0);
								dtMinDateInitHMS.set(Calendar.SECOND, 0);
								dtMinDateInitHMS.set(Calendar.MILLISECOND, 0);
							}
							if(objResult.before(DateTime.getTimeStampFromCalendar(dtMinDateInitHMS))){
								result = false;
								break;
							}
						}
					}
					break;
				}
				default: {
					return false;
				}
			}
		}
		if(!result) {
			Applicationlog.LogTruegardener(item.getFieldLabel(nFieldIndex) + "마당의 시간이 무효합니다. - BusinessRule층  CheckValidate()에서...");
		}
		return result;
	}
	/**
	 * Function IsValidEmail:
	 *   전자우편을 확인한다. 전자우편의 양식 : (a@b.c) 
	 *     RFC822 과 완전히 일치한다.
	 * Returns:
	 *   전자우편주소가 규칙에 어긋난다면: False
	 * Parameters:
	 *   (in)  item : 확인할 item표의 item자료행
	 *   (in)  nFieldIndex 마당인덱스: 확인할 item자료의 마당 
	 *   (in)  nMaxLen 최대길이: 마당의 최대길이
	 *   (out) boolean : 성공인가?
	 */
	protected boolean IsValidEmail(IItem_Model item, int nFieldIndex, int nMaxLen) throws Throwable {
		boolean isValid = true;
		if(item.isAvaiable(nFieldIndex)) {
			if(item.getFieldType(nFieldIndex)==Types.VARCHAR || item.getFieldType(nFieldIndex)==Types.LONGVARCHAR ) {
				String strEMail = (String)item.getAvaiable(nFieldIndex);
				//예비검사를 진행한다.
				if (strEMail.length() <= 0) {
					Applicationlog.LogTruegardener("전자우편이 비였습니다. - BusinessRule층  IsValidEmail()에서...");
					return false;
				}
				// 전자우편의 길이는 nMaxLen을 넘지 말아야 한다.
				isValid = IsValidField(item, nFieldIndex, nMaxLen);
				if (isValid) {
					// 정규문법으로 마당양식을 검사한다.
					isValid = (new RegularExpression(REGEXP_ISVALIDEMAIL)).matches(strEMail);
					if (!isValid) {
						Applicationlog.LogTruegardener("전자우편형식이 틀렸습니다. - BusinessRule층  IsValidEmail()에서...");
						return false;
					}
				}
				else {
					return false;
				}
			}
		}
		return true;
	}
}
