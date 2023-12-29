package DAO.Interface;

import DAO.Classe.ErroDao;
import Modelo.Usuario;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UsuarioDaoInterface {
    public void inserir(Usuario u) throws ErroDao, SQLException;
    public void editar(Usuario u) throws ErroDao;
    public Usuario buscar(int codigo) throws ErroDao;
    public Usuario verificarLogin(String login, String senha) throws ErroDao;

    public Usuario verificarRegistro(String login) throws ErroDao;

    public ArrayList<Usuario> buscarLista() throws ErroDao;
    public void sair() throws ErroDao;
}
