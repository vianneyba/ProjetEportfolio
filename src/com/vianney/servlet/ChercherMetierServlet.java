package com.vianney.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vianney.beans.Metier;
import com.vianney.dao.MetierDao;

public class ChercherMetierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public final String PAGE= 		"/WEB-INF/form/ChercherMetier.jsp";
	public List<Metier> metiers= new ArrayList<>();
       
    public ChercherMetierServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MetierDao mDao= new MetierDao((Connection) request.getAttribute("connection"));
		if(mDao.selectLikeFonction(request.getParameter("recherche"))) {
			metiers= mDao.getMetiers();
			request.setAttribute("metiers", metiers);
			request.setAttribute("ok", true);
		}

		com.vianney.HelperSession.direction(request, "Chercher Metier", PAGE);
		request.getRequestDispatcher("/Index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
