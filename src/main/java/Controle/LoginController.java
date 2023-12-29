package Controle;

import DAO.Classe.ErroDao;
import DAO.Classe.UsuarioDaoClasse;
import DAO.Interface.UsuarioDaoInterface;
import Modelo.Usuario;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;



@WebServlet(name="login", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("username");
        String senha = request.getParameter("senha");

        RequestDispatcher rd = null;

            if (login != null && senha != null && login.length() > 0 && senha.length() > 0) {
                try {
                    UsuarioDaoInterface dao = new UsuarioDaoClasse();
                    Usuario user = dao.verificarLogin(login, senha);
                    dao.sair();
                    if (user != null) {
                        rd = request.getRequestDispatcher("home.jsp");
                        request.getSession().setAttribute("usuario", user);
                    } else {
                        rd = request.getRequestDispatcher("index.jsp");
                    }
                        rd.forward(request, response);
                } catch (ErroDao ex) {
                    response.sendRedirect("index.jsp?mensagem=usuario-nao-encontrado");
                }
            } else {
                response.sendRedirect("index.jsp?mensagem=usuario-ou-senha-nulos");
            }

    }
}
