package utils;

import java.sql.*;

/**
 * @program: javaweb
 * 描述：
 * @author: LJQ
 * @create: 2020-09-10 10:51
 **/
public class JdbcUtils {
    static {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取connection
     * @return
     */
    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(PropUtils.get("jdbc.url"),PropUtils.get("jdbc.username"),PropUtils.get("jdbc.password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *修改
     * @return
     */
    public static int saveOrUpdateOrDelete(String sql,Object... param){
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < param.length; i++) {
                preparedStatement.setObject(i+1,param[i]);
            }
            int res=preparedStatement.executeUpdate();
            return res;

        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 关闭资源
     * @param resultSet
     * @param statement
     * @param connection
     */
    public static void releaseResource(ResultSet resultSet, Statement statement,Connection connection){
        try {
            if (resultSet!=null&&!resultSet.isClosed()){
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (statement!=null&&!statement.isClosed()){
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (connection!=null&&!connection.isClosed()){
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
