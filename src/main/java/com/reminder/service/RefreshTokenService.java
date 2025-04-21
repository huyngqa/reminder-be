package com.reminder.service;

import com.reminder.model.RefreshToken;

import java.util.Optional;

public interface RefreshTokenService {
    Optional<RefreshToken> selectByToken(String token);

    RefreshToken insertToken(Integer userId);

    RefreshToken verifyExpiration(RefreshToken token);

    int deleteByUserId(Integer userId);
}
