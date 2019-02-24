package com.product.judge.api.business.service;

import com.product.judge.api.business.model.Batchinfo;
import com.product.judge.api.business.model.Questionbank;
import com.product.judge.api.business.model.Questionbanktemp;
import com.product.judge.api.business.model.Sysdic;
import com.product.judge.common.base.service.BaseService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author haiyan
 * @version v0.0.1
 * @project judgeApi
 * @describe 试题录入发布服务层接口
 * @since 2018/5/10
 **/
public interface QuestionService extends BaseService
{

    List<Questionbanktemp> getOwnTempQuestions(String currentUser);

    Questionbanktemp getOwnTempQuestionsById(String currentUser, int titalId);

    List<Sysdic> getSysDicByType(String type);

    void saveNewTempQuestion(Questionbanktemp questionbanktemp, String curUserId);

    void updateNewTempQuestion(Questionbanktemp questionbanktemp);

    void deleteNewTempQuestion(int id);

    void appearQuestions(Batchinfo batchinfo, HttpServletRequest request);

    List<Questionbank> getRelevantInfo(String likeStr, HttpServletRequest request);

    List getAllReleasedQuestions(Map<String, String> params);

    int getCount4ReleasedQuestions(Map<String, String> params);

    void insertTempQuestionsByExcel(List<List<String>> datas, String userid, String type);

    List getQuestionType();
}
