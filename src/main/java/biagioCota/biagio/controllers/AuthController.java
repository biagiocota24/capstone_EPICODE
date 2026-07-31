package biagioCota.biagio.controllers;

import biagioCota.biagio.entities.User;
import biagioCota.biagio.entities.userSubclasses.Admin;
import biagioCota.biagio.entities.userSubclasses.BusinessOwner;
import biagioCota.biagio.entities.userSubclasses.Visitor;
import biagioCota.biagio.payloads.LoginPayload;
import biagioCota.biagio.payloads.LoginResponse;
import biagioCota.biagio.payloads.users.AdminPayload;
import biagioCota.biagio.payloads.users.BusinessOwnerPayload;
import biagioCota.biagio.payloads.users.UserPayloadResponse;
import biagioCota.biagio.payloads.users.VisitorPayload;
import biagioCota.biagio.security.AuthService;
import biagioCota.biagio.services.AdminService;
import biagioCota.biagio.services.BusinessOwnerService;
import biagioCota.biagio.services.VisitorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {


    private final BusinessOwnerService businessOwnerService;
    private final VisitorService visitorService;
    private final AdminService adminService;
    private final AuthService authService;

    public AuthController(BusinessOwnerService businessOwnerService, VisitorService visitorService, AdminService adminService, AuthService authService) {
        this.businessOwnerService = businessOwnerService;
        this.visitorService = visitorService;
        this.adminService = adminService;
        this.authService = authService;
    }

    @PostMapping("/register/owner")
    @ResponseStatus(HttpStatus.CREATED)
    public UserPayloadResponse registerOwner(@RequestBody @Valid BusinessOwnerPayload payload) {
        BusinessOwner saved = businessOwnerService.save(payload);
        return UserPayloadResponse.fromEntity(saved);
    }

    @PostMapping("/register/visitor")
    @ResponseStatus(HttpStatus.CREATED)
    public UserPayloadResponse registerVisitor(@RequestBody @Valid VisitorPayload payload) {
        Visitor saved = visitorService.save(payload);
        return UserPayloadResponse.fromEntity(saved);
    }

    @PostMapping("/register/admin")
    @ResponseStatus(HttpStatus.CREATED)
    public UserPayloadResponse registerAdmin(@RequestBody @Valid AdminPayload payload) {
        Admin saved = adminService.save(payload);
        return UserPayloadResponse.fromEntity(saved);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginPayload payload) {
        User user = authService.authenticate(payload.getEmail(), payload.getPassword());
        String token = jwtService.generateToken(user);

        // ← Ritorna user + token INSIEME
        return ResponseEntity.ok(Map.of(
                "user", UserPayloadResponse.fromEntity(user),  // ← User
                "token", token,                                 // ← Token
                "message", "Login riuscito"
        ));
    }˙
}
