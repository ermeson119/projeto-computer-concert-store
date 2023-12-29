package DAO.Interface;

import DAO.Classe.ErroDao;
import Modelo.Cliente;
import Modelo.OrdemServico;
import Modelo.Usuario;

import java.util.ArrayList;

public interface ClienteDaoInterface {
    Cliente inserir(Cliente c) throws ErroDao;
    Cliente verificarRegistro(String nome, String telefone) throws ErroDao;
    void editar(Cliente c) throws ErroDao;
    Cliente buscar(int codigo) throws ErroDao;

    ArrayList<Cliente> buscar(String nome) throws ErroDao;
    ArrayList<Cliente> buscar() throws ErroDao;

    ArrayList<OrdemServico> buscarOS(int idCliente) throws ErroDao;

    void deletar(int idCliente) throws ErroDao;
    void sair() throws ErroDao;
}