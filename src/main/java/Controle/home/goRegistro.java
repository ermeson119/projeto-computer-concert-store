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


@WebServlet(name="goregistro", urlPatterns = {"/goregistro"})
public class goRegistro extends HttpServlet{
        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            try {
                String value = request.getParameter("gopage");
                RequestDispatcher rd = null;

                ClienteDaoInterface daoCliente = new ClienteDaoClasse();
                AparelhoDaoInterface daoAparelho = new AparelhoDaoClasse();
                ServicoDaoInterface daoServico = new ServicoDaoClasse();

                switch (value) {
                    case "Cliente":
                        rd = request.getRequestDispatcher("registroCliente.jsp");
                        break;

                    case "OS":

                        ArrayList<Cliente> clientesLista = daoCliente.buscar();
                        ArrayList<Aparelho> aparelhosLista = daoAparelho.buscar();
                        ArrayList<Servico> servicosLista = daoServico.buscar();

                        request.setAttribute("dadosCliente", clientesLista);
                        request.setAttribute("dadosAparelho", aparelhosLista);
                        request.setAttribute("dadosServico", servicosLista);
                        rd = request.getRequestDispatcher("registroOS.jsp");
                        break;

                    case "Servico":

                        request.setAttribute("dados", daoServico.buscar());
                        rd = request.getRequestDispatcher("registroServico.jsp");
                        break;

                    case "Aparelho":
                        rd = request.getRequestDispatcher("registroAparelho.jsp");
                        break;
                }


                rd.forward(request, response);
            } catch (ErroDao ex) {
                response.sendRedirect("registroCliente.jsp?mensagem=erro-ao-redirecionar: " + ex.getMessage());
            }

        }
}
