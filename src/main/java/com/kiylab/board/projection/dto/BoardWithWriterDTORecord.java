package com.kiylab.board.projection.dto;

import com.kiylab.board.entity.Board;
import com.kiylab.board.entity.Member;

public record BoardWithWriterDTORecord(Board board, Member member) {
}
