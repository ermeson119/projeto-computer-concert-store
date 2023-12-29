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
import java.util.List;


@WebServlet(name="registrocliente", urlPatterns = {"/registrocliente"})
public class RegistrarCliente extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String nomeCompleto = request.getParameter("nomecompleto");
        String telefone = request.getParameter("telefone");
        String endereco = request.getParameter("endereco");


        Cliente cliente = new Cliente();
                try {
                    ClienteDaoInterface dao = new ClienteDaoClasse();
                    dao.verificarRegistro(nomeCompleto, telefone);
                    cliente.setNome(nomeCompleto);
                    cliente.setTelefone(telefone);
                    cliente.setEndereco(endereco);
                    cliente = dao.inserir(cliente);
                    dao.sair();

                    request.setAttribute("dados", cliente);
                    RequestDispatcher rd = request.getRequestDispatcher("pageCliente.jsp");
                    rd.forward(request, response);

                } catch (ErroDao ex) {
                    response.sendRedirect("registroCliente.jsp?mensagem=erro-ao-registrar-cliente: " + ex.getMessage());
                }

    }
}