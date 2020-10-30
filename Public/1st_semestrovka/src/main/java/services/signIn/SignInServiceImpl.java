package services.signIn;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import dto.SignInForm;
import models.User;
import repositories.UsersRepository;

import javax.servlet.http.HttpSession;
import java.util.Optional;

public class SignInServiceImpl implements SignInService {

    private UsersRepository usersRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public SignInServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }


    @Override
    public boolean signIn(SignInForm form) {

        Optional<User> user = usersRepository.findUserByEmail(form.getEmail());

        if(user.isPresent()) {
            if(passwordEncoder.matches(form.getPassword(), user.get().getHashPassword()))
                return true;
        }
        return false;

    }
}
