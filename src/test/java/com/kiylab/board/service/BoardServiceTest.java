package com.kiylab.board.service;

import com.kiylab.board.dto.BoardDTO;
import com.kiylab.board.dto.PageRequestDTO;
import com.kiylab.board.entity.Board;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class BoardServiceTest {
  @Autowired
  private BoardService service;


  @Test
  public void testRegister() {
    BoardDTO dto = BoardDTO.builder()
            .title("테스트 코드 제목")
            .content("테스트 코드 내용")
            .writerEmail("user1@gmail.com")
            .build();
    Long bno = service.register(dto);
    log.info(bno);
  }

  @Test
  public void testList() {
    service.getList(PageRequestDTO.builder().page(1).size(10).build()).getList().forEach(log::info);
  }

  @Test
  public void testGet() {
    log.info(service.get(55L));
  }

  @Test
  public void testRemove() {
    service.remove(2L);
  }

  @Test
  public void testModify() {
    BoardDTO dto = service.get(55L);
    dto.setTitle("수정 제목");
    service.modify(dto);
  }

}
