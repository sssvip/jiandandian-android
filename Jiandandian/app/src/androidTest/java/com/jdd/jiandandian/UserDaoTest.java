package com.jdd.jiandandian;

import android.test.AndroidTestCase;

import com.jdd.jiandandian.dao.UserDao;
import com.jdd.jiandandian.model.User;
import com.jdd.jiandandian.util.LogUtil;

/**
 * Created by David on 2016/6/21.
 */
public class UserDaoTest extends AndroidTestCase {
    public void testadd() {
        UserDao ud = new UserDao();
        User u = new User();
        u.setUser_name("test_name");
        ud.register(u);
    }

    public void testlogin() {
        UserDao ud = new UserDao();
        User u = ud.login("admin123", "admin");
        if (u != null) {
            LogUtil.i(u.getUser_name());

        } else {
            LogUtil.i("login failed");
        }
    }
}
