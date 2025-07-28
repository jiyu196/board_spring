package com.kiylab.board.repository;

import com.kiylab.board.domain.projection.dto.*;
import com.kiylab.board.domain.entity.Board;
import com.kiylab.board.domain.entity.Member;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class BoardRepositoryTest {
  @Autowired
  private BoardRepository repository;


  @Test
  public void testExist() {
    Assertions.assertNotNull(repository);
  }

  @Test
  public void insertBoards() {
    IntStream.range(1, 100).forEach(i -> {
      Member member = Member.builder().email("user" + i + "@gmail.com").build();

      Board board = Board.builder()
              .title("title" + i)
              .content("content" + i)
              .writer(member)
              .build();
      repository.save(board);

    });
  }

  @Test
  @Transactional(readOnly = true)
  public void testRead() {
    Board board = repository.findById(2L).orElse(null);
    log.info(board);
    log.info(board.getWriter());
  }

  @Test
  public void testReadWithWriter() {
//    Arrays.stream((Object[]) repository.getBoardWithWriter(2L)).forEach(log::info);
    BoardWithWriterDTO dto = repository.getBoardWithWriter(2L);
    log.info(dto.getBoard());
    log.info(dto.getMember());
  }

  @Test
  public void testReadWithWriter2() {
//    Arrays.stream((Object[]) repository.getBoardWithWriter(2L)).forEach(log::info);
    BoardWithWriterDTORecord dto = repository.getBoardWithWriter2(2L);
    log.info(dto.board());
    log.info(dto.member());
  }

  @Test
  public void testReadWithWriter3() {
//    Arrays.stream((Object[]) repository.getBoardWithWriter(2L)).forEach(log::info);
    BoardWithWriterDTOClass dto = repository.getBoardWithWriter3(2L);
    log.info(dto.getBoard());
    log.info(dto.getMember());
  }

  @Test
  public void getBoardWithReply() {
    List<Object[]> list = repository.getBoardWithReply(2L);
//    list.stream().forEach(o -> log.info((Arrays.toString(o))));
    list.stream().forEach(o -> log.info("{}, {}", o[0], o[1]));
//    BoardWithWriterDTOClass dto = repository.getBoardWithWriter3(2L);
//    log.info(dto.getBoard());
//    log.info(dto.getMember());
  }

  @Test
  public void getboardWithReply2() {
    List<BoardWithReplyDTORecord> list = repository.getBoardWithReply2(2L);
//    list.stream().forEach(o -> log.info((Arrays.toString(o))));
    list.stream().forEach(log::info);
  }

  @Test
  public void testWithReplyCount() {
    Page<Object[]> page = repository.getBoardWithReplyCount(PageRequest.of(1, 10, Sort.by(Sort.Direction.DESC,"bno" )));
    page.stream().forEach(p -> log.info("{}, {}, {}", p[0], p[1], p[2]));

  }

  @Test
  public void getBoardWithReplyCount2() {
    Page<BoardWithReplyCount> page = repository.getBoardWithReplyCount2(PageRequest.of(1, 10, Sort.by(Sort.Direction.DESC,"bno" )));
    page.stream().forEach(log::info);
  }

  @Test
  public void testSearch() {
    repository.search();
  }

  @Test
  public void testSearchPage() {
    Page<BoardWithReplyCount> bwrc = repository.searchPage("tcw", "title", PageRequest.of(3, 5,
            Sort.by(Sort.Direction.DESC, "bno").and(Sort.by(Sort.Direction.ASC, "title"))));

    log.info(bwrc.getTotalPages());
    log.info(bwrc.getTotalElements());
    bwrc.getContent().forEach(log::info);
  }

}
