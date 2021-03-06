package com.vianney.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vianney.beans.Metier;
import com.vianney.beans.Stagiaire;
import com.vianney.dao.CompetenceDao;
import com.vianney.dao.MetierDao;

public class AjouterCompetenceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public final String PAGE= 		"/WEB-INF/form/AjouterCompetence.jsp";
	public Metier metier;
	public Stagiaire stagiaire;

    public AjouterCompetenceServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		stagiaire= (Stagiaire) session.getAttribute("user");
		
		String[] pathInfo= request.getPathInfo().split("/");
		
		try {
			long idMetier= Integer.parseInt(pathInfo[1]);
			Connection connection= (Connection) request.getAttribute("connection");
			getMetier(connection, idMetier, stagiaire.getId());
			try {
				long idCompetence= Long.parseLong(request.getParameter("competence"));
				CompetenceDao cDao= new CompetenceDao((Connection) request.getAttribute("connection"));
				cDao.addMetierCompetence(idMetier, idCompetence);
				 
				 String url= request.getContextPath() +"/compte/competence/ajouter/"+ idMetier;
				 response.sendRedirect( url );
				 return;
			} catch (Exception e) {
				System.out.println("erreur");
			}
		} catch (Exception e) {
			
		}
		
		request.setAttribute("metier", metier);
		com.vianney.HelperSession.direction(request, "Ajout de competence", PAGE);
		request.getRequestDispatcher("/Index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		stagiaire= (Stagiaire) session.getAttribute("user");		
		long idMetier= Long.parseLong(request.getParameter("idMetier"));
		
		CompetenceDao cDao= new CompetenceDao((Connection) request.getAttribute("connection"));
		cDao.add(idMetier, request.getParameter("competence"));
	
		String url= request.getContextPath() +"/compte/competence/ajouter/"+ idMetier;
		response.sendRedirect( url );
		return;
	}
	
	private void getMetier(Connection connection, long idMetier, long idStagiaire) {
		MetierDao mDao= new MetierDao(connection);
		
		if(mDao.selectByStagiaireIdMetier(idMetier, idStagiaire)) {
			metier= mDao.getMetier();
		}
	}

}
