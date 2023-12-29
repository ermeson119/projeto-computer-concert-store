package Controle.home;


import DAO.Classe.ErroDao;
import DAO.Classe.ServicoDaoClasse;
import DAO.Interface.ServicoDaoInterface;
import Modelo.Servico;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;


@WebServlet(name="deslogar", urlPatterns = {"/deslogar"})
public class Deslogar extends HttpServlet{
        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            request.getSession().setAttribute("usuario", null);
            rd.forward(request, response);
        }
}
