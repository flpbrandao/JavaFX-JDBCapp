package model.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.entities.Appointment;

public class PopulateFields {

	
	public List<Appointment> populateTableViewList(){ //Basico: Isso é um método chamado populateList do tipo List<Appointment> e deverá retornar algo do mesmo tipo
		
	List<Appointment> list = new ArrayList<>();
	Date date = new Date();
	list.add(new Appointment(date, "a", "b"));
	list.add(new Appointment(date, "c", "d"));
	list.add(new Appointment(date, "e", "f"));
	
	return list;
	
	
	
	
	}
}
