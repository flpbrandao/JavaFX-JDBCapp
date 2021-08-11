package model.dao.impl;

import java.util.List;

import model.entities.Appointment;

public interface AppointmentDAO {

	void addToBD(Appointment app);

	void updateToBD(Appointment app);

	public List<Appointment> searchall();

}
