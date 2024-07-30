package br.unipar.uniagro.services;

import br.unipar.uniagro.domain.Marca;
import br.unipar.uniagro.exceptions.BusinessException;
import br.unipar.uniagro.repositories.MarcaRepository;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class MarcaService {
    
      private final MarcaRepository marcaRepository = new MarcaRepository();

    public Marca insert(Marca marca) throws Exception, BusinessException {
        if (marca.getId() != null) {
            throw new Exception("Para a inserção não deve-se informar um id");
        }

        validate(marca);

        marca.setDtInsercao(new Date());
        marca.setDtAtualizacao(new Date());

        marcaRepository.insert(marca);

        return marca;
    }
    
    public Marca update(Marca marca) throws Exception, BusinessException {
        validateId(marca.getId());
        validate(marca);

        marca.setDtAtualizacao(new Date());

        marcaRepository.update(marca);

        return marca;
    }
    
    public void deleteById(Integer id) throws Exception {
        validateId(id);
        marcaRepository.delete(id);
    }
    
    public List<Marca> findAll() throws SQLException {
        return marcaRepository.findAll();
    }
    
    public Marca findById(Integer id) throws Exception {
        validateId(id);
        Optional<Marca> optionalMarca = marcaRepository.findById(id);
        return optionalMarca.orElseThrow(() -> new Exception("Marca não encontrada"));
    }
    
    private void validate(Marca marca) throws BusinessException {
        if ((marca.getNome() == null) || (marca.getNome().isEmpty()) || (marca.getNome().length() < 3)) {
            throw new BusinessException("O nome da marca é obrigatório e deve possuir mais do que 2 caracteres");
        }

        if (marca.getNome().length() > 60) {
            throw new BusinessException("O nome da marca é obrigatório e não deve possuir mais do que 60 caracteres");
        }
    }
    
    private void validateId(Integer id) throws Exception {
        if (id == null) {
            throw new Exception("O ID é obrigatório");
        }
    }
}
