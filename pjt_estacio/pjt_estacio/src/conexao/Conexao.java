package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    //Conexão ao BD
    private static final String url = "jdbc:mysql://localhost:3306/pjt_estacio"; // Remova "/produto" da URL
    private static final String user = "root";
    private static final String password = "";

    private static Connection conn;

    public static Connection getConexao() {
        try {
            // Verifica se a conexão é nula ou está fechada
            if (conn == null || conn.isClosed()) {      
                conn = DriverManager.getConnection(url, user, password);
            }
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
