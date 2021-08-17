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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dao.impl.AppointmentDaoJDBC;
import model.entities.Appointment;

public class SearchFormController implements Initializable {

	ObservableList<Appointment> obsList;
	AppointmentDaoJDBC c1 = new AppointmentDaoJDBC();
	SimpleDateFormat sdf3 = new SimpleDateFormat("dd/MM/yyyy");

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
	private TableColumn<Appointment, Integer> tableColumnActive;

	@FXML
	private TableColumn<Appointment, Button> tableColumnButton;

	@FXML
	private TextField txtSearch;

	@FXML
	private void onBtSearchAction() {

		Boolean onInit = false;
		popTableView(onInit);
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		Constraints.setTextFieldMaxLength(txtSearch, 10);
		initializeTableView();
		popTableView(true);

	}

	private void initializeTableView() {
		tableColumnDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
		tableColumnDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
		tableColumnPlace.setCellValueFactory(new PropertyValueFactory<>("Place"));
		tableColumnActive.setCellValueFactory(new PropertyValueFactory<>("Active"));
		tableColumnButton.setCellValueFactory(new PropertyValueFactory<>("Update"));

	}

	private void popTableView(Boolean onInit) {
		Date a = null;
		if (txtSearch.getText() == "") {

			try {

				a = processTodayDate(new Date());
			} catch (ParseException e) {
				Alerts.showAlert("Error parsing date", null, "Verify date format", AlertType.ERROR);
			}

		} else {
			try {
				a = sdf3.parse(txtSearch.getText());
			} catch (ParseException e) {
				Alerts.showAlert("Error parsing date", null, "Verify date format", AlertType.ERROR);

			}
		}
		obsList = FXCollections.observableArrayList(c1.searchByDate(a, onInit));
		tbViewSearchAppointments.setItems(obsList);
	}

	private Date processTodayDate(Date todayDate) throws ParseException {
		String date = sdf3.format(todayDate); // Recebe a variável Date que pegou dia e horário no momento da
												// instanciação, e o format do SDF transforma apenas em data
		Date newDate = sdf3.parse(date); // Ao fazer o parse da String com a data sem horário, cria-se uma nova data com
											// horário 00:00:00, q vai ser usado para comparar dias iguais

		return newDate;

	}

}
