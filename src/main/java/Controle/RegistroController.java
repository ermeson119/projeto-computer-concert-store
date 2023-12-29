package Controle;

import DAO.Classe.ErroDao;
import DAO.Classe.UsuarioDaoClasse;
import DAO.Interface.UsuarioDaoInterface;
import Modelo.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet(name="registro", urlPatterns = {"/registro"})
public class RegistroController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String nameComplete = request.getParameter("namecomplete");
        String userName = request.getParameter("username");
        String senha = request.getParameter("senha");

            if (nameComplete != null && userName != null && senha != null) {
                try {
                    UsuarioDaoInterface dao = new UsuarioDaoClasse();
                    dao.verificarRegistro(userName);

                    Usuario user = new Usuario();
                    user.setNome(nameComplete);
                    user.setLogin(userName);
                    user.setSenha(senha);

                    dao.inserir(user);
                    dao.sair();
                    response.sendRedirect("index.jsp?menssage=registro-completo");
                } catch (Exception ex) {
                    response.sendRedirect("registro.jsp?mensagem=username-error" + ex.getMessage());
                }
            } else {
                response.sendRedirect("registro.jsp?mensagem=some-attribute-is-null");
            }

    }
}