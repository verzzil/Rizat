package ru.itis.springbootdemo.services;

import ru.itis.springbootdemo.dto.UserDto;
import ru.itis.springbootdemo.models.User;

import java.util.List;
import java.util.stream.Collectors;


public interface UsersService {
    List<UserDto> getAllUsers();

    UserDto getUserById(Long userId);

    List<UserDto> findAll();
}
