package com.kiylab.board.repository.search;

import com.kiylab.board.domain.entity.Board;
import com.kiylab.board.domain.entity.QBoard;
import com.kiylab.board.domain.entity.QMember;
import com.kiylab.board.domain.entity.QReply;
import com.kiylab.board.domain.projection.dto.BoardWithReplyCount;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

@Log4j2
public class SearchBoardRepositoryImpl extends QuerydslRepositorySupport implements  SearchBoardRepository{
  public SearchBoardRepositoryImpl() {
    super(Board.class);
  }

  @Override
  public Board search() {
    log.info("----------------------------------------------------");
    QBoard qBoard = QBoard.board;
    JPQLQuery<Board> jpqlQuery = from(qBoard);

    jpqlQuery.where(qBoard.bno.gt(0L)); // 0보다 큰
    log.info(jpqlQuery);

    // member 조인
    jpqlQuery
            .leftJoin(QMember.member).on(qBoard.writer.eq(QMember.member))
            .leftJoin(QReply.reply).on(QReply.reply.board.eq(qBoard));

    JPQLQuery<Tuple> tuple = jpqlQuery.select(qBoard, QMember.member, QReply.reply.count()).groupBy(qBoard).limit(10);
    List<Tuple> list = tuple.fetch();

    log.info(list);
    log.info("----------------------------------------------------");
    return null;
  }

  @Override
  public Page<BoardWithReplyCount> searchPage(String type, String keyword, Pageable pageable) {
    QBoard board = QBoard.board;
    QReply reply = QReply.reply;
    QMember member = QMember.member;


    // 조인
    JPQLQuery<Board> jpqlQuery = from(board);
    jpqlQuery
            .leftJoin(member).on(board.writer.eq(member))
            .leftJoin(reply).on(reply.board.eq(board));

    JPQLQuery<Tuple> tuple = jpqlQuery.groupBy(board).select(board, member, reply.count());

    // 검색
    BooleanBuilder booleanBuilder = new BooleanBuilder();
    BooleanExpression expression = board.bno.gt(0L);  // 이건 해야함. 검색을 하던 안하던

    booleanBuilder.and(expression);

    if (!(type == null || type.trim().isEmpty())) {  // 그리고 검색 타입이 있는경우에는 이 밑에 작업들을 해라
      BooleanBuilder builder = new BooleanBuilder();
      if (type.contains("t")) {
        builder.or(board.title.contains(keyword));
      }
      if (type.contains("c")) {
        builder.or(board.content.contains(keyword));
      }
      if (type.contains("w")) {
        builder.or(member.name.contains(keyword));
      }
      booleanBuilder.and(builder);
    }
    tuple.where(booleanBuilder);
    getOrder(Board.class, pageable.getSort()).forEach(tuple::orderBy);

    // 페이지 적용
    tuple.limit(pageable.getPageSize());
    tuple.offset(pageable.getOffset());

    //DTO Projection 변환
    JPQLQuery<BoardWithReplyCount> query = tuple.select(Projections.constructor(BoardWithReplyCount.class, board, member, reply.count()));

    // page 타입 반환
    return new PageImpl<>(query.fetch(),pageable, tuple.fetchCount());


//    List<?> list = tuple.fetch(); // 리스트 뽑아오려고 fetch
//    list.forEach(log::info);
  }
}
