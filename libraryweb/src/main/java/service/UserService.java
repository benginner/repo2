package service;

import dao.UserDao;
import org.apache.commons.codec.digest.DigestUtils;
import pojo.Book;
import pojo.User;

import java.util.UUID;

/**
 * @program: libraryweb
 * 描述：
 * @author: LJQ
 * @create: 2020-10-10 21:31
 **/
public class UserService {
    UserDao userDao = new UserDao();

    public User saveUser(User user) {
        UserDao userDao = new UserDao();
        int exist = userDao.isExist(user);
        if (exist > 0) {
            return null;
        } else {
            user.setUserid(userDao.querySingle("select seq_library_user.nextval from dual"));
            user.setBalance(0);
            user.setSalt(UUID.randomUUID().toString());
            user.setPassword(DigestUtils.md5Hex(user.getPassword()+user.getSalt()));

            userDao.saveUser(user);
            user.setPassword(null);
            user.setSalt(null);

            return user;

        }

    }

    public boolean loginUser(User user) {
        User dbUser = userDao.findUserByUserName(user.getUsername());
        if (dbUser == null) return false;
        String dbpwd = DigestUtils.md5Hex(user.getPassword() + dbUser.getSalt());
        if (dbpwd.equals(dbUser.getPassword())) {
            user.setUserid(dbUser.getUserid());
            return true;
        }
        return false;
    }

    public void addBook(User user, String[] uids) {
        UserDao userDao = new UserDao();
        for (String uid : uids) {
            Book book = new Book();
            book.setId(Integer.parseInt(uid));
            user.getBooks().add(book);
        }
        userDao.addBook(user.getUserid(), uids);
    }

    public boolean changePassword(User user) {
        User dbUser = userDao.findUserByUserName(user.getUsername());
        if (dbUser==null) return false;
        else {
            user.setPassword(DigestUtils.md5Hex(user.getPassword() + dbUser.getSalt()));
            userDao.updateUserPassword(user);

            return true;
        }


    }
}
