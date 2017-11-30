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
@WebServlet(name = "Edit", urlPatterns = {"/edit"})
public class Edit extends HttpServlet {

    @EJB
    private SessionBeanLocal sbl;

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        int id = 0;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            throw new NumberFormatException("exception when parsing type integer from string!");
        }
        Users u = sbl.getUserById(id);
        request.setAttribute("u", u);

        List<Roles> listr = sbl.getAllRoles();
        request.setAttribute("rolesList", listr);
        request.getRequestDispatcher("edit.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = 0;
        String fname = "";
        String lname = "";
        String login = "";
        String password = "";
        int idRole = 0;

        try {
            id = Integer.parseInt(request.getParameter("id"));
            System.out.println("id-----");
            fname = request.getParameter("fname");
            lname = request.getParameter("lname");
            login = request.getParameter("login");
            password = request.getParameter("password");
            idRole = Integer.parseInt(request.getParameter("idRole"));
        } catch (NumberFormatException e) {
            throw new NumberFormatException("exception when parsing type integer from string!");
        }
        
        Roles r = sbl.getRoleById(idRole);
        Users u = sbl.getUserById(id);
        u.setFname(fname);
        u.setLname(lname);
        u.setLogin(login);
        u.setPassword(password);
        u.setIdRole(r);
        try {
            sbl.mergeEntity(u);
            System.out.println("changing user is success!!!");
        } catch (Exception e) {
            try {
                throw new Exception("Exception when changing user!");
            } catch (Exception ex) {
                Logger.getLogger(Edit.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        List<Users> list = sbl.getAllUsers();
        List<Roles> listr = sbl.getAllRoles();
        request.getSession().setAttribute("usersList", list);
        request.getSession().setAttribute("rolesList", listr);
        request.getRequestDispatcher("adminPage.jsp").forward(request, response);
    }
}
