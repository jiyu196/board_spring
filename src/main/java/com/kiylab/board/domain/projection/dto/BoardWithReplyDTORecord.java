package com.kiylab.board.domain.projection.dto;

import com.kiylab.board.domain.entity.Board;
import com.kiylab.board.domain.entity.Reply;

public record BoardWithReplyDTORecord(Board board, Reply reply) {
}
