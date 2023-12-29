package Controle.os;


import DAO.Classe.ErroDao;
import DAO.Classe.OrdemServicoDaoClasse;
import DAO.Classe.ServicoDaoClasse;
import DAO.Interface.OrdemServicoDaoInterface;
import DAO.Interface.ServicoDaoInterface;
import Modelo.OrdemServico;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet(name="pesquisaros", urlPatterns = {"/pesquisaros"})
public class PesquisarOS extends HttpServlet{
        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            RequestDispatcher rd = request.getRequestDispatcher("searchOS.jsp");

            try {
                OrdemServicoDaoInterface dao = new OrdemServicoDaoClasse();
                String osId = request.getParameter("entity");


                if(osId != null) {
                    OrdemServico os = dao.buscar(Integer.parseInt(osId));
                    if (os != null) {
                        request.setAttribute("dados", dao.buscar(Integer.parseInt(osId)));
                        rd = request.getRequestDispatcher("pageOS.jsp");
                    }
                }

            } catch (Exception e) {
                rd = request.getRequestDispatcher("searchOS.jsp");
            }


                rd.forward(request, response);
        }
}
