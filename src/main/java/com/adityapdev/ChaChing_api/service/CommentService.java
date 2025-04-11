package com.adityapdev.ChaChing_api.service;

import com.adityapdev.ChaChing_api.dto.comment.AddCommentDto;
import com.adityapdev.ChaChing_api.dto.comment.CommentDetailDto;
import com.adityapdev.ChaChing_api.dto.comment.EditCommentDto;
import com.adityapdev.ChaChing_api.entity.Coin;
import com.adityapdev.ChaChing_api.entity.Comment;
import com.adityapdev.ChaChing_api.exception.ResourceNotFoundException;
import com.adityapdev.ChaChing_api.mapper.CommentMapper;
import com.adityapdev.ChaChing_api.repository.CoinRepository;
import com.adityapdev.ChaChing_api.repository.CommentRepository;
import com.adityapdev.ChaChing_api.service.interfaces.ICoinService;
import com.adityapdev.ChaChing_api.service.interfaces.ICommentService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService implements ICommentService {

    private final CommentRepository commentRepository;
    private final CoinRepository coinRepository;
    private final ICoinService coinService;

    public CommentService(CommentRepository commentRepository, CoinRepository coinRepository) {
        this.commentRepository = commentRepository;
        this.coinRepository = coinRepository;
        this.coinService = new CoinService(coinRepository);
    }

    @Override
    public CommentDetailDto addComment(String coinId, AddCommentDto addCommentDto) {
        Coin coin = coinService.findCoinById(coinId);
        Comment comment = CommentMapper.mapToComment(addCommentDto, coin);
        Comment savedComment = commentRepository.save(comment);
        return CommentMapper.mapToCommentDto(savedComment);
    }

    @Override
    public CommentDetailDto updateComment(EditCommentDto editCommentDto) {
        Long commentId = editCommentDto.getId();
        Comment comment = findCommentById(commentId);
        comment.setTitle(editCommentDto.getTitle());
        comment.setContent(editCommentDto.getContent());
        comment.setEditedOn(Instant.now());
        Comment savedComment =  commentRepository.save(comment);
        return CommentMapper.mapToCommentDto(savedComment);
    }

    @Override
    public void deleteComment(Long commentId) {
        findCommentById(commentId);
        commentRepository.deleteById(commentId);
    }

    private Comment findCommentById(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Comment \"%d\" does not exist.", commentId)));
    }


}
