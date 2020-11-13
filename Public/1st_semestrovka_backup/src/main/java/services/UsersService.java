package services;
import dto.UserDto;

import java.util.Optional;

public interface UsersService {
    Optional<UserDto> getUserByCookie(String value);
}