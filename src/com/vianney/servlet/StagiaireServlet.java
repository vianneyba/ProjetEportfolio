package com.vianney.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vianney.beans.Stagiaire;
import com.vianney.dao.PortfolioDAO;

public class StagiaireServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public final String PAGE= "/WEB-INF/vue/Stagiaire.jsp";

    public StagiaireServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		com.vianney.HelperSession.direction(request, "vue stagiaire", PAGE);
		
		String[] pathInfo= request.getPathInfo().split("/");
		try {
			long id= Integer.parseInt(pathInfo[1]);
			try {
				PortfolioDAO pDao= new PortfolioDAO((Connection) request.getAttribute("connection"), id);
				Stagiaire stagiaire= pDao.getStagiaire();

				request.setAttribute("stagiaire", stagiaire);	
			} catch (Exception e) {
				System.out.println("n'existe pas");
			}
		} catch (Exception e) {
			
		}
		
		request.getRequestDispatcher("/Index.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
