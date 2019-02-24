package com.product.judge.api.business.controller;

import com.product.judge.api.business.model.Batchinfo;
import com.product.judge.api.business.model.Questionbank;
import com.product.judge.api.business.model.Questionbanktemp;
import com.product.judge.api.business.model.Sysdic;
import com.product.judge.api.business.service.QuestionService;
import com.product.judge.common.base.model.QueryResult;
import com.product.judge.common.base.model.Result;
import com.product.judge.common.constant.GlobalConstant;
import com.product.judge.common.util.ExcelUtil;
import com.product.judge.common.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @author haiyan
 * @version v0.0.1
 * @project judgeApi
 * @describe 试题录入
 * @since 2018/5/20
 **/
@Controller
@RequestMapping("/questions")
public class QuestionController
{
    @Autowired
    QuestionService questionService;

    /**
     * 查询临时表中所有的试题信息
     *
     * @param request
     * @param model
     * @return
     */
    @GetMapping("/tempQuestions")
    public String list(HttpServletRequest request, Model model)
    {
        List<Questionbanktemp> list = questionService.getOwnTempQuestions(SessionUtil.getCurrentUser(request));
        model.addAttribute("tempQuestions", list);
        return "question/questionlist";
    }

    /**
     * 跳转到试题添加界面
     *
     * @param model
     * @return
     */
    @GetMapping("/addQuestions")
    public String toAddPage(Model model)
    {
        List<Sysdic> sysdics = questionService.getSysDicByType(GlobalConstant.DIC_QUESTION_TYPE);
        model.addAttribute("sysdics", sysdics);
        return "question/questionadd";
    }

    /**
     * 往临时表中保存要添加的试题
     *
     * @param questionbanktemp
     * @return
     */
    @PostMapping("/tempQuestion")
    public String addQuestionRecord(HttpServletRequest request, Questionbanktemp questionbanktemp)
    {
        questionService.saveNewTempQuestion(questionbanktemp, SessionUtil.getCurrentUser(request));
        return "redirect:/questions/tempQuestions";
    }

    /**
     * 跳转到临时表试题修改界面
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/tempQuestion/{id}")
    public String toEditPage(HttpServletRequest request, @PathVariable("id") Integer id, Model model)
    {
        Questionbanktemp questionbanktemp = questionService.getOwnTempQuestionsById(SessionUtil.getCurrentUser(request), id);
        model.addAttribute("question", questionbanktemp);
        List<Sysdic> sysdics = questionService.getSysDicByType(GlobalConstant.DIC_QUESTION_TYPE);
        model.addAttribute("sysdics", sysdics);
        return "question/questionadd";
    }

    /**
     * 临时表中的试题提交修改
     *
     * @param questionbanktemp
     * @return
     */
    @PutMapping("/tempQuestion")
    public String updateTempQuestion(HttpServletRequest request, Questionbanktemp questionbanktemp)
    {
        questionService.updateNewTempQuestion(questionbanktemp);
        return "redirect:/questions/tempQuestions";
    }

    /**
     * 删除临时中的试题
     *
     * @param id
     * @return
     */
    @DeleteMapping("/tempQuestion/{id}")
    public String deleteTempQuestion(@PathVariable("id") Integer id)
    {
        questionService.deleteNewTempQuestion(id);
        return "redirect:/questions/tempQuestions";
    }

    /**
     * 试题发布
     *
     * @return
     */
    @PostMapping("/appearQuestions")
    public String appearQuestions(HttpServletRequest request, Batchinfo batchinfo)
    {
        questionService.appearQuestions(batchinfo, request);
        return "redirect:/questions/tempQuestions";
    }

    /**
     * 模糊查询题库表试题
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/questionlikeSearch")
    public String questionlikeSearch(HttpServletRequest request, Model model, String likestr)
    {
        List<Questionbank> list = questionService.getRelevantInfo(likestr, request);
        model.addAttribute("questions", list);
        return "question/questionsearch";
    }

    @RequestMapping("/search")
    @ResponseBody
    public QueryResult searchdata(@RequestParam Map params, HttpServletRequest request)
    {
        params.put("ownid", SessionUtil.getCurrentUser(request));
        //test
        params.put("ownid", "0");
        List rows = questionService.getAllReleasedQuestions(params);
        int total = questionService.getCount4ReleasedQuestions(params);
        return new QueryResult(rows, total);
    }

    /**
     * 下载模板
     *
     * @throws IOException
     */
    @RequestMapping(value = "/downquestiontemplate", method = RequestMethod.GET)
    public void downquestiontemplate(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        String filePath = this.getClass().getClassLoader().getResource("templates/excelTemplate/questionsTemplate.xlsx").getPath();
        //获取要下载的模板名称
        String fileDownName = "questionsTemplate.xlsx";
        try
        {
            InputStream fis = new FileInputStream(new File(filePath));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            response.reset();
            response.setContentType("bin");

            String fileNames = fileDownName;
            String agent = request.getHeader("USER-AGENT");

            String codedfilename = "";
            if (null != agent && -1 != agent.indexOf("MSIE") || null != agent && -1 != agent.indexOf("Trident"))
            {// ie
                String name = java.net.URLEncoder.encode(fileNames, "UTF8");
                codedfilename = name;
            }
            else if (null != agent && -1 != agent.indexOf("Mozilla"))
            {// w3c标准浏览器
                codedfilename = new String(fileNames.getBytes("UTF-8"), "iso-8859-1");
            }

            response.addHeader("Content-Disposition", "attachment; filename=\"" + codedfilename + "\"");
            response.getOutputStream().write(buffer);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 跳转到文件上载界面
     *
     * @param map
     * @return
     */
    @RequestMapping("/uploadquestions")
    public String uploadquestions(Map<String, Object> map)
    {
        return "question/uploadquestions";
    }

    /**
     * 文件上载
     *
     * @param multipartfile
     * @return
     * @throws IOException
     */
    @RequestMapping("/fileuploaded")
    @ResponseBody
    public Result fileuploaded(@RequestParam(value = "xls_file", required = false) MultipartFile multipartfile, HttpServletRequest request, String type) throws IOException
    {
        // 判断文件是否为空
        if (multipartfile.isEmpty())
        {
            return new Result(false);
        }
        InputStream is = null;
        //遍历校验excel并处理数据
        is = multipartfile.getInputStream();
        List<List<String>> datas = ExcelUtil.readXlsx(is);
        if (datas != null && datas.size() > 0)
        {
            String userid = SessionUtil.getCurrentUser(request);
            questionService.insertTempQuestionsByExcel(datas, userid, type);
            return new Result(true);
        }
        else
        {
            return new Result(false);
        }
    }

    @RequestMapping(value = "getQuestionType")
    @ResponseBody
    List getQuestionType()
    {
        return questionService.getQuestionType();
    }
}