package com.study.redis.refresh_token;

import lombok.*;

public class RefreshTokenDto {

    @Getter
    @NoArgsConstructor
    public static class RefreshTokenRequestDto{
        private String id;
        private String accessToken;
        private String refreshToken;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class RefreshTokenResponseDto{
        private String id;
        private String accessToken;
        private String refreshToken;

        public static RefreshTokenResponseDto from(RefreshToken refreshToken){
            return RefreshTokenResponseDto.builder()
                    .id(refreshToken.getId())
                    .accessToken(refreshToken.getAccessToken())
                    .refreshToken(refreshToken.getRefreshToken())
                    .build();
        }
    }
}
