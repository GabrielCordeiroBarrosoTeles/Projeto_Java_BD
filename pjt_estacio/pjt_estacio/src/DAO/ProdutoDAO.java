package DAO;

// Importações relacionadas ao acesso e manipulação de banco de dados SQL
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

// Importação para utilizar caixas de diálogo do JOptionPane
import javax.swing.JOptionPane;

// Importações relacionadas à conexão com o bd
import conexao.Conexao;

// Importação da entidade Produto
import entity.Produto;

// Importação para realizar a formatação de datas
import java.text.ParseException;
import java.text.SimpleDateFormat;

// Importação para utilizar Listas
import java.util.List;


public class ProdutoDAO {
    
    public void cadastrarProduto(Produto produto){
        String sql = "INSERT INTO produto (NOME, QTD, DATA_ENTREGA) VALUES (?, ?, ?)";
        List<Produto> produtos; // Declare a lista de produtos aqui
        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, produto.getNome());
            ps.setInt(2, produto.getQtd());
            ps.setDate(3, produto.getData_entrega());
            
            ps.execute();
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public String listarProdutos() {
        StringBuilder listaProdutos = new StringBuilder();
        String sql = "SELECT * FROM produto";
        try {
            PreparedStatement ps = Conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("idproduto");
                String nome = rs.getString("nome");
                int qtd = rs.getInt("qtd");
                Date dataEntrega = rs.getDate("data_entrega");
    
                // Convertendo a data do formato SQL (YYYY-MM-DD) para DD/MM/YYYY
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String dataEntregaFormatada = dateFormat.format(dataEntrega);
    
                listaProdutos.append("ID: ").append(id).append(", Nome: ").append(nome).append(", Quantidade: ").append(qtd)
                        .append(", Data de Entrega: ").append(dataEntregaFormatada).append("\n");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return listaProdutos.toString();
    }
    
    
    public Produto buscarProduto(int id) {
        String sql = "SELECT * FROM produto WHERE idproduto = ?";
        try {
            PreparedStatement ps = Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Produto produto = new Produto();
                produto.setIdproduto(rs.getInt("idproduto"));
                produto.setNome(rs.getString("nome"));
                produto.setQtd(rs.getInt("qtd"));
                produto.setData_entrega(rs.getDate("data_entrega"));
                return produto;
            } else {
                return null; // Retorna null se o produto não for encontrado
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar produto: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    
    public void atualizarProduto(Produto produto) {
        String sql = "UPDATE produto SET NOME=?, QTD=?, DATA_ENTREGA=? WHERE idproduto=?";
        try {
            PreparedStatement ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, produto.getNome());
            ps.setInt(2, produto.getQtd());
            ps.setDate(3, produto.getData_entrega());
            ps.setInt(4, produto.getIdproduto());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar produto: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void deletarProduto(int id) {
        String sql = "DELETE FROM produto WHERE idproduto=?";
        try {
            PreparedStatement ps = Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto deletado com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao deletar produto: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static Date stringParaData(String dataString) {
        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            return new Date(formato.parse(dataString).getTime());
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Erro ao converter data: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}
