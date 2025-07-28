package com.kiylab.board.repository.search;

import com.kiylab.board.domain.entity.Board;
import com.kiylab.board.domain.projection.dto.BoardWithReplyCount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchBoardRepository extends SearchSupport<Board>{
  Board search();

  Page<BoardWithReplyCount> searchPage(String type, String keyword, Pageable pageable);
}
