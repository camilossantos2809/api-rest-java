package rest.model.dao;

import rest.model.Estudante;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EstudanteDao implements IDao<Estudante> {
    private Connection conn;

    public EstudanteDao(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Estudante> list() {
        try {
            Statement stmt = this.conn.createStatement();
            ResultSet res = stmt.executeQuery("select * from estudante");

            List<Estudante> list = new ArrayList<>();
            while (res.next()) {
                Estudante obj = new Estudante();
                obj.setNome(res.getString("nome"));
                obj.setCodigo(res.getInt("codigo"));
                obj.setEmail(res.getString("email"));
                list.add(obj);
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public void create(Estudante dados) throws SQLException {
        String sql = "INSERT INTO estudante(codigo, nome, email) VALUES(?,?, ?)";
        try {
            PreparedStatement pstmt = this.conn.prepareStatement(sql);
            pstmt.setInt(1, dados.getCodigo());
            pstmt.setString(2, dados.getNome());
            pstmt.setString(3,dados.getEmail());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new SQLException(ex);
        }
    }

    @Override
    public Estudante read(Map<String, Integer> pk) {
        String sql = "select * from estudante where codigo=?";
        try {
            PreparedStatement pstmt = this.conn.prepareStatement(sql);
            pstmt.setInt(1,pk.get("codigo"));

            ResultSet res = pstmt.executeQuery();

            Estudante obj = new Estudante();
            while (res.next() && res.isFirst()){
                obj.setNome(res.getString("nome"));
                obj.setCodigo(res.getInt("codigo"));
                obj.setEmail(res.getString("email"));
            }
            return obj;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public void update(Map<String, Integer> pk, Estudante dadosNovos) throws SQLException {
        String sql = "UPDATE estudante SET codigo=?, nome=?, email=? where codigo=?";
        try {
            PreparedStatement pstmt = this.conn.prepareStatement(sql);
            pstmt.setInt(1, dadosNovos.getCodigo());
            pstmt.setString(2, dadosNovos.getNome());
            pstmt.setString(3,dadosNovos.getEmail());
            pstmt.setInt(4, pk.get("codigo"));
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new SQLException(ex);
        }
    }

    @Override
    public void delete(Map<String, Integer> pk) throws SQLException {
        String sql = "DELETE FROM estudante WHERE codigo=?";
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
        String sql = "CREATE TABLE IF NOT EXISTS estudante(" +
                "  codigo integer PRIMARY KEY," +
                "  nome text NOT NULL," +
                "  email text NOT NULL" +
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
