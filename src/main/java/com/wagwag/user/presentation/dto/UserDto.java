package com.wagwag.user.presentation.dto;

import lombok.Builder;
import lombok.Getter;

public class UserDto {

    public static class Response{
        @Builder
        @Getter
        public static class SignIn{
            private String accessToken;
            private String refreshToken;
            private String nickName;

            public static SignIn of(String accessToken,String refreshToken,String nickName){
                return SignIn.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .nickName(nickName)
                        .build();
            }
        }

        @Builder
        @Getter
        public static class Reissue{
            private String accessToken;
            private String refreshToken;

            public static Reissue of(String accessToken,String refreshToken){
                return Reissue.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
            }
        }
    }
}
