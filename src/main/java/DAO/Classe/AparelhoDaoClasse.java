package DAO.Classe;

import DAO.Interface.AparelhoDaoInterface;
import Modelo.Aparelho;
import Modelo.Cliente;
import Modelo.OrdemServico;
import Modelo.Servico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/*
*   Criar o Controller.
*   Setar as infos (name, webServelet).
*   Fazer a verificação de Registro.
*   Inserir novo aparelho.
*
* */


public class AparelhoDaoClasse implements AparelhoDaoInterface {
    private Connection con;
    public AparelhoDaoClasse() throws ErroDao {
        con = ConexaoDao.pegaConexao();
    }

    @Override
    public void inserir(Aparelho a) throws ErroDao {
        String sql = "insert into aparelho (nome, modelo, marca, numero_serie) values (?,?,?,?)";
        try (PreparedStatement pstm = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstm.setString(1, a.getNome());
            pstm.setString(2, a.getModelo());
            pstm.setString(3, a.getMarca());
            pstm.setString(4, a.getNumeroDeSerie());
            pstm.executeUpdate();
            ResultSet rs = pstm.getGeneratedKeys();

            if (rs.next()) {
                a.setId(rs.getInt(1));
            }


        } catch (SQLException e) {
            throw new ErroDao(e);
        }
    }


    public Aparelho verificarRegistro(String modelo, String numeroSerie) throws ErroDao {
        Aparelho aparelho;
        if (modelo != null && numeroSerie != null) {
            aparelho = new Aparelho();
            aparelho.setModelo(modelo);
            aparelho.setNumeroDeSerie(numeroSerie);
        } else {
            throw new ErroDao("dados-do-aparelho-nulos");
        }
        ArrayList<Aparelho> aparelhoLista;
        try {
            aparelhoLista = this.buscar();
            if (!aparelhoLista.contains(aparelho)){
                return aparelho;
            } else {
                throw new ErroDao("aparelho-ja-existe-no-banco");
            }
        } catch (Exception e) {
            throw new ErroDao(e);
        }

    }






    @Override
    public void editar(Aparelho a) throws ErroDao {
        String sql = "update aparelho set nome = ?, modelo = ?, marca = ?, numero_serie = ? WHERE idaparelho = ?";
        try (PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.setString(1, a.getNome());
            pstm.setString(2, a.getModelo());
            pstm.setString(3, a.getMarca());
            pstm.setString(4, a.getNumeroDeSerie());
            pstm.setInt(5, a.getId());
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new ErroDao(e);
        }
    }


    @Override
    public Aparelho buscar(int codigo) throws ErroDao {
        Aparelho a = null;
        String sql = "SELECT * FROM aparelho WHERE idAparelho = ?";

        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setInt(1, codigo);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                a = new Aparelho();
                a.setId(rs.getInt(1));
                a.setNome(rs.getString(2));
                a.setModelo(rs.getString(3));
                a.setMarca(rs.getString(4));
                a.setNumeroDeSerie(rs.getString(5));
            }
        } catch (SQLException e) {
            throw new ErroDao(e);
        }
        return a;
    }



    @Override
    public ArrayList<Aparelho> buscar() throws ErroDao {
        ArrayList<Aparelho> aparelhos = new ArrayList<>();
        String sql = "SELECT * FROM aparelho";


        try (PreparedStatement preparedStatement = con.prepareStatement(sql);
             ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                Aparelho a = new Aparelho();
                a.setId(rs.getInt(1));
                a.setNome(rs.getString(2));
                a.setModelo(rs.getString(3));
                a.setMarca(rs.getString(4));
                a.setNumeroDeSerie(rs.getString(5));
                aparelhos.add(a);
            }
        } catch (SQLException e) {
            throw new ErroDao("erro-durante-busca-do-aparelho", e);
        }
        return aparelhos;
    }



    @Override
    public ArrayList<Aparelho> buscar(String nome) throws ErroDao {
        ArrayList<Aparelho> aparelhos = new ArrayList<>();
        String sql = "SELECT * FROM aparelho WHERE `nome` LIKE ?";
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, "%"+nome+"%");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Aparelho a = new Aparelho();
                a.setId(rs.getInt(1));
                a.setNome(rs.getString(2));
                a.setModelo(rs.getString(3));
                a.setMarca(rs.getString(4));
                a.setNumeroDeSerie(rs.getString(5));
                aparelhos.add(a);
            }
        } catch (SQLException e) {
            throw new ErroDao("Erro durante a busca de cliente:" + e);
        }
        return aparelhos;
    }


    @Override
    public ArrayList<OrdemServico> buscarOS(int idAparelho) throws ErroDao{
        String sql = "SELECT * FROM order_servico WHERE aparelho_idaparelho = ?";
        ArrayList<OrdemServico> osLista = new ArrayList<>();

        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, idAparelho);
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
    public void deletar(int idAparelho) throws ErroDao {
        String sql = "DELETE FROM Aparelho WHERE idAparelho = ?";

        try (PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.setInt(1, idAparelho);
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
//            AparelhoDaoClasse dao = new AparelhoDaoClasse();
//            ArrayList<Aparelho> aparelhos = dao.buscar();
//            for (Aparelho a: aparelhos) {
//                System.out.println(a);
//            }
//        } catch (ErroDao e) {
//            throw new RuntimeException(e);
//        }
//    }
}