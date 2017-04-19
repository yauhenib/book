package com.igels.book.service.users;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

import com.igels.book.user.UserInfo;
import org.apache.log4j.Logger;

public class UserInfoHelper {

    /**
     * log4j logger
     */
    private final static Logger logger = Logger.getLogger(UserInfoHelper.class);

    static void resetPassword(UserInfo userInfo) {
        userInfo.setPassword("");
    }

    static void resetPassword(List<UserInfo> usersInfo) {
        for (UserInfo userInfo: usersInfo) {
            userInfo.setPassword("");
        }
    }

    static String cryptPassword(UserInfo userInfo) {
        byte[] salt = getSalt();

        // TODO salt with pass

        userInfo.setPassword(cryptWithMD5(userInfo.getPassword()));
        return String.valueOf(salt);
    }

    private static byte[] getSalt() {
        final Random r = new SecureRandom();
        byte[] salt = new byte[32];
        r.nextBytes(salt);
        return salt;
    }

    private static String cryptWithMD5(String pass){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] passBytes = pass.getBytes();
            md.reset();
            byte[] digested = md.digest(passBytes);
            StringBuffer sb = new StringBuffer();
            for(int i=0;i<digested.length;i++){
                sb.append(Integer.toHexString(0xff & digested[i]));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            logger.debug(null, ex);
        }
        return null;
    }

}
