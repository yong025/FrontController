package dao;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.zerock.fc.dao.TimeDAO;



@Log4j2
public class TimeMapperTests {


    @Test
    public void testTimeDAO(){

        log.info(TimeDAO.INSTANCE.getTime());
    }

}
