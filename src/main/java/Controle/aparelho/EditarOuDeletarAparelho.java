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


@WebServlet(name="alteraraparelho", urlPatterns = {"/alteraraparelho"})
public class EditarOuDeletarAparelho extends HttpServlet{
        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");

            String id = request.getParameter("id");
            String nome = request.getParameter("nome");
            String modelo = request.getParameter("modelo");
            String marca = request.getParameter("marca");
            String numeroDeSerie = request.getParameter("numerodeserie");

            String transaction = request.getParameter("transaction");

            Aparelho a = new Aparelho();
            ArrayList<Aparelho> aparelhos = new ArrayList<>();
            try {
                AparelhoDaoInterface dao = new AparelhoDaoClasse();

                switch (transaction){
                    case "update":

                        a.setId(Integer.parseInt(id));
                        a.setNome(nome);
                        a.setModelo(modelo);
                        a.setMarca(marca);
                        a.setNumeroDeSerie(numeroDeSerie);
                        dao.editar(a);


                        /* Após a atualização, retornaria para a mesma página */
                        /*request.setAttribute("dados", dao.buscar(cliente.getId()));
                        request.setAttribute("dadosOS", dao.buscarOS(cliente.getId()));*/
                        /*RequestDispatcher rd = request.getRequestDispatcher("pageCliente.jsp");*/

                        aparelhos.add(a);
                        request.setAttribute("dados", aparelhos);
                        dao.sair();

                        RequestDispatcher rd = request.getRequestDispatcher("searchAparelho.jsp");
                        rd.forward(request, response);
                        break;
                    case "delete":
                        dao.deletar(Integer.parseInt(id));
                        dao.sair();
                        response.sendRedirect("home.jsp?message=aparelho-deletado");
                        break;
                }


            } catch (ErroDao ex) {
                response.sendRedirect("pageAparelho.jsp?mensagem=erro-ao-alterar-aparelho: " + ex.getMessage());
            }

        }
}
