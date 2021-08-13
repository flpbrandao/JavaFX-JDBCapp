package gui;

import java.io.IOException;
import java.net.URL;

import java.util.Date;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.dao.impl.AppointmentDaoJDBC;
import model.entities.Appointment;


public class AppointmentsController implements Initializable {

	AppointmentDaoJDBC a2 = new AppointmentDaoJDBC();
	private ObservableList<Appointment> obsList;
	
	
	@FXML
	private TableView<Appointment> tableViewAppointment;

	@FXML
	private TableColumn<Appointment, String> tableColumnDescription;

	@FXML
	private TableColumn<Appointment, Date> tableColumnDate;

	@FXML
	private TableColumn<Appointment, String> tableColumnPlace;

	@FXML
	private Button btRegister;

	@FXML
	private Button btSearch;

	@FXML
	private void onBtRegisterAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event); // Função para identificar o stage atual e abrir o form abaixo
														// por cima dele, sem substituir.
		createDialogForm("/gui/AppointmentForm.fxml", parentStage);
	}

	@FXML
	private void onBtSearchAction() {
		System.out.println("Button search");
	}

	private void createDialogForm(String absoluteName, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Appointment manager");
			dialogStage.setScene(new Scene(pane));
			dialogStage.initOwner(parentStage);
			dialogStage.setResizable(false);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();

		} catch (IOException e) {
			Alerts.showAlert("IO Exception", null, e.getMessage(), AlertType.ERROR);
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		popTableViewFields(); // Added function to populate tableView on form initialization - worked

		tableColumnDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
		tableColumnDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
		tableColumnPlace.setCellValueFactory(new PropertyValueFactory<>("Place"));

		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewAppointment.prefHeightProperty().bind(stage.heightProperty());

	}

	public void popTableViewFields() { // Section made by my own
		obsList = FXCollections.observableArrayList(a2.searchall());
		tableViewAppointment.setItems(obsList);
	}


}
