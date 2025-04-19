package com.adityapdev.ChaChing_api.service.interfaces;

import com.adityapdev.ChaChing_api.dto.comment.AddCommentDto;
import com.adityapdev.ChaChing_api.dto.comment.CommentDetailDto;
import com.adityapdev.ChaChing_api.dto.comment.EditCommentDto;

import java.util.List;

public interface ICommentService {
    List<CommentDetailDto> getComments(String coinId);

    CommentDetailDto addComment(String coinId, AddCommentDto addCommentDto);
    CommentDetailDto updateComment(EditCommentDto editCommentDto);
    void deleteComment(Long commentId);

}
