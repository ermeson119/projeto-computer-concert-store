package Controle.servico;


import DAO.Classe.ClienteDaoClasse;
import DAO.Classe.ErroDao;
import DAO.Classe.ServicoDaoClasse;
import DAO.Interface.ClienteDaoInterface;
import DAO.Interface.ServicoDaoInterface;
import Modelo.Cliente;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;


@WebServlet(name="pesquisarservico", urlPatterns = {"/pesquisarservico"})
public class PesquisarServico extends HttpServlet{
        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            try {
                String servicoId = request.getParameter("entity");

                ServicoDaoInterface dao = new ServicoDaoClasse();
                RequestDispatcher rd = null;

                if(servicoId != null) {
                    request.setAttribute("dados", dao.buscar(Integer.parseInt(servicoId)));
                    rd = request.getRequestDispatcher("pageServico.jsp");
                } else {
                    rd = request.getRequestDispatcher("searchServico.jsp");
                }

                rd.forward(request, response);

            } catch (ErroDao ex) {
                response.sendRedirect("searchServico.jsp?mensagem=erro-ao-buscar-servico: " + ex.getMessage());

            }

        }
}
