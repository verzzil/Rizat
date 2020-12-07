package ru.itis.services;

import ru.itis.dto.PostDto;
import ru.itis.models.Post;

import java.util.List;

public interface PostService {
    void savePost(PostDto post, Long userId);
    List<Post> getAllPosts();
    List<Post> searchForPosts(String query);
}
