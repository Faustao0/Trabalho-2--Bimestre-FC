package br.unipar.uniagro;

import br.unipar.uniagro.domain.Marca;
import br.unipar.uniagro.domain.Produto;
import br.unipar.uniagro.enums.ClasseProdutoEnum;
import br.unipar.uniagro.enums.StatusProdutoEnum;
import br.unipar.uniagro.infraestructure.DatabaseConnection;
import br.unipar.uniagro.services.MarcaService;
import br.unipar.uniagro.services.ProdutoService;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Uniagro {

    public static void main(String[] args) {
        
        try {
            /*
            RQF.001 - MANTER MARCA
            RQF.002 - MANTER UM PRODUTO
            RQF.003 - MANTER LOTES DO PRODUTOS(Produto, Dt Fabric.,
            Dt. Validade,
            Numero do Lote(AZ-O1JAJ2,
            Quantidade,
            Observacao))
            RQF.004 - MANTER CLIENTE(Nome, Cpf, Endereco, Telefone)
            RQF.005 - MANTER FORMAS DE PAGAMENTO(Id, Nome)
        
            RQF.006 - REALIZAR VENDA
            */
            
            Marca marca = new Marca();
            marca.setNome(UUID.randomUUID().toString());
            
            MarcaService marcaService = new MarcaService();
            //marcaService.insert(marca);
            
           // Marca update = new Marca();
           // update.setId(8);
           // update.setNome("Diogo Industres Company");
         
            //marcaService.update(update);
            
            //marcaService.deleteById(update.getId());
           
//            ArrayList<Marca> resultado = (ArrayList<Marca>) marcaService.findAll();
//            JOptionPane.showMessageDialog(null,resultado.toString());
//            
             Marca resultadoFindById = marcaService.findById(2);
//            JOptionPane.showMessageDialog(null, resultadoFindById.toString());

             BigDecimal preco = new BigDecimal("17");
             BigDecimal preco2 = new BigDecimal("35.00");

              Produto produto = new Produto();
              produto.setNome("Pc Gamer");
              produto.setVlrPreco(preco);
              produto.setStatus(StatusProdutoEnum.ATIVO);
              produto.setClasse(ClasseProdutoEnum.INSETICIDA);
              produto.setMarca(resultadoFindById);
            
              ProdutoService produtoService = new ProdutoService();
              //sprodutoService.insert(produto);
              
              Produto updateProduto = new Produto();
              updateProduto.setId(8);
              updateProduto.setNome("PC Gamer");
              updateProduto.setVlrPreco(preco2);
              updateProduto.setStatus(StatusProdutoEnum.ATIVO);
              updateProduto.setClasse(ClasseProdutoEnum.INSETICIDA);
              updateProduto.setMarca(resultadoFindById);
              
              //produtoService.update(updateProduto);
              
              //produtoService.deleteById(updateProduto.getId());
              
              ArrayList<Produto> resultadoProduto = (ArrayList<Produto>) produtoService.findAll();
              JOptionPane.showMessageDialog(null, resultadoProduto.toString());
              
              Produto findByIDProduto = produtoService.findById(4);
              JOptionPane.showMessageDialog(null, findByIDProduto.toString());
                      
        } catch (Exception ex) {
            ex.printStackTrace();
        }          
    }
}
