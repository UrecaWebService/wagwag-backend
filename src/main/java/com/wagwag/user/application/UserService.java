package com.wagwag.user.application;


import com.wagwag.config.jwt.util.JwtUtil;
import com.wagwag.config.oauth.OauthInfo;
import com.wagwag.config.oauth.client.Helper.GoogleOauthHelper;
import com.wagwag.config.oauth.client.Helper.KakaoOauthHelper;
import com.wagwag.config.redis.util.RedisUtil;
import com.wagwag.global.exception.BusinessException;
import com.wagwag.user.domain.User;
import com.wagwag.user.presentation.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.wagwag.global.exception.errorcode.CommonErrorCode.JWT_REFRESHTOKEN_NOT_MATCH;
import static com.wagwag.global.exception.errorcode.CommonErrorCode.REFRESH_TOKEN_NOT_FOUND;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor

public class UserService {
    private final UserRepository userRepository;
    private final KakaoOauthHelper kakaoOauthHelper;
    private final GoogleOauthHelper googleOauthHelper;

    private final JwtUtil jwtUtil;
    private final RedisUtil redisUtil;
    private static final String RT = "RT:";
    private static final String LOGOUT = "LOGOUT:";
    private static final String ROLE_USER = "ROLE_USER";

    public String kakaoCode(String code){
        return kakaoOauthHelper.getKakaoIdToken(code);
    }

    public String googleCode(String code){
        return googleOauthHelper.getGoogleIdToken(code);
    }
    @Transactional
    public UserDto.Response.SignIn login(String idToken, String type){
        OauthInfo oauthInfo;
        if(type.equals("kakao")){
            oauthInfo = kakaoOauthHelper.getOauthInfoByToken(idToken);
        }else{
            oauthInfo = googleOauthHelper.getOauthInfoByToken(idToken);
        }
        User user = userRepository.findByOauthInfoOid(oauthInfo.getOid(),oauthInfo);

        return UserDto.Response.SignIn.of(
                jwtUtil.createAccessToken(user.getId(), ROLE_USER),
                getOrGenerateRefreshToken(user)
                ,user.getOauthInfo().getNickname());
    }


    @Transactional
    public UserDto.Response.Reissue reissue(String refreshToken){
        String resolveToken = jwtUtil.resolveToken(refreshToken);
        Long userIdInToken = jwtUtil.getIdFromToken(resolveToken);

        String refreshTokenInRedis = redisUtil.getData(RT+userIdInToken);

        if(!resolveToken.equals(refreshTokenInRedis)){
            throw new BusinessException(JWT_REFRESHTOKEN_NOT_MATCH);
        }

        String newRefreshToken =jwtUtil.createRefreshToken(userIdInToken);
        String newAccessToken = jwtUtil.createAccessToken(userIdInToken, ROLE_USER);
        redisUtil.setData(RT+userIdInToken,newRefreshToken,jwtUtil.REFRESH_TOKEN_VALID_TIME);

        return UserDto.Response.Reissue.of(newRefreshToken,newAccessToken);
    }

    @Transactional
    public void logout(String accessToken){
        String resolveToken = jwtUtil.resolveToken(accessToken);
        Long userIdInToken = jwtUtil.getIdFromToken(resolveToken);
        String refreshTokenInRedis = redisUtil.getData(RT+userIdInToken);

        if (refreshTokenInRedis == null) throw new BusinessException(REFRESH_TOKEN_NOT_FOUND);

        redisUtil.deleteDate(RT+ userIdInToken);
        redisUtil.setData(LOGOUT+resolveToken, LOGOUT, jwtUtil.getExpiration(resolveToken));// 블랙리스트 처리
    }


    @Transactional
    protected String getOrGenerateRefreshToken(User user){
        String refreshToken = redisUtil.getData(RT + user.getId());

        if (refreshToken == null) {
            refreshToken = jwtUtil.createRefreshToken(user.getId());
            redisUtil.setData(RT + user.getId(), refreshToken, jwtUtil.REFRESH_TOKEN_VALID_TIME);
        }
        return refreshToken;
    }
}

