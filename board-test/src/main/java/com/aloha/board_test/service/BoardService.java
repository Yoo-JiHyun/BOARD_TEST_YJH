package com.aloha.board_test.service;

import java.util.List;

import com.aloha.board_test.dto.Board;
import com.aloha.board_test.dto.Page;



public interface BoardService {

    public List<Board> list(Page page) throws Exception;

    public Board select(int no) throws Exception;

    public int insert(Board board) throws Exception;

    public int update(Board board) throws Exception;

    public int delete(int no) throws Exception;

    public int count() throws Exception;
}
