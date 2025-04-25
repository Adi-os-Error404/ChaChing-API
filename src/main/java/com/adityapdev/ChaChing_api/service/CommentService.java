package com.adityapdev.ChaChing_api.service;

import com.adityapdev.ChaChing_api.dto.comment.AddCommentDto;
import com.adityapdev.ChaChing_api.dto.comment.CommentDetailDto;
import com.adityapdev.ChaChing_api.dto.comment.EditCommentDto;
import com.adityapdev.ChaChing_api.entity.Coin;
import com.adityapdev.ChaChing_api.entity.Comment;
import com.adityapdev.ChaChing_api.entity.User;
import com.adityapdev.ChaChing_api.exception.ResourceNotFoundException;
import com.adityapdev.ChaChing_api.exception.UnauthorizedException;
import com.adityapdev.ChaChing_api.mapper.CoinMapper;
import com.adityapdev.ChaChing_api.mapper.CommentMapper;
import com.adityapdev.ChaChing_api.repository.CoinRepository;
import com.adityapdev.ChaChing_api.repository.CommentRepository;
import com.adityapdev.ChaChing_api.repository.UserRepository;
import com.adityapdev.ChaChing_api.service.interfaces.ICoinService;
import com.adityapdev.ChaChing_api.service.interfaces.ICommentService;
import com.adityapdev.ChaChing_api.service.interfaces.IUserService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CommentService implements ICommentService {

    private final CommentRepository commentRepository;
    private final ICoinService coinService;
    private final IUserService userService;


    public CommentService(CommentRepository commentRepository, ICoinService coinService, IUserService userService) {
        this.commentRepository = commentRepository;
        this.coinService = coinService;
        this.userService = userService;
    }

    @Override
    public List<CommentDetailDto> getComments(String coinId) {
        Coin coin = coinService.findCoinById(coinId);
        List<Comment> comments = coin.getComments();
        return comments.stream().map(CommentMapper::mapToCommentDto).toList();
    }

    @Override
    public CommentDetailDto addComment(String coinId, AddCommentDto addCommentDto) {
        Coin coin = coinService.findCoinById(coinId);
        User user = userService.getCurrentUser();
        Comment comment = CommentMapper.mapToComment(addCommentDto, coin, user);
        Comment savedComment = commentRepository.save(comment);
        return CommentMapper.mapToCommentDto(savedComment);
    }

    @Override
    public CommentDetailDto updateComment(EditCommentDto editCommentDto) {
        Long commentId = editCommentDto.getId();
        Comment comment = findCommentById(commentId);
        if (isAuthor(comment)) {
            comment.setTitle(editCommentDto.getTitle());
            comment.setContent(editCommentDto.getContent());
            comment.setEditedOn(Instant.now());
            Comment savedComment =  commentRepository.save(comment);
            return CommentMapper.mapToCommentDto(savedComment);
        }
        throw new UnauthorizedException("Comment cannot be edited.");
    }

    @Override
    public void deleteComment(Long commentId) {
        Comment comment = findCommentById(commentId);
        if (isAuthor(comment)) {
            commentRepository.deleteById(commentId);
        }
        else {
            throw new UnauthorizedException("Comment cannot be deleted.");
        }
    }

    private Comment findCommentById(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Comment \"%d\" does not exist.", commentId)));
    }

    private boolean isAuthor(Comment comment) {
        User author = userService.getCurrentUser();
        return Objects.equals(author.getUsername(), comment.getUser().getUsername());
    }


}
