import com.lixindi.gradproject.dao.CandidateMapper;
import com.lixindi.gradproject.dto.CandidateDaoRequest;
import com.lixindi.gradproject.vo.NomineeRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Collections;
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
    public void insertCandidate() {
        List<String> list = new ArrayList<String>();
        list.add("");
        list.add("");
        list.removeAll(Collections.singletonList(""));
        System.out.println(list.size());
    }

    @Test
    public void getTotal() {
        NomineeRequest request = new NomineeRequest();
        request.setDepartment("工程管理");
        List<String> groups = new ArrayList<String>();
        groups.add("");
        request.setGroups(groups);
        System.out.println(candidateMapper.getNominee(request));
    }

    @Test
    public void getCandidate() {
        CandidateDaoRequest candidateDaoRequest = new CandidateDaoRequest();
        candidateDaoRequest.setName("abc");
        candidateDaoRequest.setOffset(0);
        System.out.println(candidateMapper.getCandidate(candidateDaoRequest).size());
    }

}
