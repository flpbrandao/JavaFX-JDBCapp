package gui;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import db.DBException;
import gui.util.Alerts;
import gui.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.dao.impl.AppointmentDaoJDBC;
import model.entities.Appointment;

public class AppointmentFormController implements Initializable {

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	AppointmentDaoJDBC a1 = new AppointmentDaoJDBC();

	@FXML
	private Button btSubmit;

	@FXML
	private Button btClean;

	@FXML
	private Button btExit;

	@FXML
	private TextField txtDate;

	@FXML
	private TextField txtDescription;

	@FXML
	private TextField txtPlace;

	@FXML
	public void onBtSubmitAction() throws ParseException {

		if ((txtDescription.getText() == "") || (txtDate.getText() == "")) {
			Alerts.showAlert("Required fields missing ", null, "Date and description needs to be filled!",
					AlertType.WARNING);
		} else {
			try {

				Appointment b1 = new Appointment(sdf.parse(txtDate.getText()), txtDescription.getText(),
						txtPlace.getText());
				AppointmentDaoJDBC dao1 = new AppointmentDaoJDBC();
				dao1.addToBD(b1);
				onBtCleanAction();
				Alerts.showAlert("New entry inserted", null, "New appointment was set!", AlertType.CONFIRMATION);
			
			
			} catch (DBException e) {
				Alerts.showAlert("Error inserting", null, "An error happened in DAO association", AlertType.ERROR);
			}

		}
	}

	@FXML
	public void onBtExitAction() {
		// IMPORTANTE: Obtendo o stage atual para manipula��o a partir de um controle
		// qualquer:
		Stage stage = (Stage) btExit.getScene().getWindow();
		stage.close();
	}

	@FXML
	public void onBtCleanAction() {
		txtDate.setText(null);
		txtDescription.setText(null);
		txtPlace.setText(null);

	}

	@Override
	public void initialize(URL url, ResourceBundle rg) {

		Constraints.setTextFieldMaxLength(txtDate, 20);
		Constraints.setTextFieldMaxLength(txtDescription, 80);
		Constraints.setTextFieldMaxLength(txtPlace, 25);

	}
}
