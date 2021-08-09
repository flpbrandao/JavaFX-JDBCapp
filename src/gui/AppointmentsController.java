package gui;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Appointment;

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
	}

	private void initializeNodes() {

		tableColumnDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
		tableColumnDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
		tableColumnPlace.setCellValueFactory(new PropertyValueFactory<>("Place"));

		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewAppointment.prefHeightProperty().bind(stage.heightProperty());
	}

}
