package Controle.servico;


import DAO.Classe.ClienteDaoClasse;
import DAO.Classe.ErroDao;
import DAO.Classe.ServicoDaoClasse;
import DAO.Interface.ClienteDaoInterface;
import DAO.Interface.ServicoDaoInterface;
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


@WebServlet(name="alterarservico", urlPatterns = {"/alterarservico"})
public class EditarOuDeletarServico extends HttpServlet{
        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");


            String id = request.getParameter("id");
            String nome = request.getParameter("nome");
            String descricao = request.getParameter("descricao");
            String valorString = request.getParameter("valor");

            String transaction = request.getParameter("transaction");

            Servico servico = new Servico();
            ArrayList<Servico> servicos = new ArrayList<>();
            try {
                ServicoDaoInterface dao = new ServicoDaoClasse();

                switch (transaction){
                    case "update":
                        servico.setId(Integer.parseInt(id));
                        servico.setNome(nome);
                        servico.setDescricao(descricao);
                        servico.setValor(Double.parseDouble(valorString));
                        dao.editar(servico);


                        /* Após a atualização, retornaria para a mesma página */
                        /*request.setAttribute("dados", dao.buscar(cliente.getId()));
                        request.setAttribute("dadosOS", dao.buscarOS(cliente.getId()));*/
                        /*RequestDispatcher rd = request.getRequestDispatcher("pageCliente.jsp");*/

                        servicos.add(dao.buscar(Integer.parseInt(id)));
                        request.setAttribute("dados", servicos);
                        dao.sair();

                        RequestDispatcher rd = request.getRequestDispatcher("searchServico.jsp");
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
