package com.wagwag.config.oauth;

import com.wagwag.user.domain.enums.SocialType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OauthInfo {

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    private String oid;

    private String nickname;

    private String email;

    @Builder
    public OauthInfo(SocialType socialType, String oid, String nickname,String email){
        this.socialType =socialType;
        this.oid =oid;
        this.nickname = nickname;
        this.email = email;
    }
}
