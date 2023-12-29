package DAO.Classe;

import DAO.Interface.ClienteDaoInterface;
import Modelo.*;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.AbstractQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ClienteDaoClasse implements ClienteDaoInterface {
    private Connection con;
    public ClienteDaoClasse() throws ErroDao {
        con = ConexaoDao.pegaConexao();
    }

    @Override
    public Cliente inserir(Cliente c) throws ErroDao {
        String sql = "insert into cliente (`nome`, `telefone`, `endereco`) values (?,?,?)";
        try (PreparedStatement pstm = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstm.setString(1, c.getNome());
            pstm.setString(2, c.getTelefone());
            pstm.setString(3, c.getEndereco());
            pstm.executeUpdate();
            ResultSet rs = pstm.getGeneratedKeys();

            if (rs.next()) {
                c.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            throw new ErroDao(e);
        }
        return c;
    }


    public Cliente verificarRegistro(String nome, String telefone) throws ErroDao {
        Cliente cliente;
        if (nome != null && telefone != null) {
            cliente = new Cliente();
            cliente.setNome(nome);
            cliente.setTelefone(telefone);
        } else {
            throw new ErroDao("dados-do-cliente-nulos");
        }

        ArrayList<Cliente> clientesLista;
        try {
            clientesLista = this.buscar();
            if (!clientesLista.contains(cliente)){
                return cliente;
            } else {
                throw new ErroDao("cliente-ja-existe-no-banco");
            }
        } catch (Exception e) {
            throw new ErroDao(e);
        }

    }

    @Override
    public void editar(Cliente c) throws ErroDao {
        String sql = "update cliente set nome = ?, telefone = ?, endereco = ? where idCliente = ?;";
        try(PreparedStatement pstm = con.prepareStatement(sql)){
            pstm.setString(1, c.getNome());
            pstm.setString(2, c.getTelefone());
            pstm.setString(3, c.getEndereco());
            pstm.setInt(4, c.getId());
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new ErroDao(e);
        }
    }

    @Override
    public Cliente buscar(int codigo) throws ErroDao {
        Cliente c = null;
        String sql = "select * from cliente where idCliente = ?";
        try(PreparedStatement pstm = con.prepareStatement(sql)){
            pstm.setInt(1, codigo);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()){
                c = new Cliente();
                c.setId(rs.getInt(1));
                c.setNome(rs.getString(2));
                c.setTelefone(rs.getString(3));
                c.setEndereco(rs.getString(4));
            }
            rs.close();
        } catch (SQLException e) {
            throw new ErroDao(e);
        }
        return c;
    }

    @Override
    public ArrayList<Cliente> buscar(String nome) throws ErroDao {
        ArrayList<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente WHERE `nome` LIKE ?";
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, "%"+nome+"%");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setId(rs.getInt(1));
                c.setNome(rs.getString(2));
                c.setTelefone(rs.getString(3));
                c.setEndereco(rs.getString(4));
                clientes.add(c);
            }
        } catch (SQLException e) {
            throw new ErroDao("Erro durante a busca de cliente:" + e);
        }
        return clientes;
    }

    @Override
    public ArrayList<Cliente> buscar() throws ErroDao {
        ArrayList<Cliente> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM cliente";

        try (PreparedStatement preparedStatement = con.prepareStatement(sql);
             ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setId(rs.getInt(1));
                c.setNome(rs.getString(2));
                c.setTelefone(rs.getString(3));
                c.setEndereco(rs.getString(4));
                usuarios.add(c);
            }
        } catch (SQLException e) {
            throw new ErroDao("Erro durante a busca de cliente.", e);
        }
        return usuarios;
    }


    @Override
    public ArrayList<OrdemServico> buscarOS(int idCliente) throws ErroDao{
        String sql = "SELECT * FROM order_servico WHERE cliente_idCliente = ?";
        ArrayList<OrdemServico> osLista = new ArrayList<>();

        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, idCliente);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                ArrayList<Servico> servicos = new ArrayList<>();
                OrdemServico os = new OrdemServico();
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

                osLista.add(os);
                }

        }catch (Exception e) {
            throw new ErroDao(e.getMessage());
        }
        return osLista;
    }





    @Override
    public void deletar(int idCliente) throws ErroDao {
        String sql = "DELETE FROM cliente WHERE idCliente = ?";

            try (PreparedStatement pstm = con.prepareStatement(sql)) {
                pstm.setInt(1, idCliente);
                pstm.executeUpdate();
            } catch (SQLException e) {
                throw new ErroDao(e);
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

//    public static void main(String[] args){
//
//        try{
//            ClienteDaoClasse dao = new ClienteDaoClasse();
//            ArrayList<Cliente> clientes = dao.buscar();
//            for (Cliente c: clientes) {
//                System.out.println(c);
//            }
//        } catch (ErroDao e) {
//            throw new RuntimeException(e);
//        }
//    }
}
