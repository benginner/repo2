package dao;
import utils.PropUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BaseDao {
    static {
        try {
            Class.forName(PropUtils.get("jdbc.driver"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(PropUtils.get("jdbc.url"), PropUtils.get("jdbc.username"), PropUtils.get("jdbc.password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取数字类型的查询结果一条
     * @param sql
     * @param param
     * @return
     */

    public int querySingle(String sql, Object... param) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            for (int i = 0; i < param.length; i++) {
                statement.setObject(i + 1, param[i]);
            }
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            releaseResource(resultSet, statement, connection);
        }
        return 0;
    }
    /**
     * 添加删除修改通用方法
     *
     * @param sql
     * @param param
     * @return
     */
    public int saveOrUpdateOrDelete(String sql, Object... param) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            for (int i = 0; i < param.length; i++) {
                statement.setObject(i + 1, param[i]);
            }
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            releaseResource(null, statement, connection);
        }
        return 0;
    }
    //查询
    public List<Object> queryObject(String sql, Object...param){
        Connection connection = null;
        PreparedStatement statement = null;
        List<Object> list = new ArrayList<>();
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            for (int i = 0; i < param.length; i++) {
                statement.setObject(i + 1, param[i]);
            }
            ResultSet resultSet = statement.executeQuery();
            int i=0;
            if (resultSet.next()){
                list.add(resultSet.getObject(i++));

            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            releaseResource(null, statement, connection);
        }
        return null;

    }
    public void releaseResource(ResultSet resultSet, Statement statement, Connection connection) {
        try {
            if (resultSet != null && !resultSet.isClosed()) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (statement != null && !statement.isClosed()) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
