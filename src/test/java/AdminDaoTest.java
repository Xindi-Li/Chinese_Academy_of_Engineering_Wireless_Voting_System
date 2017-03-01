import com.lixindi.gradproject.dao.AdminMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by lixindi on 2017/3/1.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext.xml")
public class AdminDaoTest {
    @Autowired
    private AdminMapper adminMapper;

    @Test
    public void test() {
        System.out.println(adminMapper.selectByUsername("123"));
    }
}
