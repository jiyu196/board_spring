package com.kiylab.board.controller;

import com.kiylab.board.dto.BoardDTO;
import com.kiylab.board.dto.PageRequestDTO;
import com.kiylab.board.entity.Board;
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







}
