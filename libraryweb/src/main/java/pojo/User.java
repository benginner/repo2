package pojo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: libraryweb
 * 描述：
 * @author: LJQ
 * @create: 2020-10-09 20:27
 **/
@Data
public class User {
    /** 用户编号 */
    private Integer userid ;
    /** 用户名 */
    private String username ;
    //密码
    private String password;
    //余额
    private double balance;
    //图书id数组
    private List<Book> books=new ArrayList<>();
    //盐
    private String salt;
}
