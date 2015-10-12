package com.lal.sp.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lal.sp.bean.SportsMatch;
import com.lal.sp.bean.SportsMatchEvaluate;
import com.lal.sp.bean.UserSportsApply;
import com.lal.sp.bean.UserSportsMatchApply;

public interface ISportsMatchDao {

	void publishSports(SportsMatch sportsMatch);

	void applySports(UserSportsApply userSportsApply);

	int listAllSportsCount(Map<String, Object> params);

	List<SportsMatch> listAllSports(Map<String, Object> params);

	int listNearBySportsCount(Map<String, Object> params);
	
	List<SportsMatch> listNearBySports(Map<String, Object> params);

	List<UserSportsApply> getApplySportsList(HashMap<String, Object> params);

	void auditingApplySports(HashMap<String, Object> params);

	List<SportsMatch> getMyPushlishSports(String userId);

	List<UserSportsApply> getMyApplySports(String user);

	int listAllSportsMatchCount(Map<String, Object> params);
	
	List<SportsMatch> listAllSportsMatch(Map<String, Object> params);

	int listNearBySportsMatchCount(Map<String, Object> params);
	
	List<SportsMatch> listNearBySportsMatch(Map<String, Object> params);

	void addSportsMatch(SportsMatch sportsMatch);

	void applySportsMatch(UserSportsMatchApply userSportsMatchApply);

	void addSMJudgeCoachs(SportsMatch sportsMatch);

	List<UserSportsApply> getApplySportsMatchList(String sportsMatchId);

	void auditingApplySportsMatch(HashMap<String, Object> params);

	List<SportsMatch> getAllPushlishSportsMatch(String oId);

	List<UserSportsMatchApply> getMyApplySportsMatch(String userId);

	void addSportsEvaluate(SportsMatchEvaluate sportsMatchEvaluate);

	void addSportsMatchEvaluate(SportsMatchEvaluate sportsMatchEvaluate);
}
