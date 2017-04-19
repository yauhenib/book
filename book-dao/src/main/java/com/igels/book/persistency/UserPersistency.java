package com.igels.book.persistency;

import com.igels.book.DbUtil;
import com.igels.book.common.IFieldsName;
import com.igels.book.common.TablesName;
import com.igels.book.common.TimestampHelper;
import com.igels.book.sesion.*;
import com.igels.book.user.UserFieldsName;
import com.igels.book.user.UserInfo;
import com.igels.book.query.QueryType;
import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;

import java.net.ConnectException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.util.Pair;

/**
 * Users persistency to CRUD Users.
 */
public class UserPersistency implements IPersistency<UserInfo> {

    /**
     * log4j logger
     */
    private final static Logger logger = Logger.getLogger(UserPersistency.class);

    /**
     * Session to database
     */
    private Session session = null;

    /**
     * Construct user persistency.
     *
     * @throws ConnectException
     */
    public UserPersistency() throws ConnectException {
        try {
            session = DbUtil.getSessionFactory().openSession();
        } catch (JSONException e) {
            logger.error("Error:", e);
            e.printStackTrace();
            throw new ConnectException(e.getMessage());
        }
    }

    /**
     * Enumerate all users
     *
     * @return List<UserInfo>
     * @throws SQLException
     */
    public List<UserInfo> enumerateItems() throws SQLException {
        String query = QueryConstructor.construct(QueryType.SELECT, TablesName.USERS, Optional.empty());
        logger.debug(query);

        ResultSet result = session.executeQuery(query);

        List<UserInfo> users = new ArrayList<>();
        while (result.next()) {
            UserInfo userInfo = fillUserInfo(result);
            logger.debug(userInfo.toString());
            users.add(userInfo);
        }

        return users;
    }

    /**
     * Get user info by user id.
     *
     * @param userId Integer
     * @return UserInfo
     * @throws SQLException
     */
    public UserInfo getItemById(Integer userId) throws SQLException {
        String query = QueryConstructor.construct(QueryType.SELECT, TablesName.USERS, Optional.of(userId));
        logger.debug(query);

        ResultSet result = session.executeQuery(query);

        if (result.next()) {
            UserInfo userInfo = fillUserInfo(result);
            logger.debug(userInfo.toString());
            return userInfo;
        } else {
            logger.warn("User with id =" + userId + " not found.");
        }

        return new UserInfo();
    }

    /**
     * Add user.
     *
     * @param userInfo UserInfo
     * @return Integer, user id
     * @throws SQLException
     */
    public Integer addItem(UserInfo userInfo) throws SQLException {

        userInfo.setCreated(TimestampHelper.getTimestamp());
        String fields = QueryConstructor.constructFields(
                UserFieldsName.RoleId,
                UserFieldsName.Name,
                UserFieldsName.Surname,
                UserFieldsName.Email,
                UserFieldsName.Password,
                UserFieldsName.Created,
                UserFieldsName.Salt
        );

        String values = QueryConstructor.constructValues(
                userInfo.getRoleId(),
                userInfo.getName(),
                userInfo.getSurname(),
                userInfo.getEmail(),
                userInfo.getPassword(),
                userInfo.getCreated(),
                "salt"
        );

        String query = QueryConstructor.construct(QueryType.INSERT, TablesName.USERS, fields, values);
        logger.debug(query);
        return session.executeUpdate(query);
    }

    /**
     * Update user info.
     *
     * @param userInfo UserInfo
     * @return Integer, user id
     * @throws SQLException
     */
    public Integer updateItem(UserInfo userInfo) throws SQLException {

        String fieldsValues = QueryConstructor.constructFieldsValues(
                new Pair<>(UserFieldsName.RoleId, userInfo.getRoleId()),
                new Pair<>(UserFieldsName.Name, userInfo.getName()),
                new Pair<>(UserFieldsName.Surname, userInfo.getSurname()),
                new Pair<>(UserFieldsName.Email, userInfo.getEmail()),
                new Pair<>(UserFieldsName.Password, userInfo.getPassword())
        );

        String query = QueryConstructor.construct(QueryType.UPDATE, TablesName.USERS, fieldsValues,
                Optional.of(userInfo.getId()));
        logger.debug(query);
        return session.executeUpdate(query);
    }

    /**
     * Delete user.
     *
     * @param userId Integer user id
     * @return Integer, user id
     * @throws SQLException
     */
    public Integer deleteItem(Integer userId) throws SQLException {
        String query = QueryConstructor.construct(QueryType.DELETE, TablesName.USERS, Optional.of(userId));
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
    private UserInfo fillUserInfo(ResultSet result) throws SQLException {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(result.getInt(UserFieldsName.Id.getValue()));
        userInfo.setRoleId(result.getInt(UserFieldsName.RoleId.getValue()));
        userInfo.setName(result.getString(UserFieldsName.Name.getValue()));
        userInfo.setSurname(result.getString(UserFieldsName.Surname.getValue()));
        userInfo.setEmail(result.getString(UserFieldsName.Email.getValue()));
        userInfo.setPassword(result.getString(UserFieldsName.Password.getValue()));
        userInfo.setCreated(result.getString(UserFieldsName.Created.getValue()));
        return userInfo;
    }
}
