package common.extern.olena.model.model.result;

import common.extern.olena.model.error.TruegardenerException;
import common.extern.olena.model.error.dataaccess.TruegardenerDBAcessException;
import common.extern.olena.model.infos.universalResult.infoUniversalReturnCode;

public class univeralResult {
	
	private Object m_page = null;
	private Object m_objResult = null;
	private long m_nResult = infoUniversalReturnCode.RESULT_ERROR_UNKNOWN;
	private String  m_strErrorMsg = "";
	
	public long getResult() { return m_nResult; }
	public void setResult(long result) { m_nResult = result; }
	public Object getResultObj() { return m_objResult; }
	public void setResultObj(Object result) { m_objResult = result; setResult(infoUniversalReturnCode.RESULT_SUCCESS); }
	public Object getPage() { return m_page; }
	public void setPage(Object info) { m_page = info; }
	public String getErrorMsg() { return m_strErrorMsg; }
	public void setErrorMsg(String errorMsg) { m_strErrorMsg = errorMsg; }
	public void setErrorMsg(Throwable ex) {
		m_strErrorMsg = "Error occured. \r\n";
		m_strErrorMsg += "Error : " + ex.getMessage() + "\r\n";
	}
	public void setErrorMsg(TruegardenerException ex) {
		setErrorMsg((Throwable)ex);
		m_nResult = ex.getErrorCode();
		m_strErrorMsg += "Error code:" + ex.getErrorCode() + "\r\n";
	}
	public void setErrorMsg(TruegardenerDBAcessException ex) {
		setErrorMsg((TruegardenerException)ex);
		String strQuery = ex.getSqlQuery();
		if(strQuery!=null) {			
			m_strErrorMsg += "Database query: " + strQuery + "\r\n";
		}
	}
	public void setErrorMsg(String errorMsg, int nErrorCode) {
		m_strErrorMsg = errorMsg;
		m_nResult = nErrorCode;
	}
	public void setErrorMsg(String errorMsg, int nErrorCode, String strQuery) {
		m_nResult = nErrorCode;
		m_strErrorMsg = "Error :" + errorMsg + "\r\n";
		m_strErrorMsg += "Error code:" + nErrorCode + "\r\n";
		m_strErrorMsg += "Database query: " + strQuery + "\r\n";
	}
}
