import javax.swing.JOptionPane;
import DAO.ProdutoDAO;
import entity.Produto;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        
        while (true) {
            String escolha = JOptionPane.showInputDialog(
                "Escolha uma opção:\n" +
                "1. Cadastrar Produto\n" +
                "2. Listar Produtos\n" +
                "3. Atualizar Produto\n" +
                "4. Deletar Produto\n" +
                "5. Sair"
            );
            
            if (escolha == null) break; // Se o usuário clicar em cancelar ou fechar a janela
            
            switch (escolha) {
                case "1":
                    cadastrarProduto(produtoDAO);
                    break;
                case "2":
                    String listaProdutos = produtoDAO.listarProdutos();
                    JOptionPane.showMessageDialog(null, listaProdutos);
                    break;
                case "3":
                    atualizarProduto(produtoDAO);
                    break;
                case "4":
                    deletarProduto(produtoDAO);
                    break;
                case "5":
                    System.exit(0); // Sair do programa
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida!");
            }
        }
    }
    
    private static void cadastrarProduto(ProdutoDAO produtoDAO) {
        Produto produto = new Produto();
        produto.setNome(JOptionPane.showInputDialog("Digite o nome do produto:"));
        produto.setQtd(Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade do produto:")));
    
        boolean validDate = false;
        LocalDate dataEntrega = null;
        while (!validDate) {
            String dataString = JOptionPane.showInputDialog("Digite a data de entrega do produto (DD/MM/YYYY):");
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                dataEntrega = LocalDate.parse(dataString, formatter);
                validDate = true;
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(null, "Formato de data inválido. Por favor, insira a data no formato DD/MM/YYYY.");
            }
        }
        
        // Convertendo a data para o formato SQL (YYYY-MM-DD)
        java.sql.Date dataEntregaSQL = java.sql.Date.valueOf(dataEntrega);
        produto.setData_entrega(dataEntregaSQL);
    
        // Chamando o método para cadastrar o produto
        produtoDAO.cadastrarProduto(produto);
    
        JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
    }
    
    
    

    
    
    private static void atualizarProduto(ProdutoDAO produtoDAO) {
        // Listar os produtos disponíveis
        String listaProdutos = produtoDAO.listarProdutos();
        JOptionPane.showMessageDialog(null, listaProdutos);
    
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do produto que deseja atualizar:"));
    
            Produto produto = produtoDAO.buscarProduto(id);
            if (produto == null) {
                JOptionPane.showMessageDialog(null, "Produto não encontrado.");
                return;
            }
    
            String novoNome = JOptionPane.showInputDialog("Digite o novo nome do produto:\n(" + produto.getNome() + ")");
            if (novoNome != null && !novoNome.isEmpty()) {
                produto.setNome(novoNome);
            }
    
            String novaQuantidadeStr = JOptionPane.showInputDialog("Digite a nova quantidade do produto:\n(" + produto.getQtd() + ")");
            if (novaQuantidadeStr != null && !novaQuantidadeStr.isEmpty()) {
                int novaQuantidade = Integer.parseInt(novaQuantidadeStr);
                produto.setQtd(novaQuantidade);
            }
    
            boolean validDate = false;
            LocalDate novaDataEntrega = null;
            while (!validDate) {
                String novaDataString = JOptionPane.showInputDialog("Digite a nova data de entrega do produto (DD/MM/YYYY):\n(" + formatarData(produto.getData_entrega()) + ")");
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    novaDataEntrega = LocalDate.parse(novaDataString, formatter);
                    validDate = true;
                } catch (DateTimeParseException e) {
                    JOptionPane.showMessageDialog(null, "Formato de data inválido. Por favor, insira a data no formato DD/MM/YYYY.");
                }
            }
    
            // Convertendo a nova data para o formato SQL (YYYY-MM-DD)
            java.sql.Date novaDataEntregaSQL = java.sql.Date.valueOf(novaDataEntrega);
            produto.setData_entrega(novaDataEntregaSQL);
    
            produtoDAO.atualizarProduto(produto);
    
            JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Por favor, insira um ID válido e uma quantidade válida.");
        }
    }
    
    // Método para formatar a data para o formato "DD/MM/YYYY"
    private static String formatarData(java.sql.Date data) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(data);
    }
    
    
    
    
    
    private static void deletarProduto(ProdutoDAO produtoDAO) {
        // Listar os produtos disponíveis
        String listaProdutos = produtoDAO.listarProdutos();
        JOptionPane.showMessageDialog(null, listaProdutos);
    
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do produto que deseja deletar:"));
    
            produtoDAO.deletarProduto(id);
    
            JOptionPane.showMessageDialog(null, "Produto deletado com sucesso!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Por favor, insira um ID válido.");
        }
    }
    
}
