package ru.itis.services;

import ru.itis.dto.PostDto;
import ru.itis.models.Post;
import ru.itis.repositories.PostsCrudRepositoryImpl;
import ru.itis.repositories.UsersRepository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class PostServiceImpl implements PostService {

    private PostsCrudRepositoryImpl postsRepository;
    private UsersRepository usersRepository;

    public PostServiceImpl(PostsCrudRepositoryImpl postsRepository, UsersRepository usersRepository) {
        this.postsRepository = postsRepository;
        this.usersRepository = usersRepository;
    }

    @Override
    public void savePost(PostDto postDto, Long userId) {
        Date current = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        postsRepository.save(Post.builder().userId(userId)
                .name(postDto.getName())
                .text(postDto.getText())
                .creationDate(dateFormat.format(current))
        .build());
    }

    @Override
    public List<Post> getAllPosts() {
        List<Post> posts = postsRepository.findAll();
        posts.stream().forEach(x -> x.setUserName(usersRepository.findById(x.getUserId()).getFirstName() + " "
        + usersRepository.findById(x.getUserId()).getLastName()));
        return posts;
    }

    @Override
    public List<Post> searchForPosts(String query) {
        List<Post> posts = postsRepository.findAll();
        posts = posts.stream().filter(x -> x.getName()
                                            .toLowerCase()
                                            .contains(query.toLowerCase()))
                .collect(Collectors.toList());
        posts.stream().forEach(x -> x.setUserName(usersRepository.findById(x.getUserId()).getFirstName() + " "
                + usersRepository.findById(x.getUserId()).getLastName()));
        return posts;
    }
}
