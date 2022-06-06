package com.veiuper.todolist.service;

import com.veiuper.todolist.model.entity.ConfirmationToken;
import com.veiuper.todolist.model.entity.User;
import com.veiuper.todolist.repository.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
@Validated
public class UserService implements UserDetailsService {
    UserRepository userRepository;
    ConfirmationTokenService confirmationTokenService;
    EmailSenderService emailSenderService;
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public UserDetails loadUserByUsername(@Email String email) throws UsernameNotFoundException {
        final Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException(
                    MessageFormat.format("Пользователь с email {0} не найден.", email)
            );
        }
        return optionalUser.get();
    }

    public Optional<@Valid User> findById(@Min(0) Long id) {
        return userRepository.findById(id);
    }

    public Optional<@Valid User> findByEmail(@Email String email) {
        return userRepository.findByEmail(email);
    }

    public List<@Valid User> findAll() {
        return userRepository.findAll();
    }

    public boolean save(@Valid User user) {
        Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());
        if (optionalUser.isPresent()) {
            return false;
        }
        final String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        userRepository.save(user);
        return true;
    }

    public boolean delete(@Min(0) Long id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean switchLocked(@Min(0) Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setLocked(!user.getLocked());
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public boolean switchEnabled(@Min(0) Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setEnabled(!user.getEnabled());
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public List<@Valid User> userGtList(@Min(0) Long id) {
        return entityManager
                .createQuery("SELECT u FROM User u WHERE u.id > :paramId", User.class)
                .setParameter("paramId", id)
                .getResultList();
    }

    public boolean registrationUser(@Valid User user) {
        if (!save(user)) {
            return false;
        }
        final ConfirmationToken confirmationToken = new ConfirmationToken(user);
        confirmationTokenService.save(confirmationToken);
        sendConfirmationMail(user.getEmail(), confirmationToken.getConfirmationToken());
        return true;
    }

    public void confirmUser(@Valid ConfirmationToken confirmationToken) {
        final User user = confirmationToken.getUser();
        user.setEnabled(true);
        userRepository.save(user);
        confirmationTokenService.delete(confirmationToken);
    }

    public void sendConfirmationMail(@Email String userMail, @NotBlank String token) {
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
