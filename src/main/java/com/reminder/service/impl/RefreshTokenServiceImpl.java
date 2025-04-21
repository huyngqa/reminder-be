package com.reminder.service.impl;

import com.reminder.exception.TokenRefreshException;
import com.reminder.model.RefreshToken;
import com.reminder.repository.RefreshTokenRepository;
import com.reminder.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {
    @Value("${reminder.app.jwtRefreshExpirationMs}")
    private Long refreshTokenDurationMs;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Override
    public Optional<RefreshToken> selectByToken(String token) {
        return refreshTokenRepository.selectByToken(token);
    }

    @Override
    public RefreshToken insertToken(Integer userId) {
        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setUserId(userId);
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setToken(UUID.randomUUID().toString());

        int result = refreshTokenRepository.insertToken(refreshToken);
        if(result > 0) {
        	return refreshToken;
        }
        return null;
    }

    @Override
    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.deleteById(token.getId());
            throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
        }

        return token;
    }

    @Override
    public int deleteByUserId(Integer userId) {
        return refreshTokenRepository.deleteByUserId(userId);
    }
}
