package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import db.DB;
import db.DBException;
import model.entities.Appointment;

public class AppointmentDaoJDBC implements AppointmentDAO {

	private List<Appointment> appList = new ArrayList<>();
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	@Override
	public void addToBD(Appointment app) {
		Connection conn = null;
		PreparedStatement st = null;
		try {
			System.out.println(app.getDate());
			conn = DB.getConnection();
			st = conn.prepareStatement("INSERT INTO mysql_appointments.appointments (Date, Description, Place, Active) VALUES (?,?,?,?)");

			st.setDate(1, new java.sql.Date(app.getDate().getTime())); // O método getTime retorna, em long
																		// (milissegundos desde 1970), a data do objeto
			st.setString(2, app.getDescription());
			st.setString(3, app.getPlace());
			st.setInt(4, 1);
			st.executeUpdate();

		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		}

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
			rs = st.executeQuery("SELECT * FROM mysql_appointments.appointments ORDER BY Date");
			while (rs.next()) {

				Date date = (rs.getDate("Date"));
				String description = (rs.getString("Description"));
				String place = (rs.getString("Place"));
				Appointment app = new Appointment(date, description, place);
				appList.add(app);
				

			}

		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		}

		return appList;
	}

}
