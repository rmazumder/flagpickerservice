package com.example.hcl.flagpicker.repository;

import com.example.hcl.flagpicker.model.User;

public interface UserRepository {
    User findByUsername(String username);
}
