package com.veiuper.todolist.service;

import com.veiuper.todolist.model.ConfirmationToken;
import com.veiuper.todolist.repository.ConfirmationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {
    private final ConfirmationTokenRepository confirmationTokenRepository;

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
