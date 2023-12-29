package DAO.Classe;

import DAO.Interface.UsuarioDaoInterface;
import Modelo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioDaoClasse implements UsuarioDaoInterface {
    private Connection con;

    public UsuarioDaoClasse() throws ErroDao {
        con = ConexaoDao.pegaConexao();
    }

    @Override
    public void inserir(Usuario u) throws ErroDao, SQLException {
        String sql = "INSERT INTO usuario (`idUsuario`,`user`, `password`, `nome`) VALUES (default, ?, ?, ?)";
        try (PreparedStatement stm = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stm.setString(1, u.getLogin());
            stm.setString(2, u.getSenha());
            stm.setString(3, u.getNome());
            stm.executeUpdate();
            ResultSet rs = stm.getGeneratedKeys();

            if (rs.next()) {
                u.setIdUsuario(rs.getInt(1));

            }

        } catch (SQLException e) {
            throw new ErroDao(e);
        }
    }

    @Override
    public void editar(Usuario u) throws ErroDao {
        String sql = "start transaction;" +
                "update usuario set login = ?, senha = ?, nome = ? where idUsuario = ?;" +
                "commit;";
        try(PreparedStatement usuario = con.prepareStatement(sql)){
            usuario.setString(1, u.getLogin());
            usuario.setString(2, u.getSenha());
            usuario.setString(3, u.getNome());
            usuario.setInt(4, u.getIdUsuario());
            usuario.executeUpdate();
        } catch (SQLException e) {
            throw new ErroDao(e);
        }

    }

    @Override
    public Usuario buscar(int codigo) throws ErroDao {
        Usuario u = null;
        String sql = "select * from usuario where `idUsuario` = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setInt(1, codigo);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                u = new Usuario();
                u.setIdUsuario(rs.getInt(1));
                u.setLogin(rs.getString(2));
                u.setSenha(rs.getString(3));
                u.setNome(rs.getString(4));
            }
            rs.close();
        } catch (SQLException e) {
            throw new ErroDao(e);
        }
        return u;
    }



    @Override
    public Usuario verificarLogin(String login, String senha) throws ErroDao {
        Usuario entity;
        if (login != null && senha != null) {
            entity = new Usuario();
            entity.setLogin(login);
            entity.setSenha(senha);
        } else {
            throw new ErroDao("User or Password is null");
        }

        try {
            ArrayList<Usuario> usersList;
            usersList = buscarLista();

            if (usersList.contains(entity)){
                String sql = "SELECT * FROM usuario WHERE user = ?";
                PreparedStatement pstm = con.prepareStatement(sql);
                pstm.setString(1, login);
                ResultSet rs = pstm.executeQuery();

                if(rs.next()){
                    Usuario u = new Usuario();
                    u.setIdUsuario(rs.getInt(1));
                    u.setLogin(rs.getString(2));
                    u.setSenha(rs.getString(3));
                    u.setNome(rs.getString(4));
                    return u;
                } else {
                    return null;
                }
            } else {
                throw new ErroDao("usuario-nao-encontrado");
            }
        } catch (Exception e) {
            throw new ErroDao("erro-na-busca-dos-dados-sql");
        }

    }

    @Override
    public Usuario verificarRegistro(String login) throws ErroDao {
        Usuario usuario;
        if (login != null) {
            usuario = new Usuario();
            usuario.setLogin(login);
        } else {
            throw new ErroDao("usuario-ou-senha-nulos");
        }

        ArrayList<Usuario> usersList;
        try {
                usersList = this.buscarLista();
            if (!usersList.contains(usuario)){
                return usuario;
            } else {
                throw new ErroDao("usuario-ja-existe-no-banco");
            }
        } catch (Exception e) {
            throw new ErroDao(e);
        }

    }

    @Override
    public ArrayList<Usuario> buscarLista() throws ErroDao{
        ArrayList<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM Usuario";

        try (PreparedStatement preparedStatement = con.prepareStatement(sql);
             ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt(1));
                u.setLogin(rs.getString(2));
                u.setSenha(rs.getString(3));
                u.setNome(rs.getString(4));
                usuarios.add(u);
            }
        } catch (SQLException e) {
            throw new ErroDao("Erro durante a busca de usuários.", e);
        }
        return usuarios;
    }

    @Override
    public void sair() throws ErroDao {
        try {
            con.close();
        } catch (SQLException e) {
            throw new ErroDao(e);
        }
    }
}
