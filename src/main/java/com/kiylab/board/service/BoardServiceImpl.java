package com.kiylab.board.service;

import com.kiylab.board.domain.dto.BoardDTO;
import com.kiylab.board.domain.dto.PageRequestDTO;
import com.kiylab.board.domain.dto.PageResponseDTO;
import com.kiylab.board.domain.entity.Board;
import com.kiylab.board.domain.projection.dto.BoardWithReplyCount;
import com.kiylab.board.repository.BoardRepository;
import com.kiylab.board.repository.ReplyRepository;
import lombok.Data;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Data
public class BoardServiceImpl implements BoardService{
  private final BoardRepository boardRepository;
  private final ReplyRepository replyRepository;

  @Override
  public Long register(BoardDTO boardDTO) {
    return boardRepository.save(toEntity(boardDTO)).getBno();
  }

  @Override
  public PageResponseDTO<BoardDTO, BoardWithReplyCount> getList(PageRequestDTO pageRequestDTO) {
    return new PageResponseDTO<>(
            boardRepository.searchPage(pageRequestDTO.getType(), pageRequestDTO.getKeyword(), pageRequestDTO.getPageable(Sort.by(Sort.Direction.DESC, "bno")))
            , this::projectionToDTO);
  }

  @Override
  public BoardDTO get(Long bno) {
    return projectionToDTO(boardRepository.getBoardByBno(bno));
  }

  @Transactional
  @Override
  public void remove(Long bno) {
    replyRepository.deleteByBoard_Bno(bno);
    boardRepository.deleteById(bno);
  }

  @Override
  public void modify(BoardDTO boardDTO) {
    Board board = boardRepository.findById(boardDTO.getBno()).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
    board.changeTitle(boardDTO.getTitle()); //1 transaction
    board.changeContent(boardDTO.getContent()); //1 transaction
    boardRepository.save(board);
  }
}
