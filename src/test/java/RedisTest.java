import com.lixindi.gradproject.redis.UserDao;
import com.lixindi.gradproject.vo.AccountInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by lixindi on 2017/3/23.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext.xml")
public class RedisTest {
    @Autowired
    private UserDao userDao;

    @Test
    public void set() {
        userDao.add();
    }

    @Test
    public void get() {
        AccountInfo accountInfo = userDao.get("abc");

    }
}
