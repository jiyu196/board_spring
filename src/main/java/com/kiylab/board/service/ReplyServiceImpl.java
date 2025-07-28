package com.kiylab.board.service;

import com.kiylab.board.domain.dto.ReplyDTO;
import com.kiylab.board.domain.mapper.ReplyMapper;
import com.kiylab.board.repository.ReplyRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class ReplyServiceImpl implements ReplyService{
  private final ReplyMapper replyMapper;
  private final ReplyRepository replyRepository;

  @Override
  public Long register(ReplyDTO dto) {
    return replyRepository.save(replyMapper.toEntity(dto)).getRno();
  }

  @Override
  public List<ReplyDTO> getlist(Long bno) {
    return replyRepository.findByBoard_BnoOrderByRno(bno).stream().map(replyMapper::toDto).toList();
  }

  @Override
  public void modify(ReplyDTO dto) {
    replyRepository.save(replyMapper.toEntity(dto));
  }


  @Override
  public void remove(Long rno) {
    replyRepository.deleteById(rno);
  }

  @Override
  public ReplyDTO get(Long rno) {
    return replyMapper.toDto(replyRepository.findById(rno).orElseThrow(() -> new IllegalArgumentException("댓글 없음")));
//    return  replyRepository.findById(rno).map(replyMapper::toDto).orElse(null);
  }
}
