package com.kiylab.board.projection.dto;

import com.kiylab.board.entity.Board;
import com.kiylab.board.entity.Reply;

public interface BoardWithReplyDTO {
  Board getBoard();
  Reply getReply();
}
