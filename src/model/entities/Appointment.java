package model.entities;
import java.io.Serializable;
import java.util.Date;

import javafx.scene.control.CheckBox;


public class Appointment implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Date date;
	private String Description;
	private String place;
	private Integer active;
	private CheckBox Update;
	private Integer id;

	public Appointment() {

	}

	public Appointment(Date date, String description, String place) {
		this.date = date;
		Description = description;
		this.place = place;
	}

	public Appointment(Date date, String description, String place, Integer active) {
		this.date = date;
		Description = description;
		this.place = place;
		this.active = active;
	}
	
	public Appointment(Integer id, Date date, String description, String place, Integer active, CheckBox CheckBox) {
		this.date = date;
		Description = description;
		this.place = place;
		this.active = active;
		this.setUpdate(CheckBox);
		this.id = id;
		
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public Date getDate() {
		return (date);
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

	public String getPlace() {
		return this.place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	@Override
	public String toString() {
		return "Appointment [date=" + date + ", Description=" + Description + ", place=" + place + "]";
	}

	public CheckBox getUpdate() {
		return Update;
	}

	public void setUpdate(CheckBox update) {
		Update = update;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	





}
