import com.lixindi.gradproject.utils.GetMD5;
import com.lixindi.gradproject.vo.VoteSetting;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Test
    public void set() {
        voteDao.updateStatus(true);
    }

    @Test
    public void get() {
        System.out.println(voteDao.getVoteParam());
    }

    @Test
    public void addId() {
        voteDao.addIdToSet(1);
    }

    @Test
    public void getMD5(){
        System.out.println(GetMD5.getMD5("123"));
    }


}
