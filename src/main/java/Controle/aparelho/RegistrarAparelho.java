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


@WebServlet(name="registroaparelho", urlPatterns = {"/registroaparelho"})
public class RegistrarAparelho extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String nome = request.getParameter("nome");
        String modelo = request.getParameter("modelo");
        String marca = request.getParameter("marca");
        String numeroDeSerie = request.getParameter("numerodeserie");

        Aparelho aparelho = new Aparelho();
                try {
                    aparelho.setNome(nome);
                    aparelho.setModelo(modelo);
                    aparelho.setMarca(marca);
                    aparelho.setNumeroDeSerie(numeroDeSerie);

                    AparelhoDaoInterface dao = new AparelhoDaoClasse();
                    dao.verificarRegistro(modelo, numeroDeSerie);
                    dao.inserir(aparelho);
                    dao.sair();
                    request.setAttribute("dados", aparelho);
                    RequestDispatcher rd = request.getRequestDispatcher("pageAparelho.jsp");
                    rd.forward(request, response);
                } catch (ErroDao ex) {
                    response.sendRedirect("registroAparelho.jsp?mensagem=erro-ao-registrar-aparelho: " + ex.getMessage());
                } catch (ServletException ex) {
                    response.sendRedirect("registroAparelho.jsp?mensagem=erro-ao-registrar-aparelho: " + ex.getMessage());
                }

    }
}