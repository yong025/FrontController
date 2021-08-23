package org.zerock.fc.dao;

import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.zerock.fc.dto.AttachDTO;
import org.zerock.fc.dto.BoardDTO;
import org.zerock.fc.dto.PageDTO;

import java.util.List;

@Log4j2
public enum BoardDAO {

    INSTANCE;

    private static final String PREFIX = "org.zerock.fc.dao.BoardMapper";

    public Integer insert(BoardDTO boardDTO)throws RuntimeException {

        Integer bno = null;

        try(SqlSession session = MyBatisLoader.INSTANCE.getFactory().openSession()) {//true를 넣으면 auto commit기능
             session.insert(PREFIX+".insert", boardDTO);
             bno = boardDTO.getBno();

             List<AttachDTO> attachDTOList = boardDTO.getAttachDTOList();
            if(attachDTOList != null && attachDTOList.size() > 0){

             for(AttachDTO attachDTO:attachDTOList){
                 attachDTO.setBno(bno);
                 session.insert(PREFIX+".insertAttach", attachDTO);
             }
        }
             session.commit();
        }catch (Exception e){
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return bno;
    }

    public BoardDTO select(Integer bno) throws RuntimeException {
        BoardDTO dto = null;

        try (SqlSession session = MyBatisLoader.INSTANCE.getFactory().openSession(true)) { //openSession에 true를 주면 autoCommit됨.
            session.update(PREFIX + ".updateViewcount", bno);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage()); //예외 잡아서 던지기!
        }

        try (SqlSession session = MyBatisLoader.INSTANCE.getFactory().openSession(true)) {//true를 넣으면 auto commit기능
            dto = session.selectOne(PREFIX + ".select", bno); //하나 가져올때는 selectOne 여러개는 selectList로 호출한다.
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return dto;
    }

    public List<BoardDTO> list(PageDTO pageDTO) throws RuntimeException {
        List<BoardDTO> list = null;

        try (SqlSession session = MyBatisLoader.INSTANCE.getFactory().openSession(true)) {//true를 넣으면 auto commit기능
           list = session.selectList(PREFIX + ".list", pageDTO); //파라미터값 없음
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return list;
    }

    public void update(BoardDTO boardDTO) throws RuntimeException {
        try (SqlSession session = MyBatisLoader.INSTANCE.getFactory().openSession(true)) { //openSession에 true를 주면 autoCommit됨.
            session.update(PREFIX + ".update", boardDTO);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage()); //예외 잡아서 던지기!
        }
    }

    public void delete(Integer bno) throws RuntimeException {

        try(SqlSession session = MyBatisLoader.INSTANCE.getFactory().openSession(true)) {//true를 넣으면 auto commit기능
            session.delete(PREFIX+".delete", bno);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

    }
}
