package com.igels.book.servlets;

import com.igels.book.common.RequestEntity;
import com.igels.book.client.RequestType;
import com.igels.book.client.RestFullClient;
import com.igels.book.client.ServiceConnectionInfo;
import com.igels.book.common.UserPagesName;
import com.igels.book.role.RoleInfo;
import com.igels.book.user.UserInfo;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import javafx.util.Pair;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
     * REST Full client.
     */
    private final RestFullClient<UserInfo> userRestFullClient = new RestFullClient<>(userAddress);

    /**
     * REST Full client.
     */
    private final RestFullClient<RoleInfo> rolesRestFullClient = new RestFullClient<>(rolesAddress);

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
        ClientResponse clientResponse = userRestFullClient.sendMessage(RequestType.GET, Optional.of(new UserInfo(userId)));

        UserInfo userInfo = clientResponse.getEntity(UserInfo.class);
        req.setAttribute(RequestEntity.User.getValue(), userInfo);

        RoleInfo roleInfo = getRoleInfo(userInfo.getRoleId());
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
        ClientResponse clientResponse = userRestFullClient.sendMessage(RequestType.GET, Optional.empty());

        GenericType<List<UserInfo>> usersListType = new GenericType<List<UserInfo>>() {
        };
        List<UserInfo> usersList = clientResponse.getEntity(usersListType);

        List<Pair<UserInfo, RoleInfo>> pairList = new ArrayList<>();
        for (UserInfo userInfo : usersList) {
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

        ClientResponse clientResponse = userRestFullClient.sendMessage(RequestType.DELETE, Optional.of(new UserInfo(userId)));

        Integer removedUserId = clientResponse.getEntity(Integer.class);
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

        ClientResponse clientResponse = userRestFullClient.sendMessage(RequestType.PUT, Optional.of(userInfo));
        Integer result = clientResponse.getEntity(Integer.class);

        userInfo.setId(result);
        req.setAttribute(RequestEntity.User.getValue(), userInfo);

        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(users + UserPagesName.AddUser.getValue());
        requestDispatcher.forward(req, resp);
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

        ClientResponse rolesClientResponse = rolesRestFullClient.sendMessage(RequestType.GET, Optional.empty());
        GenericType<List<RoleInfo>> rolesListType = new GenericType<List<RoleInfo>>() {
        };
        List<RoleInfo> rolesList = rolesClientResponse.getEntity(rolesListType);
        req.setAttribute(RequestEntity.Roles.getValue(), rolesList);

        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(users + UserPagesName.AddUser.getValue());
        requestDispatcher.forward(req, resp);
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

        ClientResponse usersClientResponse = userRestFullClient.sendMessage(RequestType.GET, Optional.of(new UserInfo(userId)));

        UserInfo userInfo = usersClientResponse.getEntity(UserInfo.class);
        req.setAttribute(RequestEntity.User.getValue(), userInfo);

        ClientResponse rolesClientResponse = rolesRestFullClient.sendMessage(RequestType.GET, Optional.empty());
        GenericType<List<RoleInfo>> rolesListType = new GenericType<List<RoleInfo>>() {
        };
        List<RoleInfo> rolesList = rolesClientResponse.getEntity(rolesListType);
        req.setAttribute(RequestEntity.Roles.getValue(), rolesList);

        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(users + UserPagesName.EditUser.getValue());
        requestDispatcher.forward(req, resp);
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

        ClientResponse clientResponse = userRestFullClient.sendMessage(RequestType.POST, Optional.of(userInfo));
        Integer result = clientResponse.getEntity(Integer.class);

        userInfo.setId(result);
        userInfo.setName("");
        req.setAttribute(RequestEntity.User.getValue(), userInfo);

        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(users + UserPagesName.EditUser.getValue());
        requestDispatcher.forward(req, resp);
    }

     private RoleInfo getRoleInfo(int roleId) {
         RoleInfo roleInfo = new RoleInfo();
         roleInfo.setId(roleId);

         ClientResponse clientResponse = rolesRestFullClient.sendMessage(RequestType.GET, Optional.of(roleInfo));
         return clientResponse.getEntity(RoleInfo.class);
    }
}
