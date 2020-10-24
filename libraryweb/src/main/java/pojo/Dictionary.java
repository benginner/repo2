package pojo;

import lombok.Data;

/**
 * @program: libraryweb
 * 描述：
 * @author: LJQ
 * @create: 2020-10-22 19:38
 **/
@Data
public class Dictionary {
    //主键id
    private Integer id;
    //字典名称
    private String name;
    //字典类型
    private  Integer type;

}
