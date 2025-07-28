package com.kiylab.board.service;

import com.kiylab.board.domain.dto.ReplyDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class ReplyServiceTest {
  @Autowired
  private ReplyService replyService;

  @Test
  public void testExist() {
    log.info(replyService);
  }

  @Test
  public void testResister() {
    replyService.register(ReplyDTO.builder().bno(5L).text("댓글 테스트").replyer("묘").build());
  }

  @Test
  public void testGetList() {
    replyService.getlist(60L).forEach(log::info);
  }

  @Test
  public void testModify() {
    ReplyDTO replyDTO = replyService.get(60L);
    replyDTO.setText("내용변경");
    replyService.modify(replyDTO);
  }

  @Test
  public void testGet() {
    try {
      replyService.get(12L);
    } catch (IllegalAccessError e) {
      log.error(e.getMessage());
    }
  }

  @Test
  public void testRemove() {
    replyService.remove(201L);
  }
}
