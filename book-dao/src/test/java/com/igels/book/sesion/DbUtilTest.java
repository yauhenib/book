package com.igels.book.sesion;

import java.net.ConnectException;
import java.sql.SQLException;
import java.util.List;

import com.igels.book.entity.UserInfo;
import com.igels.book.persistency.UserPersistency;
import org.codehaus.jettison.json.JSONException;
import org.junit.Test;

public class DbUtilTest {

    @Test
    public void test1() throws SQLException, ConnectException, JSONException {

        UserPersistency p = new UserPersistency();
        List<UserInfo> users = p.enumerateUsers();
        for (UserInfo user : users) {
            System.out.println(user.toString());
        }
    }
}
