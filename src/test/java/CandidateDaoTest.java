import com.lixindi.gradproject.dao.CandidateMapper;
import com.lixindi.gradproject.dto.CandidateDaoRequest;
import com.lixindi.gradproject.vo.CandidateInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lixindi on 2017/3/6.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext.xml")
public class CandidateDaoTest {
    @Autowired
    CandidateMapper candidateMapper;

    @Test
    public void insertCandidate(){
        List<CandidateInfo> candidateInfos = new ArrayList<CandidateInfo>();
    }

    @Test
    public void getTotal(){
        //System.out.println(candidateMapper.getTotal("机械与运载工程学部","信息"));
    }

    @Test
    public void getCandidate(){
        CandidateDaoRequest candidateDaoRequest = new CandidateDaoRequest();
        candidateDaoRequest.setName("abc");
        candidateDaoRequest.setOffset(0);
        System.out.println(candidateMapper.getCandidate(candidateDaoRequest).size());
    }

}
