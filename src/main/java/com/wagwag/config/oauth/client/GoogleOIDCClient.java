package com.wagwag.config.oauth.client;

import com.wagwag.config.oauth.dto.OIDCPublicKeysResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

// OIDC 공개 키를 가져오는 클라이언트
@FeignClient(name = "GoogleOIDCClient", url = "https://www.googleapis.com")
public interface GoogleOIDCClient {

    @GetMapping("/oauth2/v3/certs")
    OIDCPublicKeysResponse getGoogleOIDCOpenKeys();
}
