/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpweb;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import simplejdbc.CustomerEntity;
import simplejdbc.DAO;
import simplejdbc.DataSourceFactory;

/**
 *
 * @author pedago
 */
public class StateFrom extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet StateFrom</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StateFrom at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
            
            try {
                DAObis dao = new DAObis(DataSourceFactory.getDataSource());
                List<String> rs = dao.state();

                if (rs == null) {
                        throw new Exception("State inconnu");
                }
                
                out.println("<form action='StateFrom'>");
                out.println("<select name='stateID'>");
                
                for( String state : rs){
                   out.printf("<option> %s </option>",state);
                }  
                out.println("</select> ");
                out.println("<button type=submit>submit</button>");
                out.println("</form>");
                
                String val = request.getParameter("stateID");
                if (val != null) {
                        DAO dao2 = new DAO(DataSourceFactory.getDataSource());
                        List<CustomerEntity> res = dao2.customersInState(val);
                        //CustomerEntity customer = dao.customersInState(val);

                        if (res == null) {
                                throw new Exception("State inconnu");
                        }
                        out.println("<table border=1>");
                        out.println("<tr><th>ID</th><th>Name</th><th>Address</th></tr>");
                        for(int i = 0; i< res.size();i++){
                            // Afficher les propriétés du client
                            out.printf("<tr><td> %d </td> <td> %s </td> <td> %s </td></tr>",
                                (res.get(i)).getCustomerId(),
                                (res.get(i)).getName(),
                                (res.get(i)).getAddressLine1());
                        }
                        out.println("<a href='index.html'>Menu</a>");
                } 
            } catch (Exception e) {
                out.printf("Erreur : %s", e.getMessage());
            }
        }
    }

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
        processRequest(request, response);
    }

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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
