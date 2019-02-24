package com.product.judge.api.business.dao;

import com.product.judge.api.business.model.Batchinfo;
import com.product.judge.api.business.model.Questionbank;
import com.product.judge.api.business.model.Questionbanktemp;
import com.product.judge.common.base.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author haiyan
 * @version v0.0.1
 * @project judgeApi
 * @describe 试题录入发布持久层
 * @since 2018/5/10
 **/
@Repository("questionDao")
public class QuestionDao extends BaseDao
{

    /**
     * 使用ownId获取临时表中的试题
     *
     * @param currentUser
     * @return
     */
    public List<Questionbanktemp> getOwnTempQuestions(String currentUser)
    {
        String sql = "select * from Questionbanktemp where ownId = ? ";
        return queryForModelList(sql, new String[]{currentUser}, Questionbanktemp.class);
    }

    /**
     * 使用id获取当前登录人临时表中存储的数据
     *
     * @param currentUser
     * @param titalId
     * @return
     */
    public Questionbanktemp getOwnTempQuestionsById(String currentUser, int titalId)
    {
        String sql = "select * from Questionbanktemp where ownId = ? and titleid = ? ";
        return queryForModel(sql, new Object[]{currentUser, titalId}, Questionbanktemp.class);
    }

    /**
     * 往临时表中添加试题
     *
     * @param q
     * @param userId
     */
    public void saveNewTempQuestion(Questionbanktemp q, String userId)
    {
        Object[] field = {q.getClassid(), q.getQuestion(), q.getOpt1(), q.getOpt2(), q.getOpt3(), q.getOpt4(), q.getAnswer(), q.getExplainmsg(), userId};
        StringBuffer sql = new StringBuffer("\n");
        sql.append("insert into Questionbanktemp(classid,question,opt1,opt2,opt3,opt4,answer,explainmsg,ownid)");
        sql.append("values(?,?,?,?,?,?,?,?,?)");
        update(sql.toString(), field);
    }

    /**
     * 更新临时表中的试题
     *
     * @param q
     */
    public void updateNewTempQuestion(Questionbanktemp q)
    {
        Object[] field = {q.getClassid(), q.getQuestion(), q.getOpt1(), q.getOpt2(), q.getOpt3(), q.getOpt4(), q.getAnswer(), q.getExplainmsg(), q.getTitleid()};
        StringBuffer sql = new StringBuffer("\n");
        sql.append("update Questionbanktemp set \n");
        sql.append("    classid     = ?,\n");
        sql.append("    question    = ?,\n");
        sql.append("    opt1        = ?,\n");
        sql.append("    opt2        = ?,\n");
        sql.append("    opt3        = ?,\n");
        sql.append("    opt4        = ?,\n");
        sql.append("    answer      = ?,\n");
        sql.append("    explainmsg  = ? \n");
        sql.append("where  titleid  = ?");
        update(sql.toString(), field);
    }

    /**
     * 删除临时表中的试题
     *
     * @param id
     */
    public void deleteNewTempQuestion(int id)
    {
        String sql = "delete from Questionbanktemp where titleid = ? ";
        update(sql, new Object[]{id});
    }

    /**
     * 将信息存储到批次表中
     *
     * @param b
     */
    public void insertIntoBatchInfo(Batchinfo b)
    {
        Object[] field = {b.getTitle(), b.getDescinfo(), b.getPrice(), b.getBatchid(), b.getOwnid()};
        String sql = "insert into batchinfo(title,descinfo,price,batchid,ownid)values(?,?,?,?,?)";
        update(sql, field);
    }

    /**
     * 将试题更新到正式表中
     *
     * @param batchId
     * @param operator
     */
    public void insertIntoQuestionBankByTemp(String batchId, String operator)
    {
        StringBuffer sql = new StringBuffer("\n");
        sql.append("INSERT INTO questionbank (uuid, type, classid, question, opt1, opt2, opt3, opt4, answer, explainmsg, ownid, batchid) \n");
        sql.append("  SELECT \n");
        sql.append("    replace(uuid(), '-', ''),1,classid,question,opt1,opt2,opt3,opt4,answer,explainmsg,ownid,? \n");
        sql.append("  FROM questionbanktemp WHERE ownid = ?  \n");
        update(sql.toString(), new String[]{batchId, operator});
    }

    /**
     * 删除临时表信息
     *
     * @param operator
     */
    public void deleteQuestionBankTemp(String operator)
    {
        String sql = "delete from questionbanktemp where ownid = ?";
        update(sql, new String[]{operator});
    }

    /**
     * 模糊查询题目
     *
     * @param likeStr
     * @return
     */
    public List<Questionbank> getRelevantInfo(String likeStr, String ownid)
    {
        String sql = "select * from questionbank where question like ? and ownid = ?";
        return queryForModelList(sql, new String[]{"%" + likeStr + "%", ownid}, Questionbank.class);
    }

    /**
     * 获取已发布的试题
     *
     * @param question
     * @param ownid
     * @param offset
     * @param limit
     * @param order
     * @return
     */
    public List getAllReleasedQuestions(String question, String ownid, String offset, String limit, String order)
    {
        StringBuffer sql = new StringBuffer("\n");
        sql.append("select  question,opt1,opt2,opt3,opt4,explainmsg,");
        sql.append("case when answer = 1 then 'A' when answer = 2 then 'B' when answer = 3 then 'C' else 'D' end as answer \n");
        sql.append("  from  questionbank where question like ? and ownid = ?");
        sql.append(" order  by question " + order);
        sql.append(" limit " + offset + " , " + limit);
        return queryForList(sql.toString(), new String[]{"%" + question + "%", ownid});
    }

    /**
     * 获取已发布的条数
     *
     * @param question
     * @param ownid
     * @return
     */
    public int getCount4ReleasedQuestions(String question, String ownid)
    {
        String sql = "select count(1) from questionbank where question like ? and ownid = ?";
        return queryForInt(sql, new String[]{"%" + question + "%", ownid});
    }

    /**
     * 往临时表中添加试题--excel
     *
     * @param list
     * @param userId
     * @param classid
     */
    public void saveNewTempQuestion4Excel(List<String> list, String userId, String classid)
    {
        Object[] field = {classid, list.get(1), list.get(2), list.get(3), list.get(4), list.get(5), list.get(6), list.get(7), userId};
        StringBuffer sql = new StringBuffer("\n");
        sql.append("insert into Questionbanktemp(classid,question,opt1,opt2,opt3,opt4,answer,explainmsg,ownid)");
        sql.append("values(?,?,?,?,?,?,?,?,?)");
        update(sql.toString(), field);
    }

    /**
     * 获取试题类型
     *
     * @return
     */
    public List getQuestionType()
    {
        String sql = "select _id as value,description as text from questiontype";
        return queryForList(sql);
    }
}
