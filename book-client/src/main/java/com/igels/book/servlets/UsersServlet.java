package com.igels.book.servlets;

import com.igels.book.common.RequestEntity;
import com.igels.book.client.RequestType;
import com.igels.book.client.ServiceConnectionInfo;
import com.igels.book.common.UserPagesName;
import com.igels.book.role.RoleInfo;
import com.igels.book.user.UserInfo;
import javafx.util.Pair;
import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This servlet to process JSP requests and sent it to REST full service.
 */
public final class UsersServlet extends HttpServlet {

    /**
     * log4j logger.
     */
    private final static Logger logger = Logger.getLogger(UsersServlet.class);

    /**
     * User service address.
     */
    private final URI userAddress = ServiceConnectionInfo.getAddress(RequestEntity.Users);

    /**
     * Roles service address.
     */
    private final URI rolesAddress = ServiceConnectionInfo.getAddress(RequestEntity.Roles);

    /**
     *
     */
    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * Users path name.
     */
    private final String users = "/Users/";

    /**
     * Catch POST request from JSP and transit it to REST Service.
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            RequestType requestType = RequestHelper.readType(req);
            Integer userId = RequestHelper.readParam(req);
            UserInfo userInfo = RequestHelper.fillUserInfo(req);

            if (requestType == RequestType.PUT) {
                userInfo.setId(0);
                addUserPut(userInfo, req, resp);
            } else if (requestType == RequestType.POST) {
                userInfo.setId(userId);
                editUserPost(userInfo, req, resp);
            }
        } catch (Exception e) {
            logger.error("Error:", e);
        }
    }

    /**
     * Catch GET request from JSP and transit it to REST Service.
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            RequestType requestType = RequestHelper.readType(req);
            Integer userId = RequestHelper.readParam(req);

            if (requestType == RequestType.GET) {
                if (userId == null) {
                    showUsers(req, resp);
                } else {
                    showUser(userId, req, resp);
                }
            } else if (requestType == RequestType.DELETE) {
                deleteUser(userId, req, resp);
            } else if (requestType == RequestType.PUT) {
                addUser(req, resp);
            } else if (requestType == RequestType.POST) {
                editUser(userId, req, resp);
            }
        } catch (Exception e) {
            logger.error("Error:", e);
        }
    }

    /**
     * Get user info.
     *
     * @param userId Integer
     * @param req    HttpServletRequest
     * @param resp   HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    private void showUser(int userId, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("Show user with id " + userId);

        String extendedPath = userAddress.getPath() + '/' + Integer.toString(userId);
        URI requestUri = userAddress.resolve(extendedPath);
        ResponseEntity<UserInfo> userInfo = restTemplate.getForEntity(requestUri, UserInfo.class);

        UserInfo userInfoBody = userInfo.getBody();
        req.setAttribute(RequestEntity.User.getValue(), userInfoBody);

        RoleInfo roleInfo = getRoleInfo(userInfo.getBody().getRoleId());
        req.setAttribute(RequestEntity.Role.getValue(), roleInfo);

        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(users + UserPagesName.User.getValue());
        requestDispatcher.forward(req, resp);
    }

    /**
     * Get users info.
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    private void showUsers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResponseEntity<UserInfo[]> usersList  = restTemplate.getForEntity(userAddress, UserInfo[].class);

        List<Pair<UserInfo, RoleInfo>> pairList = new ArrayList<>();
        for (UserInfo userInfo : usersList.getBody()) {
            RoleInfo roleInfo = getRoleInfo(userInfo.getRoleId());
            pairList.add(new Pair<>(userInfo, roleInfo));
        }
        req.setAttribute(RequestEntity.Users.getValue(), pairList);

        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(users + UserPagesName.Users.getValue());
        requestDispatcher.forward(req, resp);
    }

    /**
     * Delete user.
     *
     * @param userId Integer
     * @param req    HttpServletRequest
     * @param resp   HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    private void deleteUser(int userId, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("Delete user with id " + userId);

        String extendedPath = userAddress.getPath() + '/' + Integer.toString(userId);
        URI requestUri = userAddress.resolve(extendedPath);

        restTemplate.delete(requestUri);

        Integer removedUserId =0;
        req.setAttribute(RequestEntity.User.getValue(), new UserInfo(removedUserId));

        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(users + UserPagesName.DeleteUser.getValue());
        requestDispatcher.forward(req, resp);
    }

    /**
     * Add user (PUT)
     *
     * @param userInfo UserInfo
     * @param req      HttpServletRequest
     * @param resp     HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    private void addUserPut(UserInfo userInfo, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("Add user " + userInfo.toString());
    }

    /**
     * Add user.
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    private void addUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     }

    /**
     * Edit user info.
     *
     * @param userId Integer
     * @param req    HttpServletRequest
     * @param resp   HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    private void editUser(int userId, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("Edit user with id " + userId);

    }

    /**
     * Edit user (POST)
     *
     * @param userInfo UserInfo
     * @param req      HttpServletRequest
     * @param resp     HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    private void editUserPost(UserInfo userInfo, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("Edit user " + userInfo.toString());

    }

     private RoleInfo getRoleInfo(int roleId) {
         RoleInfo roleInfo = new RoleInfo();
         roleInfo.setId(1);
         roleInfo.setName("Administrator");
         return new RoleInfo();
    }
}
