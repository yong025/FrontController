package org.zerock.fc.dao;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

//enum에서 lombok 사용 불가
public enum MyBatisLoader {
    INSTANCE;

    private SqlSessionFactory sqlSessionFactory;

    MyBatisLoader() {//초기화 해야함
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
           sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);//변수명 일치
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public SqlSessionFactory getFactory() {
        return this.sqlSessionFactory;
    }
}
