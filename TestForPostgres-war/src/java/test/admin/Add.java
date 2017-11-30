package test.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import test.beans.SessionBeanLocal;
import test.entities.Roles;
import test.entities.Users;

@WebServlet(name = "Add", urlPatterns = {"/add"})
public class Add extends HttpServlet {

    @EJB
    private SessionBeanLocal sbl;

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fname = "";
        String lname = "";
        String login = "";
        String password = "";
        int idRole = 0;

        try {
            fname = request.getParameter("fname");
            lname = request.getParameter("lname");
            login = request.getParameter("login");
            password = request.getParameter("password");
            idRole = Integer.parseInt(request.getParameter("idRole"));
        } catch (NumberFormatException e) {
            throw new NumberFormatException("exception when parse type integer from string!");
        }

        Roles r = sbl.getRoleById(idRole);
        Users u = new Users();
        u.setFname(fname);
        u.setLname(lname);
        u.setLogin(login);
        u.setPassword(password);
        u.setIdRole(r);
        try {
            sbl.persistEntity(u);
            System.out.println("adding new user is success!!!");
        } catch (Exception e) {
            try {
                throw new Exception("Exception when adding new user!");
            } catch (Exception ex) {
                Logger.getLogger(Add.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        List<Users> list = sbl.getAllUsers();
        List<Roles> listr = sbl.getAllRoles();
        request.getSession().setAttribute("usersList", list);
        request.getSession().setAttribute("rolesList", listr);
        request.getRequestDispatcher("adminPage.jsp").forward(request, response);
    }

}
