package common.extern.olena.model.dataaccess.util.core.query.queryProducts;
/**
 * query의 최소관계모형객체
 *
 * @author Olena.Zagreba
 * @version ModelWeb 1.0, 2012. 01. 13 AM 9:45:31
 * @since ModelWeb 1.0
 */
public class pageExpressionRelation
{
	public pageExpressionRelation() {
		m_nPageSize = -1;
		m_nPageIndex = -1;
		m_nLimit = -1;
		m_nPageCount = -1;
	}
	public pageExpressionRelation(int nPageSize) {
		m_nPageSize = nPageSize;
		m_nLimit = -1;
		m_nPageCount = -1;
		m_nPageIndex = -1;
	}
	public pageExpressionRelation(int nLimit, int nPageSize, int nPageIndex, int nPageCount) {
		m_nLimit = nLimit;
		m_nPageSize = nPageSize;
		m_nPageCount = nPageCount;
		m_nPageIndex = nPageIndex;
	}
	private long m_nLimit = -1;
	private long m_nPageSize = 100;
	private long m_nPageCount;
	private long m_nPageIndex = -1;
	public long getPageSize() { return m_nPageSize; }
	public void setPageSize(long nSize) { m_nPageSize = nSize; }
	
	public  long getPageIndex() { return m_nPageIndex; }
	public void setPageIndex( long nIndex) { m_nPageIndex = nIndex; }

	public long getLimit() { return m_nLimit; }
	public void setLimit(long limit)
	{ 
		m_nLimit = limit;
		if (m_nLimit <= 0) {
			m_nPageCount = 0;
			m_nPageIndex = 0;
		}
		else {
			long nPageCount = (m_nLimit / m_nPageSize);
			m_nPageCount = (nPageCount * m_nPageSize < m_nLimit) ? ++ nPageCount : nPageCount;
			if (m_nPageIndex >= m_nPageCount) { m_nPageIndex = m_nPageCount - 1; }
		}
	}

	public long getPageCount() { return m_nPageCount; }
	public void setPageCount( long pageCount) { m_nPageCount = pageCount; }
};
