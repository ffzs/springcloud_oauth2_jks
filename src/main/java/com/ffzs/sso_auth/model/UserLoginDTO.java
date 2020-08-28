package com.ffzs.sso_auth.model;

/**
 * @author ffzs
 * @describe
 * @date 2020/6/9
 */
public class UserLoginDTO {

    private MyToken myToken;

    private SsoUser user;

    public MyToken getMyToken() {
        return myToken;
    }

    public void setMyToken(MyToken myToken) {
        this.myToken = myToken;
    }

    public SsoUser getUser() {
        return user;
    }

    public void setUser(SsoUser user) {
        this.user = user;
    }
}
