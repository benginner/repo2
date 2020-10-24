package service;

import dao.DictionaryDao;
import pojo.Dictionary;

import java.util.HashMap;
import java.util.List;

/**
 * @program: libraryweb
 * 描述：
 * @author: LJQ
 * @create: 2020-10-22 19:40
 **/
public class DictionaryService {
    DictionaryDao dictionaryDao = new DictionaryDao();

    public HashMap<String, List<Dictionary>> addBookInit() {
        HashMap<String, List<Dictionary>> hashMap = new HashMap<>();
        List<Dictionary> list = dictionaryDao.queryByType(new int[]{1});
        hashMap.put("bookType",list);

        return hashMap;
    }
}
