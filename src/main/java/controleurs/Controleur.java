package controleurs;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jbdc.DAO;
import jbdc.DataSourceFactory;
import jbdc.DiscountEntity;

/**
 * Show Client MVC controller
 */
@WebServlet(name = "Controleur", urlPatterns = {"/Controleur"})
public class Controleur extends HttpServlet {

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		try {	
                        String code = request.getParameter("code");
                        String taux = request.getParameter("howmuch");
                        String action = request.getParameter("action");
                        
			DAO dao = new DAO(DataSourceFactory.getDataSource());
                        
			
                        if("ADD".equals(action)){
                            dao.AddDiscount(code, Float.parseFloat(taux));
                        }
                        else if("DELETE".equals(action)){
                            dao.deleteDiscount(code);
                        }
                        List<DiscountEntity> result = dao.discount();
                        // On renseigne un attribut utilisé par la vue
			request.setAttribute("discount", result);
			// On redirige vers la vue
			request.getRequestDispatcher("Jsp-v.jsp").forward(request, response);

		} catch (Exception e) {
			// On renseigne un attribut utilisé par la vue
			request.setAttribute("error", e.getMessage());			
			// On redirige vers la page d'erreur
			request.getRequestDispatcher("views/errorView.jsp").forward(request, response);
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
