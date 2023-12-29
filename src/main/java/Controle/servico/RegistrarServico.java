package Controle.servico;

import DAO.Classe.ClienteDaoClasse;
import DAO.Classe.ErroDao;
import DAO.Classe.ServicoDaoClasse;
import DAO.Interface.ClienteDaoInterface;
import DAO.Interface.ServicoDaoInterface;
import Modelo.Servico;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet(name="registroservico", urlPatterns = {"/registroservico"})
public class RegistrarServico extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");
        String valorString = request.getParameter("valor");

        Servico servico = new Servico();

                try {
                    Double valor = Double.parseDouble(valorString);
                    ServicoDaoInterface dao = new ServicoDaoClasse();
                    dao.verificarRegistro(nome);
                    servico.setNome(nome);
                    servico.setDescricao(descricao);
                    servico.setValor(valor);
                    dao.inserir(servico);
                    dao.sair();

                    response.sendRedirect("home.jsp?message=servico-registrado");
                } catch (ErroDao ex) {
                    response.sendRedirect("registroServico.jsp?mensagem=erro-ao-registrar-servico: " + ex.getMessage());
                }

    }
}