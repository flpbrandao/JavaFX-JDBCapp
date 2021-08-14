package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import db.DB;
import db.DBException;
import gui.util.Alerts;
import javafx.scene.control.Alert.AlertType;
import model.entities.Appointment;

public class AppointmentDaoJDBC implements AppointmentDAO {

	private List<Appointment> appList = new ArrayList<>();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //This is MYSQL date format
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

			st.setString(1, datestring); // Setando Date no MySQL por fun��o de setString - Gambiarra
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
		
		appList.clear();

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {

			conn = DB.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM mysql_appointments.appointments WHERE Active = 1 ORDER BY Date");
			while (rs.next()) {

				Date date = (rs.getTimestamp("Date"));
				String datestring = sdf2.format(date);
				Date date1 = (sdf2.parse(datestring));
				
				String description = (rs.getString("Description"));
				String place = (rs.getString("Place"));
				Appointment app = new Appointment(date1, description, place);
				appList.add(app);

			}

		} catch (SQLException | ParseException e) {
			throw new DBException(e.getMessage());
		}

		return appList;
		
	}
	
	@Override
	public List<Appointment> searchByDate(Date date)  {
				
		appList.clear();
		
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection conn = null;
		
		try {
			conn = DB.getConnection();
				
			ps = conn.prepareStatement("SELECT * From appointments"); //Ao inv�s de inserir a data diretamente, � mais simples (mas custa mais) fazer a filtragem na lista total
			
			rs = ps.executeQuery(); // Para resultSet associado ao PreparedStatement, s� pode ser executeQuery e n�o o executeUpdate.
		
			while (rs.next()) { //Filtrando em todos os registros se a data passada como parametro(form) � a mesma lida pelo BD. Se for, cria o objeto Appointment.
				
				Date newDate = new Date();
				newDate = rs.getDate(1);
				if (newDate.getTime()==date.getTime()) {
				
				Appointment d1 = new Appointment();
				d1.setDate(newDate);
				d1.setDescription(rs.getString("Description"));
				d1.setPlace(rs.getString("Place"));
				System.out.println(d1);
				appList.add(d1);
				
				
				}
				
							
				
			}
			Alerts.showAlert("Records found", null, appList.size() + " appointments found on this date.", AlertType.INFORMATION);
		}
				
		catch (SQLException e) {
			throw new DBException (e.getMessage());
				
			}
		return appList;
	}
}
	
	
		
		
	
	


