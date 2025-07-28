package com.kiylab.board.domain.mapper;

import com.kiylab.board.domain.dto.ReplyDTO;
import com.kiylab.board.domain.entity.Reply;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReplyMapper {
  @Mapping(source = "board.bno", target = "bno")
  ReplyDTO toDto(Reply reply);  // boardWithReplyCountDTO가 BoardDTO가되야하느

//  @Mapping(source = "bno", target = "board.bno")
  @InheritInverseConfiguration  // 이 어노테이션 바로 위에있는걸 역순으로 한다는 뜻임. 반전을 한다는
  Reply toEntity(ReplyDTO dto);

}
