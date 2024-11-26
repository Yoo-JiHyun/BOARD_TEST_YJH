package com.aloha.board_test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import com.aloha.board_test.dto.Board;
import com.aloha.board_test.dto.Page;
import com.aloha.board_test.service.BoardService;

import lombok.extern.slf4j.Slf4j;



/**
 * 목록         /board/list     [GET]
 * 조회         /board/select   [GET]
 * 등록         /board/insert   [GET]
 * 등록 처리    /board/insert   [POST]
 * 수정         /board/update   [GET]
 * 수정 처리    /board/update   [POST]
 * 삭제 처리    /board/delete   [POST]
*/
@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;
    
    /**
     * CRUD
     * 목록
     * @return
     * @throws Exception 
     * Read
    */
    @GetMapping("/list")
    public String list(Model model, Page page) throws Exception {
        int total = boardService.count();
        page.setTotal(total);
        List<Board> boardList = boardService.list(page);
        model.addAttribute("boardList", boardList);
        model.addAttribute("page", page);



        return "/board/list";
    }
    
    /**
     * 조회
     * @param id
     * @return
     * @throws Exception 
     * Read
    */
    @GetMapping("/select/{no}")
    public String select(Model model, @PathVariable("no") int no) throws Exception {
        Board board = boardService.select(no);
        model.addAttribute("board", board);

        return "/board/read";
    }


    /**
     * 등록
     * @return
     */
    @GetMapping("/insert")
    public String insert() {

        return "/board/insert";
    }
    
    /**
     * 등록 처리
     * @param board
     * @return
     * @throws Exception 
     * Creat
    */
    @PostMapping("/insert")
    public String insertPost(Board board) throws Exception {
        log.info("board : " + board);
        int result = boardService.insert(board);
        if( result > 0 ) {
            return "redirect:/board/list";
        }
        return "redirect:/board/insert?error";
    }
    
    /**
     * 수정
     * @param id
     * @return
     * @throws Exception 
     * selcet
    */
    @GetMapping("/update/{no}")
    public String update(Model model, @PathVariable("no") int no) throws Exception {
        Board board = boardService.select(no);
        model.addAttribute("board", board);

        return "/board/update";
    }
    
    /**
     * 수정 처리
     * @param board
     * @return
     * @throws Exception 
     * insert
    */
    @PostMapping("/update")
    public String updatePost(Board board) throws Exception {
        int result = boardService.update(board);
        if( result > 0 ) {
            return "redirect:/board/list";
        }
        return "redirect:/board/update?error"+board;
    }

    
    // 삭제 처리
    @PostMapping("/delete")
    public String delete(Board board) throws Exception {
        int result = boardService.delete(board.getNo());
        if( result > 0 ) 
            return "redirect:/board/list";
        return "redirect:/board/update?error";
    }
    
}
