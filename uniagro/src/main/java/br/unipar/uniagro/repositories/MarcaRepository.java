package br.unipar.uniagro.repositories;

import br.unipar.uniagro.domain.Marca;
import br.unipar.uniagro.infraestructure.DatabaseConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MarcaRepository {
    
    private static final String INSERT = 
            "INSERT INTO MARCA(NOME, DT_INSERCAO, DT_ATUALIZACAO) " +
            "VALUES(?, ?, ?)";
    private static final String UPDATE = 
            "UPDATE MARCA SET NOME = ?, DT_ATUALIZACAO = ? " +
            "WHERE ID = ?";
    private static final String DELETE = 
            "DELETE FROM MARCA WHERE ID = ?";
    private static final String FIND_ALL = 
            "SELECT ID, NOME, DT_INSERCAO, DT_ATUALIZACAO FROM MARCA";
    private static final String FIND_BY_ID = 
            "SELECT ID, NOME, DT_INSERCAO, DT_ATUALIZACAO FROM MARCA WHERE ID = ?";

    public Marca insert(Marca marca) throws SQLException {
        try (Connection conn = new DatabaseConnection().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, marca.getNome());
            pstmt.setDate(2, new Date(marca.getDtInsercao().getTime()));
            pstmt.setDate(3, new Date(marca.getDtAtualizacao().getTime()));

            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    marca.setId(rs.getInt(1));
                }
            }
        }
        return marca;
    }

    public Marca update(Marca marca) throws SQLException {
        try (Connection conn = new DatabaseConnection().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(UPDATE)) {

            pstmt.setString(1, marca.getNome());
            pstmt.setDate(2, new Date(marca.getDtAtualizacao().getTime()));
            pstmt.setInt(3, marca.getId());

            pstmt.executeUpdate();
        }
        return marca;
    }

    public void delete(Integer id) throws SQLException {
        try (Connection conn = new DatabaseConnection().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(DELETE)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    public List<Marca> findAll() throws SQLException {
        List<Marca> resultados = new ArrayList<>();
        try (Connection conn = new DatabaseConnection().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(FIND_ALL);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Marca marca = new Marca();
                marca.setId(rs.getInt("ID"));
                marca.setNome(rs.getString("NOME"));
                marca.setDtInsercao(rs.getDate("DT_INSERCAO"));
                marca.setDtAtualizacao(rs.getDate("DT_ATUALIZACAO"));

                resultados.add(marca);
            }
        }
        return resultados;
    }

    public Optional<Marca> findById(Integer id) throws SQLException {
        Marca marca = null;
        try (Connection conn = new DatabaseConnection().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(FIND_BY_ID)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    marca = new Marca();
                    marca.setId(rs.getInt("ID"));
                    marca.setNome(rs.getString("NOME"));
                    marca.setDtInsercao(rs.getDate("DT_INSERCAO"));
                    marca.setDtAtualizacao(rs.getDate("DT_ATUALIZACAO"));
                }
            }
        }
        return Optional.ofNullable(marca);
    }
}
