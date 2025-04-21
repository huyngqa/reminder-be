package com.reminder.repository;

import com.reminder.model.RefreshToken;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RefreshTokenRepository {
	
    Optional<RefreshToken> selectByToken(String token);

    int insertToken(RefreshToken refreshToken);
    
    int updateToken(RefreshToken refreshToken);
    
    int deleteByUserId(int userId);
    
    int deleteById(int id);
}
