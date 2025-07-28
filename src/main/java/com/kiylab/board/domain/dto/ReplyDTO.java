package com.kiylab.board.domain.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.AbstractList;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReplyDTO {
  private Long rno;
  private String text;
  private String replyer;
  private Long bno;
  private LocalDateTime regDate, modDate;
}
