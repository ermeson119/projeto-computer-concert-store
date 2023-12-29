package DAO.Interface;

import DAO.Classe.ErroDao;
import Modelo.Servico;

import java.util.ArrayList;

public interface ServicoDaoInterface {

    void inserir(Servico s) throws ErroDao;

    Servico verificarRegistro(String nome) throws ErroDao;
    void editar(Servico s) throws ErroDao;

    Servico buscar(int codigo) throws ErroDao;

    ArrayList<Servico> buscar() throws ErroDao;

    void deletar(int idServico) throws ErroDao;

    void sair() throws ErroDao;
}
