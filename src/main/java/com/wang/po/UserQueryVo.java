package com.wang.po;

import java.util.List;

/**
 * Created by Administrator on 2017/5/7 0007.
 */
public class UserQueryVo {
    private List<Integer> ids;
    private UserCustorm userCustorm;

    public void setUserCustorm(UserCustorm userCustorm) {
        this.userCustorm = userCustorm;
    }

    public void setIds(List<Integer> ids) { this.ids = ids; }

    public UserCustorm getUserCustorm() {
        return userCustorm;
    }

    public List<Integer> getIds() { return ids; }
}
