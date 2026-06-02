package spring_boot.session16ex05.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring_boot.session16ex05.model.dto.RegisterRequest;
import spring_boot.session16ex05.model.entity.User;
import spring_boot.session16ex05.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Username đã tồn tại!");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setFullName(request.getFullName());
        user.setRole("USER");
        user.setEnabled(true);

        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);
    }
}
