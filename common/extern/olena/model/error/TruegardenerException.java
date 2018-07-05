package common.extern.olena.model.error;

import common.extern.olena.model.infos.universalResult.infoUniversalReturnCode;

@SuppressWarnings("serial")
public class TruegardenerException extends Exception{

	public TruegardenerException() {
		super();
	}
	public TruegardenerException(String message, Throwable cause, long nErrorCode) {
		super(message, cause);
		m_nErrorCode = nErrorCode;
	}
	public TruegardenerException(String message, long nErrorCode) {
		super(message);
		m_nErrorCode = nErrorCode;
	}
	public TruegardenerException(Throwable cause, long nErrorCode) {
		super(cause);
		m_nErrorCode = nErrorCode;
	}
	
	public TruegardenerException(String message, Throwable cause) {
		super(message, cause);
	}
	public TruegardenerException(String message) {
		super(message);
	}
	public TruegardenerException(Throwable cause) {
		super(cause);
	}
	private long m_nErrorCode = infoUniversalReturnCode.RESULT_ERROR_UNKNOWN;

	public long getErrorCode() { return m_nErrorCode; }
	public void setErrorCode(long errorCode) { m_nErrorCode = errorCode; }
	
}
