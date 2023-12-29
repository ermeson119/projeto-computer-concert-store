package DAO.Interface;

import DAO.Classe.ErroDao;
import Modelo.OrdemServico;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;


public interface OrdemServicoDaoInterface {

    void inserir(OrdemServico ordemServico) throws ErroDao, ParseException;

    void editar(OrdemServico ordemServico) throws ErroDao;

    OrdemServico buscar(int codigo) throws ErroDao;

    ArrayList<OrdemServico> buscar() throws ErroDao;

    void deletar(int idOrdemServico) throws ErroDao;

    public void sair() throws ErroDao;
}
