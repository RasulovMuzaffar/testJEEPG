/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

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

/**
 *
 * @author Muzaffar
 */
@WebServlet(name = "Auth", urlPatterns = {"/auth"})
public class Auth extends HttpServlet {

    @EJB(name = "ManagementSystem")
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
        String login = "";
        String password = "";

        try {
            login = request.getParameter("login");
            password = request.getParameter("password");
        } catch (Exception e) {
            try {
                throw new Exception("exception when request parameter login or password!");
            } catch (Exception ex) {
                Logger.getLogger(Auth.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        Users u = sbl.getUserByLoginPassword(login, password);
        List<Users> list = sbl.getAllUsers();
        List<Roles> listr = sbl.getAllRoles();
        if (u != null) {
            if (u.getIdRole().getRole().equalsIgnoreCase("admin")) {
                request.getSession().setAttribute("user", u);
                request.getSession().setAttribute("usersList", list);
                request.getSession().setAttribute("rolesList", listr);
                request.getRequestDispatcher("adminPage.jsp").forward(request, response);
            } else {
                request.getSession().setAttribute("user", u);
                request.getRequestDispatcher("userPage.jsp").forward(request, response);
            }
        } else {
            System.out.println("i don't now!!!");
        }
    }

}
