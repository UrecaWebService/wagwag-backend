package com.wagwag.user.application;


import com.wagwag.config.oauth.OauthInfo;
import com.wagwag.user.domain.User;

public interface UserRepository {
    public User findByOauthInfoOid(String Oid, OauthInfo oauthInfo);
    public User save(User user);

    public User findById(Long id);

}
