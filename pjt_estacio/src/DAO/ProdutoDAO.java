package DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date; // Importe Date do pacote java.sql
import conexao.Conexao;
import entity.Produto;

public class ProdutoDAO {
    public void cadastrarProduto(Produto produto){
        String sql = "INSERT INTO produto (NOME, QTD, DATA_ENTREGA) VALUES (?, ?, ?)";
        
        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, produto.getNome());
            ps.setInt(2, produto.getQtd());
            ps.setDate(3, produto.getData_entrega());
            
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
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
}
