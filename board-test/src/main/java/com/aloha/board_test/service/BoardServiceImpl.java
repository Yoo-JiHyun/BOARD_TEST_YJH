package com.aloha.board_test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aloha.board_test.dto.Board;
import com.aloha.board_test.dto.Page;
import com.aloha.board_test.mapper.BoardMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardMapper boardMapper;
    
    @Override
    public List<Board> list(Page page) throws Exception {
        List<Board> list = boardMapper.list(page);
        return list;
    }

    @Override
    public Board select(int no) throws Exception {
        Board board = boardMapper.select(no);
        return board;
    }

    @Override
    public int insert(Board board) throws Exception {
        int result = boardMapper.insert(board);

        return result;
    }

    @Override
    public int update(Board board) throws Exception {
        // 게시글 정보 수정
        int result = boardMapper.update(board);

        return result;
    }

    @Override
    public int delete(int no) throws Exception {

        // 게시글 삭제
        int result = boardMapper.delete(no);

        return result;
    }

    @Override
    public int count() throws Exception {
       return boardMapper.count();
    }
  
}
