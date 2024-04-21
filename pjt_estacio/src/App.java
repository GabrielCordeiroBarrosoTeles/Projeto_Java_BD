import java.sql.Date;
import java.time.LocalDate;
import DAO.ProdutoDAO;
import entity.Produto;

public class App {
    public static void main(String[] args) throws Exception {
        Produto prod = new Produto();
        prod.setNome("2");
        prod.setQtd(2);
        prod.setData_entrega(Date.valueOf(LocalDate.now()));

        new ProdutoDAO().cadastrarProduto(prod);
    }
}
