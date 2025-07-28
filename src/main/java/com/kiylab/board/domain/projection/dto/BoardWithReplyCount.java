package com.kiylab.board.domain.projection.dto;

import com.kiylab.board.domain.entity.Board;
import com.kiylab.board.domain.entity.Member;

public record BoardWithReplyCount(Board board, Member member, Long replyCount) { }
