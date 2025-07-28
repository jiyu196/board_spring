package com.kiylab.board.controller;

import com.kiylab.board.service.ReplyService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("replies")
//@Data  // getter, setter, requires~
@RequiredArgsConstructor
public class ReplyController {
  private final ReplyService replyService;

  @GetMapping(value = "board/{bno}")
  public ResponseEntity<?> getlist(@PathVariable("bno") Long bno) {
    return ResponseEntity.ok(replyService.getlist(bno));
  }

}
