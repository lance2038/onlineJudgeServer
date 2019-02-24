import com.product.judge.api.business.model.Sysdic;

import java.util.*;

/**
 * @author lance
 * @version v0.0.1
 * @project judgeApi
 * @describe
 * @since 2018/6/8
 **/
public class uuidtest
{
    public static void main(String[] args)
    {
//        System.out.println(UUID.randomUUID().toString());
//        System.out.println(UUID.randomUUID().toString().replace("-", ""));
        Map map = new HashMap<>();
        map.put("key1","value1");
        map.put("key2","value2");
        System.out.println(map.get("key2"));

        List list = new ArrayList();
        Sysdic sysdic = new Sysdic();
        Sysdic sysdic2 = new Sysdic();
        list.add(sysdic);
        list.add(sysdic2);
    }
}
