package Controle.cliente;


import DAO.Classe.ClienteDaoClasse;
import DAO.Classe.ErroDao;
import DAO.Interface.ClienteDaoInterface;
import Modelo.Cliente;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;


@WebServlet(name="alterarcliente", urlPatterns = {"/alterarcliente"})
public class EditarOuDeletarCliente extends HttpServlet{
        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");

            String id = request.getParameter("id");
            String nomeCompleto = request.getParameter("nomecompleto");
            String telefone = request.getParameter("telefone");
            String endereco = request.getParameter("endereco");

            String transaction = request.getParameter("transaction");

            Cliente cliente = new Cliente();
            ArrayList<Cliente> clientes = new ArrayList<>();
            try {
                ClienteDaoInterface dao = new ClienteDaoClasse();

                switch (transaction){
                    case "update":
                        cliente.setId(Integer.parseInt(id));
                        cliente.setNome(nomeCompleto);
                        cliente.setTelefone(telefone);
                        cliente.setEndereco(endereco);
                        dao.editar(cliente);


                        /* Após a atualização, retornaria para a mesma página */
                        /*request.setAttribute("dados", dao.buscar(cliente.getId()));
                        request.setAttribute("dadosOS", dao.buscarOS(cliente.getId()));*/
                        /*RequestDispatcher rd = request.getRequestDispatcher("pageCliente.jsp");*/

                        clientes.add(cliente);
                        request.setAttribute("dados", clientes);
                        dao.sair();

                        RequestDispatcher rd = request.getRequestDispatcher("searchCliente.jsp");
                        rd.forward(request, response);
                        break;
                    case "delete":
                        dao.deletar(Integer.parseInt(id));
                        dao.sair();
                        response.sendRedirect("home.jsp?message=cliente-deletado");
                        break;
                }


            } catch (ErroDao ex) {
                response.sendRedirect("registroCliente.jsp?mensagem=erro-ao-editar-cliente: " + ex.getMessage());
            }

        }
}
