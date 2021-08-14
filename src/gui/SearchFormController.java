package gui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.dao.impl.AppointmentDaoJDBC;

public class SearchFormController {
	
	AppointmentDaoJDBC c1 = new AppointmentDaoJDBC();
	SimpleDateFormat sdf3 = new SimpleDateFormat("dd/MM/yyyy");
	
	@FXML
	private Button btSearch;
	
	@FXML
	private TextField txtSearch;
	
	@FXML
	private void onBtSearchAction() throws ParseException {
		Date date = new Date();
		date = sdf3.parse(txtSearch.getText());
				
		c1.searchByDate(date);
	}
	

}
