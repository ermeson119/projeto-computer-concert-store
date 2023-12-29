package DAO.Classe;

import DAO.Interface.ServicoDaoInterface;
import Modelo.Cliente;
import Modelo.Servico;
import Modelo.Servico;
import Modelo.Servico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServicoDaoClasse implements ServicoDaoInterface {

    private Connection con;
    public ServicoDaoClasse() throws ErroDao {
        con = ConexaoDao.pegaConexao();
    }


    @Override
    public void inserir(Servico s) throws ErroDao {
        String sql = "insert into servicos (`nome`, `descricao`, `valor`) values (?,?,?)";
        try (PreparedStatement pstm = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstm.setString(1, s.getNome());
            pstm.setString(2, s.getDescricao());
            pstm.setDouble(3, s.getValor());
            pstm.executeUpdate();
            ResultSet rs = pstm.getGeneratedKeys();

            if (rs.next()) {
                s.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            throw new ErroDao(e);
        }
    }

    @Override
    public Servico verificarRegistro(String nome) throws ErroDao {
        Servico servico;
        if (nome != null) {
            servico = new Servico();
            servico.setNome(nome);
        } else {
            throw new ErroDao("dados-do-cliente-nulos");
        }

        ArrayList<Servico> servicosLista;
        try {
            servicosLista = this.buscar();
            if (!servicosLista.contains(servico)){
                return servico;
            } else {
                throw new ErroDao("servico-ja-existe-no-banco");
            }
        } catch (Exception e) {
            throw new ErroDao(e);
        }
    }

    @Override
    public void editar(Servico s) throws ErroDao {
        String sql = "update servicos set nome = ?, descricao = ?, valor = ? where idServico = ?";
        try(PreparedStatement pstm = con.prepareStatement(sql)){
            pstm.setString(1, s.getNome());
            pstm.setString(2, s.getDescricao());
            pstm.setDouble(3, s.getValor());
            pstm.setInt(4, s.getId());
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new ErroDao(e);
        }
    }

    @Override
    public Servico buscar(int codigo) throws ErroDao {
        Servico s = null;
        String sql = "select * from servicos where idServico = ?";
        try(PreparedStatement pstm = con.prepareStatement(sql)){
            pstm.setInt(1, codigo);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()){
                s = new Servico();
                s.setId(rs.getInt(1));
                s.setNome(rs.getString(2));
                s.setDescricao(rs.getString(3));
                s.setValor(rs.getDouble(4));
            }
            rs.close();
        } catch (SQLException e) {
            throw new ErroDao(e);
        }
        return s;
    }

    @Override
    public ArrayList<Servico> buscar() throws ErroDao {
        ArrayList<Servico> servicos = new ArrayList<>();
        String sql = "SELECT * FROM servicos";

        try (PreparedStatement preparedStatement = con.prepareStatement(sql);
             ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                Servico c = new Servico();
                c.setId(rs.getInt(1));
                c.setNome(rs.getString(2));
                c.setDescricao(rs.getString(3));
                c.setValor(rs.getDouble(4));
                servicos.add(c);
            }
        } catch (SQLException e) {
            throw new ErroDao("Erro durante a busca de Servicos.", e);
        }
        return servicos;
    }

    @Override
    public void deletar(int idServico) throws ErroDao {
        String sql = "DELETE FROM servicos WHERE idServico = ?";
        String sqlTwo = "DELETE FROM order_servico_and_servico WHERE id_servico = ?";

        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, idServico);
            pstm.executeUpdate();

            PreparedStatement pstmTwo = con.prepareStatement(sqlTwo);
            pstmTwo.setInt(1, idServico);
            pstmTwo.executeUpdate();

        } catch (Exception e) {
            throw new ErroDao(e.getMessage());
        }
    }

    @Override
    public void sair() throws ErroDao {
        try{
            con.close();
        } catch (SQLException e) {
            throw new ErroDao(e);
        }
    }
}
