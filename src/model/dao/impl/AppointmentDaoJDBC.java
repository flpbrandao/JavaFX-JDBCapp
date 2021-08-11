package model.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import db.DB;
import db.DBException;
import model.entities.Appointment;

public class AppointmentDaoJDBC implements AppointmentDAO {
	
	private List<Appointment> appList = new ArrayList<>(); 

	@Override
	public void addToBD(Appointment app) {
		
	}

	@Override
	public void updateToBD(Appointment app) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Appointment> searchall() {
		
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			
			conn = DB.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM mysql_appointments.appointments");
			while (rs.next()) {

				Date date = (rs.getDate("Date"));
				String description = (rs.getString("Description"));
				String place = (rs.getString("Place"));
				Appointment app = new Appointment(date, description, place);
				appList.add(app);
				System.out.println(date + " - " + description + " - " + place);
				System.out.println(appList);

			}

		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		}
		
		return appList;
	}

}
