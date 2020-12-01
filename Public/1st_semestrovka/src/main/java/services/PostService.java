package services;

import dto.PostDto;
import models.Post;

import java.util.List;

public interface PostService {
    void savePost(PostDto post, Long userId);
    List<Post> getAllPosts();
    List<Post> searchForPosts(String query);
}
