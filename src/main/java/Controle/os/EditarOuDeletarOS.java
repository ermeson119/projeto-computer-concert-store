package Controle.os;


import DAO.Classe.ErroDao;
import DAO.Classe.OrdemServicoDaoClasse;
import DAO.Classe.ServicoDaoClasse;
import DAO.Interface.OrdemServicoDaoInterface;
import DAO.Interface.ServicoDaoInterface;
import Modelo.OrdemServico;
import Modelo.Servico;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


@WebServlet(name="alteraros", urlPatterns = {"/alteraros"})
public class EditarOuDeletarOS extends HttpServlet{
        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");


            String id = request.getParameter("id");
            String dataEntrada = request.getParameter("dataentrada");
            String dataSaida = request.getParameter("datasaida");
            String observacao = request.getParameter("observacao");

            String transaction = request.getParameter("transaction");

            OrdemServico os = new OrdemServico();
            try {
                OrdemServicoDaoInterface dao = new OrdemServicoDaoClasse();

                switch (transaction){
                    case "update":

                        DateTimeFormatter dtfFrom = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                        LocalDate entradaFormatada = LocalDate.parse(dataEntrada, dtfFrom);
                        LocalDate saidaFormatada = LocalDate.parse(dataSaida, dtfFrom);

                        os.setId(Integer.parseInt(id));
                        os.setDataEntrada(entradaFormatada);
                        os.setDataSaida(saidaFormatada);
                        os.setObservacao(observacao);
                        dao.editar(os);

                        /* Após a atualização, retornaria para a mesma página */
                        /*request.setAttribute("dados", dao.buscar(cliente.getId()));
                        request.setAttribute("dadosOS", dao.buscarOS(cliente.getId()));*/
                        /*RequestDispatcher rd = request.getRequestDispatcher("pageCliente.jsp");*/

                        dao.sair();
                        RequestDispatcher rd = request.getRequestDispatcher("searchOS.jsp");
                        rd.forward(request, response);
                        break;
                    case "delete":
                        dao.deletar(Integer.parseInt(id));
                        dao.sair();
                        response.sendRedirect("home.jsp?message=servico-deletado");
                        break;
                }


            } catch (ErroDao ex) {
                response.sendRedirect("registroCliente.jsp?mensagem=erro-ao-alterar-servico: " + ex.getMessage());
            }

        }
}
