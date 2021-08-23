package org.zerock.fc.dao;

import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;

@Log4j2
public enum TimeDAO {
    INSTANCE;

    public String getTime() throws RuntimeException {
        String result =null;// 익명클래스 사용아니기 때문에 사용가능

        try (SqlSession session = MyBatisLoader.INSTANCE.getFactory().openSession()) {//session객체 코드
           result = session.selectOne("org.zerock.fc.dao.TimeMapper.getTime");
            log.info("=================================");
            log.info(result);
            log.info("--------------------------");
        }catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}
