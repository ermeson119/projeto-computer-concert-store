package DAO.Interface;
import DAO.Classe.ErroDao;
import Modelo.Aparelho;
import Modelo.OrdemServico;

import java.util.ArrayList;

public interface AparelhoDaoInterface {
    void inserir(Aparelho a) throws ErroDao;
    void editar(Aparelho a) throws ErroDao;
    Aparelho buscar(int codigo) throws ErroDao;
    ArrayList<Aparelho> buscar() throws ErroDao;

    ArrayList<Aparelho> buscar(String nome) throws ErroDao;

    ArrayList<OrdemServico> buscarOS(int idAparelho) throws ErroDao;

    public Aparelho verificarRegistro(String modelo, String numeroSerie) throws ErroDao;

    void deletar(int idAparelho) throws ErroDao;

    public void sair() throws ErroDao;
}