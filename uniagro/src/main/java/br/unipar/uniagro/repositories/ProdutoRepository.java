package br.unipar.uniagro.repositories;

import br.unipar.uniagro.domain.Produto;
import br.unipar.uniagro.infraestructure.DatabaseConnection;
import br.unipar.uniagro.enums.ClasseProdutoEnum;
import br.unipar.uniagro.enums.StatusProdutoEnum;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProdutoRepository {
    
    private static final String INSERT = "INSERT INTO produto (nome, vlr_preco, classe, status, marca_id, dt_insercao, dt_atualizacao) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE produto SET nome = ?, vlr_preco = ?, classe = ?, status = ?, marca_id = ?, dt_atualizacao = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM produto WHERE id = ?";
    private static final String FIND_ALL = "SELECT * FROM produto";
    private static final String FIND_BY_ID = "SELECT * FROM produto WHERE id = ?";

    public Produto insert(Produto produto) throws SQLException {
        try (Connection conn = new DatabaseConnection().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, produto.getNome());
            pstmt.setBigDecimal(2, produto.getVlrPreco());
            pstmt.setString(3, produto.getClasse().name());
            pstmt.setString(4, produto.getStatus().name());
            pstmt.setInt(5, produto.getMarca().getId());
            pstmt.setDate(6, new Date(produto.getDtInsercao().getTime()));
            pstmt.setDate(7, new Date(produto.getDtAtualizacao().getTime()));

            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    produto.setId(rs.getInt(1));
                }
            }
        }
        return produto;
    }

    public Produto update(Produto produto) throws SQLException {
        try (Connection conn = new DatabaseConnection().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(UPDATE)) {

            pstmt.setString(1, produto.getNome());
            pstmt.setBigDecimal(2, produto.getVlrPreco());
            pstmt.setString(3, produto.getClasse().name());
            pstmt.setString(4, produto.getStatus().name());
            pstmt.setInt(5, produto.getMarca().getId());
            pstmt.setDate(6, new Date(produto.getDtAtualizacao().getTime()));
            pstmt.setInt(7, produto.getId());

            pstmt.executeUpdate();
        }
        return produto;
    }

    public void delete(Integer id) throws SQLException {
        try (Connection conn = new DatabaseConnection().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(DELETE)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    public List<Produto> findAll() throws SQLException {
        List<Produto> resultados = new ArrayList<>();
        try (Connection conn = new DatabaseConnection().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(FIND_ALL);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setVlrPreco(rs.getBigDecimal("vlr_preco"));
                produto.setClasse(ClasseProdutoEnum.valueOf(rs.getString("classe")));
                produto.setStatus(StatusProdutoEnum.valueOf(rs.getString("status")));
                produto.setMarca(new MarcaRepository().findById(rs.getInt("marca_id")).orElse(null));
                produto.setDtInsercao(rs.getDate("dt_insercao"));
                produto.setDtAtualizacao(rs.getDate("dt_atualizacao"));

                resultados.add(produto);
            }
        }
        return resultados;
    }

    public Optional<Produto> findById(Integer id) throws SQLException {
        Produto produto = null;
        try (Connection conn = new DatabaseConnection().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(FIND_BY_ID)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    produto = new Produto();
                    produto.setId(rs.getInt("id"));
                    produto.setNome(rs.getString("nome"));
                    produto.setVlrPreco(rs.getBigDecimal("vlr_preco"));
                    produto.setClasse(ClasseProdutoEnum.valueOf(rs.getString("classe")));
                    produto.setStatus(StatusProdutoEnum.valueOf(rs.getString("status")));
                    produto.setMarca(new MarcaRepository().findById(rs.getInt("marca_id")).orElse(null));
                    produto.setDtInsercao(rs.getDate("dt_insercao"));
                    produto.setDtAtualizacao(rs.getDate("dt_atualizacao"));
                }
            }
        }
        return Optional.ofNullable(produto);
    }
}
