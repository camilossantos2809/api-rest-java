package rest.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IDao<T> {
    public static void createSchema(Connection conn) throws SQLException{
    }

    public List<T> list();

    public void create(T dados) throws SQLException;

    public T read(Map<String, Integer> pk);

    public void update(Map<String, Integer> pk, T dadosNovos) throws SQLException;

    public void delete(Map<String, Integer> pk) throws SQLException;
}
