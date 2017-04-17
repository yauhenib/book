package com.igels.book.role;

import com.igels.book.common.InfoHelper;

/**
 * Class helper to Roles info serialization
 */
class RoleInfoHelper extends InfoHelper {

    /**
     * Role info to string
     *
     * @param roleInfo
     * @return String
     */
    static String toString(RoleInfo roleInfo) {
        return beginTerminator +
                RoleFieldsName.Id.getValue() + assign + roleInfo.getId() + delimiter +
                RoleFieldsName.Name.getValue() + assign + roleInfo.getName() +
                endTerminator;
    }
}
