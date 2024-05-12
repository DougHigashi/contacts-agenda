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

@WebServlet(urlPatterns = { "/Controller", "/main", "/agenda", "/insert", "/select", "/edit", "/delete" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DAO dao = new DAO();
	private JavaBeans contact = new JavaBeans();

	public Controller() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println(action);

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

	// List contacts
	protected void contacts(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<JavaBeans> list = dao.listContacts();
		
		//Sending contacts list to agenda.jsp
		request.setAttribute("contatos", list);
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		rd.forward(request, response);
	}

	// List contacts
	protected void newContact(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		contact.setName(request.getParameter("cttName"));
		contact.setPhone(request.getParameter("phone"));
		contact.setEmail(request.getParameter("email"));
		dao.insertContact(contact);
		response.sendRedirect("main");
	}
	
	// Select contact to edit
		protected void selectContact(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			String id = request.getParameter("idcon");
			
			contact.setId(id);
			
			dao.getContact(contact);
			
			request.setAttribute("id", contact.getId());
			request.setAttribute("name", contact.getName());
			request.setAttribute("phone", contact.getPhone());
			request.setAttribute("email", contact.getEmail());
			
			RequestDispatcher rs = request.getRequestDispatcher("edit.jsp");
			rs.forward(request, response);
		}
		
		protected void editContact(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			contact.setId(request.getParameter("id"));
			contact.setName(request.getParameter("cttName"));
			contact.setPhone(request.getParameter("phone"));
			contact.setEmail(request.getParameter("email"));
			dao.updateContact(contact);
			response.sendRedirect("main");
		}
		
		protected void deleteContact(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			
			contact.setId(request.getParameter("idcon"));
			
			dao.deleteContact(contact);
			response.sendRedirect("main");
		}

}

