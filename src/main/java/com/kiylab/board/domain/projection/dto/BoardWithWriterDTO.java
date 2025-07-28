package com.kiylab.board.domain.projection.dto;

import com.kiylab.board.domain.entity.Board;
import com.kiylab.board.domain.entity.Member;

public interface BoardWithWriterDTO {
  Board getBoard();
  Member getMember();
}
