package com.kiylab.board.projection.dto;

import com.kiylab.board.entity.Board;
import com.kiylab.board.entity.Member;
import lombok.Getter;

@Getter
public class BoardWithWriterDTOClass {
  private final Board board;
  private final Member member;

  public BoardWithWriterDTOClass(Board board, Member member) {
    this.board = board;
    this.member = member;
  }
}
