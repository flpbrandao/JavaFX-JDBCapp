package gui;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import gui.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.dao.impl.AppointmentDaoJDBC;

public class AppointmentFromController implements Initializable {

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	AppointmentDaoJDBC a1 = new AppointmentDaoJDBC();

	;

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
	public void onBtSubmitAction() {
a1.searchall();
	}

	@FXML
	public void onBtExitAction() {
		// IMPORTANTE: Obtendo o stage atual para manipulação a partir de um controle
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
