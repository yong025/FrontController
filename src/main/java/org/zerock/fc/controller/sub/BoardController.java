package org.zerock.fc.controller.sub;

import lombok.extern.log4j.Log4j2;
import org.zerock.fc.annotations.Controller;
import org.zerock.fc.annotations.GetMapping;
import org.zerock.fc.annotations.PostMapping;
import org.zerock.fc.dao.BoardDAO;
import org.zerock.fc.dto.BoardDTO;
import org.zerock.fc.dto.PageDTO;
import org.zerock.fc.dto.PageMaker;
import org.zerock.fc.service.BoardService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
@Log4j2
@Controller(value = "/board")
public class BoardController {

    private Integer getInt(String str){
        try {
            int value = Integer.parseInt(str);//문자열로 받은 값을 숫자로 변경
            if(value <= 0){ // 입력받은 값이 0보다 작으면 null로 리턴(정상적인 페이지요청만 받음)
                return null;
            }
            return value;
        }catch(Exception e){
            return null; //오류가 생기면 null로 반환하라.
        }
    }

    @PostMapping("/board/delete.do")
    public String deletePost(HttpServletRequest request, HttpServletResponse response)throws Exception{

        Integer bno = Integer.parseInt(request.getParameter("bno"));

        BoardDAO.INSTANCE.delete(bno);

        return "re:/board/list.do";
    }

    @GetMapping("/board/modify.do")
    public String modifyGet(HttpServletRequest request, HttpServletResponse response)throws Exception{

        System.out.println("board modify get....do");

        Integer bno = getInt(request.getParameter("bno"));
        Integer page = getInt(request.getParameter("page"));
        Integer size = getInt(request.getParameter("size"));

        PageDTO pageDTO = PageDTO.builder().build();

        if (page != null) { pageDTO.setPage(page); }
        if (size != null) { pageDTO.setSize(size); }

        BoardDTO boardDTO = BoardService.INSTANCE.read(bno);
        log.info(boardDTO);

        request.setAttribute("boardDTO", boardDTO);
        request.setAttribute("pageDTO", pageDTO);


        return "/board/modify";
    }

    @PostMapping("/board/modify.do")
    public String modifyPost(HttpServletRequest request, HttpServletResponse response)throws Exception{

        Integer bno = getInt(request.getParameter("bno"));
        String modifyTitle = request.getParameter("title");
        String modifyContent = request.getParameter("content");

        BoardDTO boardDTO = BoardDTO.builder()
                .bno(bno)
                .title(modifyTitle)
                .content(modifyContent)
                .build();

        BoardDAO.INSTANCE.update(boardDTO);

        return "re:/board/list.do?bno=" + bno;
    }

    @GetMapping("/board/read.do")
    public String readGet(HttpServletRequest request, HttpServletResponse response)throws Exception{

        System.out.println("board read post....do");

        Integer bno = getInt(request.getParameter("bno"));
        Integer page = getInt(request.getParameter("page"));
        Integer size = getInt(request.getParameter("size"));


        PageDTO pageDTO = PageDTO.builder().build();//pageDTO에 받은 값을 저장해야한다.

        if(page != null) {// pageDTO내의 page가 null이면 받은 page값을 넣어준다.
            pageDTO.setPage(page);
        }
        if(size != null) {// pageDTO 내의 size가 null이면 받은 size값을 넣어준다.
            pageDTO.setSize(size);
        }

        BoardDTO boardDTO = BoardService.INSTANCE.read(bno);

        request.setAttribute("boardDTO", boardDTO);
        request.setAttribute("pageDTO", pageDTO);


        return "/board/read";
    }

    @GetMapping("/board/register.do")
    public String registerGet(HttpServletRequest request, HttpServletResponse response)throws Exception{

        System.out.println("board register post....do");


        return "/board/register";
    }

    @PostMapping("/board/register.do")
    public String registerPost(HttpServletRequest request, HttpServletResponse response)throws Exception{

        System.out.println("board register post....do");

        BoardDTO boardDTO = BoardDTO.builder() //파라미터 수집
                .title(request.getParameter("title"))
                .content(request.getParameter("content"))
                .writer(request.getParameter("writer"))
                .build();

        Integer bno = BoardService.INSTANCE.register(boardDTO);


        return "re:/board/list.do?bno=" + bno;
    }

    @GetMapping(value = "/board/list.do")
    public String list(HttpServletRequest request, HttpServletResponse response)throws Exception{

        Integer page = getInt(request.getParameter("page"));// 2번  파라미터 수집해서 getInt메서드에 입력.
        Integer size = getInt(request.getParameter("size"));// getInt 메서드 재정의
        //size는 페이지 내의 글 갯수

        PageDTO pageDTO = PageDTO.builder().build();//pageDTO에 받은 값을 저장해야한다.

        if(page != null) {// pageDTO내의 page가 null이면 받은 page값을 넣어준다.
            pageDTO.setPage(page);
        }
        if(size != null) {// pageDTO 내의 size가 null이면 받은 size값을 넣어준다.
            pageDTO.setSize(size);
        }

        log.info("============================1");
        log.info(pageDTO);//파라미터 수집 ck


        List<BoardDTO> dtoList = BoardService.INSTANCE.getlist(pageDTO);//pageDTO던져주면 List<BoardDTO>목록받아온다.
        //page 값 1, size값 10인 pageDTO를 서비스에서 호출한 후 dtolist로 정의한다.
        //BoardDTO의 값은 1페이지에 10개씩 정의

        log.info("============================2");
        log.info(dtoList);

        request.setAttribute("dtolist", dtoList);
        //----------------------------------------------------pagemaker만들고 오기------------------------------
        PageMaker pageMaker = new PageMaker(pageDTO.getPage(),pageDTO.getSize(),123);
        //생성된 객체에 페이지 메이커의 현재 페이지, 페이지 내 출력할 갯수, 데이터의 총 갯수(123이라고 가정)를 pageMaker 인자값에서 받기

        request.setAttribute("pageMaker", pageMaker);


        return "board/list";
    }

}