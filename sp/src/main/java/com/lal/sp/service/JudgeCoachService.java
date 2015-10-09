package com.lal.sp.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lal.sp.bean.JCEvaluate;
import com.lal.sp.bean.JudgeCoach;
import com.lal.sp.dao.IJudgeCoachDao;
import com.lal.sp.dao.IUserDao;
import com.lal.sp.util.PageDaoUtil;

@Service
public class JudgeCoachService {
	
	@Autowired
	private IJudgeCoachDao judgeCoachDao;
	@Autowired
	private IUserDao userDao;
	@Autowired
	private PageDaoUtil pageDaoUtil;
 
	@Transactional
	public void register(JudgeCoach jc) {
		judgeCoachDao.add(jc);
		judgeCoachDao.addJCSportsCategory(jc);
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("uid", jc.getUser().getId());
		param.put("jcid", jc.getId());
		userDao.updateJudgeCoach(param);
	}

	public int getJudgeCoachCount(HashMap<String, Object> params) {
		return judgeCoachDao.getJudgeCoachCount(params);
	}

	public List<JudgeCoach> getJudgeCoach(HashMap<String, Object> params, int start, int limit) {
		return  pageDaoUtil.selectList("com.lal.sp.dao.IJudgeCoach.getJudgeCoach", params, new RowBounds((start-1)*limit,limit));
	}

	public void evaluate(JCEvaluate jcv) {
		judgeCoachDao.addEvaluate(jcv);
	}
	

}
