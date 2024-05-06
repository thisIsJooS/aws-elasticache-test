package com.study.redis.refresh_token;

import com.study.redis.refresh_token.RefreshTokenDto;
import com.study.redis.refresh_token.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TokenController {
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/redis")
    public ResponseEntity<?> saveToken(@RequestBody RefreshTokenDto.RefreshTokenRequestDto requestDto){
        refreshTokenService.saveTokenInfo(requestDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/redis-token")
    public ResponseEntity<?> getToken(@RequestParam("token") String token){
        RefreshTokenDto.RefreshTokenResponseDto tokenInfo = refreshTokenService.getTokenInfo(token);

        return new ResponseEntity<>(tokenInfo, null, HttpStatus.OK);
    }

    @GetMapping("/redis-id")
    public ResponseEntity<?> getTokenById(@RequestParam("id") String id){
        RefreshTokenDto.RefreshTokenResponseDto tokenInfoById = refreshTokenService.getTokenInfoById(id);

        return new ResponseEntity<>(tokenInfoById, null, HttpStatus.OK);
    }

    @DeleteMapping("/redis")
    public ResponseEntity<?> deleteToken(@RequestParam("token") String token){
        refreshTokenService.removeRefreshToken(token);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
