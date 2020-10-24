package dao;

import pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @program: libraryweb
 * 描述：
 * @author: LJQ
 * @create: 2020-10-10 21:32
 **/
public class UserDao extends BaseDao{
    public int saveUser(User user){
        String sql="insert into T_LIBRARY_USER values(?,?,?,?,?)";
            return super.saveOrUpdateOrDelete(sql,user.getUserid(),user.getUsername(),user.getPassword(),user.getBalance(),user.getSalt());
    }
    public int isExist(User user){
        String sql="select * from T_LIBRARY_USER where username=?";
        return super.querySingle(sql, user.getUsername());
    }
    public int usernameAndPasswordIsTrue(User user){
        String sql="select * from T_LIBRARY_USER where username=? and password=?";
        return super.querySingle(sql, user.getUsername(),user.getPassword());
    }

    public void addBook(Integer userid, String[] uids) {
        String sql="insert into t_library_user_book values(?,?)";
        for (int i = 0; i < uids.length; i++) {
            super.saveOrUpdateOrDelete(sql,uids[i],userid);
        }
    }
    public int findIdByUserName(String userName){
        String sql="select USERID from T_LIBRARY_USER where USERNAME = ?";
        return super.querySingle(sql,userName);
    }
    public int updateUserPassword(User user){
        String sql="update T_LIBRARY_USER set password=? where username=?";
        return super.saveOrUpdateOrDelete(sql,user.getPassword(),user.getUsername());
    }
    public User findUserByUserName(String username){
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        User user=null;
        String sql="select * from T_LIBRARY_USER where username=?";
        try {
            connection=getConnection();
            statement=connection.prepareStatement(sql);
            statement.setString(1,username);
            resultSet=statement.executeQuery();
            if (resultSet.next()){
                user=new User();
                user.setUserid(resultSet.getInt("userid"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setSalt(resultSet.getString("salt"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            releaseResource(resultSet,statement,connection);
        }
        return user;
    }
}
