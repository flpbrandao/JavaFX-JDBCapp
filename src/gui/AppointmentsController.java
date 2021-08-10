package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Appointment;
import model.services.PopulateFields;

public class AppointmentsController implements Initializable {

	@FXML
	private TableView<Appointment> tableViewAppointment;

	@FXML
	private TableColumn<Appointment, String> tableColumnDescription;

	@FXML
	private TableColumn<Appointment, Date> tableColumnDate;

	@FXML
	private TableColumn<Appointment, Date> tableColumnPlace;

	@FXML
	private Button btRegister;

	@FXML
	private void onBtRegisterAction() {
		System.out.println("Button register");
	}

	@FXML
	private void onBtSearchAction() {
		System.out.println("Button search");
	}

	@FXML
	private Button btSearch;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
		System.out.println("Init");
	}

	private void initializeNodes() {
		
		this.popFields(); //Added function to populate tableView on form initialization - worked

		tableColumnDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
		tableColumnDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
		tableColumnPlace.setCellValueFactory(new PropertyValueFactory<>("Place"));

		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewAppointment.prefHeightProperty().bind(stage.heightProperty());

	}

	private void popFields() { //Section made by my own
		PopulateFields popTable = new PopulateFields();
		List<Appointment> listAppointments = new ArrayList<>();
		listAppointments = popTable.populateTableViewList();
		obsList = FXCollections.observableArrayList(listAppointments);
		tableViewAppointment.setItems(obsList);
	}

	private ObservableList<Appointment> obsList;

}
