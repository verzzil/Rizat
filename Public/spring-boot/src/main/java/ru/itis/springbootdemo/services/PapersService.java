package ru.itis.springbootdemo.services;

import ru.itis.springbootdemo.dto.PapersPage;

public interface PapersService {
    PapersPage search(Integer size, Integer page, String query, String sort, String direction);
}
