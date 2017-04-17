package com.igels.book.user;

import com.igels.book.common.InfoHelper;

/**
 * Class helper to User info serialization
 */
class UserInfoHelper extends InfoHelper {

    /**
     * User info to string
     *
     * @param userInfo
     * @return String
     */
    static String toString(UserInfo userInfo) {
        return beginTerminator +
                UserFieldsName.Id.getValue() + assign + userInfo.getId() + delimiter +
                UserFieldsName.RoleId.getValue() + assign + userInfo.getRoleId() + delimiter +
                UserFieldsName.Name.getValue() + assign + userInfo.getName() + delimiter +
                UserFieldsName.Surname.getValue() + assign + userInfo.getSurname() + delimiter +
                UserFieldsName.Email.getValue() + assign + userInfo.getEmail() + delimiter +
                UserFieldsName.Password.getValue() + assign + userInfo.getPassword() + delimiter +
                UserFieldsName.Created.getValue() + assign + userInfo.getCreated() +
                endTerminator;
    }
}
