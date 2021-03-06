package com.igels.book.servlets;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.igels.book.client.RequestType;
import com.igels.book.client.RestFullClient;
import com.igels.book.client.ServiceConnectionInfo;
import com.igels.book.common.RequestEntity;
import com.igels.book.common.RolePagesName;
import com.igels.book.role.RoleInfo;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import org.apache.log4j.Logger;

public class RolesServlet extends HttpServlet {

    /**
     * log4j logger.
     */
    private final static Logger logger = Logger.getLogger(RolesServlet.class);

    /**
     * Roles service address.
     */
    private final URI rolesAddress = ServiceConnectionInfo.getAddress(RequestEntity.Roles);

    /**
     * REST Full client.
     */
    private final RestFullClient<RoleInfo> restFullClient = new RestFullClient<>(rolesAddress);

    /**
     * Roles path name.
     */
    private final String roles = "/Roles/";

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
            logger.debug("do post");

            RequestType requestType = RequestHelper.readType(req);
            Integer roleId = RequestHelper.readParam(req);
            RoleInfo roleInfo = RequestHelper.fillRoleInfo(req);

            if (requestType == RequestType.PUT) {
                roleInfo.setId(0);
                addRolePut(roleInfo, req, resp);
            } else if (requestType == RequestType.POST) {
                roleInfo.setId(roleId);
                editRolePost(roleInfo, req, resp);
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
            logger.debug("get");
            RequestType requestType = RequestHelper.readType(req);
            Integer roleId = RequestHelper.readParam(req);

            if (requestType == RequestType.GET) {
                if (roleId == null) {
                    showRoles(req, resp);
                } else {
                    showRole(roleId, req, resp);
                }
            } else if (requestType == RequestType.DELETE) {
                deleteRole(roleId, req, resp);
            } else if (requestType == RequestType.PUT) {
                addRole(req, resp);
            } else if (requestType == RequestType.POST) {
                editRole(roleId, req, resp);
            }
        } catch (Exception e) {
            logger.error("Error:", e);
        }
    }

    /**
     * Show roles
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void showRoles(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    /**
     * Show role
     *
     * @param roleId
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void showRole(int roleId, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    /**
     * Add role
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void addRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(roles + RolePagesName.AddRole.getValue());
        requestDispatcher.forward(req, resp);
    }

    /**
     * Delete role
     *
     * @param roleId
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void deleteRole(int roleId, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("Delete role with id " + roleId);


    }

    /**
     * Add role put
     * @param roleInfo
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void addRolePut(RoleInfo roleInfo, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("Add role " + roleInfo.toString());

    }

    /**
     * Edit role
     * @param roleId
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void editRole(int roleId, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("Edit role with id " + roleId);

    }

    /**
     * Edit role post
     * @param roleInfo
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void editRolePost(RoleInfo roleInfo, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("Edit role " + roleInfo.toString());


    }
}
