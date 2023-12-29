package Controle.cliente;


import DAO.Classe.AparelhoDaoClasse;
import DAO.Classe.ClienteDaoClasse;
import DAO.Classe.ErroDao;
import DAO.Classe.ServicoDaoClasse;
import DAO.Interface.AparelhoDaoInterface;
import DAO.Interface.ClienteDaoInterface;
import DAO.Interface.ServicoDaoInterface;
import Modelo.Aparelho;
import Modelo.Cliente;
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
import java.util.ArrayList;


@WebServlet(name="pesquisarcliente", urlPatterns = {"/pesquisarcliente"})
public class PesquisarCliente extends HttpServlet{
        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            ArrayList<Cliente> clientes = new ArrayList<>();
            try {
                String searchInfo = request.getParameter("entityinfo");
                String searchType = request.getParameter("searchtype");
                String clienteId = request.getParameter("entity");

                ClienteDaoInterface dao = new ClienteDaoClasse();
                RequestDispatcher rd = null;



                if(clienteId == null) {
                    if (searchInfo != null) {
                        switch (searchType) {
                            case "id":
                                try {
                                    Integer.parseInt(searchInfo);
                                } catch (Exception e) {
                                    rd = request.getRequestDispatcher("searchCliente.jsp");
                                    rd.forward(request, response);
                                }

                                int id = Integer.parseInt(searchInfo);
                                clientes.add(dao.buscar(id));

                                request.setAttribute("dados", clientes);
                                break;

                            case "nome":
                                if (searchInfo.isEmpty()) {
                                    break;
                                }
                                String nome = searchInfo;
                                clientes.addAll(dao.buscar(nome));

                                request.setAttribute("dados", clientes);
                                break;
                        }

                    }

                    rd = request.getRequestDispatcher("searchCliente.jsp");
                } else {

                    if (clienteId.equals("nulo")) {
                        request.setAttribute("dados", null);
                        rd = request.getRequestDispatcher("searchCliente.jsp");
                        rd.forward(request, response);
                    }

                    request.setAttribute("dados", dao.buscar(Integer.parseInt(clienteId)));
                    request.setAttribute("dadosOS", dao.buscarOS(Integer.parseInt(clienteId)));
                    rd = request.getRequestDispatcher("pageCliente.jsp");
                }

                rd.forward(request, response);

            } catch (ErroDao ex) {
                response.sendRedirect("searchCliente.jsp?mensagem=erro-ao-buscar-cliente: " + ex.getMessage());
            }

        }
}
