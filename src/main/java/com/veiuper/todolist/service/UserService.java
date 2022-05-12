package com.veiuper.todolist.service;

import com.veiuper.todolist.dao.UserRepository;
import com.veiuper.todolist.model.ConfirmationToken;
import com.veiuper.todolist.model.User;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailSenderService emailSenderService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException(
                    MessageFormat.format("Пользователь с email {0} не найден.", email)
            );
        }
        return optionalUser.get();
    }

    public void signUpUser(User user) {
        final String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        final User createdUser = userRepository.save(user);
        final ConfirmationToken confirmationToken = new ConfirmationToken(user);
        confirmationTokenService.save(confirmationToken);
    }

    public void confirmUser(ConfirmationToken confirmationToken) {
        final User user = confirmationToken.getUser();
        user.setEnabled(true);
        userRepository.save(user);
        confirmationTokenService.delete(confirmationToken);
    }

    public void sendConfirmationMail(String userMail, String token) {
        final SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(userMail);
        message.setSubject("Mail Confirmation Link");
        message.setFrom("MAIL");
        message.setText(
                "Thank you for registering." + System.lineSeparator() +
                        "Please click on the below link to activate your account." + System.lineSeparator() +
                        "http://localhost:8080/sign-up/confirm?token=" + token
        );
        emailSenderService.sendEmail(message);
    }
}
