package com.veiuper.todolist.service;

import com.veiuper.todolist.model.entity.ConfirmationToken;
import com.veiuper.todolist.repository.ConfirmationTokenRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class ConfirmationTokenService {
    ConfirmationTokenRepository confirmationTokenRepository;

    public void save(ConfirmationToken confirmationToken) {
        confirmationTokenRepository.save(confirmationToken);
    }

    public void delete(ConfirmationToken confirmationToken) {
        confirmationTokenRepository.delete(confirmationToken);
    }

    public Optional<ConfirmationToken> findConfirmationTokenByConfirmationToken(String confirmationToken) {
        return confirmationTokenRepository.findConfirmationTokenByConfirmationToken(confirmationToken);
    }
}
