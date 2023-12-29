package DAO.Classe;

import DAO.Interface.OrdemServicoDaoInterface;
import Modelo.Aparelho;
import Modelo.Cliente;
import Modelo.OrdemServico;
import Modelo.Servico;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;


public class OrdemServicoDaoClasse implements OrdemServicoDaoInterface {

    private Connection con;
    public OrdemServicoDaoClasse() throws ErroDao {
        con = ConexaoDao.pegaConexao();
    }


    @Override
    public void inserir(OrdemServico ordemServico) throws ErroDao, ParseException {
        ArrayList<Servico> servicos = null;

        if (!ordemServico.getServicos().isEmpty() ||
            ordemServico.getServicos() != null) {
            servicos = new ArrayList<>(ordemServico.getServicos());
        }

        String sql = "INSERT INTO order_servico (`cliente_idCliente`, `aparelho_idaparelho`, `data_entrada`, `data_saida`, `observacao`, `valor_total`)" +
                " VALUES (?,?,?,?,?,?)";


        try{

            PreparedStatement pstm = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pstm.setInt(1, ordemServico.getCliente().getId());
            pstm.setInt(2, ordemServico.getAparelho().getId());
            pstm.setString(3, ordemServico.getDataEntrada().toString());
            pstm.setString(4, ordemServico.getDataSaida().toString());
            pstm.setString(5, ordemServico.getObservacao());
            pstm.setDouble(6, ordemServico.getValorTotal());
            pstm.executeUpdate();

            ResultSet rs = pstm.getGeneratedKeys();
            if (rs.next()) {
                ordemServico.setId(rs.getInt(1));
            }

            String sqlTwo = "INSERT INTO order_servico_and_servico (`id_order_servico`, `id_servico`) VALUES (?,?)";

            PreparedStatement pstmTwo = con.prepareStatement(sqlTwo);
            for (Servico servico: servicos) {
                pstmTwo.setInt(1, ordemServico.getId());
                pstmTwo.setInt(2, servico.getId());
                pstmTwo.executeUpdate();
            }

        } catch (Exception e) {
            throw new ErroDao(e);
        }
    }

    @Override
    public void editar(OrdemServico os) throws ErroDao {
        String sql = "update order_servico set data_entrada = ?, data_saida = ?, observacao = ? where order_servico_id = ?";
        try(PreparedStatement pstm = con.prepareStatement(sql)){
            pstm.setString(1, os.getDataEntrada().toString());
            pstm.setString(2, os.getDataSaida().toString());
            pstm.setString(3, os.getObservacao());
            pstm.setInt(4, os.getId());
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new ErroDao(e);
        }
    }

    @Override
    public OrdemServico buscar(int codigo) throws ErroDao {
        String sql = "SELECT * FROM order_servico WHERE order_servico_id = ?";
        OrdemServico os = new OrdemServico();

        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, codigo);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                ArrayList<Servico> servicos = new ArrayList<>();
                os.setId(rs.getInt(1));
                os.setDataEntrada(LocalDate.parse(rs.getString(2), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                os.setDataSaida(LocalDate.parse(rs.getString(3), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                os.setObservacao(rs.getString(5));

                String sqlServico = "SELECT s.idServico, s.nome, s.descricao,s.valor FROM servicos as s inner join order_servico_and_servico as osas WHERE osas.id_order_servico = ? and osas.id_servico = s.idServico";
                PreparedStatement pstmServico = con.prepareStatement(sqlServico);
                pstmServico.setInt(1, os.getId());
                ResultSet rsTwo = pstmServico.executeQuery();

                while (rsTwo.next()){
                    Servico s = new Servico();
                    s.setId(rsTwo.getInt(1));
                    s.setNome(rsTwo.getString(2));
                    s.setDescricao(rsTwo.getString(3));
                    s.setValor(rsTwo.getDouble(4));
                    servicos.add(s);
                }
                os.setServicos(servicos);

                String sqlCliente = "SELECT * FROM cliente WHERE idCliente = ?";
                PreparedStatement pstmCliente = con.prepareStatement(sqlCliente);
                pstmCliente.setInt(1, rs.getInt("cliente_idCliente"));
                ResultSet rsCliente = pstmCliente.executeQuery();

                if(rsCliente.next()) {
                    Cliente c = new Cliente();
                    c.setId(rsCliente.getInt(1));
                    c.setNome(rsCliente.getString(2));
                    c.setTelefone(rsCliente.getString(3));
                    c.setEndereco(rsCliente.getString(4));

                    os.setCliente(c);
                }

                String sqlAparelho = "SELECT * FROM aparelho WHERE idaparelho = ?";
                PreparedStatement pstmAparelho = con.prepareStatement(sqlAparelho);
                pstmAparelho.setInt(1, rs.getInt("aparelho_idaparelho"));
                ResultSet rsAparelho = pstmAparelho.executeQuery();

                if(rsAparelho.next()){
                    Aparelho a = new Aparelho();
                    a.setId(rsAparelho.getInt(1));
                    a.setNome(rsAparelho.getString(2));
                    a.setModelo(rsAparelho.getString(3));
                    a.setMarca(rsAparelho.getString(4));
                    a.setNumeroDeSerie(rsAparelho.getString(5));

                    os.setAparelho(a);
                }

            } else {
                return null;
            }

        }catch (Exception e) {
            throw new ErroDao(e.getMessage());
        }
        return os;
    }

    @Override
    public ArrayList<OrdemServico> buscar() throws ErroDao {
        return null;
    }

    @Override
    public void deletar(int idOrdemServico) throws ErroDao {
        String sql = "DELETE FROM order_servico WHERE order_servico_id = ?";
        String sqlTwo = "DELETE FROM order_servico_and_servico WHERE id_order_servico = ?";

        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, idOrdemServico);
            pstm.executeUpdate();
            pstm.close();

            PreparedStatement pstmTwo = con.prepareStatement(sqlTwo);
            pstmTwo.setInt(1, idOrdemServico);
            pstmTwo.executeUpdate();
            pstmTwo.close();
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
