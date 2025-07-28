package com.kiylab.board.repository;

import com.kiylab.board.domain.entity.Board;
import com.kiylab.board.domain.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply,Long> {
  void deleteByBoard_Bno(Long bno);

  //댓글 목록이 나오는데 bno를 가지고 해야함
  List<Reply> findByBoard_BnoOrderByRno(Long bno);
  List<Reply> findByBoard(Board board);
}
