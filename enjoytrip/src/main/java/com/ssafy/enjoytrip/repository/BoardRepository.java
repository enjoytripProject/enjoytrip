package com.ssafy.enjoytrip.repository;

import com.ssafy.enjoytrip.model.AttractionInfo;
import com.ssafy.enjoytrip.model.Board;
import com.ssafy.enjoytrip.model.BoardGroup;
import com.ssafy.enjoytrip.model.Plan;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface BoardRepository {
    public void insertBoard(Board board);

    public List<BoardGroup> getBoardGroup();

    public List<Board> getCategoryBoard(int groupId);

    public void deleteBoard(int BoardId);

    public void updateBoard(Board board);

    public List<Board> getAllBoardList();

    public Board getBoardDetail(int boardId);


}