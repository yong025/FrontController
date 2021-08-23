package dao;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import org.zerock.fc.dao.BoardDAO;
import org.zerock.fc.dto.AttachDTO;
import org.zerock.fc.dto.BoardDTO;
import org.zerock.fc.dto.PageDTO;

@Log4j2
public class BoardDAOTests {

    @Test
    public void testInsertWithAttach(){
        BoardDTO boardDTO = BoardDTO.builder()
                .title("Test")
                .content("Test")
                .writer("user1")
                .build();

//       Integer bno =  BoardDAO.INSTANCE.insert(boardDTO);

       for(int i =0; i < 3; i++){
           AttachDTO attachDTO = AttachDTO.builder()
                   .fname("file" + i + ".jpg")
                   .savename(System.currentTimeMillis() + "_file" + i + ".jpg")
                   .imgyn(true)
                   .build();
           boardDTO.addAttach(attachDTO);
       }

       //-------------------controller ---------------------------------------
        log.info("========================================");
        log.info(boardDTO);

        BoardDAO.INSTANCE.insert(boardDTO);
        //

    }

    @Test
    public void testInsert(){
        BoardDTO boardDTO = BoardDTO.builder()
                .title("Test")
                .content("Test")
                .writer("user1")
                .build();

        BoardDAO.INSTANCE.insert(boardDTO);

        log.info("========================================");
        log.info(boardDTO);

    }

    @Test
    public void testselect(){
        log.info(BoardDAO.INSTANCE.select(4));
    }

    @Test
    public void testlist(){

        PageDTO pageDTO = PageDTO.builder().page(3).build();


        BoardDAO.INSTANCE.list(pageDTO).forEach(boardDTO -> log.info(boardDTO));
    }

    @Test
    public void testupdate(){

        BoardDTO boardDTO = BoardDTO.builder()
                .bno(32766)
                .title("왜케안바뀌냐")
                .content("bbbbbb")
                .build();

        BoardDAO.INSTANCE.update(boardDTO);
    }

    @Test
    public void delete(){

        log.info("delete......");
        BoardDAO.INSTANCE.delete(5);
    }
}
