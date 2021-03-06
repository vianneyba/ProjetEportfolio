package com.vianney.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class Dao {
	
	protected Connection connection;
	protected  boolean ok= true;

	public Dao(Connection uConnection) {
		this.connection = uConnection;
	}
	
	protected String formatDate(LocalDate date) { 
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//		String formattedDate = date.format(formatter);
		
		String formattedDate = date.getYear()+"-"+date.getMonthValue()+"-"+date.getDayOfMonth();
		
		return formattedDate;
	}
		
	protected PreparedStatement initPs(String sql, boolean genered_keys, Object... objects) {
		PreparedStatement ps = null;
		try {
			if (genered_keys) {
				ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			} else {
				ps = connection.prepareStatement(sql);
			}
			for ( int i = 0; i < objects.length; i++ ) {
				ps.setObject( i + 1, objects[i] );
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ps;
	}
	
	protected LocalDate ddnLocalDate(String date) {
		String[] my = date.split("-");
		int jour= Integer.parseInt(my[2]);
		int mois= Integer.parseInt(my[1]);
		int annee= Integer.parseInt(my[0]);
		LocalDate d= LocalDate.of(annee, mois, jour);
		
		return d;
	}
	
	
	
	public boolean isOk() {
		return ok;
	}
	
	public boolean testR(ResultSet r) throws SQLException {
		if(r.next()) {
			r.beforeFirst();
			return true;
		}
		return false;
	}

}
