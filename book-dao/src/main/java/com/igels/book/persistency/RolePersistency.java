package com.igels.book.persistency;

import java.net.ConnectException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.igels.book.DbUtil;
import com.igels.book.common.TablesName;
import com.igels.book.query.QueryType;
import com.igels.book.role.RoleFieldsName;
import com.igels.book.role.RoleInfo;
import com.igels.book.sesion.Session;
import com.igels.book.user.UserFieldsName;
import javafx.util.Pair;
import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;

public class RolePersistency implements IPersistency<RoleInfo> {

    /**
     * log4j logger
     */
    private final static Logger logger = Logger.getLogger(RolePersistency.class);

    /**
     * Session to database
     */
    private Session session = null;

    /**
     * Construct user persistency.
     *
     * @throws ConnectException
     */
    public RolePersistency() throws ConnectException {
        try {
            session = DbUtil.getSessionFactory().openSession();
        } catch (JSONException e) {
            logger.error("Error:", e);
            e.printStackTrace();
            throw new ConnectException(e.getMessage());
        }
    }

    /**
     * Enumerate all roles
     *
     * @return List<RoleInfo>
     * @throws SQLException
     */
    public List<RoleInfo> enumerateItems() throws SQLException {
        String query = QueryConstructor.construct(QueryType.SELECT, TablesName.ROLES, Optional.empty());
        logger.debug(query);

        ResultSet result = session.executeQuery(query);

        List<RoleInfo> roles = new ArrayList<>();
        while (result.next()) {
            RoleInfo userInfo = fillRoleInfo(result);
            logger.debug(userInfo.toString());
            roles.add(userInfo);
        }

        return roles;
    }

    /**
     * Get user info by user id.
     *
     * @param roleId Integer
     * @return RoleInfo
     * @throws SQLException
     */
    public RoleInfo getItemById(Integer roleId) throws SQLException {
        String query = QueryConstructor.construct(QueryType.SELECT, TablesName.ROLES, Optional.of(roleId));
        logger.debug(query);

        ResultSet result = session.executeQuery(query);

        if (result.next()) {
            RoleInfo roleInfo = fillRoleInfo(result);
            logger.debug(roleInfo.toString());
            return roleInfo;
        } else {
            logger.warn("Role with id =" +roleId + " not found.");
        }

        return new RoleInfo();
    }

    /**
     * Add user.
     *
     * @param roleInfo RoleInfo
     * @return Integer, role id
     * @throws SQLException
     */
    public Integer addItem(RoleInfo roleInfo) throws SQLException {

        String fields = QueryConstructor.constructFields(
                RoleFieldsName.Name
        );

        String values = QueryConstructor.constructValues(
                roleInfo.getName()
        );

        String query = QueryConstructor.construct(QueryType.INSERT, TablesName.ROLES, fields, values);
        logger.debug(query);
        return session.executeUpdate(query);
    }

    /**
     * Update user info.
     *
     * @param roleInfo RoleInfo
     * @return Integer, role id
     * @throws SQLException
     */
    public Integer updateItem(RoleInfo roleInfo) throws SQLException {

        String fieldsValues = QueryConstructor.constructFieldsValues(
                new Pair<>(RoleFieldsName.Name, roleInfo.getName())
        );

        String query = QueryConstructor.construct(QueryType.UPDATE, TablesName.ROLES, fieldsValues,
                Optional.of(roleInfo.getId()));
        logger.debug(query);
        return session.executeUpdate(query);
    }

    /**
     * Delete user.
     *
     * @param roleId Integer role id
     * @return Integer, user id
     * @throws SQLException
     */
    public Integer deleteItem(Integer roleId) throws SQLException {
        String query = QueryConstructor.construct(QueryType.DELETE, TablesName.ROLES, Optional.of(roleId));
        logger.debug(query);
        return session.executeUpdate(query);
    }

    /**
     * Fill user info from SQL result set.
     *
     * @param result ResultSet
     * @return UserInfo
     * @throws SQLException
     */
    private RoleInfo fillRoleInfo(ResultSet result) throws SQLException {
        RoleInfo roleInfo = new RoleInfo();
        roleInfo.setId(result.getInt(RoleFieldsName.Id.getValue()));
        roleInfo.setName(result.getString(RoleFieldsName.Name.getValue()));
        return roleInfo;
    }
}
