package com.springapi.donatecharity.service.auth;

import com.springapi.donatecharity.models.User;

public interface AuthService {
    String getJwtToken(String username, String password);

}
