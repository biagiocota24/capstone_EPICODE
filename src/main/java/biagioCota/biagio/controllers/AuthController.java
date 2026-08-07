package biagioCota.biagio.controllers;

import biagioCota.biagio.payloads.LoginPayload;
import biagioCota.biagio.payloads.LoginResponse;
import biagioCota.biagio.payloads.users.AdminPayload;
import biagioCota.biagio.payloads.users.BusinessOwnerPayload;
import biagioCota.biagio.payloads.users.UserResponse;
import biagioCota.biagio.payloads.users.VisitorPayload;
import biagioCota.biagio.security.AuthService;
import biagioCota.biagio.services.AdminService;
import biagioCota.biagio.services.BusinessOwnerService;
import biagioCota.biagio.services.VisitorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final BusinessOwnerService businessOwnerService;
    private final VisitorService visitorService;
    private final AdminService adminService;
    private final AuthService authService;

    public AuthController(BusinessOwnerService businessOwnerService, VisitorService visitorService,
                          AdminService adminService, AuthService authService) {
        this.businessOwnerService = businessOwnerService;
        this.visitorService = visitorService;
        this.adminService = adminService;
        this.authService = authService;
    }

    @PostMapping("/register/visitor")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse registerVisitor(@RequestBody @Valid VisitorPayload payload) {
        return UserResponse.fromEntity(visitorService.save(payload));
    }

    @PostMapping("/register/owner")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse registerOwner(@RequestBody @Valid BusinessOwnerPayload payload) {
        return UserResponse.fromEntity(businessOwnerService.save(payload));
    }

    @PostMapping("/register/admin")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse registerAdmin(@RequestBody @Valid AdminPayload payload) {
        return UserResponse.fromEntity(adminService.save(payload));
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse login(@RequestBody @Valid LoginPayload payload) {
        return authService.login(payload);
    }
}
