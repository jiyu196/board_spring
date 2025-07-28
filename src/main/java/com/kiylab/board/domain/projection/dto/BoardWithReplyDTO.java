package com.kiylab.board.domain.projection.dto;

import com.kiylab.board.domain.entity.Board;
import com.kiylab.board.domain.entity.Reply;

public interface BoardWithReplyDTO {
  Board getBoard();
  Reply getReply();
}
