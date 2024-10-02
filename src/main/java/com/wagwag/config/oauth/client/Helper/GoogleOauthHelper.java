package com.wagwag.config.oauth.client.Helper;


import com.wagwag.config.oauth.OAuthOIDCHelper;
import com.wagwag.config.oauth.OauthInfo;
import com.wagwag.config.oauth.client.GoogleOIDCClient;
import com.wagwag.config.oauth.client.GoogleOauthClient;
import com.wagwag.config.oauth.dto.OIDCDecodePayload;
import com.wagwag.config.oauth.dto.OIDCPublicKeysResponse;
import com.wagwag.user.domain.enums.SocialType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@RequiredArgsConstructor
@Slf4j
public class GoogleOauthHelper {

    private final OAuthOIDCHelper oauthOIDCHelper;
    private final GoogleOauthClient googleOauthClient;
    private final GoogleOIDCClient googleOIDCClient;

    private final String googleClientId;
    private final String googleClientSecret;  // Google Client Secret 추가
    private final String googleRedirectUri;

    public OIDCDecodePayload getOIDCDecodePayload(String token) {
        OIDCPublicKeysResponse oidcPublicKeyResponse = googleOIDCClient.getGoogleOIDCOpenKeys();

        return oauthOIDCHelper.getPayloadFromIdToken(
                token, //idToken
                "https://accounts.google.com",  // iss 와 대응되는 값
                googleClientId, // aud 와 대응되는값
                oidcPublicKeyResponse  // 공개키 목록
        );
    }

    public OauthInfo getOauthInfoByToken(String idToken) {
        OIDCDecodePayload oidcDecodePayload = getOIDCDecodePayload(idToken);
        return OauthInfo.builder()
                .socialType(SocialType.GOOGLE)
                .oid(oidcDecodePayload.getSub())
                .nickname(oidcDecodePayload.getName())  // 구글에서는 이름이 닉네임으로 사용될 수 있습니다.
                .email(oidcDecodePayload.getEmail())
                .build();
    }

    public String getGoogleIdToken(String code) {


        Map<String, Object> tokenResponse = googleOauthClient.getIdToken(
                "authorization_code", // grant_type
                googleClientId, // client_id
                googleRedirectUri, // redirect_uri
                code, // authorization code
                googleClientSecret // client_secret (필요 시 사용)
        );

        return (String) tokenResponse.get("id_token");
    }

}
