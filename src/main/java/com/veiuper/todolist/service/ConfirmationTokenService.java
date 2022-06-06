package com.veiuper.todolist.service;

import com.veiuper.todolist.model.entity.ConfirmationToken;
import com.veiuper.todolist.repository.ConfirmationTokenRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
@Validated
public class ConfirmationTokenService {
    ConfirmationTokenRepository confirmationTokenRepository;

    public void save(@Valid ConfirmationToken confirmationToken) {
        confirmationTokenRepository.save(confirmationToken);
    }

    public void delete(@Valid ConfirmationToken confirmationToken) {
        confirmationTokenRepository.delete(confirmationToken);
    }

    public Optional<@Valid ConfirmationToken> findConfirmationTokenByConfirmationToken(@NotBlank String token) {
        return confirmationTokenRepository.findConfirmationTokenByConfirmationToken(token);
    }
}
