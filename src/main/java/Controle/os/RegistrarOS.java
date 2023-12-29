package Controle.os;

import DAO.Classe.*;
import DAO.Interface.AparelhoDaoInterface;
import DAO.Interface.OrdemServicoDaoInterface;
import DAO.Interface.ServicoDaoInterface;
import Modelo.OrdemServico;
import Modelo.Servico;
import com.mysql.cj.protocol.a.LocalDateTimeValueEncoder;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;


@WebServlet(name="registroos", urlPatterns = {"/registroos"})
public class RegistrarOS extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        int idCliente = Integer.parseInt(request.getParameter("cliente"));
        int idAparelho = Integer.parseInt(request.getParameter("aparelho"));
        String[] servicos = request.getParameterValues("servico");
        String dataEntrada = request.getParameter("dataentrada");
        String dataSaida = request.getParameter("datasaida");
        String observacao = request.getParameter("observacao");
        OrdemServico os = new OrdemServico();


        try {
            OrdemServicoDaoInterface daoOS = new OrdemServicoDaoClasse();
            ClienteDaoClasse daoCliente = new ClienteDaoClasse();
            AparelhoDaoInterface daoAparelho = new AparelhoDaoClasse();
            ServicoDaoInterface daoServico = new ServicoDaoClasse();

            ArrayList<Servico> servicosLista = new ArrayList<>();

            DateTimeFormatter dtfFrom = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            LocalDate entradaFormatada = LocalDate.parse(dataEntrada, dtfFrom);
            LocalDate saidaFormatada = LocalDate.parse(dataSaida, dtfFrom);

            os.setCliente(daoCliente.buscar(idCliente));
            os.setAparelho(daoAparelho.buscar(idAparelho));
            for (String idServico : servicos) {
                int id = Integer.parseInt(idServico);
                servicosLista.add(daoServico.buscar(id));
            }
            os.setServicos(servicosLista);
            os.setDataEntrada(entradaFormatada);
            os.setDataSaida(saidaFormatada);
            os.setObservacao(observacao);

            daoOS.inserir(os);

            daoOS.sair();
            daoCliente.sair();
            daoAparelho.sair();
            daoServico.sair();
            response.sendRedirect("home.jsp?message=ordem-de-servico-registrada");
        } catch (Exception e) {
            response.sendRedirect("registroOS.jsp?messageerror=" + e.getMessage());
        }

    }
}