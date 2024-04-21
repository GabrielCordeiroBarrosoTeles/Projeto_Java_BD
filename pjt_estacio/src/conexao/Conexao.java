package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String url = "jdbc:mysql://localhost:3306/pjt_estacio"; // Remova "/produto" da URL
    private static final String user = "root";
    private static final String password = "";

    private static Connection conn; // Não é necessário inicializar aqui, a conexão será inicializada dentro do método getConexao()

    public static Connection getConexao() {
        try {
            if (conn == null || conn.isClosed()) { // Verifica se a conexão é nula ou está fechada
                conn = DriverManager.getConnection(url, user, password);
            }
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
