package com.product.judge.common.util;

import org.springframework.util.StringUtils;

import java.security.MessageDigest;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author haiyan
 * @version v0.0.1
 * @project judgeApi
 * @describe 字符串处理类
 * @since 2018/5/10
 **/
public class StringUtil extends StringUtils
{

    private static char[] numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz" + "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();

    private static Random randGen = new Random();

    /**
     * 在字符串上加CDATA。
     *
     * @param string
     * @return String
     */
    public static String addCDATA(String string)
    {
        String returnStr = "<![CDATA[";
        returnStr += string;
        returnStr += "]]>";
        return returnStr;
    }

    /**
     * 判断是否是空字符串，“”也算是空字符串
     *
     * @param str
     * @return boolean
     */
    public static boolean isNullString(String str)
    {
        if (str == null || str.trim().equals("") || str.trim().equalsIgnoreCase("NULL"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }


    /**
     * 判断输入的字符串是否为null，如果是null则返回"",否则，返回原字符串。
     *
     * @param str
     * @return String
     */
    public static String null2String(String str)
    {
        if (isNullString(str))
        {
            return "";
        }
        else
        {
            return str;
        }
    }

    /**
     * 字符串左边开始填充字符处理
     *
     * @param strPara 原字符串
     * @param cPara   要填充的字符
     *                填充后字符达到的总长度
     * @return String
     */
    public static String addChar(String strPara, char cPara, int nLength)
    {

        if (strPara.length() >= nLength)
        {
            return strPara;
        }
        else
        {
            String temp = "";
            for (int i = 0; i < (nLength - strPara.length()); i++)
            {
                temp = temp + String.valueOf(cPara);
            }
            return temp + strPara;
        }
    }

    /**
     * 判断是否是数字型的字符串
     *
     * @param str 要判断的字符串
     * @return boolean true：真，false：假
     */
    public static boolean isNumericString(String str)
    {
        boolean b = true;
        if (str != null && str.length() > 0)
        {
            for (int i = 0; i < str.length(); i++)
            {
                if (str.charAt(i) < '0' || str.charAt(i) > '9')
                {
                    b = false;
                    break;
                }
            }
        }
        return b;
    }

    /**
     * 判断字符串是否都为数字
     *
     * @param str
     * @return
     */
    public static boolean isAllNumbericString(String str)
    {
        Pattern pattern = Pattern.compile("-?[0-9]+.?[0-9]+");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches())
        {
            return false;
        }
        return true;
    }


    /**
     * html编码，把< 转换为html可以识别的字符串，转换的包含：\\&,<,>,\r,\n,\",\',空串。
     *
     * @param str 待编码的字符串
     * @return html编码
     */
    public static String htmlIncode(String str)
    {
        if (str != null && !str.equals(""))
        {
            str = str.replaceAll("\\&", "&amp;");
            str = str.replaceAll("<", "&lt;");
            str = str.replaceAll(">", "&gt;");
            str = str.replaceAll("\r", "");
            str = str.replaceAll("\n", "&nbsp;<br>&nbsp;");
            str = str.replaceAll("\"", "&quot;");
        }
        else
        {
            str = "&nbsp;";
        }
        return str;
    }

    /**
     * 把ISO8859_1编码转换为GBK编码
     *
     * @param strvalue
     * @return String
     */
    public static String iso8859Rgbk(String strvalue)
    {
        try
        {
            if (strvalue == null)
            {
                return strvalue;
            }
            else
            {
                strvalue = new String(strvalue.getBytes("ISO8859_1"), "GBK");
                return strvalue;
            }
        }
        catch (Exception e)
        {
            return strvalue;
        }
    }

    /**
     * gbk转iso8859
     *
     * @param s
     * @return
     */
    public static String gbk2Iso8859(String s)
    {
        try
        {
            if (s != null)
            {
                s = new String(s.getBytes("GBK"), "ISO8859_1");
            }
        }
        catch (Exception e)
        {
        }
        return s;
    }

    /**
     * MD5加密
     *
     * @param s
     * @return String
     */
    public static String getMD5Str(String s)
    {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try
        {
            byte[] strTemp = s.getBytes();
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++)
            {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str).toUpperCase();
        }
        catch (Exception e)
        {
            return null;
        }
    }

    /**
     * 切分字符串
     *
     * @param originStr
     * @param len
     * @return
     */
    public static String subString(String originStr, int len)
    {
        try
        {
            if (originStr == null || originStr.equals("") || len < 1)
            {
                return "";
            }
            byte[] strByte = new byte[len];
            if (len > originStr.getBytes("GBK").length)
            {
                return originStr;
            }
            System.arraycopy(originStr.getBytes("GBK"), 0, strByte, 0, len);
            int count = 0;
            for (int i = 0; i < len; i++)
            {
                int value = (int) strByte[i];
                if (value < 0)
                {
                    count++;
                }
            }
            if (count % 2 != 0)
            {
                len = (len == 1) ? ++len : --len;
            }
            return new String(strByte, 0, len, "GBK");
        }
        catch (Exception ee)
        {
            ee.printStackTrace();
        }
        return "";
    }

    /**
     * 获得字符串指定位长度对应的截取部分
     *
     * @param strPrevious 原字符串
     * @param size        截取长度(字节)
     * @return String
     */
    public static String getSubStringByByteSize(String strPrevious, int size)
    {
        int i = 0;
        String strTemp = "";
        char[] chars = strPrevious.toCharArray();
        for (int j = 0; j < chars.length; j++)
        {
            if (chars[j] > 255)
            {
                i += 2;
            }
            else
            {
                i += 1;
            }

            if (i <= size)
            {
                strTemp = strTemp + strPrevious.substring(j, j + 1);
            }
            if (i > size)
            {
                strTemp = strTemp + "…";
                break;
            }
        }

        return strTemp;
    }

    /**
     * 生成一个指定长度的随机字符串
     *
     * @param length
     * @return
     */
    public static final String randomString(int length)
    {
        if (length < 1)
        {
            return null;
        }
        char[] randBuffer = new char[length];
        for (int i = 0; i < randBuffer.length; i++)
        {
            randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];
        }
        return new String(randBuffer);
    }

    /**
     * 生成一个指定长度的随机字符串 都是数字
     *
     * @param length
     * @return
     */
    public static final String randomIntger(int length)
    {
        Random random = new Random();

        String result = "";

        for (int i = 0; i < length; i++)
        {
            result += random.nextInt(10);
        }

        return result;
    }

    public static String generateUUID()
    {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
