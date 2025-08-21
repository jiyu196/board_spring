package com.kiylab.board.controller;

import com.kiylab.board.domain.dto.BoardDTO;
import com.kiylab.board.domain.dto.PageRequestDTO;
import com.kiylab.board.service.BoardService;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("boardrest")
@Log4j2
@Data
@CrossOrigin(origins = "http://localhost:3000")
public class BoardRestController {
  private final BoardService service;

  // 목록 조회
  @GetMapping("list")
  public ResponseEntity<?> list(@ModelAttribute("requestDto") PageRequestDTO dto, Model model) {
    return ResponseEntity.ok(service.getList(dto));
  }  // 반환값 void말고  / 이렇게 되면 restapi 구현이 끝난거


  // 등록 프로세스
  @PostMapping("register")
  public ResponseEntity<?> register(@RequestBody BoardDTO dto) {
    return ResponseEntity.ok(service.register(dto));
  }

  @GetMapping("read")
  public ResponseEntity<?> read(@ModelAttribute("requestDto") PageRequestDTO dto, Long bno) {
    log.info(bno);
    return ResponseEntity.ok(service.get(bno));
  }

  @PostMapping("modify")
  public ResponseEntity<?> modify(BoardDTO boardDTO) {
//    rttr.addAttribute("bno", boardDTO.getBno());
//    rttr.addAttribute("page", dto.getPage());
//    rttr.addAttribute("size", dto.getSize());
//    rttr.addAttribute("type", dto.getType());
//    rttr.addAttribute("keyword", dto.getKeyword());
    service.modify(boardDTO);  // 이걸 넘기면 리턴 타입이 없어서 글번호 넘기기로함.
    return  ResponseEntity.ok(boardDTO.getBno());
  }

  @PostMapping("remove")
  public  ResponseEntity<?> remove(Long bno) {

//    rttr.addAttribute("msg", bno);
//    rttr.addAttribute("page", 1);
//    rttr.addAttribute("size", dto.getSize());
    service.remove(bno);  // 이것도 modify 랑 같은이유로
    return ResponseEntity.ok("글이 삭제되었습니다.");
  }


}
