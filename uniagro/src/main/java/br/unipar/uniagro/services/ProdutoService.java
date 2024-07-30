package br.unipar.uniagro.services;

import br.unipar.uniagro.domain.Produto;
import br.unipar.uniagro.exceptions.BusinessException;
import br.unipar.uniagro.repositories.ProdutoRepository;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class ProdutoService {
    
    private final ProdutoRepository produtoRepository = new ProdutoRepository();

    public Produto insert(Produto produto) throws Exception, BusinessException {
        if (produto.getId() != null) {
            throw new Exception("Para inserção não deve se informar o ID");
        }

        validate(produto);
        produto.setDtInsercao(new Date());
        produto.setDtAtualizacao(new Date());

        if (produto.getMarca() == null || produto.getMarca().getId() == null) {
            throw new BusinessException("A marca do produto deve ter um ID válido");
        }

        return produtoRepository.insert(produto);
    }

    public Produto update(Produto produto) throws Exception, BusinessException {
        validateId(produto.getId());
        validate(produto);
        produto.setDtAtualizacao(new Date());

        // Verifique se a marca tem um ID válido
        if (produto.getMarca() == null || produto.getMarca().getId() == null) {
            throw new BusinessException("A marca do produto deve ter um ID válido");
        }

        return produtoRepository.update(produto);
    }

    public void deleteById(Integer id) throws Exception {
        validateId(id);
        produtoRepository.delete(id);
    }

    public List<Produto> findAll() throws SQLException {
        return produtoRepository.findAll();
    }

    public Produto findById(Integer id) throws Exception {
        validateId(id);
        return produtoRepository.findById(id).orElseThrow(() -> new Exception("Produto não encontrado"));
    }

    private void validate(Produto produto) throws BusinessException {
        if ((produto.getNome() == null) || (produto.getNome().length() < 3) || (produto.getNome().length() > 60)) {
            throw new BusinessException("Nome é obrigatório e não deve possuir mais do que 60 caracteres e menos do que 3");
        }

        if (produto.getVlrPreco() == null) {
            throw new BusinessException("Informe o Preço do Produto");
        }

        if (produto.getVlrPreco().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException("O preço do Produto deve ser maior que zero");
        }

        if (produto.getClasse() == null) {
            throw new BusinessException("A classe do Produto é obrigatória");
        }

        if (produto.getStatus() == null) {
            throw new BusinessException("O status do Produto é obrigatório");
        }

        if (produto.getMarca() == null) {
            throw new BusinessException("A marca é obrigatória");
        }
    }

    private void validateId(Integer id) throws Exception {
        if (id == null) {
            throw new Exception("O ID é obrigatório.");
        }
    }
}
