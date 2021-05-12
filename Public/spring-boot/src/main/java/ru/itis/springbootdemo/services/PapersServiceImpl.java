package ru.itis.springbootdemo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import ru.itis.springbootdemo.dto.PapersPage;
import ru.itis.springbootdemo.models.Paper;
import ru.itis.springbootdemo.repositories.PapersRepository;

import static ru.itis.springbootdemo.dto.PaperDto.from;

@Service
public class PapersServiceImpl implements PapersService {

    @Autowired
    private PapersRepository papersRepository;

    @Override
    public PapersPage search(Integer size, Integer page, String query, String sortParameter, String directionParameter) {
        Direction direction = Direction.ASC;
        Sort sort = Sort.by(direction, "id");

        if (directionParameter != null) {
            direction = Direction.fromString(directionParameter);
        }

        if (sortParameter != null) {
            sort = Sort.by(direction, sortParameter);
        }

        if (query == null) {
            query = "empty";
        }

        PageRequest pageRequest = PageRequest.of(page, size, sort);
        Page<Paper> papersPage = papersRepository.search(query, pageRequest);

        return PapersPage.builder()
                .pagesCount(papersPage.getTotalPages())
                .papers(from(papersPage.getContent()))
                .build();
    }
}
