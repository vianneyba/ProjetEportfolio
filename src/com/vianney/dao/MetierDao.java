package com.vianney.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vianney.beans.Metier;

public class MetierDao extends Dao {
	
	private List<Metier> metiers= new ArrayList<Metier>();
	private Metier metier;

	public MetierDao(Connection uConnection) {
		super(uConnection);
	}
	
	public boolean selectByStagiaireIdMetier(long idMetier, long idStagiaire) {
		String sql= "SELECT M.Id AS IdMetier, SM.DateEntree, SM.DateSortie, SM.Description, M.Fonction ";
		sql+= "FROM Stagiaire_Metier AS SM, Metiers AS M ";
		sql+= "WHERE SM.IdStagiaire= ? AND SM.IdMetier= ? AND M.Id= ?";
		
		PreparedStatement ps= initPs(sql, false, idStagiaire, idMetier, idMetier);
		
		ResultSet result;
		try {
			result = ps.executeQuery();
			if (testR(result)) {
				createList(result);
				
				result.close();
				ps.close();
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean SelectByStagiaire(long idStagiaire) {

		String sql= "SELECT M.Id AS IdMetier, SM.DateEntree, SM.DateSortie, SM.Description, M.Fonction ";
		sql+= "FROM Stagiaire_Metier AS SM, Metiers AS M ";
		sql+= "WHERE SM.IdStagiaire= ? AND M.Id= SM.IdMetier";
			
		PreparedStatement ps= initPs(sql, false, idStagiaire);
		ResultSet result;
		
		try {
			result= ps.executeQuery();
			if (testR(result)) {
				createList(result);
				
				result.close();
				ps.close();
				return true;
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return false;
	}
	
	public long add(Metier metier, long idStagiaire) {
				
		String sql = "INSERT INTO  Metiers (Fonction) VALUES (?);";
		
		PreparedStatement ps= initPs(sql, true, metier.getFonction());
		ResultSet result = null;
		try {
			ps.executeUpdate();
			result= ps.getGeneratedKeys();
			if (result.next()) {
				metier.setId(result.getInt(1));
				insertStagiaireMetier(metier, idStagiaire);	
			}			
			return metier.getId();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0; 	
	}
	
	private boolean insertStagiaireMetier(Metier metier, long idStagiaire) {
		String sql= "INSERT INTO Stagiaire_Metier ";
		sql+= "(DateEntree, DateSortie, Description, IdStagiaire, IdMetier) ";
		sql+= "VALUES (?, ?, ?, ?, ?) ";
		
		PreparedStatement ps= initPs(sql, false, metier.getDateEntree(), metier.getDateSortie(), metier.getDescription(), idStagiaire, metier.getId());
		try {
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void addMetierEntreprise(long idMetier, long idEntreprise) {
		String sql= "INSERT INTO Metier_Entreprise (IdMetier, IdEntreprise) VALUES (?, ?)";
		PreparedStatement ps= initPs(sql, false, idMetier, idEntreprise);
		try {
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean selectLikeFonction(long idStagiaire, String likeFonction) {
		String sql= "SELECT M.Id AS IdMetier, SM.DateEntree, SM.DateSortie, SM.Description, M.Fonction ";
		sql+= "FROM Stagiaire_Metier AS SM, Metiers AS M ";
		sql+= "WHERE SM.IdStagiaire= ? AND M.Id= SM.IdMetier AND M.Fonction LIKE ?";
		
		String fonction= "%"+likeFonction+"%";
		
		PreparedStatement ps= initPs(sql, false, idStagiaire, fonction);
		
		ResultSet result;
		try {
			result = ps.executeQuery();
			if (testR(result)) {
				createList(result);
				
				result.close();
				ps.close();
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	private void createList(ResultSet r) {
		try {
			while (r.next()) {
			    Metier metier= new Metier();
			    metier.setId(r.getLong("IdMetier"));
			    metier.setFonction(r.getString("Fonction"));
			    
			    String[] my= r.getString("DateEntree").split("-");
			    metier.setDateEntree(Integer.parseInt(my[0]), Integer.parseInt(my[1]), Integer.parseInt(my[2]));
			    
			    my= r.getString("DateSortie").split("-");
			    metier.setDateSortie(Integer.parseInt(my[0]), Integer.parseInt(my[1]), Integer.parseInt(my[2]));
			    
			    metier.setDescription(r.getString("Description"));

			    metiers.add(metier);    
			}
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Metier> getMetiers() {
		return metiers;
	}
	
	public Metier getMetier() {
		return metier;
	}
}