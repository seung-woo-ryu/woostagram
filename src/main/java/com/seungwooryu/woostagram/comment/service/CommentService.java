package com.seungwooryu.woostagram.comment.service;

import com.seungwooryu.woostagram.comment.domain.Comment;
import com.seungwooryu.woostagram.comment.repository.CommentRepository;
import com.seungwooryu.woostagram.post.domain.Post;
import com.seungwooryu.woostagram.post.exception.PostNotFoundException;
import com.seungwooryu.woostagram.user.domain.User;
import com.seungwooryu.woostagram.user.exception.AuthenticationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    @Transactional
    public Boolean delete(Long commentId, User sessionUser) {
        Comment comment = findById(commentId);
        validateUser(comment, sessionUser);

        commentRepository.delete(comment);
        return true;
    }

    @Transactional
    public void create(User sessionUser, Post postWithComment, String content) {
        Comment newComment = Comment.of(content, sessionUser, postWithComment);
        Comment savedComment = commentRepository.save(newComment);
    }

    private void validateUser(Comment comment, User user) {
        if (!comment.isAuthor(user)) {
            throw new AuthenticationException();
        }
    }

    private Comment findById(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(PostNotFoundException::new);
    }
}
