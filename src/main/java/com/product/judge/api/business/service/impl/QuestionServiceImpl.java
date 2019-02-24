package com.product.judge.api.business.service.impl;

import com.product.judge.api.business.dao.PublicMethodDao;
import com.product.judge.api.business.dao.QuestionDao;
import com.product.judge.api.business.model.Batchinfo;
import com.product.judge.api.business.model.Questionbank;
import com.product.judge.api.business.model.Questionbanktemp;
import com.product.judge.api.business.model.Sysdic;
import com.product.judge.api.business.service.QuestionService;
import com.product.judge.common.base.service.impl.BaseServiceImpl;
import com.product.judge.common.util.SessionUtil;
import com.product.judge.common.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author haiyan
 * @version v0.0.1
 * @project judgeApi
 * @describe 试题录入发布服务层接口实现类
 * @since 2018/5/10
 **/
@Service("questionService")
public class QuestionServiceImpl extends BaseServiceImpl implements QuestionService
{
    @Autowired
    QuestionDao questionDao;

    @Autowired
    PublicMethodDao publicMethodDao;

    /**
     * 获取当前登录人临时表中存储的数据
     *
     * @param currentUser
     * @return
     */
    @Override
    public List<Questionbanktemp> getOwnTempQuestions(String currentUser)
    {
        return questionDao.getOwnTempQuestions(currentUser);
    }

    /**
     * 使用id获取当前登录人临时表中存储的数据
     *
     * @param currentUser
     * @return
     */
    @Override
    public Questionbanktemp getOwnTempQuestionsById(String currentUser, int titalId)
    {
        return questionDao.getOwnTempQuestionsById(currentUser, titalId);
    }

    /**
     * 通过字典种类获取数据字典
     *
     * @param type
     * @return
     */
    @Override
    public List<Sysdic> getSysDicByType(String type)
    {
        return publicMethodDao.getSysDicByType(type);
    }

    /**
     * 往临时表中添加试题
     *
     * @param questionbanktemp
     */
    @Override
    public void saveNewTempQuestion(Questionbanktemp questionbanktemp, String curUserId)
    {
        questionDao.saveNewTempQuestion(questionbanktemp, curUserId);
    }

    /**
     * 更新临时表中的试题
     *
     * @param questionbanktemp
     */
    @Override
    public void updateNewTempQuestion(Questionbanktemp questionbanktemp)
    {
        questionDao.updateNewTempQuestion(questionbanktemp);
    }

    /**
     * 删除临时表中的试题
     *
     * @param id
     */
    @Override
    public void deleteNewTempQuestion(int id)
    {
        questionDao.deleteNewTempQuestion(id);
    }

    /**
     * 上报试题
     *
     * @param batchinfo
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void appearQuestions(Batchinfo batchinfo, HttpServletRequest request)
    {
        String operator = SessionUtil.getCurrentUser(request);
        String batchId = StringUtil.generateUUID();
        batchinfo.setBatchid(batchId);
        batchinfo.setOwnid(operator);
        //1.将试题信息存储到batchInfo表中
        questionDao.insertIntoBatchInfo(batchinfo);
        //2.将试题更新到正式表中
        questionDao.insertIntoQuestionBankByTemp(batchId, operator);
        //3.删除临时表信息
        questionDao.deleteQuestionBankTemp(operator);
    }

    /**
     * 模糊查询题目
     *
     * @param likeStr
     * @param request
     * @return
     */
    @Override
    public List<Questionbank> getRelevantInfo(String likeStr, HttpServletRequest request)
    {
        String ownid = SessionUtil.getCurrentUser(request);
        return questionDao.getRelevantInfo(likeStr, ownid);
    }

    /**
     * 获取已发布的试题
     *
     * @param params
     * @return
     */
    @Override
    public List getAllReleasedQuestions(Map<String, String> params)
    {
        String ownid = params.get("ownid");
        String order = params.get("order");
        String question = params.get("question");
        String limit = params.get("limit");
        String offset = params.get("offset");
        return questionDao.getAllReleasedQuestions(question, ownid, offset, limit, order);
    }

    /**
     * 获取已发布的条数
     *
     * @param params
     * @return
     */
    @Override
    public int getCount4ReleasedQuestions(Map<String, String> params)
    {
        String ownid = params.get("ownid");
        String question = params.get("question");
        return questionDao.getCount4ReleasedQuestions(question, ownid);
    }

    /**
     * 将上载的excel数据保存到试题临时表中
     *
     * @param datas
     */
    @Override
    public void insertTempQuestionsByExcel(List<List<String>> datas, String userid, String type)
    {
        for (List<String> list : datas)
        {
            questionDao.saveNewTempQuestion4Excel(list, userid, type);
        }
    }

    /**
     * 获取试题类型
     *
     * @return
     */
    @Override
    public List getQuestionType()
    {
        return questionDao.getQuestionType();
    }
}
