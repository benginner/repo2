package pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: libraryweb
 * 描述：
 * @author: LJQ
 * @create: 2020-10-09 20:27
 **/
@Data
public class Book {
    /**
     * 图书编号
     */
    private Integer id;
    /**
     * 图书类型
     */
    private Dictionary bookType =new Dictionary();
    /**
     * 书名
     */
    private String bookname;
    /**
     * 图书数量
     */
    private Integer bookamount;
}
