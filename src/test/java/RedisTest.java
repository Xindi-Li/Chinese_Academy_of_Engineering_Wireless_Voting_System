import com.lixindi.gradproject.redis.VoteDao;
import com.lixindi.gradproject.utils.GetMD5;
import com.lixindi.gradproject.vo.RoundTimes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
        RoundTimes roundTimes = voteDao.getValueByKey("roundtimes");
        roundTimes.setRound(1);
        roundTimes.setTimes(2);
        voteDao.setKeyValue("roundtimes", roundTimes);
    }

    @Test
    public void get() {
        System.out.println(redisTemplate.keys("*"));
    }

    @Test
    public void delKey() {
        redisTemplate.delete(redisTemplate.keys("*"));
    }

    @Test
    public void getMD5() {
        System.out.println(GetMD5.getMD5("123"));
    }


}
