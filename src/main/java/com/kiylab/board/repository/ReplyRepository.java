package com.kiylab.board.repository;

import com.kiylab.board.entity.Board;
import com.kiylab.board.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply,Long> {
  void deleteByBoard_Bno(Long bno);
}
