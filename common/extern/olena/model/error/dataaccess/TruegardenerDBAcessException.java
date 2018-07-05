package common.extern.olena.model.error.dataaccess;

import common.extern.olena.model.error.TruegardenerException;


/**
 * Truegardener Model's Error Object for Exception
 */
@SuppressWarnings("serial")
public class TruegardenerDBAcessException extends TruegardenerException{

	public TruegardenerDBAcessException() {
		super();
	}
	public TruegardenerDBAcessException(String message, Throwable cause, long nErrorCode) {
		super(message, cause, nErrorCode);
	}
	public TruegardenerDBAcessException(String message, long nErrorCode) {
		super(message, nErrorCode);
	}
	public TruegardenerDBAcessException(Throwable cause, long nErrorCode) {
		super(cause, nErrorCode);
	}
	public TruegardenerDBAcessException(String message, Throwable cause) {
		super(message, cause);
	}
	public TruegardenerDBAcessException(String message) {
		super(message);
	}
	public TruegardenerDBAcessException(Throwable cause) {
		super(cause);
	}
	public TruegardenerDBAcessException(String message, Throwable cause, long nErrorCode, String strQuery) {
		super(message, cause, nErrorCode);
		m_strSqlQuery = strQuery;
	}
	public TruegardenerDBAcessException(String message, long nErrorCode, String strQuery) {
		super(message, nErrorCode);
		m_strSqlQuery = strQuery;
	}
	public TruegardenerDBAcessException(Throwable cause, long nErrorCode, String strQuery) {
		super(cause, nErrorCode);
		m_strSqlQuery = strQuery;
	}
	public TruegardenerDBAcessException(String message, Throwable cause, String strQuery) {
		super(message, cause);
		m_strSqlQuery = strQuery;
	}
	public TruegardenerDBAcessException(String message, String strQuery) {
		super(message);
		m_strSqlQuery = strQuery;
	}
	public TruegardenerDBAcessException(Throwable cause, String strQuery) {
		super(cause);
	}
	
	private String m_strSqlQuery;

	public String getSqlQuery() { 	return m_strSqlQuery; }
	public void setSqlQuery(String sqlQuery) { m_strSqlQuery = sqlQuery; }
	
}
