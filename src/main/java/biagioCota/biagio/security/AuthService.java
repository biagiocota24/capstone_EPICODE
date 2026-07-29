package biagioCota.biagio.security;

import biagioCota.biagio.entities.User;
import biagioCota.biagio.entities.userSubclasses.Admin;
import biagioCota.biagio.entities.userSubclasses.BusinessOwner;
import biagioCota.biagio.exceptions.DuplicateEmailException;
import biagioCota.biagio.payloads.LoginPayload;
import biagioCota.biagio.payloads.LoginResponse;
import biagioCota.biagio.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class
AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;

    public AuthService(AuthenticationManager authenticationManager,
                       UserRepository userRepository,
                       JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
    }

    public LoginResponse login(LoginPayload payload) {
        // Delega la verifica delle credenziali a Spring Security (usa BCrypt internamente)
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(payload.getEmail(), payload.getPassword())
        );

        User user = userRepository.findByEmail(payload.getEmail())
                .orElseThrow(() -> new DuplicateEmailException("Utente non trovato"));

        String role = determineRole(user);
        String token = jwtUtils.generateToken(user.getEmail(), role);

        return new LoginResponse(token, "Bearer", user.getEmail(), role);
    }

    private String determineRole(User user) {
        if (user instanceof Admin) return "ADMIN";
        if (user instanceof BusinessOwner) return "BUSINESS_OWNER";
        return "VISITOR";
    }
}
