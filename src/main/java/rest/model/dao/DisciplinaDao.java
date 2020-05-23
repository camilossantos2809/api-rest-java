package rest.model.dao;

import rest.model.Disciplina;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaDao implements IDao<Disciplina> {
    private Connection conn;

    public DisciplinaDao(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Disciplina> list() {
        try {
            Statement stmt = this.conn.createStatement();
            ResultSet res = stmt.executeQuery("select * from disciplina");

            List<Disciplina> list = new ArrayList<>();
            while (res.next()) {
                Disciplina obj = new Disciplina();
                obj.setNome(res.getString("nome"));
                obj.setCodigo(res.getInt("codigo"));
                list.add(obj);
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public void create(Disciplina dados) throws SQLException {
        String sql = "INSERT INTO disciplina(codigo, nome) VALUES(?,?)";
        try {
            PreparedStatement pstmt = this.conn.prepareStatement(sql);
            pstmt.setInt(1, dados.getCodigo());
            pstmt.setString(2, dados.getNome());
            pstmt.executeUpdate();
        } catch (SQLException ex) {

        }
    }

    @Override
    public Disciplina read(int id) {
        String sql = "select * from disciplina where codigo=?";
        try {
            PreparedStatement pstmt = this.conn.prepareStatement(sql);
            pstmt.setInt(1,id);

            ResultSet res = pstmt.executeQuery();

            Disciplina obj = new Disciplina();
            while (res.next() && res.isFirst()){
                obj.setNome(res.getString("nome"));
                obj.setCodigo(res.getInt("codigo"));
            }
            return obj;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public void update(int id, Disciplina dadosNovos) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }

    public static void createSchema(Connection conn) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS disciplina(" +
                "  codigo integer PRIMARY KEY," +
                "  nome text NOT NULL" +
                ");";
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new SQLException(ex);
        }
    }


}
