package org.zerock.fc.service;

import lombok.extern.log4j.Log4j2;
import org.zerock.fc.dao.BoardDAO;
import org.zerock.fc.dto.BoardDTO;
import org.zerock.fc.dto.PageDTO;


import java.util.List;

@Log4j2
public enum BoardService {

    INSTANCE;

    public Integer register(BoardDTO boardDTO) throws RuntimeException{

        return BoardDAO.INSTANCE.insert(boardDTO);
    }

    public BoardDTO read(Integer bno) throws RuntimeException {

        log.info("BoardService read............." + bno);

        return BoardDAO.INSTANCE.select(bno);

    }

    public List<BoardDTO> getlist(PageDTO pageDTO)throws RuntimeException{//페이징

        log.info("Getlist....");
        log.info(pageDTO);
        return BoardDAO.INSTANCE.list(pageDTO);//dao에서 처리한 후 이어진 값을 List에 전달한다.
    }


    public void insert(BoardDTO boardDTO){
        log.info("insert ..........");
        BoardDAO.INSTANCE.insert(boardDTO);
    }

    public BoardDTO select(Integer bno){

        log.info("select ..........");
        log.info(BoardDAO.INSTANCE.select(bno));

       return BoardDAO.INSTANCE.select(bno);
    }


    public void modify(BoardDTO boardDTO) throws RuntimeException {
        log.info("BoardService update..............");
        BoardDAO.INSTANCE.update(boardDTO);
    }

    public void delete(Integer bno){

        log.info("delete.......");
        BoardDAO.INSTANCE.delete(bno);
    }

}
