package DAO.Classe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDao {
    public static Connection pegaConexao() throws ErroDao {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/oficina?allowMultiQueries=true&useSSL=false","root","Seni");
        } catch (ClassNotFoundException | SQLException e) {
            throw new ErroDao(e);
        }
    }

    public static void main(String[] args) {
        try {
            ConexaoDao.pegaConexao();
            System.out.print("Conexão Ok");
        } catch (ErroDao e) {
            throw new RuntimeException(e);
        }
    }
}