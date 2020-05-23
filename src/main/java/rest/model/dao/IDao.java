package rest.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface IDao<T> {
    public static void createSchema(Connection conn) throws SQLException{
    }

    public List<T> list();

    public void create(T dados) throws SQLException;

    public T read(int id);

    public void update(int id, T dadosNovos) throws SQLException;

    public void delete(int id) throws SQLException;
}
