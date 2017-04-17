package com.igels.book.servlets;

import com.igels.book.client.RequestType;
import com.igels.book.role.RoleFieldsName;
import com.igels.book.role.RoleInfo;
import com.igels.book.user.UserFieldsName;
import com.igels.book.user.UserInfo;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * This helper class to:
 * Request type conversion.
 * Read request get param.
 * Full user info from request.
 */
class RequestHelper {

    /**
     * log4j logger.
     */
    private final static Logger logger = Logger.getLogger(UsersServlet.class);

    /**
     * Read GET param from HttpServletRequest
     *
     * @param req HttpServletRequest.
     * @return Integer value of param.
     */
    static Integer readParam(HttpServletRequest req) {
        String path = req.getPathInfo();

        if (path != null) {
            String lastQueryParam = path.substring(path.lastIndexOf("/") + 1);
            return Integer.parseInt(lastQueryParam);
        }
        return null;
    }

    /**
     * Read request type from HttpServletRequest.
     *
     * @param req HttpServletRequest.
     * @return RequestType
     */
    static RequestType readType(HttpServletRequest req) {

        logger.debug(req.getRequestURI());
        String requestUri = req.getRequestURI();

        if (requestUri.contains(RequestType.DELETE.getValue()))
            return RequestType.DELETE;

        if (requestUri.contains(RequestType.PUT.getValue()))
            return RequestType.PUT;

        if (requestUri.contains(RequestType.POST.getValue()))
            return RequestType.POST;

        return RequestType.GET;
    }

    /**
     * Fill UserInfo from HttpServletRequest
     *
     * @param req HttpServletRequest
     * @return UserInfo object.
     */
    static UserInfo fillUserInfo(HttpServletRequest req) {
        UserInfo userInfo = new UserInfo();
        userInfo.setRoleId(Integer.valueOf(req.getParameter(UserFieldsName.RoleId.getValue())));
        userInfo.setName(req.getParameter(UserFieldsName.Name.getValue()));
        userInfo.setSurname(req.getParameter(UserFieldsName.Surname.getValue()));
        userInfo.setEmail(req.getParameter(UserFieldsName.Email.getValue()));
        userInfo.setPassword(req.getParameter(UserFieldsName.Password.getValue()));
        return userInfo;
    }

    /**
     * Fill role info
     *
     * @param req
     * @return RoleInfo
     */
    static RoleInfo fillRoleInfo(HttpServletRequest req) {
        RoleInfo roleInfo = new RoleInfo();
        roleInfo.setName(req.getParameter(RoleFieldsName.Name.getValue()));
        return roleInfo;
    }
}
