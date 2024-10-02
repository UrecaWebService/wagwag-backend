package com.wagwag.user.presentation;


import com.wagwag.global.response.SuccessResponse;
import com.wagwag.user.application.UserService;
import com.wagwag.user.presentation.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/auth")
public class UserController {

    private final UserService userService;

    @GetMapping("/kakao-login")
    public SuccessResponse<UserDto.Response.SignIn> kakaoLogin(@RequestParam final String code){
        // Step 1: code로 idToken 가져오기
        String idToken = userService.kakaoCode(code);
        System.out.println(idToken);
        // Step 2: idToken으로 로그인 처리
        UserDto.Response.SignIn response = userService.login(idToken,"kakao");
        return SuccessResponse.success(response);

    }
    @GetMapping("/google-login")
    public SuccessResponse<UserDto.Response.SignIn> googleLogin(@RequestParam final String code,
                                                                 @RequestParam String state){
        String idToken = userService.googleCode(code);
        log.info(" Kakao idToken : {}", idToken);

        UserDto.Response.SignIn response = userService.login(idToken,"google");
        return SuccessResponse.success(response);
    }
    //
    @PostMapping("/reissue")
    public SuccessResponse<UserDto.Response.Reissue> reissue(@RequestHeader("Authorization") String refreshToken){
        UserDto.Response.Reissue response = userService.reissue(refreshToken);
        return SuccessResponse.success(response);
    }

    @PostMapping("/logout")
    public SuccessResponse<String> logout(@RequestHeader("Authorization") String accessToken){
        userService.logout(accessToken);
        return SuccessResponse.success("로그아웃 성공");
    }

    @GetMapping("/test")
    public void test(@AuthenticationPrincipal Long userId){
        System.out.println("ji");
        System.out.println(userId);
    }

}