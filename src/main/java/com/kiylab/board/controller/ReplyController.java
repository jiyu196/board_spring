package com.kiylab.board.controller;

import com.kiylab.board.domain.dto.ReplyDTO;
import com.kiylab.board.service.ReplyService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

  @PostMapping("")
  public ResponseEntity<?> createReply(@RequestBody ReplyDTO dto) {
    log.info(dto);
    return ResponseEntity.ok(replyService.register(dto));
  }

  @DeleteMapping("/{rno}")
  public ResponseEntity<?> deleteReply(@PathVariable("rno") Long rno) {
    replyService.remove(rno);
    return ResponseEntity.ok("삭제완료");
  }

  @PutMapping("/{rno}")
  public ResponseEntity<?> modifyReply(@PathVariable("rno") Long rno, @RequestBody ReplyDTO dto) {
    log.info(dto);
    dto.setRno(rno);
    replyService.modify(dto);
    return ResponseEntity.ok("수정완료");
  }

}
