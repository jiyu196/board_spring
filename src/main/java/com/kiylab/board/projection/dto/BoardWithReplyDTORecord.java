package com.kiylab.board.projection.dto;

import com.kiylab.board.entity.Board;
import com.kiylab.board.entity.Reply;

public record BoardWithReplyDTORecord(Board board, Reply reply) {
}
