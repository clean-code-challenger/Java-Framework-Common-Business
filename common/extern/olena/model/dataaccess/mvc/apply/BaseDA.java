package common.extern.olena.model.dataaccess.mvc.apply;

import java.util.Vector;

import common.extern.olena.model.dataaccess.daFatherModel;
import common.extern.olena.model.dataaccess.mvc.base.IItem_Model;
import common.extern.olena.model.dataaccess.mvc.base.da_Model;
import common.extern.olena.model.dataaccess.util.core.connections.dbConn_Model;
import common.extern.olena.model.dataaccess.util.core.query.queryProducts.*;
/**
 * 자료기지표호출를 위한 기초정보정의 클라스
 * @author Olena.Zagreba 
 * @version  2013-03-26 08:14:12
 */
public class BaseDA extends da_Model
{
	public BaseDA(){
		super();
	}
	public BaseDA(dbConn_Model conn, daFatherModel parentDA) throws Throwable
	{
		m_connDB = conn;
		m_daParent = parentDA;
		InitSQLAndResultSets(conn.getDbType());
	}
	public BaseDA(dbConn_Model conn, daFatherModel parentDA, IItem_Model itemM) throws Throwable
	{
		m_ItemModel = itemM;
		m_daParent = parentDA;
		m_connDB = conn;
		InitSQLAndResultSets(conn.getDbType());
	}
	public BaseDA(dbConn_Model conn, daFatherModel parentDA, IItem_Model itemM, int nKind) throws Throwable {
		m_ItemModel = itemM; m_ItemModel.setKind(nKind);
		m_daParent = parentDA;
		m_connDB = conn;
		InitSQLAndResultSets(conn.getDbType());
	}
	@Override
	public Vector<Object> get_DistinctListByEqu(int nDistinctField, whereExpressionRelation whereList, boolean bAndOr, orderExpressionRelation orderList, pageExpressionRelation pgInfo) throws Throwable {
		productUpdateEquWhereRelation(whereList, bAndOr);
		Paging(whereList, pgInfo);
		return super.get_DistinctListByEqu(nDistinctField, whereList, bAndOr, orderList,
				pgInfo);
	}
	@Override
	public Vector<Object> get_DistinctListByLike(int nDistinctField, whereExpressionRelation likeList, boolean bAndOr, orderExpressionRelation orderList, pageExpressionRelation pgInfo) throws Throwable {
		productUpdateEquWhereRelation(likeList, bAndOr);
		Paging(likeList, pgInfo);
		return super.get_DistinctListByLike(nDistinctField, likeList, bAndOr, orderList, pgInfo);
	}
	@Override
	public Vector<Object> get_DistinctListByQuery(String query, executeExpressionRelation executeList, pageExpressionRelation pgInfo) throws Throwable {
		Paging(query, executeList, pgInfo);
		return super.get_DistinctListByQuery(query, executeList, pgInfo);
	}
	@Override
	public Vector<Object> get_DistinctListInfo(int nDistinctField, whereExpressionRelation whereList, orderExpressionRelation orderList, pageExpressionRelation pgInfo) throws Throwable {
		Paging(whereList, pgInfo);
		return super.get_DistinctListInfo(nDistinctField, whereList, orderList, pgInfo);
	}
	@Override
	public Vector<IItem_Model> get_Info(selectExpressionRelation selectList, whereExpressionRelation whereList, orderExpressionRelation orderList, pageExpressionRelation pgInfo) throws Throwable {
		Paging(whereList, pgInfo);
		return super.get_Info(selectList, whereList, orderList, pgInfo);
	}
	@Override
	public Vector<IItem_Model> get_ListByEqu(selectExpressionRelation selectList, whereExpressionRelation whereList, boolean bAndOr, orderExpressionRelation orderList, pageExpressionRelation pgInfo) throws Throwable {
		productUpdateEquWhereRelation(whereList, bAndOr);
		Paging(whereList, pgInfo);
		return super.get_ListByEqu(selectList, whereList, bAndOr, orderList, pgInfo);
	}
	@Override
	public Vector<IItem_Model> get_ListByLike(selectExpressionRelation selectList, whereExpressionRelation likeList, boolean bAndOr, orderExpressionRelation orderList, pageExpressionRelation pgInfo) throws Throwable {
		productUpdateLikeWhereRelation(likeList, bAndOr);
		Paging(likeList, pgInfo);
		return super.get_ListByLike(selectList, likeList, bAndOr, orderList, pgInfo);
	}
	/**
	 * where정보에 해당한 질의식의 개수를 얻기
	 * @param whereList 검색하는 관계객체
	 * @param pgInfo
	 * @throws Throwable
	 */
	private void Paging(whereExpressionRelation whereList, pageExpressionRelation pgInfo) throws Throwable {
		if(pgInfo!=null) {
			if(pgInfo.getPageSize() > 0 && pgInfo.getLimit() <= 0) {
				long nLimit = super.get_ListCountByInfo(whereList, "countAsNum");
				pgInfo.setLimit(nLimit);
			}
		}
	}
	/**
	 * query에 해당한 질의식의 개수를 얻기
	 * @param strQuery
	 * @param executeList
	 * @param pgInfo
	 * @throws Throwable
	 */
	private void Paging(String strQuery, executeExpressionRelation executeList, pageExpressionRelation pgInfo) throws Throwable {
		if(strQuery!=null && pgInfo.getPageSize() > 0 && pgInfo.getLimit() <= 0) {
			long nLimit = super.get_NumValueByQuery(strQuery, executeList, "countAsNum");
			pgInfo.setLimit(nLimit);
		}
	}
}
