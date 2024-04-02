package com.qnit18.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginRequest {

    @Size(min = 12, message = "EMAIL_INVALID")
    String email;
    @Size(min = 6, message = "INVALID_PASSWORD")
    String password;
}
