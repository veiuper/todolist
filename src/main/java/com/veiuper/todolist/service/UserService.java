package com.veiuper.todolist.service;

import com.veiuper.todolist.model.ConfirmationToken;
import com.veiuper.todolist.model.User;
import com.veiuper.todolist.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailSenderService emailSenderService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @PersistenceContext
    private final EntityManager entityManager;

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
    public User findById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(new User());
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public boolean save(User user) {
        Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());
        if (optionalUser.isPresent()) {
            return false;
        }
        final String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        userRepository.save(user);
        return true;
    }

    public boolean delete(Long id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<User> userGtList(Long id) {
        return entityManager
                .createQuery("SELECT u FROM usr u WHERE u.id > :paramId", User.class)
                .setParameter("paramId", id)
                .getResultList();
    }

    public boolean registrationUser(User user) {
        if (!save(user)) {
            return false;
        }
        final ConfirmationToken confirmationToken = new ConfirmationToken(user);
        confirmationTokenService.save(confirmationToken);
        sendConfirmationMail(user.getEmail(), confirmationToken.getConfirmationToken());
        return true;
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
                        "http://localhost:8080/registration/confirm?token=" + token
        );
        emailSenderService.sendEmail(message);
    }
}
