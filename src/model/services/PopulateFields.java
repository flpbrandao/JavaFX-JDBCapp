package model.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.dao.impl.AppointmentDaoJDBC;
import model.entities.Appointment;

public class PopulateFields {

	AppointmentDaoJDBC a1 = new AppointmentDaoJDBC();

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public List<Appointment> populateTableViewList() { // Basico: Isso � um m�todo chamado populateList do tipo
														// List<Appointment> e dever� retornar algo do mesmo tipo

		return(a1.searchall());
		

	}
}
