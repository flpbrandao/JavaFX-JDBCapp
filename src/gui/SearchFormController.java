package gui;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import gui.util.Alerts;
import gui.util.Constraints;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.dao.impl.AppointmentDaoJDBC;
import model.entities.Appointment;

public class SearchFormController implements Initializable {

	ObservableList<Appointment> obsList;
	AppointmentDaoJDBC c1 = new AppointmentDaoJDBC();
	SimpleDateFormat sdf3 = new SimpleDateFormat("dd/MM/yyyy");

	@FXML
	private ComboBox<Appointment> cmbApp;

	@FXML
	private void onCmbAppAction() {
		cmbApp.setItems(obsList);
		cmbApp.getSelectionModel().selectFirst();
	}

	@FXML
	private Button btSearch;

	@FXML

	private TableView<Appointment> tbViewSearchAppointments;

	@FXML
	private TableColumn<Appointment, String> tableColumnDescription;

	@FXML
	private TableColumn<Appointment, Date> tableColumnDate;

	@FXML
	private TableColumn<Appointment, String> tableColumnPlace;
	
	
	@FXML
	private TextField txtSearch;

	@FXML
	private void onBtSearchAction() {
		try {
			Date date = new Date();
			date = sdf3.parse(txtSearch.getText());

			obsList = FXCollections.observableArrayList(c1.searchByDate(date));
			onCmbAppAction();

		} catch (ParseException e) {
			Alerts.showAlert("Error in data format!", null, "Please type date in the correct format: DD/MM/YYYY!",
					AlertType.ERROR);
			txtSearch.setText(null);
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		Constraints.setTextFieldMaxLength(txtSearch, 10);
		
		tableColumnDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
		tableColumnDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
		tableColumnPlace.setCellValueFactory(new PropertyValueFactory<>("Place"));
		tableColumnPlace.setCellValueFactory(new PropertyValueFactory<>("Action"));
	}

}
