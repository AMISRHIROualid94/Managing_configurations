package com.sbip.mc.models;

import com.sbip.mc.CustomPwdValidator.Password;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class User {

    private String userName;
    @Password
    private String password;
}
