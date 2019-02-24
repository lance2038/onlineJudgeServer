package com.product.judge.common.base.dao;

import com.product.judge.common.base.model.Sqllog;
import com.product.judge.common.constant.SqlConstants;
import com.product.judge.common.util.DateUtil;
import com.product.judge.common.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author haiyan
 * @version v0.0.1
 * @project judgeApi
 * @describe 持久层基类
 * @since 2018/5/10
 **/
@Repository("baseDao")
@Scope("prototype")
public class BaseDao
{
    private JdbcTemplate jdbcTemplate;

    @Resource
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    public JdbcTemplate getJdbcTemplate()
    {
        return jdbcTemplate;
    }

    private Logger logger = LoggerFactory.getLogger(BaseDao.class);

    /**
     * 执行Sql语句进行数据修改
     */
    public void execute(String sql) throws DataAccessException
    {
        Long stime = System.currentTimeMillis();
        String status = SqlConstants.faild;
        String sqlLogId = StringUtil.generateUUID();
        try
        {
            this.beforExecuteSql("execute", sql, "", sqlLogId);
            this.jdbcTemplate.execute(sql);
            status = SqlConstants.successful;
        }
        catch (DataAccessException dataAccessException)
        {
            throw dataAccessException;
        }
        finally
        {
            Long etime = System.currentTimeMillis();
            log(sql, stime, etime, status, sqlLogId, "");
        }
    }

    /**
     * 记录sql日志
     *
     * @param sql
     * @param starttime
     * @param endtime
     * @param sqlLogId
     * @param argsInfo
     */
    private void log(String sql, Long starttime, Long endtime, String status, String sqlLogId, String argsInfo)
    {
        StackTraceElement[] stack = new Throwable().getStackTrace();
        String callLocation = stack[3].getClassName();
        int lineNum = stack[3].getLineNumber();
        Long timeConsuming = endtime - starttime;
        Sqllog sqlLog = new Sqllog(sqlLogId, sql, timeConsuming, status, callLocation, lineNum, argsInfo);
        logger.info(sqlLog.toString());
    }

    /**
     * sql方法执行前log
     *
     * @param methodName
     * @param sql
     * @param msg
     * @throws Exception
     */
    private void beforExecuteSql(String methodName, String sql, String msg, String logId)
    {
        try
        {
            String curTime = DateUtil.getCurrentTime();
            logger.info("准备执行sql [" + logId + "] -- 执行时间:" + curTime + ", 执行方法:" + methodName + ", 执行sql:" + sql + " " + msg);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    /**
     * 查询Sql，返回double
     *
     * @param sql
     * @return
     */
    public double queryForDouble(String sql) throws DataAccessException
    {
        double backVal = 0.0;
        Long stime = System.currentTimeMillis();
        String status = SqlConstants.faild;
        String sqlLogId = StringUtil.generateUUID();
        try
        {
            this.beforExecuteSql("queryForDouble", sql, "", sqlLogId);
            Number number = (Number) this.jdbcTemplate.queryForObject(sql, Double.class);
            backVal = (number != null ? number.doubleValue() : 0.0);
            status = SqlConstants.successful;
        }
        catch (DataAccessException dataAccessException)
        {
            throw dataAccessException;
        }
        finally
        {
            Long etime = System.currentTimeMillis();
            log(sql, stime, etime, status, sqlLogId, "");
        }
        return backVal;
    }

    /**
     * 查询double
     *
     * @param sql  预编译带？的sql
     * @param args 参数数组
     * @return double
     */
    public double queryForDouble(String sql, Object[] args) throws DataAccessException
    {
        double backVal = 0.0;
        Long stime = System.currentTimeMillis();
        String status = SqlConstants.faild;
        String sqlLogId = StringUtil.generateUUID();
        String argsInfo = (args != null ? "参数 - " + Arrays.toString(args) : "");
        try
        {
            this.beforExecuteSql("queryForDouble", sql, argsInfo, sqlLogId);
            Number number = (Number) this.jdbcTemplate.queryForObject(sql, args, Double.class);
            backVal = (number != null ? number.doubleValue() : 0.0);
            status = SqlConstants.successful;
        }
        catch (DataAccessException dataAccessException)
        {
            throw dataAccessException;
        }
        finally
        {
            Long etime = System.currentTimeMillis();
            log(sql + " " + argsInfo, stime, etime, status, sqlLogId, argsInfo);
        }
        return backVal;
    }


    /**
     * 查询Sql，返回float
     *
     * @param sql
     * @return
     */
    public float queryForFloat(String sql) throws DataAccessException
    {
        float backVal = 0;
        Long stime = System.currentTimeMillis();
        String status = SqlConstants.faild;
        String sqlLogId = StringUtil.generateUUID();
        try
        {
            this.beforExecuteSql("queryForFloat", sql, "", sqlLogId);
            Number number = (Number) this.jdbcTemplate.queryForObject(sql, Float.class);
            backVal = (number != null ? number.floatValue() : 0);
            status = SqlConstants.successful;
        }
        catch (DataAccessException dataAccessException)
        {
            throw dataAccessException;
        }
        finally
        {
            Long etime = System.currentTimeMillis();
            log(sql, stime, etime, status, sqlLogId, "");
        }
        return backVal;
    }

    /**
     * 查询float
     *
     * @param sql  预编译带？的sql
     * @param args 参数数组
     * @return
     */
    public float queryForFloat(String sql, Object[] args) throws DataAccessException
    {
        float backVal = 0;
        Long stime = System.currentTimeMillis();
        String status = SqlConstants.faild;
        String sqlLogId = StringUtil.generateUUID();
        String argsInfo = (args != null ? "参数 - " + Arrays.toString(args) : "");
        try
        {
            this.beforExecuteSql("queryForFloat", sql, argsInfo, sqlLogId);
            Number number = (Number) this.jdbcTemplate.queryForObject(sql, args, Float.class);
            backVal = (number != null ? number.floatValue() : 0);
            status = SqlConstants.successful;
        }
        catch (DataAccessException dataAccessException)
        {
            throw dataAccessException;
        }
        finally
        {
            Long etime = System.currentTimeMillis();
            log(sql + " " + argsInfo, stime, etime, status, sqlLogId, argsInfo);
        }
        return backVal;
    }


    /**
     * 查询Sql，返回int
     */
    public int queryForInt(String sql) throws DataAccessException
    {
        int backVal = 0;
        Long stime = System.currentTimeMillis();
        String status = SqlConstants.faild;
        String sqlLogId = StringUtil.generateUUID();
        try
        {
            this.beforExecuteSql("queryForInt", sql, "", sqlLogId);
            Integer temBack = this.jdbcTemplate.queryForObject(sql, Integer.class);
            if (null != temBack)
            {
                backVal = temBack;
            }
            status = SqlConstants.successful;
        }
        catch (DataAccessException dataAccessException)
        {
            throw dataAccessException;
        }
        finally
        {
            Long etime = System.currentTimeMillis();
            log(sql, stime, etime, status, sqlLogId, "");
        }
        return backVal;
    }

    /**
     * 查询int
     *
     * @param sql  预编译带？的sql
     * @param args 参数数组
     * @return int
     */
    public int queryForInt(String sql, Object[] args) throws DataAccessException
    {
        int backVal = 0;
        Long stime = System.currentTimeMillis();
        String status = SqlConstants.faild;
        String sqlLogId = StringUtil.generateUUID();
        String argsInfo = (args != null ? "参数 - " + Arrays.toString(args) : "");
        try
        {
            this.beforExecuteSql("queryForInt", sql, argsInfo, sqlLogId);
            Integer temBack = this.jdbcTemplate.queryForObject(sql, args, Integer.class);
            if (null != temBack)
            {
                backVal = temBack;
            }
            status = SqlConstants.successful;
        }
        catch (DataAccessException dataAccessException)
        {
            throw dataAccessException;
        }
        finally
        {
            Long etime = System.currentTimeMillis();

            log(sql + " " + argsInfo, stime, etime, status, sqlLogId, argsInfo);
        }
        return backVal;
    }


    /**
     * 查询Sql，返回List
     */
    @SuppressWarnings("rawtypes")
    public List queryForList(String sql) throws DataAccessException
    {
        List<Map<String, Object>> backVal = null;
        Long stime = System.currentTimeMillis();
        String status = SqlConstants.faild;
        String sqlLogId = StringUtil.generateUUID();
        try
        {
            this.beforExecuteSql("queryForList", sql, "", sqlLogId);
            backVal = this.jdbcTemplate.query(sql, new RowMapperResultSetExtractor<Map<String, Object>>(new ColumnMapRowMapper(), 0));
            status = SqlConstants.successful;
        }
        catch (DataAccessException dataAccessException)
        {
            throw dataAccessException;
        }
        finally
        {
            Long etime = System.currentTimeMillis();
            log(sql, stime, etime, status, sqlLogId, "");
        }
        return backVal;
    }

    /**
     * 查询List
     *
     * @param sql  预编译带？的sql
     * @param args 参数数组
     * @return List
     */
    @SuppressWarnings("rawtypes")
    public List queryForList(String sql, Object[] args) throws DataAccessException
    {
        List<Map<String, Object>> backVal = null;
        Long stime = System.currentTimeMillis();
        String status = SqlConstants.faild;
        String sqlLogId = StringUtil.generateUUID();
        String argsInfo = (args != null ? "参数 - " + Arrays.toString(args) : "");
        try
        {
            this.beforExecuteSql("queryForList", sql, argsInfo, sqlLogId);
            backVal = this.jdbcTemplate.query(sql, args, new RowMapperResultSetExtractor<Map<String, Object>>(new ColumnMapRowMapper(), 0));

            status = SqlConstants.successful;
        }
        catch (DataAccessException dataAccessException)
        {
            throw dataAccessException;
        }
        finally
        {
            Long etime = System.currentTimeMillis();
            log(sql + " " + argsInfo, stime, etime, status, sqlLogId, argsInfo);
        }
        return backVal;
    }

    /**
     * 查询Sql，返回对象
     */
    @SuppressWarnings("rawtypes")
    public <T> T queryForModel(String sql, Object[] args, Class<T> cls) throws DataAccessException
    {
        T backVal = null;
        Long stime = System.currentTimeMillis();
        String status = SqlConstants.faild;
        String sqlLogId = StringUtil.generateUUID();
        String argsInfo = (args != null ? "参数 - " + Arrays.toString(args) : "");
        try
        {
            backVal = this.jdbcTemplate.queryForObject(sql, args, new BeanPropertyRowMapper<T>(cls));
            status = SqlConstants.successful;
        }
        catch (EmptyResultDataAccessException e)
        {
            backVal = null;
        }
        catch (DataAccessException dataAccessException)
        {
            throw dataAccessException;
        }
        finally
        {
            Long etime = System.currentTimeMillis();
            log(sql + " " + argsInfo, stime, etime, status, sqlLogId, argsInfo);
        }
        return backVal;
    }

    /**
     * 查询Sql，返回List
     */
    @SuppressWarnings("rawtypes")
    public List queryForModelList(String sql, Class cls) throws DataAccessException
    {
        List<Class> backVal = null;
        Long stime = System.currentTimeMillis();
        String status = SqlConstants.faild;
        String sqlLogId = StringUtil.generateUUID();
        try
        {
            this.beforExecuteSql("queryForModelList", sql, "", sqlLogId);
            backVal = this.jdbcTemplate.query(sql, new BeanPropertyRowMapper(cls));
            status = SqlConstants.successful;
        }
        catch (DataAccessException dataAccessException)
        {
            throw dataAccessException;
        }
        finally
        {
            Long etime = System.currentTimeMillis();
            log(sql, stime, etime, status, sqlLogId, "");
        }
        return backVal;
    }

    @SuppressWarnings("rawtypes")
    public List queryForModelList(String sql, Object[] args, Class cls) throws DataAccessException
    {
        List<Class> backVal = null;
        Long stime = System.currentTimeMillis();
        String status = SqlConstants.faild;
        String sqlLogId = StringUtil.generateUUID();
        String argsInfo = (args != null ? "参数 - " + Arrays.toString(args) : "");
        try
        {
            this.beforExecuteSql("queryForModelList", sql, argsInfo, sqlLogId);
            backVal = this.jdbcTemplate.query(sql, args, new BeanPropertyRowMapper(cls));
            status = SqlConstants.successful;
        }
        catch (DataAccessException dataAccessException)
        {
            throw dataAccessException;
        }
        finally
        {
            Long etime = System.currentTimeMillis();
            log(sql + " " + argsInfo, stime, etime, status, sqlLogId, argsInfo);
        }
        return backVal;
    }

    /**
     * 查询Sql，返回long
     */
    public long queryForLong(String sql) throws DataAccessException
    {
        long backVal = 0L;
        Long stime = System.currentTimeMillis();
        String status = SqlConstants.faild;
        String sqlLogId = StringUtil.generateUUID();
        try
        {
            this.beforExecuteSql("queryForLong", sql, "", sqlLogId);
            Long temBack = this.jdbcTemplate.queryForObject(sql, Long.class);
            if (null != temBack)
            {
                backVal = temBack;
            }
            status = SqlConstants.successful;
        }
        catch (DataAccessException dataAccessException)
        {
            throw dataAccessException;
        }
        finally
        {
            Long etime = System.currentTimeMillis();
            log(sql, stime, etime, status, sqlLogId, "");
        }
        return backVal;
    }

    /**
     * 查询long
     *
     * @param sql  预编译带？的sql
     * @param args 参数数组
     * @return long
     */
    public long queryForLong(String sql, Object[] args) throws DataAccessException
    {
        long backVal = 0L;
        Long stime = System.currentTimeMillis();
        String status = SqlConstants.faild;
        String sqlLogId = StringUtil.generateUUID();
        String argsInfo = (args != null ? "参数 - " + Arrays.toString(args) : "");
        try
        {
            this.beforExecuteSql("queryForLong", sql, argsInfo, sqlLogId);
            Long temBack = this.jdbcTemplate.queryForObject(sql, args, Long.class);
            if (null != temBack)
            {
                backVal = temBack;
            }
            status = SqlConstants.successful;
        }
        catch (DataAccessException dataAccessException)
        {
            throw dataAccessException;
        }
        finally
        {
            Long etime = System.currentTimeMillis();
            log(sql + " " + argsInfo, stime, etime, status, sqlLogId, argsInfo);
        }
        return backVal;
    }

    /**
     * @param sql
     * @return Map 如果sql可以查到值，返回Map，否则返回null
     */
    @SuppressWarnings("rawtypes")
    public Map queryForMap(String sql) throws DataAccessException
    {
        Map<String, Object> map = null;
        Long stime = System.currentTimeMillis();
        String status = SqlConstants.faild;
        String sqlLogId = StringUtil.generateUUID();
        try
        {
            this.beforExecuteSql("queryForMap", sql, "", sqlLogId);
            map = this.jdbcTemplate.queryForMap(sql);
            status = SqlConstants.successful;
        }
        catch (EmptyResultDataAccessException emptyException)
        {
            map = null;
            status = SqlConstants.successful;
        }
        finally
        {
            Long etime = System.currentTimeMillis();
            log(sql, stime, etime, status, sqlLogId, "");
        }
        return map;
    }

    /**
     * 查询Map
     *
     * @param sql  预编译带？的sql
     * @param args 参数数组
     * @return Map
     */
    public Map queryForMap(String sql, Object[] args) throws DataAccessException
    {
        Map<String, Object> backVal = null;
        Long stime = System.currentTimeMillis();
        String status = SqlConstants.faild;
        String sqlLogId = StringUtil.generateUUID();
        String argsInfo = (args != null ? "参数 - " + Arrays.toString(args) : "");
        try
        {
            this.beforExecuteSql("queryForMap", sql, argsInfo, sqlLogId);
            backVal = this.jdbcTemplate.queryForMap(sql, args);
            status = SqlConstants.successful;
        }
        catch (EmptyResultDataAccessException emptyException)
        {
            backVal = null;
            status = SqlConstants.successful;
        }
        finally
        {
            Long etime = System.currentTimeMillis();
            log(sql + " " + argsInfo, stime, etime, status, sqlLogId, argsInfo);
        }
        return backVal;
    }

    /**
     * 执行sql，查询String
     */
    public String queryForString(String sql) throws DataAccessException
    {
        String backVal = null;
        Long stime = System.currentTimeMillis();
        String status = SqlConstants.faild;
        String sqlLogId = StringUtil.generateUUID();
        try
        {
            this.beforExecuteSql("queryForString", sql, "", sqlLogId);
            backVal = (String) this.jdbcTemplate.queryForObject(sql, String.class);
            status = SqlConstants.successful;
        }
        catch (EmptyResultDataAccessException ee)
        {
            backVal = null;
            status = SqlConstants.successful;
        }
        finally
        {
            Long etime = System.currentTimeMillis();
            log(sql, stime, etime, status, sqlLogId, "");
        }
        return backVal;
    }

    /**
     * 查询String
     *
     * @param sql  预编译带？的sql
     * @param args 参数数组
     * @return String
     */
    public String queryForString(String sql, Object[] args) throws DataAccessException
    {
        String backVal = null;
        Long stime = System.currentTimeMillis();
        String status = SqlConstants.faild;
        String sqlLogId = StringUtil.generateUUID();
        String argsInfo = (args != null ? "参数 - " + Arrays.toString(args) : "");
        try
        {
            this.beforExecuteSql("queryForString", sql, argsInfo, sqlLogId);
            backVal = (String) this.jdbcTemplate.queryForObject(sql, args, String.class);
            status = SqlConstants.successful;
        }
        catch (EmptyResultDataAccessException ee)
        {
            backVal = null;
            status = SqlConstants.successful;
        }
        finally
        {
            Long etime = System.currentTimeMillis();
            log(sql + " " + argsInfo, stime, etime, status, sqlLogId, argsInfo);
        }
        return backVal;
    }

    /**
     * 执行数据修改操作，返回影响的行数
     */
    public int update(final String sql) throws DataAccessException
    {
        int backVal;
        Long stime = System.currentTimeMillis();
        String status = SqlConstants.faild;
        String sqlLogId = StringUtil.generateUUID();
        try
        {
            this.beforExecuteSql("update", sql, "", sqlLogId);
            backVal = this.jdbcTemplate.update(sql);
            status = SqlConstants.successful;
        }
        catch (DataAccessException dataAccessException)
        {
            throw dataAccessException;
        }
        finally
        {
            Long etime = System.currentTimeMillis();
            log(sql, stime, etime, status, sqlLogId, "");
        }
        return backVal;
    }

    /**
     * 执行数据库修改操作
     *
     * @param sql  预编译带？的sql
     * @param args 参数数组
     * @return 影响的数据行数
     * @throws DataAccessException
     */
    public int update(String sql, Object[] args) throws DataAccessException
    {
        int backVal;
        Long stime = System.currentTimeMillis();
        String status = SqlConstants.faild;
        String sqlLogId = StringUtil.generateUUID();
        String argsInfo = (args != null ? "参数 - " + Arrays.toString(args) : "");
        try
        {
            this.beforExecuteSql("update", sql, argsInfo, sqlLogId);
            backVal = this.jdbcTemplate.update(sql, args);
            status = SqlConstants.successful;
        }
        catch (DataAccessException dataAccessException)
        {
            throw dataAccessException;
        }
        finally
        {
            Long etime = System.currentTimeMillis();
            log(sql + " " + argsInfo, stime, etime, status, sqlLogId, argsInfo);
        }
        return backVal;
    }


}
