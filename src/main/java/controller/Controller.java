package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO;
import model.JavaBeans;

// TODO: Auto-generated Javadoc
/**
 * The Class Controller.
 */
@WebServlet(urlPatterns = { "/Controller", "/main", "/agenda", "/insert", "/select", "/edit", "/delete" })
public class Controller extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The dao. */
	private DAO dao = new DAO();
	
	/** The contact. */
	private JavaBeans contact = new JavaBeans();

	/**
	 * Instantiates a new controller.
	 */
	public Controller() {
		super();
	}

	/**
	 * Do get.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		if(action.equals("/main")) {
			contacts(request, response);
		} else if (action.equals("/insert")) {
			newContact(request, response);
		} else if (action.equals("/select")) {
			selectContact(request, response);
		} else if (action.equals("/edit")) {
			editContact(request, response);
		} else if (action.equals("/delete")) {
			deleteContact(request, response);
		} else { 
			response.sendRedirect("index.html");
		}
	}

	/**
	 * Contacts.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void contacts(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<JavaBeans> list = dao.listContacts();
		
		//Sending contacts list to agenda.jsp
		request.setAttribute("contatos", list);
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		rd.forward(request, response);
	}

	/**
	 * New contact.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void newContact(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		contact.setName(request.getParameter("cttName"));
		contact.setPhone(request.getParameter("phone"));
		contact.setEmail(request.getParameter("email"));
		dao.insertContact(contact);
		response.sendRedirect("main");
	}
	
	/**
	 * Select contact.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
		protected void selectContact(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			contact.setId(request.getParameter("idcon"));
			
			dao.getContact(contact);
			
			request.setAttribute("id", contact.getId());
			request.setAttribute("name", contact.getName());
			request.setAttribute("phone", contact.getPhone());
			request.setAttribute("email", contact.getEmail());
			
			RequestDispatcher rs = request.getRequestDispatcher("edit.jsp");
			rs.forward(request, response);
		}
		
		/**
		 * Edits the contact.
		 *
		 * @param request the request
		 * @param response the response
		 * @throws ServletException the servlet exception
		 * @throws IOException Signals that an I/O exception has occurred.
		 */
		protected void editContact(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			contact.setId(request.getParameter("id"));
			contact.setName(request.getParameter("cttName"));
			contact.setPhone(request.getParameter("phone"));
			contact.setEmail(request.getParameter("email"));
			dao.updateContact(contact);
			response.sendRedirect("main");
		}
		
		/**
		 * Delete contact.
		 *
		 * @param request the request
		 * @param response the response
		 * @throws ServletException the servlet exception
		 * @throws IOException Signals that an I/O exception has occurred.
		 */
		protected void deleteContact(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			contact.setId(request.getParameter("idcon"));
			
			dao.deleteContact(contact);
			response.sendRedirect("main");
		}

}