package rest.model.dao;

import rest.model.Nota;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NotaDao implements IDao<Nota> {
    private Connection conn;

    public NotaDao(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Nota> list() {
        try {
            Statement stmt = this.conn.createStatement();
            ResultSet res = stmt.executeQuery("select * from nota");

            List<Nota> list = new ArrayList<>();
            while (res.next()) {
                Nota obj = new Nota();

                list.add(obj);
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public void create(Nota dados) throws SQLException {
        String sql = "INSERT INTO nota(estudante_cod, disciplina_cod, nota, frequencia) " +
                "VALUES(?,?,?,?)";
        try {
            PreparedStatement pstmt = this.conn.prepareStatement(sql);
            pstmt.setInt(1, dados.getEstudante().getCodigo());
            pstmt.setInt(2, dados.getDisciplina().getCodigo());
            pstmt.setDouble(3, dados.getNota());
            pstmt.setDouble(4, dados.getFrequencia());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new SQLException(ex);
        }
    }

    @Override
    public Nota read(Map<String, Integer> pk) {
        String sql = "select * from nota where estudante_cod=? AND disciplina_cod=?";
        try {
            PreparedStatement pstmt = this.conn.prepareStatement(sql);
            pstmt.setInt(1, pk.get("estudante_cod"));
            pstmt.setInt(2, pk.get("disciplina_cod"));

            ResultSet res = pstmt.executeQuery();

            Nota obj = new Nota();
            while (res.next() && res.isFirst()) {
                obj.setNota(res.getDouble("nota"));
                obj.setFrequencia(res.getDouble("frequencia"));
            }
            return obj;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public void update(Map<String, Integer> pk, Nota dadosNovos) throws SQLException {
        String sql = "UPDATE disciplina " +
                "SET estudante_cod=?, disciplina_cod=?, nota=?, frequencia=? " +
                "where estudante_cod=? AND disciplina_cod=?";
        try {
            PreparedStatement pstmt = this.conn.prepareStatement(sql);
            pstmt.setInt(1, dadosNovos.getEstudante().getCodigo());
            pstmt.setInt(2, dadosNovos.getDisciplina().getCodigo());
            pstmt.setDouble(3, dadosNovos.getNota());
            pstmt.setDouble(4, dadosNovos.getFrequencia());
            pstmt.setInt(5, pk.get("estudante_cod"));
            pstmt.setInt(6, pk.get("disciplina_cod"));
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new SQLException(ex);
        }
    }

    @Override
    public void delete(Map<String, Integer> pk) throws SQLException {
        String sql = "DELETE FROM nota WHERE estudante_cod=? AND disciplina_cod=?";
        try {
            PreparedStatement pstmt = this.conn.prepareStatement(sql);
            pstmt.setInt(1, pk.get("estudante_cod"));
            pstmt.setInt(2, pk.get("disciplina_cod"));
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new SQLException(ex);
        }
    }

    public static void createSchema(Connection conn) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS nota (" +
                "  estudante_cod integer," +
                "  disciplina_cod integer," +
                "  nota real NOT NULL," +
                "  frequencia real NOT NULL," +
                "  PRIMARY KEY (disciplina_cod, estudante_cod)," +
                "  FOREIGN KEY (estudante_cod) REFERENCES estudante (codigo)," +
                "  FOREIGN KEY (disciplina_cod) REFERENCES disciplina (codigo)" +
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