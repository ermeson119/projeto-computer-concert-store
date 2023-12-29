package Controle.home;


import DAO.Classe.AparelhoDaoClasse;
import DAO.Classe.ClienteDaoClasse;
import DAO.Classe.ErroDao;
import DAO.Classe.ServicoDaoClasse;
import DAO.Interface.AparelhoDaoInterface;
import DAO.Interface.ClienteDaoInterface;
import DAO.Interface.ServicoDaoInterface;
import Modelo.Aparelho;
import Modelo.Cliente;
import Modelo.Servico;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;


@WebServlet(name="gosearch", urlPatterns = {"/gosearch"})
public class goSearch extends HttpServlet{
        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            try {
                String value = request.getParameter("search");
                RequestDispatcher rd = null;

                ServicoDaoInterface dao = new ServicoDaoClasse();

                switch (value) {
                    case "os":
                        rd = request.getRequestDispatcher("searchOS.jsp");
                        break;

                    case "servico":
                        ArrayList<Servico> servicosLista = dao.buscar();

                        request.setAttribute("dados", servicosLista);

                        rd = request.getRequestDispatcher("searchServico.jsp");
                        break;
                }

                rd.forward(request, response);
            } catch (ErroDao ex) {
                response.sendRedirect("registroCliente.jsp?mensagem=erro-ao-redirecionar-pagina: " + ex.getMessage());
            }

        }
}
