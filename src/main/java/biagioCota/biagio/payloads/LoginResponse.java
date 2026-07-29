package biagioCota.biagio.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private String type;
    private String email;
    private String role;
}
