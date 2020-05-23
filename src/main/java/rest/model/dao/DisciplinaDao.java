package rest.model.dao;

import rest.model.Disciplina;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            ex.printStackTrace();
            throw new SQLException(ex);
        }
    }

    @Override
    public Disciplina read(Map<String, Integer> pk) {
        String sql = "select * from disciplina where codigo=?";
        try {
            PreparedStatement pstmt = this.conn.prepareStatement(sql);
            pstmt.setInt(1, pk.get("codigo"));

            ResultSet res = pstmt.executeQuery();

            Disciplina obj = new Disciplina();
            while (res.next() && res.isFirst()) {
                obj.setNome(res.getString("nome"));
                obj.setCodigo(res.getInt("codigo"));
            }
            return obj;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Disciplina read(int codigo) throws SQLException {
        Map<String, Integer> pk = new HashMap<>();
        pk.put("codigo", codigo);
        return this.read(pk);
    }

    @Override
    public void update(Map<String, Integer> pk, Disciplina dadosNovos) throws SQLException {
        String sql = "UPDATE disciplina SET codigo=?, nome=? where codigo=?";
        try {
            PreparedStatement pstmt = this.conn.prepareStatement(sql);
            pstmt.setInt(1, dadosNovos.getCodigo());
            pstmt.setString(2, dadosNovos.getNome());
            pstmt.setInt(3, pk.get("codigo"));
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new SQLException(ex);
        }
    }

    @Override
    public void delete(Map<String, Integer> pk) throws SQLException {
        String sql = "DELETE FROM disciplina WHERE codigo=?";
        try {
            PreparedStatement pstmt = this.conn.prepareStatement(sql);
            pstmt.setInt(1, pk.get("codigo"));
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new SQLException(ex);
        }
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
