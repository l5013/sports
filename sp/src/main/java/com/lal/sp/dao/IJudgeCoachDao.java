package com.lal.sp.dao;

import java.util.HashMap;
import java.util.List;

import com.lal.sp.bean.JCEvaluate;
import com.lal.sp.bean.JudgeCoach;

public interface IJudgeCoachDao {

	JudgeCoach add(JudgeCoach jc);

	void addJCSportsCategory(JudgeCoach jc);

	int getJudgeCoachCount(HashMap<String, Object> params);
	
	List<JudgeCoach> getJudgeCoach(HashMap<String, Object> params);

	void addEvaluate(JCEvaluate jcv);

	JudgeCoach getJudgeCoachById(String id);

	void addJifen(JudgeCoach judgeCoach);

}
