package com.test.jump.user;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class UserCreateForm {
    @Size(min = 3, max = 25)
    @NotEmpty(message = "ID를 입력하십시오.")
    private String username;

    @NotEmpty(message = "비밀번호를 입력하십시오.")
    private String password;

    @NotEmpty(message = "비밀번호를 확인 하십시오.")
    private String password2;

    @Email
    private String email;
}
