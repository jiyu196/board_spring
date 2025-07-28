package com.kiylab.board.controller;

import com.kiylab.board.domain.dto.BoardDTO;
import com.kiylab.board.domain.dto.PageRequestDTO;
import com.kiylab.board.service.BoardService;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("board")
@Log4j2
@Data
public class BoardController {
  private final BoardService service;

  // 목록 조회
  @GetMapping("list")
  public void list(@ModelAttribute("requestDto") PageRequestDTO dto, Model model) {
    model.addAttribute("dto", service.getList(dto));
  }

  // 등록 폼
  @GetMapping("register")
  public void register() {}



  // 등록 프로세스
  @PostMapping("register")
  public String register(BoardDTO dto , RedirectAttributes rttr) {
    rttr.addFlashAttribute("msg", service.register(dto));
    return "redirect:list";
  }

  @GetMapping("read")
  public void read(@ModelAttribute("requestDto") PageRequestDTO dto, Model model, Long bno) {
    log.info(bno);
    log.info(service.get(bno));
    model.addAttribute("dto", service.get(bno));
    log.info(model.toString());
//    return "read";
  }

  @GetMapping("modify")
  public void modify(@ModelAttribute("requestDto") PageRequestDTO dto, Model model, Long bno) {
    model.addAttribute("dto", service.get(bno));
  }

  @PostMapping("modify")
  public String modify(@ModelAttribute("requestDto") PageRequestDTO dto, BoardDTO boardDTO, RedirectAttributes rttr) {
    service.modify(boardDTO);
    rttr.addAttribute("bno", boardDTO.getBno());
    rttr.addAttribute("page", dto.getPage());
    rttr.addAttribute("size", dto.getSize());
    rttr.addAttribute("type", dto.getType());
    rttr.addAttribute("keyword", dto.getKeyword());
    return  "redirect:read";
  }

  @PostMapping("remove")
  public  String remove(PageRequestDTO dto, Long bno, RedirectAttributes rttr) {
    service.remove(bno);

    rttr.addAttribute("msg", bno);
    rttr.addAttribute("page", 1);
    rttr.addAttribute("size", dto.getSize());
    return "redirect:list";
  }


}
