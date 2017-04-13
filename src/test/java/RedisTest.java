import com.lixindi.gradproject.utils.GetMD5;
import com.lixindi.gradproject.vo.VoteSetting;
import com.lixindi.gradproject.vo.VotedNum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.access.BootstrapException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.lixindi.gradproject.redis.VoteDao;

/**
 * Created by lixindi on 2017/3/23.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext.xml")
public class RedisTest {
    @Autowired
    private VoteDao voteDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void set() {
        voteDao.setKeyValue("is_start",true);
    }

    @Test
    public void get() {
        System.out.println(voteDao.getValueByKey("voteResult"));
    }

    @Test
    public void delKey(){
        redisTemplate.delete("机械与运载工程学部score");
    }

    @Test
    public void getMD5(){
        System.out.println(GetMD5.getMD5("123"));
    }


}
