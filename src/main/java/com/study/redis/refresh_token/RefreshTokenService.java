package com.study.redis.refresh_token;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public void saveTokenInfo(RefreshTokenDto.RefreshTokenRequestDto requestDto){
        refreshTokenRepository.save(new RefreshToken(requestDto.getId(), requestDto.getAccessToken(), requestDto.getRefreshToken()));
    }

    @Transactional(readOnly = true)
    public RefreshTokenDto.RefreshTokenResponseDto getTokenInfo(String accessToken){
        RefreshToken refreshToken = refreshTokenRepository.findByAccessToken(accessToken)
                .orElseThrow(() -> new RuntimeException("없다!"));

        return RefreshTokenDto.RefreshTokenResponseDto.from(refreshToken);
    }

    @Transactional(readOnly = true)
    public RefreshTokenDto.RefreshTokenResponseDto getTokenInfoById(String id){
        RefreshToken refreshToken = refreshTokenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("없다!"));

        return RefreshTokenDto.RefreshTokenResponseDto.from(refreshToken);
    }

    @Transactional
    public void removeRefreshToken(String accessToken){
        refreshTokenRepository.findByAccessToken(accessToken)
                .ifPresent(refreshTokenRepository::delete);
    }
}
