/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author Muzaffar
 */
@WebServlet(name = "Delete", urlPatterns = {"/delete"})
public class Delete extends HttpServlet {

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = 0;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            throw new NumberFormatException("exception when parsing type integer from string!");
        }
        Users u = sbl.getUserById(id);
        try {
            sbl.removeUser(u);
            System.out.println("deleting is success!!!");
        } catch (Exception e) {
            try {
                throw new Exception("exception when removing user!");
            } catch (Exception ex) {
                Logger.getLogger(Delete.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        List<Users> list = sbl.getAllUsers();
        List<Roles> listr = sbl.getAllRoles();
        request.getSession().setAttribute("usersList", list);
        request.getSession().setAttribute("rolesList", listr);
        request.getRequestDispatcher("adminPage.jsp").forward(request, response);
    }
}
