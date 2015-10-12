package com.lal.sp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lal.sp.bean.Organization;
import com.lal.sp.bean.SportsMatch;
import com.lal.sp.bean.SportsMatchEvaluate;
import com.lal.sp.bean.User;
import com.lal.sp.bean.UserSportsApply;
import com.lal.sp.bean.UserSportsMatchApply;
import com.lal.sp.dao.ISportsMatchDao;
import com.lal.sp.util.LLSquarePointUtil;
import com.lal.sp.util.PageDaoUtil;

@Service
public class SportsMatchService {

	@Autowired
	private ISportsMatchDao sportsMatchDao;
	@Autowired
	private PageDaoUtil pageDaoUtil;
	@Autowired
	private UserService userService;
	@Autowired
	private OrganizationService organizationService;

	public void publishSports(SportsMatch sportsMatch) {
		sportsMatchDao.publishSports(sportsMatch);
	}

	public void applySports(UserSportsApply userSportsApply) {
		sportsMatchDao.applySports(userSportsApply);
	}

	public int listAllSportsCount(Map<String, Object> params) {
		return sportsMatchDao.listAllSportsCount(params);
	}

	public List<SportsMatch> listAllSports(Map<String, Object> params, int start, int limit) {
		return pageDaoUtil.selectList("com.lal.sp.dao.ISportsMatchDao.listAllSports", params, new RowBounds((start-1)*limit,limit));
	}

	public int listNearBySportsCount(Map<String, Object> params) {
		params.put("squares", LLSquarePointUtil.returnLLSquarePoint((Double)params.get("longitude"), (Double)params.get("latitude"), (Double)params.get("distance")));
		return sportsMatchDao.listNearBySportsCount(params);
	}

	public List<SportsMatch> listNearBySports(Map<String, Object> params) {
		params.put("squares", LLSquarePointUtil.returnLLSquarePoint((Double)params.get("longitude"), (Double)params.get("latitude"), (Double)params.get("distance")));
		return pageDaoUtil.selectList("com.lal.sp.dao.ISportsMatchDao.listNearBySports",params);
	}

	public List<UserSportsApply> getApplySportsList(HashMap<String, Object> params) {
		return sportsMatchDao.getApplySportsList(params);
	}

	public void auditingApplySports(HashMap<String, Object> params) {
		sportsMatchDao.auditingApplySports(params);
	}

	public List<SportsMatch> getMyPushlishSports(String userId) {
		return sportsMatchDao.getMyPushlishSports(userId);
	}

	public List<UserSportsApply> getMyApplySports(String user) {
		return sportsMatchDao.getMyApplySports(user);
	}

	public int listAllSportsMatchCount(Map<String, Object> params) {
		return sportsMatchDao.listAllSportsMatchCount(params);
	}

	public List<SportsMatch> listAllSportsMatch(Map<String, Object> params, int start, int limit) {
		return pageDaoUtil.selectList("com.lal.sp.dao.ISportsMatchDao.listAllSportsMatch", params, new RowBounds((start-1)*limit,limit));
	}

	public int listNearBySportsMatchCount(Map<String, Object> params) {
		return sportsMatchDao.listNearBySportsMatchCount(params);
	}

	public List<SportsMatch> listNearBySportsMatch(Map<String, Object> params) {
		params.put("squares", LLSquarePointUtil.returnLLSquarePoint((Double)params.get("longitude"), (Double)params.get("latitude"), (Double)params.get("distance")));
		return pageDaoUtil.selectList("com.lal.sp.dao.ISportsMatchDao.listNearBySportsMatch",params);
	}

	@Transactional
	public void publishSportsMatch(SportsMatch sportsMatch) {
		sportsMatchDao.addSportsMatch(sportsMatch);
		if(sportsMatch.getJudgeCoachs()!=null && sportsMatch.getJudgeCoachs().size()>0) {
			sportsMatchDao.addSMJudgeCoachs(sportsMatch);
		}
	}

	public void applySportsMatch(UserSportsMatchApply userSportsMatchApply) {
		sportsMatchDao.applySportsMatch(userSportsMatchApply);
	}

	public List<UserSportsApply> getApplySportsMatchList(String sportsMatchId) {
		return sportsMatchDao.getApplySportsMatchList(sportsMatchId);
	}

	public void auditingApplySportsMatch(HashMap<String, Object> params) {
		sportsMatchDao.auditingApplySportsMatch(params);
	}

	public List<SportsMatch> getAllPushlishSportsMatch(String oId) {
		return sportsMatchDao.getAllPushlishSportsMatch(oId);
	}

	public List<UserSportsMatchApply> getMyApplySportsMatch(String userId) {
		return sportsMatchDao.getMyApplySportsMatch(userId);
	}

	@Transactional
	public void evaluateSports(SportsMatchEvaluate sportsMatchEvaluate) {
		sportsMatchDao.addSportsEvaluate(sportsMatchEvaluate);
		User user = userService.getUserById(sportsMatchEvaluate.getSportsMatch().getPublisher().getId().toString());
		user.setJifen(user.getJifen()+sportsMatchEvaluate.getJifen());
		userService.addJifen(user);
	}

	public void evaluateSportsMatch(SportsMatchEvaluate sportsMatchEvaluate) {
		sportsMatchDao.addSportsMatchEvaluate(sportsMatchEvaluate);
		Organization organization = organizationService.getOrganizationById(sportsMatchEvaluate.getSportsMatch().getPublisher().getId().toString());
		organization.setJifen(organization.getJifen()+sportsMatchEvaluate.getJifen());
		organizationService.addJifen(organization);
	}
}
