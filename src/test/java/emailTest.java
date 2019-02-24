import com.product.Application;
import com.product.judge.api.plugins.email.EmailSendFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class emailTest
{
    @Autowired
    EmailSendFactory emailSendFactory;

    @Test
    public void test() throws Exception
    {
        emailSendFactory.sendEmail("标题", "内容");
    }

}
