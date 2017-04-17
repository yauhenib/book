package com.igels.book.entity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserInfoHelperTest {

    @Test
    public void testUserInfoToString() {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(1);
        userInfo.setRoleId(1);
        userInfo.setCreated("1");
        userInfo.setSurname("1");
        userInfo.setName("1");
        userInfo.setEmail("1");
        userInfo.setPassword("1");

        assertEquals("{id=1, role_id=1, name=1, surname=1, email=1, password=1, created=1}", UserInfoHelper.toString(userInfo));
    }
}
