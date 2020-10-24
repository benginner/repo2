package dao;


import pojo.Dictionary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DictionaryDao extends BaseDao {

    public List<Dictionary> queryByType(int[] types) {
        List<Dictionary> list = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String sql = generatorSql(types);
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            if (types != null && types.length > 0) {
                for (int i = 0; i < types.length; i++) {
                    statement.setInt(i + 1, types[i]);
                }
            }
            rs = statement.executeQuery();

            while (rs.next()) {
                if(rs.isFirst()) {
                    list = new ArrayList<>();
                }
                Dictionary dictionary = new Dictionary();
                dictionary.setId(rs.getInt("id"));
                dictionary.setName(rs.getString("name"));
                dictionary.setType(rs.getInt("type"));
                list.add(dictionary);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            releaseResource(rs, statement, connection);
        }
        return list;
    }

    private String generatorSql(int[] types) {
        StringBuilder sql = new StringBuilder("select * from t_library_dictionary ");
        if (types == null || types.length == 0) return sql.toString();
        sql.append("where type in (");
        for (int i = 0; i < types.length; i++) {
            sql.append("?,");
        }
        sql.deleteCharAt(sql.length() - 1);
        sql.append(")");
        return sql.toString();
    }
}
