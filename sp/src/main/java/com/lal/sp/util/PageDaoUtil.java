package com.lal.sp.util;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class PageDaoUtil {
	@Resource(name="sqlSession")
	private SqlSession session;
	
	/**
	 * 分页查询
	 * @param queryid  查询的id,命名，如： com.xinfeijinxin.qy.dao.IreportDao.queryRb
	 * @param params 参数
	 * @param bounds 分页数据 如：new RowBounds((start-1)*Conv.pageSize, limit)
	 * @return list
	 */
	public List selectList(String queryid,Map<String, Object> params,RowBounds bounds){
		return session.selectList(queryid, params,bounds);
	}
	/**
	 * 不分页查询
	 * @param queryid
	 * @param params
	 * @return
	 */
	public List selectList(String queryid,Map<String, Object> params){
		return session.selectList(queryid, params);
	}
}
