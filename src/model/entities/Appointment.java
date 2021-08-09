package model.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Appointment implements Serializable {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	private static final long serialVersionUID = 1L;
	
	private Date date;
	private String Description;
	private String place;
	
	public Appointment () {
		
	}

	public Appointment(Date date, String description, String place) {
		this.date = date;
		Description = description;
		this.place = place;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}
	public String getPlace () {
		return this.place;
	}
	public void setPlace (String place) {
		this.place = place;
	}

	@Override
	public String toString() {
		return "Appointment [date=" + sdf.format(date) + ", Description=" + Description + "]";
	}

}
