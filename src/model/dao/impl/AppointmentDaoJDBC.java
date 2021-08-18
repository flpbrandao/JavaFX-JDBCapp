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
import gui.util.Alerts;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import model.entities.Appointment;

public class AppointmentDaoJDBC implements AppointmentDAO {

	private List<Appointment> appList = new ArrayList<>();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // This is MYSQL date format
	SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public void addToBD(Appointment app) {
		Connection conn = null;
		PreparedStatement st = null;
		try {

			String datestring = sdf.format(app.getDate()); // Gambiarra inserindo a data como string manipulado pelo SDF
															// no campo Date do MySQL

			conn = DB.getConnection();
			st = conn.prepareStatement(
					"INSERT INTO mysql_appointments.appointments (Date, Description, Place, Active) VALUES (?,?,?,?)");

			st.setString(1, datestring); // Setando Date no MySQL por função de setString - Gambiarra
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
		Connection conn = null;
		conn = DB.getConnection();
		try {
			PreparedStatement st = conn
					.prepareStatement("UPDATE mysql_appointments.appointments SET Active = 0 WHERE ID = ? ");
			st.setInt(1, app.getId());
			st.executeUpdate();
		} catch (SQLException e) {

			throw new DBException(e.getMessage());
		}
		 
	}

	@Override
	public List<Appointment> searchall() {

		appList.clear();

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {

			conn = DB.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM mysql_appointments.appointments WHERE Active = 1 ORDER BY Date DESC");
			while (rs.next()) {

				Date date = (rs.getTimestamp("Date"));

				Integer active = rs.getInt(5);
				String description = (rs.getString("Description"));
				String place = (rs.getString("Place"));
				CheckBox b1 = new CheckBox();
				Integer id = rs.getInt(1);
				Appointment app = new Appointment(id, date, description, place, active, b1);
				appList.add(app);

			}
			 

		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		}

		return appList;

	}

	@Override
	public List<Appointment> searchByDate(Date date, Boolean onInit) {

		appList.clear();

		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection conn = null;

		try {
			conn = DB.getConnection();

			ps = conn.prepareStatement("SELECT * From appointments WHERE Active = 1"); // Ao invés de inserir a data diretamente, é mais
																		// simples (mas custa mais) fazer a filtragem na
																		// lista total

			rs = ps.executeQuery(); // Para resultSet associado ao PreparedStatement, só pode ser executeQuery e não
									// o executeUpdate.

			while (rs.next()) { 

				Date newDate = new Date();
				newDate = rs.getDate(2);

				if (newDate.getTime() == date.getTime()) {   // Filtrando em todos os registros se a data passada como parametro(form) é a
					// mesma lida pelo BD. Se for, cria o objeto Appointment.

					Appointment d1 = new Appointment();
					d1.setDate(rs.getTimestamp(2)); // Uso a date pra comparar os gettimes e o time stamp para exibição
					d1.setId(rs.getInt(1));

					d1.setDescription(rs.getString("Description"));
					d1.setPlace(rs.getString("Place"));
					d1.setActive(rs.getInt(5));
					d1.setUpdate(new CheckBox());

					appList.add(d1);

				}

			}
			if (onInit == false) {
				Alerts.showAlert("Records found", null, appList.size() + " appointments found on this date.",
						AlertType.INFORMATION);
			}
			 
		}

		catch (SQLException e) {
			throw new DBException(e.getMessage());

		}
		return appList;
	}
}
