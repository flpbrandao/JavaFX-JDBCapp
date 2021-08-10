package model.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.entities.Appointment;

public class PopulateFields {

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public List<Appointment> populateTableViewList(){ //Basico: Isso é um método chamado populateList do tipo List<Appointment> e deverá retornar algo do mesmo tipo
		
	List<Appointment> list = new ArrayList<>();
	Date date =  new Date();
	list.add(new Appointment(date, "Meeting at 9 am to vacations handover", "Teams - Remotely"));
	list.add(new Appointment(date, "Vacinate child in local health unit at 15 PM", "Posto de Saude Gil de Gois"));
	list.add(new Appointment(date, "Java course at 7 PM", "Home"));
	
	return list;
	
	
	
	
	}
}
