package com.kiylab.board.projection.dto;

import com.kiylab.board.entity.Board;
import com.kiylab.board.entity.Member;
import com.kiylab.board.entity.Reply;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BoardWithReplyCount {
  private Board board;
  private Member member;
  private Long replyCount;

  public BoardWithReplyCount(Board board, Member member, Long replyCount) {
    this.board = board;
    this.member = member;
    this.replyCount = replyCount;
  }


}
