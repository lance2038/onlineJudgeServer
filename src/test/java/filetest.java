import com.product.judge.common.util.StringUtil;

import java.io.File;

/**
 * @author lance
 * @version v0.0.1
 * @project judgeApi
 * @describe
 * @since 2018/5/23
 **/
public class filetest
{
    public static void main(String[] args)
    {
        System.out.println(StringUtil.getMD5Str(StringUtil.getMD5Str("2")));

    }
}
