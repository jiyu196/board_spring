package com.kiylab.board.service;

import com.kiylab.board.domain.dto.BoardDTO;
import com.kiylab.board.domain.dto.PageRequestDTO;
import com.kiylab.board.domain.dto.PageResponseDTO;
import com.kiylab.board.domain.dto.ReplyDTO;
import com.kiylab.board.domain.projection.dto.BoardWithReplyCount;

import java.util.List;

public interface ReplyService {
  Long register(ReplyDTO dto);

  List<ReplyDTO>  getlist(Long bno);

  ReplyDTO get(Long rno);

  void modify (ReplyDTO dto);

  void remove (Long rno);
}
