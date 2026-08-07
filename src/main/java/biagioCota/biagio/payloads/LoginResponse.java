package biagioCota.biagio.payloads;

import biagioCota.biagio.payloads.users.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private String type;
    private String role;
    private UserResponse user;
}
