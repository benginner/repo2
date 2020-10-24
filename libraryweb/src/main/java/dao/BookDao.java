package dao;

import pojo.Book;
import pojo.PageBean;
import utils.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

/**
 * @program: libraryweb
 * 描述：
 * @author: LJQ
 * @create: 2020-10-10 19:57
 **/
public class BookDao extends BaseDao {
    public int saveBook(Book book) {
        String sql = "insert into T_LIBRARY_BOOK values(?,?,?,?)";
        return super.saveOrUpdateOrDelete(sql, book.getId(), book.getBookType().getId(), book.getBookname(), book.getBookamount());
    }
    public List<Book> queryBooks(){
        String sql="select * from T_LIBRARY_BOOK";
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        List<Book> list=null;

        try {

            connection=getConnection();
            statement = connection.prepareStatement(sql);
            resultSet=statement.executeQuery();
            while (resultSet.next()){
                if (resultSet.isFirst()){
                    list=new ArrayList<>();
                }
                Book book = new Book();
                book.setBookamount(resultSet.getInt("bookamount"));
                book.setId(resultSet.getInt("id"));
                book.setBookname(resultSet.getString("bookname"));
                book.getBookType().setId(resultSet.getInt("book_type"));
                list.add(book);
            }
            return list;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            releaseResource(resultSet,statement,connection);
        }



    }

    public List<Book> queryBooksByPage(PageBean<Book> pageBean){
        StringBuilder sql=new StringBuilder("SELECT * FROM (SELECT ROWNUM rowno,t.* FROM (SELECT b.*,d.name diname,d.type ditype FROM t_library_book b LEFT JOIN t_library_dictionary d ON b.book_type=d.id WHERE 1=1 ");
        if (StringUtils.isNotEmpty(pageBean.getEntity().getBookType().getName())){
            sql.append(" and bookname like '%"+pageBean.getEntity().getBookType().getName()+"%' ");
        }
        if (!(pageBean.getEntity().getBookType().getId()==null||pageBean.getEntity().getBookType().getId()==0)){
            sql.append(" and book_type = "+pageBean.getEntity().getBookType().getId()+" ");
        }
        sql.append(") t WHERE ROWNUM<=?) a WHERE a.rowno >=?");
        Connection connection = null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        List<Book> books=null;
        try {
            connection=getConnection();
            statement=connection.prepareStatement(sql.toString());
            statement.setInt(1,pageBean.getEnd());
            statement.setInt(2,pageBean.getStart());
            resultSet=statement.executeQuery();
            while (resultSet.next()){
                if (resultSet.isFirst()){
                    books=new ArrayList<>();
                }
                Book book = new Book();
                book.setBookamount(resultSet.getInt("bookamount"));
                book.setId(resultSet.getInt("id"));
                book.setBookname(resultSet.getString("bookname"));
                book.getBookType().setId(resultSet.getInt("book_type"));
                book.getBookType().setName(resultSet.getString("diname"));
                book.getBookType().setType(resultSet.getInt("ditype"));
                books.add(book);
            }
            return books;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            releaseResource(resultSet,statement,connection);
        }

    }
    public int queryTotalCount(PageBean<Book> pageBean){
        StringBuilder sql=new StringBuilder("select count(*) from T_LIBRARY_BOOK where 1=1 ");
        if (StringUtils.isNotEmpty(pageBean.getEntity().getBookType().getName())){
            sql.append(" and bookname like '%"+pageBean.getEntity().getBookType().getName()+"%' ");
        }
        if (!(pageBean.getEntity().getBookType().getId()==null||pageBean.getEntity().getBookType().getId()==0)){
            sql.append(" and book_type = "+pageBean.getEntity().getBookType().getId()+" ");
        }
        return super.querySingle(sql.toString());
    }
}
