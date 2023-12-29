package Controle.aparelho;


import DAO.Classe.AparelhoDaoClasse;
import DAO.Classe.ClienteDaoClasse;
import DAO.Classe.ErroDao;
import DAO.Interface.AparelhoDaoInterface;
import DAO.Interface.ClienteDaoInterface;
import Modelo.Aparelho;
import Modelo.Cliente;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;


@WebServlet(name="pesquisaraparelho", urlPatterns = {"/pesquisaraparelho"})
public class PesquisarAparelho extends HttpServlet{
        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            ArrayList<Aparelho> aparelhos = new ArrayList<>();
            try {
                String searchInfo = request.getParameter("entityinfo");
                String searchType = request.getParameter("searchtype");
                String aparelhoId = request.getParameter("entity");

                AparelhoDaoInterface dao = new AparelhoDaoClasse();
                RequestDispatcher rd = null;



                if(aparelhoId == null) {
                    if (searchInfo != null) {
                        switch (searchType) {
                            case "id":
                                try {
                                    Integer.parseInt(searchInfo);
                                } catch (Exception e) {
                                    rd = request.getRequestDispatcher("searchAparelho.jsp");
                                    rd.forward(request, response);
                                }

                                int id = Integer.parseInt(searchInfo);
                                aparelhos.add(dao.buscar(id));

                                request.setAttribute("dados", aparelhos);
                                break;

                            case "nome":
                                if (searchInfo.isEmpty()) {
                                    break;
                                }
                                String nome = searchInfo;
                                aparelhos.addAll(dao.buscar(nome));

                                request.setAttribute("dados", aparelhos);
                                break;
                        }

                    }

                    rd = request.getRequestDispatcher("searchAparelho.jsp");
                } else {

                    if (aparelhoId.equals("nulo")) {
                        request.setAttribute("dados", null);
                        rd = request.getRequestDispatcher("searchAparelho.jsp");
                        rd.forward(request, response);
                    }

                    request.setAttribute("dados", dao.buscar(Integer.parseInt(aparelhoId)));
                    request.setAttribute("dadosOS", dao.buscarOS(Integer.parseInt(aparelhoId)));
                    rd = request.getRequestDispatcher("pageAparelho.jsp");
                }

                rd.forward(request, response);

            } catch (ErroDao ex) {
                response.sendRedirect("searchAparelho.jsp?mensagem=erro-ao-buscar-cliente: " + ex.getMessage());
            }

        }
}
