package com.wagwag.user.infrastructure;


import com.wagwag.config.oauth.OauthInfo;
import com.wagwag.global.exception.BusinessException;
import com.wagwag.global.exception.errorcode.CommonErrorCode;
import com.wagwag.user.application.UserRepository;
import com.wagwag.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Override
    @Transactional
    public User findByOauthInfoOid(String Oid, OauthInfo oauthInfo) {
        return userJpaRepository.findByOauthInfoOid(Oid).orElseGet(()-> forceJoin(oauthInfo));
    }

    @Override
    @Transactional
    public User save(User user) {
        return userJpaRepository.save(user);
    }

    @Transactional
    public User forceJoin(OauthInfo oauthInfo) {
        User newMember = User.create(oauthInfo);
        return save(newMember);
    }

    @Override
    public User findById(Long id) {
        return userJpaRepository.findById(id).orElseThrow(()->new BusinessException(CommonErrorCode.USER_NOT_FOUND));
    }

}
